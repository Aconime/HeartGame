package gui;

import game.GameEngine;
import game.GameLogic;
import ui.UIButton;
import ui.WindowTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class GameGUI {
    private boolean gameFinished = false;

    public GameGUI(JFrame parent, int playerId) {
        // Create new window using the template from the UI
        JFrame gameWindow = new WindowTemplate("HeartGame - Gameplay", new Dimension(700, 650)).createWindow();

        gameWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new GameLogic().gameLost(playerId);
                int cancelGame = JOptionPane.showConfirmDialog(gameWindow, "If you close your game now, you will lose points. Are you sure?", "Cancel Game?", JOptionPane.YES_NO_OPTION);
                if (cancelGame == 0) {
                    parent.setEnabled(true);
                    new GameMenuGUI(playerId);
                    parent.dispose();
                } else gameWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });

        JPanel gameHeader = new JPanel();
        gameHeader.setLayout(null);
        gameHeader.setBackground(new Color(240, 240, 240));
        gameHeader.setBounds(0, 0, 680, 80);
        gameWindow.add(gameHeader);

        // Init GameLogic
        GameLogic gameLogic = new GameLogic();

        JLabel gameLives = new JLabel("Lives: " + gameLogic.gameInitLives() + " / " + gameLogic.gameMaxLives);
        gameLives.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gameLives.setBounds(10, 10, 400, 60);
        gameHeader.add(gameLives);

        // Init GameEngine
        GameEngine gameEngine = new GameEngine();

        try {
            gameEngine.generateGame(); // Generate Game
        } catch (MalformedURLException e) {
            // Close Game if there's an error in generating the game
            JOptionPane.showMessageDialog(gameWindow, "Error NL1: Could not load game due to invalid generation seed.", "Game Engine - Loader", JOptionPane.ERROR_MESSAGE);
            parent.setEnabled(true);
            gameWindow.dispose();
        }

        // Display HeartGame Image from URL.
        ImageIcon heartGameImage = new ImageIcon(gameEngine.getCurrentGame());
        JLabel imageDisplay = new JLabel(heartGameImage);
        imageDisplay.setBounds(10, 100, 660, 360); // Bounds calculated by image width and height (ratio)
        gameWindow.add(imageDisplay);

        // Create all 10 buttons for the answers of the heart game
        int posX = 12;
        for (int i = 0; i <= 9; i++) {
            // Create buttons with properties
            JButton answerBtn = new UIButton().uiFilledButton(String.valueOf(i));
            answerBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            answerBtn.setForeground(Color.white);
            answerBtn.setBounds(posX, 550, 45, 45);

            gameWindow.add(answerBtn); // Add button to the Game Window

            // Add click event for each button in the loop
            answerBtn.addActionListener(e -> {
                // Check if answer is correct
                boolean result = gameEngine.checkGameResults(Integer.parseInt(answerBtn.getText()));
                if (result == false) {
                    // If result is not correct the, update the lives for the current round and check if lives are not 0.
                    // If the lives are 0 then -> Game Over :: Restart or Go To Main Menu
                    int lives = gameLogic.gameUpdateLives();
                    gameLives.setText("Lives: " + lives + " / " + gameLogic.gameMaxLives);
                    answerBtn.setBackground(Color.gray);
                    answerBtn.setEnabled(false);
                    if (lives == 0) {
                        // Perform all the required actions for the lost round (game)
                        int[] gameXPInfo = gameLogic.gameLost(playerId);
                        confirmNextGame(gameWindow, parent, playerId, 0, gameXPInfo);
                    }
                } else {
                    // Perform all the required actions for the won round (game)
                    int[] gameXPInfo = gameLogic.gameWon(playerId);
                    confirmNextGame(gameWindow, parent, playerId, 1, gameXPInfo);
                }
            });

            posX += 68; // Increment button position on the X axis to add buttons in a horizontal layout
        };

        gameWindow.setVisible(true);
    }

    private void confirmNextGame(JFrame game, JFrame gameParent, int gamePlayerId, int gameResults, int[] xpInfo) {
        String messageGameResults = "";

        if (gameResults == 0) messageGameResults = "Game Over! You have failed to find the correct answer. You have lost -" + xpInfo[0] + " XP. You have now " + xpInfo[1] + " XP in total. Would you like to try again?";
        else messageGameResults = "Amazing! You have won this round, congratulations. You have gained +" + xpInfo[0] + " XP. You have now " + xpInfo[1] + " XP in total. Would you like to play again?";

        int playAgain = JOptionPane.showConfirmDialog(game, messageGameResults, "Game Over", JOptionPane.YES_NO_OPTION);
        if (playAgain == 0) {
            // Open new window - start from scratch
            new GameGUI(gameParent, gamePlayerId);
        } else {
            // Enable Main Menu
            gameParent.setEnabled(true);
            new GameMenuGUI(gamePlayerId);
            gameParent.dispose();
        }
        game.dispose(); // Close this window
    }
}
