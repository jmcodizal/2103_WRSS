
package DBConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Control_connector {
    private static Control_connector connection;
    private static Control_connector dbconec;
    
    private static String url = "jdbc:mysql://localhost:waterrefilling03";
    private static String user = "root";
    private static String password = "";
    
    public Control_connector(){
        connection = null;
        
        try {
            DriverManager.getConnection(url, user, password);
        } catch (SQLException e) { 
             System.out.println("Connection failed!");
            e.printStackTrace();
        } 
    }
}
