package callCenter;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class CreateOrderByCallCenterTest extends BaseTests {

    @Test
    public void createCallCenterOrderAsDelivered() throws FileNotFoundException, InterruptedException {
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
        callCenter.submitOrder();
        //callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());

        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.pendingStatusIsAppear());
        productPage.clickPreparingButton();
        productPage.closeTab(2);
        productPage.clickOnWayButton();
        productPage.clickDeliveredButton();
        productPage.markOrderAsPaid();
        Assert.assertTrue(productPage.paidStatusIsAppear());
    }

    @Test(dependsOnMethods = "createCallCenterOrderAsDelivered")
    public void createCallCenterOrderAsCancel() throws FileNotFoundException {


        // open call center and create order
        var admin =homePage.openAdmin();
        var callCenter = admin.openCallCenterPage();
        callCenter.searchByPhoneNumber(dataModel().phone);
        callCenter.cleanCart();
        callCenter.selectCategory();
        callCenter.addProductToCart();
        callCenter.submitOrder();
        //callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());

        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.pendingStatusIsAppear());
        productPage.clickCancelButton();
        Assert.assertTrue(productPage.cancelStatusIsAppear());
    }

}
