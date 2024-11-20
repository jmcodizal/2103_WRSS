package waterrefillingsalesystem;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class WaterRefillingSystem {
    private final List<Customer> customers; 
    private final Map<String, Integer> deliveries; 
    private final Map<String, Integer> containerSummary; 
    private double overallSales;

    public WaterRefillingSystem() {
        customers = new ArrayList<>();
        deliveries = new HashMap<>();
        containerSummary = new HashMap<>();
        overallSales = 0.0;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        recordSale(customer);  
        incrementDeliveryCount(customer);  
        updateContainerSummary(customer);  
    }

   
    private void recordSale(Customer customer) {
        double price = 0;
        switch (customer.getContainerType()) {
            case "Small" -> price = 20.00;
            case "Medium" -> price = 30.00;
            case "Large" -> price = 40.00;
            case "Extra Large" -> price = 50.00;
        }
        price *= customer.getQuantity();

        if ("GCash".equals(customer.getPaymentMethod())) {
            double amountPaid = customer.getGcashAmount();
            double remainingAmount = price - amountPaid;
            if (remainingAmount > 0) {
                System.out.println("Remaining balance: P" + remainingAmount);
            } else {
                System.out.println("Change: P" + Math.abs(remainingAmount));
            }
            price = amountPaid;
        }
        overallSales += price;  
        System.out.println("Sale recorded: P" + price);
    }

   
    private void incrementDeliveryCount(Customer customer) {
        String barangay = customer.getAddress();
        if ("Cash on Delivery".equalsIgnoreCase(customer.getPaymentMethod()) || 
            "Cash on Pick-Up".equalsIgnoreCase(customer.getPaymentMethod())) {
            deliveries.put(barangay, deliveries.getOrDefault(barangay, 0) + 1);
        }
    }

   
    private void updateContainerSummary(Customer customer) {
        String containerType = customer.getContainerType();
        containerSummary.put(containerType, containerSummary.getOrDefault(containerType, 0) + customer.getQuantity());
    }

    
    public double getOverallSales() {
        return overallSales;
    }

  
    public Map<String, Integer> getDeliveriesByBarangay() {
        return deliveries;
    }

   
    public Map<String, Integer> getContainerSummary() {
        return containerSummary;
    }

   
    public int getWalkInCount() {
        int walkInCount = 0;
        for (Customer customer : customers) {
            if ("Cash on Pick-Up".equalsIgnoreCase(customer.getPaymentMethod())) {
                walkInCount++;
            }
        }
        return walkInCount;
    }

 
    public int getTotalDeliveries() {
        int deliveryCount = 0;
        for (Customer customer : customers) {
            if ("Cash on Delivery".equalsIgnoreCase(customer.getPaymentMethod())) {
                deliveryCount++;
            }
        }
        return deliveryCount;
    }
}
