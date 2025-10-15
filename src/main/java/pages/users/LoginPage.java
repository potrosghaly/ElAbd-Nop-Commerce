package pages.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.MethodHandles;

public class LoginPage extends MethodHandles {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By phoneNumberField = By.id("phoneNumber");
    private final By egyptCode = By.xpath("//option[@value='+20']");
    private final By verifyOtpButton = By.id("verify-button");
    private final By sendOtpButton = By.id("send-otp-btn");
    private final By passwordField = By.id("Password");
    private final By logButton = By.cssSelector(".btn.btn-primary.login-button.btn-block");


    public void clickSendOtpButton() {
        click(sendOtpButton, 30);
    }

    public void clickVerifyOtpButton() {
        click(verifyOtpButton, 5);
    }

    public void writePhoneNumber(String phone) {
        visiblityOfElement(egyptCode, 30);
        sendKeys(phoneNumberField, phone, 30);
    }

    public void writePassword(String password) {
        visiblityOfElement(passwordField, 20);
        sendKeys(passwordField, password, 20);
    }

    public HomePage clickLogin() {
        visiblityOfElement(logButton, 20);
        click(logButton, 30);
        return new HomePage(driver);

    }


}
