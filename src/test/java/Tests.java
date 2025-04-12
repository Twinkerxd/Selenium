import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthPage;
import pages.CartPage;
import pages.InventoryPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static pages.InventoryPage.ProductSortOption.PRICE_LOWER_HIGH;

public class Tests extends BaseTest {

    @Test
    @DisplayName("End to end test")
    @Tag("tag_one")
    public void endToEnd() {
        String actualResult = openMainPage()
                .standardUserLogIn()
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Onesie")
                .clickCartIcon()
                .clickCheckoutButton()
                .fillFirstNameInput("Sergei")
                .fillLastNameInput("Testowy")
                .fillPostalCodeInput("1613")
                .clickContinueButton()
                .clickFinishButton()
                .getCompleteHeaderText();

        String expectedResult = "Thank you for your order!";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void visibleOfShoppingCartBadge() {
        InventoryPage inventoryPage =  openMainPage()
                .standardUserLogIn()
                .addRandomAddItemToCart();

        Assertions.assertTrue(inventoryPage.getShoppingCartBadge().isDisplayed());
        Assertions.assertEquals("1", inventoryPage.getShoppingCartBadge().getText());
    }

    @Test
    public void removeItemFromCart() {
        CartPage cartPage = openMainPage().standardUserLogIn().addRandomAddItemToCart().clickCartIcon();
        Assertions.assertEquals(1,cartPage.getItemsCount());
        Assertions.assertEquals(0,cartPage.clickRemoveButton().getItemsCount());
    }

    @Test
    public void loginWithInvalidCredentials() {
        AuthPage authPage = openMainPage();

        authPage.logIn("qwe","qwe");
        Assertions.assertTrue(authPage.getErrorMessage()
                .contains("Username and password do not match any user in this service"));
    }

    @Test
    public void emptyUsernameErrorMessage() {
        AuthPage authPage = openMainPage();

        authPage.clickLoginButton();
        Assertions.assertTrue(authPage.getErrorMessage()
                .contains("Username is required"));
    }

    @Test
    public void emptyPasswordErrorMessage() {
        AuthPage authPage = openMainPage();

        authPage.fillLogin("djkfcghn");
        authPage.clickLoginButton();
        Assertions.assertTrue(authPage.getErrorMessage()
                .contains("Password is required"));
    }

    @Test
    public void sortByPrice() {
        List<Double> list = openMainPage().standardUserLogIn().clickSortDropdown().selectSortOptionByValue(PRICE_LOWER_HIGH).getListOfPrices();

        List<Double> expectedResult = new ArrayList<>(list);
        expectedResult.sort(Comparator.naturalOrder());

        Assertions.assertEquals(expectedResult,list);
    }

    @Test
    public void checkTitleColorAfterMouseOver() {
        String actualResult = openMainPage().standardUserLogIn().moveCursorToFirstItemName().getFirstItemColor();
        String expectedResult = "rgba(61, 220, 145, 1)";

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
