import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserB implements ActionListener {
    JTextField text;
    JPanel chatArea;
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    JScrollPane scrollPane;

    UserB() {
        f.setLayout(null);

        JPanel header = new JPanel();
        header.setBackground(new Color(7, 94, 84));
        header.setBounds(0, 0, 450, 70);
        header.setLayout(null);
        f.add(header);

        try {
            URL backURL = new URL("https://cdn-icons-png.flaticon.com/512/93/93634.png");
            ImageIcon backIcon = new ImageIcon(backURL);
            Image backImage = backIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
            JLabel back = new JLabel(new ImageIcon(backImage));
            back.setBounds(5, 20, 25, 25);
            header.add(back);

            back.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent ae) {
                    System.exit(0);
                }
            });

            URL profileURL = new URL("https://cdn-icons-png.flaticon.com/512/847/847969.png");
            Image profileImg = new ImageIcon(profileURL).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            JLabel profile = new JLabel(new ImageIcon(profileImg));
            profile.setBounds(40, 10, 50, 50);
            header.add(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ Display UserB ka name from UserDataB
        String username = (UserDataB.displayName != null && !UserDataB.displayName.isEmpty())
                ? UserDataB.displayName : "User B";

        JLabel name = new JLabel(username);
        name.setBounds(110, 15, 200, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        header.add(name);

        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.addActionListener(this);
        f.add(send);

        // 🔹 Enter key press to send
        text.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send.doClick();
                }
            }
        });

        f.setSize(450, 700);
        f.setLocation(700, 20);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText().trim();
            if (out.equals("")) return;
            text.setText(""); // Clear input
            addMessage(out, true);
            dout.writeUTF(out);
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

        // 🔽 Auto-scroll to latest message
        SwingUtilities.invokeLater(() -> 
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum())
        );
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
        UserB userB = new UserB();
        try {
            Socket socket = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                userB.addMessage(msg, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
