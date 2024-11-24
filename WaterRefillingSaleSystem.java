package waterrefillingsalesystem;

import java.sql.Connection;
import java.util.Scanner;
import DBConnector.ControlConnector; 

public class WaterRefillingSaleSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
        
            Connection connection = ControlConnector.getCon(); 

            
           WaterRefillingSystem system = new WaterRefillingSystem(connection);  


           
            boolean exitSystem = false;

            while (!exitSystem) {
                System.out.println("\nWelcome to Water Refilling System");
                System.out.println("1. Customer Panel");
                System.out.println("2. Admin Panel");
                System.out.println("3. Exit");
                System.out.print("Select your role: ");
                int role = scanner.nextInt();
                scanner.nextLine();

                switch (role) {
                    case 1:
                       
                        CustomerPanel customerPanel = new CustomerPanel(scanner, system, connection); 
                        customerPanel.showMenu();
                        break;

                    case 2:
                       
                        AdminPanel adminPanel = new AdminPanel(scanner, system);
                        adminPanel.showAdminMenu();
                        break;

                    case 3:
                       
                        exitSystem = true;
                        System.out.println("Exiting the system...");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

           
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
