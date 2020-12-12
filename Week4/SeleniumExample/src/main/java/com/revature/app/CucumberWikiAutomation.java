package com.revature.app;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.CucumberWikiHome;

public class CucumberWikiAutomation {
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		CucumberWikiHome cucumberWikiHome = new CucumberWikiHome(driver);
		
		cucumberWikiHome.navigateTo();
		cucumberWikiHome.clickRandomArticle();
		cucumberWikiHome.search("Piracy");
		
		driver.quit();
	}

}

