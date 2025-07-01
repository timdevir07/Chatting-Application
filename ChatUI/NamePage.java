import java.awt.*;
import javax.swing.*;

public class NamePage extends JFrame {
    JTextField nameField;
    JButton readyButton;
    String contactInput;
    boolean isNewUser;

    public NamePage(String contactInput, boolean isNewUser) {
        this.contactInput = contactInput;
        this.isNewUser = isNewUser;

        setTitle("Enter Name");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Enter Your Name", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setHorizontalAlignment(SwingConstants.CENTER);

        readyButton = new JButton("Ready to Chat");
        readyButton.setBackground(new Color(46, 204, 113));
        readyButton.setForeground(Color.WHITE);
        readyButton.setFont(new Font("Arial", Font.BOLD, 16));

        readyButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                if (isNewUser) {
                    ChatAppDB.updateUserName(contactInput, name);
                }
                UserData.displayName = name;
                dispose();
                new UserA();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name!");
            }
        });

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.add(nameField);
        centerPanel.add(readyButton);

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
