package pages;

import base.BaseScreen;
import base.elements.ButtonElement;
import base.elements.NotificationElement;
import enums.GameNotifications;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.waiters.SmartWait;

import java.util.Random;

public class LobbyScreen extends BaseScreen {

    private static final Logger log = LoggerFactory.getLogger(LobbyScreen.class.getSimpleName());


    By btnStart = By.cssSelector(".battlefield-start-button");
    By textWaitingForRival = By.cssSelector(".notification__waiting-for-rival");
    By btnFamiliarRival = By.cssSelector(".battlefield-start-choose_rival-variant-link__connect");
    By btnShuffleShips = By.cssSelector(".placeships-variant-link");

    public LobbyScreen() {
        super(By.cssSelector(".logo"), "Screen logo");
    }

    public void startGame() {
        new ButtonElement(btnStart, "Play button").click();
    }

    public void shuffleShipsLocation() {
        int r = new Random().nextInt(15);
        ButtonElement btnShuffle = new ButtonElement(btnShuffleShips, "Shuffle ships button");
    }

    public void waitForRival() {
        SmartWait.waitFor(ExpectedConditions.attributeContains(
                textWaitingForRival,
                "class",
                "none"
        ));
    }

    public void chooseSpecificRival() {
        new ButtonElement(btnFamiliarRival, "Button familiar rival").click();
    }

    public boolean isGameOver() {
        boolean won = new NotificationElement(GameNotifications.GAME_OVER_WIN, "Game won notification")
                .isVisible();
        boolean lost = new NotificationElement(GameNotifications.GAME_OVER_LOSE, "Game lost notification")
                .isVisible();
        log.info("Game finished - won: {}, lost: {}", won, lost);
        return won || lost;
    }

    public boolean wasGameStopped() {
        boolean serverError = new NotificationElement(GameNotifications.SERVER_ERROR, "Server error")
                .isVisible();
        boolean rivalLeft = new NotificationElement(GameNotifications.RIVAL_LEAVE, "Rival left")
                .isVisible();
        boolean gameError = new NotificationElement(GameNotifications.GAME_ERROR, "Game error")
                .isVisible();
        log.info("Game stopped - Server error: {}, Rival left: {}, Game Error: {}", serverError, rivalLeft, gameError);
        return serverError || rivalLeft || gameError;
    }

    public void assertGameWon() {
        boolean won = new NotificationElement(GameNotifications.GAME_OVER_WIN, "Game won notification")
                .isVisible();
        Assert.assertTrue(won, "!!!!!!!!!!!!!! Game was lost !!!!!!!!!!!!!!");
    }
}
