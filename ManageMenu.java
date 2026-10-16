
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageMenu extends JFrame {

    private JTextField nameField;
    private JComboBox<String> categoryBox;
    private JTextField priceField;
    private JTextField descriptionField;
    private DefaultTableModel tableModel;

    public ManageMenu() {
        setTitle("DineHub - Manage Menu");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color background = new Color(245, 241, 232);
        Color brown = new Color(189, 151, 126);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(background);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel(
                "Manage Menu", SwingConstants.CENTER);
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        title.setForeground(brown);

        // Food item form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(background);

        nameField = new JTextField();

        categoryBox = new JComboBox<>(new String[] {
                "Starter", "Main Course", "Dessert",
                "Beverage", "Other"
        });

        priceField = new JTextField();
        descriptionField = new JTextField();

        formPanel.add(new JLabel("Food Item Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryBox);

        formPanel.add(new JLabel("Price (₹):"));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);

        JButton addButton = new JButton("Add Food Item");
        addButton.setBackground(brown);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        // Menu table
        tableModel = new DefaultTableModel(
                new String[] {
                        "Food Name", "Category", "Price (₹)", "Description"
                }, 0);

        JTable menuTable = new JTable(tableModel);
        menuTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(menuTable);

        JButton deleteButton = new JButton("Delete Selected Item");
        JButton backButton = new JButton("Back to Dashboard");

        addButton.addActionListener(e -> addFoodItem());

        deleteButton.addActionListener(e -> {
            int selectedRow = menuTable.getSelectedRow();

            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(
                        this, "Please select a food item to delete.");
            }
        });

        backButton.addActionListener(e -> {
            new OwnerDashboard();
            dispose();
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(background);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setPreferredSize(new Dimension(180, 35));
        addButton.setMaximumSize(new Dimension(180, 35));

        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(15));
        topPanel.add(formPanel);
        topPanel.add(Box.createVerticalStrut(15));
        topPanel.add(addButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(background);
        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void addFoodItem() {
        String name = nameField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();
        String priceText = priceField.getText().trim();
        String description = descriptionField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty()
                || description.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please fill in all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);

            if (price <= 0) {
                JOptionPane.showMessageDialog(
                        this, "Price must be greater than zero.");
                return;
            }

            tableModel.addRow(new Object[] {
                    name, category, String.format("%.2f", price), description
            });

            nameField.setText("");
            priceField.setText("");
            descriptionField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Please enter a valid price.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageMenu());
    }
}
