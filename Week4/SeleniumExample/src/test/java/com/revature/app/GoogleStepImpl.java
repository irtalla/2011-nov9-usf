package com.revature.app;


import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import com.revature.pages.CatAppHome;
import com.revature.pages.Google;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class GoogleStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static WebDriver driver = new ChromeDriver();
	static Google google = new Google(driver);
	

@Given("I am on the Google home page")
public void i_am_on_the_Youtube_home_page() {
	driver.get("http://google.com");
    throw new io.cucumber.java.PendingException();
}

@When("I click on the search bar")
public void i_click_on_the_search_bar() {
    google.clickSearchBar();
    throw new io.cucumber.java.PendingException();
}

@Then("previous searches should appear")
public void previous_searches_should_appear() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



@When("I {string}")
public void i(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

}
