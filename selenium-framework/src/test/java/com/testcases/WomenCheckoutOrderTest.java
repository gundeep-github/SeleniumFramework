package com.testcases;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.factory.pages.HomePage;
import com.factory.pages.LoginPage;
import com.factory.pages.WomenStorePage;
import com.tests.constant.Constant;
import com.tests.support.TestBaseUI;
import com.tests.utils.CSVReader;
import com.tests.utils.UIHelper;

public class WomenCheckoutOrderTest extends TestBaseUI
{
    @BeforeMethod
    public void beforeMethod(Method method, ITestContext context)
    {
        Test test = method.getAnnotation(Test.class);
        context.setAttribute("TestName", test.testName());
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        String absolutePathLP = getDataPath(loginPage.DATASEEDING_DATA_FILENAME, dataFolderDP);
        List<Map<String, Object>> loginPagerows = CSVReader.readResourceCSV(absolutePathLP);
        String validemail = loginPagerows.get(0).get(Constant.EMAIL.getValue()).toString();
        String validpassword = loginPagerows.get(0).get(Constant.PASSWORD.getValue()).toString();
        loginPage.click_on_SignIn();
        loginPage.enterexistingemail(validemail);
        loginPage.enterexistingpassword(validpassword);
        loginPage.logintoSite();

    }

    @Test(testName = "Challenge-1", description = "To Login a existing User in the system", priority = 101)
    public void SignUpaNewUser(ITestContext context) throws Exception
    {
        WomenStorePage womenStorePage = PageFactory.initElements(driver, WomenStorePage.class);
        String absolutePathWSP = getDataPath(womenStorePage.DATASEEDING_DATA_FILENAME, dataFolderDP);

        System.out.println("step 0" + absolutePathWSP);
        int count0 = readLines(absolutePathWSP);
        System.out.println("step 1 " + count0);
        if (count0 > 0)
        {
            List<Map<String, Object>> rows = CSVReader.readResourceCSV(absolutePathWSP);
            for (int row = 0; row < rows.size(); row++)
            {
                String key = rows.get(row).get(Constant.KEY.getValue()).toString();

                String dressName = rows.get(row).get(Constant.DRESS_NAME.getValue()).toString();
                String sizeDetails = rows.get(row).get(Constant.SIZE.getValue()).toString();
                String selectColor = rows.get(row).get(Constant.COLOR.getValue()).toString();
                if (!((key.contains((CharSequence) context.getAttribute("TestName"))))) continue;
                womenStorePage.clickonWomenoption();
                womenStorePage.clickonDressOption(dressName);
                womenStorePage.selectSize(sizeDetails);
                womenStorePage.selectColor(selectColor);
                womenStorePage.click_on_Submit();
                womenStorePage.clickoncheckOutButton();
                womenStorePage.clickonproceedCheckoutButton();
                womenStorePage.clickonbtn_process_address();
                womenStorePage.clickonrbn_accept_term();
                womenStorePage.clickonbtn_process_carrier();
                womenStorePage.clickonpayByBankwire();
                womenStorePage.clickonbtn_confirm_order();

                Assert.assertEquals("ORDER CONFIRMATION", womenStorePage.confirmHeader());
                Assert.assertTrue(womenStorePage.confirmShippingIsDisplayed());
                Assert.assertTrue(womenStorePage.confirmpaymentIsDisplayed());
                Assert.assertTrue(
                    womenStorePage.confirmOrderIsComplete().contains("Your order on My Store is complete."));
                Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));

            }
        }
    }

}
