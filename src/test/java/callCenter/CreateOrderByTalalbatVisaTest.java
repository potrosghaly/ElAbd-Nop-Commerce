package callCenter;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class CreateOrderByTalalbatVisaTest extends BaseTests {
    @Test
    public void creatTalabatOrderAsDelivered() throws FileNotFoundException {
        method.login();
        var admin = homePage.openAdmin();

        // open call center and create order
        var callCenter = admin.openCallCenterPage();
        callCenter.searchByPhoneNumber(dataModel().phone);
        callCenter.cleanCart();
        callCenter.selectCategory();
        callCenter.addProductToCart();
        callCenter.selectTalabatType();
        callCenter.submitOrder();
        //callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());
        String orderID = method.extractOrderIDFromText(callCenter.getOrderID());
        var salePage = admin.openSalesPage();
        var productPage = salePage.searchByOrderID(orderID);
        Assert.assertTrue(productPage.paidStatusIsAppear());
        productPage.clickPreparingButton();
        productPage.closeTab(2);
        productPage.clickOnWayButton();
        productPage.clickDeliveredButton();
        Assert.assertTrue(productPage.paidStatusIsAppear());
    }


    @Test (dependsOnMethods = "creatTalabatOrderAsDelivered")
    public void createOnlineOrderAsCancel() throws FileNotFoundException {

        var admin = homePage.openAdmin();
        var callCenter = admin.openCallCenterPage();
        callCenter.searchByPhoneNumber(dataModel().phone);
        callCenter.cleanCart();
        callCenter.selectCategory();
        callCenter.addProductToCart();
        callCenter.selectTalabatType();
        callCenter.submitOrder();
        //callCenter.cleanCart();
        Assert.assertTrue(callCenter.successOrderAlertIsAppear());
        String orderID = method.extractOrderIDFromText(callCenter.getOrderID());
        var salePage = admin.openSalesPage();
        var productPage = salePage.searchByOrderID(orderID);
        Assert.assertTrue(productPage.paidStatusIsAppear());
        productPage.clickCancelButton();
        Assert.assertTrue(productPage.cancelStatusIsAppear());

    }


}
