package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class SalesPage extends MethodHandles {
    public SalesPage(WebDriver driver) {
        super(driver);
    }

    private final By resetButton = By.cssSelector("a[type='button']");
    private final By searchButton = By.id("search-orders");
    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By ViewButton = By.xpath("//tbody/tr[1]/td[15]/a[1]");
    private final By phoneNumberField = By.id("BillingPhone");





    public void resestFilter() {
        click(resetButton, 30);
        invisibilityOf(loadingIcon , 30);
    }

    public void clickSearchButton() {
        sendKeys(phoneNumberField , "01271331374" , 30);
        click(searchButton, 30);
        invisibilityOf(loadingIcon , 30);
    }

    public void clickResetAndSearchButton() {
        click(searchButton, 30);
        invisibilityOf(loadingIcon , 30);
    }

    public OrderDetailsPage openFirstOrder()
    {
        click(ViewButton , 30);
        invisibilityOf(loadingIcon , 30);
        return new OrderDetailsPage(driver);
    }



}
