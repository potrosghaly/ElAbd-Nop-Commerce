package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class OrderDetailsPage extends MethodHandles {
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By preparingButton = By.id("StatusConfirm");
    private final By onWayButton = By.id("StatusShipped");
    private final By deliveredButton = By.id("StatusDelivered");
    private final By cancelButton = By.id("btnCancelOrder");
    private final By cancelReasonButton = By.id("cancellationReason_1");
    private final By confirmCancelButton = By.id("StatusCancel");
    private final By markAsPaidButton = By.id("markorderaspaid");
    private final By confirmMarkAsPaidButton = By.id("markorderaspaid-action-confirmation-submit-button");

    private final By paidStatus = By.xpath("//div[contains(text(),'Paid')]");
    private final By pendingStatus = By.xpath("//div[contains(text(),'Pending')]");
    private final By cancelStatus = By.xpath("//*[contains(text(),'Cancelled')]");
    private final By autoCancelReason = By.xpath("//div[@class='form-text-row'][normalize-space()='Auto-cancel due to payment failure or unpaid transaction.']");
    private final By authorizedStatus = By.xpath("//div[contains(text(),'Authorized')]");
    private final By unchangedAlertUnpaidOrder = By.cssSelector(".alert.alert-danger.alert-dismissable");
    private final By merchantReferenceNumber = By.xpath("//div[@id='order-info']//div[1]//div[1]//div[8]//div[2]");
    private final By paymobDashboardButton = By.xpath("//p[normalize-space()='Paymob Transactions']");


    public void clickPreparingButton() {
        click(preparingButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void clickOnWayButton() {
        click(onWayButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void clickDeliveredButton() {
        click(deliveredButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void clickCancelButton() {
        click(cancelButton, 30);
        visiblityOfElement(cancelReasonButton, 30);
        click(cancelReasonButton, 30);
        click(confirmCancelButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void markOrderAsPaid() {
        visiblityOfElement(markAsPaidButton , 30);
        click(markAsPaidButton, 30);
        click(confirmMarkAsPaidButton, 30);
    }

    public boolean pendingStatusIsAppear() {
        return isDisplayed(pendingStatus, 30);
    }

    public boolean paidStatusIsAppear() {
        return isDisplayed(paidStatus, 30);
    }

    public boolean cancelStatusIsAppear() {
        driver.navigate().refresh();
        return isDisplayed(cancelStatus, 100);
    }
    public boolean autoCancelReasonIsAppear() {
        return isDisplayed(autoCancelReason, 100);
    }
    public boolean authorizedStatusIsAppear() {
        return isDisplayed(authorizedStatus, 30);
    }
    public boolean unchangeAlertIsAppear() {
        return isDisplayed(unchangedAlertUnpaidOrder, 30);
    }
    public String getMerchantNumber() {
        return getText(merchantReferenceNumber, 30);
    }
    public PaymobDashboardPage openpaymobPage() {
        hover(paymobDashboardButton, 30);
        click(paymobDashboardButton, 30);
        invisibilityOf(loadingIcon, 30);
        return new PaymobDashboardPage(driver);
    }


    public int windowNumber()
    {
        return driver.getWindowHandles().size();
    }

    public void closeTab (int tabNumber)
    {
        String originalTab = driver.getWindowHandle();

        // Step 2: Switch to the second tab and close it
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[tabNumber - 1]);
        driver.close();

        // Step 3: Switch back to the first tab
        driver.switchTo().window(originalTab);
    }
}
