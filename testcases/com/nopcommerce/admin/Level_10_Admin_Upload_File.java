package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjectNopCommerceAdmin.DashboardPageObject;
import pageObjectNopCommerceAdmin.LoginPageObject;
import pageObjectNopCommerceAdmin.PageGeneratorManager;
import pageObjectNopCommerceAdmin.ProductDetailPageObject;
import pageObjectNopCommerceAdmin.ProductSearchPageObject;

public class Level_10_Admin_Upload_File extends BaseTest {

	WebDriver driver;
	String productName = "Adobe Photoshop CS4";
	String productdefaultName = "Adobe Photoshop CS4";
	String productAvatarImg ="Avatar.png";
	String productAvatarAlt ="Avatar Alt";
	String productAvatarTitle ="Avatar Title";
	String productAvatarOrder ="1";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		//loginPage.enterToEmailTextbox("admin@yourstore.com");
		//loginPage.enterToPasswordTextbox("admin");
		dashBoardPage = loginPage.clickToLoginButton();

		dashBoardPage.openSubMenuPageByName(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);

		productSearchPage.enterToProductNameTextbox(productName);

		productSearchPage.clickToSearchButton();

		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);

	}

	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.clickToExpandPanelByName("Pictures");
		
		productDetailPage.uploadFilesCarName ( driver,  "pictures", productAvatarImg);
		Assert.assertTrue(productDetailPage.isPictureUploadedSuccessByFileName(productAvatarImg));

		productDetailPage.enterToAltTextbox(productAvatarAlt);

		productDetailPage.enterToTitleTextbox(productAvatarTitle);

		productDetailPage.clickToUPDownDisplayOrderTextbox("Increase");

		productDetailPage.clickToAddProductPictureButton();

		Assert.assertTrue(productDetailPage.isPictureImageDislayed(productName,productAvatarOrder,productAvatarAlt, productAvatarTitle));

		productDetailPage.clickTBackTopIcon();

		productSearchPage = productDetailPage.clickToSaveButton();

		Assert.assertTrue(productSearchPage.isSuccessMessageDisplayed("The product has been updated successfully."));

		productSearchPage.enterToProductNameTextbox(productName);

		productSearchPage.clickToSearchButton();

		Assert.assertTrue(productSearchPage.isPictureImageUpdate(productName,productName));

		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);

		productDetailPage.clickToExpandPanelByName("Picture");

		productDetailPage.clickToDeleteAtpictureName(productAvatarTitle); // Accept alert

		Assert.assertTrue(productDetailPage.isMessageDisplayedInEmtyTable(driver,"productpictures"));
		
		productDetailPage.clickTBackTopIcon();
		
		productSearchPage = productDetailPage.clickToSaveButton();

		productSearchPage.enterToProductNameTextbox(productName);

		productSearchPage.clickToSearchButton();

		Assert.assertTrue(productSearchPage.isPictureDefaultImageUpdate("default-image",productName));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	LoginPageObject loginPage;
	DashboardPageObject dashBoardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;

}
