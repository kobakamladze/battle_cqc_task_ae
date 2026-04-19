package base;

import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class BaseScreen {
    protected WebDriver driver;

    public BaseScreen() {
        this.driver = DriverManager.getDriver();
    }
}
