package waterrefillingsalesystem;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

public class CustomerPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;
    private final Connection connection;

   
    public CustomerPanel(Scanner scanner, WaterRefillingSystem system, Connection connection) {
        this.scanner = scanner;
        this.system = system;
        this.connection = connection; 
    }

    public void showMenu() {
        boolean continueShopping = true;

        while (continueShopping) {
            System.out.println("Customer Menu:");
            System.out.println("1. Make a Purchase");
            System.out.println("2. Exit to Main Menu");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  

            switch (option) {
                case 1 -> makePurchase();
                case 2 -> {
                    continueShopping = false;
                    System.out.println("Returning to the Main Menu...\n");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

  private void makePurchase() {
    System.out.println("\n--- Making a Purchase ---");

    System.out.print("Enter your name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Your Barangay (Poblacion 1 - 12): ");
    String barangay = scanner.nextLine();

    System.out.print("Enter your contact number: ");
    String contactNumber = scanner.nextLine();

    System.out.print("Enter Payment Method (GCash/Cash on Delivery/Cash on Pickup): ");
    String paymentMethod = scanner.nextLine();

    System.out.print("Enter Container Type (Small/Medium/Large/Extra Large): ");
    String containerType = scanner.nextLine();

    System.out.print("Enter Quantity: ");
    int quantity = scanner.nextInt();
    scanner.nextLine(); 

    double gcashAmount = 0;
    String gcashNumber = null;
    String gcashName = null;

    if ("GCash".equalsIgnoreCase(paymentMethod)) {
        System.out.print("Enter GCash Number: ");
        gcashNumber = scanner.nextLine();

        System.out.print("Enter GCash Name: ");
        gcashName = scanner.nextLine();

        System.out.print("Enter GCash amount: P");
        gcashAmount = scanner.nextDouble();
        scanner.nextLine(); 
    }

    String deliveryDate = null;
    if ("Cash on Delivery".equalsIgnoreCase(paymentMethod)) {
        System.out.print("Enter preferred delivery date (YYYY-MM-DD): ");
        deliveryDate = scanner.nextLine();
    }

   
    Customer customer = new Customer(name, containerType, quantity, paymentMethod, 
                                     contactNumber, barangay, deliveryDate, 
                                     gcashNumber, gcashName, gcashAmount);

   
    insertCustomerIntoDatabase(customer);

    printReceipt(customer);  

    System.out.print("Would you like to make another purchase? (yes/no): ");
    String choice = scanner.nextLine().toLowerCase();
    if (choice.equals("no")) {
        System.out.println("Returning to the Main Menu...\n");
    } else if (!choice.equals("yes")) {
        System.out.println("Invalid input, returning to the Main Menu...\n");
    }
}

private void insertCustomerIntoDatabase(Customer customer) {
    String query = "INSERT INTO customers (Name, Barangay, ContactNumber, ContainerType, Quantity, " +
                   "PaymentMethod, GcashNumber, GcashName, GcashAmount, DeliveryDate, SalesDate) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      
        pstmt.setString(1, customer.getName()); 
        pstmt.setString(2, customer.getBarangay()); 
        pstmt.setString(3, customer.getContactNumber()); 
        pstmt.setString(4, customer.getContainerType()); 
        pstmt.setInt(5, customer.getQuantity()); 
        pstmt.setString(6, customer.getPaymentMethod());

      
        if ("GCash".equalsIgnoreCase(customer.getPaymentMethod())) {
            pstmt.setString(7, customer.getGcashNumber()); 
            pstmt.setString(8, customer.getGcashName()); 
            pstmt.setDouble(9, customer.getGcashAmount()); 
        } else {
           
            pstmt.setNull(7, java.sql.Types.VARCHAR); 
            pstmt.setNull(8, java.sql.Types.VARCHAR);  
            pstmt.setDouble(9, 0); 
        }

       
        if (customer.getDeliveryDate() != null && !customer.getDeliveryDate().isEmpty()) {
            pstmt.setString(10, customer.getDeliveryDate()); 
        } else {
            pstmt.setNull(10, java.sql.Types.VARCHAR); 
        }
        
       
        pstmt.setDate(11, java.sql.Date.valueOf(customer.getSalesDate()));

        
        pstmt.executeUpdate();
        System.out.println("Customer details have been successfully inserted into the database.");
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error while inserting customer details into the database.");
    }
}



   private void printReceipt(Customer customer) {
    double price = 0;

 
    switch (customer.getContainerType()) {
        case "Small" -> price = 20.00;
        case "Medium" -> price = 30.00;
        case "Large" -> price = 40.00;
        case "Extra Large" -> price = 50.00;
        default -> {
            System.out.println("Invalid container type.");
            return;
        }
    }
    price *= customer.getQuantity();  

    double finalAmount = price;
    String amountPaid = "N/A";
    String change = "N/A";
    
   
    if ("GCash".equalsIgnoreCase(customer.getPaymentMethod())) {
        double gcashAmount = customer.getGcashAmount();
        double remainingAmount = price - gcashAmount;

        if (remainingAmount > 0) {
            amountPaid = "P" + gcashAmount;
            change = "N/A"; 
        } else {
            amountPaid = "P" + gcashAmount;
            change = "P" + Math.abs(remainingAmount); 
            finalAmount = price; 
        }
    }

   
    System.out.println("\n--- Receipt ---");
    System.out.println("Customer Name: " + customer.getName());
    System.out.println("Barangay: " + customer.getBarangay());
    System.out.println("Contact Number: " + customer.getContactNumber());
    System.out.println("Container Type: " + customer.getContainerType());
    System.out.println("Quantity: " + customer.getQuantity());
    System.out.println("Payment Method: " + customer.getPaymentMethod());
    System.out.println("Total Price: P" + price);

    if ("GCash".equalsIgnoreCase(customer.getPaymentMethod())) {
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("Change: " + change);
    } else if ("Cash on Delivery".equalsIgnoreCase(customer.getPaymentMethod()) || "Cash on Pick-Up".equalsIgnoreCase(customer.getPaymentMethod())) {
        System.out.println("Amount Paid: N/A");
        System.out.println("Final Amount: P" + price);
    }

  
    if ("Cash on Delivery".equalsIgnoreCase(customer.getPaymentMethod())) {
        System.out.println("Preferred Delivery Date: " + customer.getDeliveryDate());
    }

    System.out.println("Thank you for your purchase!\n");
}
}
