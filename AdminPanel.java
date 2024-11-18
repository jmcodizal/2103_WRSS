package waterrefillingsalesystem;


import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminPanel extends JFrame {
     public AdminPanel() {
        setTitle("Admin Panel");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
      
        Scanner scanner = new Scanner(System.in);

        String correctEmail = "";
        String correctPassword = "";

        System.out.println("Welcome to the Admin Panel");
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        if (email.equals(correctEmail) && password.equals(correctPassword)) {
            System.out.println("Login successful! Welcome to the Admin Panel.");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
        scanner.close();
    }

    AdminPanel(Scanner scanner, WaterRefillingSystem system) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void showMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

    