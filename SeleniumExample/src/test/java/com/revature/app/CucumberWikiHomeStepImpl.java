package com.revature.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import static org.junit.jupiter.api.Assertions.*;

import com.revature.pages.CucumberWikiHome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberWikiHomeStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static WebDriver driver = new ChromeDriver();
	static CucumberWikiHome cucumberWikiHome = new CucumberWikiHome(driver);
	
	@Given("I am on the Cucumber Wikipedia home page")
	public void i_am_on_the_Cucumber_wikipedia_home_page() {
	    driver.get("https://en.wikipedia.org/wiki/Cucumber_(software)");
	}

	
	@When("I click on \"Random Article\" to access a random article")
	public void i_click_on_random_article() {
	    cucumberWikiHome.clickRandomArticle();
	}
	
	@When("I search")
	public void i_search_wikipedia(String searchTerm){
	    cucumberWikiHome.search(searchTerm);
	}
	
	@Then("we should be redirected to a page that has our search term as a heading")
	public void the_results_page_should_have_our_search_term_as_heading(String searchTerm) {
		WebElement header = driver.findElement(By.id("firstHeading"));
	    assertEquals(searchTerm, header.getText());
	}
}
