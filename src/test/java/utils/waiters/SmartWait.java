package utils.waiters;

import base.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SmartWait {

    private static final Logger log = LoggerFactory.getLogger(SmartWait.class.getSimpleName());

    private static final int MAX_WAIT = 30;
    private static final int POLLING_WAIT = 200;
    private static final int DEFAULT_TIMEOUT = 90;

    private static final String ERROR_MESSAGE = "An exception has been intercepted";

    public static <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, DEFAULT_TIMEOUT);
    }

    public static <T> T waitFor(ExpectedCondition<T> condition, long timeOutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .pollingEvery(Duration.ofMillis(POLLING_WAIT))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        try {
            return wait.until(condition);
        } catch (AssertionError throwable) {
            log.error(ERROR_MESSAGE);
        }
        return null;
    }
}
