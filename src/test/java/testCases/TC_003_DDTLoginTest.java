package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_DDTLoginTest extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class) //getting dataprovider from different package
	public void verify_DDTLogin(String email, String password) {
	SoftAssert as = new SoftAssert(); 
	{
		logger.info("***** Starting TC_003_DDTLoginTest*****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage ap=new MyAccountPage(driver);
		boolean msg=ap.isMyAccountPageExists();
		//Assert.assertTrue(msg);
		
//		if(exp.equalsIgnoreCase("valid"))
//		{
//			if(msg==true)
//			{
//				ap.clickLogout();
//				Assert.assertTrue(true);
//				
//			}else
//			{
//				Assert.assertTrue(false);
//			}
//		}
//		
//		if(exp.equalsIgnoreCase("invalid"))
//		{
//			if(msg==true)
//			{
//				ap.clickLogout();
//				SoftAssert as=new SoftAssert();
//				as.assertFalse(false, "Invalid data");
//			}else
//			{
//				Assert.assertTrue(true);
//			}
		if(msg==true)
		{
			ap.clickLogout();
			
		
		}else {
			as.assertFalse(msg, "Login failed for invalid data: " + email);
		}
		}catch(Exception e)
		{
			Assert.fail();
			logger.error("Test failed due to exception: " + e.getMessage());
		}
		
		logger.info("***** Finished TC_003_DDTLoginTest*****");

	}}
	

}
