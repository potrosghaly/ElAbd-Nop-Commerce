package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class PaymobDashboardPage extends MethodHandles {
    public PaymobDashboardPage(WebDriver driver) {
        super(driver);
    }

    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By resetButton = By.cssSelector("a[class='btn btn-secondary']");
    private final By merchantNumberField = By.id("MerchantReferenceNumber");
    private final By searchButton = By.id("search-paymobtransaction");
    private final By paidCell = By.cssSelector("td:nth-child(3)");
    private final By orderStatusColumn = By.cssSelector("td:nth-child(3)");
    private final By transactionTypeColumn = By.cssSelector("td:nth-child(5)");


    public void clickRestButton() {
        click(resetButton, 30);
    }

    public void filterByMerchantNumber(String merchantNumber) {
        sendKeys(merchantNumberField, merchantNumber, 20);
    }

    public void clickSearchButton() {
        click(searchButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public String getOrderPaidStatus() {
        return getText(orderStatusColumn, 20);
    }

    public String getTransactionType() {
        return getText(transactionTypeColumn, 20);
    }


}
