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
	public boolean flag;

	public Dashboard(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='header-branding']/ul/li[1]/a")
	private	WebElement profileDropDown;
	@FindBy(xpath = "//a[contains(text(),'Japanese')]")
	private	WebElement japaneeseLink;
	@FindBy(xpath = "//span[@class='product']")
	private	WebElement textVisible;
	@FindBy(xpath = "//a[contains(text(),'English')]") // alternate xpath --> //div[@class='ng-scope']/a "")
	private	WebElement englishLink;
	@FindBy(xpath = "//div[@class='cc-compliance']/a")
	private	WebElement cookie;
	@FindBy(xpath ="//div[@id='wrapper']/header/section[@class='sup-header']/div/ul/li[1]/a") // "//li[@class='active mactive']//a[contains(text(),'Dashboard')]")
	private	WebElement dashboard;
	@FindBy(xpath = "//a[@href='/alerts']")
	private	WebElement Alerts;
	@FindBy(xpath = "//a[@href='/tests']")
	private	WebElement Tests;
	@FindBy(xpath = "//a[@href='/variables']")
	private	WebElement Variables;
	@FindBy(xpath = "//a[@href='/notifi']")
	private	WebElement Notifications;
	@FindBy(xpath="//h1[contains(text(),'Overall Performance')]")
	private	WebElement PageLoadCheck;
	@FindBy(xpath="//span[contains(text(),'Total')]")
	private	WebElement ElementCheckForAlertTab;
	@FindBy(xpath="//body//th[4]")
	private	WebElement ValidateAlertsText;
	@FindBy(xpath ="//h4[contains(text(),'Test')]")
	private	WebElement PageLoadCheckTestsTab;
	@FindBy(xpath ="//div[@class='ng-scope']//h4[contains(text(),'Variable')]")
	private	WebElement PageLoadCheckVariablesTab;
	@FindBy(xpath = "//h4[contains(text(),'Notification')]")
	private	WebElement PageLoadCheckNotifyTab;
	@FindBy(xpath = "//a[@href='/dashboard' and @data-i18n='_dashboard_']")
	private	WebElement DashboardTabText;

	public void waitforpage() {
		Utility.waitForElementPresent(driver, textVisible, "VoiceWatch");
	}

	public void dashboards() throws InterruptedException {
		englishLanguageSelection();
		Utility.fluentWait(driver, dashboard,30);
		dashboard.click();
		System.out.println("Dashboard tab clicked");
		Utility.fluentWait(driver, DashboardTabText,10);		
	}
	
	public void alerts() throws InterruptedException {
		
		if(flag==false) {
		englishLanguageSelection();
		}
		Utility.fluentWait(driver, Alerts,30);
		Thread.sleep(2000);
		Alerts.click();
		System.out.println("Alerts tab clicked");
		Thread.sleep(2000);
		Utility.fluentWait(driver, ElementCheckForAlertTab,30);
	}

	public void tests() throws InterruptedException {
		if(flag==false) {
			englishLanguageSelection();
			}
		Tests.click();
		System.out.println("Tests tab clicked");
		Utility.fluentWait(driver, PageLoadCheckTestsTab,10);
	}

	public void variables() throws InterruptedException {
		if(flag==false) {
			englishLanguageSelection();
			}
		Variables.click();
		System.out.println("Variables tab clicked");
		Utility.fluentWait(driver, PageLoadCheckVariablesTab,10);
	}

	public void notification() throws InterruptedException {
		
		if(flag==false) {
			englishLanguageSelection();
			}
		Notifications.click();
		System.out.println("Notifications tab clicked");
		Utility.fluentWait(driver, PageLoadCheckNotifyTab,10);
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
		Utility.fluentWait(driver, dashboard,30);
		return DashboardTabText.isDisplayed();			
	}
	public boolean englishLanguageSelectionValidate() {
		Utility.fluentWait(driver, dashboard,30);
		dashboard.click();
		Utility.fluentWait(driver, dashboard,30);
		return DashboardTabText.isDisplayed();			
	}
	
	public void englishLanguageSelection() throws InterruptedException {
		System.out.println(cookie.isDisplayed());
		try {
		if (cookie.isDisplayed()) {
			cookie.click();
		}
		}catch (NoSuchElementException e) {
			System.out.println("Cookie Check not available");
		}
		Utility.fluentWait(driver, profileDropDown,30);
		profileDropDown.click();
		Utility.fluentWait(driver, englishLink,30);	
			try {
				englishLink.click();
				//Thread.sleep(2000);
				Utility.alertAccept(driver);
				Utility.fluentWait(driver, dashboard,30);
				flag=true;
			} catch (NoSuchElementException e) {
				System.out.println("English Language selected");
			}
	}

	public ClientProfile languageSelection(String language) throws InterruptedException, IOException {
		if (cookie.isDisplayed()) {
			cookie.click();
		}
		Utility.fluentWait(driver, profileDropDown,30);
		profileDropDown.click();
		
		if (flag==false & language.equalsIgnoreCase("english")) {
			try {
				Thread.sleep(3000);
				englishLink.click();
				Utility.alertAccept(driver);
				flag=true;
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				System.out.println("English already selected");
			}
		} else if(flag=true){
			try {
				Utility.fluentWait(driver, japaneeseLink,30);
				
				japaneeseLink.click();
				Thread.sleep(2000);
				Utility.alertAccept(driver);
				flag=false;
				Utility.fluentWait(driver, dashboard,30);
			} catch (NoSuchElementException e) {
				System.out.println("Japaneese already selected");
			}
		}		
		return new ClientProfile(driver, flag);
	}

	

}
