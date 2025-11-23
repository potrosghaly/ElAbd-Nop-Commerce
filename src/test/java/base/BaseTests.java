package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.Methods;
import utils.ScreenRecorderUtil;
import utils.UtilsTests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static reader.ReadDataFromJson.dataModel;

public class BaseTests {

    protected WebDriver driver;
    protected HomePage homePage;
    protected Methods method;

    UtilsTests utilsTests;

    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;


    public void goHomePage() throws Exception {
        driver.get(dataModel().newDevURL);
    }


    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("Chrome") String browser) {
        setUpBrowser(browser);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        method = new Methods(homePage);
    }


    @Parameters("browser")
    public void setUpBrowser(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--kiosk-printing");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("headlessChrome")) {
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
            System.out.println("Headless Chrome viewport size: " + driver.manage().window().getSize());

        } else if (browser.equalsIgnoreCase("headlessFirefox")) {
            firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }
    }

    @BeforeMethod
    public void goHome(Method method) throws Exception {
        ScreenRecorderUtil.startRecord(method.getName());
        goHomePage();
        utilsTests = new UtilsTests(driver);
        utilsTests.createTestCaseInReport(method);

    }


    @AfterMethod
    public void afterMethod(Method method, ITestResult result) throws Exception {
        utilsTests = new UtilsTests(driver);
        utilsTests.takeScreenShot(method);
        ScreenRecorderUtil.stopRecord();
        utilsTests.endsOfSteps();
        utilsTests.setStatus(result);
        utilsTests.addAttachment(method);


    }

    @BeforeSuite
    public void beforeSuite() {
        utilsTests = new UtilsTests(driver);
        utilsTests.createReport();
    }

    @AfterSuite
    public void afterSuite() {
        utilsTests = new UtilsTests(driver);
        utilsTests.flushReport();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void closeTab() {
        driver.close();
    }

    public void sleepPerSeconds(int seconds) throws InterruptedException {
        Thread.sleep(1000L * seconds);
    }

    public void sleepPerMinutes(int minutes) throws InterruptedException {
        Thread.sleep(1000L * minutes * 60);
    }

    public String saveTab() {
        return driver.getWindowHandle();
    }

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void openNewTab(int index) {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }


    public void backToTab(String tabName) {
        driver.switchTo().window(tabName);
    }
    public void refreshPage() {
        driver.navigate().refresh();
    }


}
