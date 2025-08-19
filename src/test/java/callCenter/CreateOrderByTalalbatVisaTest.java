package callCenter;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class CreateOrderByTalalbatVisaTest extends BaseTests {
    @Test(priority = 1)
    public void creatTalabatOrderAsDelivered() throws FileNotFoundException {
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        var admin = homePage.openAdmin();

        // open call center and create order
        var callCenter = admin.openCallCenterPage();
        callCenter.searchByPhoneNumber(dataModel().phone);
        callCenter.cleanCart();
        callCenter.selectCategory();
        callCenter.addProductToCart();
        callCenter.selectTalabatType();
        callCenter.submitOrder();
        callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());

        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.paidStatusIsAppear());
        productPage.clickPreparingButton();
        productPage.clickOnWayButton();
        productPage.clickDeliveredButton();
        Assert.assertTrue(productPage.paidStatusIsAppear());
    }


    @Test(priority = 2)
    public void createOnlineOrderAsCancel() throws FileNotFoundException {

        var admin = homePage.openAdmin();
        var callCenter = admin.openCallCenterPage();
        callCenter.searchByPhoneNumber(dataModel().phone);
        callCenter.cleanCart();
        callCenter.selectCategory();
        callCenter.addProductToCart();
        callCenter.selectTalabatType();
        callCenter.submitOrder();
        callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());

        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.paidStatusIsAppear());
        productPage.clickCancelButton();
        Assert.assertTrue(productPage.cancelStatusIsAppear());

    }


}
