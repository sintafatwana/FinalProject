package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartPage {

    WebDriver driver;

    By ItemQuantity = By.className("cart_quantity");
    public By ItemName = By.className("inventory_item_name");
    By ItemDesc = By.className("inventory_item_desc");
    By ItemPrice = By.className("inventory_item_price");
    By BtnContinueShopping = By.id("continue-shopping");
    By BtnCheckOut = By.id("checkout");
    public By BtnRemove = By.id("remove-sauce-labs-backpack");
    By CartIconShowBadge = By.cssSelector(".shopping_cart_badge");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public void ClickBtnRemove(){
        driver.findElement(BtnRemove).click();
    }

    public void ValidateRemoveBerhasil(){
        boolean isRemoveButtonStillPresent = driver.findElements(BtnRemove).isEmpty();

        if (!isRemoveButtonStillPresent) {
            System.out.println("✅ Item telah berhasil dihapus.");
        } else {
            System.out.println("❌ Tombol Remove masih muncul. Item mungkin belum terhapus.");
        }

    }

    public void ClickBtnContinueShopping(){
        driver.findElement(BtnContinueShopping).click();
    }

    public void ClickBtnCheckOut() {
        driver.findElement(BtnCheckOut).click();
    }

    public void ValidateItemQuantity(){
        WebElement ItemQuantityElement = driver.findElement(ItemQuantity);
        assertTrue(ItemQuantityElement.isDisplayed());
        assertEquals("1", ItemQuantityElement.getText());
    }

    public void ValidateItemName(){
        WebElement ItemNameElement = driver.findElement(ItemName);
        assertTrue(ItemNameElement.isDisplayed());
        assertEquals("Sauce Labs Backpack", ItemNameElement.getText());
    }

    public void ValidateItemDesc(){
        WebElement ItemDescElement = driver.findElement(ItemDesc);
        assertTrue(ItemDescElement.isDisplayed());
        assertEquals("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", ItemDescElement.getText());
    }

    public void ValidateItemPrice() {
        WebElement ItemPriceElement = driver.findElement(ItemPrice);
        assertTrue(ItemPriceElement.isDisplayed());
        assertEquals("$29.99", ItemPriceElement.getText());
    }

    public String GetCartShowBadge(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(CartIconShowBadge));
        return badge.getText();
        //return driver.findElement(CartIconShowBadge).getText();
    }
}
