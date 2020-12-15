package com.revature.app;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.TRMSHome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TRMSHomeStepImpl {
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static WebDriver driver = new ChromeDriver();
	static TRMSHome trmsHome = new TRMSHome(driver);
	
	@Given("I am on the TRMS home page")
	public void i_am_on_the_trms_home_page() {
	    driver.get("http://localhost:8080");
	}

	@When("I enter {string} and {string} to log in")
	public void i_enter_and_to_log_in(String string, String string2) {
	    trmsHome.enterUsernameAndPassword(string, string2);
	}

	@When("I click the login button")
	public void i_click_the_login_button() {
	    trmsHome.loginOrLogout();
	}

	@Then("the username link should contain {string}")
	public void the_username_link_should_contain(String string) {
	    String linkText = trmsHome.getLoginText();
	    assertEquals(string + " ", linkText);
	    trmsHome.loginOrLogout();
	}

	@When("I click on the View Events link")
	public void i_click_on_the_View_Events_link() {
		trmsHome.clickViewEvents();
	}

	@Then("the header will say View Events")
	public void the_header_will_say_View_Events() {
	    WebElement header = driver.findElement(By.tagName("h3"));
	    assertEquals("Available Eventss", header.getText());
	}
	
	@Given("I am logged in")
	public void i_am_logged_in() {
	    trmsHome.enterUsernameAndPassword("pokemon", "pokemon");
	    trmsHome.loginOrLogout();
	}

	@When("I click on the {string} link")
	public void i_click_on_the_link(String string) {
	    WebElement link = driver.findElement(By.linkText(string));
	    link.click();
	}

	@Then("the header will say {string}")
	public void the_header_will_say(String string) {
	    WebElement header = driver.findElement(By.tagName("h3"));
	    assertEquals(string, header.getText());
	    trmsHome.loginOrLogout();
	}


}
