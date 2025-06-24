import java.awt.*;
import javax.swing.*;

public class NamePage extends JFrame {
    public NamePage() {
        setTitle("Enter Name - Chat App");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel prompt = new JLabel("Enter Your Name", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.BOLD, 18));
        prompt.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton continueBtn = new JButton("Ready to Chat");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 14));
        continueBtn.setBackground(new Color(46, 204, 113));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        continueBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Welcome " + name + "!\nChat feature coming soon!");
                dispose();
                // TODO: Launch Chat UI
            } else {
                JOptionPane.showMessageDialog(this, "Please enter your name!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel center = new JPanel(new GridLayout(2, 1, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        center.add(nameField);
        center.add(continueBtn);

        add(prompt, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        setVisible(true);
    }
}
