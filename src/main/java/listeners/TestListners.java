package listeners;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import testSetup.BaseTest;
import utils.ExtentReportManager;
import utils.TestUtils;

public class TestListners extends BaseTest implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Inside Test Start ->");

		ExtentReportManager.setExtentTest(ExtentReportManager.getExtent().createTest(result.getName()));
		System.out.println("Extent Test  -> " + ExtentReportManager.getExtentTest());
		String logmessage = "<b>" + "<i>" + "Execution of " + result.getName() + " Started" + "</i>" + "</b>";
		Markup m = MarkupHelper.createLabel(logmessage, ExtentColor.GREY);
		ExtentReportManager.getExtentTest().info(m);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String successMessage = "<b>" + "<i>" + "This Test Case is Passed" + "</i>" + "</b>";
		Markup m = MarkupHelper.createLabel(successMessage, ExtentColor.GREEN);
		ExtentReportManager.getExtentTest().pass(m);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String exceptionStackTrace = Arrays.toString(result.getThrowable().getStackTrace());

		// System.out.println(failedTestCaseInfo.getThrowable().getStackTrace().toString());
		ExtentReportManager.getExtentTest().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" + exceptionStackTrace.replaceAll(",", "<br>") + "</details>"
				+ " \n");
		try {

			ExtentReportManager.getExtentTest().fail("<b>" + "<font color=" + "red>" + "ScreenShot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.screenshotPath + TestUtils.screenshotName)
							.build());
		} catch (Exception e) {

		}

		String successMessage = "<b>" + "<i>" + "This Test Case is Failed" + "</i>" + "</b>";
		Markup m = MarkupHelper.createLabel(successMessage, ExtentColor.RED);
		ExtentReportManager.getExtentTest().fail(m);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String successMessage = "<b>" + "<i>" + "This Test Case is Skipped" + "</i>" + "</b>";
		Markup m = MarkupHelper.createLabel(successMessage, ExtentColor.YELLOW);
		ExtentReportManager.getExtentTest().skip(m);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReportManager.getExtent().flush();

	}

}
