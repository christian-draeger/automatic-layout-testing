package selenium;

import org.junit.After;
import org.openqa.selenium.WebDriver;

import selenium.driver.WebDriverConfig;
import selenium.utils.WebDriverProvider;



public abstract class SeleniumTestWrapper {

	// Config
	private final WebDriverConfig webDriverConfig = new WebDriverConfig();
	protected final WebDriverProvider webDriverProvider = new WebDriverProvider(this.webDriverConfig);


	protected WebDriver getDriver() {
		return this.webDriverProvider.getDriver();
	}

	@After
	public void closeBrowser(){
		getDriver().quit();
	}

}
