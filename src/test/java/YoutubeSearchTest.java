import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


// Open Youtube
// Search for Portnov Computer School
// Verify results
// Click on first result

public class YoutubeSearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
    }


    private void typeQuery(String queryForSearch){
        String selector = "input.ytd-searchbox"; // Input Box

        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        element.sendKeys(queryForSearch);

        element.submit();

        waitForResultsStats();

        verifyResultsPage();
    }

    private void waitForResultsStats() {
        String resultStatsElementID = "portnovschool";

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(resultStatsElementID)));
    }

    public void verifyResultsPage(){
        String resultStatsElementID = "portnovschool";

        WebElement element = driver.findElement(By.id(resultStatsElementID));

        boolean isResultsDisplayed = element.isDisplayed();

        Assert.assertTrue(isResultsDisplayed);
    }


    //TODO: Find any WebElement with 'contains()'
    @Test
    public void test0001() {
        String queryForSearch = "//*[contains(@text,'inputClass')]";

        driver.get("https://www.youtube.com/");

        typeQuery(queryForSearch);
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
