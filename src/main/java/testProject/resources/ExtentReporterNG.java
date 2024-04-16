package testProject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReportsObject() {
		//ExtentReports, ExtentSparkReporter.
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter  reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web automation results");
		reporter.config().setDocumentTitle("QAfox test results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Manojkumar");
		return extent;
	}

}
