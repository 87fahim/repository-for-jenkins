package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// 1. Find Elemnts
	// The POM only support FindBy class to located elements, as bellow,
	// the Weblement element or By by will no work in POM model
	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	WebElement userName;
	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	WebElement password;
	@FindBy(how = How.XPATH, using = "/html/body/div/div/div/form/div[3]/button")
	WebElement signinBtn;

	// 2. Create interactive methods,
	// for every element we can create one method or individual method for each

	// 2.1 (Recommanded)
	// Individual method for each field
	public void enterUserName(String userName) {
		this.userName.sendKeys(userName);
	}

	public void enterPass(String pass) {
		this.password.sendKeys(pass);
	}

	public void pressLoginBtn() {
		this.signinBtn.click();
	}

	// 2.2 (Not Recommanded)
	// One method for all fields
	public void testLogin(String userName, String pass) {
	
		this.userName.sendKeys(userName);
		this.password.sendKeys(pass);
		this.signinBtn.click();
	}
}
