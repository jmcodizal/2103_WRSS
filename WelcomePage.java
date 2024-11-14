package waterrefillingsalesystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {

    public WelcomePage() {
        setTitle("Water Refilling Sales System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
        setLayout(null);
        setSize(500, 400); 
        setLocationRelativeTo(null);

        
        JButton customerButton = new JButton("CUSTOMER");
        customerButton.setBounds(100, 150, 200, 40);
        customerButton.setBackground(new java.awt.Color(204, 204, 204)); 
        customerButton.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18)); 

        
        JButton adminButton = new JButton("ADMIN");
        adminButton.setBounds(100, 210, 200, 40);
        adminButton.setBackground(new java.awt.Color(204, 204, 204)); // Light gray
        adminButton.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18)); 

        
        JLabel titleLabel = new JLabel("WATER REFILLING SALES SYSTEM", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24));
        titleLabel.setForeground(new java.awt.Color(0, 153, 153)); // Title in teal color
        titleLabel.setBounds(50, 50, 400, 40); // Positioning title in the center

        
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                WelcomeCustomer welcomeCustomer = new WelcomeCustomer();
                welcomeCustomer.setVisible(true); 
                dispose(); 
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                AdminPanel adminPanel = new AdminPanel();
                adminPanel.setVisible(true);  
                dispose();  
            }
        });

        
        getContentPane().setBackground(new java.awt.Color(204, 255, 255)); // Light cyan background

       
        add(titleLabel);
        add(customerButton);
        add(adminButton);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }
}
