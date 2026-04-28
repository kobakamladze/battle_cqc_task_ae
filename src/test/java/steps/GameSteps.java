package steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GameBoard;

public class GameSteps {

    Logger log = LoggerFactory.getLogger(LobbySteps.class.getSimpleName());

    public void play() {
        log.info("STEP - Playing game");
        new GameBoard().strike();
    }

    public boolean waitingForMyTurn() {
        log.info("STEP - Waiting for my turn");
        return new GameBoard().isMyTurn();
    }
}
