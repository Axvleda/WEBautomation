import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
//        String selector = "input.ytd-searchbox"; // Input Box
//        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
//        element.sendKeys(queryForSearch);
//        element.submit();

        //quicker way
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(queryForSearch + Keys.ENTER);


        waitForResultsStats();

        verifyResultsPage();
    }

    private void dismissLogin() {

        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dismiss-button']//paper-button"))).click();
//            driver.findElement(By.xpath("//*[@id='dismiss-button']//paper-button")).click();

            driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='iframe']")));
            driver.findElement(By.xpath("//*[@id='introAgreeButton' and @role='button']")).click();
            driver.switchTo().defaultContent();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //TODO: QUESTION: What is an iFrame and why do i need to switch my webdriver to access any element in it?
    }

    private void waitForResultsStats() {
        String resultStatsElementID = "//*[contains(text(),'portnov']";

        WebElement resultsconfirm = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(resultStatsElementID)));
        if (!resultsconfirm.isDisplayed()){
            dismissLogin();
        }
    }

    public void verifyResultsPage(){

        //driver.findElement(By.xpath("//*[contains(@id,'text')]")).getText();
        WebElement element = driver.findElement(By.xpath("//*[contains(@id,'text')]"));



        boolean isResultsDisplayed = element.isDisplayed();

        Assert.assertTrue(isResultsDisplayed);
    }


    @Test
    public void test0001() {
        String queryForSearch = "Portnov Computer School";

        driver.get("https://www.youtube.com/");

        typeQuery(queryForSearch);
    }


    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
