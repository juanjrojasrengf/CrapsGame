package org.example.crapsgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.example.crapsgame.model.Dice;
import org.example.crapsgame.model.Game;
import org.example.crapsgame.view.alert.AlertBox;
import org.example.crapsgame.view.alert.IAlertBox;

import java.util.ArrayList;

public class GameController {

    @FXML
    private Label pointLabel, shootLabel;

    @FXML
    private ImageView dice1ImageView, dice2ImageView;

    Dice dice1, dice2;
    ArrayList<Game> games = new ArrayList<Game>();

    public GameController() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
    }

    @FXML
    public void onHandleButtonRollTheDice(ActionEvent event) {
        this.dice1.rollDice();
        this.dice2.rollDice();
        this.dice1ImageView.setImage(this.dice1.getDiceImage());
        this.dice2ImageView.setImage(this.dice2.getDiceImage());
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

}
