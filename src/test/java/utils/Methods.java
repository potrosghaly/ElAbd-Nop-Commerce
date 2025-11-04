package utils;

import base.BaseTests;
import org.testng.Assert;
import pages.HomePage;
import pages.users.PaymobPage;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class Methods extends BaseTests {

    private HomePage homePage;


    public Methods(HomePage homePage) {
        this.homePage = homePage;
    }

    public HomePage login() throws FileNotFoundException {
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        return login.clickLogin();
    }

    public void completeSuccessOnlinePayment(PaymobPage payForm) throws FileNotFoundException {
        payForm.fillPaymobForm(dataModel().paymobForm.numberCard, dataModel().paymobForm.expiryCard, dataModel().paymobForm.cvcCard,
                dataModel().paymobForm.nameCard);
        payForm.clickPayButton();
        payForm.clicksumbitButton();
        Assert.assertTrue(payForm.thankYouMessage());
    }
    public void completeFailedOnlinePayment (PaymobPage payForm) throws FileNotFoundException {
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

}