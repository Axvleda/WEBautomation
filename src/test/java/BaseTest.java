import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    public WebDriver driver;

    @Parameters({ "browser" })
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/resources/drivers/Firefox/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().setPosition(new Point(-1685,22));
        driver.manage().window().setSize(new Dimension(850,1015));

    }

    @AfterClass
    public void afterClass() {
        driver.close();
        driver.manage().deleteAllCookies();
    }
}
