package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.adminDashboard.AdminPage;
import pages.users.CartPage;
import pages.users.LoginPage;
import utils.MethodHandles;

public class HomePage extends MethodHandles {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Mega White Cheese Croissant 474 product
    String productID = "1";

    //bakery category
    String categorySort = "2";

    // before login
    private final By loginIcon = By.id("login-button");
    private final By logoutIcon = By.id("user-icon");
    private final By logoutButton = By.cssSelector(".btn.btn-primary-transparent.pt-0.logout-link-menu");
    private final By successSendMessage = By.cssSelector("div[class='otp-message-container validation-summary-errors'] ul li");
    private final By otpField = By.xpath("(//input[@type='text'])[1]");
    private final By loadingIcon = By.xpath("//div[@class='loading-image']");
    private final By categoryButton = By.xpath("//ul[@class='nav navbar-nav']/li[" + categorySort + "]/a");
    private final By adminPage = By.xpath("//*[@class='administration']");
    private final By location = By.xpath("//*[contains(text() , 'وسط البلد')]");
    private final By autoLocation = By.xpath("//*[contains(text() , 'Auto Location')]");
    private final By closePopup = By.xpath("//button[@class='popup-close']");



    public LoginPage clickLoginIcon() {
        visiblityOfElement(autoLocation , 30);
        click(loginIcon, 5);
        return new LoginPage(driver);
    }

    public void writeOtp(String otp) {
        sendKeys(otpField, otp, 5);
    }

    public boolean messageIsAppear() {
        return isDisplayed(successSendMessage, 5);
    }

    public void openCategoryPage() {
        click(categoryButton, 30);
    }


    //logged in user
    private final By addToCartButtonLocator = By.cssSelector(".button-2.product-box-add-to-cart-button");

    private final By productLocator = By.xpath("//div[@data-productid='" + productID + "']");
    private final By productLocatorAsGuest = By.xpath("//div[@data-productid] [1]");

    private final By cartIcon = By.id("flyout-cart");
    private final By viewYourCart = By.id("view-cart-button");


    public void addProductToCart() {

        visiblityOfElement(productLocator, 15);
        WebElement productElement = driver.findElement(productLocator);
        WebElement addToCartButton = productElement.findElement(addToCartButtonLocator);
        addToCartButton.click();
        invisibilityOf(loadingIcon, 10);
    }
public void addProductToCartAsGuest() {

        visiblityOfElement(productLocatorAsGuest, 15);
        WebElement productElement = driver.findElement(productLocatorAsGuest);
        WebElement addToCartButton = productElement.findElement(addToCartButtonLocator);
        addToCartButton.click();
        invisibilityOf(loadingIcon, 10);
    }

    public CartPage openCartPage() {
        click(cartIcon, 5);
        click(viewYourCart, 5);
        return new CartPage(driver);
    }

    public AdminPage openAdmin() {
        click(adminPage, 5);
        return new AdminPage(driver);
    }

//    ------------------------------------------------------
//    as guest user
    private final By changeLocation = By.cssSelector(".change-location");
    private final By map = By.id("map");
    private final By continueAsGuestButton = By.id("general-popup-ok");
    private final By loginButtonFromPopup = By.id("general-popup-cancel");
    private final By phoneNumberField = By.id("phoneNumber");



    public void clickChangeLocation ()
    {
        click(changeLocation, 30);
        visiblityOfElement(map , 30);
    }

    public void clickLogout ()
    {
        click(logoutIcon, 30);
        click(logoutButton , 30);
        visiblityOfElement(loginIcon , 30);
    }

    public boolean checkIfPopupAppear()
    {
        boolean popup = isDisplayed(continueAsGuestButton , 30) && isDisplayed(loginButtonFromPopup , 30);
        return popup;
    }

    public void clickContinueAsGuest()
    {
        click(continueAsGuestButton , 20);
    }
    public void clickLoginFromPopup()
    {
        click(loginButtonFromPopup , 20);

    }
    public boolean mapIsAppear()
    {
        return isDisplayed(map , 30);
    }

    public boolean loginPageIsAppear()
    {
        return isDisplayed(phoneNumberField , 30);
    }

    public void closePopup() {
        while (!driver.findElements(closePopup).isEmpty()) {
            click(closePopup, 30);
            invisibilityOf(closePopup, 30);
        }
    }








}
