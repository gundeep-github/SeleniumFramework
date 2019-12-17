package com.testcases;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.factory.pages.HomePage;
import com.factory.pages.LoginPage;
import com.factory.pages.SignUpPage;
import com.tests.constant.Constant;
import com.tests.support.TestBaseUI;
import com.tests.utils.CSVReader;
import com.tests.utils.UIHelper;

public class LoginTest extends TestBaseUI
{
    @BeforeMethod
    public void beforeMethod(Method method, ITestContext context)
    {
        Test test = method.getAnnotation(Test.class);
        context.setAttribute("TestName", test.testName());
    }
    
    @Test(testName = "Challenge-1", description = "To Login a existing User in the system", priority = 101)
    public void SignUpaNewUser(ITestContext context) throws Exception
    {
        int i = 1;

        // Step 2 : page factory
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        String absolutePathLP = getDataPath(loginPage.DATASEEDING_DATA_FILENAME,
            dataFolderDP);
        System.out.println("step 0" +absolutePathLP);
        int count0 = UIHelper.readLines(absolutePathLP);
        System.out.println("step 1 "+count0);
        if (count0 > 0)
        {
            List<Map<String, Object>> rows = CSVReader.readPayloadTemplate(absolutePathLP);
            System.out.println("payload content---------");
            System.out.println(rows);
            for (int row = 0; row < rows.size(); row++)
            {
                String key = rows.get(row).get(Constant.KEY.getValue()).toString();
                String validemail = rows.get(row).get(Constant.EMAIL.getValue()).toString();
                String validpassword = rows.get(row).get(Constant.PASSWORD.getValue()).toString();       
                if (!((key.contains((CharSequence) context.getAttribute("TestName")))))
                    continue;
                loginPage.click_on_SignIn();
                loginPage.enterexistingemail(validemail);
                loginPage.enterexistingpassword(validpassword);
                loginPage.logintoSite();

            }
        }
    }

}
