package org.example.crapsgame.model;

public class Game {
    private int shootCount;
    private int shoot;
    private int point;
    private boolean win;
    private boolean lose;
    public Dice dice1, dice2;

    public Game() {
        this.shootCount = 0;
        this.shoot = 0;
        this.point = 0;
        this.win = false;
        this.lose = false;
        this.dice1 = new Dice();
        this.dice2 = new Dice();
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

    public void resetGame() {
        this.shootCount = 0;
        this.shoot = 0;
        this.point = 0;
        this.win = false;
        this.lose = false;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void startGame() {
        this.win = false;
        this.lose = false;
    }

    public boolean isGameStarting() {
        return this.point != 0;
    }

    public int rollDices() {
        this.dice1.rollDice();
        this.dice2.rollDice();
        this.shoot = this.dice1.getValue() + this.dice2.getValue();
        return this.shoot;
    }
}
