package userb;
import db.ChatAppDB;
import model.UserDataB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.regex.Pattern;

public class RegisterPageB extends JFrame {
    JTextField inputField;
    JButton getOtpButton;
    JLabel otpLabel, errorLabel;
    String generatedOtp = "";
    boolean isNewUser;

    public RegisterPageB() {
        setTitle("Register - UserB");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        inputField = new JTextField("Enter email or phone");
        inputField.setForeground(Color.GRAY);

        inputField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Enter email or phone")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setText("Enter email or phone");
                    inputField.setForeground(Color.GRAY);
                }
            }
        });

        getOtpButton = new JButton("Get OTP");
        otpLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        getOtpButton.addActionListener(e -> handleOtp());

        add(new JLabel("Register - UserB", SwingConstants.CENTER));
        add(inputField);
        add(getOtpButton);
        add(otpLabel);
        add(errorLabel);

        setVisible(true);
    }

    private void handleOtp() {
        String input = inputField.getText().trim();
        if (isValidEmail(input) || isValidPhone(input)) {
            isNewUser = !ChatAppDB.isUserRegistered(input);
            if (isNewUser) {
                ChatAppDB.registerUser(input);
            }
            generatedOtp = String.valueOf(100000 + new Random().nextInt(900000));
            otpLabel.setText("Your OTP: " + generatedOtp);

            String enteredOtp = JOptionPane.showInputDialog(this, "Enter OTP:");
            if (enteredOtp != null && enteredOtp.equals(generatedOtp)) {
                UserDataB.contactInput = input;
                dispose();
                new NamePageB(isNewUser);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid OTP!");
            }
        } else {
            errorLabel.setText("Invalid email or phone!");
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
