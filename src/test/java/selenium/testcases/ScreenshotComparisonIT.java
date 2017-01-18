package selenium.testcases;

import static selenium.driver.WebDriverBuilder.Browser;
import static selenium.driver.WebDriverBuilder.Browser.CHROME;
import static selenium.driver.WebDriverBuilder.Browser.FIREFOX;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.galenframework.api.GalenPageDump;

import selenium.SeleniumTestWrapper;

public class ScreenshotComparisonIT extends SeleniumTestWrapper {

    @Before
    public void collectScreenshotsToCompare() {

        takeScreenshotWith(CHROME);
        // takeScreenshotWith(OPERA);
    }

    @Test
    public void compareScreenShotsOfLoginPage() throws Exception {

        WebDriver firefox = getDriver(FIREFOX);

        setBrowserDimension(firefox, 800,800);

        firefox.get(getBaseUrl());

        sleep(1000);

        new GalenPageDump("login").dumpPage(firefox,"src/test/resources/specs/comparison.gspec","target/html-reports");

        checkSpecFile(firefox, "comparison");

        firefox.quit();
    }

    private void takeScreenshotWith(Browser browser) {

        WebDriver driver = getDriver(browser);

        setBrowserDimension(driver, 800,800);

        driver.get(getBaseUrl());

        sleep(1000);

        takeScreenshot("loginPage-chrome", driver);

        driver.quit();

    }
}
