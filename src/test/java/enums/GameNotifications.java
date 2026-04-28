package enums;

public enum GameNotifications {
    RIVAL_LEAVE(".notification__rival-leave"),
    GAME_OVER_WIN(".notification__game-over-win"),
    GAME_OVER_LOSE(".notification__game-over-lose"),
    SERVER_ERROR(".notification__server-error"),
    GAME_ERROR(".notification__game-error");

    public final String selector;
    GameNotifications(String selector) { this.selector = selector; }
}
