package gui;

import lib.Authentication;
import ui.UIButton;
import ui.UIInputFields;
import ui.UILabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterGUI {
    // Constructor with an argument of the JFrame that called it and opened it.
    // This reference of the parent is used to restore the parent window's enabled state after a user is done with this window.
    public RegisterGUI(JFrame parent) {
        // Create Register Window
        JFrame regWindow = new JFrame("HEARTGAME - CREATE NEW ACCOUNT");

        // Set Window Properties (Size and Layout)
        regWindow.setSize(450,520);
        regWindow.setAlwaysOnTop(true);
        regWindow.setLayout(null);
        regWindow.getContentPane().setBackground(Color.white); // Set the background color for the window
        regWindow.setLocationRelativeTo(null); // Show Window at the center of the screen
        regWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose of the window once it's closed
        // Actions before the window closes
        regWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setEnabled(true); // Enable the parent window that called this window
            }
        });
        regWindow.setIconImage(new ImageIcon("src/assets/icons/32.png").getImage()); // Set Frame Icon/Logo
        regWindow.setResizable(false); // Make sure the window cannot be resized

        // Register Section - Title
        JLabel sectionLbl = new UILabel().titleLabel("REGISTER");
        sectionLbl.setBounds(10, 25, 430, 30);
        regWindow.add(sectionLbl);

        // Username Label and Field
        JLabel unameLbl = new UILabel().uiLabel("Choose a Username");
        unameLbl.setBounds(10, 80, 430, 30);
        regWindow.add(unameLbl);
        JTextField usernameTxt = new UIInputFields().uiTextField();
        usernameTxt.setBounds(10, 110, 415, 42);
        regWindow.add(usernameTxt);

        // Email Address Label and Field
        JLabel emailLbl = new UILabel().uiLabel("Enter your E-mail Address");
        emailLbl.setBounds(10, 162, 430, 30);
        regWindow.add(emailLbl);
        JTextField emailTxt = new UIInputFields().uiTextField();
        emailTxt.setBounds(10, 192, 415, 42);
        regWindow.add(emailTxt);

        // Password Label and Field
        JLabel pwdLbl = new UILabel().uiLabel("Create your Password");
        pwdLbl.setBounds(10, 244, 430, 30);
        regWindow.add(pwdLbl);
        JPasswordField pwdTxt = new UIInputFields().uiPasswordField();
        pwdTxt.setBounds(10, 276, 415, 42);
        regWindow.add(pwdTxt);

        // Password Confirmation Label and Field
        JLabel rpwdLbl = new UILabel().uiLabel("Confirm your Password");
        rpwdLbl.setBounds(10, 328, 430, 30);
        regWindow.add(rpwdLbl);
        JPasswordField rpwdTxt = new UIInputFields().uiPasswordField();
        rpwdTxt.setBounds(10, 358, 415, 42);
        regWindow.add(rpwdTxt);

        // Register Button - Used to perform the authentication of the user's details and create an account
        JButton regButton = new UIButton().uiFilledButton("REGISTER");
        regButton.setBounds(10, 420, 415, 45);
        regWindow.add(regButton);

        regButton.addActionListener(e -> {
            if (!usernameTxt.getText().isEmpty() && !emailTxt.getText().isEmpty() && !String.valueOf(pwdTxt.getPassword()).isEmpty()) {
                if (String.valueOf(pwdTxt.getPassword()).equals(String.valueOf(rpwdTxt.getPassword()))) {
                    Authentication auth = new Authentication();
                    int regStatus = auth.authenticateRegistration(usernameTxt.getText(), emailTxt.getText(), String.valueOf(pwdTxt.getPassword()));
                    if (regStatus == -1) System.out.println("Fatal Error: Unknown Error - Database MySQL Connection Issue");
                    else if (regStatus == -2) System.out.println("Error: Username already exists");
                    else if (regStatus == -3) System.out.println("Error: Email already registered on a different account");
                    else if (regStatus == 0) System.out.println("Error: Your account could not be registered - Unknown Error");
                    else if (regStatus == 1) System.out.println("Account Registered Successfully!");
                } else System.out.println("Error: Your password confirmation does not match your initial password.");
            } else System.out.println("Error: Please fill in all the required fields");
        });

        regWindow.setVisible(true); // Display the window and all child components included
    }
}
