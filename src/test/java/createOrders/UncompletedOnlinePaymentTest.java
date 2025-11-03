package createOrders;

import base.BaseTests;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class UncompletedOnlinePaymentTest extends BaseTests {
    @Test
    public void createUnpaidOrder() throws InterruptedException, FileNotFoundException {
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectOnlineOption();
        var payForm = checkout.submitOnlineOrder();
        payForm.fillPaymobForm(dataModel().paymobForm.numberCard, dataModel().paymobForm.expiryCard, dataModel().paymobForm.cvcCard,
                dataModel().paymobForm.nameCard);
    }


//    @Test(priority = 2)
//    public void checkUnpaidStatusIsPending() throws InterruptedException {
//        var admin = homePage.openAdmin();
//        var salePage = admin.openSalesPage();
//        salePage.resestFilter();
//        salePage.clickSearchButton();
//        var productPage = salePage.openFirstOrder();
//        Assert.assertTrue(productPage.pendingStatusIsAppear());
//    }
//
//    @Test(priority = 3)
//    public void tryToChangeStatusForUnpaidOrder() throws InterruptedException {
//        var admin = homePage.openAdmin();
//        var salePage = admin.openSalesPage();
//        salePage.resestFilter();
//        salePage.clickSearchButton();
//        var productPage = salePage.openFirstOrder();
//        productPage.clickPreparingButton();
//        Assert.assertTrue(productPage.unchangeAlertIsAppear());
//    }


//    @Test(priority = 4)
//    public void checkUnpaidStatusAfter7min() throws InterruptedException {
//        var admin = homePage.openAdmin();
//        var salePage = admin.openSalesPage();
//        sleepPerMinutes(7);
//        salePage.resestFilter();
//        salePage.clickSearchButton();
//        var productPage = salePage.openFirstOrder();
//        Assert.assertTrue(productPage.cancelStatusIsAppear());
//        Assert.assertTrue(productPage.autoCancelReasonIsAppear());
//    }

}
