package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC01_AccountRegistration extends BaseClass {

	@Test(groups = {"sanity","master"})
	public void verify_account_registration() {
		logger.info("**Starting TC01_AccountRegistration**");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickRegister();
			logger.info("Clicked on Register link");

			RegistrationPage rp = new RegistrationPage(driver);
			String regTxt = rp.verifyRegisterAccTxt();
			Assert.assertEquals("Register Account", regTxt);

			logger.info("Providing user details..");
			rp.setFirstName("Sample");
			rp.setLastName(randomString().toLowerCase());
			rp.setEmail(randomString() + "@mailinator.com"); // randomly generate
			rp.setPhoneNo(randomNumber());

			String password = randomAlphaNumeric(); // pwd randomly
			rp.setPassword(password);
			rp.setConfirmPassword(password);

			rp.clickAgreeTerms();
			rp.clickContinueBtn();

			logger.info("User created");
			String confirmationMsg = rp.getConfirmationMsg();
			Assert.assertEquals("Your Account Has Been Created!", confirmationMsg);
			
		} catch (Exception exception) {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
}
