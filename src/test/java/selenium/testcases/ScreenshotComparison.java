package selenium.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.galenframework.api.Galen;
import com.galenframework.api.GalenPageDump;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;
import utils.TestUtils;

public class ScreenshotComparison {

        List<GalenTestInfo> tests = new LinkedList<>();

        @Before
        public void before() {
            ChromeDriverManager.getInstance().setup();
            final ChromeDriver chromeDriver = new ChromeDriver();
            chromeDriver.manage().window().setSize(new Dimension(800, 800));

            chromeDriver.get("https://blueantwebinar.proventis.net/webinar5//psap");

            TestUtils.sleep(1000);

            takeScreenshot("chrome", chromeDriver);

            TestUtils.sleep(1000);

            chromeDriver.quit();
        }


        @Test
        public void checkLogin() throws Exception {

            MarionetteDriverManager.getInstance().setup();
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.manage().window().setSize(new Dimension(800, 800));

            firefoxDriver.get("https://blueantwebinar.proventis.net/webinar5//psap");

            TestUtils.sleep(1000);

            new GalenPageDump("login").dumpPage(firefoxDriver,
                                            "src/test/resources/specs/comparison.gspec",
                                            "target/galen-html-reports");

            TestUtils.sleep(1000);

            // link to spec file
            LayoutReport layoutReport = Galen.checkLayout(firefoxDriver, "src/test/resources/specs/comparison.gspec", Arrays.asList("compare"));

            // create test layout report object for the test report
            GalenTestInfo test = GalenTestInfo.fromString("login");

            // add layout report object to the test report
            test.getReport().layout(layoutReport, "test");
            this.tests.add(test);

            firefoxDriver.quit();
        }

        @After
        // export test report to html page into target
        public void tearDown() throws IOException {

            new HtmlReportBuilder().build(tests, "target/galen-html-reports");

        }

        private void takeScreenshot(String browserName, WebDriver driver) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = getFileForScreenshot(browserName);
            try {
                FileUtils.copyFile(scrFile, destFile);
            } catch (final IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        public File getFileForScreenshot(String browserName) {
            for (int i = 0;; ++i) {
                File file = new File("./src/test/resources/specs/" + browserName + ".png");
                if (!file.exists()) {
                    return file;
                }
            }
            
        }
}
