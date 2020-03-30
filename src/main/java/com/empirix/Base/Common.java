package com.empirix.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.empirix.Utility.WebEventListener;

public class Common {
	public WebDriver driver;
	public Properties prop;
	public EventFiringWebDriver e_driver;
	public WebEventListener eventListener;
	public String Basepath;
	
	public Common() throws IOException {
		Basepath= System.getProperty("user.dir");
		prop = new Properties();
		FileInputStream fis = new FileInputStream(Basepath+"\\src\\main\\java\\com\\empirix\\config\\config.properties");
		prop.load(fis);

	}

	public WebDriver initiateBrowser(String browser) throws IOException, InterruptedException {
		String Browser = browser; // prop.getProperty("browser");

		if (Browser.equals("chrome")) {
//			ChromeOptions co = new ChromeOptions();
//			 co.addArguments("Headless");
			System.setProperty("webdriver.chrome.driver",Basepath+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", Basepath+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		// driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://services.empirix.com");
		Thread.sleep(3000);
		return driver;

	}

}
