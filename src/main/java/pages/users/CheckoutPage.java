package pages.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.MethodHandles;

public class CheckoutPage extends MethodHandles {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By cashOnDelieveryOption = By.id("paymentmethod_0");
    private final By onlinePymentOption = By.id("paymentmethod_1");
    private final By confirmOrderButton = By.id("confirm-order-button");
    private final By successMessage = By.cssSelector("div[class='title'] h1 strong");
    private final By paymobField = By.id("number");


    public void selectCashOption() {
        click(cashOnDelieveryOption, 15);
    }

    public void selectOnlineOption() {
        click(onlinePymentOption, 5);
    }

    public void submitCashOrder() {
        click(confirmOrderButton, 5);
        visiblityOfElement(successMessage, 10);
    }

    public PaymobPage submitOnlineOrder() {
        click(confirmOrderButton, 5);
        visiblityOfElement(paymobField, 50);
        return new PaymobPage(driver);
    }

    public boolean thankYouMessage() {
        return isDisplayed(successMessage, 30);
    }







}
