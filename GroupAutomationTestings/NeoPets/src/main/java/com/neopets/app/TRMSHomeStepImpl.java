
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import com.revature.pages.CatAppHome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TRMSHomeStepImpl {
	
	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}
	
	static WebDriver driver = new ChromeDriver();
	static CatAppHome catAppHome = new CatAppHome(driver);
	
	@Given("I am on the CatApp home page")
	public void i_am_on_the_CatApp_home_page() {
	    driver.get("http://localhost:8080");
	}

	@When("I enter {string} and {string} to log in")
	public void i_enter_and_to_log_in(String string, String string2) {
	    catAppHome.enterUsernameAndPassword(string, string2);
	}

	@When("I click the login button")
	public void i_click_the_login_button() {
	    catAppHome.loginOrLogout();
	}

	@Then("the username link should contain {string}")
	public void the_username_link_should_contain(String string) {
	    String linkText = catAppHome.getLoginText();
	    assertEquals(string + " ", linkText);
	    catAppHome.loginOrLogout();
	}

	@When("I click on the View Cats link")
	public void i_click_on_the_View_Cats_link() {
		catAppHome.clickViewCats();
	}

	@Then("the header will say View Cats")
	public void the_header_will_say_View_Cats() {
	    WebElement header = driver.findElement(By.tagName("h3"));
	    assertEquals("Available Cats", header.getText());
	}
	
	@Given("I am logged in")
	public void i_am_logged_in() {
	    catAppHome.enterUsernameAndPassword("sierra", "pass");
	    catAppHome.loginOrLogout();
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
	    catAppHome.loginOrLogout();
	}
}
