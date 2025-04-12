package utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import testSetup.BaseTest;


public class Data extends BaseTest {

	@DataProvider(name = "data")
	public synchronized static Object[][] getData(Method m) {
		// Getting the Sheetname from the className
		String sheetName =m.getDeclaringClass().getSimpleName();
		
		System.out.println("Sheet Name IS-->" +sheetName);
		// get the total number of rows in the sheet
		int numberOfRows = excel.getRowCount(sheetName);
		System.out.println("Number Of ROws->"+numberOfRows);

		// Getting the name of the testcase which will be executed
		String testCaseName = m.getName();
		System.out.println("Test case name->"+testCaseName);

		int testCaseStartsRowNum = 1;

		for (testCaseStartsRowNum = 1; testCaseStartsRowNum <= numberOfRows; testCaseStartsRowNum++) {
			// get the value of first column of each row
			String testcaseNameInExcel = excel.getCellData(sheetName, 0, testCaseStartsRowNum);

			// Now I will compare the value I am geeting from excel to my
			// testcase name
			if (testcaseNameInExcel.equalsIgnoreCase(testCaseName)) {
				System.out.println("Test case found in the excel file");
				break;
			}
		}

		int testDataStartRowNum = testCaseStartsRowNum + 2;
		int numberOfTestDataRows = 0;

		//I am getting total Number of TestData Row using below while loop and storing it in numberOfTestDataRows variable
		while (!excel.getCellData(sheetName, 0, testDataStartRowNum + numberOfTestDataRows)
				.equalsIgnoreCase("endOfTestData")) {
			System.out.println("inside first while loop");
			numberOfTestDataRows++;//1

		}
		
		System.out.println("Total Number of Test Data ROws--> "+numberOfTestDataRows);
		
		int headerStartRowNum=testCaseStartsRowNum+1;
		int totalHeadersCount=0;
		//Finding out total number of columns
		while(!excel.getCellData(sheetName, totalHeadersCount, headerStartRowNum).equals(""))
		{
			totalHeadersCount++;//2
		}
		System.out.println("Total Number of Test Data Columns--> "+totalHeadersCount);
		
	

		Object[][] data = new Object[numberOfTestDataRows][totalHeadersCount];//[1][2]
		int r=0;

		for (int rows = testDataStartRowNum; rows < testDataStartRowNum+numberOfTestDataRows; rows++) //rows=
		{
				System.out.println("Rows--> "+testCaseStartsRowNum);

			
			for (int cols = 0; cols < totalHeadersCount; cols++) 
			{
				

				data[r ][cols] = excel.getCellData(sheetName, cols, rows);//[1][1]='secret_sauce'

			}
			r++;

		}

		return data;
		
		
		
		
	}

}
