package utils;

import base.BaseTests;
import pages.HomePage;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class Methods extends BaseTests {

    private HomePage homePage;

    public Methods(HomePage homePage) {
        this.homePage = homePage;
    }

    public HomePage login() throws FileNotFoundException {
        if (homePage == null) {
            throw new RuntimeException("HomePage is not initialized. Please check your setup.");
        }
        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        return login.clickLogin();
    }
}