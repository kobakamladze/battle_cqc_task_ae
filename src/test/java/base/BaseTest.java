package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Config;

import java.time.Duration;

public abstract class BaseTest {

    private static final String URL = Config.get("base_url");
    protected static final int MAX_WAIT = 10;
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverManager.quit();
        }
    }
}
