
package waterrefillingsalesystem;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;

    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
    }

    public void showMenu() {
        while (true) {
            System.out.println("1. View Total Walk-in Customers");
            System.out.println("2. View Total Delivery Customers");
            System.out.println("3. View Deliveries by Barangay");
            System.out.println("4. View Total Sales Per Day");
            System.out.println("5. Summary By Container");
            System.out.println("6. Exit Admin Panel");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (option) {
                case 1:
                    System.out.println("Total Walk-in Customers: " + system.getWalkInCount());
                    break;
                case 2:
                    System.out.println("Total Delivery Customers: " + system.getDeliveryCount());
                    break;
                case 3:
                    System.out.println("Deliveries by Barangay: " + system.getDeliveriesByBarangay());
                    break;
                case 4:
                    System.out.println("Total Sales: " + system.getTotalSales());
                    break;
                case 5:
                    system.summaryByContainer();
                    break;
                case 6:
                    return; 
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


