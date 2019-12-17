package com.tests.support;

import static com.tests.support.TestBaseUI.driver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
//import com.factory.pages.WebDriverWait;
import com.tests.constant.Constant;

public class TestBaseUI
{

    protected final static Logger logger = Logger.getLogger(TestBaseUI.class);
    public static WebDriver driver;
    public WebDriverWait wait;
    public String dataFolderDP = "";

    @BeforeClass
    @Parameters({ "Front_End_Server","browser" })
    public void setupApplication(String serverName, String browser)
    {
        if(browser.equalsIgnoreCase("chrome"))
        {
            logger.info("====Browser Session Started in Chrome====");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver1.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(serverName);
            logger.info("=====Application Started=====");
        }
        
        else if(browser.equalsIgnoreCase("firefox"))
        {
            logger.info("====Browser Session Started in Chrome====");
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(serverName);
            logger.info("=====Application Started=====");
        }
        
       // return driver;
        //Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
        
    }
    
    public String getDataPath(String dataseedingSchemaDataFileName, String dataFolder)
    {
        String absolutePath = Constant.DATAFILE_BASE_PATH.getValue() + Constant.FORWARD_SLASH.getValue() + dataFolder
            + Constant.FORWARD_SLASH.getValue() + dataseedingSchemaDataFileName + Constant.FORWARD_SLASH.getValue()
            + dataseedingSchemaDataFileName + Constant.CSV.getValue();
        return absolutePath;
    }

    @AfterClass
    public void closeApplication()
    {
        driver.quit();
        logger.info("=====Browser Session End=====");

    }
}
