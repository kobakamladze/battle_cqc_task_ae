package base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class.getSimpleName());
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        log.info("Creating driver...");
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        log.info("Getting driver...");
        return driver.get();
    }

    public static void quit() {
        log.info("Closing driver...");
        driver.get().quit();
        driver.remove();
    }

}