package ui;

import javax.swing.*;
import java.awt.*;

public class WindowTemplate {
    // Create Window
    private JFrame newWindow = new JFrame();

    public WindowTemplate(String windowTitle, Dimension windowSize) {
        // Set Window Properties (Size and Layout)
        newWindow.setTitle(windowTitle);
        newWindow.setSize(windowSize);
        newWindow.setLayout(null);
        newWindow.getContentPane().setBackground(Color.white); // Set the background color for the window
        newWindow.setLocationRelativeTo(null); // Show Window at the center of the screen
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose of the window once it's closed
        newWindow.setIconImage(new ImageIcon("src/assets/icons/32.png").getImage()); // Set Frame Icon/Logo
        newWindow.setResizable(false); // Make sure the window cannot be resized
    }

    // Create the JFrame
    public JFrame createWindow() {
        return newWindow;
    }
}
