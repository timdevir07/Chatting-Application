package usera;

import db.ChatAppDB;
import java.awt.*;
import javax.swing.*;
import model.UserData;

public class NamePage extends JFrame {
    JTextField nameField;
    JButton readyButton;
    boolean isNewUser;

    public NamePage(boolean isNewUser) {
        this.isNewUser = isNewUser;

        setTitle("Enter Name - UserA");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        nameField = new JTextField();
        readyButton = new JButton("Ready to Chat");

        readyButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                UserData.displayName = name;
                if (isNewUser) {
                    ChatAppDB.updateUserName(UserData.contactInput, name);
                } else {
                    UserData.displayName = ChatAppDB.getUserName(UserData.contactInput);
                }
                dispose();
                new UserA();
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
