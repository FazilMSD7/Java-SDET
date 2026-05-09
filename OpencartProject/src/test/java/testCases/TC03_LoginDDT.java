package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "datadriven") // getting DataProvider from diff. class
	public void verify_loginDDT(String email, String password, String exp) {
		logger.info("**TC03_LoginDDT started**");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			MyAccount ac = new MyAccount(driver);
			boolean targetPage = ac.isMyAccountPageExists();
//			Assert.assertEquals(targetPage, true, "Login passed");

			/*
			 * Data valid -> logIn success -> TC passed : logout Data valid -> logIn failed
			 * -> TC failed Data invalid -> logIn success -> TC failed : logout Data invalid
			 * -> login failed -> TC passed
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					Assert.assertTrue(true);
					ac.clickLogout();
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					ac.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("**TC03_LoginDDT finished**");

	}
}
