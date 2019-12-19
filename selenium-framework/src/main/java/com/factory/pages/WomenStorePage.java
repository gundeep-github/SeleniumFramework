package com.factory.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.tests.support.TestBaseUI;

public class WomenStorePage extends TestBaseUI
{
    public WebDriverWait wait;
    public WomenStorePage()
    {
        this.wait = new WebDriverWait(driver,5);
    }
    
    /*
    WebDriver driver;
    public WomenStorePage(WebDriver driver)
    {
        this.driver=driver;
    }
    */
    
    public static final String DATASEEDING_DATA_FILENAME ="WomenStorePage";

    @FindBy(how = How.LINK_TEXT, using = "Women")
    WebElement goToWomenSelection;
    
    @FindBy(how = How.XPATH, using = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
    WebElement fadedShortSleeve;
    
    @FindBy(how = How.XPATH, using = "//a[@title='Blouse']/ancestor::li")
    WebElement blouse;
    
    @FindBy(how=How.ID,using="group_1")
    WebElement size;
    
    @FindBy(how=How.NAME,using="Orange")
    WebElement orangeColor;
    
    @FindBy(how=How.NAME,using="Blue")
    WebElement blueColor;
    
    @FindBy(how=How.NAME,using="White")
    WebElement whiteColor;
    
    @FindBy(how=How.NAME,using="Black")
    WebElement blackColor;

    @FindBy(how = How.NAME, using = "Submit")
    WebElement clickOnSubmit;
    
    @FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
    WebElement checkoutButton;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
    WebElement proceedCheckoutButton;

    @FindBy(how = How.NAME, using = "processAddress")
    WebElement btn_process_address;
    
    @FindBy(how = How.ID, using = "uniform-cgv")
    WebElement rbn_accept_term;

    @FindBy(how = How.NAME, using = "processCarrier")
    WebElement btn_process_carrier;

    @FindBy(how = How.CLASS_NAME, using = "bankwire")
    WebElement payByBankwire;

    @FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
    WebElement btn_confirm_order;

    // verification

    @FindBy(how = How.CSS, using = "h1")
    WebElement lbl_heading;

    @FindBy(how = How.XPATH, using = "//li[@class='step_done step_done_last four']")
    WebElement lbl_shipping;

    @FindBy(how = How.XPATH, using = "//li[@id='step_end' and @class='step_current last']")
    WebElement lbl_payment;
    
    @FindBy(how = How.XPATH, using = "//*[@class='cheque-indent']/strong")
    WebElement verifyOrderComplete;
    
    
           
    public void clickonWomenoption()
    {
        goToWomenSelection.click();
    }
    
    public void clickonDressOption(String dressName)
    {
        
        if(fadedShortSleeve.getText().contains(dressName))
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", fadedShortSleeve); 
            fadedShortSleeve.click();
        }
        
        else if(blouse.getText().contains(dressName))
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", blouse); 
            blouse.click();
        }
        
        else
        {
            Reporter.log("Incorrect Dress Name");
        }
        
        
        //if(dressName.contains(s))
    }
    public void clickonfadedShortSleeve()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // WebDriverWait wait = new WebDriverWait(driver, 15);
        js.executeScript("arguments[0].scrollIntoView();", fadedShortSleeve); 
        fadedShortSleeve.click();
    }
    
    public void selectSize(String sizeDetails)
    {
        Select select = new Select(size);
        select.selectByVisibleText(sizeDetails);
    }
    
    public void selectColor(String color)
    {
        if(color.equalsIgnoreCase("orange"))
        {
            orangeColor.click();
        }
        
        else if (color.equalsIgnoreCase("blue"))
        {
            blueColor.click();
        }
        
        else if (color.equalsIgnoreCase("white"))
        {
            whiteColor.click();
        }
        
        else if (color.equalsIgnoreCase("black"))
        {
            blackColor.click();
        }
        
        else
        {
            Reporter.log("Choose Correct color");
        }
    }
    public void click_on_Submit()
    {
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView();", clickOnSubmit); 
        clickOnSubmit.click();
    }
    
    public void clickoncheckOutButton()
    {
        checkoutButton.click();
    }
    
    public void clickonproceedCheckoutButton()
    {
        proceedCheckoutButton.click();
    }
    
    public void clickonbtn_process_address()
    {
        btn_process_address.click();
    }
    
    public void clickonrbn_accept_term()
    {
        rbn_accept_term.click();
    }
    
    public void clickonbtn_process_carrier()
    {
        btn_process_carrier.click();
    }
    
    public void clickonpayByBankwire()
    {
        payByBankwire.click();
    }
    
    public void clickonbtn_confirm_order()
    {
        btn_confirm_order.click();
    }
    
}
