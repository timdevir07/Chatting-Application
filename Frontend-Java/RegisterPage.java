import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;

public class RegisterPage extends JFrame {
    JTextField inputField;
    JButton getOtpButton, loginButton;
    JLabel otpLabel, errorLabel;
    String generatedOtp = "";

    public RegisterPage() {
        setTitle("Register - Chat App");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setPreferredSize(new Dimension(250, 35));
        inputField.setText("Enter email or phone number");
        inputField.setForeground(Color.GRAY);

        inputField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Enter email or phone number")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setText("Enter email or phone number");
                    inputField.setForeground(Color.GRAY);
                }
            }
        });

        getOtpButton = createStyledButton("Get OTP");
        otpLabel = new JLabel("", SwingConstants.CENTER);
        otpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        otpLabel.setForeground(new Color(0, 102, 0));

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        loginButton = createStyledButton("Go to Login");

        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.add(inputField);
        centerPanel.add(getOtpButton);
        centerPanel.add(otpLabel);
        centerPanel.add(errorLabel);
        centerPanel.add(loginButton);

        getOtpButton.addActionListener(e -> handleOtpLogic());

        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Login page under development!");
        });

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void handleOtpLogic() {
        String input = inputField.getText().trim();
        if (isValidEmail(input) || isValidPhone(input)) {
            generatedOtp = "123456"; // Dummy OTP
            otpLabel.setText("Your OTP is: " + generatedOtp + " (Valid for 180 sec)");
            askOtpInput();
            errorLabel.setText("");
        } else {
            errorLabel.setText("Please enter a valid email or 10-digit phone number.");
        }
    }

    private void askOtpInput() {
        String enteredOtp = JOptionPane.showInputDialog(this, "Enter OTP:");
        if (enteredOtp != null && enteredOtp.equals(generatedOtp)) {
            dispose();
            new NamePage();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid OTP!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 90, 180), 1),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterPage::new);
    }
}


