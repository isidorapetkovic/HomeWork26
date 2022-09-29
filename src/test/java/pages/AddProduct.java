package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProduct extends BasePage{ //add-to-cart-sauce-labs-backpack
    private By addToCart = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    private By cartIcon=By.xpath("//*[@id=\"shopping_cart_container\"]/a");

    public AddProduct(WebDriver driver) {
        super(driver);
   }

    public void add () {
        WebElement button=getDriver().findElement(addToCart);
        button.click();
    }

    public void clickCart() {
        WebElement cartIcon=getDriver().findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cartIcon.click();
    }
}
