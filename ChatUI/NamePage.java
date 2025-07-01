import java.awt.*;
import javax.swing.*;

public class NamePage extends JFrame {
    JTextField nameField;
    JButton readyButton;
    String contactInput;

    public NamePage(String contactInput, boolean isNewUser) {
        this.contactInput = contactInput;

        setTitle("Enter Name");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Enter Your Name", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setHorizontalAlignment(SwingConstants.CENTER);

        readyButton = new JButton("Ready to Chat");
        readyButton.setFont(new Font("Arial", Font.BOLD, 16));
        readyButton.setBackground(new Color(30, 144, 255));
        readyButton.setForeground(Color.WHITE);
        readyButton.setFocusPainted(false);
        readyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        readyButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                ChatAppDB.updateUserName(contactInput, name); // Always update
                JOptionPane.showMessageDialog(this, "Welcome " + name + "!");
                dispose();
                new UserA(); // or new UserB();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.add(nameField);
        centerPanel.add(readyButton);

        add(heading, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
