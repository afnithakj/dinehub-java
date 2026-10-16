
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("DineHub - Login");
        setSize(450, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main background
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 241, 232));
        mainPanel.setLayout(new GridBagLayout());

        // Login box
        JPanel loginBox = new JPanel();
        loginBox.setLayout(new BoxLayout(loginBox, BoxLayout.Y_AXIS));
        loginBox.setBackground(Color.WHITE);
        loginBox.setBorder(
                BorderFactory.createEmptyBorder(35, 35, 35, 35));

        // Title
        JLabel title = new JLabel("DineHub");
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
        title.setForeground(new Color(189, 151, 126));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Welcome back!");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(320, 38));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(320, 38));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(189, 151, 126));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(320, 40));

        // Create account button
        JButton createButton = new JButton("Create Account");
        createButton.setBorderPainted(false);
        createButton.setContentAreaFilled(false);
        createButton.setForeground(new Color(140, 100, 75));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login button action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(
                    passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter both username and password.");
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Login form is working! Database login will be added later.");
            }
        });

        // Create account action

        createButton.addActionListener(e -> {
            new CreateAccount();
            dispose();
        });

        // Arrange components
        loginBox.add(title);
        loginBox.add(Box.createVerticalStrut(8));
        loginBox.add(subtitle);
        loginBox.add(Box.createVerticalStrut(30));
        loginBox.add(usernameLabel);
        loginBox.add(Box.createVerticalStrut(6));
        loginBox.add(usernameField);
        loginBox.add(Box.createVerticalStrut(18));
        loginBox.add(passwordLabel);
        loginBox.add(Box.createVerticalStrut(6));
        loginBox.add(passwordField);
        loginBox.add(Box.createVerticalStrut(25));
        loginBox.add(loginButton);
        loginBox.add(Box.createVerticalStrut(12));
        loginBox.add(createButton);

        loginBox.setPreferredSize(new Dimension(370, 420));
        mainPanel.add(loginBox);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
