package steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LobbyScreen;

public class LobbySteps {

    Logger log = LoggerFactory.getLogger(LobbySteps.class.getSimpleName());

    public void startGame() {
        log.info("STEP - Starting game");
        new LobbyScreen().startGame();
    }

    public void waitingForRival() {
        log.info("STEP - Waiting for rival");
        new LobbyScreen().waitForRival();
    }

    public boolean isGameOver() {
        log.info("STEP - Waiting for my turn");
        return new LobbyScreen().isGameOver();
    }

    public boolean wasGameStopped() {
        log.info("STEP - Check if game was stopped");
        return new LobbyScreen().wasGameStopped();
    }

    public void shuffleShips() {
        log.info("STEP - Shuffling ships");
        new LobbyScreen().shuffleShipsLocation();
    }


    public void playWithSpecificUser() {
        new LobbyScreen().chooseSpecificRival();
    }

    public void assertGameWasWon() {
        log.info("STEP - Asserting game was won");
        new LobbyScreen().assertGameWon();
    }
}
