package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UIInputFields {
    // Field Border Properties (State)
    private LineBorder defaultBorder = new LineBorder(new Color(125,125,125), 3, true);
    private LineBorder focusedBorder = new LineBorder(new Color(231, 76, 60), 3, true);

    // Field Back & Text Color Properties
    private Color backgroundColor = Color.white;
    private Color textColor = new Color(55,55,55);

    // Field Font Property
    private Font font = new Font("Arial", Font.PLAIN, 18);

    // Field Text Alignment
    private int textAlign = SwingConstants.CENTER;

    // Register Focus Listener (Focus Gained - Focus Lost)
    // Used to give a highlight effect on the selected input field. Used for improved design and user experience.
    // Changes the border color of the input box to give a highlight effect.
    public final FocusListener fieldFocusListener = new FocusListener() {
        @Override // Field focused / selected
        public void focusGained(FocusEvent e) {
            JTextField selectedTextField = (JTextField) e.getComponent();
            selectedTextField.setBorder(focusedBorder);
        }

        @Override // Field no longer focused
        public void focusLost(FocusEvent e) {
            JTextField selectedTextField = (JTextField) e.getComponent();
            selectedTextField.setBorder(defaultBorder);
        }
    };

    // UI Text Field - TextBased
    public JTextField uiTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(defaultBorder);
        textField.setBackground(backgroundColor);
        textField.setForeground(textColor);
        textField.setFont(font);
        textField.setHorizontalAlignment(textAlign);
        textField.addFocusListener(fieldFocusListener);
        return textField;
    }

    // UI Password Field - PasswordBased
    public JPasswordField uiPasswordField() {
        JPasswordField pwdField = new JPasswordField();
        pwdField.setBorder(defaultBorder);
        pwdField.setBackground(backgroundColor);
        pwdField.setForeground(textColor);
        pwdField.setFont(font);
        pwdField.setHorizontalAlignment(textAlign);
        pwdField.addFocusListener(fieldFocusListener);
        return pwdField;
    }
}
