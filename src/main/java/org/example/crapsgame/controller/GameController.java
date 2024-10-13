package org.example.crapsgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.example.crapsgame.model.Dice;
import org.example.crapsgame.view.alert.AlertBox;
import org.example.crapsgame.view.alert.IAlertBox;
import org.example.crapsgame.model.Game;

public class GameController {

    @FXML
    private Label pointLabel, shootLabel, gamesWonLabel, gamesLostLabel;

    @FXML
    private ImageView dice1ImageView, dice2ImageView;

    private Game game;
    private int gamesWon = 0;
    private int gamesLost = 0;

    public GameController() {
        this.game = new Game();
    }

    @FXML
    public void onHelpButtonClicked(ActionEvent event) {
        IAlertBox alertBox = new AlertBox();
        String instructions = "Instrucciones del juego:\n" +
                "1. Si en el primer tiro sacas un 7 u 11, ganas automáticamente.\n" +
                "2. Si sacas un 2, 3 o 12, pierdes automáticamente.\n" +
                "3. Si sacas cualquier otro número, ese número será tu 'Punto'.\n" +
                "4. Sigue lanzando hasta que saques el Punto o un 7. Si sacas el Punto antes que un 7, ganas.\n" +
                "5. Si sacas un 7 antes que el Punto, pierdes.";
        alertBox.showMessage("Ayuda", "Reglas del juego Craps", instructions);
    }


    @FXML
    public void onHandleButtonRollTheDice(ActionEvent event) {
        int shoot = game.rollDices();
        shootLabel.setText(String.valueOf(shoot));
        dice1ImageView.setImage(game.dice1.getDiceImage());
        dice2ImageView.setImage(game.dice2.getDiceImage());

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
