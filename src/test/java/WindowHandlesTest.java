import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class WindowHandlesTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(-1685,22));
        driver.manage().window().setSize(new Dimension(850,1015));
        driver.manage().deleteAllCookies();
        driver.get("http://the-internet.herokuapp.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test001() {
        //Test001 tries to open # of 'expectedWindows' tabs then close all.
        int expectedWindows = 5;

        openMultipleWindowsLink();
        clickOnLink();
        waitUntilNumberOfWindows(expectedWindows);
        int actualWindows = driver.getWindowHandles().size();
        Assert.assertEquals(actualWindows, expectedWindows, MyOwnErrorMessages.AMOUNT_OF_WINDOWS);
    }

    private void openMultipleWindowsLink() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Windows')]"))).click();
    }

    private void clickOnLink() {
        driver.findElement(By.xpath("//a[contains(text(), 'Click')]")).click();    }

    private void waitUntilNumberOfWindows(int numberOfWindows) {
        while (driver.getWindowHandles().size() < numberOfWindows){
            clickOnLink();
            try {
                new WebDriverWait(driver, 1).until(driver -> driver.getWindowHandles().size() == numberOfWindows);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int AmountOfCreatedButtons, AmountofDeletedButtons;
    @Test
    public void test002() {
        //Test002 adds 'AmountOfButtons' and then deletes all.
        int AmountOfButtons = 9;
        openAddRemoveElements();
        addElement(AmountOfButtons);
        deleteElement();
        Assert.assertEquals(AmountOfCreatedButtons & AmountofDeletedButtons, AmountOfButtons, "AmountOfCreatedButtons: " + AmountOfCreatedButtons + "   AmountofDeletedButtons: " + AmountofDeletedButtons + "  Values should be: " + AmountOfButtons);
    }

    private void openAddRemoveElements() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add')]"))).click();
    }

    private void addElement(int AmountOfButtons) {
        for (int i = 0; i < AmountOfButtons; i++){
            driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
        }
        AmountOfCreatedButtons = driver.findElements(By.className("added-manually")).size();
    }

    private void deleteElement() {
        List<WebElement> deleteButtons =  driver.findElements(By.className("added-manually"));
        AmountofDeletedButtons = deleteButtons.size();
        for (WebElement deleteButton : deleteButtons) {
            deleteButton.click();
        }
    }

    @Test
    public void test003() {
        //Test003 tries the Authentification.
        getSimpleAuthLink();
        enterCredentials();
        Assert.assertTrue(verifyLogin(), "Login was not successful.");
    }

    private void openSimpleAuthLink() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Basic Auth')]"))).click();
    }

    String SimpleAuthLink;
    void getSimpleAuthLink(){
        WebElement LinkElement = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(LinkElement));
        SimpleAuthLink = LinkElement.getAttribute("href").replace("http://","");
    }

    private void enterCredentials() {
        String UsernameAndPassword = "admin";

        //FIXME: Question: How can we type our credentials into the Alert box?
        //        new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        //        Alert alert = driver.switchTo().alert();
        //        String AlertMessage = alert.getText();
        //        alert.dismiss();
        //        alert.sendKeys(UsernameAndPassword);
        //        driver.findElement(By.xpath("//*[contains(text(),'username')]"));


        //FIXME: Since we cannot sendKeys to the Alert, we open the AuthLink with the Credentials in the URL.
        String urlWithCredentials = String.format("http://%s:%s@%s", UsernameAndPassword, UsernameAndPassword, SimpleAuthLink);
        driver.navigate().to(urlWithCredentials);
    }

    private boolean verifyLogin() {
        try {
            new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'Congratulations! You must have the proper credentials.')]"))));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Test
    public void test004() {
        //Test004 
        openHoversLink();
        getFigureDetails();
        openUser(2);
    }

    private void openUser(int UserIndex) {
        if (UserIndex <= HoverNames.size()) {
            //TODO: Implement url finder in HoverNames and open selected.
            driver.navigate().to(HoverNames.get(2));
        }
    }

    HashMap<String, String> HoverNames = new HashMap<>();
    private void getFigureDetails() {
        Actions actions = new Actions(driver);
        List<WebElement> HoverElements = driver.findElements(By.xpath("//div[@class='figure']"));

        for (WebElement Figure: HoverElements) {
            actions.moveToElement(Figure);
            actions.build().perform();
            //TODO: Implement for each WebElement.
            new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(Figure + "/div/h5"))));
            String name = driver.findElement(By.xpath("//h5")).getText().split(": ")[1];
            String url = driver.findElement(By.xpath("//a[contains(text(),'View')]")).getAttribute("href");
            HoverNames.put(name, url);
        }
    }

    private void openHoversLink() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Hovers')]"))).click();
    }
}
