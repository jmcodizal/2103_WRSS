package waterrefillingsalesystem;

import DBConnector.ControlConnector;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;


public class WaterRefillingSystem {
    private final List<Customer> customers;
    private final Map<String, Integer> deliveries;
    private final Map<String, Integer> containerSummary;
    private final Map<String, Double> dailySales;
    private final double overallSales;
    private final int walkInCount;
    private final int totalDeliveries;
    private final Map<String, Integer> deliveriesByBarangay;
    private final double totalGcashPayments;
    private final Map<String, Map<String, Double>> salesByBarangayPerDay;

    private final Connection connection; // Database connection

    // Constructor that accepts a connection
    public WaterRefillingSystem(Connection connection) {
        customers = new ArrayList<>();
        deliveries = new HashMap<>();
        containerSummary = new HashMap<>();
        dailySales = new HashMap<>();
        deliveriesByBarangay = new HashMap<>();
        salesByBarangayPerDay = new HashMap<>();
        overallSales = 0.0;
        walkInCount = 0;
        totalDeliveries = 0;
        totalGcashPayments = 0.0;
        this.connection = connection; 
    }
   
    public void addCustomer(Customer customer) {
        String insertCustomerQuery = "INSERT INTO Customer (Name, Barangay, ContainerType, Quantity, PaymentMethod, GcashAmount, DeliveryDate, SalesDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertCustomerQuery, Statement.RETURN_GENERATED_KEYS)) {
           
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getBarangay());
            stmt.setString(3, customer.getContainerType());
            stmt.setInt(4, customer.getQuantity());
            stmt.setString(5, customer.getPaymentMethod());
            stmt.setDouble(6, customer.getGcashAmount());

           
            stmt.setDate(7, Date.valueOf(customer.getDeliveryDate()));  
            stmt.setDate(8, Date.valueOf(customer.getSalesDate()));   

          
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
              
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int customerID = rs.getInt(1); 

                       
                        String insertSaleQuery = "INSERT INTO Sales (CustomerID, SaleAmount, SaleDate, Barangay, ContainerType, Quantity, PaymentMethod, GcashAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement saleStmt = connection.prepareStatement(insertSaleQuery)) {
                            double saleAmount = calculateSaleAmount(customer);  
                            saleStmt.setInt(1, customerID);
                            saleStmt.setDouble(2, saleAmount);

                           
                            saleStmt.setDate(3, Date.valueOf(customer.getSalesDate())); 
                            saleStmt.setString(4, customer.getBarangay());
                            saleStmt.setString(5, customer.getContainerType());
                            saleStmt.setInt(6, customer.getQuantity());
                            saleStmt.setString(7, customer.getPaymentMethod());
                            saleStmt.setDouble(8, customer.getGcashAmount());

                            
                            saleStmt.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private double calculateSaleAmount(Customer customer) {
        double price = 0.0;
        switch (customer.getContainerType()) {
            case "Small" -> price = 20.00;
            case "Medium" -> price = 30.00;
            case "Large" -> price = 40.00;
            case "Extra Large" -> price = 50.00;
            default -> price = 0.0;
        }
        return price * customer.getQuantity();
    }

    
}
