package com.revature.app;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.RevatureHome;

public class RevAutomation {

	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		RevatureHome revHome = new RevatureHome(driver);
		
		revHome.navigateTo();
		revHome.getHireTalent().click();
		revHome.navigateTo();
		revHome.getGetHired().click();
		revHome.getJoinOurTeam().click();
		revHome.navigateTo();
		revHome.getGetHired().click();
		revHome.getSuccessStories();
		
		driver.quit();
	}

}
