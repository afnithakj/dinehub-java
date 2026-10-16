
import javax.swing.*;
import java.awt.*;

public class CreateAccount extends JFrame {

    public CreateAccount() {
        setTitle("DineHub - Create Account");
        setSize(450, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color background = new Color(245, 241, 232);
        Color brown = new Color(189, 151, 126);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(background);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        );
        form.setPreferredSize(new Dimension(370, 640));

        JLabel title = new JLabel("Join DineHub");
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 32));
        title.setForeground(brown);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Create your customer account");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();

        // Create Account button
        JButton createButton = new JButton("Create Account");
        createButton.setBackground(brown);
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setMaximumSize(new Dimension(300, 38));

        // Back to Login button
        JButton backButton = new JButton("Back to Login");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(new Color(140, 100, 75));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add all components in the correct order
        form.add(title);
        form.add(Box.createVerticalStrut(5));
        form.add(subtitle);
        form.add(Box.createVerticalStrut(25));

        addField(form, "Full Name", nameField);
        addField(form, "Username", usernameField);
        addField(form, "Email", emailField);
        addField(form, "Password", passwordField);
        addField(form, "Confirm Password", confirmField);

        form.add(Box.createVerticalStrut(12));
        form.add(createButton);
        form.add(Box.createVerticalStrut(8));
        form.add(backButton);

        // Registration validation
        createButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password =
                new String(passwordField.getPassword());
            String confirm =
                new String(confirmField.getPassword());

            if (name.isEmpty() || username.isEmpty()
                    || email.isEmpty() || password.isEmpty()
                    || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Please fill in all fields."
                );
            } else if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                JOptionPane.showMessageDialog(
                    this, "Please enter a valid email address."
                );
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(
                    this, "Passwords do not match."
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Registration form completed successfully!\n"
                    + "Account storage will be added later."
                );
            }
        });

        // Return to Login
        backButton.addActionListener(e -> {
            new Login();
            dispose();
        });

        mainPanel.add(form);
        add(mainPanel);
        setVisible(true);
    }

    private void addField(
            JPanel panel, String labelText, JComponent field) {

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setPreferredSize(new Dimension(300, 32));
        field.setMaximumSize(new Dimension(300, 32));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
        panel.add(field);
        panel.add(Box.createVerticalStrut(10));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateAccount());
    }
}

