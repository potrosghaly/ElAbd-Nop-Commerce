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
    private final By authorizedStatus = By.xpath("//div[contains(text(),'Authorized')]");
    private final By unchangedAlertUnpaidOrder = By.cssSelector(".alert.alert-danger.alert-dismissable");


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
        visiblityOfElement(cancelReasonButton , 30);
        click(cancelReasonButton, 30);
        click(confirmCancelButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void markOrderAsPaid() {
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
        return isDisplayed(cancelStatus, 30);
    }
    public boolean authorizedStatusIsAppear() {
        return isDisplayed(authorizedStatus, 30);
    }
    public boolean unchangeAlertIsAppear() {
        return isDisplayed(unchangedAlertUnpaidOrder, 30);
    }


}
