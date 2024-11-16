package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter esp;
	public ExtentReports exrep;
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext testcontext) {
	/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Date dt=new Date();
	String currentDateTimestamp=df.format(dt);*/
	
	String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //generated TimeStamp
	repName="Test-Report"+timestamp+".html"; //attach timestamp to report name
	
	esp=new ExtentSparkReporter(".\\reports\\"+repName); ///specifying location of report
	
	esp.config().setDocumentTitle("OpenCart Automation Testing");
	esp.config().setReportName("OpenCart Functional Testing");
	esp.config().setTheme(Theme.DARK);
	
	exrep=new ExtentReports();
	exrep.attachReporter(esp);
	
	//way to hardcode values
	exrep.setSystemInfo("Application Name", "OpenCart");
	exrep.setSystemInfo("Module Name", "Amdin");
	exrep.setSystemInfo("sub-Mudule Name", "Customer");
	
	//getting values dynamically
	exrep.setSystemInfo("username",System.getProperty("user.name"));
	exrep.setSystemInfo("Environment", "QA");
	
	String os=testcontext.getCurrentXmlTest().getParameter("os");
	exrep.setSystemInfo("Operating System", os);
	
	String browser=testcontext.getCurrentXmlTest().getParameter("browser");
	exrep.setSystemInfo("Browser", browser);
	
	List<String> includedGroup=testcontext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroup.isEmpty()) 
	{
	exrep.setSystemInfo("Groups", includedGroup.toString());
	}
}
	
	
	public void onTestSuccess(ITestResult result) {
		test=exrep.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ "Got successfully executed");
	}
	
	
	public void onTestFailure(ITestResult result) {
		test=exrep.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
	
		test.log(Status.FAIL, result.getName()+ "Test Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			String imgpath=new BaseClass().captureScreenshots(result.getName());
			test.addScreenCaptureFromPath(imgpath);
	}catch(Exception e)
		{
		e.getMessage();
		}
	}
	
		public void onTestSkipped(ITestResult result){
		
		test=exrep.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "Test skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
			
		}
		public void onFinish(ITestContext testcontext)   {
			exrep.flush();
			
			
			String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
			File reportFile=new File(pathOfExtentReport);
			
			try {
		        if (reportFile.exists()) {
		            Desktop.getDesktop().browse(reportFile.toURI());
		        } else {
		            System.out.println("File not found: " + reportFile.getAbsolutePath());
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	
	
	


}
