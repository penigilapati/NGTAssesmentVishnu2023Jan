package com.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;
	public static Properties prop;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void EnterUserName(String s) {
		driver.findElement(By.id("mobileNumberPass")).sendKeys(s);
	}

	public void EnterPassword(String s) {
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(s);
	}

	public void ClickLoginButton() {
		driver.findElement(By.xpath("//*[text()='LOGIN']")).click();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void Login(String username, String password) throws Exception {
		EnterUserName(username);
		EnterPassword(password);
		ClickLoginButton();
		Thread.sleep(32000);
		ClickLoginButton();
		Thread.sleep(3000);
	}

	public void verify_Login(String expected, String actual) {
		Assert.assertEquals(expected, actual, "The user is not logged in");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@data-reactid='855']")).isDisplayed());
	}
}
