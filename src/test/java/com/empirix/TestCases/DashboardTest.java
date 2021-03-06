package com.empirix.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.empirix.Base.Common;
import com.empirix.Pages.Dashboard;
import com.empirix.Pages.Login;

public class DashboardTest {
	public Login Login;
	public Dashboard Dashboard;
	public WebDriver driver;
	public Common com;
	public String language;

	public DashboardTest() throws IOException {
	}

	@BeforeClass
	@Parameters("browserName")
	public void setUp(String browser) throws IOException, InterruptedException {
		Common com = new Common();
		driver = com.initiateBrowser(browser);
		Login = new Login(driver);
		Dashboard = Login.signIn(com.prop.getProperty("UserName"), com.prop.getProperty("Password"));
	}

	@Test(priority = 1)
	public void validateAlertsTab() throws InterruptedException {
		System.out.println("Inside Alert");
		Dashboard.alerts();
		Thread.sleep(3000);
		System.out.println("Actual p1 -->  "+Dashboard.alertsTab());
		Thread.sleep(3000);
		String Actual=Dashboard.alertsTab();
		String Expected="Error Step";	
		Assert.assertEquals(Actual,Expected);
	}

	@Test(priority = 2)
	public void validateTestsTab() throws InterruptedException {
		System.out.println("Inside Validate Tests Lab");
		Dashboard.tests();
		System.out.println("Actual p2 -->  "+Dashboard.testsTab());
		String Actual=Dashboard.testsTab();
		String Expected="Test";//com.prop.getProperty("ExpectedTestsValue");		
		Assert.assertEquals(Actual,Expected);
	}

	@Test(priority = 3)
	public void validateVariablesTab() throws InterruptedException {
		System.out.println("Inside Variables");
		Dashboard.variables();		
		String Actual=Dashboard.variablesTab();
		String Expected="Variable"; //com.prop.getProperty("ExpectedVariablesValue");		
		Assert.assertEquals(Actual, Expected);
	}

	@Test(priority = 4)
	public void validateNotificationsTab() throws InterruptedException {
		System.out.println("Inside Notify");
		Dashboard.notification();		
		String Actual=Dashboard.notificationsTab();
		String Expected="Notification";//com.prop.getProperty("ExpectedNotifyValue");		
		Assert.assertEquals(Actual, Expected);
	}
	
	@Test(priority=5)
	public void validateDashboardTab() throws InterruptedException {
		System.out.println("Inside Dashboard");
		Thread.sleep(3000);
		Dashboard.dashboards();
		String Actual = Dashboard.dashboardTab();
		String Expected="Overall Performance"; //com.prop.getProperty("ExpectedDashboardValue");
		Assert.assertEquals(Actual, Expected);
	}

	@Test(priority=6)
	public void validateJapaneeseLanguageSelection() throws InterruptedException, IOException {
		language = "japaneese";
		Thread.sleep(2000);
		Dashboard.languageSelection(language);
		Thread.sleep(2000);
		if(Dashboard.japaneeseLanguageSelectionValidate()==true) {
			System.out.println("Japaneese Language is Selected");
		}
		
	}

	@Test(priority=7)
	public void validateEnglishLanguageSelection() throws InterruptedException, IOException {
		language = "english";
		Thread.sleep(2000);
		Dashboard.languageSelection(language);
		
		if(Dashboard.englishLanguageSelectionValidate()==true) {
			System.out.println("English Language is Selected");
		}
		
	}

	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
