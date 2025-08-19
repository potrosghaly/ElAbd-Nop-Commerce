import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class testtt extends BaseTests {
    @Test
    public void test1() throws InterruptedException, FileNotFoundException {
//        var login = homePage.clickLoginIcon();
//        login.writePhoneNumber(dataModel().phone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().password);
//        var homePage = login.clickLogin();
//        var admin = homePage.openAdmin();
//
//        var customer = admin.openCustomerPage();
//        customer.searchByCustomerPhone(dataModel().customerPhone);

//        if (!customer.customerIsExisting()) {
//            var customerProfile = customer.deleteCustomer();
//            customerProfile.clickDeleteButton();
//            Assert.assertTrue(customerProfile.successAlertIsAppear());
//        }


    }



    // @Test
    public void test2() throws InterruptedException, FileNotFoundException {

        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        var admin = homePage.openAdmin();

        var callCenter = admin.openCallCenterPage();
        callCenter.searchByPhoneNumber(dataModel().phone);
        callCenter.selectCategory();
        callCenter.addProductToCart();


        Thread.sleep(3000);
    }
}
