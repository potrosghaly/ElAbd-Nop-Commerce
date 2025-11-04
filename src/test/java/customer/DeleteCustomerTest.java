package customer;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static reader.ReadDataFromJson.dataModel;

public class DeleteCustomerTest extends BaseTests {


    @Test
    public void tryToDeleteCutomerProfile() throws FileNotFoundException {
        method.login();
        var admin = homePage.openAdmin();

        var customer = admin.openCustomerPage();
        customer.searchByCustomerPhone(dataModel().customerPhone);
        if (!customer.customerIsExisting()) {
            var customerProfile = customer.deleteCustomer();
            customerProfile.clickDeleteButton();
            Assert.assertTrue(customerProfile.successAlertIsAppear());
        } else {
            System.out.println("The user doen't existion");
        }
    }
}
