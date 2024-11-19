package waterrefillingsalesystem;

import java.util.Scanner;

public class WaterRefillingSaleSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WaterRefillingSystem system = new WaterRefillingSystem();

        while (true) {
            System.out.println("[1] Customer Panel");
            System.out.println("[2] Admin Panel");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1 -> {
                    System.out.println("Entering Customer Panel...");
                    CustomerPanel customerPanel = new CustomerPanel(scanner, system);
                    customerPanel.showMenu();
                }
                case 2 -> {
                    System.out.println("Entering Admin Panel...");
                    AdminPanel adminPanel = new AdminPanel(scanner, system);
                    adminPanel.showMenu();
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
