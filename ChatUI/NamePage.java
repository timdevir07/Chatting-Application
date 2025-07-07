package ChatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NamePage extends JFrame {
    JTextField nameField;
    JButton readyBtn;

    public NamePage() {
        setTitle("Enter Name");
        setSize(300, 200);
        setLayout(new BorderLayout());

        nameField = new JTextField();
        readyBtn = new JButton("Ready to Chat");

        add(new JLabel("Enter your name:"), BorderLayout.NORTH);
        add(nameField, BorderLayout.CENTER);
        add(readyBtn, BorderLayout.SOUTH);

        readyBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                UserData.displayName = name;
                new UserA();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name");
            }
        });

        setVisible(true);
        setLocation(300, 200);
    }
}
