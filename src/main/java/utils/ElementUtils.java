package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class ElementUtils {


	public static void type(By locator, String value) {

		
		DriverManager.getDriver().findElement(locator).sendKeys(value);
		

	}
	public static void type(By locator, String value, String fieldName) {

		
		DriverManager.getDriver().findElement(locator).sendKeys(value);
		ExtentReportManager.getExtentTest()
				.info("Entered " + "<b>" + value + "</b>" + " in " + "<b>" + fieldName + "</b>" + " Field");

	}

	public static void click(By locator) {

		DriverManager.getDriver().findElement(locator).click();
	}
	
	public static void click(By locator, String fieldName) {

		DriverManager.getDriver().findElement(locator).click();
		ExtentReportManager.getExtentTest().info("Clicked on " + "<b>" + fieldName + "</b>");
	}

	public static String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public static String getElementText(By locator) {
		return DriverManager.getDriver().findElement(locator).getText();
	}

	public static WebElement getWebElement(By locator) {
		return DriverManager.getDriver().findElement(locator);
	}

}
