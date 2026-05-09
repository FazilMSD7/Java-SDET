package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath = "//*[@title='My Account']") WebElement myAccount;
	@FindBy(xpath = "//*[text()='Register']") WebElement registerbtn;
	@FindBy(xpath = "//*[text()='Login']") WebElement loginbtn;
	
	//action mtds
	public void clickMyAccount() {
		myAccount.click();
	}
	
	public void clickRegister() {
		registerbtn.click();
	}
	
	public void clickLogin() {
		loginbtn.click();
	}

}
