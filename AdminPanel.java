package waterrefillingsalesystem;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;
    private Admin admin;  

    public AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
    }

    public void showMenu() {
        while (true) {
            System.out.println("[1] Log in");
            System.out.println("[2] Sign up");
            System.out.println("[3] Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> login();
                case 2 -> signUp();
                case 3 -> {
                    System.out.println("Exiting the Admin Panel...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again!");
            }
        }
    }

    private void login() {
        if (admin == null) {
            System.out.println("No admin account found! Please try to sign up first.");
            return;  
        }

        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

          
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome to the Admin Dashboard.");
                
                adminMenu();  
                break; 
            } else {
                System.out.println("Incorrect username or password!");
                System.out.print("Do you want to recover your acoount password? (yes/no): ");
                String answer = scanner.nextLine().toLowerCase();
                if (answer.equals("yes")) {
                    recoverPassword(); 
                    break;
                } else {
                    System.out.println("Please try to login again.");
                }
            }
        }
    }

    private void recoverPassword() {
        System.out.println("Password recovery:");

      
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

       
        if (admin.getName().equals(name) && admin.getEmail().equals(email)) {
            System.out.println("The name and email you provide are match. You can now reset your password.");
            
          
            System.out.print("New password: ");
            String newPassword = scanner.nextLine();

           
            admin.setPassword(newPassword);
            System.out.println("Your new password has been successfully updated!");
            
          
            adminMenu();
        } else {
            System.out.println("Incorrect name or email! Please check your information and try again!");
        }
    }

    private void signUp() {
        if (admin != null) {
            System.out.println("The admin account already exists! Please log in.");
            return;  
        }

        System.out.println("Sign Up for Admin Account:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

      
        admin = new Admin(name, username, email, password);
        System.out.println("Sign Up successful! You can now log in.");
    }

  
    private void adminMenu() {
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Walk-in Count");
            System.out.println("2. View Delivery Count");
            System.out.println("3. View Deliveries per Barangay");
            System.out.println("4. View Overall Sales");
            System.out.println("5. View Summary by Container");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> System.out.println("Walk-in count: " + system.getWalkInCount());
                case 2 -> System.out.println("Delivery count: " + system.getDeliveryCount());
                case 3 -> System.out.println("Deliveries per Barangay: " + system.getDeliveriesPerBarangay());
                case 4 -> System.out.println("Overall Sales: " + system.getOverallSales());
                case 5 -> system.summaryByContainer();
                case 6 -> {
                    System.out.println("Exiting Admin Panel....");
                    validChoice = true;  
                }
                default -> System.out.println("Invalid Choice. Please try again.");
            }
        }
    }
}