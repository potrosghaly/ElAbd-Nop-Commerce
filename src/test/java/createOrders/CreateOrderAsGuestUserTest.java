package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CreateOrderAsGuestUserTest extends BaseTests {
    @Test
    public void tryOpenCheckoutPageAsGuestUser() throws FileNotFoundException, InterruptedException {
        homePage.clickChangeLocation();
        homePage.selectNewLocationAsGuest("وسط البلد، Bab Al Louq, Abdeen, Egypt");
        homePage.openCategoryPage();
        homePage.addProductToCartAsGuest();
        var cartPage = homePage.openCartPage();
        cartPage.tryOpenCheckoutToGuest();
        Assert.assertTrue(cartPage.loginPageIsOpen());
    }
}
