package waterrefillingsalesystem;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;

    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
    }

    void showAdminMenu() {
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
            System.out.println("5. View Total Sales Per Day");
            System.out.println("6. View Summary by Container");
            System.out.println("7. View Total GCash Payments");
            System.out.println("8. Logout");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1 -> showTotalWalkIns();
                case 2 -> showTotalDeliveries();
                case 3 -> showDeliveriesByBarangay();
                case 4 -> showOverallSales();
                case 5 -> showTotalSalesPerDay();
                case 6 -> showSummaryByContainer();
                case 7 -> showTotalGcashPayments(); 
                case 8 -> {
                    showMenu = false;
                    System.out.println("Logged out successfully.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showTotalGcashPayments() {
        double totalGcashPayments = system.getTotalGcashPayments();
        System.out.println("Total GCash Payments: P" + totalGcashPayments);
    }

    private void showTotalSalesPerDay() {
        System.out.println("Total Sales Per Day:");
        system.getDailySales().forEach((date, sales) -> {
            System.out.println(date + ": P" + sales);
        });
    }

    private void showTotalWalkIns() {
        int totalWalkIns = system.getWalkInCount();  
        System.out.println("Total Walk-ins (Cash on Pickup): " + totalWalkIns);
    }

    private void showTotalDeliveries() {
        int totalDeliveries = system.getTotalDeliveries();  
        System.out.println("Total Deliveries (Cash on Delivery): " + totalDeliveries);
    }

    private void showDeliveriesByBarangay() {
        System.out.println("Deliveries by Barangay:");
        system.getDeliveriesByBarangay().forEach((barangay, count) -> {
            System.out.println(barangay + ": " + count + " orders");
        });
    }

    private void showOverallSales() {
        double overallSales = system.getOverallSales();
        System.out.println("Overall Sales: P" + overallSales);
    }

    private void showSummaryByContainer() {
        System.out.println("Container Summary:");
        system.getContainerSummary().forEach((container, count) -> {
            System.out.println(container + ": " + count);
        });
    }
}
