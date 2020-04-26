package main.core;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

public class UI_Module {
	public static WebDriver driverThread;

	public static WebDriver initDriver(String Browsers) throws Exception {
		if (Browsers.equalsIgnoreCase("C") || Browsers.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("version"); // Prints chrome browser version
			options.addArguments("disable-extensions"); // Disables existing extensions on Chrome browser
			options.addArguments("disable-infobars"); // Prevents Chrome from displaying the notification 'Chrome is
														// being controlled by automated software
			// options.addArguments("incognito"); //Opens Chrome in incognito mode
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ravi\\Desktop\\Presentation\\Tools\\Jars and Drivers\\chromedriver_2.41.exe");
			driverThread = new ChromeDriver(options);
			Reporter.log("Chrome Browser Opened");
			return driverThread;
		}else if (Browsers.equalsIgnoreCase("Firefox") || Browsers.equalsIgnoreCase("Mozilla") || Browsers.equalsIgnoreCase("F")){
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\Ravi\\Desktop\\Presentation\\Tools\\Jars and Drivers\\geckodriver.exe");
				driverThread = new FirefoxDriver();
				Reporter.log("Mozilla Firefox Browser Opened");
				return driverThread;
			
		} else {
			return null;
		}

	}
	public WebElement fluentWait(By by) {
	@SuppressWarnings("deprecation")
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driverThread)    
		    .withTimeout(30, TimeUnit.SECONDS)    
		    .pollingEvery(5, TimeUnit.SECONDS)   
		    .ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {    
		    public WebElement apply(WebDriver driver) {    
		        return driver.findElement(by);    
		    }
		});
		return element;
	}
	/**
	 * Load URL
	 * 
	 * @param URL
	 * @throws Exception
	 */
	protected void Navigateto(String URL) throws Exception {
		driverThread.navigate().to(URL);
	}
	/**
	 * Load URL
	 * 
	 * @param URL
	 * @throws Exception
	 */
	protected void Get(String URL) throws Exception {
		driverThread.get(URL);
	}

	/**
	 * Close Browser
	 * 
	 * @throws Exception
	 */
	protected static void CloseBrowser() throws Exception {
		driverThread.close();
	}

	/**
	 * Check for Alert
	 */
	protected static boolean ISAlertPresent() {
		try {
			driverThread.switchTo().alert();
			String alert_text = driverThread.switchTo().alert().getText();
			System.out.println("Alert Present: "+alert_text);
			return true;
		} catch (NoAlertPresentException Ex) {
			System.out.println("No Alert Present");
			return false;
		}
	}

	/**
	 * AcceptAlert
	 * 
	 * @throws Exception
	 */
	protected static void AcceptAlert() throws Exception {
		try {
			String alert_text = driverThread.switchTo().alert().getText();
			System.out.println("Accepting Alert Box: "+alert_text);
			driverThread.switchTo().alert().accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Iterate Accept Alert
	 * 
	 * @throws Exception
	 */
	protected static void IterateAcceptAlert() throws Exception {
		while (ISAlertPresent() == true)
			AcceptAlert();
	}

	/**
	 * Click an Element
	 * 
	 * @param by
	 * @throws Exception
	 */
	protected void Click(By by) throws Exception {
		WebElement element = fluentWait(by);
		if (element == null) {
			Negative_Message(by);
		} else {
			element.click();
			Positive_Message(by);
		}
		// WebElement element = FluentWait(by,10);
		// new FluentWait()
	}

	/**
	 * EnterText into element
	 * 
	 * @param by
	 * @throws Exception
	 */
	protected void Enter(By by, String text) throws Exception {
		WebElement element = fluentWait(by);
		if (element == null) {
			Negative_Message(by);
		} else {
			element.clear();
			element.sendKeys(text);
			Positive_Message(by);
		}
		// WebElement element = FluentWait(by,10);
		// new FluentWait()
	}
	/**
	 * Get the Text
	 * 
	 * @param by
	 * @throws Exception
	 */
	protected String GetText(By by) throws Exception {
		WebElement element = fluentWait(by);
		if (element == null) {
			Negative_Message(by);
			return null;
		} else {
			return element.getText();
		}
		// WebElement element = FluentWait(by,10);
		// new FluentWait()
	}
	
	/**
	 * Get the Parent Window
	 * 
	 * @throws Exception
	 */
	static String ParentWindow;
	protected String GetParentWindowHandles() throws Exception {
		ParentWindow = driverThread.getWindowHandle();
		return ParentWindow;

	}
	/**
	 * Close Child Window
	 * 
	 * @throws Exception
	 */
	protected void CloseChildWindow() throws Exception {
		for (String winHandle : driverThread.getWindowHandles()) {
			if(!winHandle.equalsIgnoreCase(ParentWindow)) {
				driverThread.switchTo().window(winHandle);
				driverThread.close();
			}
		}
	}
	/**
	 * Switch to Parent Window
	 * 
	 * @throws Exception
	 */
	protected void SwitchtoParentWindow() throws Exception {
		driverThread.switchTo().window(ParentWindow);
	}
	
	
	protected void Positive_Message(By by) throws Exception {
		System.out.println("Element located by " + by);
	}

	protected void Negative_Message(By by) throws Exception {
		System.out.println("Unable to Locate Element " + by);
		throw new Exception("Unable to Locate Element " + by);
	}

}
