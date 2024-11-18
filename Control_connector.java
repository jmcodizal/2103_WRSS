
package DBConnector;
import java.sql.*;
import java.sql.DriverManager;

public class Control_connector {
    public static Connection getCon() {
        try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/wrss?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
           return con;
        }
        catch(Exception e) {

        return null;
        }
    }
}
