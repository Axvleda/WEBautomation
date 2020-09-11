 import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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

    private void SearchString(String queryForSearch){
        try {
            //Types our "queryForSearch" into the search field and hits "ENTER".
            driver.findElement(By.xpath("//input[@id='search']")).sendKeys(queryForSearch + Keys.ENTER);
        } catch (org.openqa.selenium.ElementNotInteractableException e) {
            e.printStackTrace();
        }

    }

    void Login() throws NoSuchElementException{
        try {
            driver.findElement(By.xpath("//*[@id='remind-me-later-button']")).click();
            String LoginButton = "//*[@id='buttons']//paper-button[@id='button']";
            driver.findElement(By.xpath(LoginButton)).click();
        } finally {
            driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("dnalor618@gmail.com");
        }
    };

    private void dismissLogin() {
        //If Youtube is visited for the first time, the website asks for Login etc. - We want to dismiss that
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dismiss-button']//paper-button"))).click();

            //To agree to the Google form we have to switch to the frame first and then find the button.
            driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='iframe']")));
            driver.findElement(By.xpath("//*[@id='introAgreeButton' and @role='button']")).click();
            driver.switchTo().defaultContent();
            System.out.println("Login dismissed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //QUESTION: What is an iFrame and why do I need to switch my WebDriver to access any element in it?
        // - A: You can put a seperate HTML in it.
    }


    public boolean verifyResultsPage(String resultStatsElementID){
        WebElement resultsconfirm = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultStatsElementID)));
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
    public void test0001() throws Exception {
        queryForSearch = "Portnov Computer School";
        driver.get("https://www.youtube.com/");
        SearchString(queryForSearch);
        dismissLogin();
        verifyResultsPage("//*[@id='video-count' and contains(text(),'Videos')]");
        //ASSERTION AT THE END OF TEST
        Assert.assertTrue(verifyResultsPage("//*[@id='video-count' and contains(text(),'Videos')]"));
        Login();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}


//driver.executeScript("return navigator.userAgent");
//driver.manage().deleteAllCookies();
//driver.manage().getCookies();