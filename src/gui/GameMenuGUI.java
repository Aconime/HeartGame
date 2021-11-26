package gui;

import player.PlayerData;
import ui.UIButton;
import ui.WindowTemplate;

import javax.swing.*;
import java.awt.*;

public class GameMenuGUI {
    public GameMenuGUI(int playerId) {
        // Create instance of GameData
        PlayerData playerData = new PlayerData();

        // Create new window using the template from the UI
        JFrame menuWindow = new WindowTemplate("HEARTGAME - Game Menu", new Dimension(650, 650)).createWindow();

        // Header of application
        JPanel header = new JPanel();
        header.setBounds(0, 0, 800, 60);
        header.setLayout(null);
        header.setBackground(new Color(240, 240, 240));
        menuWindow.add(header);

        // Username display
        JLabel player = new JLabel(playerData.getPlayerUsername(playerId).toUpperCase());
        player.setFont(new Font("Segoe UI", 0, 18));
        player.setBounds(10, 8, 150, 20);
        header.add(player);

        // Display user rank under username
        JLabel playerRank = new JLabel(playerData.getPlayerRankName(playerId).toUpperCase());
        playerRank.setFont(new Font("Segoe UI", 0, 11));
        playerRank.setBounds(11, 34, 200, 20);
        header.add(playerRank);

        // Option Menu Wrapper Panel
        JPanel optionWrapper = new JPanel();
        optionWrapper.setLayout(null);
        optionWrapper.setBackground(Color.white);
        optionWrapper.setBounds(140, 120, 385, 240);
        menuWindow.add(optionWrapper);

        // Play New Game Option
        JButton newGameButton = new UIButton().uiFilledButton("PLAY NEW GAME");
        newGameButton.setBounds(10, 10, 365, 45);
        optionWrapper.add(newGameButton);

        // Play New Game Option Event -> Open new window with the game and let user play
        newGameButton.addActionListener(e -> {
            menuWindow.setEnabled(false);
            new GameGUI(menuWindow, playerId);
        });

        JButton profileButton = new UIButton().uiFilledButton("PLAYER PROFILE");
        profileButton.setBounds(10, 65, 365, 45);
        optionWrapper.add(profileButton);

        JButton leaderboardButton = new UIButton().uiFilledButton("LEADERBOARD");
        leaderboardButton.setBounds(10, 120, 365, 45);
        optionWrapper.add(leaderboardButton);

        // Logout Option
        JButton logoutButton = new UIButton().uiFilledButton("LOGOUT");
        logoutButton.setBounds(10, 175, 365, 45);
        optionWrapper.add(logoutButton);

        // Logout Event -> Function to close menu and open LoginGUI
        logoutButton.addActionListener(e -> {
            int msgRes = JOptionPane.showConfirmDialog(menuWindow, "Are you sure you want to logout?", "Logout?", JOptionPane.YES_NO_OPTION);
            if (msgRes == 0) {
                new LoginGUI();
                menuWindow.dispose();
            }
        });

        menuWindow.setVisible(true);
    }
}
