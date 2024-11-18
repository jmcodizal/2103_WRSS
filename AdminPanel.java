package waterrefillingsalesystem;

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame {

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Constructor to initialize AdminPanel with scanner and system
    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        setTitle("Admin Panel");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Implementing logic to show menu (assuming it's a basic text-based option)
        showMenu(scanner, system);
    }

    public void showMenu(Scanner scanner, WaterRefillingSystem system) {
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Walk-in Count");
            System.out.println("2. View Delivery Count");
            System.out.println("3. View Deliveries by Barangay");
            System.out.println("4. View Total Sales");
            System.out.println("5. View Summary by Container");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Walk-in count: " + system.getWalkInCount());
                    break;
                case 2:
                    System.out.println("Delivery count: " + system.getDeliveryCount());
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
                    System.out.println("Exiting Admin Panel.");
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    public static void main(String[] args) {
        // This is just an example to show the functionality. Can be adapted.
        Scanner scanner = new Scanner(System.in);
        WaterRefillingSystem system = new WaterRefillingSystem();
        AdminPanel adminPanel = new AdminPanel(scanner, system);
        adminPanel.setVisible(true);
    }

    void showMenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
