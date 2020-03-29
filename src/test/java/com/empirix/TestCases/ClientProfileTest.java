package com.empirix.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.empirix.Base.Common;
import com.empirix.Pages.ClientProfile;
import com.empirix.Pages.Dashboard;

public class ClientProfileTest {
	public WebDriver driver;
	public Common com;
	public com.empirix.Pages.Login Login;
	public ClientProfile ClientProfile;
	public Dashboard Dashboard;

	public ClientProfileTest() throws IOException {
	}

	@BeforeClass
	@Parameters("browserName")
	public void setUp(String browser) throws IOException, InterruptedException {
		Common com = new Common();
		driver = com.initiateBrowser(browser);
		Login = new com.empirix.Pages.Login(driver);
		Dashboard = Login.signIn(com.prop.getProperty("UserName"), com.prop.getProperty("Password"));

	}

	@Test(priority = 1)
	@Parameters("Language")
	public void validateClientName(String language) throws InterruptedException, IOException {
		ClientProfile = Dashboard.languageSelection(language);
		ClientProfile.navigateToClientPage();
		System.out.println("Navigated to Client Page");
//		String Actual = ClientProfile.clientName();
//		String Expected = com.prop.getProperty("ClientName");
//		Assert.assertEquals(Actual, Expected);
	}

	@Test(priority = 2)
	public void validateClientDescription() throws InterruptedException, IOException {
		String Actual = ClientProfile.description();		
		String Expected = "This client is for QA Test purposes";
		Assert.assertEquals(Actual, Expected);
	}
	
	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
}
