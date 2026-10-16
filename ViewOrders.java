
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewOrders extends JFrame {

    private DefaultTableModel tableModel;

    public ViewOrders() {
        setTitle("DineHub - View Orders");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color background = new Color(245, 241, 232);
        Color brown = new Color(189, 151, 126);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 20));
        mainPanel.setBackground(background);
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel title = new JLabel(
            "Customer Orders", SwingConstants.CENTER
        );
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        title.setForeground(brown);

        String[] columns = {
            "Order ID", "Customer", "Food Item",
            "Quantity", "Total (₹)", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0);

        // Sample orders for demonstration
        tableModel.addRow(new Object[]{
            "ORD001", "Anu", "Veg Burger", 2, "240.00", "Pending"
        });
        tableModel.addRow(new Object[]{
            "ORD002", "Rahul", "Pizza", 1, "350.00", "Preparing"
        });
        tableModel.addRow(new Object[]{
            "ORD003", "Maya", "Pasta", 2, "400.00", "Completed"
        });

        JTable ordersTable = new JTable(tableModel);
        ordersTable.setRowHeight(30);
        ordersTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(ordersTable);

        JButton updateButton = new JButton("Update Status");
        JButton backButton = new JButton("Back to Dashboard");

        updateButton.addActionListener(e -> {
            int selectedRow = ordersTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select an order first."
                );
                return;
            }

            String[] statuses = {
                "Pending", "Preparing", "Completed", "Cancelled"
            };

            String currentStatus = tableModel.getValueAt(
                selectedRow, 5
            ).toString();

            String newStatus = (String) JOptionPane.showInputDialog(
                this,
                "Choose the new order status:",
                "Update Order Status",
                JOptionPane.PLAIN_MESSAGE,
                null,
                statuses,
                currentStatus
            );

            if (newStatus != null) {
                tableModel.setValueAt(newStatus, selectedRow, 5);
            }
        });

        backButton.addActionListener(e -> {
            new OwnerDashboard();
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(background);
        bottomPanel.add(updateButton);
        bottomPanel.add(backButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewOrders());
    }
}

