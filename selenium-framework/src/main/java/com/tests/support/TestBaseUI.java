package com.tests.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.tests.constant.Constant;

public class TestBaseUI {

	protected final static Logger logger = Logger.getLogger(TestBaseUI.class);
	public static WebDriver driver;
	public WebDriverWait wait;
	public String dataFolderDP = "";
	protected static Properties prop;

	public TestBaseUI() {
		try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

			prop = new Properties();
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@BeforeClass
	public void setupApplication() {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			logger.info("====Browser Session Started in Chrome====");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("BASEURI"));
			logger.info("=====Application Started=====");
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			logger.info("====Browser Session Started in Chrome====");
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("BASEURI"));
			logger.info("=====Application Started=====");
		}

	}

	public String getDataPath(String dataseedingSchemaDataFileName, String dataFolder) {
		String absolutePath = Constant.DATAFILE_BASE_PATH.getValue() + Constant.FORWARD_SLASH.getValue() + dataFolder
				+ Constant.FORWARD_SLASH.getValue() + dataseedingSchemaDataFileName + Constant.FORWARD_SLASH.getValue()
				+ dataseedingSchemaDataFileName + Constant.CSV.getValue();
		return absolutePath;
	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
	    if (testResult.getStatus() == ITestResult.FAILURE) { 
	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	        FileUtils.copyFile(scrFile, new File("./failedScreenshots/" + testResult.getName() + "-"
	                + Arrays.toString(testResult.getParameters()) +  ".jpg"));
	    } 
	}

	@AfterClass
	public void closeApplication() {
		driver.quit();
		logger.info("=====Browser Session End=====");

	}
}
