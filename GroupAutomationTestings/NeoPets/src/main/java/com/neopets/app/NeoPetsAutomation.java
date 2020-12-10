package com.neopets.app;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.NeoPetsHome;

public class NeoPetsAutomation {
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		NeoPetsHome neoPetsHome = new NeoPetsHome(driver);
		
		neoPetsHome.navigateTo();
		neoPetsHome.getMerch().click();
	}


}

