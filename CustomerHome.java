
import javax.swing.*;
import java.awt.*;

public class CustomerHome extends JFrame {

    public CustomerHome() {
        setTitle("DineHub - Customer Home");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color background = new Color(245, 241, 232);
        Color brown = new Color(189, 151, 126);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(background);
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        );

        JLabel title = new JLabel("DineHub", SwingConstants.CENTER);
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
        title.setForeground(brown);

        JLabel welcome = new JLabel(
            "Welcome! What would you like to do today?",
            SwingConstants.CENTER
        );
        welcome.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel heading = new JPanel(new GridLayout(2, 1, 5, 5));
        heading.setBackground(background);
        heading.add(title);
        heading.add(welcome);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 12, 12));
        buttonPanel.setBackground(background);

       String[] actions = {
    "View Menu",
    "Place Order",
    "Book a Table",
    "My Profile"
};

        for (String action : actions) {
            JButton button = new JButton(action);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(100, 70, 50));
            button.setFocusPainted(false);

            button.addActionListener(e ->
                JOptionPane.showMessageDialog(
                    this,
                    action + " page will be added next!"
                )
            );

            buttonPanel.add(button);
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            new Login();
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(background);
        bottomPanel.add(logoutButton);

        mainPanel.add(heading, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerHome());
    }
}
