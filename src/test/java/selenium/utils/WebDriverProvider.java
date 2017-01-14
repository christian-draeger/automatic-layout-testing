package selenium.utils;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import selenium.driver.WebDriverBuilder;

public class WebDriverProvider extends TestWatcher {
    private final WebDriverBuilder webDriverBuilder;
    private WebDriver driver;

    public WebDriverProvider() {
        this.webDriverBuilder = new WebDriverBuilder();
    }

    public WebDriver getDriver(WebDriverBuilder.Browser browser) {
        return webDriverBuilder.toWebDriver(browser);
    }


    public boolean existsDriver() {
        return driver != null;
    }

    @Override
    protected void starting(final Description description) {
        String methodName = description.getClassName() + "." + description.getMethodName();
        this.webDriverBuilder.setName(methodName);
    }

    @Override
    protected void finished(final Description description) {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
