package testProject.pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testProject.AbstractComponents.HelperFunction;

public class CartPage extends HelperFunction {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title = 'Shopping Cart']//span")
	WebElement shoppingCart;
	
	@FindBy(xpath = "//div[@class = 'table-responsive']//tr//td[2]")
	List<WebElement> productNameColumn;
	
	@FindBy(xpath = "//a[text() = 'Estimate Shipping & Taxes '] ")
	WebElement estimateShippingBtn;
	
	@FindBy(id = "input-country")
	WebElement country;
	
	@FindBy(id = "input-zone")
	WebElement state;
	
	@FindBy(id = "input-postcode")
	WebElement postCode;
	
	@FindBy(id = "button-quote")
	WebElement quote;
	
	@FindBy(className = "modal-title")
	WebElement modalTitle;
	
	@FindBy(name = "shipping_method")
	WebElement flatShippingRate;
	
	@FindBy(id = "button-shipping")
	WebElement applyShipping;
	
	@FindBy(xpath = "//div[contains(@class , 'alert')]")
	WebElement shippingMsg;
	
	@FindBy(xpath = "//a[text() = 'Checkout']")
	WebElement checkOutBtn;
	
	@FindBy(xpath = "//button[@data-original-title = 'Remove']")
	List<WebElement> removeBtns;
	
	public void openShoppingCart() {
		try {
			clickOnElement(shoppingCart);
			waitForVisibilityOfElement(checkOutBtn);
		} catch (Exception e) {
			throw new Error("openShoppingCart has an issue" + e);
		}
		
	}
	
	public void verifyProductsAdded(String product) {
		try {
			boolean flag = productNameColumn.stream().anyMatch(prod->prod.getText().contains(product));
			System.out.println(flag);
			Assert.assertTrue(flag);
		} catch (Exception e) {
			throw new Error("Verify products added has an issue" + e);
		}
	}
	
	public void estimateShippingCost() {
		try {
			//Click on Estimate btn.
			clickOnElement(estimateShippingBtn);
			
			//Select country, state and add pincode.
			openDropDownAndSelect(country, "India");
			openDropDownAndSelect(state, "Karnataka");
			postCode.sendKeys("582102");
			
			//Click on Quote
			clickOnElement(quote);
			
			//Wait for title.
			waitForVisibilityOfElement(modalTitle);
			Assert.assertEquals(modalTitle.getText(), "Please select the preferred shipping method to use on this order.");
			
			clickOnElement(flatShippingRate);
			clickOnElement(applyShipping);
			
			waitForVisibilityOfElement(shippingMsg);
			Assert.assertTrue(shippingMsg.isDisplayed());
			System.out.println("Shipping msg is "+shippingMsg.getText());
			Assert.assertTrue(shippingMsg.getText().contains("Success: Your shipping estimate has been applied!"));
			
		} catch (Exception e) {
			throw new Error("Estimate shipping cost has an issue" + e);
		}
	}
	
	public void checkOut(String product) {
		try {
			openShoppingCart();
			verifyProductsAdded(product);
			estimateShippingCost();
			clickOnElement(checkOutBtn);
			removeItems();
		} catch (Exception e) {
			throw new Error("Check out has an issue" + e);
		}
	}
	
	public void removeItems() {
		try {
			removeBtns.stream().forEach(btn -> clickOnElement(btn));
		} catch (Exception e) {
			throw new Error("Remove items from cart has an issue" + e);
		}
	}
	
	
	
		
}
