package utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	
	//Extent Reporting Object
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	
	public void onStart(ITestContext testContext)
	{
		
		
		String workspacePath=System.getProperty("user.dir");
		
		htmlReporter=new ExtentHtmlReporter(workspacePath+"\\Reports\\RestApiReport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		
		htmlReporter.config().setReportName("Rest Api Testing Report");
		
		htmlReporter.config().setTheme(Theme.DARK);
		
		
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Project Name", "RestApiTesting");
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("user", "Judith");
		extent.setSystemInfo("Enviornment", "QA");
		
	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		
	
		test=extent.createTest(result.getName());// create New entry in the report
		
		test.log(Status.PASS, "Test Case PASSED ID is :"+result.getName());
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		
		test=extent.createTest(result.getName());// create New entry in the report
		test.log(Status.FAIL, "Test Case Failed ID is: "+result.getName());
		//code for exception
		test.log(Status.FAIL, "Test Case FAILED IS "+result.getThrowable());
		
	}
	
	
	
	
	public void onTestSkipped(ITestResult result)
	{
		
		test=extent.createTest(result.getName());// create New entry in the report

		test.log(Status.SKIP, "Test Case SKIPPED IS : "+result.getName());
		
		
	}
	
	
	public void onFinish(ITestContext testContext)
	{
		
		extent.flush();
		
	}
	

}
