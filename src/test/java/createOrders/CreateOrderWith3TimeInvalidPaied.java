package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class CreateOrderWith3TimeInvalidPaied extends BaseTests {

    @Test(priority = 1)
    public void createUnpaidOrder() throws InterruptedException, FileNotFoundException {
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectOnlineOption();
        var payForm = checkout.submitOnlineOrder();
      // first time
        payForm.fillPaymobForm (dataModel().paymobForm.numberCard ,dataModel().paymobForm.expiryCard ,dataModel().paymobForm.cvcCard,
                dataModel().paymobForm.nameCard);
        payForm.clickPayButton();
        payForm.selectAuthenticationInvalid();
        payForm.clicksumbitButton();
        payForm.clickTryAgain();
        // second time
        payForm.fillPaymobForm (dataModel().paymobForm.numberCard ,dataModel().paymobForm.expiryCard ,dataModel().paymobForm.cvcCard,
                dataModel().paymobForm.nameCard);
        payForm.clickPayButton();
        payForm.selectAuthenticationInvalid();
        payForm.clicksumbitButton();
        payForm.clickTryAgain();
        // third time
        payForm.fillPaymobForm (dataModel().paymobForm.numberCard ,dataModel().paymobForm.expiryCard ,dataModel().paymobForm.cvcCard,
                dataModel().paymobForm.nameCard);
        payForm.clickPayButton();
        payForm.selectAuthenticationInvalid();
        payForm.clicksumbitButton();
        Assert.assertTrue(payForm.faildMessage());


    }

   // @Test(priority = 2)
    public void checkUnpaidStatusAfter15min() throws InterruptedException {
        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.cancelStatusIsAppear());
    }

}
