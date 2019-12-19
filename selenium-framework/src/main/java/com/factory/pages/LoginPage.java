package com.factory.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tests.support.TestBaseUI;

public class LoginPage extends TestBaseUI
{

    public WebDriverWait wait;
    public LoginPage()
    {
        this.wait = new WebDriverWait(driver,5);
    }
    //WebDriver driver;
    
    /*public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }*/

    /*
     String fullName = "Joe Black";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        driver.findElement(By.id("SubmitLogin")).click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        assertEquals("MY ACCOUNT", heading.getText());
        assertEquals(fullName, driver.findElement(By.className("account")).getText());
        assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        assertTrue(driver.findElement(By.className("logout")).isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
     */
    public static final String DATASEEDING_DATA_FILENAME ="LoginPage";
    @FindBy(how=How.CLASS_NAME,using="login")
    WebElement signInButton;
    
    
    
    @FindBy(how=How.ID,using="email")
    @CacheLookup
    WebElement enterexistingemail;
    
    @FindBy(how=How.ID,using="passwd")
    @CacheLookup
    WebElement enterexistingpassword;
    
    @FindBy(how=How.ID,using="SubmitLogin")
    @CacheLookup
    WebElement clickonLogin;
    
    @FindBy(how=How.CSS,using="h1")
    WebElement headerverification;
    
    @FindBy(how=How.CLASS_NAME,using="account")
    public WebElement usernameverfication;
    
    public void click_on_SignIn()
    {
        try {
            if(signInButton.isDisplayed())
            {
                signInButton.click();
            }
        }
            catch (NoSuchElementException ex) {
                
        }
    }
    
    public void enterexistingemail(String validemail)
    {
        enterexistingemail.sendKeys(validemail);
    }
    
    public void enterexistingpassword(String validpassword)
    {
        enterexistingpassword.sendKeys(validpassword);
    }
    
    
    
    public void logintoSite()
    {
        clickonLogin.click();
    }
    
}
