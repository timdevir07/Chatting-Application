import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserB implements ActionListener {

    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    JFrame f;
    DataOutputStream dout;
    JScrollPane scrollPane;

    public UserB() throws Exception {
        f = new JFrame();
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        // Back icon
        URL backUrl = new URL("https://cdn-icons-png.flaticon.com/512/860/860790.png");
        ImageIcon i1 = new ImageIcon(backUrl);
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        JLabel back = new JLabel(new ImageIcon(i2));
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // Profile
        URL profileUrl = new URL("https://cdn-icons-png.flaticon.com/512/847/847969.png");
        ImageIcon i4 = new ImageIcon(profileUrl);
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel profile = new JLabel(new ImageIcon(i5));
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        // Video icon
        URL videoUrl = new URL("https://cdn-icons-png.flaticon.com/512/727/727245.png");
        ImageIcon i7 = new ImageIcon(videoUrl);
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel video = new JLabel(new ImageIcon(i8));
        video.setBounds(300, 20, 30, 30);
        p1.add(video);

        // Phone icon
        URL phoneUrl = new URL("https://cdn-icons-png.flaticon.com/512/455/455705.png");
        ImageIcon i10 = new ImageIcon(phoneUrl);
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_SMOOTH);
        JLabel phone = new JLabel(new ImageIcon(i11));
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);

        // 3-dot menu icon
        URL moreUrl = new URL("https://cdn-icons-png.flaticon.com/512/512/512222.png");
        ImageIcon i13 = new ImageIcon(moreUrl);
        Image i14 = i13.getImage().getScaledInstance(15, 25, Image.SCALE_SMOOTH);
        JLabel morevert = new JLabel(new ImageIcon(i14));
        morevert.setBounds(415, 20, 15, 25);
        p1.add(morevert);

        JLabel name = new JLabel("User B");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Online");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        p1.add(status);

        a1 = new JPanel();
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(5, 75, 440, 570);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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

        f.setSize(450, 700);
        f.setLocation(800, 20);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText();
            if (out.trim().isEmpty()) return;

            JPanel p2 = formatLabel(out);
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);

            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);
            text.setText("");
            f.repaint();
            f.revalidate();

            SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        JLabel time = new JLabel(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        try {
            UserB userB = new UserB();

            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            userB.dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                String msg = din.readUTF();
                JPanel panel = userB.formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                userB.a1.add(vertical, BorderLayout.PAGE_START);
                userB.f.validate();

                SwingUtilities.invokeLater(() -> userB.scrollPane.getVerticalScrollBar().setValue(userB.scrollPane.getVerticalScrollBar().getMaximum()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
