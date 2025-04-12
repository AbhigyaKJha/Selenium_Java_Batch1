package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	private static ExtentSparkReporter htmlReporter;
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();

	public synchronized static  ExtentReports ExtentReportSetup() {
		htmlReporter = new ExtentSparkReporter("./TestReports/TestReport.html");

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Selenium With Java  Batch1 TestReport");
		htmlReporter.config().setReportName("Way2Automation Selenium Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	public static ExtentReports getExtent() {
		return ExtentReportManager.extent;
	}

	public static ExtentTest getExtentTest() {
		return ExtentReportManager.extentTest.get();
	}

	public static void setExtentTest(ExtentTest test) {
		ExtentReportManager.extentTest.set(test);
	}

}
