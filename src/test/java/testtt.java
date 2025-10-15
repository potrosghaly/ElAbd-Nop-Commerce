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
            1410048,
            1410074,
            1410128,
            1410141,
            1410180,
            1410216,
            1410227,
            1410244,
            1410273,
            1410392,
            1410572,
            1410574,
            1410653,
            1410697,
            1410727,
            1410736,
            1410742,
            1410744,
            1410785,
            1410786,
            1410788,
            1410801,
            1410851,
            1410888,
            1410908,
            1410910,
            1410913,
            1410981,
            1411015,
            1411187,
            1411218,
            1411334,
            1411337,
            1411484,
            1411697,
            1411741,
            1411751,
            1411754,
            1411759,
            1411858,
            1411872,
            1411895,
            1411918,
            1411932,
            1411940,
            1412061,
            1412075,
            1412118,
            1412145,
            1412175,
            1412225,
            1412232,
            1412269,
            1412284,
            1412388,
            1412490,
            1412501,
            1412538,
            1412539,
            1412559,
            1412584,
            1412595,
            1412613,
            1412617,
            1412621,
            1412629,
            1412634,
            1412681,
            1412682,
            1412715,
            1412771,
            1412806,
            1412832,
            1412875,
            1413016,
            1413135,
            1413151,
            1413168,
            1413255,
            1413419,
            1413469,
            1413507,
            1413511,
            1413560,
            1413589,
            1413593,
            1413602,
            1413611,
            1413618,
            1413634,
            1413646,
            1413698,
            1413699,
            1413805,
            1413871,
            1413872,
            1413877,
            1413887,
            1413893,
            1413897,
            1413899,
            1413953,
            1413973,
            1413996,
            1414006,
            1414028,
            1414080,
            1414082,
            1414106,
            1414143,
            1414210,
            1414325,
            1414382,
            1414416,
            1414480,
            1414508,
            1414521,
            1414604,
            1414606,
            1414853,
            1414986
    );


}



