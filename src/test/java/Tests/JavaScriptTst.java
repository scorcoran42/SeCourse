package Tests;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScriptTst {
	private static WebDriver driver = new FirefoxDriver();
	private static WebDriverWait wait = new WebDriverWait(driver,10);

	
	@AfterClass
	public static void shutdown(){
		driver.quit();
	}
	
	@Before
	public void refreshPage(){
		driver.get("http://compendiumdev.co.uk/selenium/canvas_basic.html");
		wait.until(ExpectedConditions.titleIs("Javascript Canvas Example"));
		}
	
	@Test
	public void callDrawWithExecuteScript() {
		//confirm page is fresh
		int actionCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertEquals("Should be 2 by default", 2, actionCount);
		//setup JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//clear the canvas
		js.executeScript("clearCanvas();");
		//draw some circles with arguments
		for (int i=0; i < 5; i++){

			js.executeScript("draw(1,150,150,arguments[0],arguments[1]);",
					150 - (30*i), "#" + (i*20) + (i*20) + "00");
			actionCount++;
		}
		//assert the count is correct
		assertEquals("Should be 7", 7, actionCount);
	}
	
	@Test
	public void executeScriptToAdd() {
		//setup JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//assert addition in JS worked
		assertEquals(42L, js.executeScript("var x; x = arguments[0] + arguments[1]; return(x);", 40, 2));
	}
	
	@Test
	public void hideAnElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//get the canvas as a web element
		WebElement webEl = driver.findElement(By.cssSelector("#canvas"));
		//confirm that the canvas is displayed
		assertTrue(webEl.isDisplayed());
		//execute JS to hide the canvas
		js.executeScript("$(arguments[0]).hide();", webEl);
		//confirm that the canvas is not displayed
		assertFalse(webEl.isDisplayed());
	}
	
	@Test
	public void leaveJSOnPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//create an alert that isn't left behind and dismiss it
		js.executeScript("alert('alert triggered by webdriver');");

        assertThat(driver.switchTo().alert().getText(), is("alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        // this code creates a function that will persist as we have added it to the global window
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');};"+
                    "window.webdriveralert.call();");

        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        // this can only work if we managed to leave javascript lying around
        js.executeScript("window.webdriveralert.call();");

        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();
	}
}
