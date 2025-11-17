package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CreateOrderWith3TimeInvalidPaied extends BaseTests {

    String  orderID = "";
    @Test
    public void createUnpaidOrder3Time() throws InterruptedException, FileNotFoundException {
        method.login();
        homePage.checkLocation();
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectOnlineOption();
        var payForm = checkout.submitOnlineOrder();
        method.completeFailedOnlinePayment(payForm);
        orderID = method.extractOrderIDFromURL(driver.getCurrentUrl());
        System.out.println(orderID);
    }

    @Test(dependsOnMethods = "createUnpaidOrder3Time")
    public void checkUnpaidStatusAfter15min() throws InterruptedException {
        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        salePage.resestFilter();
        salePage.searchByOrderID(driver.getCurrentUrl());
        Assert.assertTrue(salePage.tableIsEmpty());
    }

}
