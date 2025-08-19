package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateOrderAsGuestUserTest extends BaseTests {
    @Test
    public void tryOpenCheckoutPageAsGuestUser()
    {
        homePage.openCategoryPage();
        homePage.addProductToCart();
        var cartPage = homePage.openCartPage();
        cartPage.tryOpenCheckoutToGuest();
        Assert.assertTrue(cartPage.loginPageIsOpen());


    }
}
