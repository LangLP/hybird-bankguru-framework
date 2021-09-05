package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import facebook.register.PageGeneratorManager;
import facebook.register.RegisterPageObject;

public class Level_11_Register_Login_Element_Undisplayed extends BaseTest {

	WebDriver driver;
	String emailAddress, password;
	RegisterPageObject registerPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		driver.manage().window().maximize();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

	}

	@Test
	public void Login_01_Element_Displayed() {
		// Dispayed : Visible on UI + Exist in DOM
		// wait Visibale
		// isDisplayed->true
		Assert.assertTrue(registerPage.isEmailTextboxDispalyed());

		// Business :Input to Email textbox
		// Confirm email displayed
		registerPage.enterToEmailTextBox("kam@gmail.com");
		Assert.assertTrue(registerPage.isConfirmEmailTextboxDispalyed());

	}

	@Test
	public void Login_02_Element_Undisplayed_In_Dom() {
		// Dispayed : Invisible on UI + Exist in DOM
		// isDisplayed->false

		// Business :clear to Email textbox
		registerPage.enterToEmailTextBox("");
		registerPage.sleepInSecond(3);
		// Confirm email undisplayed
		Assert.assertFalse(registerPage.isConfirmEmailTextboxDispalyed());

	}

	@Test
	public void Login_03_Element_Undisplayed_Not_Dom() {
		// Undisplayed :Invisibale on UI + Not exist in DOM
		// isDisplayed -> flase (try-catch)
		
		// phu dinh
		Assert.assertFalse(registerPage.isLoginButtonDispalyed());
	
	}
	@Test
	public void Login_04_Element_Undisplayed_Not_Dom() {
		// Undisplayed :Invisibale on UI + Not exist in DOM

		// khang dinh
		Assert.assertTrue(registerPage.isLoginButtonUnDispalyed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
