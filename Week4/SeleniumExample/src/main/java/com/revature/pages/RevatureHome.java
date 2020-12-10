package com.revature.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class RevatureHome {
	private WebDriver driver;
	
	public RevatureHome(WebDriver d) {
		driver = d;
	}
	
	public void navigateTo() {
		driver.get("https://www.revature.com");
	}
	
	public WebElement getHireTalent() {
		return driver.findElement(By.linkText("HIRE TALENT"));
	}
	
	public WebElement getGetHired() {
		return driver.findElement(By.linkText("GET HIRED"));
	}
	
	public WebElement getJoinOurTeam() {
		waitForElementByLinkText("Join Our Team");
		return driver.findElement(By.linkText("Join Our Team"));
	}
	
	public WebElement getSuccessStories() {
		waitForElementByLinkText("Success Stories");
		return driver.findElement(By.linkText("Success Stories"));
	}
	
	public WebElement getPartners() {
		return driver.findElement(By.linkText("PARTNERS"));
	}
	
	public WebElement getAboutUs() {
		return driver.findElement(By.linkText("ABOUT US"));
	}
	
	public WebElement getApplyNow() {
		return driver.findElement(By.linkText("Apply Now"));
	}
	
	public void waitForElementByLinkText(String linkText) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
	}
}
