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

public class LoginTest {
	public WebDriver driver;
	public Login Login;
	public Common com;
	public Dashboard Dashboard;
		
	public LoginTest() throws IOException {
	}

	@BeforeClass
	@Parameters("browserName")
	public void setUp(String browser) throws IOException, InterruptedException {
		com = new Common();
		driver = com.initiateBrowser(browser);
		Login = new Login(driver);
	}
	
	@Test(priority=1)
	public void validateTitleOnLoginPage() {
		String Expectedtitle="OpenAM - Login";
		String actualTitle= driver.getTitle();
		Assert.assertEquals(actualTitle,Expectedtitle);
	}
	
	@Test(priority=2)
	public void validateSignIn() throws IOException, InterruptedException  {		
		Dashboard=Login.signIn(com.prop.getProperty("UserName"), com.prop.getProperty("Password"));	
		Login.validateDashboardPage();
	}
	
	@Test(priority=3)
	public void validatetitleOnDashboardPage() {
		String Expectedtitles="VoiceWatch";
		String actualTitles= driver.getTitle();
		Assert.assertEquals(actualTitles,Expectedtitles);
	}
	
	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
}
