package stocks;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static reader.ReadDataFromJson.dataModel;

public class TestStockOnlineOrderCanceled extends BaseTests {
    @Test
    public void testStockAfterCanceledOnlineOrder() throws Exception {

        method.login();
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

//        login = homePage.clickLoginIcon();
//        login.writePhoneNumber(dataModel().phone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().password);
//        homePage = login.clickLogin();

        homePage.searchByProductName(productName);
        // create order

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
        // close new tab
        closeTab();
        // back to product tab
        backToTab(productTab);
        refreshPage();
        product.getStocks();
        int newStock = product.getStock();
        int newReserved = product.getReserved();

        System.out.println("old StocK = " + oldStock + " -->" + "New StocK = " +newStock);
        System.out.println("old Reserved = " +oldReserved + " -->" +"new Reserved = " +  newReserved);


        int newRes = newReserved - 1;
        Assert.assertEquals(oldStock ,newStock);
        Assert.assertEquals(oldReserved ,newRes);

        openNewTab();
        goHomePage();

        admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.clickSearchButton();
        var productPage = salePage.openFirstOrder();
        productPage.clickCancelButton();

        // close new tab
        closeTab();
        // back to product tab
        backToTab(productTab);
        refreshPage();
        product.getStocks();
        newStock = product.getStock();
        newReserved = product.getReserved();
        System.out.println("after order cancel");
        System.out.println("old StocK = " + oldStock + "-->" + "New StocK = " +newStock);
        System.out.println("old Reserved = " +oldReserved + "-->" +"new Reserved = " +  newReserved);

        Assert.assertEquals(oldStock ,newStock);
        Assert.assertEquals(oldReserved ,newReserved );
    }

}
