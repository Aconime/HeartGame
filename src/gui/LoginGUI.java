package gui;

import lib.Authentication;
import ui.UIButton;
import ui.UIInputFields;
import ui.UILabel;
import ui.WindowTemplate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginGUI {
    public LoginGUI() {
        // Create Window
        JFrame loginWindow = new WindowTemplate("HEARTGAME - LOGIN", new Dimension(650,650)).createWindow();

        // Add Window Background
        try {
            loginWindow.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/assets/background.png")))));
        } catch (IOException e) { e.printStackTrace(); }

        // Add Window Logo and Icon / Asset(s)
        try {
            BufferedImage mainPicture = ImageIO.read(new File("src/assets/heart-logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(mainPicture.getScaledInstance(375, 320, Image.SCALE_SMOOTH)));
            picLabel.setBounds(142,-90,375,320);
            loginWindow.add(picLabel);
        } catch (IOException ignore) { }

        // Login Section - Title
        JLabel sectionLbl = new UILabel().titleLabel("LOGIN");
        sectionLbl.setBounds(142, 245, 365, 30);
        loginWindow.add(sectionLbl);

        // Username Label and Field for the user to enter data
        JLabel unameLbl = new UILabel().uiLabel("Username");
        unameLbl.setBounds(142, 300, 365, 30);
        loginWindow.add(unameLbl);
        JTextField usernameTxt = new UIInputFields().uiTextField();
        usernameTxt.setBounds(142, 330, 365, 42);
        loginWindow.add(usernameTxt);

        // Password Label and Field for the user to enter data
        JLabel pwdLbl = new UILabel().uiLabel("Password");
        pwdLbl.setBounds(142, 382, 365, 30);
        loginWindow.add(pwdLbl);
        JPasswordField pwdTxt = new UIInputFields().uiPasswordField();
        pwdTxt.setBounds(142, 412, 365, 42);
        loginWindow.add(pwdTxt);

        // Login Button - Used to perform the authentication of the user
        JButton loginButton = new UIButton().uiFilledButton("LOGIN");
        loginButton.setBounds(142, 469, 365, 45);
        loginWindow.add(loginButton);

        // Login Action - Authenticates the data which a user enters and confirms if everything checks out or not
        // Proceed -> GameGUI : If account exists and credentials are correct
        // Display -> ErrorMessage : If account does not exist or credentials are incorrect
        loginButton.addActionListener(e -> {
            if (!usernameTxt.getText().isEmpty() && !String.valueOf(pwdTxt.getPassword()).isEmpty()) {
                Authentication auth = new Authentication();
                int[] loginStatus = auth.authenticateLogin(usernameTxt.getText(), String.valueOf(pwdTxt.getPassword()));
                if (loginStatus[0] == 1) {
                    new GameMenuGUI(loginStatus[1]);
                    loginWindow.dispose();
                }
                else if (loginStatus[0] == 0) System.out.println("Username or Password are NOT correct!");
                else if (loginStatus[0] == -1) System.out.println("Fatal Error - Database Connection Failed.");
            } else System.out.println("Error: Please enter both your username and password to login");
        });

        // Create Account Button -> Used for opening the registration window (frame)
        JButton gotoRegButton = new UIButton().uiTextButton("Create Account");
        gotoRegButton.setBounds(142, 524, 365, 45);
        loginWindow.add(gotoRegButton);

        // Open register window on-top of the current window
        // Disable -> Current window : Until register window is closed
        gotoRegButton.addActionListener(e -> {
            loginWindow.setEnabled(false); // Disable this window as long as register window is open
            new RegisterGUI(loginWindow); // Pass the current JFrame to the register window as an argument to the constructor
        });

        loginWindow.setVisible(true); // Display the window and all child components included
    }
}
