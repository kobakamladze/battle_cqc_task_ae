package base;

import base.elements.TextElement;
import org.openqa.selenium.By;

public abstract class BaseScreen {

    protected TextElement screenBasicElement;

    public BaseScreen(By by, String elementName) {
        screenBasicElement = new TextElement(by, elementName);
        screenBasicElement.waitForIsDisplayed();
    }
}
