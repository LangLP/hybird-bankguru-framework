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
import pageObject.nopCommerce.MyAcountPageObject;
import pageObject.nopCommerce.OrderPageObject;
import pageObject.nopCommerce.PageGeneratorManager;
import pageObject.nopCommerce.RegisterPageObject;
import pageObject.nopCommerce.SearchPageObject;

public class Level_07_Register_Login_Base_Switch_Page extends BaseTest {

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
		System.out.println("Home Page =" + homePage.hashCode());
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox("John");
		registerPage.enterToLastnameTextbox("Wick");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPassworkTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccesMessageDisplayed());
		homePage = registerPage.clickToLogoutLink();
		System.out.println("Home Page =" + homePage.hashCode());
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void Login_02_Login_To_System() {
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPassworkTextbox(password);
		homePage = loginPage.clickToLoginButton();
		System.out.println("Home Page =" + homePage.hashCode());
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}
	@Test
	public void Login_03_Switch_Page_At_Footer() {
		// home ->search
		searchPage = homePage.openSearchPage(driver);

		// search ->myacount
		myAcountPage = searchPage.openMyAccountPage(driver);

		// myacount ->oder
		oderPage = myAcountPage.openOrderPage(driver);

		// oder ->myacount
		myAcountPage = oderPage.openMyAccountPage(driver);

		// myacount ->search
		searchPage = myAcountPage.openSearchPage(driver);
		//orders ->  search
		oderPage = searchPage.openOrderPage(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	SearchPageObject searchPage;
	MyAcountPageObject myAcountPage;
	OrderPageObject oderPage;

}
