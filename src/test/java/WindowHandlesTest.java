import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
//
public class WindowHandlesTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.get("http://the-internet.herokuapp.com/");
    }

    private void waitUntilNumberOfWindows(int numberOfWindows) {
        while (driver.getWindowHandles().size() < numberOfWindows){
            clickOnLink();
            try {
                new WebDriverWait(driver, 1).until(driver -> driver.getWindowHandles().size() == numberOfWindows);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clickOnLink() {
        driver.findElement(By.xpath("//a[contains(text(), 'Click')]")).click();    }

    private void openMultipleWindowsLink() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Windows')]"))).click();
    }

    private void deleteElement() {
        List<WebElement> deleteButtons =  driver.findElements(By.className("added-manually"));
        AmountofDeletedButtons = deleteButtons.size();
        for (WebElement deleteButton : deleteButtons) {
            deleteButton.click();
        }
    }

    private void addElement(int AmountOfButtons) {
        for (int i = 0; i < AmountOfButtons; i++){
            driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
        }
        AmountOfCreatedButtons = driver.findElements(By.className("added-manually")).size();
    }

    private void openAddRemoveElements() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add')]"))).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test001() {
        //Test001 tries to open # of 'expectedWindows' tabs then close all.
        int expectedWindows = 5;

        openMultipleWindowsLink();
        clickOnLink();
        waitUntilNumberOfWindows(expectedWindows);
        int actualWindows = driver.getWindowHandles().size();
        Assert.assertEquals(actualWindows, expectedWindows, MyOwnErrorMessages.AMOUNT_OF_WINDOWS);
    }


    int AmountOfCreatedButtons, AmountofDeletedButtons;

    @Test
    public void test002() {
        //Test002 adds 'AmountOfButtons' and then deletes all.
        int AmountOfButtons = 9;
        openAddRemoveElements();
        addElement(AmountOfButtons);
        deleteElement();

        Assert.assertEquals(AmountOfCreatedButtons & AmountofDeletedButtons, AmountOfButtons, "All created buttons, got deleted.");
    }



    @Test
    public void test003() {

        //Test003 tries the Authentification
        openSimpleAuthLink();
        enterCredentials();
    }

    private void enterCredentials() {
        String User, Password = "admin";

        //FIXME: Question: How can we type our credentials into the Alert box?

    }

    private void openSimpleAuthLink() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Basic Auth')]"))).click();
    }
}
