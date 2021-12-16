package gui;

import player.PlayerGetData;
import ui.UIButton;
import ui.WindowTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;

public class ProfileGUI {
    public ProfileGUI(JFrame parent, int playerId) {
        JFrame profileWindow = new WindowTemplate("HeartGame - My Profile", new Dimension(480,350)).createWindow();

        profileWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.setEnabled(true);
            }
        });

        PlayerGetData playerData = new PlayerGetData();
        String uname = playerData.getPlayerUsername(playerId);
        String email = playerData.getPlayerEmail(playerId);
        String rank = playerData.getPlayerRank(playerId);
        int xpMax = playerData.getPlayerXPMax(playerId);
        int w_games = playerData.getPlayerWins(playerId);
        int l_games = playerData.getPlayerLosses(playerId);
        int xp = playerData.getPlayerXP(playerId);
        int gp = playerData.getPlayerGP(playerId);

        JLabel usernameLbl = new JLabel(uname.toUpperCase());
        usernameLbl.setBounds(10, 10, 300, 30);
        usernameLbl.setForeground(new Color(45, 45,45));
        usernameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        profileWindow.add(usernameLbl);

        JLabel emailLbl = new JLabel(email.toLowerCase());
        emailLbl.setBounds(10, 35, 300, 40);
        emailLbl.setForeground(new Color(65,65,65));
        emailLbl.setFont(new Font("Segoe UI", 0, 14));
        profileWindow.add(emailLbl);

        JPanel profileSeparator = new JPanel();
        profileSeparator.setBackground(new Color(235, 235, 235));
        profileSeparator.setBounds(10, 75, 440, 2);
        profileWindow.add(profileSeparator);

        JLabel playerRankLbl = new JLabel("Rank: [" + rank + "]");
        playerRankLbl.setBounds(10, 85, 400, 20);
        playerRankLbl.setFont(new Font("Segoe UI", 0, 14));
        profileWindow.add(playerRankLbl);

        JLabel playerXPLbl = new JLabel("XP: [" + xp + " / " + xpMax + "]");
        playerXPLbl.setBounds(10, 105, 400, 20);
        playerXPLbl.setFont(new Font("Segoe UI", 0, 14));
        profileWindow.add(playerXPLbl);

        JLabel playerGW = new JLabel("Games Won: [" + w_games + " / " + gp + "]");
        playerGW.setBounds(10, 125, 400, 20);
        playerGW.setFont(new Font("Segoe UI", 0, 14));
        profileWindow.add(playerGW);

        JLabel playerGL = new JLabel("Games Lost: [" + l_games + " / " + gp + "]");
        playerGL.setBounds(10, 145, 400, 20);
        playerGL.setFont(new Font("Segoe UI", 0, 14));
        profileWindow.add(playerGL);

        JButton closeProfileBtn = new UIButton().uiFilledButton("Close");
        closeProfileBtn.setBounds(10, 260, 440, 35);
        closeProfileBtn.setFont(new Font("Segoe UI", 0, 16));
        profileWindow.add(closeProfileBtn);

        closeProfileBtn.addActionListener(e -> {
            parent.setEnabled(true);
            profileWindow.dispose();
        });

        profileWindow.setVisible(true);
    }
}
