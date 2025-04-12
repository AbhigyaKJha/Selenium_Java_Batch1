package demo.saucelabs;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObects.SauceLabsProductsPage;
import pageObects.SaucelabsLoginPage;
import testSetup.BaseTest;
import utils.Data;
import utils.ElementUtils;
import utils.ExtentReportManager;

public class SauceLabsLoginTest extends BaseTest {

	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void verifyLoginWithValidCredentials(String UserName, String Password) throws InterruptedException {
		ExtentReportManager.getExtentTest().assignAuthor("Abhigya");
		
		SaucelabsLoginPage loginPage = new SaucelabsLoginPage();
		SauceLabsProductsPage productPage = loginPage.doLogin(UserName, Password);

		String actualProductHeader = productPage.getHeaderText();
		// Assert if Header element is displayed
		Assert.assertTrue(productPage.isProductHeaderDisplayed());
		Assert.assertEquals(actualProductHeader, "Products");
		Thread.sleep(5000);

	}

	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void verifyLoginWithInValidCredentials(String UserName, String Password) throws InterruptedException {
		ExtentReportManager.getExtentTest().assignAuthor("Abhigyaa");
		SaucelabsLoginPage loginPage = new SaucelabsLoginPage();
		loginPage.doLoginWithInvalidCredentials(UserName, Password);

		String expetcedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
		String actualErrorMeString = loginPage.getLoginErrorMessage();
		Assert.assertEquals(expetcedErrorMessage, actualErrorMeString);

		Thread.sleep(5000);

	}

}
