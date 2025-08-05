package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

import static org.junit.Assert.*;

public class HomePage {
    public By ProductTitle = By.id("item_4_title_link");
    public By BtnRemove = By.id("remove-sauce-labs-backpack");
    public By BtnAddToCart = By.id("add-to-cart-sauce-labs-backpack");
    public By CartIcon = By.id("shopping_cart_container");
    public By ItemImage = By.className("inventory_item_img");

    private WebDriver driver;

    public HomePage() {
        this.driver = DriverManager.getDriver(); // properly fetch WebDriver
    }

    public void validateOnHomePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement itemName = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductTitle));
        assertTrue(itemName.isDisplayed());
        assertEquals("Sauce Labs Backpack", itemName.getText());
    }

    public void ValidateOnRemoveButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(BtnRemove));
    }

    public void ClickRemoveButtonOnHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(BtnRemove));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public void ClickBtnAddToCart(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(BtnAddToCart));
        //addToCartBtn.click();
        driver.findElement(BtnAddToCart).click();
    }

    public void ClickCartIcon(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(CartIcon));
        //cartIcon.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(CartIcon)).click();
    }

    public void ValidateOnAddToCartBtn(){
        try {
            WebElement addToCartBtn = driver.findElement(BtnAddToCart);

            assertTrue("Add to Cart button is not displayed.", addToCartBtn.isDisplayed());
            assertEquals("Add to Cart button text mismatch.", "Add to cart", addToCartBtn.getText());

        } catch (NoSuchElementException e) {
            fail("Add to Cart button not found on the page.");
        }
    }
    public void ClickItemImageOnHomePage(){
        driver.findElement(ItemImage).click();
    }
}
