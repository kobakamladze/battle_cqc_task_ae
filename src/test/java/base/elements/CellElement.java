package base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CellCoordinate;
import utils.CellState;
import utils.waiters.SmartWait;

public class CellElement extends ButtonElement {

    private static final Logger log = LoggerFactory.getLogger(CellElement.class.getSimpleName());

    private CellState cellState;
    private CellCoordinate coordinates;


    public CellElement(WebElement element, By by, String elementName) {
        super(by, elementName);
        this.element = element;
        this.setCoordinates();
        String classes = element.findElement(By.xpath("..")).getAttribute("class");
        if (classes.contains("battlefield-cell__done")) {
            this.cellState = CellState.KILL;
        } else if (classes.contains("battlefield-cell__hit")) {
            this.cellState = CellState.HIT;
        } else if (classes.contains("battlefield-cell__miss")) {
            this.cellState = CellState.MISS;
        } else {
            this.cellState = CellState.EMPTY;
        }
    }

    public CellState getCellState() {
        return cellState;
    }

    public void waitForIsClickable() {
        log.info("Waiting for cell - {} to be clickable", elementInfo);
        SmartWait.waitFor(ExpectedConditions.elementToBeClickable(element));
    }

    private void setCoordinates() {
        int y = Integer.parseInt(element.getAttribute("data-y"));
        int x = Integer.parseInt(element.getAttribute("data-x"));
        this.coordinates = new CellCoordinate(x, y);
    }

    public CellCoordinate getCoordinates() {
        return coordinates;
    }
}
