package com.testcases;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.factory.pages.HomePage;
import com.factory.pages.SignUpPage;
import com.tests.constant.Constant;
import com.tests.support.TestBaseUI;
import com.tests.utils.CSVReader;
import com.tests.utils.UIHelper;

public class SignUpTest extends TestBaseUI
{
    @BeforeMethod
    public void beforeMethod(Method method, ITestContext context)
    {
        Test test = method.getAnnotation(Test.class);
        context.setAttribute("TestName", test.testName());
    }
    
    @Test(testName = "Challenge-1", description = "To sign up a New User in the system", priority = 101)
    public void SignUpaNewUser(ITestContext context) throws Exception
    {
        int i = 1;

        // Step 2 : page factory
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        SignUpPage signUp = PageFactory.initElements(driver, SignUpPage.class);
        String absolutePathSU = getDataPath(signUp.DATASEEDING_DATA_FILENAME,
            dataFolderDP);
        System.out.println("step 0" +absolutePathSU);
        int count0 = UIHelper.readLines(absolutePathSU);
        System.out.println("step 1 "+count0);
        if (count0 > 0)
        {
            List<Map<String, Object>> rows = CSVReader.readPayloadTemplate(absolutePathSU);
            System.out.println("payload content---------");
            System.out.println(rows);
            for (int row = 0; row < rows.size(); row++)
            {
                String key = rows.get(row).get(Constant.KEY.getValue()).toString();
                String email = rows.get(row).get(Constant.EMAIL.getValue()).toString();
                String customerfirstname = rows.get(row).get(Constant.FIRSTNAME.getValue()).toString();
                String customerlastname = rows.get(row).get(Constant.LASTNAME.getValue()).toString();
                String randompasswordid = rows.get(row).get(Constant.PASSWORD.getValue()).toString();
                String day = rows.get(row).get(Constant.DAY.getValue()).toString();
                String month = rows.get(row).get(Constant.MONTH.getValue()).toString();
                String year = rows.get(row).get(Constant.YEAR.getValue()).toString();
                String companyDetails = rows.get(row).get(Constant.COMPANY.getValue()).toString();
                String addresspoint1 = rows.get(row).get(Constant.ADDRESS1.getValue()).toString();
                String addresspoint2 = rows.get(row).get(Constant.ADDRESS2.getValue()).toString();
                String citydetails = rows.get(row).get(Constant.CITY.getValue()).toString();
                String statedetails = rows.get(row).get(Constant.STATE.getValue()).toString();
                String additionalinfo = rows.get(row).get(Constant.ADDITIONAL_INFO.getValue()).toString();
                String postcode = rows.get(row).get(Constant.POSTCODE.getValue()).toString();
                String phone = rows.get(row).get(Constant.PHONE.getValue()).toString();
                String mobile = rows.get(row).get(Constant.MOBILE.getValue()).toString();
                String alias = rows.get(row).get(Constant.ALIAS.getValue()).toString();

                if (!((key.contains((CharSequence) context.getAttribute("TestName")))))
                    continue;
              // wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
                driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
                //Thread.sleep(4000);
                homePage.click_on_SignIn();
                Thread.sleep(4000);
                signUp.enterNewUserEmail(email);
                signUp.submitemail();
                Thread.sleep(5000);                
                signUp.selectid();
                signUp.enterFirstName(customerfirstname);
                signUp.enterLastName(customerlastname);
                signUp.enterPassword(randompasswordid);
                signUp.selectBirthDay(day);
                signUp.selectBirthMonth(month);
                signUp.selectBirthYear(year);
                signUp.enterCompnay(companyDetails);
                signUp.enterAddress1(addresspoint1);
                signUp.enterAddress2(addresspoint2);
                signUp.enterCity(citydetails);
                signUp.selectState(statedetails);
                signUp.enterAdditionalInfo(additionalinfo);
                signUp.enterPostCode(postcode);
                signUp.enterhomePhone(phone);
                signUp.enterMobilePhone(mobile);
                signUp.enterAddressAlias(alias);
                signUp.clickSubmit();
                Thread.sleep(4000);
                driver.findElement(By.className("logout")).click();
              
            }
        }
    }
}
