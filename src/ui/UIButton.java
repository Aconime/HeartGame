package ui;

import javax.swing.*;
import java.awt.*;

public class UIButton {
    // Button Back & Text Color Properties
    private Color fBackgroundColor = new Color(231, 76, 60);
    private Color fTextColor = Color.white;
    private Color txtBackgroundColor = new Color(0, 0, 0, 0);
    private Color txtTextColor = new Color(231, 76, 60);

    // Button Font Properties
    private Font fFont = new Font("Segoe UI", Font.BOLD, 21);
    private Font txtFont = new Font("Segoe UI", Font.BOLD, 19);

    // UI-Based Button - Background Design - (Login & Registration Windows)
    public JButton uiFilledButton(String text) {
        JButton fillBtn = new JButton(text);
        fillBtn.setBorderPainted(false);
        fillBtn.setFocusPainted(false);
        fillBtn.setBackground(fBackgroundColor);
        fillBtn.setForeground(fTextColor);
        fillBtn.setFont(fFont);
        return fillBtn;
    }

    // UI-Based Button - No Background Design - (Login & Registration Windows)
    public JButton uiTextButton(String text) {
        JButton txtBtn = new JButton(text);
        txtBtn.setFocusPainted(false);
        txtBtn.setBorderPainted(false);
        txtBtn.setBackground(txtBackgroundColor);
        txtBtn.setForeground(txtTextColor);
        txtBtn.setFont(txtFont);
        txtBtn.setOpaque(false);
        return txtBtn;
    }

    public JButton uiGameButton(String text) {
        JButton gameBtn = new JButton(text);
        gameBtn.setBorderPainted(false);
        gameBtn.setFocusPainted(false);
        gameBtn.setBackground(fBackgroundColor);
        gameBtn.setForeground(fTextColor);
        gameBtn.setFont(fFont);
        return gameBtn;
    }
}
