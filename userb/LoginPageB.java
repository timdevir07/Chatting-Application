package userb;

import db.ChatAppDB;
import java.awt.*;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.*;
import model.UserDataB;

public class LoginPageB extends JFrame {
    JTextField inputField;
    JButton loginButton;
    JLabel otpLabel, errorLabel;
    String generatedOtp = "";

    public LoginPageB() {
        setTitle("Login - UserB");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        inputField = new JTextField("Enter email or phone");
        inputField.setForeground(Color.GRAY);

        inputField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (inputField.getText().equals("Enter email or phone")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setText("Enter email or phone");
                    inputField.setForeground(Color.GRAY);
                }
            }
        });

        loginButton = new JButton("Login");
        otpLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        loginButton.addActionListener(e -> handleLogin());

        add(new JLabel("Login - UserB", SwingConstants.CENTER));
        add(inputField);
        add(loginButton);
        add(otpLabel);
        add(errorLabel);

        setVisible(true);
    }

    private void handleLogin() {
        String input = inputField.getText().trim();
        if (isValidEmail(input) || isValidPhone(input)) {
            if (!ChatAppDB.isUserRegistered(input)) {
                errorLabel.setText("User not registered!");
                return;
            }
            generatedOtp = String.valueOf(100000 + new Random().nextInt(900000));
            otpLabel.setText("Your OTP: " + generatedOtp);

            String enteredOtp = JOptionPane.showInputDialog(this, "Enter OTP:");
            if (enteredOtp != null && enteredOtp.equals(generatedOtp)) {
                UserDataB.contactInput = input;
                UserDataB.displayName = ChatAppDB.getUserName(input);
                dispose();
                new UserB();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid OTP!");
            }
        } else {
            errorLabel.setText("Invalid input!");
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
