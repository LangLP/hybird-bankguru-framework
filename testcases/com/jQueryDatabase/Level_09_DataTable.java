package com.jQueryDatabase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.HomePageObject;
import pageObject.jQuery.PageGeneratorManager;

public class Level_09_DataTable extends BaseTest {

	WebDriver driver;
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	public void Table_01_Paging() {
		homePage.openPageByNumber("15");
		Assert.assertTrue(homePage.isPageActivesByNumber("15"));

		homePage.openPageByNumber("5");
		Assert.assertTrue(homePage.isPageActivesByNumber("5"));

		homePage.openPageByNumber("10");
		Assert.assertTrue(homePage.isPageActivesByNumber("10"));

	}

	public void Table_02_Input() {
		homePage.inputToHeaderTextboxByName("Females", "160000");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Country", "Guatemala");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Males", "162000");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

	}

	public void Table_03_Actions() {

		homePage.clickToIconByCountryName("Afghanistan", "remove");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

		homePage.clickToIconByCountryName("Angola", "remove");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

		homePage.clickToIconByCountryName("Armenia", "edit");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

	}
	public void Table_04_Verify() {
		homePage.inputToHeaderTextboxByName("Country", "Afghanistan");
		homePage.isRowValueDisplayed("384187", "Afghanistan", "407124", "791312");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Country", "Angola");
		homePage.isRowValueDisplayed("276880", "Angola", "276472", "553353");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);

	}

	@Test
	public void Table_05_Input_To_Row_Textbox() {
		homePage.inputToTextboxByRowNumber("Contact Person", "3", "John Wick");
		homePage.sleepInSecond(3);

		homePage.inputToTextboxByRowNumber("Order Placed", "2", "3");
		homePage.sleepInSecond(3);
		
		homePage.inputToTextboxByRowNumber("Company", "2", "Apple");
		homePage.sleepInSecond(3);

	}

	@Test

	public void Table_06_Click_Icon_At_Row() {
		homePage.clickToIconByRowNumber("2", "Move Up");
		homePage.sleepInSecond(3);
		
		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(3);
		
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(3);
		
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(3);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
