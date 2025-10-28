package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class CreateOnlinePaymentTest extends BaseTests {

    String merchantNumber = "";


    @Test(priority = 1)
    public void createOnlineOrderAsDelivered() throws InterruptedException, FileNotFoundException {
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
        payForm.clickPayButton();
        payForm.clicksumbitButton();
        Assert.assertTrue(payForm.thankYouMessage());

        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
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
        Assert.assertEquals(paymob.getOrderPaidStatus() , "PAID");
        Assert.assertEquals(paymob.getTransactionType() , "CAPTURE");

    }

    @Test(priority = 2)
    public void createOnlineOrderAsCancel() throws FileNotFoundException {
//        var login = homePage.clickLoginIcon();
//        login.writePhoneNumber(dataModel().phone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().password);
//        var homePage = login.clickLogin();

        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectOnlineOption();
        var payForm = checkout.submitOnlineOrder();
        payForm.fillPaymobForm(dataModel().paymobForm.numberCard, dataModel().paymobForm.expiryCard, dataModel().paymobForm.cvcCard,
                dataModel().paymobForm.nameCard);
        payForm.clickPayButton();
        payForm.clicksumbitButton();
        Assert.assertTrue(payForm.thankYouMessage());

        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.authorizedStatusIsAppear());
        productPage.clickCancelButton();
        Assert.assertTrue(productPage.cancelStatusIsAppear());


        merchantNumber = productPage.getMerchantNumber();
//        var paymob = productPage.openpaymobPage();
//        //paymob.clickRestButton();
//        paymob.filterByMerchantNumber(merchantNumber);
//        paymob.clickSearchButton();
//        Assert.assertEquals(paymob.getOrderPaidStatus() , "PAID");
//        Assert.assertEquals(paymob.getTransactionType() , "VOID");

    }


}
