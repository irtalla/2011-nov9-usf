package com.revature.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.revature.pages.Louis;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LouisStempImpl {

	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static WebDriver driver = new ChromeDriver();
	static Louis louis = new Louis(driver);
	
	@Given("I am viewing Henry XIVs signature")
	public void i_am_viewing_Henry_XIVs_signature() {
		driver.get("https://en.wikipedia.org/wiki/Henry_IV_of_France");
		louis.getSignature().click();
	}

	@When("I press the escape key")
	public void i_press_the_escape_key() {
		louis.closeSignature();
	}

	@Then("I return to Henry XIVs page")
	public void i_return_to_Henry_XIVs_page() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(2000, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.attributeContains(By.id("firstHeading"),
				"class", "firstHeading"));	
		WebElement header = driver.findElement(By.id("firstHeading"));
		assertEquals("Henry IV of France", header.getText());
	}

}
