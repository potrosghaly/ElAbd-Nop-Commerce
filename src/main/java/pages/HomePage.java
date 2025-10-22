package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.adminDashboard.AdminPage;
import pages.users.CartPage;
import pages.users.LoginPage;
import utils.MethodHandles;

import java.util.Objects;


public class HomePage extends MethodHandles {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Mega White Cheese Croissant 474 product
    String productID = "1";

    //bakery category
    String categorySort = "3";

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
    private final By adminLable = By.cssSelector(".admin-header-links");
    private final By openHomePage = By.cssSelector("img[alt='Your store name']");
    private final By searchIcon = By.xpath("//a[@id='btnMainSearch']//i[contains(@class,'fas fa-search')]");

    private final By submitSearchIcon = By.xpath("//button[@type='submit']//i[@class='fas fa-search']");
    private final By searchField = By.id("small-searchterms");


    public LoginPage clickLoginIcon() {
        visiblityOfElement(autoLocation, 30);
        click(loginIcon, 5);
        return new LoginPage(driver);
    }

    public LoginPage clickLoginIconWithoutLocation() {

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
        visiblityOfElement(categoryButton, 50);
        click(categoryButton, 30);
    }


    //logged in user
    private final By addToCartButtonLocator = By.cssSelector(".button-2.product-box-add-to-cart-button");

    //private final By productLocator = By.xpath("//div[@data-productid='" + productID + "']");
    private final By productLocator = By.xpath("//div[@data-productid] [1]");
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
        visiblityOfElement(cartIcon, 50);
        click(cartIcon, 5);
        visiblityOfElement(viewYourCart, 50);
        click(viewYourCart, 5);
        return new CartPage(driver);
    }

    public AdminPage openAdmin() {
        visiblityOfElement(adminPage, 50);
        click(adminPage, 5);
        while (!driver.findElements(loadingIcon).isEmpty()){
        invisibilityOf(loadingIcon , 30);}
        return new AdminPage(driver);
    }

    //    ------------------------------------------------------
//    as guest user
    private final By changeLocation = By.cssSelector(".change-location");
    private final By map = By.id("map");
    private final By continueAsGuestButton = By.id("general-popup-ok");
    private final By loginButtonFromPopup = By.id("general-popup-cancel");
    private final By phoneNumberField = By.id("phoneNumber");


    public void clickChangeLocation() {
        click(changeLocation, 30);
        visiblityOfElement(map, 30);
    }

    public void clickLogout() {
        visiblityOfElement(logoutIcon, 30);
        click(logoutIcon, 30);
        click(logoutButton, 30);
    }

    public boolean checkIfPopupAppear() {
        boolean popup = isDisplayed(continueAsGuestButton, 30) && isDisplayed(loginButtonFromPopup, 30);
        return popup;
    }

    public void clickContinueAsGuest() {
        click(continueAsGuestButton, 20);
    }

    public void clickLoginFromPopup() {
        click(loginButtonFromPopup, 20);

    }

    public boolean mapIsAppear() {
        return isDisplayed(map, 30);
    }

    public boolean loginPageIsAppear() {
        return isDisplayed(phoneNumberField, 30);
    }

    public void closePopup() {
        while (!driver.findElements(closePopup).isEmpty()) {
            click(closePopup, 30);
            invisibilityOf(closePopup, 30);
        }
    }

    public void searchByProductName(String productName) {
        visiblityOfElement(searchIcon, 30);
        click(searchIcon, 20);
        visiblityOfElement(searchField, 20);
        sendKeys(searchField, productName, 30);
        click(submitSearchIcon, 20);
    }

    //    ------------------------------------------------------ Change Location

    String hubName = "وسط البلد";
    private final By locationName = By.xpath("//p[@class='location-word']");
    private final By changeAddressIcon = By.cssSelector(".change-location");
    private static final By editAddressButton = By.cssSelector("body > div:nth-child(8) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)");
    private static final By markAsDefaultCheckbox = By.id("Address_IsDefaultAddress");
    private static final By saveButton = By.cssSelector("button[class='btn btn-primary save-address-button']");


    public void checkLocation() {
        visiblityOfElement(locationName, 20);
        String location = getText(locationName, 20);
        String actual = hubName;
        if (Objects.equals(location, actual))
            return;
        else {
            openChangeAddressPage();
            clickEditIcon();
        }
    }


    public void clickEditIcon() {
        visiblityOfElement(editAddressButton, 30);

        By addressItemLocator = By.xpath("//div[contains(@class, 'address-item') and .//strong[contains(text(),'" + hubName + "')]]");
        visiblityOfElement(addressItemLocator, 20);

        WebElement addressItem = driver.findElement(addressItemLocator);
        WebElement editButton = addressItem.findElement(By.xpath(".//button[contains(@class, 'edit-address-button')]"));

        editButton.click();
        setDefaultAddress();
    }

    public void openChangeAddressPage() {
        visiblityOfElement(changeAddressIcon, 30);
        click(changeAddressIcon, 30);
    }

    public void setDefaultAddress() {
        visiblityOfElement(markAsDefaultCheckbox, 50);
        click(markAsDefaultCheckbox, 20);
        click(saveButton, 20);
        visiblityOfElement(editAddressButton, 50);
        click(openHomePage, 30);
    }

    public TestPage goToTestPage() {
        return new TestPage(driver);
    }


}
