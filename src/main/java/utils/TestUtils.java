package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TestUtils {
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException {

		screenshotPath = System.getProperty("user.dir") + "/Screenshots/";

		TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
		File scrFile = ts.getScreenshotAs(OutputType.FILE);

		Date d = new Date();

		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		FileUtils.copyFile(scrFile, new File(screenshotPath + screenshotName));

	}
	
	

}
