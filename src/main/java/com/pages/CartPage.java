package com.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {
  WebDriver driver;
  public CartPage(WebDriver driver)
  {
	  this.driver=driver;
  }
  public void verify_EmptyCart(String expected) throws Exception
  {   
	  driver.findElement(By.xpath("//*[@data-reactid='903']")).click();
	  Thread.sleep(2000);
	  String actual=driver.findElement(By.xpath("//*[text()='Hey, it feels so light!']")).getText();
	  Assert.assertEquals(expected,actual);
  }
  public void add_to_cart(String s) throws Exception
  {
	  driver.findElement(By.xpath("//a[@class=\"linkClean\"]")).click();
	  driver.findElement(By.xpath("//*[@data-reactid='907']")).sendKeys(s,Keys.ENTER);
	  driver.findElement(By.xpath("//img[@title=\"WROGN Men Blue Slim Fit Faded Denim Casual Shirt\"]")).click();
	//h1[text()="Men Blue Slim Fit Faded Denim Casual Shirt"]
	  ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
   
      driver.switchTo().window(newTab.get(1));
	  driver.findElement(By.xpath("//div[text()='ADD TO BAG']")).click();
	  driver.findElement(By.xpath("//*[@data-reactid='903']")).click();
	  Thread.sleep(3000);
	  driver.navigate().refresh();
  }
  public void verify_cart_after_adding(String expected,String actual)
  {
	  Assert.assertEquals(expected, actual);
  }
}
