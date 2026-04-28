package base.elements;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.waiters.SmartWait;

public class BaseElement {

    private static final Logger log = LoggerFactory.getLogger(BaseElement.class.getSimpleName());

    public String elementName;
    protected String elementInfo;
    private By by;
    protected WebElement element;

    BaseElement(By by, String elementName) {
        this.elementName = elementName;
        this.by = by;
        this.elementInfo = by + " - " + elementName;
        driver();
        find(by);
    }

    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    public void find(By by) {
        try {
            log.info("Looking for element - " + elementInfo);
            this.element = driver().findElement(by);
            System.out.println(driver());
        } catch (NoSuchElementException e) {
            log.error(elementInfo + " Not found");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForIsDisplayed() {
        log.info("Waiting for element - {} to be visible", elementInfo);
        SmartWait.waitFor(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getClasses() {
        return element.getAttribute("class");
    }
}
