package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.MethodHandles;

public class TestPage extends MethodHandles {
    public TestPage(WebDriver driver) {
        super(driver);
    }


    private final By merchantReferenceNumber = By.xpath("//div[@id='order-info']//div[1]//div[1]//div[7]//div[2]");
    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");


    public void openOrderDetailsPage(int orderID) {
        driver.get("https://elabdfoods.com/Admin/Order/Edit/" +orderID);
        invisibilityOf(loadingIcon, 30);
    }


    public String getMerchantNumber() {

        return getText(merchantReferenceNumber, 30);
    }


}
