package callCenter;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class CreateOrederWithNewCutomerTest extends BaseTests {

    @Test
    public void deleteTheUserIfExsiting() throws FileNotFoundException, InterruptedException {
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        var admin = homePage.openAdmin();

        var customer = admin.openCustomerPage();
        customer.searchByCustomerPhone(dataModel().customerPhone);
        if (!customer.customerIsExisting()) {
            var customerProfile = customer.deleteCustomer();
            customerProfile.clickDeleteButton();
            Assert.assertTrue(customerProfile.successAlertIsAppear());
        } else {
            System.out.println("The user doen't existion");
        }
    }

    @Test (dependsOnMethods = "deleteTheUserIfExsiting")
    public void createOrderToNewUser() throws FileNotFoundException {
        var callCenterData = dataModel().callCenterUserForm;

//        var login = homePage.clickLoginIcon();
//        login.writePhoneNumber(dataModel().phone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().password);
//        var homePage = login.clickLogin();

        var admin = homePage.openAdmin();
        var callCenter = admin.openCallCenterPage();
        callCenter.addNewUser(dataModel().customerPhone, callCenterData.name,
                callCenterData.build, callCenterData.floor, callCenterData.appartment , callCenterData.address);
        callCenter.clickSaveFormButton();
        callCenter.cleanCart();
        callCenter.selectCategory();
        callCenter.addProductToCart();
        callCenter.submitOrder();
        //callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());


        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickResetAndSearchButton();
        var productPage = salePage.openFirstOrder();
        productPage.clickPreparingButton();
        productPage.closeTab(2);
        productPage.clickOnWayButton();
        productPage.clickDeliveredButton();
        productPage.markOrderAsPaid();
        Assert.assertTrue(productPage.paidStatusIsAppear());
    }

}
