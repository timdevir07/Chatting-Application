import java.awt.*;
import javax.swing.*;

public class RegisterPage extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;

    public RegisterPage() {
        setTitle("User Registration");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("Register");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton registerBtn = new JButton("Register");
        JButton loginBtn = new JButton("Go to Login");

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // TODO: backend integration yahan karein
            JOptionPane.showMessageDialog(this, "Registered: " + username);
        });

        loginBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "plz wait (Next Step)");
        });

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(heading);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);

        JPanel btnPanel = new JPanel();
        btnPanel.add(registerBtn);
        btnPanel.add(loginBtn);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterPage();
    }
}



