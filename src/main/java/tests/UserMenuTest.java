package tests;


import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import constants.FileConstants;
import listeners.SFDCListeners;
import pages.LoginPage;
import pages.UserMenuPage;
import utils.CommonUtils;

@Listeners(SFDCListeners.class)
public class UserMenuTest extends BaseTest {
	
//	@BeforeMethod()
	public void navigateToHome() throws IOException {
//		WebDriver driver = BaseTest.getDriver();
		
	}

	@Test (priority = 1)
	public void userMenuDropDown_TC06(Method name) throws IOException, InterruptedException {
		WebDriver driver = BaseTest.getDriver();
		ExtentTest test = BaseTest.threadExtentTest.get();
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(driver);
		test.info("Driver configured");
		UserMenuPage ump = new UserMenuPage(driver);
		CommonUtils.waitForElement(driver, ump.userMenu);
		test.info("Username element is visible");
		ump.userMenu.click();
		test.info("clicked on usermenu");
		Assert.assertTrue(ump.verifyUserMenuItems(), "Failed to verify user menu options");
		test.info("Verified User Menu items");
		Assert.assertTrue(ump.selectMyProfile(), "");
		test.info("Selected my profile");
		CommonUtils.waitForElement(driver, ump.profilePage);
		test.info("profile page loaded");
		Assert.assertTrue(ump.isProfilePageVisible(), "");
		ump.selectEditContact(driver);
		Assert.assertTrue(ump.verifyEditContactPopUp(driver), "");
		test.info("Edit contact pop up verified");
		Assert.assertTrue(ump.verifyLastNameChangeInAboutTab(driver, "Tekarch"), "");
		test.info("Verified last name change");
		Assert.assertTrue(ump.verifyCreatePost(driver, "Hello welcome to java"));
		test.info("Verified create post");
		Assert.assertTrue(ump.verifyFileUpload(driver, FileConstants.LOGIN_TESTDATA_FILE_PATH));
		test.info("Verified file upload");
		Assert.assertTrue(ump.verifyPhotoUpload(driver, FileConstants.PROFILE_PHOTO_FILE_PATH));
		test.info("Verified photo upload");
		
		
	}
	
	@Test ()
	public void userMenuDropDown_TC07() throws IOException {
		
		WebDriver driver = BaseTest.getDriver();
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(driver);
	}
	
	@Test ()
	public void userMenuDropDown_TC08() {
		
		System.out.println("userMenuDropDown_TC08");
	}
	

}
