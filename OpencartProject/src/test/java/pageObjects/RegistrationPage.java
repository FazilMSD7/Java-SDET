package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h1[text()='Register Account']") WebElement registerAccounttxt;
	@FindBy(xpath = "//*[@id='input-firstname']") WebElement firstname;
	@FindBy(xpath = "//*[@id='input-lastname']") WebElement lastname;
	@FindBy(xpath = "//*[@id='input-email']") WebElement email;
	@FindBy(xpath = "//*[@id='input-telephone']") WebElement telephone;
	@FindBy(xpath = "//*[@id='input-password']") WebElement password;
	@FindBy(xpath = "//*[@id='input-confirm']") WebElement confirmPassword;
	@FindBy(xpath = "//*[@name='agree']") WebElement agreeTerms;
	@FindBy(xpath = "//*[@class='btn btn-primary']") WebElement regContinuebtn;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	public String verifyRegisterAccTxt() {registerAccounttxt.getText();
		return registerAccounttxt.getText();
	}
	
	public void setFirstName(String fname) {
		firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		lastname.sendKeys(lname);
	}
	
	public void setEmail(String emailid) {
		email.sendKeys(emailid);
	}
	
	public void setPhoneNo(String ph) {
		telephone.sendKeys(ph);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) {
		confirmPassword.sendKeys(pwd);
	}
	
	public void clickAgreeTerms() {
		agreeTerms.click();
	}
	
	public void clickContinueBtn() {
		regContinuebtn.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	}
}
