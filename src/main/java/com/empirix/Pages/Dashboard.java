package com.empirix.Pages;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.empirix.Utility.Utility;

public class Dashboard {
	public WebDriver driver;

	public Dashboard(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='header-branding']/ul/li[1]/a")
	WebElement profileDropDown;
	@FindBy(xpath = "//a[contains(text(),'Japanese')]")
	WebElement japaneeseLink;
	@FindBy(xpath = "//span[@class='product']")
	WebElement textVisible;
	@FindBy(xpath = "//a[contains(text(),'English')]")
	WebElement englishLink;
	@FindBy(xpath = "//div[@class='cc-compliance']/a")
	WebElement cookie;
	@FindBy(xpath ="//div[@id='wrapper']/header/section[@class='sup-header']/div/ul/li[1]/a") // "//li[@class='active mactive']//a[contains(text(),'Dashboard')]")
	WebElement dashboard;
	@FindBy(xpath = "//a[contains(text(),'Alerts')]")
	WebElement Alerts;
	@FindBy(xpath = "//a[@href='/tests']")
	WebElement Tests;
	@FindBy(xpath = "//a[@href='/variables']")
	WebElement Variables;
	@FindBy(xpath = "//a[@href='/notifi']")
	WebElement Notifications;
	@FindBy(xpath="//h1[contains(text(),'Overall Performance')]")
	WebElement PageLoadCheck;
	@FindBy(xpath="//span[contains(text(),'Total')]")
	WebElement ElementCheckForAlertTab;
	@FindBy(xpath="//th[contains(text(),'Error Step')]")
	WebElement ValidateAlertsText;
	@FindBy(xpath ="//h4[contains(text(),'Test')]")
	WebElement PageLoadCheckTestsTab;
	@FindBy(xpath ="//div[@class='ng-scope']//h4[contains(text(),'Variable')]")
	WebElement PageLoadCheckVariablesTab;
	@FindBy(xpath = "//h4[contains(text(),'Notification')]")
	WebElement PageLoadCheckNotifyTab;
	@FindBy(xpath = "//div[@id='overall']/nav/div/div/h1")
	WebElement DashboardTabText;

	public void waitforpage() {
		Utility.waitForElementPresent(driver, textVisible, "VoiceWatch");
	}

	public void dashboards() {
		Utility.fluentWait(driver, dashboard);
		dashboard.click();
		System.out.println("Dashboard tab clicked");
		Utility.fluentWait(driver, DashboardTabText);		
	}
	
	public void alerts() throws InterruptedException {
		englishLanguageSelection();
		Utility.fluentWait(driver, Alerts);
		Alerts.click();
		System.out.println("Alerts tab clicked");
		Utility.fluentWait(driver, ElementCheckForAlertTab);		
	}

	public void tests() {
		Tests.click();
		System.out.println("Tests tab clicked");
		Utility.fluentWait(driver, PageLoadCheckTestsTab);
	}

	public void variables() {
		Variables.click();
		System.out.println("Variables tab clicked");
		Utility.fluentWait(driver, PageLoadCheckVariablesTab);
	}

	public void notification() {
		Notifications.click();
		System.out.println("Notifications tab clicked");
		Utility.fluentWait(driver, PageLoadCheckNotifyTab);
	}
	
	public String dashboardTab() {
		return PageLoadCheck.getText();			
	}
	public String alertsTab() {
		return ValidateAlertsText.getText();			
	}
	public String testsTab() {
		return PageLoadCheckTestsTab.getText();			
	}
	public String variablesTab() {
		return PageLoadCheckVariablesTab.getText();			
	}
	public String notificationsTab() {
		return PageLoadCheckNotifyTab.getText();			
	}
	public boolean japaneeseLanguageSelectionValidate() {
		Utility.fluentWait(driver, dashboard);
		return DashboardTabText.isDisplayed();			
	}
	public boolean englishLanguageSelectionValidate() {
		Utility.fluentWait(driver, dashboard);
		dashboard.click();
		Utility.fluentWait(driver, dashboard);
		return DashboardTabText.isDisplayed();			
	}
	
	public void englishLanguageSelection() throws InterruptedException {
		if (cookie.isDisplayed()) {
			cookie.click();
		}
		profileDropDown.click();
		Thread.sleep(2000);		
			try {
				englishLink.click();
				Utility.alertAccept(driver);
			} catch (NoSuchElementException e) {
				System.out.println("English Language selected");
			}
	}

	public ClientProfile languageSelection(String language) throws InterruptedException, IOException {
		if (cookie.isDisplayed()) {
			cookie.click();
		}
		profileDropDown.click();
		Thread.sleep(2000);
		if (language.equalsIgnoreCase("english")) {
			try {
				englishLink.click();
				Utility.alertAccept(driver);
			} catch (NoSuchElementException e) {
				System.out.println("English already selected");
			}
		} else {
			try {
				japaneeseLink.click();
				Utility.alertAccept(driver);
			} catch (NoSuchElementException e) {
				System.out.println("Japaneese already selected");
			}
		}		
		return new ClientProfile(driver);
	}

	

}
