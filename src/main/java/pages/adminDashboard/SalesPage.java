package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class SalesPage extends MethodHandles {
    public SalesPage(WebDriver driver) {
        super(driver);
    }

    private final By resetButton = By.cssSelector("a[class='btn btn-secondary']");
    private final By searchButton = By.id("search-orders");
    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By ViewButton = By.xpath("//tbody/tr[1]/td[15]/a[1]");
    private final By phoneNumberField = By.id("BillingPhone");
    private final By orderIDField = By.id("GoDirectlyToCustomOrderNumber");
    private final By goToOrderButton = By.id("go-to-order-by-number");
    private final By emptyTable = By.cssSelector(".dataTables_empty");


    public void resestFilter() {
        visiblityOfElement(resetButton, 50);
        click(resetButton, 30);
        invisibilityOf(loadingIcon, 30);
    }


    public void searchByOrderID(String orderID) {
        clear(phoneNumberField, 30);
        clear(orderIDField , 30);
        sendKeys(orderIDField , orderID , 30);
        click(searchButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void clickSearchButton() {
        clear(phoneNumberField, 30);
        sendKeys(phoneNumberField, "01271331374", 30);
        click(searchButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void clickResetAndSearchButton() {
        click(searchButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public OrderDetailsPage openFirstOrder() {
        click(ViewButton, 30);
        invisibilityOf(loadingIcon, 30);
        return new OrderDetailsPage(driver);
    }

    public boolean tableIsEmpty() {
        return isDisplayed(emptyTable , 30);
    }


}
