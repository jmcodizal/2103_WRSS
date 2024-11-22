package waterrefillingsalesystem;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class AdminPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;
    private Map<String, Admin> admins;

    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
        this.admins = new HashMap<>();
    }

    void showAdminMenu() {
        boolean showMenu = true;

        while (showMenu) {
            System.out.println("Admin Panel - Login / Sign-Up");
            System.out.println("1. Login");
            System.out.println("2. Sign-Up");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> login();
                case 2 -> signUp();
                case 3 -> {
                    showMenu = false;
                    System.out.println("Exiting Admin Panel.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Admin admin = admins.get(username);
        
        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Login successful!");
            showAdminOptions(admin); 
        } else {
            System.out.println("Invalid credentials.");
            forgotCredentials(username);
        }
    }

    private void forgotCredentials(String username) {
        System.out.println("Do you want to reset your credentials?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 
        
        if (option == 1) {
            resetCredentials(username);
        } else {
            System.out.println("Returning to the menu.");
        }
    }

    private void resetCredentials(String username) {
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        Admin admin = admins.get(username);
        if (admin != null) {
            admin.setPassword(newPassword); 
            System.out.println("Password updated successfully.");
            admins.remove(username);  
            admins.put(newUsername, admin); 
        } else {
            System.out.println("No such username found.");
        }
    }

    private void signUp() {
        System.out.println("Sign-Up for Admin Account");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        Admin newAdmin = new Admin(name, username, email, password);
        admins.put(username, newAdmin);
        
        System.out.println("Sign-up successful! You can now login.");
    }

    private void showAdminOptions(Admin admin) {
    boolean showMenu = true;
    
    while (showMenu) {
        System.out.println("\nAdmin Options:");
        System.out.println("1. View Total Walk-ins");
        System.out.println("2. View Total Deliveries");
        System.out.println("3. View Deliveries by Barangay");
        System.out.println("4. View Overall Sales");
        System.out.println("5. View Sales Per Barangay by Day");  
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
            case 5 -> showSalesPerBarangayByDay();  
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
    
   private void showSalesPerBarangayByDay() {
    System.out.println("Sales Per Barangay by Day:");

 
    String[] barangays = {
        "Poblacion 1", "Poblacion 2", "Poblacion 3", "Poblacion 4", "Poblacion 5",
        "Poblacion 6", "Poblacion 7", "Poblacion 8", "Poblacion 9", "Poblacion 10",
        "Poblacion 11", "Poblacion 12"
    };

  
    for (String barangay : barangays) {
        double totalSalesForBarangay = 0.0;

        
        Map<String, Double> salesByDate = system.getSalesByBarangayPerDay().get(barangay);

        
        if (salesByDate != null && !salesByDate.isEmpty()) {
            System.out.println("Barangay: " + barangay);
            
            for (Map.Entry<String, Double> entry : salesByDate.entrySet()) {
                String date = entry.getKey();
                double sales = entry.getValue();
                System.out.println("  Date: " + date + " - Total Sales: P" + sales);
                totalSalesForBarangay += sales;  
            }
        } else {
           
            System.out.println("Barangay: " + barangay + " has no sales recorded.");
        }

      
        System.out.println("  Total Sales for " + barangay + ": P" + totalSalesForBarangay);
    }
}

}



