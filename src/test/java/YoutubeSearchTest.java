import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


// Open Youtube.
// Search for "queryForSearch".
// Verify results and print Details about first Channel that matches "queryForSearch".
//TODO: Implement user-agent
// - move comments#
// -restructure
// - names and descriptions


public class YoutubeSearchTest {
    boolean isResultsDisplayed;
    WebDriver driver;
    String queryForSearch;
    WebElement subs_count;
    WebElement video_count;

    @BeforeClass
    public void beforeSuite() {
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
        //FIXME: QUESTION: What is an iFrame and why do I need to switch my WebDriver to access any element in it?
        // - you can put a seperate HTML in it.
    }

    private void waitForResultsStats(String resultStatsElementID) {
        //Confirms that we see at least one channel on results page.
//        resultStatsElementID = "//*[@id='video-count' and contains(text(),'Videos')]";
        WebElement resultsconfirm = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultStatsElementID)));

    }

    public boolean verifyResultsPage(){
        //FIXME: WTF IS GOING ON HERE?
        //How many Subscribers does the channel have?
        subs_count = driver.findElement(By.xpath("//*[@id='subscribers']"));
        //How many Videos did the Channel upload?
        video_count = driver.findElement(By.xpath("//*[@id='video-count' and contains(text(),'Videos')]"));
        //Confirm we are on the results page.
        System.out.format("The Query for '%s' found a Channel with %s Subscribers and %s uploaded.", queryForSearch, subs_count.getText().split(" ")[0],video_count.getText());
        //Test was successful if Result is displayed.
        return isResultsDisplayed = video_count.isDisplayed();
    }


    //Test description
    @Test
    public void test0001() {
        queryForSearch = "Portnov Computer School";
        driver.get("https://www.youtube.com/");
        typeQuery(queryForSearch);
        waitForResultsStats("//*[@id='video-count' and contains(text(),'Videos')]");
        verifyResultsPage();
        //ASSERTION AT THE END OF TEST
        Assert.assertTrue(verifyResultsPage());
    }

    //FIXME: QUESTION: Since SetUp() is called before every Test, it also creates new instances of firefox browser - but my @After only closes the most recent. //call quit()
    // - How can we setup one browser instance for all tests? With @BeforeSuite
    // - How can we call @After after each test?
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
