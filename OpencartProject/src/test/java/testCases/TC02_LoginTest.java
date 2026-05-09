package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass{
	
	@Test(groups = {"regression","master"})
	public void verifyLogin() {
		logger.info("**Starting TC02_LoginTest");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(property.getProperty("email"));
		lp.setPassword(property.getProperty("password"));
		lp.clickLogin();
		
		MyAccount ac = new MyAccount(driver);
		boolean targetPage = ac.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true,"Login failed");
		} catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("**Finished TC02_LoginTest");
	}
	
	
}
