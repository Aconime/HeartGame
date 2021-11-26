package ui;

import javax.swing.*;
import java.awt.*;

public class UILabel {
    // Label Text Color Property
    private Color textColor = new Color(85,85,85);

    // Label Font Properties
    private Font font = new Font("Segoe UI", Font.BOLD, 16);
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 36);

    // Label Text Alignment
    private int textAlign = SwingConstants.CENTER;

    // UI-Based Label - (Login & Registration Windows)
    public JLabel uiLabel(String Text) {
        JLabel uiLbl = new JLabel(Text.toUpperCase());
        uiLbl.setForeground(textColor);
        uiLbl.setFont(font);
        uiLbl.setHorizontalAlignment(textAlign);
        return uiLbl;
    }

    // UI-Based Label - (Login & Registration Windows)
    public JLabel titleLabel(String Text) {
        JLabel uiLbl = new JLabel(Text.toUpperCase());
        uiLbl.setForeground(textColor);
        uiLbl.setFont(titleFont);
        uiLbl.setHorizontalAlignment(textAlign);
        return uiLbl;
    }
}
