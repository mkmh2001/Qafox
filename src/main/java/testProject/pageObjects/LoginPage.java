package testProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import org.junit.Assert.*;
import testProject.AbstractComponents.HelperFunction;

public class LoginPage extends HelperFunction {

	// Create a constructor to assign driver to the class
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory elements
	@FindBy(id = "input-email")
	WebElement emailTxtBox;

	@FindBy(id = "input-password")
	WebElement passwordTxtBox;

	@FindBy(xpath = "//input[@value = 'Login']")
	WebElement loginBtn;

	String url = "https://tutorialsninja.com/demo/index.php?route=account/login";
	public void openApplication() {
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Opening the application has an issue " + e);
		}
	}
	
	public void verifyUrl() {
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}

	public ProductsPage loginToApplication(String email, String password) {
		try {
			this.openApplication();
			waitForVisibilityOfElement(emailTxtBox);
			emailTxtBox.sendKeys(email);
			passwordTxtBox.sendKeys(password);
			loginBtn.click();			
			return new ProductsPage(driver);
		} catch (Exception e) {
			System.out.println("Logging into the application has an issue " + e);
		}
		return null;
	}
}
