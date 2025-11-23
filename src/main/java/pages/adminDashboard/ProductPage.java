package pages.adminDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import utils.MethodHandles;

public class ProductPage extends MethodHandles {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    int numberOfRow = 2;
    String hubName = "وسط البلد";
    String totalStockQty = "";
    String totalReservedQTY = "";
    String productName = "";


    private final By loadingIcon = By.xpath("//div[@id='ajaxBusy']//span");
    private final By warehouseDropdown = By.id("SearchWarehouseId");
    private final By publishedIdDropdown = By.id("SearchPublishedId");
    private final By searchButton = By.id("search-products");
    private final By productNameField = By.id("Name");
    private final By productNameFilter = By.id("SearchProductName");

    private final By resetButton = By.xpath("//a[normalize-space()='Reset filter']");

    public By editButton = By.xpath("//tbody/tr[" + numberOfRow + "]/td[13]/a[1]");
    public By publicStoreButton = By.linkText("Public store");



    public void resetFilter() {
        visiblityOfElement(resetButton, 30);
        click(resetButton, 20);
    }

    public void openProductDetailPageByRow(String numberOfRow) {
        By editButtonByRow = By.xpath("//tbody/tr[" + numberOfRow + "]/td[13]/a[1]");
        visiblityOfElement(editButtonByRow, 30);
        click(editButtonByRow, 20);
    }

    public void selectHub() {
        visiblityOfElement(warehouseDropdown, 30);
        selectByVisibleText(warehouseDropdown, 60, hubName);
    }

    public void selectPublished() {
        visiblityOfElement(publishedIdDropdown, 30);
        selectByVisibleText(publishedIdDropdown, 60, "Published only");
    }

    public void writeProductName(String productName) {
        visiblityOfElement(productNameFilter, 30);
        sendKeys(productNameFilter , productName , 30);
    }

    public void submitFilter() {
        visiblityOfElement(searchButton, 30);
        click(searchButton, 30);
        invisibilityOf(loadingIcon, 30);
    }

    public void openProductDetailPage() {
        visiblityOfElement(editButton, 30);
        click(editButton, 20);
        invisibilityOf(loadingIcon, 30);
    }


    public void checkStock() {

        By rowLocator = By.xpath("//tr[.//div[contains(text(),'" + hubName + "')]]");
        visiblityOfElement(rowLocator, 20);
        WebElement row = driver.findElement(rowLocator);

        WebElement stockQty = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_qty_')]"));
        totalStockQty = stockQty.getAttribute("value");
        WebElement reservedQTY = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_reserved_')]"));
        totalReservedQTY = reservedQTY.getAttribute("value");
        int totalQtyInt = Integer.parseInt(totalStockQty) - Integer.parseInt(totalReservedQTY);

        while (totalQtyInt <= 0) {
            driver.navigate().back();
            numberOfRow = numberOfRow + 1;
            editButton = By.xpath("//tbody/tr[" + numberOfRow + "]/td[13]/a[1]");
            openProductDetailPage();

            rowLocator = By.xpath("//tr[.//div[contains(text(), '" + hubName + "')]]");
            visiblityOfElement(rowLocator, 20);
            row = driver.findElement(rowLocator);

            stockQty = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_qty_')]"));
            totalStockQty = stockQty.getAttribute("value");
            reservedQTY = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_reserved_')]"));
            totalReservedQTY = reservedQTY.getAttribute("value");
            totalQtyInt = Integer.parseInt(totalStockQty) - Integer.parseInt(totalReservedQTY);
        }
        rowLocator = By.xpath("//tr[.//div[contains(text(), '" + hubName + "')]]");
        visiblityOfElement(rowLocator, 20);
        row = driver.findElement(rowLocator);

        stockQty = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_qty_')]"));
        totalStockQty = stockQty.getAttribute("value");
        reservedQTY = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_reserved_')]"));
        totalReservedQTY = reservedQTY.getAttribute("value");
        WebElement name = driver.findElement(productNameField);
        productName = name.getAttribute("value");
    }

    public void getStocks() {
        By rowLocator = By.xpath("//tr[.//div[contains(text(), '" + hubName + "')]]");
        visiblityOfElement(rowLocator, 20);
        WebElement row = driver.findElement(rowLocator);

        WebElement stockQty = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_qty_')]"));
        totalStockQty = stockQty.getAttribute("value");
        WebElement reservedQTY = row.findElement(By.xpath(".//input[starts-with(@id, 'warehouse_reserved_')]"));
        totalReservedQTY = reservedQTY.getAttribute("value");
    }

    public int getStock() {
        return Integer.parseInt(totalStockQty);
    }

    public int getReserved() {
        return Integer.parseInt(totalReservedQTY);
    }

    public String getProductName() {
        return productName;
    }

    public HomePage openHomePage() {
        click(publicStoreButton , 30);
        return new HomePage(driver);
    }

    public void openProductDetailsByID(int productID)
    {
        driver.get("https://online-store-dotnet5-dev.azurewebsites.net/Admin/Product/Edit/" +productID);
        invisibilityOf(loadingIcon, 30);
    }

}
