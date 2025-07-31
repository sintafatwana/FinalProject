package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePage {
    public By ProductTitle = By.id("item_4_title_link");
    public By BtnRemove = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");
    public By BtnAddToCart = By.xpath("//button[contains(text(), 'Add to cart')]");
    public By CartIcon = By.className("shopping_cart_link");
    public By ItemImage = By.className("inventory_item_img");

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void validateOnHomePage(){
        WebElement ProductElement = driver.findElement(ProductTitle);
        assertTrue(ProductElement.isDisplayed());
        assertEquals("Sauce Labs Backpack", ProductElement.getText());
    }

    public void ValidateOnRemoveButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(BtnRemove));
        removeBtn.click();
    }

    public void ClickRemoveButtonOnHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(BtnRemove));
            removeBtn.click();
        } catch (TimeoutException e) {
            throw new AssertionError("Remove button on Home Page was not clickable within timeout. Locator: " + BtnRemove, e);
        }
    }

    public void ClickBtnAddToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(BtnAddToCart));
        addToCartBtn.click();
    }

    public void ClickCartIcon(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(CartIcon));
        //cartIcon.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(CartIcon)).click();
    }

    public void ValidateOnAddToCartBtn(){
        WebElement AddToCartBtn = driver.findElement(BtnAddToCart);
        assertTrue(AddToCartBtn.isDisplayed());
        assertEquals("Add To Cart", AddToCartBtn.getText());
    }
    public void ClickItemImageOnHomePage(){
        driver.findElement(ItemImage).click();
    }
}
