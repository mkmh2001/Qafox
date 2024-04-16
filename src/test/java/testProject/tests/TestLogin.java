package testProject.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testProject.TestComponents.BaseTest;
import testProject.pageObjects.CartPage;
import testProject.pageObjects.LoginPage;
import testProject.pageObjects.ProductsPage;

public class TestLogin extends BaseTest {

	@Test
	public void test1() throws InterruptedException {

		WebDriver driver = initializeDriver();

		List<String> requiredMonitors = new ArrayList<String>();
		requiredMonitors.add("Apple Cinema 30");
		requiredMonitors.add("Samsung SyncMaster 941BW");

		LoginPage loginPage = new LoginPage(driver);
		
		ProductsPage productsPage = loginPage.loginToApplication("qafoxTestMk@gmail.com", "1234");
		productsPage.openMonitorTab();
		
		CartPage cartPage = productsPage.addProductToCart("Apple Cinema 30");
		cartPage.checkOut("Apple Cinema 30");
		
		driver.quit();

	}
}
