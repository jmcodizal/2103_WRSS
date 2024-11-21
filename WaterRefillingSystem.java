package waterrefillingsalesystem;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class WaterRefillingSystem {
    private final List<Customer> customers;
    private final Map<String, Integer> deliveries;
    private final Map<String, Integer> containerSummary;
    private final Map<String, Double> dailySales;
    private double overallSales;  // This will only include container sales
    private int walkInCount;      // Track customers with Cash on Pickup
    private int totalDeliveries;  // Track customers with Cash on Delivery
    private final Map<String, Integer> deliveriesByBarangay;
    private double totalGcashPayments;  // Track total GCash payments

    public WaterRefillingSystem() {
        customers = new ArrayList<>();
        deliveries = new HashMap<>();
        containerSummary = new HashMap<>();
        dailySales = new HashMap<>();
        deliveriesByBarangay = new HashMap<>();
        overallSales = 0.0;
        walkInCount = 0;
        totalDeliveries = 0;
        totalGcashPayments = 0.0;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        recordSale(customer);
        incrementDeliveryCount(customer);
        updateContainerSummary(customer);

       
        String deliveryDate = customer.getDeliveryDate();
        deliveries.put(deliveryDate, deliveries.getOrDefault(deliveryDate, 0) + 1);

       
        String barangay = customer.getBarangay();
        deliveriesByBarangay.put(barangay, deliveriesByBarangay.getOrDefault(barangay, 0) + 1);

       
        if ("Cash on Pickup".equals(customer.getPaymentMethod())) {
            walkInCount++;
        } else if ("Cash on Delivery".equals(customer.getPaymentMethod())) {
            totalDeliveries++;
        }

      
        if ("GCash".equals(customer.getPaymentMethod())) {
            totalGcashPayments += customer.getGcashAmount();
        }
    }

    private void recordSale(Customer customer) {
        double containerSales = 0.0;

        
        switch (customer.getContainerType()) {
            case "Small" -> containerSales = 20.00 * customer.getQuantity();
            case "Medium" -> containerSales = 30.00 * customer.getQuantity();
            case "Large" -> containerSales = 40.00 * customer.getQuantity();
            case "Extra Large" -> containerSales = 50.00 * customer.getQuantity();
            default -> System.out.println("Invalid container type for customer: " + customer.getName());
        }

        
        overallSales += containerSales;

      
        String today = java.time.LocalDate.now().toString();
        dailySales.put(today, dailySales.getOrDefault(today, 0.0) + containerSales);

       
        if ("GCash".equals(customer.getPaymentMethod())) {
            totalGcashPayments += customer.getGcashAmount();
        }

        System.out.println("Sale recorded: P" + containerSales);
    }

    public double getTotalGcashPayments() {
        return totalGcashPayments; 
    }

    private void incrementDeliveryCount(Customer customer) {
        String deliveryDate = customer.getDeliveryDate();
        deliveries.put(deliveryDate, deliveries.getOrDefault(deliveryDate, 0) + 1);
    }

    private void updateContainerSummary(Customer customer) {
        String containerType = customer.getContainerType();
        containerSummary.put(containerType, containerSummary.getOrDefault(containerType, 0) + customer.getQuantity());
    }

   
    public double getOverallSales() {
        return overallSales;
    }

    public Map<String, Double> getDailySales() {
        return dailySales;
    }

    public Map<String, Integer> getDeliveriesByBarangay() {
        return deliveriesByBarangay;
    }

    public Map<String, Integer> getDeliveriesByDate() {
        return deliveries;
    }

    public Map<String, Integer> getContainerSummary() {
        return containerSummary;
    }

    public int getWalkInCount() {
        return walkInCount;
    }

    public int getTotalDeliveries() {
        return totalDeliveries; 
    }
}
