package Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.Driver;

public class DriverMgrTst {
	
	Driver helpdriver = new Driver();
	WebDriver driver;
	
	@After
	public void shutdown(){
		//clear the system property and quit
		System.clearProperty(Driver.SELENIUM2_BASICS_DRIVER);
		driver.quit();
	}
	
	@Test
	public void getAFirefoxDriver() {
		System.setProperty(Driver.SELENIUM2_BASICS_DRIVER, "firefox");
		assertGooglePageComesUp();
	}
	
	@Test
	public void getAChromeDriver() {
		System.setProperty(Driver.SELENIUM2_BASICS_DRIVER, "chrome");
		assertGooglePageComesUp();
	}
	
	@Test
	public void getAnIEDriver() {
		System.setProperty(Driver.SELENIUM2_BASICS_DRIVER, "ie");
		assertGooglePageComesUp();
	}
	
	@Test
	public void getADefaultDriver() {
		//get default by not setting system property
		//default is HtmlUnit
		assertGooglePageComesUp();
	}
	
	
	
	public void assertGooglePageComesUp() {
		driver = helpdriver.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://www.google.com");
		wait.until(ExpectedConditions.titleIs("Google"));
		Assert.assertEquals("Google", driver.getTitle());
		
	}
}
