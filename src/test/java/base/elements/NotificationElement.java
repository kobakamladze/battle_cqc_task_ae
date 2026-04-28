package base.elements;

import enums.GameNotifications;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationElement extends BaseElement {

    private static final Logger log = LoggerFactory.getLogger(NotificationElement.class.getSimpleName());

    public NotificationElement(GameNotifications notification, String elementName) {
        super(By.cssSelector(notification.selector), elementName);
    }

    public boolean isVisible() {
        try {
            String classes = getClasses();
            boolean visible = !classes.contains("none");
            log.info("Notification {} visible: {}", elementName, visible);
            return visible;
        } catch (NoSuchElementException e) {
            log.error("Notification {} not found", elementName);
            return false;
        }
    }
}
