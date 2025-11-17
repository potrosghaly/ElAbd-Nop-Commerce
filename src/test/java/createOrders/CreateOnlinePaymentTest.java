package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CreateOnlinePaymentTest extends BaseTests {

    String merchantNumber = "";

    @Test
    public void createOnlineOrderAsDelivered() throws InterruptedException, FileNotFoundException {
        method.login();
        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectOnlineOption();
        var payForm = checkout.submitOnlineOrder();
        method.completeSuccessOnlinePayment(payForm);
        Assert.assertTrue(payForm.thankYouMessage());
        String orderID = method.extractOrderIDFromURL(driver.getCurrentUrl());
        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        var productPage = salePage.searchByOrderID(orderID);
        Assert.assertTrue(productPage.authorizedStatusIsAppear());
        productPage.clickPreparingButton();
        productPage.closeTab(2);
        productPage.clickOnWayButton();
        productPage.clickDeliveredButton();
        Assert.assertTrue(productPage.paidStatusIsAppear());

        merchantNumber = productPage.getMerchantNumber();
        var paymob = productPage.openpaymobPage();
        //paymob.clickRestButton();
        paymob.filterByMerchantNumber(merchantNumber);
        paymob.clickSearchButton();
        Assert.assertEquals(paymob.getOrderPaidStatus(), "PAID");
        Assert.assertEquals(paymob.getTransactionType(), "CAPTURE");

    }

    @Test(dependsOnMethods = "createOnlineOrderAsDelivered")
    public void createOnlineOrderAsCancel() throws FileNotFoundException {
//     method.login();
        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectOnlineOption();
        var payForm = checkout.submitOnlineOrder();
        method.completeSuccessOnlinePayment(payForm);
        Assert.assertTrue(payForm.thankYouMessage());
        String orderID = method.extractOrderIDFromURL(driver.getCurrentUrl());
        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        var productPage = salePage.searchByOrderID(orderID);
        Assert.assertTrue(productPage.authorizedStatusIsAppear());
        productPage.clickCancelButton();
        Assert.assertTrue(productPage.cancelStatusIsAppear());
        merchantNumber = productPage.getMerchantNumber();
        var paymob = productPage.openpaymobPage();
        //paymob.clickRestButton();
        paymob.filterByMerchantNumber(merchantNumber);
        paymob.clickSearchButton();
        Assert.assertEquals(paymob.getOrderPaidStatus(), "PAID");
        Assert.assertEquals(paymob.getTransactionType(), "VOID");

    }


}
