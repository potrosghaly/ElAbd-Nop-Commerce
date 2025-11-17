package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.MethodHandles;

public class CallCenterPage extends MethodHandles {
    public CallCenterPage(WebDriver driver) {
        super(driver);
    }


    String categoryId = "37";
    String productID = "108";


    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By searchByPhoneField = By.id("searchbyphonenumber");
    private final By searchButton = By.cssSelector("button[onclick='SearchACustomer()']");
    private final By categoryIcon = By.xpath("//*[@data-categoryid = '" + categoryId + "' ]");
    private final By productLocator = By.xpath("//div[@data-productid='" + productID + "']");
//    private final By productLocator = By.xpath("//div[@data-productid] [1]");
    private final By addToCartButtonLocator = By.xpath("//*[@class = 'btn btn-primary add-to-cart addToCart-submit-btn']");
    private final By sumbitOrderButton = By.id("submitOrderBtn");
    private final By updateCartButton = By.id("updateAllBtn");
    private final By deleteProductIcon = By.xpath("//button[@class='btn bg-transparent remove-product-cart-btn']");
    private final By successOrderAlert = By.xpath("//div[@class='alert alert-success alert-dismissable']");
    private final By orderTypeDropdown = By.id("orderSource");
    private final By addNewUser = By.cssSelector(".btn.btn-primary.add-new-customer-btn");

    //add new user form
    private final By addPhoneNumberField = By.id("Phonenumber");
    private final By addCustomerNameField = By.id("Name");
    private final By addAddressField = By.id("StreetName");
    private final By addBuildNumberField = By.id("BuildingNumber");
    private final By addFloorNumberField = By.id("FloorNumber");
    private final By addApartmentNumberField = By.id("ApartmentNumber");
    private final By hubDropdoun = By.id("WarehouseId");
    private final By saveButton = By.id("saveButton");
    private final By isAlreadyExisting = By.xpath("//li[contains(text(),'رقم الموبايل تم تسجيله من قبل')]");
    private final By searchAtMapField = By.xpath("//input[@class='pac-target-input custom-search-box']");


    public void searchByPhoneNumber(String phone) {
        sendKeys(searchByPhoneField, phone, 30);
        click(searchButton, 30);
        waitForUrlcontain("/Admin/CustomerService/MainPage?", 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void selectCategory() {
        click(categoryIcon, 30);
    }

    public void addProductToCart() {
        visiblityOfElement(productLocator, 30);
        WebElement productElement = driver.findElement(productLocator);
        WebElement addToCartButton = productElement.findElement(addToCartButtonLocator);
        addToCartButton.click();
        invisibilityOf(loadingIcon, 30);
    }

    public void submitOrder() {
        click(sumbitOrderButton, 5);
    }

    public boolean successOrderAlertIsAppear() {
        visiblityOfElement(successOrderAlert, 20);
        return isDisplayed(successOrderAlert, 30);
    }

    public void cleanCart() {
        while (!driver.findElements(deleteProductIcon).isEmpty()) {
            click(deleteProductIcon, 30);
            invisibilityOf(loadingIcon, 30);
        }
    }

    public void selectTalabatType() {
        selectByValue(orderTypeDropdown, 30, "Talabat");
        invisibilityOf(loadingIcon, 30);
    }

    public void addNewUser(String phoneNo, String name, String build, String floor, String apartment, String shoubra) {
        click(addNewUser, 30);
        sendKeys(addPhoneNumberField, phoneNo, 30);
        sendKeys(addCustomerNameField, name, 30);
        visiblityOfElement(searchAtMapField, 30);
        sendKeys(searchAtMapField, shoubra, 30);
        enter();
        elemnebtNotEmpty(addAddressField, 30);
        sendKeys(addBuildNumberField, build, 30);
        sendKeys(addFloorNumberField, floor, 30);
        sendKeys(addApartmentNumberField, apartment, 30);

    }

    public void clickSaveFormButton() {
        click(saveButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public boolean existingAlertIsAppear() {
        visiblityOfElement(isAlreadyExisting, 30);
        return isDisplayed(isAlreadyExisting, 30);
    }

    ;

    public void selectHub() {
        selectByValue(hubDropdoun, 30, "10");
    }

    public String getOrderID()
    {
        WebElement alert = driver.findElement(successOrderAlert);
        return alert.getText();
    }
}
