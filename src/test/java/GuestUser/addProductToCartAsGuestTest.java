package GuestUser;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class addProductToCartAsGuestTest extends BaseTests {

    @Test(priority = 1)
    public void tryAddToCartAsGuestThenOpenMap() throws FileNotFoundException {
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        homePage.clickLogout();
        homePage.openCategoryPage();
        homePage.addProductToCartAsGuest();
        Assert.assertTrue(homePage.checkIfPopupAppear());
        homePage.clickContinueAsGuest();
        Assert.assertTrue(homePage.mapIsAppear());
    }

    @Test(priority = 2)
    public void tryAddToCartAsGuestThenOpenlogin() throws FileNotFoundException, InterruptedException {
//        var login = homePage.clickLoginIcon();
//        login.writePhoneNumber(dataModel().phone);
//        login.clickSendOtpButton();
//        login.writePassword(dataModel().password);
//        var homePage = login.clickLogin();
//        homePage.clickLogout();
        homePage.openCategoryPage();
        homePage.addProductToCartAsGuest();
        Assert.assertTrue(homePage.checkIfPopupAppear());
        homePage.clickLoginFromPopup();
        Assert.assertTrue(homePage.loginPageIsAppear());
    }


    }
