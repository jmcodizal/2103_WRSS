package waterrefillingsalesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JFrame {
    private final WaterRefillingSystem system;

    private JTextField nameField, quantityField, gcashNumberField, gcashNameField, gcashAmountField;
    private JComboBox<String> addressComboBox, containerComboBox, paymentComboBox;

    // Constructor for CustomerPanel
    public CustomerPanel(WaterRefillingSystem system) {
        this.system = system;
        setTitle("Customer Panel");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout for the frame
        setLayout(new GridLayout(8, 2));

        // Initialize input fields
        nameField = new JTextField();
        quantityField = new JTextField();
        gcashNumberField = new JTextField();
        gcashNameField = new JTextField();
        gcashAmountField = new JTextField();

        // Address dropdown
        addressComboBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            addressComboBox.addItem("Población " + i);
        }

        // Container type dropdown
        containerComboBox = new JComboBox<>(new String[]{"Small - P20.00", "Medium - P30.00", "Large - P40.00"});

        // Payment method dropdown
        paymentComboBox = new JComboBox<>(new String[]{"Cash on Delivery", "Cash on Pick-Up", "GCash"});

        // Button to submit order
        JButton submitButton = new JButton("Submit Order");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrder();
            }
        });

        // Add components to frame
        add(new JLabel("Customer Name:"));
        add(nameField);
        add(new JLabel("Select Barangay Address:"));
        add(addressComboBox);
        add(new JLabel("Select Container Type:"));
        add(containerComboBox);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Payment Method:"));
        add(paymentComboBox);
        add(new JLabel("GCash Number:"));
        add(gcashNumberField);
        add(new JLabel("GCash Name:"));
        add(gcashNameField);
        add(new JLabel("GCash Amount:"));
        add(gcashAmountField);
        add(submitButton);

        // Center the window
        setLocationRelativeTo(null);
    }

    // Handle the order when the submit button is clicked
    private void handleOrder() {
        String name = nameField.getText();
        String address = (String) addressComboBox.getSelectedItem();
        String containerType = (String) containerComboBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());
        String paymentMethod = (String) paymentComboBox.getSelectedItem();
        String gcashNumber = gcashNumberField.getText();
        String gcashName = gcashNameField.getText();
        int gcashAmount = Integer.parseInt(gcashAmountField.getText());

        if (isValidOrder(name, address, containerType, paymentMethod)) {
            system.addCustomer(new Customer(name, address, containerType, paymentMethod, gcashNumber, gcashName, gcashAmount, quantity));
            JOptionPane.showMessageDialog(this, "Your order is placed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        }
    }

    // Validate the order
    private boolean isValidOrder(String name, String address, String containerType, String paymentMethod) {
        return !name.isEmpty() && address != null && containerType != null && paymentMethod != null;
    }

    // Customer class to store order details
    private static class Customer {
        private String name;
        private String address;
        private String containerType;
        private String paymentMethod;
        private String gcashNumber;
        private String gcashName;
        private int gcashAmount;
        private int quantity;

        public Customer(String name, String address, String containerType, String paymentMethod, String gcashNumber, String gcashName, int gcashAmount, int quantity) {
            this.name = name;
            this.address = address;
            this.containerType = containerType;
            this.paymentMethod = paymentMethod;
            this.gcashNumber = gcashNumber;
            this.gcashName = gcashName;
            this.gcashAmount = gcashAmount;
            this.quantity = quantity;
        }
    }

    // Main method to test the panel
    public static void main(String[] args) {
        // Create an instance of WaterRefillingSystem (assuming it's already implemented)
        WaterRefillingSystem system = new WaterRefillingSystem();

        // Create and display the CustomerPanel
        CustomerPanel customerPanel = new CustomerPanel(system);
        customerPanel.setVisible(true);
    }
}
