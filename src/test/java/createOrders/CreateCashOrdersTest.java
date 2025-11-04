package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CreateCashOrdersTest extends BaseTests {


    @Test
    public void createCashOrderAsDelivered() throws InterruptedException, FileNotFoundException {
        method.login();
        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectCashOption();
        checkout.submitCashOrder();
        Assert.assertTrue(checkout.thankYouMessage());

        // mark as paid and delivered
        var admin = homePage.openAdmin();
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

    @Test (dependsOnMethods = "createCashOrderAsDelivered")
    public void createCashOrderAsCancel() throws FileNotFoundException {
        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectCashOption();
        checkout.submitCashOrder();
        Assert.assertTrue(checkout.thankYouMessage());

        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.pendingStatusIsAppear());
        productPage.clickCancelButton();
        Assert.assertTrue(productPage.cancelStatusIsAppear());
    }

    //        var login = homePage.clickLoginIcon();
//        login.writePhoneNumber(dataModel().phone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().password);
//        var homePage = login.clickLogin();


}
