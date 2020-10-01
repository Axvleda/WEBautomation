package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ResultsPageForGoogleTest {
    private WebDriver driver;
    String resultStatsElementID = "result-stats";


    public ResultsPageForGoogleTest(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForResultsStats() {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(resultStatsElementID)));
    }

    public boolean verifyResultsPage(){
        WebElement element = driver.findElement(By.id(resultStatsElementID));
        return element.isDisplayed();

    }
}
