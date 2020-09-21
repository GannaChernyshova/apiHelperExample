package apiHelperExample;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for all the JUnit-based test classes
 */
public class JUnitTestBase {

    protected static String baseUrl;
    protected WebDriver driver;

    @BeforeAll
    public static void loadConfig() throws Throwable {
        SuiteConfiguration config = new SuiteConfiguration();
        RestAssured.baseURI = config.getProperty("github.url");
        RestAssured.basePath = config.getProperty("github.repositories.path");
        baseUrl = config.getProperty("site.url");
    }

    @BeforeEach
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
