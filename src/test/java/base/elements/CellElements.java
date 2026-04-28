package base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CellCoordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CellElements extends BaseElement {

    private static final Logger log = LoggerFactory.getLogger(CellElements.class.getSimpleName());

    private List<CellElement> elements = new ArrayList<>(0);

    public CellElements(By by, String elementName) {
        super(by, elementName);
        find(by);
    }

    @Override
    public void find(By by) {
        try {
            log.info("Looking for element - {}", elementInfo);
            List<WebElement> retrieved = driver().findElements(by);
            this.elements = retrieved.stream()
                    .map(e -> new CellElement(e, by, "Cell"))
                    .collect(Collectors.toList());
            System.out.println(driver());
        } catch (NoSuchElementException e) {
            log.error(elementInfo + " Not found");
            e.printStackTrace();
        }
    }

    public CellElement getCell(int index) {
        return elements.get(index);
    }

    public CellElement getCellByCoordinates(int x, int y) {
        return elements.stream()
                .filter(e -> e.getCoordinates().y == x && e.getCoordinates().x == y)
                .findFirst()
                .orElse(null);
    }

    public List<CellElement> getAllElements() {
        return elements;
    }

    public int getAmount() {
        log.info("Getting cells amount");
        return elements.size();
    }
}
