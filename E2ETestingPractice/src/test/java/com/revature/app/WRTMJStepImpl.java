package com.revature.app;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.revature.pages.WRTMJHome;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WRTMJStepImpl {
	
	static {
		File file = new File("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
	}
	
	static WebDriver driver = new FirefoxDriver();
	static WRTMJHome wrtmjHome = new WRTMJHome(driver);
	
	private static String url = "https://willrobotstakemyjob.com/";
	
	@Given("I am on the WRTMJ home page")
	public void i_am_on_the_WRTMJ_home_page() {
	    driver.get(url);
	}

	@When("I enter {string} to search")
	public void i_enter_to_search(String string) {
	    wrtmjHome.search(string);
	}

	@When("I click on the first option")
	public void i_click_on_the_first_option() {
	    wrtmjHome.clickJob();
	}

	@Then("the percentage should be {string}")
	public void the_percentage_should_be(String string) {
	    assertEquals(string, wrtmjHome.getPercentage());
		
	}
}
