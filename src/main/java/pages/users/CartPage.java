package pages.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class CartPage extends MethodHandles {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutButton =By.id("checkout");
    private final By phoneNumberField = By.id("phoneNumber");


    public CheckoutPage openCheckoutPage()
    {
        click(checkoutButton , 10);
        waitForUrlcontain("/onepagecheckout", 30);
        return new CheckoutPage(driver);
    }

    public void tryOpenCheckoutToGuest()
    {
        click(checkoutButton , 10);
        waitForUrlcontain("/login?returnUrl=checkout" , 30);
    }
    public boolean loginPageIsOpen() {
        return isDisplayed(phoneNumberField, 30);
    }




}
