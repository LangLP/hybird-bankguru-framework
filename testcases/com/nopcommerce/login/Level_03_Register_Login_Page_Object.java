package com.nopcommerce.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjectnopCommerce.HomePageObject;
import pageObjectnopCommerce.LoginPageObject;
import pageObjectnopCommerce.RegisterPageObject;

public class Level_03_Register_Login_Page_Object {

	WebDriver driver;

	String emailAddress, password;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		emailAddress=getRandomEmail();
		password="1234567";
	}

	@Test
	public void Login_01_Register_To_System() {

		// Step 1 : mở Url ra -> Home Page
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);

		// Step 02 :Verify Home Page logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());

		// Step 2 :Click to Register link -> Register Page
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		// Step 3:Click to Gender male radio
		registerPage.clickToGenderMaleRadioButton();

		// Step 4:Click to Firstname textbox
		registerPage.enterToFirstnameTextbox("John");

		// Step 5:Input to Lastname textbox
		registerPage.enterToLastnameTextbox("Wick");

		// Step 6:Input to Email textbox
		registerPage.enterToEmailTextbox(emailAddress);

		// Step 7:Input to Password textbox
		registerPage.enterToPassworkTextbox(password);

		// Step 8:Input to Confirm Password textbox
		registerPage.enterToConfirmPasswordTextbox(password);

		// Step 9:Click to Register button
		registerPage.clickToRegisterButton();

		// Step 10 :Verify Succes message displayed
		Assert.assertTrue(registerPage.isSuccesMessageDisplayed());

		// Step 11 :Click to Logout link ->Home page
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);

		// Step 12 :Verify Home Page logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void Login_02_Login_To_System() {

		// Step 1:Click to Loginlink ->Home page
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);

		// Step 2:Input to Email textbox
		loginPage.enterToEmailTextbox(emailAddress);

		// Step 3:Input to Password textbox
		loginPage.enterToPassworkTextbox(password);

		// Step 4:Click to Login button
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);

		// Step 12 :Verify Home Page logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "Johndeep" + rand.nextInt(99999) + "@gmail.com";
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

}
