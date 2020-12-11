package com.revature.app;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikiAutomation {
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.wikipedia.org");
		
		WebElement englishWiki = driver.findElement(By.id("js-link-box-en"));
		englishWiki.click();
		
		WebElement artsLink = driver.findElement((By.xpath("//a[@href='/wiki/Portal:The_arts']")));
		artsLink.click();
		
		WebElement paintingLink = driver.findElement(By.linkText("painting"));
		paintingLink.click();
		
		// By.cssSelector("input#searchInput")
		WebElement searchBar = driver.findElement(By.id("searchInput"));
		searchBar.sendKeys("koala");
		
		// implicit wait
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// explicit wait
//		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.suggestions")));
		
		// fluent wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(50, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(By.cssSelector("div.suggestions"),
				"style", "display: none")));
		
		searchBar.sendKeys(Keys.ENTER);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
		
		// driver.close();
		//driver.quit();

		
	}
}
