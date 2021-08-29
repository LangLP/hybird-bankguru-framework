package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.nopCommerce.HomePageObject;
import pageObject.nopCommerce.LoginPageObject;
import pageObject.nopCommerce.PageGeneratorManager;
import pageObject.nopCommerce.RegisterPageObject;

public class Level_06_Register_Login_Base_Generator extends BaseTest {

	WebDriver driver;
	String emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		driver.manage().window().maximize();

		emailAddress = getRandomEmail();
		password = "1234567";
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void Login_01_Register_To_System() {

		homePage = PageGeneratorManager.getHomePage(driver);
		System.out.println("Home Page ="+ homePage.hashCode());

		// Step 02 :Verify Home Page logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());

		// Step 2 :Click to Register link -> Register Page
		registerPage = homePage.clickToRegisterLink();
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
		homePage = registerPage.clickToLogoutLink();
		System.out.println("Home Page ="+ homePage.hashCode());

		// Step 12 :Verify Home Page logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void Login_02_Login_To_System() {

		// Step 1:Click to Loginlink ->Home page
		loginPage = homePage.clickToLoginLink();
		// Step 2:Input to Email textbox
		loginPage.enterToEmailTextbox(emailAddress);

		// Step 3:Input to Password textbox
		loginPage.enterToPassworkTextbox(password);

		// Step 4:Click to Login button
		homePage = loginPage.clickToLoginButton();
		System.out.println("Home Page ="+ homePage.hashCode());

		// Step 12 :Verify Home Page logo displayed
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

}
