package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import base.Base;
import pages.RegisterPage;
import utilities.ReadExcelFile;

public class RegisterPageTest extends Base {

	RegisterPage RegisterPage;
	
	@BeforeTest
	public void setupTest() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html",true);	
	}
	@BeforeMethod
	public void setupMethod() {
		Base.initialization();
		RegisterPage = new RegisterPage();
	}
	
	@DataProvider // to read valid data from excel sheet
	public Object[][] RegistrationDataFomExcelFile() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/java/testData";
		String fileName = "Test_Data.xlsx";
		String sheetName = "RegistrationData";
		return ReadExcelFile.readExcel(filePath, fileName, sheetName);
	} 
	
	@Test(dataProvider = "RegistrationDataFomExcelFile")
	public void VerifyRegisteration(String FN, String LN, String email, String PW, String ConfirmPW) {
		extentTest = extent.startTest("VerifyRegisterMsg");
		String Actual= RegisterPage.Registeration(FN, LN, email, PW, ConfirmPW);
		String Expected= "Thank you for registering with Madison Island.";
		Assert.assertEquals(Actual, Expected);
	}
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) { // in case of failure
			extentTest.log(LogStatus.FAIL, "FAILED:  " + result.getName()); // to add the test name to the extent report
			extentTest.log(LogStatus.FAIL, "ERROR MESSAGE:  " + result.getThrowable()); // to add error/exception 	
			String screenshotPath = Base.takeScreenShot(result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot 
		} 
		else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "SKIPPED:  " + result.getName());
		}else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "PASSED: " + result.getName());
		}
		//driver.quit();
	}
		
	@AfterTest
	public void tearDownTest() {
		extent.endTest(extentTest);
		extent.flush();
	}
	
}
