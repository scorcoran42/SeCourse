package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaScriptTst {
	private static WebDriver driver = new FirefoxDriver();
	
	@BeforeClass
	public static void setup(){
		driver.get("http://compendiumdev.co.uk/selenium/canvas_basic.html");
	}
	@AfterClass
	public static void shutdown(){
		driver.quit();
	}
	
	@Before
	public void refreshPage(){driver.navigate().refresh();}
	
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
}
