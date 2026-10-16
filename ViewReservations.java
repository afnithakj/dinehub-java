
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewReservations extends JFrame {

    private DefaultTableModel tableModel;

    public ViewReservations() {
        setTitle("DineHub - View Reservations");
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
            "Table Reservations", SwingConstants.CENTER
        );
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        title.setForeground(brown);

        String[] columns = {
            "Booking ID", "Customer", "Date",
            "Time", "Guests", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0);

        // Sample reservations for demonstration
        tableModel.addRow(new Object[]{
            "RES001", "Anu", "2026-10-18", "7:00 PM", 2, "Confirmed"
        });
        tableModel.addRow(new Object[]{
            "RES002", "Rahul", "2026-10-19", "8:00 PM", 4, "Pending"
        });
        tableModel.addRow(new Object[]{
            "RES003", "Maya", "2026-10-20", "6:30 PM", 3, "Confirmed"
        });

        JTable reservationsTable = new JTable(tableModel);
        reservationsTable.setRowHeight(30);
        reservationsTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(reservationsTable);

        JButton updateButton = new JButton("Update Status");
        JButton backButton = new JButton("Back to Dashboard");

        updateButton.addActionListener(e -> {
            int selectedRow = reservationsTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select a reservation first."
                );
                return;
            }

            String[] statuses = {
                "Pending", "Confirmed", "Cancelled"
            };

            String currentStatus = tableModel.getValueAt(
                selectedRow, 5
            ).toString();

            String newStatus = (String) JOptionPane.showInputDialog(
                this,
                "Choose the reservation status:",
                "Update Reservation",
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
        SwingUtilities.invokeLater(() -> new ViewReservations());
    }
}

