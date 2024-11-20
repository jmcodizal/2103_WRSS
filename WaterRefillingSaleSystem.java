package waterrefillingsalesystem;

import java.util.Scanner;

public class WaterRefillingSaleSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            WaterRefillingSystem system = new WaterRefillingSystem();
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
                    case 1 -> {
                        CustomerPanel customerPanel = new CustomerPanel(scanner, system);
                        customerPanel.showMenu();
                    }
                    case 2 -> {
                        AdminPanel adminPanel = new AdminPanel(scanner, system);
                        adminPanel.showAdminMenu();
                    }
                    case 3 -> {
                        exitSystem = true;
                        System.out.println("Exiting the system...");
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
}
