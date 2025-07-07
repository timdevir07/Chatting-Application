package userb;

import db.ChatAppDB;
import model.UserDataB;

import javax.swing.*;
import java.awt.*;

public class UserB extends JFrame {
    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    public UserB() {
        setTitle("Chat - " + UserDataB.displayName);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty()) {
            chatArea.append("Me: " + msg + "\n");
            ChatAppDB.saveMessage(UserDataB.displayName, "UserA", msg);
            inputField.setText("");
        }
    }
}
