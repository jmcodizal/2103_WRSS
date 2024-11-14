
package waterrefillingsalesystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeCustomer extends JFrame {

    public WelcomeCustomer() {
        setTitle("Welcome Customer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JButton startButton = new JButton("Click Here to Start");

        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                CustomerPanel customerPanel = new CustomerPanel();
                customerPanel.setVisible(true);  
                dispose(); 
            }
        });

        
        JPanel panel = new JPanel();
        panel.add(startButton);  

        
        add(panel);

        
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeCustomer().setVisible(true);
            }
        });
    }
}

