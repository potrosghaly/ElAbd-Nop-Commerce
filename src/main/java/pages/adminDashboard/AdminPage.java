package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class AdminPage extends MethodHandles {
    public AdminPage(WebDriver driver) {
        super(driver);
    }



    private final By salesDropdown = By.xpath("//i[@class='nav-icon fas fa-shopping-cart']");
    private final By salesButton = By.xpath("//li[@class='nav-item has-treeview menu-is-opening menu-open']//li[1]//a[1]");
    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By callCenterIcon = By.xpath("//a[@href='/Admin/CustomerService']");
    private final By customerDropdownIcon = By.xpath("//i[@class='nav-icon far fa-user']");
    private final By customerButton = By.xpath("//a[@href='/Admin/Customer/List']");
    private final By catalogDropdown = By.xpath("//i[@class='nav-icon fas fa-book']");
    private final By productButton = By.xpath("//a[@href='/Admin/Product/List']");


    public SalesPage openSalesPage() {
        hover(salesDropdown, 30);
        click(salesDropdown, 30);
        visiblityOfElement(salesButton, 30);
        click(salesButton, 30);
        invisibilityOf(loadingIcon, 30);
        return new SalesPage(driver);
    }

    public CallCenterPage openCallCenterPage() {
        hover(callCenterIcon, 30);
        click(callCenterIcon, 30);
        return new CallCenterPage(driver);
    }

    public CustomerPage openCustomerPage() {
        hover(customerDropdownIcon, 30);
        click(customerDropdownIcon, 10);
        visiblityOfElement(customerButton, 10);
        click(customerButton, 10);
        invisibilityOf(loadingIcon, 30);
        return new CustomerPage(driver);
    }

    public ProductPage openProductPage() {

        hover(catalogDropdown, 30);
        click(catalogDropdown, 10);
        visiblityOfElement(productButton, 10);
        click(productButton, 10);
        invisibilityOf(loadingIcon, 30);
        return new ProductPage(driver);
    }

}
