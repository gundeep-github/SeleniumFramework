package com.factory.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tests.support.TestBaseUI;

public class SignUpPage extends TestBaseUI
{
   
    public WebDriverWait wait;
    public SignUpPage()
    {
        this.wait = new WebDriverWait(driver,5);
    }
    /*
    WebDriver driver;
    
    public SignUpPage(WebDriver driver)
    {
        this.driver=driver;
    }
    /* Contains options present on Sign Up Page
     * 1. Enter Email Address
     * 2. Fields which needs to be filled to create a user in the system
     * Note: There could have many other functions to verify the current page
     *       but implementation has been done keeping in mind the Hello Fresh Challenge
     *       Questions and its validations
     */
    public static final String DATASEEDING_DATA_FILENAME ="SignUpPage";
    @FindBy(how=How.ID,using="email_create")
    WebElement create_email;
    
    @FindBy(how=How.ID,using="SubmitCreate")
    WebElement click_on_SubmitCreate;
    
   // WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
    //assertEquals(heading.getText(), "MY ACCOUNT");
    //assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
    //assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
    //assertTrue(driver.findElement(By.className("logout")).isDisplayed());
    //assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
    
 
    @FindBy(how=How.ID,using="id_gender2")
    @CacheLookup
    WebElement gender_select;
    
    @FindBy(how=How.ID,using="customer_firstname")
    @CacheLookup
    WebElement firstName;
    
    @FindBy(how=How.ID,using="customer_lastname")
    @CacheLookup
    WebElement lastName;
    
    @FindBy(how=How.ID,using="passwd")
    @CacheLookup
    WebElement signupPassword;
    
    @FindBy(how=How.ID,using="days")
    @CacheLookup
    WebElement birthDay;
    
    @FindBy(how=How.ID,using="months")
    @CacheLookup
    WebElement birthMonth;
    
    @FindBy(how=How.ID,using="years")
    @CacheLookup
    WebElement birthYear;
    
    @FindBy(how=How.ID, using="company")
    @CacheLookup
    WebElement company;
    
    @FindBy(how=How.ID,using="address1")
    @CacheLookup
    WebElement address1;
    
    @FindBy(how=How.ID,using="address2")
    @CacheLookup
    WebElement address2;
    
    @FindBy(how=How.ID,using="city")
    @CacheLookup
    WebElement city;
    
    @FindBy(how=How.ID,using="id_state")
    @CacheLookup
    WebElement state;
    
    @FindBy(how=How.ID,using="other")
    @CacheLookup
    WebElement AdditionalInformation;
    
    @FindBy(how=How.ID,using="postcode")
    @CacheLookup
    WebElement postCode;
    
    @FindBy(how=How.ID,using="phone")
    @CacheLookup
    WebElement homePhone;
    
    @FindBy(how=How.ID,using="phone_mobile")
    @CacheLookup
    WebElement mobilePhone;
    
    @FindBy(how=How.ID,using="alias")
    @CacheLookup
    WebElement addressAlias;
    
    @FindBy(how=How.ID,using="submitAccount")
    @CacheLookup
    WebElement submitAccount;
    
    @FindBy(how=How.CSS,using="h1")
    WebElement headerverification;
    
    public void enterNewUserEmail(String randomemailincrement)
    {
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7)+randomemailincrement+ ".com";
        create_email.sendKeys(email);
    }
    
    public void submitemail()
    {
        click_on_SubmitCreate.click();
    }
    
    public void selectid()
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(gender_select)).click();
        }
        //wait.until(ExpectedConditions.visibilityOf(gender_select)).click();
        
        /*try {
            if(gender_select.isDisplayed())
            {
                gender_select.click();
            }
        }*/
            catch (NoSuchElementException ex) {
                

        }
    }
    
    public void enterFirstName(String customerfirstname)
    {
        firstName.sendKeys(customerfirstname);
    }
    
    public void enterLastName(String customerlastname)
    {
        lastName.sendKeys(customerlastname);
    }
    
    public void enterPassword(String randompasswordid)
    {
        String passkey = "Qwerty"+randompasswordid;
        signupPassword.sendKeys(passkey);
    }
    
    public void selectBirthDay(String day)
    {
        Select select = new Select(birthDay);
        select.selectByValue(day);
    }
    
    public void selectBirthMonth(String month)
    {
        Select select = new Select(birthMonth);
        select.selectByValue(month);
    }
    
    public void selectBirthYear(String year)
    {
        Select select = new Select(birthYear);
        select.selectByValue(year);
    }
    
    public void enterCompnay(String companyDetails)
    {
        company.sendKeys(companyDetails);
    }
    
    public void enterAddress1(String addresspoint1)
    {
        address1.sendKeys(addresspoint1);
    }
    
    public void enterAddress2(String addresspoint2)
    {
        address2.sendKeys(addresspoint2);
    }
    
    public void enterCity(String citydetails)
    {
        city.sendKeys(citydetails);
    }
    
    public void selectState(String statedetails)
    {
        Select select = new Select(state);
        select.selectByVisibleText(statedetails);
    }
    
    public void enterAdditionalInfo(String additionalinfo)
    {
        AdditionalInformation.sendKeys(additionalinfo);
    }
    
    public void enterPostCode(String postcode)
    {
        postCode.sendKeys(postcode);
    }
    
    public void enterhomePhone(String phone)
    {
        homePhone.sendKeys(phone);
    }
    
    public void enterMobilePhone(String mobile)
    {
        mobilePhone.sendKeys(mobile);
    }
    
    public void enterAddressAlias(String alias)
    {
        addressAlias.sendKeys(alias);
    }
    
    public void clickSubmit()
    {
        submitAccount.click();
    }        
        
}
