package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.domstorage.model.Item;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DetailItemPage {
    public By ItemName = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]");
    public By RemoveBtn = By.id("remove");
    public By AddToCartBtn = By.id("add-to-cart");

    WebDriver driver;
    public DetailItemPage(WebDriver driver){
        this.driver=driver;
    }
    public void ValidateItemName(){
        WebElement validateItemName = driver.findElement(ItemName);
        assertTrue(validateItemName.isDisplayed());
        assertEquals("Sauce Labs Backpack", validateItemName.getText());
    }
    public void ClickRemoveBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(RemoveBtn));
        removeBtn.click();
    }
    public void ClickAddToCartBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(AddToCartBtn));
        addToCartBtn.click();
    }
    public void ValidateRemoveBtn(){
        boolean RemoveBtnHilang = driver.findElements(RemoveBtn).isEmpty();

        if(!RemoveBtnHilang){
            System.out.println("Remove button belum berhasil dihapus");
        } else {
            System.out.println("Remove button berhasil dihapus");
        }
    }
    public void ValidateAddToCartBtn(){
        WebElement addToCartBtn = driver.findElement(AddToCartBtn);
        assertTrue(addToCartBtn.isDisplayed());
        assertEquals("Add To Cart", addToCartBtn.getText());
    }

}
