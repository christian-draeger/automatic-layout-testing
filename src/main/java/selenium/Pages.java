package selenium;

import org.openqa.selenium.WebDriver;

import selenium.configurations.TypedProperties;

public abstract class Pages {

	protected WebDriver driver;

	private String baseUrl = new TypedProperties("/test_config.properties").getValue("base_url");

	public Pages(WebDriver driver) {
		this.driver = driver;
	}

	protected void open(String path){
		driver.get(baseUrl + path);
	}

	protected void open(){
		driver.get(baseUrl);
	}

}
