package selenium.pageobjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

public class Login extends Pages {

	public Login(final WebDriver driver) {
		super(driver);
	}

	public void open(){
		super.open();
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
