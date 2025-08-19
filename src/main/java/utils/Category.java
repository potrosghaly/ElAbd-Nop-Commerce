package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Category {
    private final WebElement category;

    public Category(WebElement category) {
        this.category = category;
    }

    private final By image = By.tagName("img");
    private final By button = By.tagName("button");

    public void clickonbutton()
    {
        //category.findElements(button).
    }
}
