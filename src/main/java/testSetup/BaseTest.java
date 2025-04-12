package testSetup;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;

import utils.DriverManager;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.PropertyFileManager;

public class BaseTest {

	public static Properties props;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");
	
	
	@Parameters({ "env" })
	@BeforeSuite
	public void beforeSuite(String env) throws IOException {
		props = PropertyFileManager.getProperty(env);
		ExtentReportManager.ExtentReportSetup();
		

	}

	@Parameters({ "browser" })
	@BeforeMethod
	public synchronized void  setUp(String browser, Method m) {

		DriverManager.createDriver(browser);
		// Navigate to test URL
		DriverManager.getDriver().get(props.getProperty("testURL"));// UAT->testURL

	}

	@AfterMethod
	public void shutDown() throws InterruptedException {
		DriverManager.destroyDriver(DriverManager.getDriver());

	}

}
