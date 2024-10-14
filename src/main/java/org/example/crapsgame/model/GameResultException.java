package org.example.crapsgame.model;

public class GameResultException extends Exception {

    private boolean isWin;

    public GameResultException(String message, boolean isWin) {
        super(message);
        this.isWin = isWin;
    }

    public boolean isWin() {
        return isWin;
    }
}