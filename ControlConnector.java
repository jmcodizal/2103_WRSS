package DBConnector;

import java.sql.*;
import javax.swing.JOptionPane;

public class ControlConnector {
    public static Connection getCon() {
        Connection con = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 

          
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/waterrefilling03", "root", "");

           
            JOptionPane.showMessageDialog(null, "Connected to Database!");
        } catch (Exception e) {
          
            JOptionPane.showMessageDialog(null, "Failed to Connect to Database\n" + e.getMessage());
        }
        return con; 
    }
}
