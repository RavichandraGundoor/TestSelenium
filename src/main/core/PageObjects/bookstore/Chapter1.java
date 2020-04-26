package main.core.PageObjects.bookstore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.core.UI_Module;

public class Chapter1 extends UI_Module {
	public Chapter1(WebDriver driver) {
		driverThread=driver;
	}
	
	private By Obj_Chapter1_link = By.cssSelector("a[href='/chapter1']");
	public void Chapter1_link() throws Exception {
		Click(Obj_Chapter1_link);
		
		Thread.sleep(5000);
		GetParentWindowHandles();
		CloseChildWindow();
		SwitchtoParentWindow();
	}
	

}
