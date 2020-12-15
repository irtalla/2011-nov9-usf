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

	public class Google{
		private WebDriver driver;
		
		
		@FindBy(name="q")
		WebElement viewSearchBar;
		
		
		public Google(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		


		
		public void clickSearchBar() {
			viewSearchBar.click();
		}
	}
