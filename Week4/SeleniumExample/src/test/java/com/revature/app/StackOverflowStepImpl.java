package com.revature.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import com.revature.pages.STOHome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StackOverflowStepImpl {
	

	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	static WebDriver driver = new ChromeDriver();
	static STOHome stohome = new STOHome(driver);
	
	
		
	@Given("On stackoverflow landing page")
	public void on_stackoverflow_landing_page() {
		driver.get("http://www.stackoverflow.com");
	   // throw new io.cucumber.java.PendingException();
	}
	
	@When("The {string} is clicked")
	public void the_is_clicked(String string) {
		stohome.clickAbout();
	 //   throw new io.cucumber.java.PendingException();
	}
	
	@Then("Should be taken to about page")
	public void should_be_taken_to_about_page() {
		WebElement ele = driver.findElement(By.className("unified-theme pt0"));
		assertNotNull(ele);
		assertNotEquals("http://www.stackoverflow.com", driver.getCurrentUrl());
	 //   throw new io.cucumber.java.PendingException();
	}

}
