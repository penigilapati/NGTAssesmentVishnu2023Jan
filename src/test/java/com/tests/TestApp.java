package com.tests;

import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.LoginPage;



import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestApp {
	 WebDriver driver;
	 LoginPage login;
	 CartPage cart;
	 public static Properties prop;
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  driver = new ChromeDriver();
	  String rootFolder= System.getProperty("user.dir");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	  login=new LoginPage(driver);
	  cart=new CartPage(driver);
	  prop=new Properties();
	  prop.load(new FileInputStream(rootFolder+"//src//test//resources//loginDetails.properties"));
	  driver.get(prop.getProperty("loginLink"));
	  
  }
  @Test
  public void verifyLoginPage() throws Exception {
	 
	 login.Login(prop.getProperty("email"),prop.getProperty("password"));
	 login.verify_Login(prop.getProperty("expectedTitle"),login.getPageTitle());
  }
  @Test
  public void verifyCartPage() throws Exception
  {
	  login.Login(prop.getProperty("email"),prop.getProperty("password"));
	  String expectedText=prop.getProperty("expectedText");
	  cart.verify_EmptyCart(expectedText);
	  cart.add_to_cart(prop.getProperty("searchvalue"));
	  String expectedItem=prop.getProperty("addedItem");
	  String actualItem=driver.findElement(By.xpath("//a[text()='Men Blue Slim Fit Faded Denim Casual Shirt']")).getText();
	  cart.verify_cart_after_adding(expectedItem,actualItem);
	  
	  
  }
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
