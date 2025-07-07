package ChatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RegisterPage extends JFrame {
    JTextField phoneField, emailField;
    JButton getOtpBtn, nextBtn;
    JLabel phoneOtpLabel, emailOtpLabel;
    String phoneOtp = "";
    String emailOtp = "";

    public RegisterPage() {
        setTitle("UserA Registration");
        setSize(300, 400);
        setLayout(new GridLayout(8, 1, 5, 5));
        setLocation(300, 200);

        phoneField = new JTextField();
        emailField = new JTextField();
        getOtpBtn = new JButton("Get OTP");
        nextBtn = new JButton("Next");

        phoneOtpLabel = new JLabel();
        emailOtpLabel = new JLabel();

        add(new JLabel("Phone Number:"));
        add(phoneField);
        add(new JLabel("Email ID:"));
        add(emailField);
        add(getOtpBtn);
        add(phoneOtpLabel);
        add(emailOtpLabel);
        add(nextBtn);

        getOtpBtn.setBackground(new Color(7, 94, 84));
        getOtpBtn.setForeground(Color.WHITE);
        nextBtn.setBackground(new Color(7, 94, 84));
        nextBtn.setForeground(Color.WHITE);

        getOtpBtn.addActionListener(e -> generateAndShowOtp());

        nextBtn.addActionListener(e -> {
            if (!phoneOtp.isEmpty() && !emailOtp.isEmpty()) {
                new NamePage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please generate OTP first.");
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

    public static void main(String[] args) {
        new RegisterPage();
    }
}
