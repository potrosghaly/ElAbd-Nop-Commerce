import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class testtt extends BaseTests {

    @Test(priority = 1)
    public void createCashOrderAsDelivered() throws InterruptedException, FileNotFoundException {
        method.login();
        var admin = homePage.openAdmin();
        var salePage = admin.openSalesPage();
        var productPage = salePage.openFirstOrder();
        Assert.assertTrue(productPage.pendingStatusIsAppear());
        productPage.clickPreparingButton();
        productPage.closeTab(2);
        productPage.clickOnWayButton();

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


}



