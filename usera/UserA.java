package usera;

import db.ChatAppDB;
import model.UserData;

import javax.swing.*;
import java.awt.*;

public class UserA extends JFrame {
    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    public UserA() {
        setTitle("Chat - " + UserData.displayName);
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
            ChatAppDB.saveMessage(UserData.displayName, "UserB", msg);
            inputField.setText("");
        }
    }
}
