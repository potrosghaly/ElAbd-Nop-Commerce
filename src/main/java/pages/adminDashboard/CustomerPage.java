package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class CustomerPage extends MethodHandles {
    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By searchPhoneField = By.id("SearchPhone");
    private final By searchButton = By.id("search-customers");
    private final By viewCustomerButton = By.xpath("//a[@class='btn btn-default']");


    public void searchByCustomerPhone(String phone) {
        sendKeys(searchPhoneField, phone, 30);
        click(searchButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public boolean customerIsExisting() {
        return driver.findElements(viewCustomerButton).isEmpty();
    }

    public CutomerProfilePage deleteCustomer() {
        click(viewCustomerButton, 30);
        return new CutomerProfilePage(driver);
    }
}
