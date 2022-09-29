package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.List;

public class TestPage {

    private LoginPage loginPage;
    private AddProduct addProduct;

    private CheckOutMessage checkOutMessage;
    private ConfirmPage confirmPage;
    private EndPage endPage;
    private CartPage cartPage;
    private WebDriver driver;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        addProduct = new AddProduct(driver);
        cartPage = new CartPage(driver);
        checkOutMessage = new CheckOutMessage(driver);
        confirmPage = new ConfirmPage(driver);
        endPage = new EndPage(driver);
    }

    @Test
    public void login() {
        loginPage.login("standard_user", "secret_sauce");
        String expectedResult = "Swag Labs";
        String actualResult = loginPage.getDriver().getTitle();
        Assert.assertEquals(actualResult, expectedResult);

    }


    @Test(dependsOnMethods = {"login"})
    public void addProduct() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        addProduct.add();
        addProduct.clickCart();
    }

    @Test(dependsOnMethods = {"addProduct"})
    public void checkOut() throws InterruptedException {
        cartPage.checkout();
        Assert.assertTrue(driver.getPageSource().contains("Checkout: Your Information"));
    }

    @Test(dependsOnMethods = {"checkOut"})
    public void confirm() throws InterruptedException {
        confirmPage.fillForm();
        confirmPage.nextPage();
        Assert.assertTrue(driver.getPageSource().contains("Checkout: Overview"));
        Assert.assertTrue(driver.getPageSource().contains("QTY"));
        Assert.assertTrue(driver.getPageSource().contains("DESCRIPTION"));
    }

    @Test(dependsOnMethods = {"confirm"})
    public void checkoutMessage() throws InterruptedException {
        Assert.assertEquals(checkOutMessage.getTotal(), "Total: $32.39");
        checkOutMessage.finish();
        Assert.assertTrue(driver.getPageSource().contains("THANK YOU FOR YOUR ORDER"));
    }

    @Test(dependsOnMethods = {"checkoutMessage"})
    public void endPage() throws InterruptedException {
        endPage.logout();
        Assert.assertTrue(driver.getPageSource().contains("Swag Labs"));
        Assert.assertTrue(driver.getPageSource().contains("Login"));
    }
}