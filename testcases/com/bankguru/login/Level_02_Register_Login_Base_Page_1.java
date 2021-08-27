package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Register_Login_Base_Page_1 {

	WebDriver driver;
	BasePage basePage;
	String username, password, loginPageUrl;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
//  che dấu sự khởi  tạo  đối  tượng 
		basePage =BasePage.getBasePage() ;
	}

	@Test
	public void Login_01_Register_To_System() {
		
		loginPageUrl = basePage.getPageUrl(driver);
		
		basePage.clickToElement(driver,"//a[text()='here']");
		basePage.sendkeyToElement(driver,"//input[@name='emailid']",getRandomEmail());
		basePage.clickToElement(driver,"//input[@name='btnLogin']");
		username = basePage.getElementText(driver,"//td[text()='User ID :']/following-sibling::td");
		password = basePage.getElementText(driver,"//td[text()='Password :']/following-sibling::td");

	}
	@Test
	public void Login_02_Login_To_System() {
		
		basePage.openPageUrl(driver,loginPageUrl);

		
		basePage.sendkeyToElement(driver,"//input[@name='uid']",username);
		basePage.sendkeyToElement(driver,"//input[@name='password']",password);
		basePage.clickToElement(driver,"//input[@name='btnLogin']");
		Assert.assertEquals(basePage.getElementText(driver,"//marquee[@class='heading3']"), "Welcome To Manager's Page of  Guru99 Bank");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}
	public String getRandomEmail() {
		Random rand = new Random();
		return "Johndeep" + rand.nextInt(99999) + "@gmail.com";
	}

}
