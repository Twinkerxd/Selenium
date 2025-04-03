package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class InventoryPage extends BasePage {
    // https://www.saucedemo.com/inventory.html

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartIcon;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement shoppingCartBadge;

    @FindBy(css = "[data-test^='add-to-cart']")
    private List<WebElement> addToCartButtons;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public InventoryPage addItemToCart(String itemName) {
        String generatedXpath = "//*[text()='" + itemName + "']/parent::a/parent::div/following-sibling::*/button";
        driver.findElement(By.xpath(generatedXpath)).click();
        return this;
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage(driver);
    }

    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }

    public InventoryPage addRandomAddItemToCart() {
        Random random = new Random();
        addToCartButtons.get(random.nextInt(addToCartButtons.size())).click();
        return this;
    }


}
