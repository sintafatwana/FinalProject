package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutCompletePage {
    WebDriver driver;

    By completeHeader = By.className("complete-header");
    By btnBackToHome = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public void ValidateOnCheckoutCompletePage(){
        WebElement complete = driver.findElement(completeHeader);
        assertTrue(complete.isDisplayed());
        assertEquals("Thank you for your order!", complete.getText());
    }

    public void ClickBtnBackToHome(){
        driver.findElement(btnBackToHome).click();
    }
}
