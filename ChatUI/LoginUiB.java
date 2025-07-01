package ChatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginUiB extends JFrame implements ActionListener {

    JTextField emailField, phoneField;
    JButton loginButton;

    LoginUiB() {
        setLayout(null);
        setTitle("Login - UserB");
        setSize(400, 300);
        setLocation(850, 300);
        getContentPane().setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 80, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 180, 30);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 100, 80, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 100, 180, 30);
        add(phoneField);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 170, 120, 40);
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String phone = phoneField.getText();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT username FROM users WHERE email = ? OR phone = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, phone);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("username");
                if (name != null && !name.isEmpty()) {
                    UserDataB.displayName = name;
                    UserDataB.email = email;
                    UserDataB.phone = phone;
                    this.setVisible(false);
                    new UserB();
                } else {
                    JOptionPane.showMessageDialog(this, "Name not set. Please enter your name.");
                    this.setVisible(false);
                    UserDataB.email = email;
                    UserDataB.phone = phone;
                    new NamePageB(email, phone);
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found. Please register first.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "DB Error: " + ex.getMessage());
        }
    }
}
