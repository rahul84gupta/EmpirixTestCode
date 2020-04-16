package com.empirix.Utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	// public static WebElement element;
	public static void hoverOver(WebDriver driver, WebElement target) {
		Actions ac = new Actions(driver);
		ac.moveToElement(target).build().perform();
	}

	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	public static void contextClick(WebDriver driver, WebElement target) {
		Actions ac = new Actions(driver);
		ac.moveToElement(target).contextClick().build().perform();
	}

	public static void explicitWait(WebDriver driver, WebElement element) {
		WebDriverWait expWait = new WebDriverWait(driver, 20);
		expWait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void waitForElementPresent(WebDriver driver, WebElement element, String text) {
		WebDriverWait expectWait = new WebDriverWait(driver, 20);
		expectWait.until(ExpectedConditions.textToBePresentInElement(element, text));

	}

	public static void fluentWait(WebDriver driver, final WebElement element, int Time) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Time))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		 wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver t) {
				return element;
			}
		});
	}

	public static void getScreenshot(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void deleteText(WebDriver driver) {
		Actions ac = new Actions(driver);
		ac.keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE);
	}

	public static void doubleClick(WebDriver driver, WebElement target) {
		Actions ac = new Actions(driver);
		ac.moveToElement(target).doubleClick(target).build().perform();
	}

	public static void alertAccept(WebDriver driver) {
		org.openqa.selenium.Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void alertDismiss(WebDriver driver) {
		org.openqa.selenium.Alert alert = driver.switchTo().alert();
		alert.dismiss();
		;
	}

}
