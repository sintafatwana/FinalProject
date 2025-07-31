package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.domstorage.model.Item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutOverviewPage {
    WebDriver driver;

    By SummaryQTY = By.className("cart_quantity");
    By ItemName = By.id("item_4_title_link");
    By ItemDesc = By.className("inventory_item_desc");
    By ItemPrice = By.className("inventory_item_price");
    By ValueLabel = By.className("summary_value_label");
    By ItemTotalLabel = By.className("summary_subtotal_label");
    By TaxLabel = By.className("summary_tax_label");
    By TotalLabel = By.className("summary_total_label");
    public By BtnFinish = By.id("finish");
    By BtnCancel = By.id("cancel");

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
    }

    public void ValidateOnSummaryQTY(){
        WebElement SummaryQTYElement = driver.findElement(SummaryQTY);
        assertTrue(SummaryQTYElement.isDisplayed());
        assertEquals("1",SummaryQTYElement.getText());
    }

    public void ValidateOnItemName(){
        WebElement ItemNameElement = driver.findElement(ItemName);
        assertTrue(ItemNameElement.isDisplayed());
        assertEquals("Sauce Labs Backpack", ItemNameElement.getText());
    }

    public void ValidateOnItemDesc(){
        WebElement ItemDescElement = driver.findElement(ItemDesc);
        assertTrue(ItemDescElement.isDisplayed());
        assertEquals("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", ItemDescElement.getText());
    }

    public void ValidateOnItemPrice() {
        WebElement ItemPriceElement = driver.findElement(ItemPrice);
        assertTrue(ItemPriceElement.isDisplayed());
        assertEquals("$29.99", ItemPriceElement.getText());
    }

    public void ValidateOnValueLabel(){
        WebElement ValueLabelElement = driver.findElement(ValueLabel);
        assertTrue(ValueLabelElement.isDisplayed());
        assertEquals("SauceCard #31337", ValueLabelElement.getText());
    }

    public void ValidateOnItemTotalLabel(){
        WebElement ItemTotalLabelElement = driver.findElement(ItemTotalLabel);
        assertTrue(ItemTotalLabelElement.isDisplayed());
        assertEquals("Item total: $29.99", ItemTotalLabelElement.getText());
    }

    public void ValidateOnTaxLabel(){
        WebElement TaxLabelElement = driver.findElement(TaxLabel);
        assertTrue(TaxLabelElement.isDisplayed());
        assertEquals("Tax: $2.40", TaxLabelElement.getText());
    }

    public void ValidateOnTotalLabel(){
        WebElement TotalLabelElement = driver.findElement(TotalLabel);
        assertTrue(TotalLabelElement.isDisplayed());
        assertEquals("Total: $32.39", TotalLabelElement.getText());
    }

    public void ClickBtnFinish(){
        driver.findElement(BtnFinish).click();
    }

    public void ClickBtnCancel(){
        driver.findElement(BtnCancel).click();
    }

}
