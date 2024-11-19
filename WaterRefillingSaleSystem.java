
package waterrefillingsalesystem;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WaterRefillingSaleSystem {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/waterrefilling03";
        String user = "root";
        String password = "";
        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the MySQL database");
        } catch (SQLException e){
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        
        Scanner scanner = new Scanner(System.in);
        WaterRefillingSystem system = new WaterRefillingSystem();

        while (true) {
            System.out.println("1. Customer Panel");
            System.out.println("2. Admin Panel");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (option) {
                case 1:
                    // Customer Panel
                    System.out.println("Entering Customer Panel...");
                    CustomerPanel customerPanel = new CustomerPanel(scanner, system);
                    customerPanel.showMenu();
                    break;
                case 2:
                    // Admin Panel
                    System.out.println("Entering Admin Panel...");
                    AdminPanel adminPanel = new AdminPanel(scanner, system);
                    adminPanel.showMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}



