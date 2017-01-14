package selenium.testcases;

import static selenium.driver.WebDriverBuilder.Browser.PHANTOMJS;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import selenium.LayoutTestSetup;
import selenium.pageobjects.Login;
import selenium.pageobjects.StartPage;
import utils.TestUtils;

public class LayoutIT extends LayoutTestSetup {

    Login login = PageFactory.initElements(getDriver(PHANTOMJS), Login.class);
    StartPage startPage = PageFactory.initElements(getDriver(PHANTOMJS), StartPage.class);

    @Before
    public void setup() {
        login.open();
        TestUtils.sleep(5000);

    }

    @Test
    public void checkLogoOnLoginPage() throws Exception {
        checkSpecFile("login");
    }

    @Test
    public void checkLogoOnStartpage() throws Exception {
        login.loginTestUser();
        TestUtils.sleep(1000);
        checkSpecFile("startpage");
    }

    @Test
    public void checkLogoOnProjecsPage() throws Exception {
        login.loginTestUser();
        TestUtils.sleep(1000);
        startPage.clickProjekte();
        TestUtils.sleep(1000);
        //iFRAME
        startPage.switchToFrame();
        startPage.clickSearchButton();
        TestUtils.sleep(1000);
        checkSpecFile("projects");
    }





}
