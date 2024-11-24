package waterrefillingsalesystem;

import java.sql.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import DBConnector.ControlConnector;  

public class AdminPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;
    private final Map<String, Admin> admins;
    private final Connection connection;  

    
    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
        this.admins = new HashMap<>();
        this.connection = ControlConnector.getCon(); 
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

       
        String query = "SELECT * FROM Admin WHERE Username = ? AND Password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful!");
                showAdminOptions();
            } else {
                System.out.println("Invalid credentials.");
                forgotCredentials(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

       
        String query = "UPDATE Admin SET Username = ?, Password = ? WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newUsername);
            stmt.setString(2, newPassword);  
            stmt.setString(3, username);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Credentials updated successfully.");
            } else {
                System.out.println("Admin not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

     
        String query = "INSERT INTO Admin (Name, Username, Email, Password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, password);  
            stmt.executeUpdate();
            System.out.println("Sign-up successful! You can now login.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private void showAdminOptions() {
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
        String query = "SELECT TotalGcashPayments FROM SystemMetrics WHERE DateRecorded = CURRENT_DATE";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double totalGcashPayments = rs.getDouble("TotalGcashPayments");
                System.out.println("Total GCash Payments: P" + totalGcashPayments);
            } else {
                System.out.println("No data found for today's GCash payments.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    private void showTotalWalkIns() {
        String query = "SELECT TotalWalkIns FROM SystemMetrics WHERE DateRecorded = CURRENT_DATE";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int totalWalkIns = rs.getInt("TotalWalkIns");
                System.out.println("Total Walk-ins (Cash on Pickup): " + totalWalkIns);
            } else {
                System.out.println("No data found for today's walk-ins.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void showTotalDeliveries() {
        String query = "SELECT TotalDeliveries FROM SystemMetrics WHERE DateRecorded = CURRENT_DATE";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int totalDeliveries = rs.getInt("TotalDeliveries");
                System.out.println("Total Deliveries (Cash on Delivery): " + totalDeliveries);
            } else {
                System.out.println("No data found for today's deliveries.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void showDeliveriesByBarangay() {
        String query = "SELECT BarangayName, DeliveryCount FROM BarangayDeliveries";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Deliveries by Barangay:");
            while (rs.next()) {
                String barangayName = rs.getString("BarangayName");
                int deliveryCount = rs.getInt("DeliveryCount");
                System.out.println(barangayName + ": " + deliveryCount + " orders");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void showOverallSales() {
        String query = "SELECT TotalSales FROM SystemMetrics WHERE DateRecorded = CURRENT_DATE";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double totalSales = rs.getDouble("TotalSales");
                System.out.println("Overall Sales: P" + totalSales);
            } else {
                System.out.println("No data found for today's overall sales.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void showSummaryByContainer() {
        String query = "SELECT ContainerType, TotalQuantitySold FROM ContainerSummary";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Container Summary:");
            while (rs.next()) {
                String containerType = rs.getString("ContainerType");
                int totalQuantitySold = rs.getInt("TotalQuantitySold");
                System.out.println(containerType + ": " + totalQuantitySold);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void showSalesPerBarangayByDay() {
        String query = "SELECT SalesDate, SalesByBarangay FROM DailySales";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Sales Per Barangay by Day:");
            while (rs.next()) {
                String salesDate = rs.getString("SalesDate");
                String salesByBarangayJson = rs.getString("SalesByBarangay");
                
                System.out.println("Date: " + salesDate);
                displaySalesByBarangay(salesByBarangayJson); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displaySalesByBarangay(String salesJson) {
        try {
            JSONObject jsonObject = new JSONObject(salesJson);  
            jsonObject.keys().forEachRemaining(barangay -> {
                System.out.println(barangay + ": P" + jsonObject.getDouble(barangay));
            });
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
