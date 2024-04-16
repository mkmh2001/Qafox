package testProject.pageObjects;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testProject.AbstractComponents.HelperFunction;

public class ProductsPage extends HelperFunction {

	WebDriver driver;
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//nav[@id = 'menu']//following::a[text() = 'Components']")
	WebElement components;
	
	@FindBy(xpath = "//a[contains(text() , 'Monitors')]")
	WebElement monitorOption;
	
	@FindBy(xpath = "//div[@class = 'product-thumb']")
	List<WebElement> monitors;
	
	@FindBy(id = "button-cart")
	WebElement addToCart;
	
	@FindBy(xpath = "//div[@id = 'content']//h1")
	WebElement addedProd;
	
	@FindBy(xpath = "//label[text() = 'Radio']//following::input[@type = 'radio']")
	WebElement radio;
	
	@FindBy(xpath = "//label[text() = 'Checkbox']//following::input[@type = 'checkbox']")
	WebElement checkBox;
	
	@FindBy(xpath = "//label[text() = 'Text']//following::input")
	WebElement txtBox;
	
	@FindBy(xpath = "//label[text() = 'Select']//following::select")
	WebElement selectDD;
	
	@FindBy(xpath = "//textarea[@placeholder = 'Textarea']")
	WebElement txtArea;
		
	@FindBy(id = "button-upload222")
	WebElement uploadFileBtn;
	
	public void openMonitorTab() {
		try {
			waitForVisibilityOfElement(components);
			clickOnElement(components);
			clickOnElement(monitorOption);
		} catch (Exception e) {
			throw new Error("Open monitor tab has an issue" + e);
		}
	}
	
	public void AddMonitorsToCard(List<String> requiredMonitors) {
		try {	
			//The below code doesn't work bcz of -> stale element reference: stale element not found.
			
			//For every monitor in requiredMonitors, check if it is present in web.S
			for(String name: requiredMonitors) {
				openMonitorTab();
				for(WebElement monitor: monitors) {
					String nameFromApp = monitor.findElement(By.xpath("//div[@class = 'caption']")).getText();
					//If the monitor name is present, click on add to cart and proceed.
					if(nameFromApp.contains(name)) {
						clickOnElement(monitor.findElement(By.xpath("//span[text() = 'Add to Cart']")));
						waitForVisibilityOfElement(addToCart);
						Thread.sleep(5000);
						String temp = addedProd.getText();
						if(temp.charAt(temp.length() - 1) == '"') {
							temp = temp.substring(0, temp.length() - 1);						
						}
						Assert.assertTrue(nameFromApp.contains(temp));
						
						//Click on Radio
						clickOnElement(radio);
						
						//Click on checkBox
						clickOnElement(checkBox);
						
						//Type on text box
						typeOnTxtBox(txtBox, "MkTest");
						
						//Select DropDown value
						openDropDownAndSelect(selectDD, "Red (+$4.80) ");
						
						//Type on Text area
						typeOnTxtBox(txtArea, "Area Test");
						
						//Uploading file.
						clickOnElement(uploadFileBtn);
						Thread.sleep(2000);
						Runtime.getRuntime().exec("C:\\Users\\lmbma\\Desktop\\Automation\\Projects\\Qafox\\fileUpload.exe");		
						//Thread.sleep(7000);
						waitForAlert();
						driver.switchTo().alert().accept();
						
					
						//Click on Add to cart
						clickOnElement(addToCart);						
						Thread.sleep(3000);
						continue;
					}
				}
			}
		} catch (Exception e) {
			throw new Error("Add monitors to tab has an issue" + e);
		}
	}
	
	public CartPage addProductToCart(String product) {
		try {				
				for(WebElement monitor: monitors) {
					
					String nameFromApp = monitor.findElement(By.xpath("//div[@class = 'caption']")).getText();
					//If the monitor name is present, click on add to cart and proceed.
					if(nameFromApp.contains(product)) {
						clickOnElement(monitor.findElement(By.xpath("//span[text() = 'Add to Cart']")));
						waitForVisibilityOfElement(addToCart);
						Thread.sleep(5000);
						String temp = addedProd.getText();
						if(temp.charAt(temp.length() - 1) == '"') {
							temp = temp.substring(0, temp.length() - 1);						
						}
						Assert.assertTrue(nameFromApp.contains(temp));
						
						//Click on Radio
						clickOnElement(radio);
						
						//Click on checkBox
						clickOnElement(checkBox);
						
						//Type on text box
						typeOnTxtBox(txtBox, "MkTest");
						
						//Select DropDown value
						openDropDownAndSelect(selectDD, "Red (+$4.80) ");
						
						//Type on Text area
						typeOnTxtBox(txtArea, "Area Test");
						
						//Uploading file.
						clickOnElement(uploadFileBtn);
						Thread.sleep(2000);
						Runtime.getRuntime().exec("C:\\Users\\lmbma\\Desktop\\Automation\\Projects\\Qafox\\fileUpload.exe");		
						//Thread.sleep(7000);
						waitForAlert();
						driver.switchTo().alert().accept();
						
					
						//Click on Add to cart
						clickOnElement(addToCart);						
						Thread.sleep(3000);
						break;
					}
				}
		} catch (Exception e) {
			throw new Error("Add monitors to tab has an issue" + e);
		}
		return new CartPage(driver);
	}
	
	
}
