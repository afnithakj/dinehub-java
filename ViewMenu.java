
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewMenu extends JFrame {

    public ViewMenu() {
        setTitle("DineHub - Restaurant Menu");
        setSize(800, 500);
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
            "Our Menu", SwingConstants.CENTER
        );
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        title.setForeground(brown);

        String[] columns = {
            "Food Item", "Category", "Description", "Price (₹)"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Sample menu items
        model.addRow(new Object[]{
            "Veg Burger", "Starter", "Fresh vegetable burger", "120"
        });
        model.addRow(new Object[]{
            "Margherita Pizza", "Main Course", "Cheese pizza", "350"
        });
        model.addRow(new Object[]{
            "White Sauce Pasta", "Main Course", "Creamy pasta", "200"
        });
        model.addRow(new Object[]{
            "Chocolate Brownie", "Dessert", "Chocolate dessert", "100"
        });
        model.addRow(new Object[]{
            "Cold Coffee", "Beverage", "Chilled coffee", "90"
        });

        JTable menuTable = new JTable(model);
        menuTable.setRowHeight(30);
        menuTable.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(menuTable);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            new CustomerHome();
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(background);
        bottomPanel.add(backButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewMenu());
    }
}

