package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageForGoogleTest {
    private WebDriver driver;
    String queryInputCSSSelector = ".gLFyf"; // Input Box


    public MainPageForGoogleTest(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.google.de/");
    }

    public void typeQuery(String queryForSearch){
        WebElement element = driver.findElement(By.cssSelector(queryInputCSSSelector));
        element.sendKeys(queryForSearch);
        element.submit();
    }
}
