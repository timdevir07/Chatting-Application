// import java.awt.*;
// import java.awt.event.*;
// import java.util.regex.*;
// import javax.swing.*;

// public class LoginPage extends JFrame {
//     JTextField inputField;
//     JPasswordField passwordField;
//     JLabel errorLabel;

//     public LoginPage() {
//         setTitle("Login - Chat App");
//         setSize(400, 320);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         JLabel title = new JLabel("Login", SwingConstants.CENTER);
//         title.setFont(new Font("Arial", Font.BOLD, 24));
//         title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

//         inputField = new JTextField();
//         inputField.setFont(new Font("Arial", Font.PLAIN, 16));
//         inputField.setText("Enter email or phone number");
//         inputField.setForeground(Color.GRAY);

//         inputField.addFocusListener(new FocusAdapter() {
//             public void focusGained(FocusEvent e) {
//                 if (inputField.getText().equals("Enter email or phone number")) {
//                     inputField.setText("");
//                     inputField.setForeground(Color.BLACK);
//                 }
//             }
//             public void focusLost(FocusEvent e) {
//                 if (inputField.getText().isEmpty()) {
//                     inputField.setForeground(Color.GRAY);
//                     inputField.setText("Enter email or phone number");
//                 }
//             }
//         });

//         passwordField = new JPasswordField();
//         passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
//         passwordField.setEchoChar('•');

//         JButton loginBtn = createStyledButton("Login");
//         JButton registerBtn = createStyledButton("Go to Register");

//         errorLabel = new JLabel("", SwingConstants.CENTER);
//         errorLabel.setForeground(Color.RED);

//         loginBtn.addActionListener(e -> {
//             String input = inputField.getText().trim();
//             String password = new String(passwordField.getPassword()).trim();

//             if ((isValidEmail(input) || isValidPhone(input)) && !password.isEmpty()) {
//                 JOptionPane.showMessageDialog(this, "Logged in successfully!");
//                 // TODO: Navigate to Chat page
//             } else {
//                 errorLabel.setText("Invalid credentials. Try again.");
//             }
//         });

//         registerBtn.addActionListener(e -> {
//             dispose();
//             new RegisterPage(); //RegisterPage class call ,, call the Reg page
//         });

//         JPanel form = new JPanel(new GridLayout(5, 1, 10, 10));
//         form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
//         form.add(inputField);
//         form.add(passwordField);
//         form.add(errorLabel);
//         form.add(loginBtn);
//         form.add(registerBtn);

//         add(title, BorderLayout.NORTH);
//         add(form, BorderLayout.CENTER);
//         setVisible(true);
//     }

//     private boolean isValidEmail(String email) {
//         return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", email);
//     }

//     private boolean isValidPhone(String phone) {
//         return phone.matches("\\d{10}");
//     }

//     private JButton createStyledButton(String text) {
//         JButton btn = new JButton(text);
//         btn.setFocusPainted(false);
//         btn.setFont(new Font("Arial", Font.BOLD, 14));
//         btn.setBackground(new Color(52, 152, 219));
//         btn.setForeground(Color.WHITE);
//         btn.setBorder(BorderFactory.createCompoundBorder(
//             BorderFactory.createLineBorder(new Color(41, 128, 185), 1),
//             BorderFactory.createEmptyBorder(8, 16, 8, 16)
//         ));
//         btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//         btn.setOpaque(true);
//         return btn;
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(LoginPage::new);
//     }
// }

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    JTextField inputField, otpField;
    JButton getOtpBtn, loginBtn, registerBtn;
    JLabel errorLabel;
    String generatedOtp = "";

    public LoginPage() {
        setTitle("Login - Chat App");
        setSize(420, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Input Field
        inputField = new JTextField("Enter email or phone number");
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        inputField.setForeground(Color.GRAY);
        inputField.setPreferredSize(new Dimension(250, 35));
        inputField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Enter email or phone number")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText("Enter email or phone number");
                }
            }
        });

        // OTP Field
        otpField = new JTextField("Enter OTP");
        otpField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        otpField.setForeground(Color.GRAY);
        otpField.setPreferredSize(new Dimension(250, 35));
        otpField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (otpField.getText().equals("Enter OTP")) {
                    otpField.setText("");
                    otpField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (otpField.getText().isEmpty()) {
                    otpField.setForeground(Color.GRAY);
                    otpField.setText("Enter OTP");
                }
            }
        });

        // Error Label
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);

        // Buttons
        getOtpBtn = create3DButton("Get OTP");
        loginBtn = create3DButton("Enter OTP to Login");
        registerBtn = create3DButton("Go to Register");

        // OTP Button Action
        getOtpBtn.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (isValidEmail(input) || isValidPhone(input)) {
                generatedOtp = String.valueOf(100000 + (int)(Math.random() * 900000));
                JOptionPane.showMessageDialog(this, "OTP: " + generatedOtp); // Mock show
                errorLabel.setText("OTP valid for 180 seconds (mock)");
            } else {
                errorLabel.setText("Enter a valid email or 10-digit phone.");
            }
        });

        // Login Button Action
        loginBtn.addActionListener(e -> {
            String userOtp = otpField.getText().trim();
            if (userOtp.equals(generatedOtp)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new NamePage(); // Go to name page
            } else {
                errorLabel.setText("Invalid OTP!");
            }
        });

        // Register Navigation
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterPage(); // Go to registration page
        });

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.add(inputField);
        centerPanel.add(getOtpBtn);
        centerPanel.add(otpField);
        centerPanel.add(errorLabel);
        centerPanel.add(loginBtn);
        centerPanel.add(registerBtn);

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$", email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    private JButton create3DButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}


