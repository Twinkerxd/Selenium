import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests extends BaseTest {

    @Test
    public void endToEnd() {
        String actualResult = openMainPage()
                .login("standard_user", "secret_sauce")
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

        Assertions.assertTrue(actualResult.equals("Thank you for your order!"));
    }
}
