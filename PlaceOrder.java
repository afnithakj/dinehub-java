
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlaceOrder extends JFrame {

    private JComboBox<String> foodBox;
    private JSpinner quantitySpinner;
    private JLabel totalLabel;

    private String[] foodNames = {
        "Veg Burger",
        "Margherita Pizza",
        "White Sauce Pasta",
        "Chocolate Brownie",
        "Cold Coffee"
    };

    private double[] foodPrices = {
        120, 350, 200, 100, 90
    };

    private DefaultTableModel orderModel;
    private double total = 0;

    public PlaceOrder() {
        setTitle("DineHub - Place Order");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color background = new Color(245, 241, 232);
        Color brown = new Color(189, 151, 126);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(background);
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel title = new JLabel(
            "Place Your Order", SwingConstants.CENTER
        );
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        title.setForeground(brown);

        // Food selection form
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(background);

        foodBox = new JComboBox<>(foodNames);
        quantitySpinner = new JSpinner(
            new SpinnerNumberModel(1, 1, 20, 1)
        );

        formPanel.add(new JLabel("Select Food:"));
        formPanel.add(foodBox);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantitySpinner);

        JButton addButton = new JButton("Add to Order");
        addButton.setBackground(brown);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        // Order table
        orderModel = new DefaultTableModel(
            new String[]{"Food Item", "Price (₹)", "Quantity", "Subtotal (₹)"},
            0
        );

        JTable orderTable = new JTable(orderModel);
        orderTable.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(orderTable);

        totalLabel = new JLabel("Total: ₹0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton removeButton = new JButton("Remove Selected");
        JButton placeButton = new JButton("Confirm Order");
        JButton backButton = new JButton("Back to Home");

        addButton.addActionListener(e -> {
            int index = foodBox.getSelectedIndex();
            int quantity = (Integer) quantitySpinner.getValue();

            double price = foodPrices[index];
            double subtotal = price * quantity;

            orderModel.addRow(new Object[]{
                foodNames[index],
                String.format("%.2f", price),
                quantity,
                String.format("%.2f", subtotal)
            });

            calculateTotal();
        });

        removeButton.addActionListener(e -> {
            int selectedRow = orderTable.getSelectedRow();

            if (selectedRow != -1) {
                orderModel.removeRow(selectedRow);
                calculateTotal();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Please select an item to remove."
                );
            }
        });

        placeButton.addActionListener(e -> {
            if (orderModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(
                    this, "Please add at least one food item."
                );
                return;
            }

            JOptionPane.showMessageDialog(
                this,
                "Order placed successfully!\nTotal: ₹"
                    + String.format("%.2f", total)
                    + "\n\nThis is a demo. The order is not saved yet."
            );

            orderModel.setRowCount(0);
            calculateTotal();
        });

        backButton.addActionListener(e -> {
            new CustomerHome();
            dispose();
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(background);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(20));
        topPanel.add(formPanel);
        topPanel.add(Box.createVerticalStrut(12));
        topPanel.add(addButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(background);

        JPanel totalPanel = new JPanel();
        totalPanel.setBackground(background);
        totalPanel.add(totalLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.add(removeButton);
        buttonPanel.add(placeButton);
        buttonPanel.add(backButton);

        bottomPanel.add(totalPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void calculateTotal() {
        total = 0;

        for (int i = 0; i < orderModel.getRowCount(); i++) {
            total += Double.parseDouble(
                orderModel.getValueAt(i, 3).toString()
            );
        }

        totalLabel.setText(
            "Total: ₹" + String.format("%.2f", total)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlaceOrder());
    }
}

