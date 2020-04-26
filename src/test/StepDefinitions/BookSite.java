package test.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.core.PageObjects.bookstore.Login;
import main.core.UI_Module;
import main.core.PageObjects.bookstore.Chapter1;


public class BookSite {
	public static WebDriver driverThread;
	
	Login login = new Login(driverThread);
	Chapter1 chapter1 = new Chapter1(driverThread);
	
	@Parameters({"Browser"})
	@Test
	public void Chapter1(@Optional("Chrome") String Browser) throws Exception {
		
		driverThread = UI_Module.initDriver(Browser); //Initialize Browser
		
		login.Login();  //Load the URL		Reporter.log("URL is loaded");
		
		chapter1.Chapter1_link(); //Click on Chapter 1 Link
		Reporter.log("Chapter 1 Link is clicked");
	}
	/*
	@AfterClass
	public void tearDown() {
		if(driverThread!=null)
			driverThread.close();
	}*/
}
