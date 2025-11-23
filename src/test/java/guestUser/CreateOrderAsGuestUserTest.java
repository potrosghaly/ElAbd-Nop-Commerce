package guestUser;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CreateOrderAsGuestUserTest extends BaseTests {
    @Test
    public void tryOpenCheckoutPageAsGuestUserWithValidLocation() throws FileNotFoundException, InterruptedException {
        homePage.clickChangeLocation();
        homePage.selectNewLocationAsGuest("وسط البلد، Bab Al Louq, Abdeen, Egypt");
        homePage.openCategoryPage();
        homePage.addProductToCartAsGuest();
        var cartPage = homePage.openCartPage();
        cartPage.tryOpenCheckoutToGuest();
        Assert.assertTrue(cartPage.loginPageIsOpen());
    }

    @Test
    public void tryOpenCheckoutPageAsGuestUserWithInvalidLocation() throws FileNotFoundException, InterruptedException {
        homePage.clickChangeLocation();
        homePage.selectNewLocationAsGuest("بشتيل، Bashtil, Ossim, Egypt");
        driver.navigate().refresh();
        homePage.openCategoryPage();
        homePage.addProductToCartAsGuest();
        Assert.assertTrue(homePage.errorBannerWillAppear());


    }




}
