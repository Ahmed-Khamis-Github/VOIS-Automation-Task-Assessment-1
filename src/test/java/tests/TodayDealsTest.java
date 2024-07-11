package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class TodayDealsTest extends TestBase {
    HomePage homeObject;
    TodayDealsPage todayDealsObject;
    ProductPage productObject;
    CartPage cartObject;

    @BeforeMethod
    public void setUp() {
        homeObject = new HomePage(driver);
        todayDealsObject = new TodayDealsPage(driver);
        productObject = new ProductPage(driver);
        cartObject = new CartPage(driver);
    }

    @Test
    public void userCanNavigateToTodayDealsSuccessfully() {
        homeObject.navigateToTodayDealsPage();
    }

    @Test
    public void userCanApplyFiltersAndAddItemToCart() throws InterruptedException {
        homeObject.navigateToTodayDealsPage();

        todayDealsObject.applyFilters();
        productObject.addItemToCart();

        Assert.assertTrue(productObject.getProductAddedAlertAssertion().contains("Added to Cart"));
    }
}
