package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private final WebDriver driver;

    private final By searchTxtBox = By.id("twotabsearchtextbox") ;
    private final By searchSubmitBtn = By.id("nav-search-submit-button") ;

    private final By cartBtn = By.id("nav-cart") ;

    private final By cartCountAssertion = By.id("nav-cart-count") ;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForProduct(String productName)
    {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(searchTxtBox));

        setInputText(driver.findElement(searchTxtBox),productName);
        clickButton(driver.findElement(searchSubmitBtn));

    }





    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));

        clickButton(driver.findElement(cartBtn)); ;
    }

    public String getCartCount()
    {
       return driver.findElement(cartCountAssertion).getText() ;
    }


}
