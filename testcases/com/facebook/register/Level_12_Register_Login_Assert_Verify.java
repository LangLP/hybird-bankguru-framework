package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import facebook.register.PageGeneratorManager;
import facebook.register.RegisterPageObject;

public class Level_12_Register_Login_Assert_Verify extends BaseTest {

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
	public void Login_01_Verify() {
		//Failed lan 1
		verifyFalse(registerPage.isEmailTextboxDispalyed());
		
		registerPage.enterToEmailTextBox("kam@gmail.com");
		
		//Failed lan 2
		verifyTrue(registerPage.isConfirmEmailTextboxDispalyed());
		
		registerPage.enterToEmailTextBox("");
		registerPage.sleepInSecond(3);
		
		verifyFalse(registerPage.isConfirmEmailTextboxDispalyed());
		
		verifyFalse(registerPage.isLoginButtonDispalyed());
		
		//Failed lan 3
		verifyFalse(registerPage.isLoginButtonUnDispalyed());
	}



	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
