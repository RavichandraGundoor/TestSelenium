package main.core.PageObjects.bookstore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.core.UI_Module;

public class Login extends UI_Module {
	public Login(WebDriver driver) {
		driverThread=driver;
	}
	
	private By Obj_MainHeading = By.cssSelector("div[class='mainheading']");
	public void Login() throws Exception {
		Get("http://book.theautomatedtester.co.uk/");
		
	//Assert on main text
		String mainheading = GetText(Obj_MainHeading);
		System.out.println("Main Heading is: "+mainheading);
	}

}
