package com.revature.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Louis {
	private WebDriver driver;
	
	public Louis(WebDriver d) {
		driver = d;
	}
	
	public void navigateTo() {
		driver.get("https://en.wikipedia.org/wiki/Louis_XIII");
	}
	
	public WebElement getDad() {
		return driver.findElement((By.xpath("//a[@href='/wiki/Henry_IV_of_France']")));
	}
	
	public WebElement getSignature() {
		return driver.findElement((By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[23]/td/a")));
	}
	
	public void closeSignature() {
		waitForClickableElement();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}
	
	public void waitForClickableElement() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(2000, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.attributeContains(By.cssSelector("button.mw-mmv-close"),
				"class", "mw-mmv-close"));	
		}
}
