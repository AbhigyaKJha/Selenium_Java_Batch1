package pageObects;

import org.openqa.selenium.By;

import utils.DriverManager;
import utils.ElementUtils;

public class SaucelabsLoginPage {

	// Locators for all WebElements
	private By email = By.id("user-name");
	private By password = By.id("password");
	private By loginbutton = By.className("btn_action");
	private By loginErrorMessage = By.xpath("//h3[@data-test='error']");

	// Action Methods

	public SauceLabsProductsPage doLogin(String userName, String pwd) {

		ElementUtils.type(email, userName, "Email");
		ElementUtils.type(password, pwd, "Password");
		ElementUtils.click(loginbutton, "LoginButton");
		return new SauceLabsProductsPage();
	}

	public void doLoginWithInvalidCredentials(String userName, String pwd) {

		ElementUtils.type(email, userName, "Email");
		ElementUtils.type(password, pwd, "Password");
		ElementUtils.click(loginbutton, "LoginButton");

	}

	public String getPageTitle() {

		return DriverManager.getDriver().getTitle();
	}

	public String getLoginErrorMessage() {
		return ElementUtils.getElementText(loginErrorMessage);
	}

}
