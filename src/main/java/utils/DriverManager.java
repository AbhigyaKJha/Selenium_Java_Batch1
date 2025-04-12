package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	private static final int defaultWaitTime = 30;

	public static WebDriver getDriver() {
		return DriverManager.driver.get();
	}
	
	public static void setDriver(WebDriver driver) {
		DriverManager.driver.set(driver);
	}

	public synchronized static WebDriver createDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			DriverManager.setDriver(new ChromeDriver());
			maximizeBrowser();
			applyImplicitWait();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			DriverManager.setDriver(new FirefoxDriver());
			maximizeBrowser();
			applyImplicitWait();

		} else if (browserName.equalsIgnoreCase("safari")) {
			DriverManager.setDriver(new SafariDriver());
			maximizeBrowser();
			applyImplicitWait();
		} else {

		}
		return DriverManager.getDriver();
	}

	public static void maximizeBrowser() {
		DriverManager.getDriver().manage().window().maximize();
	}

	public static void applyImplicitWait() {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultWaitTime));
	}

	public static void destroyDriver(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}

	}

	public synchronized static  String getScreenshot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
