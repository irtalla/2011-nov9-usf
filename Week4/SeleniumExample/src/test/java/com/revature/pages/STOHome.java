package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class STOHome {
	private WebDriver driver;
	
	@FindBy(linkText = "About")
	WebElement about;
	
	public STOHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickAbout() {
		about.click();
	}
}
