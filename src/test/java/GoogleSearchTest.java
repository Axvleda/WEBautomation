import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "src\\resources\\drivers\\win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    private void typeQuery(String queryForSearch){
        String selector = ".gLFyf"; // Input Box

        WebElement element = driver.findElement(By.cssSelector(selector));

        element.sendKeys(queryForSearch);

        element.submit();

        waitForResultsStats();

        verifyResultsPage();

    }

    private void waitForResultsStats() {
        String resultStatsElementID = "result-stats";

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(resultStatsElementID)));
    }

    public void verifyResultsPage(){
         String resultStatsElementID = "result-stats";


         WebElement element = driver.findElement(By.id(resultStatsElementID));

         boolean isResultsDisplayed = element.isDisplayed();

        Assert.assertTrue(isResultsDisplayed);
    }


    @Test
    public void test0001() {
        String queryForSearch = "Portnov Computer School";

        driver.get("https://www.google.de/");

        typeQuery(queryForSearch);


    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
