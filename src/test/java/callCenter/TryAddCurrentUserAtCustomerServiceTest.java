package callCenter;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class TryAddCurrentUserAtCustomerServiceTest extends BaseTests {
    @Test
    public void addCurrentUserAtCustomerService() throws FileNotFoundException {
        var callCenterData = dataModel().callCenterUserForm;

        var login = homePage.clickLoginIcon();
        login.writePhoneNumber(dataModel().phone);
        login.clickSendOtpButton();
        login.writePassword(dataModel().password);
        var homePage = login.clickLogin();
        var admin = homePage.openAdmin();

        // open call center and create order
        var callCenter = admin.openCallCenterPage();
        callCenter.addNewUser(callCenterData.phoneNumber, callCenterData.name, callCenterData.address,
                callCenterData.build, callCenterData.floor, callCenterData.appartment);
        callCenter.clickSaveFormButton();
        Assert.assertTrue(callCenter.existingAlertIsAppear());
    }

}
