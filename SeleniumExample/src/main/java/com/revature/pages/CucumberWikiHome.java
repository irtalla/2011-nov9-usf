package com.revature.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CucumberWikiHome {
	private WebDriver driver;
	
	@FindBy(linkText="Random article")
	WebElement randomArticleLink;
	
	@FindBy(name="search")
	WebElement searchInput;
	
	@FindBy(name="go")
	WebElement searchButton;

	public CucumberWikiHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickRandomArticle() {
		randomArticleLink.click();
	}

	
	public void search(String searchTerm) {
		searchInput.sendKeys(searchTerm);
		searchButton.submit();
	}

	public void navigateTo() {
		driver.get("https://en.wikipedia.org/wiki/Cucumber_(software)");
	}
}
