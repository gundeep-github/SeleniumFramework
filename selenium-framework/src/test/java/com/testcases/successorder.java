package com.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.factory.pages.HomePage;
import com.tests.support.TestBaseUI;
import com.tests.utils.CSVReader;

public class successorder extends TestBaseUI
{/*
    public WebDriverWait wait;
    public successorder()
    {
        this.wait = new WebDriverWait(driver,5);
        
    }*/
    String existingUserEmail = "hf_challenge_123456@hf123456.com";
    String existingUserPassword = "12345678";

  @Test
    public void order1() throws Exception
    {
      
      HomePage home = PageFactory.initElements(driver, HomePage.class);
      Thread.sleep(4000);
      home.click_on_SignIn();
      driver.findElement(By.id("email")).sendKeys(existingUserEmail);
      driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
      driver.findElement(By.id("SubmitLogin")).click();
      Thread.sleep(4000);
      driver.findElement(By.linkText("Women")).click();;
      //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
      Thread.sleep(4000);
      driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
      System.out.println("==============");
      //System.out.println(text);
      Thread.sleep(4000);
      WebElement ss = driver.findElement(By.id("group_1"));
      Select s1 = new Select(ss);
      //s1.selectByValue("M");
      s1.selectByVisibleText("M");
      Thread.sleep(4000);
      driver.findElement(By.name("Orange")).click();
      String color = driver.findElement(By.name("Orange")).getText();
      System.out.println("colorrrrrrrrrrrr");
      System.out.println(color);
      Thread.sleep(4000);
      
      
      System.out.println("================");
      
      
      /*
      String text1 = driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).getTagName();
      System.out.println(text1);
      System.out.println("*********");
      String text3 = driver.findElement(By.xpath("//a[@title='Blouse']/ancestor::li")).getText();
      System.out.println("=============="+text3);
      */
        
    }
}
