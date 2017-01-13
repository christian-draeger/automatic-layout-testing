package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Pages;

/**
 * Created by jose-luis.nunez-icho on 09.01.17.
 */
public class StartPage extends Pages {
    public StartPage(final WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".nano-content div[style*='projekte']")
    private WebElement projekteNav;

    public void clickProjekte() {
        projekteNav.click();
    }

    @FindBy(css = "iframe[id*='oldiframe']")
    private WebElement iframe;

    @FindBy(css = "span.baButton")
    private WebElement searchButton;

    public void clickSearchButton() {
        searchButton.click();
    }

    public void switchToFrame() {
        driver.switchTo().frame(iframe);
    }
}
