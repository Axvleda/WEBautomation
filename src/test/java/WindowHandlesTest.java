import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

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

    @AfterClass
    public void afterSuite() {
        driver.quit();
    }


    @Test
    public void test001() {
        openWindowsPage();
        clickOnLink();
        waitUntilNumberOfWindows(2);
        int expectedWindows = 2;
        int actualWindows = driver.getWindowHandles().size();
        Assert.assertEquals(actualWindows, expectedWindows, MyOwnErrorMessages.AMOUNT_OF_WINDOWS);
    }

    private void waitUntilNumberOfWindows(final int numberOfWindows) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //TODO: UNDERSTAND lambda expressions "->"
        // - change to check for titles?
        // - change to check for anything else
        // - leetcode.com?
        wait.until(driver -> driver.getWindowHandles().size() == numberOfWindows);



    }

    private void clickOnLink() {
        driver.findElement(By.xpath("//*[contains(text(), 'Windows')]")).click();
    }

    private void openWindowsPage() {
        driver.findElement(By.xpath("//a[contains(text(), 'Click')]")).click();
    }
}
