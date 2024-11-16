package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public static Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity", "Regression", "master"})
	@Parameters({"browser","os"})
	public void Setup( String br, String os) throws IOException {
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			
		{
			String GridURL="http://192.168.1.55:4444/wd/hub";
			DesiredCapabilities cap=new DesiredCapabilities();
			
			switch(os.toLowerCase())
			{
			case "windows" :cap.setPlatform(Platform.WIN11); break;
			case "Mac" : cap.setPlatform(Platform.MAC);
			default : System.out.println("Invalid OS"); return;
			}
			
			switch(br.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome");break;
			case "edge" : cap.setBrowserName("MicrsoftEdge");break;
			//case "firefox" : driver=new FirefoxDriver();break;
			default: System.out.println("Invalid Broswer"); return;
			}
			
			 driver=new RemoteWebDriver(new URL(GridURL),cap);
		}
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox" : driver=new FirefoxDriver();break;
		default: System.out.println("Invalid Broswer"); return;
		}}
		
		
		//driver=new ChromeDriver();
		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	@AfterClass(groups= {"Sanity", "Regression", "master"})
	public void teardown() {
		if(driver!=null)
		{
			driver.quit();
		}
	}
	
	public String randomString()
	{
		String generatedString= RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber()
	{
		String generatedNumber= RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	public String randomAlphaNumeric()
	{
		String generatedString= RandomStringUtils.randomAlphabetic(5);
		String generatedNumber= RandomStringUtils.randomNumeric(10);
		return (generatedString+"@"+generatedNumber);
	}

	public String captureScreenshots(String tname) {
		String timestamp=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourcefile=ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshot\\"+tname+"_"+timestamp+".png";
		File targetFile=new File(targetFilePath);
				
				sourcefile.renameTo(targetFile);
				return targetFilePath;
	}
}
