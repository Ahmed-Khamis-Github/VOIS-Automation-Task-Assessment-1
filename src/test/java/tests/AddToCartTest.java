package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class AddToCartTest extends TestBase {

    HomePage homeObject;
    SearchResultsPage searchResultsObject;
    ProductPage productObject;
    CartPage cartObject;

    @BeforeMethod
    public void setUp() {
        homeObject = new HomePage(driver);
        searchResultsObject = new SearchResultsPage(driver);
        productObject = new ProductPage(driver);
        cartObject = new CartPage(driver);
    }

    @Test()
    public void verifyEmptyCartMessage() {
        homeObject.goToCart();
        Assert.assertTrue(cartObject.getEmptyCartMessage().contains("Your Amazon Cart is empty"), "Cart is not empty as expected.");
    }

    @Test()
    public void userCanSearchForProduct() {
        homeObject.searchForProduct("car accessories");
        Assert.assertTrue(searchResultsObject.getSearchedKeywordTxt().contains("car accessories"));
        searchResultsObject.clickFirstItem();
        Assert.assertTrue(productObject.isProductTitlePresent());
    }

    @Test()
    public void userCanAddItemToCart() {
        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickFirstItem();
        productObject.addItemToCart();
        Assert.assertTrue(productObject.getProductAddedAlertAssertion().contains("Added to Cart"));
        homeObject.goToCart();
        Assert.assertTrue(cartObject.isCheckoutElementPresent());
        Assert.assertTrue(cartObject.isSubtotalElementPresent());
    }

    @Test
    public void userCanAddMultipleQuantities() {

        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickFirstItem();
        productObject.selectQuantity(4);
        productObject.addItemToCart();
        homeObject.goToCart();
        Assert.assertTrue(cartObject.getCartQuantity().contains("4"));

    }

    @Test
    public void userCanRemoveProductFromCart() {

        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickFirstItem();
        productObject.addItemToCart();
        homeObject.goToCart();
        cartObject.removeProductFromCart();
        homeObject.goToCart();
        Assert.assertTrue(cartObject.getEmptyCartMessage().contains("Your Amazon Cart is empty"), "Cart is not empty as expected.");

    }

    @Test
    public void userCanAddMultipleItems() {

        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickFirstItem();
        productObject.addItemToCart();
        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickSecondItem();
        productObject.addItemToCart();
        homeObject.goToCart();
        Assert.assertEquals(homeObject.getCartCount(), "2");
    }


    @Test()
    public void verifyEmptyResultsForMissingSearchTerm() {
        String initialUrl = driver.getCurrentUrl();
        homeObject.searchForProduct("");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, initialUrl, "URL changed after performing empty search.");
    }

    @Test()
    public void verifyProductDetailsAreDisplayed() {
        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickFirstItem();
        Assert.assertTrue(productObject.isProductPricePresent());
        Assert.assertTrue(productObject.isProductTitlePresent());
     }

 }
