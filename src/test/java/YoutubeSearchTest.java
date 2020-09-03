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
    String queryForSearch;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
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
        String resultStatsElementID = "//*[@id='video-count' and contains(text(),'Videos')]";

        WebElement resultsconfirm = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultStatsElementID)));

        if (!resultsconfirm.isDisplayed()){
            dismissLogin();
        }
    }

    public void verifyResultsPage(){

        //driver.findElement(By.xpath("//*[contains(@id,'text')]")).getText();

        WebElement subs_count = driver.findElement(By.xpath("//*[@id='subscribers']"));
        WebElement video_count = driver.findElement(By.xpath("//*[@id='video-count' and contains(text(),'Videos')]"));

        boolean isResultsDisplayed = video_count.isDisplayed();

        Assert.assertTrue(isResultsDisplayed);

        System.out.format("The Query for '%s' found a Channel with %s Subscribers and %s uploaded.", queryForSearch, subs_count.getText().split(" ")[0],video_count.getText());
    }


    @Test
    public void test0001() {
        queryForSearch = "Portnov Computer School";

        driver.get("https://www.youtube.com/");

        typeQuery(queryForSearch);
    }


    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
