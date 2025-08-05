package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutInformationPage {
    WebDriver driver;

    public By FirstNameField = By.id("first-name");
    public By LastNameField = By.id("last-name");
    public By PostalCodeField = By.id("postal-code");
    public By BtnCancel = By.id("cancel");
    public By BtnContinue = By.id("continue");
    public By Title = By.className("title");
    public By ErrorButton = By.cssSelector("h3[data-test='error']");

    public CheckoutInformationPage() {
        this.driver = DriverManager.getDriver(); // properly fetch WebDriver
    }

    public void InputFirstNameField(String FirstName){
        driver.findElement(FirstNameField).sendKeys(FirstName);
    }
    public void InputLastNameField(String LastName){
        driver.findElement(LastNameField).sendKeys(LastName);
    }
    public void InputPostalCodeField(String PostalCode){
        driver.findElement(PostalCodeField).sendKeys(PostalCode);
    }
    public void ClickBtnCancel(){
        driver.findElement(BtnCancel).click();
    }
    public void ClickBtnContinue(){
        driver.findElement(BtnContinue).click();
    }
    public void ValidateOnCheckoutInformationPage(){
        WebElement title = driver.findElement(Title);
        assertTrue(title.isDisplayed());
        assertEquals("Checkout: Your Information", title.getText());
    }

    public void ValidateBlankFirstNameField(){
        WebElement errorMessage = driver.findElement(ErrorButton);
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Error: First Name is required", errorMessage.getText());
    }

    public void ValidateBlankLastNameField(){
        WebElement errorMessage = driver.findElement(ErrorButton);
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Error: Last Name is required", errorMessage.getText());
    }
    public void ValidateBlankPostalCodeField(){
        WebElement errorMessage = driver.findElement(ErrorButton);
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Error: Postal Code is required", errorMessage.getText());
    }
}
