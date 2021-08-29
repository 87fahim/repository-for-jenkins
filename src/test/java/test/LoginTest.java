package test;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import base.TestBase;
import pages.BasePage;
import pages.DatabasePage;
import pages.LoginPage;
import util.BrowserFactory;
import util.Database;

public class LoginTest extends TestBase{
	WebDriver driver;
	DatabasePage db;

	@Test
	public void validTest(	) throws InterruptedException, IOException, SQLException, ClassNotFoundException {
		

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		loginPage.enterUserName(Database.getDataFromDatabase("email"));
		loginPage.enterPass(Database.getDataFromDatabase("passowrd"));
		Thread.sleep(3000);
		loginPage.pressLoginBtn();

		BasePage.takeScreenshotAtEndOfTest(driver);
	}
}
