package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public AuthPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public InventoryPage login(String login, String password) {
        usernameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new InventoryPage(driver);
    }
}
