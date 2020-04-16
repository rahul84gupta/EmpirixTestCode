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
		private	WebElement username;
	@FindBy(xpath="//input[@type='password']")
	private	WebElement password;
	@FindBy(xpath="//input[@type='submit']")
	private	WebElement submit;
	@FindBy(xpath="//h1[contains(text(),'Overall Performance')]")
	private	WebElement detail;
	@FindBy(xpath = "//a[contains(text(),'Alerts')]")
	private	WebElement Alerts;
	@FindBy(xpath = "//div[@id='overall']/nav/div/div/h1")
	private	WebElement textVisible;
	
	public void waitforpage() {
		Utility.waitForElementPresent(driver, detail, "Overall Performance");		
	}	
	public void validateDashboardPage() {
		Utility.fluentWait(driver, textVisible,30);
		if(textVisible.isDisplayed()==true) {
			System.out.println("User is successfully Signed In");
		}
		else {
			System.out.println("Issue while SignIn");
		}
	}
	public Dashboard signIn(String UserName, String Password) throws IOException{
		Utility.fluentWait(driver, username,30);
		username.sendKeys(UserName);
		password.sendKeys(Password);
		submit.click();
		Utility.fluentWait(driver, Alerts,30);
		return new Dashboard(driver);
	}
	
}
