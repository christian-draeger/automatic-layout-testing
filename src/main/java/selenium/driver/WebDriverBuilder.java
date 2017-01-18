package selenium.driver;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

public class WebDriverBuilder {

    public enum Browser {
        CHROME,
        FIREFOX,
        OPERA,
        PHANTOMJS,
        EDGE,
        INTERNET_EXPLORER
    }

    private String name;

    // for junit testreport
    public void setName(String name) {
        this.name = name;
    }


    public WebDriver toWebDriver(Browser browser) {

        switch (browser) {
            case CHROME:
                ChromeDriverManager.getInstance().setup();
                final ChromeDriver chromeDriver = new ChromeDriver();
                chromeDriver.manage().window().maximize();
                return chromeDriver;
            case EDGE:
                EdgeDriverManager.getInstance().setup();
                final EdgeDriver edgeDriver = new EdgeDriver();
                edgeDriver.manage().window().maximize();
                return edgeDriver;
            case INTERNET_EXPLORER:
                InternetExplorerDriverManager.getInstance().setup();
                final InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver();
                internetExplorerDriver.manage().window().maximize();
                return internetExplorerDriver;
            case OPERA:
                OperaDriverManager.getInstance().setup();
                final OperaDriver operaDriver = new OperaDriver();
                operaDriver.manage().window().maximize();
                return operaDriver;
            case PHANTOMJS:
                PhantomJsDriverManager.getInstance().setup();
                final PhantomJSDriver phantomJsWebDriver = new PhantomJSDriver();
                phantomJsWebDriver.manage().timeouts().implicitlyWait(5, SECONDS);
                phantomJsWebDriver.manage().timeouts().setScriptTimeout(60, SECONDS);
                phantomJsWebDriver.manage().window().maximize();
                return phantomJsWebDriver;
            default:
                MarionetteDriverManager.getInstance().setup();
                FirefoxDriver firefoxDriver = new FirefoxDriver();
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;
        }
    }
}
