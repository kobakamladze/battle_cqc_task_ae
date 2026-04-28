package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import steps.GameSteps;
import steps.LobbySteps;

public class SeaBattlePlayTest extends BaseTest {

    @Test
    public void test() {
        new LobbySteps().shuffleShips();
        // enable line below to be able to play against autotest
//        new LobbySteps().playWithSpecificUser();
        new LobbySteps().startGame();
        new LobbySteps().waitingForRival();
        while(!new LobbySteps().isGameOver() || !new LobbySteps().wasGameStopped()) {
            if (new GameSteps().waitingForMyTurn()) {
                new GameSteps().play();
            }
        }
        new LobbySteps().assertGameWasWon();
    }
}
