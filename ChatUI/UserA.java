// ✅ FINAL WORKING CODE FOR UserA.java (Server)
// Save this file as UserA.java inside ChatUI folder

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserA implements ActionListener {
    JTextField text;
    JPanel chatArea;
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    Box vertical = Box.createVerticalBox();
    JScrollPane scrollPane;

    UserA() {
        f.setLayout(null);

        JPanel header = new JPanel();
        header.setBackground(new Color(7, 94, 84));
        header.setBounds(0, 0, 450, 70);
        header.setLayout(null);
        f.add(header);

        try {
            URL profileURL = new URL("https://cdn-icons-png.flaticon.com/512/3135/3135715.png");
            Image profileImg = new ImageIcon(profileURL).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            JLabel profile = new JLabel(new ImageIcon(profileImg));
            profile.setBounds(10, 10, 50, 50);
            header.add(profile);
        } catch (Exception e) {}

        JLabel name = new JLabel("User A");
        name.setBounds(70, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        header.add(name);

        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        f.add(send);

        f.setSize(450, 700);
        f.setLocation(200, 20);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText().trim();
            if (out.equals("")) return;
            addMessage(out, true);
            dout.writeUTF(out);
            text.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMessage(String msg, boolean alignRight) {
        JPanel messagePanel = formatLabel(msg);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(messagePanel, alignRight ? BorderLayout.LINE_END : BorderLayout.LINE_START);
        chatArea.add(wrapper);
        chatArea.add(Box.createVerticalStrut(15));
        f.revalidate();
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }

    public JPanel formatLabel(String msg) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("<html><p style='width: 150px;'>" + msg + "</p></html>");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setBackground(new Color(37, 211, 102));
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(label);

        JLabel time = new JLabel(new SimpleDateFormat("hh:mm a").format(new Date()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        UserA userA = new UserA();
        try {
            ServerSocket server = new ServerSocket(6001);
            Socket socket = server.accept();
            DataInputStream din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                userA.addMessage(msg, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
