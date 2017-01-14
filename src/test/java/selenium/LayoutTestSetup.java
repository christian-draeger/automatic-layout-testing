package selenium;


import static selenium.driver.WebDriverBuilder.Browser.PHANTOMJS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.model.MultipleFailureException;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.validation.ValidationResult;

import utils.TestUtils;

/**
 * Check RWD Layouts on all page templates with Galen Framework
 */
@RunWith(Parameterized.class)
public class LayoutTestSetup extends SeleniumTestWrapper {

    static List<GalenTestInfo> tests = new LinkedList<>();

    @Parameters(name="test for {0} viewport {1}")
    // returns required (CSS) breakpoints and mobile UserAgent
    public static Iterable<Object[]> data() {
        // TODO: change UAs as soon as RWD-pages are reachable via desktop browser
        return Arrays.asList(new Object[][] {
            { "test" }
        });
    }

    @Parameter
    public String device;

    @Before
    public void init() throws IOException {
        TestUtils.sleep(500); // wait until window is resized
    }

    protected void checkSpecFile(final String SpecName) throws Exception {
        // link to spec file
        LayoutReport layoutReport = Galen.checkLayout(getDriver(PHANTOMJS), "src/test/resources/specs/" + SpecName + ".gspec", Arrays.asList(device));

        // create test layout report object for the test report
        GalenTestInfo test = GalenTestInfo.fromString(SpecName + " (" + device + ")");

        // add layout report object to the test report
        test.getReport().layout(layoutReport, SpecName);
        LayoutTestSetup.tests.add(test);

        failIfErrors(layoutReport);
    }

    // create error message
    private void failIfErrors(final LayoutReport report) throws Exception {
        List<Throwable> errors = new ArrayList<>();

        for (ValidationResult result : report.getValidationErrorResults()) {
            if (!result.getError().isOnlyWarn()) {
                String message = StringUtils.join(result.getError().getMessages(), "\n");
                errors.add(new AssertionError(message));
            }
        }

        MultipleFailureException.assertEmpty(errors);
    }

    @AfterClass
    // export test report to html page into target
    public static void tearDown() throws IOException {
        new HtmlReportBuilder().build(LayoutTestSetup.tests, "target/galen-html-reports-mobil-idealo-de");
    }
}
