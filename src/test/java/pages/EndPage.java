package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EndPage extends BasePage {
    private By confirmMessage = By.xpath("//*[@id=\"checkout_complete_container\"]/h2");
    private By menuButton = By.id("react-burger-menu-btn");


    public EndPage(WebDriver driver) {
        super(driver);

    }

    public By getConfirmMessage() {
        return confirmMessage;
    }

    public By getMenuButton() {
        return menuButton;
    }

    public void logout() {
        WebElement menuButton = getDriver().findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        WebElement logoutButton = getDriver().findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }
}