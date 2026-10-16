
import javax.swing.*;
import java.awt.*;

public class OwnerLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public OwnerLogin() {
        setTitle("DineHub - Owner Login");
        setSize(450, 500);
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
            BorderFactory.createEmptyBorder(35, 35, 35, 35)
        );
        form.setPreferredSize(new Dimension(370, 410));

        JLabel title = new JLabel("DineHub");
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
        title.setForeground(brown);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Restaurant Owner Login");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        addField(form, "Owner Username", usernameField);
        addField(form, "Password", passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(brown);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(300, 40));

        JButton backButton = new JButton("Back to Customer Login");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Temporary demonstration credentials
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password =
                new String(passwordField.getPassword());

            if (username.equals("owner")
                    && password.equals("owner123")) {

                JOptionPane.showMessageDialog(
                    this, "Owner login successful!"
                );

                new OwnerDashboard();
                dispose();

            } else {
                JOptionPane.showMessageDialog(
                    this, "Incorrect username or password."
                );
            }
        });

        backButton.addActionListener(e -> {
            new Login();
            dispose();
        });

        form.add(title);
        form.add(Box.createVerticalStrut(8));
        form.add(subtitle);
        form.add(Box.createVerticalStrut(30));
        form.add(loginButton);
        form.add(Box.createVerticalStrut(10));
        form.add(backButton);

        mainPanel.add(form);
        add(mainPanel);
        setVisible(true);
    }

    private void addField(
            JPanel panel, String labelText, JComponent field) {

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setMaximumSize(new Dimension(300, 35));
        field.setPreferredSize(new Dimension(300, 35));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(18));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OwnerLogin());
    }
}

