package gui;

import game.GameEngine;
import player.PlayerData;
import ui.WindowTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

public class GameGUI {
    private boolean gameFinished = false;

    public GameGUI(JFrame parent, int playerId) {
        // Create new window using the template from the UI
        JFrame gameWindow = new WindowTemplate("HEARTGAME - Gameplay", new Dimension(700, 660)).createWindow();

        gameWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setEnabled(true); // Enable the parent window that called this window once closed
            }
        });

        // Init GameEngine
        GameEngine gameEngine = new GameEngine();

        try {
            gameEngine.generateGame(); // Generate Game
        } catch (MalformedURLException e) { }

        // Display HeartGame Image from URL.
        ImageIcon heartGameImage = new ImageIcon(gameEngine.getCurrentGame());
        JLabel imageDisplay = new JLabel(heartGameImage);
        imageDisplay.setBounds(10, 10, 660, 360); // Bounds calculated by image width and height (ratio)
        gameWindow.add(imageDisplay);

        JTextField answer = new JTextField();
        answer.setBounds(10, 400, 200, 35);
        gameWindow.add(answer);

        JButton answerBtn = new JButton("Check");
        answerBtn.setBounds(10, 450, 200, 40);
        gameWindow.add(answerBtn);

        answerBtn.addActionListener(e -> {
            PlayerData playerData = new PlayerData();
            int experience = 4500;
            int result = gameEngine.checkGameResults(Integer.parseInt(answer.getText()), false);
            if (result == 0) {
                System.out.printf("Lost!");
            } else {
                if (experience < 1500) {
                    experience += 550;
                    System.out.printf("Won - Experience gained: " + experience);
                } else if (experience > 1500 && experience < 4000) {
                    experience += 600;
                    System.out.printf("Won - Experience gained: " + experience);
                } else if (experience > 4000 && experience < 10000) {
                    experience += 800;
                    System.out.printf("Won - Experience gained: " + experience);
                }
            }
        });

        gameWindow.setVisible(true);
    }
}
