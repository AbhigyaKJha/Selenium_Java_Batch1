package pageObects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class SauceLabsProductsPage {

	private By productsPageHeader = By.xpath("//div[text()='Products']");
	private By addProductToCart = By.xpath("(//button[text()='ADD TO CART'])[1]");
	private By cartLogo = By.xpath("//*[@data-icon='shopping-cart']");
	

	public String getHeaderText() {
		// If header is displayed then only return the text... else terminate the
		return ElementUtils.getElementText(productsPageHeader);

	}

	public boolean isProductHeaderDisplayed() {
		return ElementUtils.getWebElement(productsPageHeader).isDisplayed();
	}

	public void addProductToCart() {
		ElementUtils.click(addProductToCart,"Add To Cart");
	}

	// ToDo
	public void addProductToCart(String productName) {

	}

	// ToDo
	public void addProductToCart(List<String> productName) {

	}

	public SauceLabsCartPage clickOnCartLogo() {
		ElementUtils.click(cartLogo,"Cart Logo");
		return new SauceLabsCartPage();
	}

}
