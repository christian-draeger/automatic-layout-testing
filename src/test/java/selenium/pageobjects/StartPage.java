package selenium.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jose-luis.nunez-icho on 09.01.17.
 */
public class StartPage {


    @FindBy(css = ".nano-content div[style*='projekte']")
    private WebElement projekteNav;

    public void clickProjekte() {
        projekteNav.click();
    }

    @FindBy(css = "iframe[id*='oldiframe']")
    private WebElement iframe;

    @FindBy(css = "span.baButton")
    private WebElement searchButton;

    public WebElement getIFrame(){
        return iframe;
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
