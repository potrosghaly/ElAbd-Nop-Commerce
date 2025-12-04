package stocks;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStockValuePacksAfterCancelled extends BaseTests {

    String mainProduct = "عبوه اقتصادية 3 قطعه مخبوزات اونلاين";
    String attributeProduct = "دانش فواكه";


    @Test
    public void testStockFromValuePackAfterOrderDeliverd() throws Exception {
        method.login();
        homePage.checkLocation();

        var admin = homePage.openAdmin();
        var product = admin.openProductPage();
        product.openProductDetailsByID(3104);
        product.checkStock();
        int oldStockMain = product.getStock();
        int oldReservedMain = product.getReserved();
        System.out.println("main product" + oldStockMain + "  " +oldReservedMain);

        String mainProductTab = saveTab();
        // open new tab and go to home page
        openNewTab();
        goHomePage();

        // ckeck Attribute Stock
        admin = homePage.openAdmin();
        product = admin.openProductPage();
        product.openProductDetailsByID(2807);
        product.checkStock();
        int oldStockValue = product.getStock();
        int oldReservedValue = product.getReserved();

        System.out.println("value product" + oldStockValue + "  " +oldReservedValue);

        String valueProductTab = saveTab();
        // open new tab and go to home page
        openNewTab(2);
        goHomePage();

        // create order with value pack product
        var value = homePage.openValuePackPage();
        value.addProductToCart();
        value.addAttribute("3");
        String expectAlertText = "The product has been added to your";
        String actualAlertText = value.getAlertText();
        Assert.assertTrue(actualAlertText.contains(expectAlertText));

        var cartPage = homePage.openCartPage();
        var checkout = cartPage.openCheckoutPage();
        checkout.selectCashOption();
        checkout.submitCashOrder();
        Assert.assertTrue(checkout.thankYouMessage());
        String orderID = method.extractOrderIDFromURL(driver.getCurrentUrl());
        // close new tab
        closeTab();

        // back to product tab
        backToTab(valueProductTab);
        refreshPage();


        // for value product
        product.getStocks();
        int newStockvalue = product.getStock();
        int newReservedvalue = product.getReserved();

        System.out.println("for Value old StoK = " + oldStockValue + " -->" + "New StocK = " + newStockvalue);
        System.out.println("for Value old Reserved = " + oldReservedValue + " -->" + "new Reserved = " + newReservedvalue);

        int newRes = newReservedvalue - 3;
        Assert.assertEquals(oldStockValue, newStockvalue);
        Assert.assertEquals(oldReservedValue, newRes);


        backToTab(mainProductTab);
        // for main product
        product.getStocks();
        int newStockMain = product.getStock();
        int newReservedMain = product.getReserved();

        System.out.println("for Main old StocK = " + oldStockMain + " -->" + "New StocK = " + newStockMain);
        System.out.println("for Main old Reserved = " + oldReservedMain + " -->" + "new Reserved = " + newReservedMain);

        Assert.assertEquals(oldStockMain, newStockMain);
        Assert.assertEquals(oldReservedMain, newReservedMain);

        backToTab(mainProductTab);
        openNewTab(2);
        goHomePage();
        admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        var productPage = salePage.searchByOrderID(orderID);
        productPage.clickCancelButton();

        closeTab();

        backToTab(valueProductTab);
        refreshPage();


        // for value product
        product.getStocks();
        newStockvalue = product.getStock();
        newReservedvalue = product.getReserved();
        System.out.println("After the order is Cancel");
        System.out.println("for Value old StoK = " + oldStockValue + " -->" + "New StocK = " + newStockvalue);
        System.out.println("for Value old Reserved = " + oldReservedValue + " -->" + "new Reserved = " + newReservedvalue);


        Assert.assertEquals(oldStockValue, newStockvalue);
        Assert.assertEquals(oldReservedValue, newReservedvalue);


        backToTab(mainProductTab);
        // for main product
        product.getStocks();
        newStockMain = product.getStock();
        newReservedMain = product.getReserved();

        System.out.println("for Main old StocK = " + oldStockMain + " -->" + "New StocK = " + newStockMain);
        System.out.println("for Main old Reserved = " + oldReservedMain + " -->" + "new Reserved = " + newReservedMain);

        Assert.assertEquals(oldStockMain, newStockMain);
        Assert.assertEquals(oldReservedMain, newReservedMain);



    }

}
