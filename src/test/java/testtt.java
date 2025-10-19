import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static reader.ReadDataFromJson.dataModel;

public class testtt extends BaseTests {


    @Test
    public void testStock() throws Exception {

        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        //homePage.checkLocation();
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
        System.out.println(oldStock);
        System.out.println(oldReserved);
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
        checkout.selectCashOption();
        checkout.submitCashOrder();
        Assert.assertTrue(checkout.thankYouMessage());
        // close new tab
        closeTab();
        // back to product tab
        backToTab(productTab);
        refreshPage();
        product.getStocks();
        int newStock = product.getStock();
        int newReserved = product.getReserved();

        System.out.println(oldStock + "-->" + newStock);
        System.out.println(oldReserved + "-->" + newReserved);

        sleepPerSeconds(3);
    }


    @Test
    public void getMerchantNumberLoop() throws FileNotFoundException, InterruptedException, FileNotFoundException {

        var login = homePage.clickLoginIcon();
        sleepPerSeconds(1);
        login.writePhoneNumber(dataModel().liveData.livephone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().liveData.livepassword);
        var homePage = login.clickLogin();
        int index = 0;

        while (index < ID.size()) {
            var test = homePage.goToTestPage();
            test.openOrderDetailsPage(ID.get(index));
            System.out.println(ID.get(index)+ " --> " +test.getMerchantNumber());
            index = index + 1  ;

        }


    }

    List<Integer> ID = Arrays.asList(
            1414986
    );

    @Test
    public void getOrderIDTest()
    {

    }


}



