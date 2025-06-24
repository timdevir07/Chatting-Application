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
    JButton send;
    static JPanel chatPanel;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    JScrollPane scrollPane;

    UserA() {
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        try {
            URL backUrl = new URL("https://cdn-icons-png.flaticon.com/512/93/93634.png");
            Image backImg = new ImageIcon(backUrl).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
            JLabel back = new JLabel(new ImageIcon(backImg));
            back.setBounds(5, 20, 25, 25);
            p1.add(back);
            back.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent ae) {
                    System.exit(0);
                }
            });

            URL profileUrl = new URL("https://cdn-icons-png.flaticon.com/512/3135/3135715.png");
            Image profileImg = new ImageIcon(profileUrl).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            JLabel profile = new JLabel(new ImageIcon(profileImg));
            profile.setBounds(40, 10, 50, 50);
            p1.add(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel name = new JLabel("USER A");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Online");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        p1.add(status);

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
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
            String out = text.getText();
            JPanel p2 = formatLabel(out);
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            chatPanel.add(vertical);
            dout.writeUTF(out);
            text.setText("");
            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        JLabel time = new JLabel(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new UserA();

        try {
            ServerSocket skt = new ServerSocket(6001);
            Socket s = skt.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                chatPanel.add(vertical);
                f.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
