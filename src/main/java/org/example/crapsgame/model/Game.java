package org.example.crapsgame.model;

public class Game {
    private int shootCount;
    private int shoot;
    private int point;
    private boolean win;
    private boolean lose;
    Dice dice1, dice2;
    private boolean gameStarting;

    public Game() {
        this.shootCount = 0;
        this.shoot = 0;
        this.point = 0;
        this.gameStarting = false; // Inicializa el estado del juego
    }

    public int getShoot() {
        return this.shoot;
    }

    public int getShootCount() {
        return this.shootCount;
    }

    public int getPoint() {
        return this.point;
    }

    public boolean isWin() {
        return this.win;
    }

    public boolean isLose() {
        return this.lose;
    }

    public boolean isGameStarting() {
        return gameStarting;
    }

    public void startGame() {
        gameStarting = true; // Cambia el estado del juego a iniciado
    }

    public void resetGame() {
        gameStarting = false;
        point = 0; // Reinicia el punto
        shootCount = 0; // Reinicia el conteo de tiros
        shoot = 0; // Reinicia el tiro
        win = false; // Reinicia el estado de victoria
        lose = false; // Reinicia el estado de derrota
    }

    public void setPoint(int point) {
        this.point = point; // Establece el punto actual
    }

    public int rollDices() {
        // Lógica para lanzar los dados, actualizar `shoot`, etc.
        return this.shoot; // Devuelve el resultado del tiro
    }
}
