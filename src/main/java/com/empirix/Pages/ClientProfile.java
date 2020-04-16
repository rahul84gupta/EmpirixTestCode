package com.empirix.Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.empirix.Utility.Utility;

public class ClientProfile {
	public WebDriver driver;
	public boolean flag;
	
	public ClientProfile(WebDriver driver, boolean flag) throws IOException {
		this.driver = driver;
		this.flag=flag;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='/client']")
	private	WebElement Client;
	@FindBy(xpath="//label[contains(text(),'Empirix_QA_Training')]")
	private	WebElement ClientName;
	@FindBy(xpath="//label[contains(text(),'This client is for QA Test purposes')]")
	private	WebElement Description;
	@FindBy(xpath = "//div[@class='header-branding']/ul/li[1]/a")
	private	WebElement profileDropDown;
	
	public String clientName() {
		return ClientName.getText();
	}
	public String description() {
		return Description.getText();
	}
	public void navigateToClientPage() throws InterruptedException {
		if(flag==true) {
		Utility.fluentWait(driver, profileDropDown,30);
		profileDropDown.click();}
		Thread.sleep(5000);
		Client.click();
	}
	

}
