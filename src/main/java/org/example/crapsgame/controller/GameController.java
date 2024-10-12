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
    Game game = new Game();
    ArrayList<Game> games = new ArrayList<Game>();

    public GameController() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
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
        this.game.setShootCount();
        this.dice1.rollDice();
        this.dice2.rollDice();
        this.dice1ImageView.setImage(this.dice1.getDiceImage());
        this.dice2ImageView.setImage(this.dice2.getDiceImage());


        int totalValue = this.dice1.getValue() + this.dice2.getValue();

        if (this.game.getShootCount() == 1 ){
            if (totalValue == 7 || totalValue == 11){
                this.game.setWinner();
                System.out.println("GANASTE!");
            }
            else if (totalValue == 2 || totalValue == 3 || totalValue == 12){
                this.game.setLoser();
                System.out.println("PERDISTE!");
            }
            else{
                this.game.setPoint(totalValue);
                System.out.println("Tu punto es: " + totalValue);
            }
        }
        else{
            if (totalValue != 7){
                if (this.game.getPoint() == totalValue){
                    this.game.setWinner();
                    System.out.println("GANASTE");
                }
            }
            else{
                this.game.setLoser();
                System.out.println("PERDISTE");
            }
        }
    }
}