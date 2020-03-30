package com.empirix.Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientProfile {
	public WebDriver driver;
	
	public ClientProfile(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='/client']")
	private	WebElement Client;
	@FindBy(xpath="//label[contains(text(),'Empirix_QA_Training')]")
	private	WebElement ClientName;
	@FindBy(xpath="//label[contains(text(),'This client is for QA Test purposes')]")
	private	WebElement Description;
	
	public String clientName() {
		return ClientName.getText();
	}
	public String description() {
		return Description.getText();
	}
	public void navigateToClientPage() {
		Client.click();
	}
	

}
