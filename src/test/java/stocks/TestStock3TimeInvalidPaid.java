package stocks;
import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static reader.ReadDataFromJson.dataModel;

public class TestStock3TimeInvalidPaid extends BaseTests {
    @Test
    public void testStock3TimeInvalidPaidOrder() throws Exception {

        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();


        homePage.checkLocation();
        var admin = homePage.openAdmin();
        var product = admin.openProductPage();
        product.resetFilter();
        product.selectHub();
        product.selectPublished();
        product.submitFilter();
        product.openProductDetailPage();
        product.checkStock();
        int oldStock = product.getStock();
        int oldReserved = product.getReserved();

        String productName = product.getProductName();
        // save old tab name
        String productTab = saveTab();
        // open new tab and go to home page
        openNewTab();
        goHomePage();

        homePage.searchByProductName(productName);
        // create order

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


        // close new tab
        closeTab();
        // back to product tab
        backToTab(productTab);
        sleepPerSeconds(10);
        refreshPage();
        product.getStocks();
        int newStock = product.getStock();
        int newReserved = product.getReserved();

        System.out.println("old StocK = " + oldStock + " --> " + "New StocK = " +newStock);
        System.out.println("old Reserved = " +oldReserved + " --> " +"new Reserved = " +  newReserved);

        Assert.assertEquals(oldStock ,newStock);
        Assert.assertEquals(oldReserved ,newReserved);
    }


}
