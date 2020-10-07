import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MainPageForGoogleTest;
import pages.ResultsPageForGoogleTest;

public class GoogleSearchTest extends BaseTest{


    @Test
    public void test001() {
        String queryForSearch = "Portnov Computer Schhool";

        MainPageForGoogleTest mainPage = new MainPageForGoogleTest(driver);
        mainPage.open();
        mainPage.typeQuery(queryForSearch);

        ResultsPageForGoogleTest resultsPage = new ResultsPageForGoogleTest(driver);
        resultsPage.waitForResultsStats();
        boolean elementIsVisible = resultsPage.verifyResultsPage();

        Assert.assertTrue(elementIsVisible);
    }

    @Parameters({ "queryText "})
    @Test
    public void test002( String param01) {

        MainPageForGoogleTest mainPage = new MainPageForGoogleTest(driver);
        mainPage.open();
        mainPage.typeQuery(param01);

        ResultsPageForGoogleTest resultsPage = new ResultsPageForGoogleTest(driver);
        resultsPage.waitForResultsStats();
        boolean elementIsVisible = resultsPage.verifyResultsPage();

        Assert.assertTrue(elementIsVisible);
    }

    @Test
    public void test_SearchWithParameters( String param01) {
        String queryForSearch = param01;

        MainPageForGoogleTest mainPage = new MainPageForGoogleTest(driver);
        mainPage.open();
        mainPage.typeQuery(queryForSearch);

        ResultsPageForGoogleTest resultsPage = new ResultsPageForGoogleTest(driver);
        resultsPage.waitForResultsStats();
        boolean elementIsVisible = resultsPage.verifyResultsPage();

        Assert.assertTrue(elementIsVisible);
    }
}


//FIXME: finalHOMEWORK
// - BaseTest Class with selection of browserType, setup & teardown.
// - Main Page  with this.driver, open & interaction.
// - Results Page with verification.
// - testNG.xml with Parameters Test
// - CHECK: Aleks Github for Testng






