package gui;

import api.GeneratorAPI;
import lib.Authentication;
import ui.UIButton;
import ui.UIInputFields;
import ui.UILabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterGUI {
    // Constructor with an argument of the JFrame that called it and opened it.
    // This reference of the parent is used to restore the parent window's enabled state after a user is done with this window.
    public RegisterGUI(JFrame parent) {
        // Create Register Window
        JFrame regWindow = new JFrame("HeartGame - Register");

        // Create a new auth object (auth login, reg and uname)
        Authentication auth = new Authentication();

        // Create a new generator object (uname and pwd)
        GeneratorAPI generator = new GeneratorAPI();

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
        unameLbl.setHorizontalAlignment(SwingConstants.LEFT);
        regWindow.add(unameLbl);
        JTextField usernameTxt = new UIInputFields().uiTextField();
        usernameTxt.setBounds(10, 110, 415, 42);
        regWindow.add(usernameTxt);


        // Check if generated username already exists, if it does then try a different username,
        // until it is not found in the database.
        String generatedUsername = "";
        do {
            generatedUsername = generator.generateUsername();
        } while (auth.authenticateUsername(generatedUsername));

        // Username Suggestions Label
        JLabel unameSug = new JLabel("Example: " + generatedUsername);
        unameSug.setBounds(10, 155, 430, 20);
        unameSug.setFont(new Font("Segoe UI", Font.BOLD, 14));
        unameSug.setForeground(new Color(224, 110, 110));
        regWindow.add(unameSug);

        // Username suggestion generator from WebAPI @ - https://api.namefake.com/[options]
        unameSug.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                String generatedUsername = "";
                do {
                    generatedUsername = generator.generateUsername();
                } while (auth.authenticateUsername(generatedUsername));

                unameSug.setText("Example: " + generatedUsername);
            }
        });

        // Email Address Label and Field
        JLabel emailLbl = new UILabel().uiLabel("Enter your E-mail Address");
        emailLbl.setHorizontalAlignment(SwingConstants.LEFT);
        emailLbl.setBounds(10, 182, 430, 30);
        regWindow.add(emailLbl);
        JTextField emailTxt = new UIInputFields().uiTextField();
        emailTxt.setBounds(10, 212, 415, 42);
        regWindow.add(emailTxt);

        // Password Label and Field
        JLabel pwdLbl = new UILabel().uiLabel("Create your Password");
        pwdLbl.setHorizontalAlignment(SwingConstants.LEFT);
        pwdLbl.setBounds(10, 266, 430, 30);
        regWindow.add(pwdLbl);
        JPasswordField pwdTxt = new UIInputFields().uiPasswordField();
        pwdTxt.setBounds(10, 296, 300, 42);
        pwdTxt.setEchoChar((char)0);
        regWindow.add(pwdTxt);

        // Generate Password Button
        JButton genPwd = new UIButton().uiFilledButton("GENERATE");
        genPwd.setBounds(315, 296, 110, 42);
        genPwd.setFont(new Font("Segoe UI", 0, 16));
        genPwd.setBackground(new Color(125,125,125));
        regWindow.add(genPwd);

        // Generate password for User, using the Web API @ - www.passwordrandom.com/[options]
        genPwd.addActionListener(e -> {
            pwdTxt.setText(generator.generatePassword());
        });

        // Checkbox Password Toggle
        JCheckBox togglePwd = new JCheckBox("Show Password");
        togglePwd.setSelected(true);
        togglePwd.setBounds(10, 340, 300, 30);
        togglePwd.setOpaque(false);
        regWindow.add(togglePwd);

        // Show or Hide password characters
        togglePwd.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) pwdTxt.setEchoChar((char)0);
                else pwdTxt.setEchoChar('•');
            }
        });

        // Register Button - Used to perform the authentication of the user's details and create an account
        JButton regButton = new UIButton().uiFilledButton("REGISTER");
        regButton.setBounds(10, 420, 415, 45);
        regWindow.add(regButton);

        regButton.addActionListener(e -> {
            if (!usernameTxt.getText().isEmpty() && !emailTxt.getText().isEmpty() && !String.valueOf(pwdTxt.getPassword()).isEmpty()) {
                int regStatus = auth.authenticateRegistration(usernameTxt.getText(), emailTxt.getText(), String.valueOf(pwdTxt.getPassword()));
                if (regStatus == -1) JOptionPane.showMessageDialog(regWindow, "SQL Error: There was a problem with your connection. Please retry again.", "REGISTER", JOptionPane.ERROR_MESSAGE); // Fatal Error - Database Connection Failed.
                else if (regStatus == -2) JOptionPane.showMessageDialog(regWindow, "The username you have entered already exists. Please choose a different one.", "REGISTER", JOptionPane.ERROR_MESSAGE);
                else if (regStatus == -3) JOptionPane.showMessageDialog(regWindow, "The E-mail address you have entered has been already used. Please use a different E-mail address.", "REGISTER", JOptionPane.ERROR_MESSAGE);
                else if (regStatus == 0) JOptionPane.showMessageDialog(regWindow, "Error UE1: Your account could not be created.", "REGISTER", JOptionPane.ERROR_MESSAGE);
                else if (regStatus == 1) {
                    JOptionPane.showMessageDialog(regWindow, "Your account has been successfully registered.", "REGISTER", JOptionPane.INFORMATION_MESSAGE);
                    parent.setEnabled(true);
                    regWindow.dispose();
                }
            } else JOptionPane.showMessageDialog(regWindow, "Please enter all the required details in the fields to register.", "REGISTER", JOptionPane.ERROR_MESSAGE);
        });

        regWindow.setVisible(true); // Display the window and all child components included
    }
}
