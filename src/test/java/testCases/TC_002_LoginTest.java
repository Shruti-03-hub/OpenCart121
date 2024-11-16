package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	@Test(groups= { "Regression", "master"})
	public void verify_login()
	{
		
	logger.info("***** Starting LoginTest*****");
	try {
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp= new LoginPage(driver);
	lp.setEmail(p.getProperty("Email"));
	lp.setPassword(p.getProperty("Password"));
	lp.clickLogin();
	
	MyAccountPage ap=new MyAccountPage(driver);
	boolean msg=ap.isMyAccountPageExists();
	Assert.assertTrue(msg);
	//Assert.assertEquals(msg, true, "Login Failed");
	}catch(Exception e)
	{
		Assert.fail();
	}
	
	logger.info("***** Finished LoginTest*****");

}}
