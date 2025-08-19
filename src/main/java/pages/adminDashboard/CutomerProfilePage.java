package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class CutomerProfilePage extends MethodHandles {
    public CutomerProfilePage(WebDriver driver) {
        super(driver);
    }

    private final By deleteButton = By.id("customer-delete");
    private final By confirmDeleteButton = By.xpath("//button[@class='btn btn-danger float-right']");

    private final By successDeleteAlert = By.xpath("//div[@class='alert alert-success alert-dismissable']");

    public void clickDeleteButton()
    {
        click(deleteButton , 30);
        click(confirmDeleteButton , 30);
    }

    public Boolean successAlertIsAppear()
    {
        return isDisplayed(successDeleteAlert , 30);
    }

}
