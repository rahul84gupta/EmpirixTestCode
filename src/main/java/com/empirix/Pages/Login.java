package com.empirix.Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.empirix.Utility.Utility;

public class Login {
	public WebDriver driver;

	public Login(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='text']")
			WebElement username;
	@FindBy(xpath="//input[@type='password']")
			WebElement password;
	@FindBy(xpath="//input[@type='submit']")
			WebElement submit;
	@FindBy(xpath="//h1[contains(text(),'Overall Performance')]")
			WebElement detail;
	@FindBy(xpath = "//a[contains(text(),'Alerts')]")
	WebElement Alerts;
	@FindBy(xpath = "//div[@id='overall']/nav/div/div/h1")
	WebElement textVisible;
	
	public void waitforpage() {
		Utility.waitForElementPresent(driver, detail, "Overall Performance");		
	}
	
	public Dashboard signIn(String UserName, String Password) throws IOException{
		Utility.fluentWait(driver, username);
		username.sendKeys(UserName);
		password.sendKeys(Password);
		submit.click();
		Utility.fluentWait(driver, Alerts);
		return new Dashboard(driver);
	}
	public void validateDashboardPage() {
		Utility.fluentWait(driver, textVisible);
		if(textVisible.isDisplayed()==true) {
			System.out.println("User is successfully Signed In");
		}
		else {
			System.out.println("Issue while SignIn");
		}
	}
	
}
