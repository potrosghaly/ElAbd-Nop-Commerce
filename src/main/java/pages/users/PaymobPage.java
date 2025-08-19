package pages.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private final By paymobfram  = By.id("iframe");
    private final By welcome  = By.id("welcome");
    private final By submitButton = By.id("acssubmit");
    private final By successMessage = By.cssSelector("div[class='title'] h1 strong");



    public void fillPaymobForm(String number , String expiry , String cvc , String name)
    {
        sendKeys(cardNumber , number , 50);
        sendKeys(cardExpiry , expiry , 10);
        sendKeys(cardCvc , cvc , 10);
        sendKeys(cardName , name , 10);
    }
    public void clickPayButton()
    {
        click(payButton , 5);
    }
    public void clicksumbitButton()
    {
        visiblityOfElement(paymobfram , 20);
        switchToFrame("iframe");
        visiblityOfElement(welcome , 20);
        click(submitButton , 30);
        switchToParent();
        waitForUrlcontain("/checkout/completed/", 30);
    }
    public boolean thankYouMessage()
    {
        return isDisplayed(successMessage, 30);
    }




}
