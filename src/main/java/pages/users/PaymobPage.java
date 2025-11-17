package pages.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class PaymobPage extends MethodHandles {
    public PaymobPage(WebDriver driver) {
        super(driver);
    }

    private final By cardNumber = By.id("number");
    private final By cardExpiry = By.id("expiry");
    private final By cardCvc = By.id("cvc");
    private final By cardName = By.id("name");
    private final By payButton = By.id("pay-button");
    private final By paymobfram = By.id("iframe");
    private final By welcome = By.id("welcome");
    private final By submitButton = By.id("acssubmit");
    private final By successMessage = By.cssSelector("div[class='title'] h1 strong");
    private final By authenticationDropdown = By.id("selectAuthResult");
    private final By tryAgainButton = By.tagName("button");
    private final By failMessage = By.cssSelector("div[class='section order-completed'] strong");




    public void fillPaymobForm(String number, String expiry, String cvc, String name) {
        visiblityOfElement(cardNumber , 30);
        sendKeys(cardNumber, number, 50);
        sendKeys(cardExpiry, expiry, 10);
        sendKeys(cardCvc, cvc, 10);
        sendKeys(cardName, name, 10);
    }

    public void clickPayButton() {
        click(payButton, 5);
    }

    public void clicksumbitButton() {
        visiblityOfElement(paymobfram, 20);
        switchToFrame("iframe");
        visiblityOfElement(welcome, 20);
        click(submitButton, 30);
        switchToParent();
    }

    public boolean thankYouMessage() {
        waitForUrlcontain("/checkout/completed/", 30);
        return isDisplayed(successMessage, 30);
    }
    public boolean faildMessage() {
        waitForUrlcontain("failed=True", 100);
        return isDisplayed(failMessage, 30);
    }

    public void selectAuthenticationInvalid()
    {
        visiblityOfElement(paymobfram, 20);
        switchToFrame("iframe");
        visiblityOfElement(authenticationDropdown , 30);
        selectByValue(authenticationDropdown , 30 , "UNAUTHENTICATED");
        switchToParent();

    }

    public void clickTryAgain()
    {
        visiblityOfElement(tryAgainButton , 30);
        click(tryAgainButton , 30);
    }


    public static class SearchPage extends MethodHandles {
        public SearchPage(WebDriver driver) {
            super(driver);

        }
    }


    public String extractOrderIDFromURL(String url) {
        // Define the regular expression pattern to match the number after "/completed/"
        Pattern pattern = Pattern.compile("/completed/(\\d+)");
        Matcher matcher = pattern.matcher(url);

        // Extract the order ID if found
        if (matcher.find()) {
            return matcher.group(1); // Group 1 contains the captured digits
        } else {
            throw new IllegalArgumentException("No order ID found in the URL.");
        }
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
