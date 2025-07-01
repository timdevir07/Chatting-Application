import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.regex.*;
import javax.swing.*;

public class RegisterPage extends JFrame {
    JTextField inputField;
    JButton getOtpButton;
    JLabel otpLabel, errorLabel;
    String generatedOtp = "";
    String currentInput;
    boolean isNew;

    public RegisterPage() {
        setTitle("Register - Chat App");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        inputField = new JTextField("Enter email or phone number");
        inputField.setForeground(Color.GRAY);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
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

        getOtpButton = createButton("Get OTP");
        otpLabel = new JLabel("", SwingConstants.CENTER);
        otpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        otpLabel.setForeground(new Color(0, 102, 0));

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.add(inputField);
        centerPanel.add(getOtpButton);
        centerPanel.add(otpLabel);
        centerPanel.add(errorLabel);

        getOtpButton.addActionListener(e -> handleOtpLogic());

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleOtpLogic() {
        currentInput = inputField.getText().trim();
        if (isValidEmail(currentInput) || isValidPhone(currentInput)) {
            isNew = !ChatAppDB.isUserRegistered(currentInput);
            if (isNew) {
                ChatAppDB.registerUser(currentInput);
            }
            generatedOtp = String.valueOf(100000 + new Random().nextInt(900000));
            otpLabel.setText("Your OTP: " + generatedOtp);
            errorLabel.setText("");
            askOtpInput();
        } else {
            errorLabel.setText("Please enter valid email or phone.");
        }
    }

    private void askOtpInput() {
        String enteredOtp = JOptionPane.showInputDialog(this, "Enter OTP:");
        if (enteredOtp != null && enteredOtp.equals(generatedOtp)) {
            dispose();
            new NamePage(currentInput, isNew);
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

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterPage::new);
    }
}
