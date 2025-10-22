package createOrders;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateOrderAsGuestUserTest extends BaseTests {
    @Test
    public void tryOpenCheckoutPageAsGuestUser()
    {
        homePage.openCategoryPage();
        homePage.addProductToCartAsGuest();
        var cartPage = homePage.openCartPage();
        cartPage.tryOpenCheckoutToGuest();
        Assert.assertTrue(cartPage.loginPageIsOpen());
    }
}
