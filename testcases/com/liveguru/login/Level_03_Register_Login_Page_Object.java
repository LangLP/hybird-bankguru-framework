package com.liveguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObject.liveGuru.HomePageObject;
import pageObject.liveGuru.LoginPageObject;
import pageObject.liveGuru.MyDasboardPageObject;

public class Level_03_Register_Login_Page_Object{
	WebDriver driver;
	BasePage basePage;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Login_01_Emty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");
		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountFooterLink();

		loginPage = new LoginPageObject(driver);

		loginPage.enterToPasswordTextbox("");
		loginPage.enterToEmailTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmtyEmailErrorMessage(), "This is a required field.");
		Assert.assertEquals(loginPage.getEmtyPasswordErrorMessage(), "This is a required field.");
	}

	@Test
	public void Login_02_Login_Invalid_Email() {
		loginPage.refreshCurrentPage(driver);

		loginPage.enterToPasswordTextbox("123@456.789");
		loginPage.enterToEmailTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getInvalidEmailErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test(description = "Passwork less than 6 chars")

	public void Login_03_Login_Invalid_Password() {
		loginPage.refreshCurrentPage(driver);

		loginPage.enterToPasswordTextbox("lang@gmail.com");
		loginPage.enterToEmailTextbox("1234");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test(description = "Email not  exist in system")
	public void Login_04_Login_Incorrect_Email() {
		loginPage.refreshCurrentPage(driver);

		loginPage.enterToPasswordTextbox(getRandomEmail());
		loginPage.enterToEmailTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getInvalidEmailOrPasswordErrorMessage(), "Invalid login or password.");

	}

	@Test
	public void Login_05_Login_Incorrect_Password() {
		loginPage.refreshCurrentPage(driver);

		loginPage.enterToPasswordTextbox("dam@gmail.com");
		loginPage.enterToEmailTextbox("111222");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getInvalidEmailOrPasswordErrorMessage(), "Invalid login or password.");

	}

	@Test
	public void Login_06_Vaild_Email_And_Password() {
		loginPage.refreshCurrentPage(driver);

		loginPage.enterToPasswordTextbox("dam@gmail.com");
		loginPage.enterToEmailTextbox("123123");
		loginPage.clickToLoginButton();

		myDasboardPage = new MyDasboardPageObject(driver);
		Assert.assertTrue(myDasboardPage.isMyDashboardHeaderDisplayed());

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
	MyDasboardPageObject myDasboardPage;

}
