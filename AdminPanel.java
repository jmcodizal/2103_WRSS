package waterrefillingsalesystem;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;

    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
    }

    public void showAdminMenu() {
        System.out.println("Admin Panel - Login / Sign-Up");
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        
       
        System.out.println("Admin login successful!");

        boolean showMenu = true;
        while (showMenu) {
          
            System.out.println("\nAdmin Options:");
            System.out.println("1. View Total Walk-ins");
            System.out.println("2. View Total Deliveries");
            System.out.println("3. View Deliveries by Barangay");
            System.out.println("4. View Overall Sales");
            System.out.println("5. View Summary by Container");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  

            switch (option) {
                case 1 -> showTotalWalkIns();
                case 2 -> showTotalDeliveries();
                case 3 -> showDeliveriesByBarangay();
                case 4 -> showOverallSales();
                case 5 -> showSummaryByContainer();
                case 6 -> {
                    showMenu = false;
                    System.out.println("Logged out successfully.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

   
    private void showTotalWalkIns() {
        int totalWalkIns = system.getWalkInCount();  
        System.out.println("Total Walk-ins: " + totalWalkIns);
    }

  
    private void showTotalDeliveries() {
        int totalDeliveries = system.getTotalDeliveries();  
        System.out.println("Total Deliveries: " + totalDeliveries);
    }

    private void showDeliveriesByBarangay() {
        System.out.println("Deliveries by Barangay:");
        system.getDeliveriesByBarangay().forEach((barangay, count) -> {
            System.out.println(barangay + ": " + count);
        });
    }

   
    private void showOverallSales() {
        System.out.println("Overall Sales: P" + system.getOverallSales());
    }

 
    private void showSummaryByContainer() {
        System.out.println("Container Summary:");
        system.getContainerSummary().forEach((container, count) -> {
            System.out.println(container + ": " + count);
        });
    }
}
