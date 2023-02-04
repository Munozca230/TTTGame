package com.example.ttt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Arrays;


public class TTTController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Text winnerText;

    @FXML

    private String currentPlayer="X";

    ArrayList<Button> buttons;

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(button -> {
            button.setOnAction(event -> handleButtonClick(button));
        });
    }

    private void changePlayerTurn() {
        currentPlayer=currentPlayer.equals("X") ? "O" : "X";
    }

    public void handleButtonClick(Button button) {
        button.setText(currentPlayer);
        button.setDisable(true);
        changePlayerTurn();
        isGameOver();
    }

    public void restartGame(){
        buttons.forEach(this::resetButton);
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
        winnerText.setText("Tic-Tac-Toe");
    }

    public void isGameOver() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                winnerText.setText("X won!");
            }

            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O won!");
            }
        }
    }


}