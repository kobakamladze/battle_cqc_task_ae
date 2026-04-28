package base.elements;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ButtonElement extends BaseElement {

    private static final Logger log = LoggerFactory.getLogger(ButtonElement.class.getSimpleName());

    public ButtonElement(By by, String elementName) {
        super(by, elementName);
    }

    public void click() {
        log.info("Clicking on Button - {}", elementInfo);
        element.click();
    }
}
