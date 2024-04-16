package testProject.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testProject.AbstractComponents.HelperFunction;

public class ProductDetailsPage extends HelperFunction{
	
	WebDriver driver;
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id = 'content']//h1")
	WebElement addedProd;
	
	public void addProductDetails(String productString) {
		try {
			Thread.sleep(5000);
			String temp = addedProd.getText();
			if(temp.charAt(temp.length() - 1) == '"') {
				temp = temp.substring(0, temp.length() - 1);						
			}
			Assert.assertTrue(productString.contains(temp));
			return;
		} catch (Exception e) {
			throw new Error("Add product details has an issue");
		}
	}
	
	
}
