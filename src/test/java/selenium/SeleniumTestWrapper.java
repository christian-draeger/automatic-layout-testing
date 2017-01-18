package selenium;

import static selenium.driver.WebDriverBuilder.Browser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.runners.model.MultipleFailureException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.validation.ValidationResult;

import selenium.utils.WebDriverProvider;



public abstract class SeleniumTestWrapper {

	protected final WebDriverProvider webDriverProvider = new WebDriverProvider();
	List<GalenTestInfo> tests = new LinkedList<>();

	protected void checkSpecFile(WebDriver driver, String specName) throws Exception {
		LayoutReport layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/" + specName + ".gspec", Arrays.asList("compare"));
		GalenTestInfo test = GalenTestInfo.fromString(getClass().getSimpleName());
		test.getReport().layout(layoutReport, specName);
		tests.add(test);
		new HtmlReportBuilder().build(tests, "target/html-reports");

		failIfErrors(layoutReport);
	}

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

	protected WebDriver getDriver(Browser browser) {
		return this.webDriverProvider.getDriver(browser);
	}

	protected void setBrowserDimension(WebDriver driver, int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	protected void takeScreenshot(String fileName, WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = getFileForScreenshot(fileName);
		try {
			FileUtils.copyFile(scrFile, destFile);
		} catch (final IOException e2) {
			throw new RuntimeException(e2);
		}
	}

	private File getFileForScreenshot(String fileName) {
		for (int i = 0;; ++i) {
			File file = new File("./src/test/resources/specs/" + fileName + ".png");
			if (!file.exists()) {
				return file;
			}
		}
	}

	protected String getBaseUrl() {
		return "https://blueantwebinar.proventis.net/webinar5//psap";
	}

	public static void sleep(final long millis) throws IllegalArgumentException {
		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
		}
	}
}
