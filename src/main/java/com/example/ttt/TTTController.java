package com.example.ttt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


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

    private String currentPlayer = "X";

    private String color = "#3FF300";

    private String audioButtonClick = "src/main/resources/assets/clickButton.wav";

    private String audioWinner = "src/main/resources/assets/winnerSound.wav";

    private String audioLose = "src/main/resources/assets/loseSound.wav";

    ArrayList<Button> buttons;

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            button.setOnAction(event -> handleButtonClick(button));
        });
    }

    private void changePlayerTurn() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public void handleButtonClick(Button button) {
        button.setText(currentPlayer);
        runAudio(audioButtonClick);
        button.setDisable(true);
        changePlayerTurn();
        isDraw();
        isGameOver();
    }

    public void restartGame() {
        buttons.forEach(this::resetButton);
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
        button.setStyle("white");
        winnerText.setText("Tic-Tac-Toe");
    }

    public void disableButtons(){
        buttons.forEach(button -> button.setDisable(true));
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
                runAudio(audioWinner);
                drawWinningLine(a);
                disableButtons();
            }

            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O won!");
                runAudio(audioWinner);
                drawWinningLine(a);
                disableButtons();
            }
        }
    }

    public void drawWinningLine(Integer a) {
        switch (a) {
            case 0 ->{
                button1.setStyle("-fx-background-color: "+color);
                button2.setStyle("-fx-background-color: "+color);
                button3.setStyle("-fx-background-color: "+color);
            }
            case 1 -> {
                button4.setStyle("-fx-background-color: "+color);
                button5.setStyle("-fx-background-color: "+color);
                button6.setStyle("-fx-background-color: "+color);
            }
            case 2 -> {
                button7.setStyle("-fx-background-color: "+color);
                button8.setStyle("-fx-background-color: "+color);
                button9.setStyle("-fx-background-color: "+color);
            }
            case 3 -> {
                button1.setStyle("-fx-background-color: "+color);
                button5.setStyle("-fx-background-color: "+color);
                button9.setStyle("-fx-background-color: "+color);
            }
            case 4 -> {
                button3.setStyle("-fx-background-color: "+color);
                button5.setStyle("-fx-background-color: "+color);
                button7.setStyle("-fx-background-color: "+color);
            }
            case 5 -> {
                button1.setStyle("-fx-background-color: "+color);
                button4.setStyle("-fx-background-color: "+color);
                button7.setStyle("-fx-background-color: "+color);
            }
            case 6 -> {
                button2.setStyle("-fx-background-color: "+color);
                button5.setStyle("-fx-background-color: "+color);
                button8.setStyle("-fx-background-color: "+color);
            }
            case 7 -> {
                button3.setStyle("-fx-background-color: "+color);
                button6.setStyle("-fx-background-color: "+color);
                button9.setStyle("-fx-background-color: "+color);
            }
        }
    }

    public void isDraw() {
        int count = 0;
        for (Button button : buttons) {
            count += button.getText().length();
        }
        if (count == 9) {
            winnerText.setText("Draw :(");
            runAudio(audioLose);
        }
    }

    public void runAudio(String audioPath) {
        try {
            File audioFile = new File(audioPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
