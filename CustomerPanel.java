
package waterrefillingsalesystem;

import java.util.Scanner;

public class CustomerPanel {
    private final Scanner scanner;
    private final WaterRefillingSystem system;

    public CustomerPanel(Scanner scanner, WaterRefillingSystem system) {
        this.scanner = scanner;
        this.system = system;
    }

    public void showMenu() {
        String name = "", address = "", containerType = "", paymentMethod = "", gcashNumber = "", gcashName = "";
        int gcashAmount = 0, quantity = 0;  
        boolean validOrder = false;

        while (!validOrder) {
            System.out.println("1. Enter Customer Name");
            System.out.println("2. Select Barangay Address");
            System.out.println("3. Select Container Type and Quantity");
            System.out.println("4. Choose Payment Method");
            System.out.println("5. Cancel Order");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1 -> {
                    System.out.print("Enter Customer Name: ");
                    name = scanner.nextLine();
                }
                case 2 -> {
                    System.out.println("Select Barangay Address (Poblacion 1 - 12): ");
                    for (int i = 1; i <= 12; i++) {
                        System.out.println("[" + i + "] Poblacion " + i);
                    }
                    int barangayChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (barangayChoice >= 1 && barangayChoice <= 12) {
                        address = "Poblacion " + barangayChoice;
                    } else {
                        System.out.println("Invalid barangay choice.");
                        continue; 
                    }
                }
                case 3 -> {
                    containerType = selectContainer(scanner);
                    System.out.print("Enter Quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                }
                case 4 -> paymentMethod = selectPaymentMethod(scanner, gcashNumber, gcashName, gcashAmount);
                case 5 -> {
                    System.out.println("Order has been canceled.");
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    continue; 
                }
            }

            if (isValidOrder(name, address, containerType, paymentMethod)) {
                validOrder = true; 
                system.addCustomer(new Customer(name, address, containerType, paymentMethod, gcashNumber, gcashName, gcashAmount, quantity));
                System.out.println("Your refill order is placed successfully!");
            }
        }

        
        System.out.print("Do you want to make another refill order? (yes/no): ");
        String repeatOrder = scanner.nextLine();
        if (repeatOrder.equalsIgnoreCase("yes")) {
            showMenu(); 
        }
    }

    private boolean isValidOrder(String name, String address, String containerType, String paymentMethod) {
        return !name.isEmpty() && !address.isEmpty() && !containerType.isEmpty() && !paymentMethod.isEmpty();
    }

    private String selectContainer(Scanner scanner) {
        System.out.println("Select Container Type:");
        System.out.println("[1] Small - P20.00");
        System.out.println("[2] Medium - P30.00");
        System.out.println("[3] Large - P40.00");
        System.out.println("[4] Extra Large - P50.00");
        int containerChoice = scanner.nextInt();
        scanner.nextLine(); 
        switch (containerChoice) {
            case 1 -> {
                return "Small";
            }
            case 2 -> { 
                return "Medium";
            }
            case 3 -> {
                return "Large";
            }
            case 4 -> {
                return "Extra Large";
            }
            default -> {
                System.out.println("Invalid container choice.");
                return selectContainer(scanner); 
            }
        }
    }

    private String selectPaymentMethod(Scanner scanner, String gcashNumber, String gcashName, int gcashAmount) {
        System.out.println("Select Payment Method:");
        System.out.println("[1] Cash on Delivery");
        System.out.println("[2] Cash on Pick-Up");
        System.out.println("[3] GCash");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); 
        switch (paymentChoice) {
            case 1 -> {
                return "Cash on Delivery";
            }
            case 2 -> {
                return "Cash on Pick-Up";
            }
            case 3 -> {
                System.out.print("Enter GCash Number: ");
                gcashNumber = scanner.nextLine();
                System.out.print("Enter GCash Name: ");
                gcashName = scanner.nextLine();
                System.out.print("Enter Amount: ");
                gcashAmount = scanner.nextInt();
                scanner.nextLine();  
                return "GCash";
            }
            default -> {
                System.out.println("Invalid payment option.");
                return selectPaymentMethod(scanner, gcashNumber, gcashName, gcashAmount);
            }
        }
    }
}

