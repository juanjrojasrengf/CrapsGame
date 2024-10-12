package org.example.crapsgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.example.crapsgame.model.Dice;
import org.example.crapsgame.model.Game;

import java.util.ArrayList;

public class GameController {

    @FXML
    private Label pointLabel, shootLabel, gamesWonLabel, gamesLostLabel;

    @FXML
    private ImageView dice1ImageView, dice2ImageView;

    private Dice dice1, dice2;
    private Game game;
    private int gamesWon = 0;
    private int gamesLost = 0;

    public GameController() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.game = new Game();
    }

    @FXML
    public void onHandleButtonRollTheDice(ActionEvent event) {
        dice1.rollDice();
        dice2.rollDice();
        dice1ImageView.setImage(dice1.getDiceImage());
        dice2ImageView.setImage(dice2.getDiceImage());

        int shoot = dice1.getValue() + dice2.getValue();
        shootLabel.setText(String.valueOf(shoot));

        // Si el juego ha comenzado
        if (!game.isGameStarting()) {
            if (shoot == 7 || shoot == 11) {
                gamesWon++;
                gamesWonLabel.setText(String.valueOf(gamesWon));
                showAlert("¡Ganaste!", "Has ganado automáticamente con un " + shoot + "!");
                game.resetGame();
            } else if (shoot == 2 || shoot == 3 || shoot == 12) {
                gamesLost++;
                gamesLostLabel.setText(String.valueOf(gamesLost));
                showAlert("¡Perdiste!", "Has perdido automáticamente con un " + shoot + "!");
                game.resetGame();
            } else {
                game.setPoint(shoot);
                pointLabel.setText(String.valueOf(game.getPoint()));
                game.startGame(); // Indica que el juego ha comenzado
            }
        } else {
            // Jugador ya ha establecido un punto
            if (shoot == 7) {
                gamesLost++;
                gamesLostLabel.setText(String.valueOf(gamesLost));
                showAlert("¡Perdiste!", "Has perdido al sacar un 7 antes de tu punto!");
                game.resetGame();
            } else if (shoot == game.getPoint()) {
                gamesWon++;
                gamesWonLabel.setText(String.valueOf(gamesWon));
                showAlert("¡Ganaste!", "Has ganado al sacar tu punto: " + shoot + "!");
                game.resetGame();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
