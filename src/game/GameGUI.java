package game;

import engine.GameData;
import ui.WindowTemplate;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    public GameGUI(int playerId) {
        JFrame gameWindow = new WindowTemplate("HEARTGAME - MENU", new Dimension(500, 500)).createWindow();

        JPanel header = new JPanel();
        header.setBounds(0, 0, 800, 40);
        header.setLayout(null);
        header.setBackground(new Color(240, 240, 240));
        gameWindow.add(header);

        JLabel player = new JLabel(new GameData().getPlayerDetails(playerId)[0]);
        player.setBounds(10, 10, 150, 20);
        header.add(player);

        gameWindow.setVisible(true);
    }
}
