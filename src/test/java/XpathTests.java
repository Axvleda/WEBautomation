import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class XpathTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        String url = "https://www.google.com/";
        driver.get(url);
    }

    @Test
    public void test0001() {



        String xpath01 = "/html/body/div/div[2]/form/div[2]/div[1]/div[1]/div/div[2]/input";
        // absolute xpath is not safe to use because of changes that do not even have to do with it

        String xpath02 = "//form/div[2]/div[1]/div[1]/div/div[2]/input";
        // relative xpath is much safer

        String xpath03 = "//<input>[@name='q']" ;
        String xpath04 = "//*[@name='q']" ;
        String xpath05 = "//*[@name='q'][@title='Suche']";
        // pretty much safe 100%

        driver.findElement(By.xpath(xpath05));

        //Set a breakpoint and debug.
        //Click on test# and on "Evaluate" [kleiner taschenrechner im Debugger]
        //Perform methods and stuff



        String xpath06 = "//*[@name='q' and @title='Search']";

        String xpath07 = "/*[@title='search' and @title='Search']";
        String xpath08 = "/*[@title='search' or @title='Search']";

        //DONE: try to find the element with contains()
        String xpath09 = "//<input>[contains(@class,'inputClass')]";

        String xpath10 = "//<input>[starts-with(@class,'inputClass')]";

        String xpath11 = "//*[text()='Log In']";

        String xpath12 = "//*[text()=' Login']";

        String xpath13 = "(//a)[1]";
        String xpath14 = "(//a)[last()]";
        String xpath15 = "(//a)[position()=1]";

        driver.findElement(By.xpath("//*[@title='Suche']")).sendKeys(
                "costco" + Keys.ENTER
        );


        driver.findElement(By.xpath("//*[text()='Log In'"));
    }
}
