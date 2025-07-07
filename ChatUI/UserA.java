package ChatUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class UserA extends JFrame {

    JPanel chatPanel;
    JTextField inputField;
    JButton sendButton;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    static String displayName = UserData.displayName;

    UserA() {
        setTitle("Chat - " + displayName);
        setSize(400, 600);
        setLocation(200, 100);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(7, 94, 84));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel(displayName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        topPanel.add(nameLabel);
        add(topPanel, BorderLayout.NORTH);

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(7, 94, 84));
        sendButton.setForeground(Color.WHITE);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(6001);
            Socket socket = serverSocket.accept();

            DataInputStream din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                addReceivedMessage(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendMessage() {
        try {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty()) {
                addSentMessage(msg);
                dout.writeUTF(msg);
                inputField.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addSentMessage(String msg) {
        JPanel messagePanel = formatLabel(msg, true);
        chatPanel.add(messagePanel);
        vertical.add(Box.createVerticalStrut(10));
        chatPanel.add(vertical);
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    void addReceivedMessage(String msg) {
        JPanel messagePanel = formatLabel(msg, false);
        chatPanel.add(messagePanel);
        vertical.add(Box.createVerticalStrut(10));
        chatPanel.add(vertical);
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    JPanel formatLabel(String msg, boolean sentByUserA) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel("<html><p style='width:150px'>" + msg + "</p></html>");
        messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        messageLabel.setBackground(sentByUserA ? new Color(37, 211, 102) : new Color(220, 248, 198));
        messageLabel.setOpaque(true);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(new Date());
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));

        if (sentByUserA) {
            panel.add(messageLabel);
            panel.add(timeLabel);
            panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        } else {
            panel.add(messageLabel);
            panel.add(timeLabel);
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        return panel;
    }

    public static void main(String[] args) {
        new UserA();
    }
}
