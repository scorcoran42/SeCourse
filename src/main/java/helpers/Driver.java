package helpers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {
	
	public static final String SELENIUM2_BASICS_DRIVER = "selenium2Basics.driver";
	public Capabilities caps;
	
	public WebDriver getDriver() {
		
		//if not property set, return a blank string which will give the default browser in the switch
		String browser = System.getProperty(SELENIUM2_BASICS_DRIVER, "");
		
		switch (browser){
		case "firefox": 
			return new FirefoxDriver();
		case "chrome": 
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stuart\\EcMarsProjects\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-plugins");
			options.addArguments("disable-extensions");
			options.addArguments("test-type");
			return new ChromeDriver(options);
		case "ie":
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Stuart\\EcMarsProjects\\iedriverserver.exe");
			return new InternetExplorerDriver();
		default:
			return new HtmlUnitDriver();
		}
	}

}
