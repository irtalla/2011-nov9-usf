package com.revature.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class TRMSHome {
private WebDriver driver;
	
	@FindBy(name="user")
	WebElement usernameInput;
	@FindBy(name="pass")
	WebElement passwordInput;
	@FindBy(id="loginBtn")
	WebElement loginBtn;
	@FindBy(linkText="View Events")
	WebElement viewEventsLink;
	@FindBy(id="usernameLink")
	WebElement usernameLink;
	
	public TRMSHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsernameAndPassword(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
	}
	
	public void loginOrLogout() {
		loginBtn.click();
	}
	
	public String getLoginText() {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(usernameLink));
		return usernameLink.getText();
	}
	
	public void clickViewEvents() {
		viewEventsLink.click();
	}


}
