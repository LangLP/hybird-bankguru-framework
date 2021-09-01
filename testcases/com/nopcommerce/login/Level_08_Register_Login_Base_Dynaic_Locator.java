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

public class Level_08_Register_Login_Base_Dynaic_Locator extends BaseTest {

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
	public void Login_03_Open_Page_At_Footer() {
		// home ->search
		searchPage = (SearchPageObject) homePage.getFooterPageByName(driver, "Search");

		// search ->myacount
		myAcountPage = (MyAcountPageObject) searchPage.getFooterPageByName(driver, "My account");

		// myacount ->oder
		orderPage = (OrderPageObject) myAcountPage.getFooterPageByName(driver, "Orders");

		// oder ->myacount
		myAcountPage = (MyAcountPageObject) orderPage.getFooterPageByName(driver, "My account");

		// myacount ->search
		searchPage = (SearchPageObject) myAcountPage.getFooterPageByName(driver, "Search");
		// orders -> search
		orderPage = (OrderPageObject) searchPage.getFooterPageByName(driver, "Orders");

	}
	
	@Test
	public void Login_04_Open_Page_At_Footer() {
		// oder ->myacount
		orderPage.openFooterPageByName(driver, "My account");
		myAcountPage = PageGeneratorManager.getMyAcountPage(driver);
		
		// myacount ->search
		myAcountPage.openFooterPageByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		// search ->oder
		searchPage.openFooterPageByName(driver, "Orders");
		orderPage = PageGeneratorManager.getOrderPage(driver);

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
	OrderPageObject orderPage;

}
