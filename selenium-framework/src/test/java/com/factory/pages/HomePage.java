package com.factory.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tests.support.TestBaseUI;

public class HomePage extends TestBaseUI
{
    public WebDriverWait wait;
    public HomePage()
    {
        this.wait = new WebDriverWait(driver,5);
    }
    //WebDriverWait wait;
    /* Contains options present on Home Page
     * 1. Sign In Button
     * 2. Header Verify
     * 3. Title Verify
     * Note: There could have many other functions to verify the current page
     *       but implementaion has been done keeping in mind the Hello Fresh Challenge
     *       Questions and its validations
     */
    
    public static final String DATASEEDING_DATA_FILENAME ="";
    @FindBy(how=How.CLASS_NAME,using="login")
    public WebElement signInButton;
    
    public WebElement getsigninButton()
    {
        return signInButton;
    }
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
        //wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
        //signInButton.click();
    }
}
