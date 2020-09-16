import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class WindowHandlesTest {
    
    
    WebDriver driver;

    //FIXME: for(String eachHandle : windowsHandles){}


    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.get("http://the-internet.herokuapp.com/");
    }

    private void waitUntilNumberOfWindows(final int numberOfWindows) {
//        WebDriverWait wait = new WebDriverWait(driver, 3);
//        while (driver.getWindowHandles().size() < numberOfWindows) {
//            try {
//                wait.until(driver -> driver.getWindowHandles().size() == numberOfWindows);
//            } catch (TimeoutException e) {
//                clickOnLink();
//            }
//        }

        while (driver.getWindowHandles().size() < numberOfWindows){
            clickOnLink();
        }
    }

    private void clickOnLink() {
        driver.findElement(By.xpath("//a[contains(text(), 'Click')]")).click();
    }

    private void openMultipleWindowsLink() {
        driver.findElement(By.xpath("//*[contains(text(), 'Windows')]")).click();
    }

    @AfterClass
    public void afterSuite() {
        driver.quit();
    }

    @Test
    public void test001() {
        //Test001 tries to open '5' tabs.
        int expectedWindows = 5;

        openMultipleWindowsLink();
        clickOnLink();
        waitUntilNumberOfWindows(expectedWindows);

        int actualWindows = driver.getWindowHandles().size();
        Assert.assertEquals(actualWindows, expectedWindows, MyOwnErrorMessages.AMOUNT_OF_WINDOWS);
    }
}
