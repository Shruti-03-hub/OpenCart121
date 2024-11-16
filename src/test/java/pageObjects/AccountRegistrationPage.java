package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-firstname")
	WebElement txtFirstName;
	
	@FindBy(id="input-lastname")
	WebElement txtLastName;
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//div[@id='content']//h1")
	WebElement msgConfirmation;
	
	//Actions method
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLasttName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String mail)
	{
		txtEmail.sendKeys(mail);
	}
	
	public void setTelephone(String phone)
	{
		txtTelephone.sendKeys(phone);
	}
	
	public void setPassword(String pass)
	{
		txtPassword.sendKeys(pass);
	}
	
	public void setConfirmPassword(String cpass)
	{
		txtConfirmPassword.sendKeys(cpass);
	}
	
	public void clickPolicy()
	{
		chkdPolicy.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public String getConfirmationMsg()
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
			WebElement element = wait.until(ExpectedConditions.visibilityOf(msgConfirmation));
			return msgConfirmation.getText();
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
}
