import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


// Open Youtube.
// Search for "queryForSearch".
// Verify results and print Details about first Channel that matches "queryForSearch".


public class YoutubeSearchTest {

    WebDriver driver;
    String queryForSearch;
    WebElement subs_count;
    WebElement video_count;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
    }

    private void typeQuery(String queryForSearch){
        try {
            //Types our "queryForSearch" into the search field and hits "ENTER".
            driver.findElement(By.xpath("//input[@id='search']")).sendKeys(queryForSearch + Keys.ENTER);
        } catch (org.openqa.selenium.ElementNotInteractableException e) {
            dismissLogin();
            e.printStackTrace();
        }
        waitForResultsStats();
        verifyResultsPage();
    }

    private void dismissLogin() {
        //If Youtube is visited for the first time, the website asks for Login etc. - We want to dismiss that
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dismiss-button']//paper-button"))).click();

            //To agree to the Google form we have to switch to the frame first and then find the button.
            driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='iframe']")));
            driver.findElement(By.xpath("//*[@id='introAgreeButton' and @role='button']")).click();
            driver.switchTo().defaultContent();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //FIXME: QUESTION: What is an iFrame and why do i need to switch my WebDriver to access any element in it?
    }

    private void waitForResultsStats() {
        //Confirms that we see at least one channel on results page.
        String resultStatsElementID = "//*[@id='video-count' and contains(text(),'Videos')]";
        WebElement resultsconfirm = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultStatsElementID)));

    }

    public void verifyResultsPage(){
        //How many Subscribers does the channel have?
        subs_count = driver.findElement(By.xpath("//*[@id='subscribers']"));
        //How many Videos did the Channel upload?
        video_count = driver.findElement(By.xpath("//*[@id='video-count' and contains(text(),'Videos')]"));
        //Confirm we are on the results page.
        boolean isResultsDisplayed = video_count.isDisplayed();
        //Test was successful if Result is displayed.
        Assert.assertTrue(isResultsDisplayed);
        System.out.format("The Query for '%s' found a Channel with %s Subscribers and %s uploaded.", queryForSearch, subs_count.getText().split(" ")[0],video_count.getText());
    }

    @Test
    public void test0001() {
        queryForSearch = "Portnov Computer School";
        driver.get("https://www.youtube.com/");
        typeQuery(queryForSearch);
    }

    @Test
    public void test0002() {
        queryForSearch = "Neuralink";
        driver.get("https://www.youtube.com/");
        typeQuery(queryForSearch);
    }


    //FIXME: QUESTION: Since SetUp() is called before every Test, it also creates new instances of firefox browser - but my @After only closes the most recent.
    // - How can we setup one browser instance for all tests?
    // - How can we call @After after each test?
    @AfterTest
    public void afterTest() {
        driver.close();
    }
}
