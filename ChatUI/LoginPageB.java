package ChatUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class LoginPageB extends JFrame {
    JTextField phoneField, emailField;
    JButton getOtpBtn, loginBtn;
    JLabel phoneOtpLabel, emailOtpLabel;
    String phoneOtp = "";
    String emailOtp = "";

    public LoginPageB() {
        setTitle("UserB Login");
        setSize(300, 400);
        setLocation(700, 200);
        setLayout(new GridLayout(8, 1, 5, 5));

        phoneField = new JTextField();
        emailField = new JTextField();
        getOtpBtn = new JButton("Get OTP");
        loginBtn = new JButton("Login");

        phoneOtpLabel = new JLabel();
        emailOtpLabel = new JLabel();

        add(new JLabel("Phone Number:"));
        add(phoneField);
        add(new JLabel("Email ID:"));
        add(emailField);
        add(getOtpBtn);
        add(phoneOtpLabel);
        add(emailOtpLabel);
        add(loginBtn);

        getOtpBtn.setBackground(new Color(7, 94, 84));
        getOtpBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(7, 94, 84));
        loginBtn.setForeground(Color.WHITE);

        getOtpBtn.addActionListener(e -> generateAndShowOtp());

        loginBtn.addActionListener(e -> verifyAndLogin());

        emailField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    verifyAndLogin();
                }
            }
        });

        setVisible(true);
    }

    void generateAndShowOtp() {
        phoneOtp = generateOtp();
        emailOtp = generateOtp();

        phoneOtpLabel.setText("Phone OTP: " + phoneOtp);
        emailOtpLabel.setText("Email OTP: " + emailOtp);
    }

    String generateOtp() {
        Random rand = new Random();
        int otp = 1000 + rand.nextInt(9000);
        return String.valueOf(otp);
    }

    void verifyAndLogin() {
        if (!phoneOtp.isEmpty() && !emailOtp.isEmpty()) {
            new NamePageB();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please generate OTP first.");
        }
    }

    public static void main(String[] args) {
        new LoginPageB();
    }
}
