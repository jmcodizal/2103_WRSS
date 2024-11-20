package waterrefillingsalesystem;

import java.util.Scanner;

public class CustomerPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;

    public CustomerPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
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
                case 1:
                    makePurchase();  
                    break;
                case 2:
                    continueShopping = false;
                    System.out.println("Returning to the Main Menu...\n");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void makePurchase() {
        System.out.println("\n--- Making a Purchase ---");
        
      
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

      
        System.out.print("Enter Container Type (Small/Medium/Large/Extra Large): ");
        String containerType = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter Payment Method (GCash/Cash on Delivery/Cash on Pick-Up): ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter Your Barangay(Poblacion 1 - 12): ");
        String address = scanner.nextLine();

      
        Customer customer = new Customer(name, containerType, quantity, paymentMethod, address);
        system.addCustomer(customer);  

      
        printReceipt(customer);

       
        System.out.print("Would you like to make another purchase? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("no")) {
            System.out.println("Returning to the Main Menu...\n");
        } else if (!choice.equals("yes")) {
            System.out.println("Invalid input, returning to the Main Menu...\n");
        }
    }

  
    private void printReceipt(Customer customer) {
        double price = 0;

       
        switch (customer.getContainerType()) {
            case "Small" -> price = 20.00;
            case "Medium" -> price = 30.00;
            case "Large" -> price = 40.00;
            case "Extra Large" -> price = 50.00;
        }
        price *= customer.getQuantity();

      
        if ("GCash".equals(customer.getPaymentMethod())) {
            double amountPaid = customer.getGcashAmount();
            double remainingAmount = price - amountPaid;
            if (remainingAmount > 0) {
                System.out.println("Remaining balance: P" + remainingAmount);
            } else {
                System.out.println("Change: P" + Math.abs(remainingAmount));
            }
            price = amountPaid;
        }

       
        System.out.println("\n--- Receipt ---");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Container Type: " + customer.getContainerType());
        System.out.println("Quantity: " + customer.getQuantity());
        System.out.println("Payment Method: " + customer.getPaymentMethod());
        System.out.println("Barangay: " + customer.getAddress());
        System.out.println("Total Price: P" + price);
        System.out.println("Thank you for your purchase!\n");
    }
}
