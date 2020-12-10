package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WRTMJP {
	private WebDriver driver;
	
	@FindBy(className = "probability")
	WebElement per;
	
	public WRTMJP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPercentage() {
		return per.getText();
	}
}
