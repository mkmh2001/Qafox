package testProject.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class HelperFunction {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	public HelperFunction(WebDriver driver){
		this.driver = driver;
	}
	
	public void waitForVisibilityOfElement(WebElement el) {
		try {			
			wait.until(ExpectedConditions.visibilityOf(el));
		} catch (Exception e) {
			throw new Error("Wait for visibility of element has an issue" + e);
		}
	}
	
	public void waitForAlert() {
		try {
			int i=0;
			   while(i++<15)
			   {
			        try
			        {
			            Alert alert = driver.switchTo().alert();
			            break;
			        }
			        catch(NoAlertPresentException e)
			        {
			          Thread.sleep(1000);
			          continue;
			        }
			   }
		} catch (Exception e) {
			throw new Error("Wait for alert has an issue" + e);
		}
	}
	
	public void waitForElementToBeClickable(WebElement el) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(el));
		} catch (Exception e) {
			throw new Error("Wait for element to be clickable has an issue" + e);
		}
	}
	
	public void openDropDownAndSelect(WebElement el, String value) {
		try {
			waitForElementToBeClickable(el);
			Select dropDown = new Select(el);
			dropDown.selectByVisibleText(value);
			System.out.println("Drop down value " + value + " is slected" );
		} catch (Exception e) {
			throw new Error("Open drop down and select value has an issue" + e);
		}
	}
	
	public void clickOnElement(WebElement el) {
		try {
			waitForElementToBeClickable(el);
			el.click();
			System.out.println("Click on Element "+el.getText()+" is executed." );
		} catch (Exception e) {
			throw new Error("Click on "+el+" has an issue" + e);
		}
	}
	
	public void typeOnTxtBox(WebElement el,String txt) {
		try {
			waitForVisibilityOfElement(el);
			el.sendKeys(txt);
			System.out.println(txt + " is typed on the text box");
		} catch (Exception e) {
			throw new Error("Type on text box has an issue" + e);
		}
	}
	

	public void uploadFile(WebElement el, String uploadFile, String fileName) {
		try {
		    el.sendKeys(uploadFile);
		    clickOnElement(el);
		} catch (Exception e) {
			throw new Error("Upload file has an issue" + e);
		}
	}
	

}
