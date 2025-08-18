package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
    public void onStart(ITestContext testContext) {
 
            // Report file name with timestamp
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName= "Test-Report-"+timestamp+".html";
            
            // Report path
            sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

            // Create reporter
            sparkReporter.config().setDocumentTitle("API Automation Test Report");
            sparkReporter.config().setReportName("Pet Store Users API Test Results");
            sparkReporter.config().setTheme(Theme.DARK);

            // Attach reporter to ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system/environment info
            extent.setSystemInfo("Tester", "Shaik Yaseen");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Application", "Pet Store Users API");
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            
 }
    
    public void onTestSuccess(ITestResult result)
    {
    	
    	test=extent.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
    	test.log(Status.PASS, "Test Passed");   	
    	
    
    }
    public void onTestFailure(ITestResult result)
    {
    	
    	test=extent.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
    	test.log(Status.FAIL, "Test Failed");   
    	test.log(Status.FAIL, result.getThrowable().getMessage());

    
    }
    public void onTestSkipped(ITestResult result)
    {
    	
    	test=extent.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
    	test.log(Status.SKIP, "Test Skipped");   
    	test.log(Status.SKIP, result.getThrowable().getMessage());

    }
    
    public void onFinish(ITestContext testContext)
    {
    	extent.flush();
    }
}
