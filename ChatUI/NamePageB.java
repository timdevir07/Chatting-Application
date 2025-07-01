package ChatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NamePageB extends JFrame implements ActionListener {

    JTextField nameField;
    JButton readyButton;

    NamePageB(String email, String phone) {
        setLayout(null);
        setTitle("Enter Name - UserB");
        setSize(400, 300);
        setLocation(850, 300);
        getContentPane().setBackground(Color.WHITE);

        JLabel label = new JLabel("Enter your name:");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setBounds(50, 50, 200, 30);
        add(label);

        nameField = new JTextField();
        nameField.setBounds(50, 100, 250, 30);
        add(nameField);

        readyButton = new JButton("Ready to Chat");
        readyButton.setBounds(100, 170, 150, 40);
        readyButton.addActionListener(this);
        add(readyButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
            return;
        }

        // DB insert
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE users SET username = ? WHERE email = ? OR phone = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, UserDataB.email);
            pst.setString(3, UserDataB.phone);
            int rows = pst.executeUpdate();

            if (rows > 0) {
                UserDataB.displayName = name;
                this.setVisible(false);
                new UserB();
            } else {
                JOptionPane.showMessageDialog(this, "User not found to update name.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB Error: " + ex.getMessage());
        }
    }
}
