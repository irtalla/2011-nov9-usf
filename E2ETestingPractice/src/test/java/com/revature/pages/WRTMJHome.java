package com.revature.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WRTMJHome {
	private WebDriver driver;
	
	@FindBy(name="s")
	WebElement searchBar;
	@FindBy(id="results")
	WebElement resultsDiv;
	
	public WRTMJHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void search(String job) {
		searchBar.sendKeys(job);
	}
	
	public void clickJob() {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(5,TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("li"), 0));
		WebElement li = resultsDiv.findElement(By.cssSelector("ul")).findElement(By.cssSelector("li"));
		
		WebElement a = li.findElement(By.cssSelector("a"));
		a.click();
	}
	
	public String getPercentage() {
		WRTMJP perPage = new WRTMJP(driver);
		return perPage.getPercentage();
	}
}
