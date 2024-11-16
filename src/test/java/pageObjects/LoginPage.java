package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(id="input-email")
	WebElement txtEmailAddress;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmail(String mail)
	{
		txtEmailAddress.sendKeys(mail);
	}
	public void setPassword(String pass)
	{
		txtPassword.sendKeys(pass);
	}
	public void clickLogin()
	{
		btnLogin.click();
	}
}
