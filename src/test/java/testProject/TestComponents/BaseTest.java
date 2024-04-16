package testProject.TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeTest;

import testProject.pageObjects.LoginPage;
import testProject.pageObjects.ProductsPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public WebDriver initializeDriver() {
				//Give the required chrome set up options.
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				//Create a driver for the test.
				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//reports//"+testCaseName+".png";
		File file = new File(path);
		FileHandler.copy(source, file);
		return path;	
	}
	


}
