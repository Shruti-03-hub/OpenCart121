package testCases;

import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"Sanity",  "master"})
	public void verify_account_registration() throws FileNotFoundException
	{
		
		logger.info("******Execution Started********");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		Thread.sleep(3000);
		hp.clickRegister();
		Thread.sleep(3000);
		logger.info("****** Home Page complete********");
		AccountRegistrationPage ap=new AccountRegistrationPage(driver);
		ap.setFirstName(randomString().toUpperCase());
		ap.setLasttName(randomString().toUpperCase());
		ap.setEmail(randomString()+"@gmail.com");
		ap.setTelephone(randomNumber());
		String password= randomAlphaNumeric();
		ap.setPassword(password);
		ap.setConfirmPassword(password);
		ap.clickPolicy();
		Thread.sleep(3000);
		ap.clickContinue();
		logger.info("****** enter all details in registration page********");
		
		logger.info("****** validating confirm msg********");
		String cmsg=ap.getConfirmationMsg();
		Thread.sleep(3000);
		
		if(cmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}else
		{
			
			logger.error("Test Failed");
			logger.debug("debug test");
			Assert.assertTrue(false);
		}}
		catch(Exception e) {
			
			logger.debug("debug test");	
			//Assert.fail();
			//e.getMessage();		
	}

}}
