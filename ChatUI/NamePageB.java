package ChatUI;

import java.awt.*;
import javax.swing.*;

public class NamePageB extends JFrame {
    JTextField nameField;
    JButton readyBtn;

    public NamePageB() {
        setTitle("Enter Name - UserB");
        setSize(300, 200);
        setLayout(new BorderLayout());
        setLocation(700, 200);

        nameField = new JTextField();
        readyBtn = new JButton("Ready to Chat");

        add(new JLabel("Enter your name:"), BorderLayout.NORTH);
        add(nameField, BorderLayout.CENTER);
        add(readyBtn, BorderLayout.SOUTH);

        readyBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                UserDataB.displayName = name;
                new UserB();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name");
            }
        });

        setVisible(true);
    }
}
