package selenium.testcases;

import static selenium.driver.WebDriverBuilder.Browser.CHROME;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import selenium.SeleniumTestWrapper;
import selenium.pageobjects.Login;
import selenium.pageobjects.StartPage;

public class LayoutIT extends SeleniumTestWrapper {

    WebDriver driver = getDriver(CHROME);

    Login login = PageFactory.initElements(driver, Login.class);
    StartPage startPage = PageFactory.initElements(driver, StartPage.class);

    @Before
    public void setup() {
        driver.get(login.getBaseurl());
        sleep(1000);
    }

    @Test
    public void checkLogoOnLoginPage() throws Exception {
        checkSpecFile(driver, "login");
    }

    @Test
    public void checkLogoOnStartpage() throws Exception {
        login.loginTestUser();
        sleep(1000);
        checkSpecFile(driver, "startpage");
    }

    @Test
    public void checkLogoOnProjecsPage() throws Exception {
        login.loginTestUser();
        sleep(1000);
        startPage.clickProjekte();
        sleep(1000);
        //iFRAME
        driver.switchTo().frame(startPage.getIFrame());
        startPage.clickSearchButton();
        sleep(1000);
        checkSpecFile(driver, "projects");
    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
