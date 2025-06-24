// import java.awt.*;
// import java.time.LocalTime;
// import javax.swing.*;

// public class TwoUserChatUI extends JFrame {
//     private JTextArea userAChatArea, userBChatArea;
//     private JTextField userAInput, userBInput;

//     public TwoUserChatUI() {
//         setTitle("2-User Chat - Chat App");
//         setSize(900, 500);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);

//         JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
//         mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         // User A Panel
//         JPanel userAPanel = createUserPanel("User A", true);
//         // User B Panel
//         JPanel userBPanel = createUserPanel("User B", false);

//         mainPanel.add(userAPanel);
//         mainPanel.add(userBPanel);
//         add(mainPanel);

//         setVisible(true);
//     }

//     private JPanel createUserPanel(String username, boolean isUserA) {
//         JPanel panel = new JPanel(new BorderLayout(10, 10));
//         panel.setBorder(BorderFactory.createTitledBorder(username));

//         JTextArea chatArea = new JTextArea();
//         chatArea.setEditable(false);
//         chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
//         chatArea.setLineWrap(true);
//         JScrollPane scrollPane = new JScrollPane(chatArea);

//         JTextField inputField = new JTextField();
//         JButton sendBtn = new JButton("Send");
//         styleButton(sendBtn);

//         JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
//         inputPanel.add(inputField, BorderLayout.CENTER);
//         inputPanel.add(sendBtn, BorderLayout.EAST);

//         panel.add(scrollPane, BorderLayout.CENTER);
//         panel.add(inputPanel, BorderLayout.SOUTH);

//         if (isUserA) {
//             userAChatArea = chatArea;
//             userAInput = inputField;
//             sendBtn.addActionListener(e -> sendMessage(userAInput, userBChatArea, "User A"));
//         } else {
//             userBChatArea = chatArea;
//             userBInput = inputField;
//             sendBtn.addActionListener(e -> sendMessage(userBInput, userAChatArea, "User B"));
//         }

//         return panel;
//     }

//     private void styleButton(JButton btn) {
//         btn.setBackground(new Color(52, 152, 219));
//         btn.setForeground(Color.WHITE);
//         btn.setFocusPainted(false);
//         btn.setFont(new Font("Arial", Font.BOLD, 14));
//         btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//         btn.setBorder(BorderFactory.createCompoundBorder(
//                 BorderFactory.createLineBorder(new Color(41, 128, 185)),
//                 BorderFactory.createEmptyBorder(8, 16, 8, 16)));
//     }

//     private void sendMessage(JTextField inputField, JTextArea receiverArea, String sender) {
//         String text = inputField.getText().trim();
//         if (!text.isEmpty()) {
//             String time = LocalTime.now().withNano(0).toString();
//             receiverArea.append("[" + sender + " - " + time + "]: " + text + "\n");
//             inputField.setText("");
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(TwoUserChatUI::new);
//     }
// }

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatAppTwoUser extends JFrame {
    private JTextArea chatAreaA;
    private JTextField inputFieldA;

    private JTextArea chatAreaB;
    private JTextField inputFieldB;

    public ChatAppTwoUser() {
        setTitle("2-User Chat - Chat App");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        chatAreaA = new JTextArea();
        chatAreaA.setEditable(false);
        chatAreaA.setLineWrap(true);

        inputFieldA = new JTextField();
        JButton sendButtonA = new JButton("Send");
        JPanel panelA = new JPanel(new BorderLayout());
        panelA.setBorder(BorderFactory.createTitledBorder("User A"));
        panelA.add(new JScrollPane(chatAreaA), BorderLayout.CENTER);

        JPanel inputPanelA = new JPanel(new BorderLayout());
        inputPanelA.add(inputFieldA, BorderLayout.CENTER);
        inputPanelA.add(sendButtonA, BorderLayout.EAST);
        panelA.add(inputPanelA, BorderLayout.SOUTH);

        chatAreaB = new JTextArea();
        chatAreaB.setEditable(false);
        chatAreaB.setLineWrap(true);

        inputFieldB = new JTextField();
        JButton sendButtonB = new JButton("Send");
        JPanel panelB = new JPanel(new BorderLayout());
        panelB.setBorder(BorderFactory.createTitledBorder("User B"));
        panelB.add(new JScrollPane(chatAreaB), BorderLayout.CENTER);

        JPanel inputPanelB = new JPanel(new BorderLayout());
        inputPanelB.add(inputFieldB, BorderLayout.CENTER);
        inputPanelB.add(sendButtonB, BorderLayout.EAST);
        panelB.add(inputPanelB, BorderLayout.SOUTH);

        add(panelA);
        add(panelB);

        sendButtonA.addActionListener(e -> sendMessage("A"));
        sendButtonB.addActionListener(e -> sendMessage("B"));

        inputFieldA.addActionListener(e -> sendMessage("A"));
        inputFieldB.addActionListener(e -> sendMessage("B"));

        setVisible(true);
    }

    private void sendMessage(String sender) {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        if (sender.equals("A")) {
            String msg = inputFieldA.getText().trim();
            if (!msg.isEmpty()) {
                chatAreaA.append("\n[You - " + time + "] -> " + msg);
                chatAreaB.append("\n[User A - " + time + "] <- " + msg);
                inputFieldA.setText("");
            }
        } else {
            String msg = inputFieldB.getText().trim();
            if (!msg.isEmpty()) {
                chatAreaB.append("\n[You - " + time + "] -> " + msg);
                chatAreaA.append("\n[User B - " + time + "] <- " + msg);
                inputFieldB.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatAppTwoUser::new);
    }
}

