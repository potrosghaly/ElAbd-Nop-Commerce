package pages.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.MethodHandles;

public class ValuePackPage extends MethodHandles {
    public ValuePackPage (WebDriver driver)
    {
        super(driver);
    }

    String productID = "3104";

    private final By loadingIcon = By.xpath("//div[@class='loading-image']");
    private final By productLocator = By.xpath("//div[@data-productid='" + productID + "']");
    private final By addToCartButtonLocator = By.cssSelector(".button-2.product-box-add-to-cart-button");
    private final By valueCheckbox = By.id("product_attribute_29_65");
    private final By attributeQtyField = By.id("product_attribute_29_65_qty");
    private final By addToCartButton = By.id("add-to-cart-button-" +productID);
    private final By alert = By.xpath("//p[@class='content']");



    public void addProductToCart() {
        visiblityOfElement(productLocator, 15);
        WebElement productElement = driver.findElement(productLocator);
        WebElement addToCartButton = productElement.findElement(addToCartButtonLocator);
        addToCartButton.click();
    }
    public void addAttribute(String QTY) {
        visiblityOfElement(valueCheckbox, 15);
        click(valueCheckbox , 20);
        clear(attributeQtyField , 20);
        sendKeys(attributeQtyField , QTY , 10);
        click(addToCartButton , 20);
        invisibilityOf(loadingIcon , 50);
    }

    public String getAlertText()
    {
        return getText(alert , 20);
    }



}
