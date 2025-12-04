import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class testtt extends BaseTests {

    @Test(priority = 1)
    public void createCashOrderAsDelivered() throws InterruptedException, FileNotFoundException {
        method.login();
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

    }


//    @Test
//    public void getMerchantNumberLoop() throws FileNotFoundException, InterruptedException, FileNotFoundException {
//
//        var login = homePage.clickLoginIcon();
//        sleepPerSeconds(1);
//        login.writePhoneNumber(dataModel().liveData.livephone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().liveData.livepassword);
//        var homePage = login.clickLogin();
//        int index = 0;
//
//        while (index < ID.size()) {
//            var test = homePage.goToTestPage();
//            test.openOrderDetailsPage(ID.get(index));
//            System.out.println(ID.get(index)+ " --> " +test.getMerchantNumber());
//            index = index + 1  ;
//
//        }
//
//
//    }
//
//    List<Integer> ID = Arrays.asList(1414986);


    @Test
    public void testAlert() throws FileNotFoundException {
        method.login();
        var admin =homePage.openAdmin();
        var productPage = admin.openProductPage();
        productPage.openProductDetailsByID(2807);
        refreshPage();
        productPage.getStocks();
        System.out.println(productPage.getStock());




    }

}



