package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartIcon;

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
}
