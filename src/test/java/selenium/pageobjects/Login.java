package selenium.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {


	private String baseUrl = "https://blueantwebinar.proventis.net/webinar5//psap";

	public String getBaseurl(){
		return baseUrl;
	}

	@FindBy(name = "MT_BENUTZERNAME")
	private WebElement userInput;

	@FindBy(name = "MT_Kennwort")
	private WebElement pwInput;

	@FindBy(name = "Submit2_btn")
	private WebElement submitButton;

	public void loginTestUser() {
		userInput.sendKeys("mustermann");
		pwInput.sendKeys("123456");
		submitButton.click();
	}



}
