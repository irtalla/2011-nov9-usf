package com.revature.app;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.Louis;

public class LouisAutomation {
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		Louis louis = new Louis(driver);
		
		louis.navigateTo();
		louis.getDad().click();
		louis.getSignature().click();
		louis.closeSignature();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		driver.quit();
		}
		
	}
}
