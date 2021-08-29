package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserFactory {
	private static WebDriver driver;
	private static BrowserType BR;
	private static String url;

//	@BeforeClass
	public static void readConfig() {
		try {
			//We can use any of these: BufferReader, InputStream, FileReader, Scanner
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			url = prop.getProperty("url");
			String temp = prop.getProperty("browser").toUpperCase();
			convertToEnum(temp);
		} catch (IOException e) {
			System.out.printf("This message come from readConfig(), BrowserFactory.\n");
			e.printStackTrace();
		}
		

	}
	private static void convertToEnum(String browserType) {
		if(browserType.equals(BrowserType.CHROME.toString()))
			BR = BrowserType.CHROME;
		else if(browserType.equals(BrowserType.FIREFOX.toString()))
			BR = BrowserType.FIREFOX;
		
		else if(browserType.equals(BrowserType.EDGE.toString()))
			BR = BrowserType.EDGE;
		else 
			BR = null;
	}

//	@BeforeMethod
	public static WebDriver init() {
		readConfig();
		if (BR == BrowserType.FIREFOX)
			fireFox();
		else if (BR == BrowserType.EDGE)
			edge();
		else if (BR == BrowserType.CHROME)
			chrome();
		else {
			System.out.println("Please choose a driver!!");
		}
		driverSetup();
		return driver;
	}

	//@AfterMethod
	public static void closeBrowser() throws InterruptedException {
		wait(3);
		driver.quit();
	}

	private static void driverSetup() {
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private static void edge() {
		System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
	}

	private static void chrome() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	private static void fireFox() {
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	private static void wait(int seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private enum BrowserType {
		CHROME, FIREFOX, EDGE;

	}
}
