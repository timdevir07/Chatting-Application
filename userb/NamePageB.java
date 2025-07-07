package userb;

import db.ChatAppDB;
import java.awt.*;
import javax.swing.*;
import model.UserDataB;

public class NamePageB extends JFrame {
    JTextField nameField;
    JButton readyButton;
    boolean isNewUser;

    public NamePageB(boolean isNewUser) {
        this.isNewUser = isNewUser;

        setTitle("Enter Name - UserB");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        nameField = new JTextField();
        readyButton = new JButton("Ready to Chat");

        readyButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                UserDataB.displayName = name;
                if (isNewUser) {
                    ChatAppDB.updateUserName(UserDataB.contactInput, name);
                } else {
                    UserDataB.displayName = ChatAppDB.getUserName(UserDataB.contactInput);
                }
                dispose();
                new UserB();
            } else {
                JOptionPane.showMessageDialog(this, "Enter a name!");
            }
        });

        add(new JLabel("Enter Your Name:", SwingConstants.CENTER));
        add(nameField);
        add(readyButton);

        setVisible(true);
    }
}
