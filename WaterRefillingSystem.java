package waterrefillingsalesystem;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class WaterRefillingSystem {
    private final List<Customer> customers;
    private final Map<String, Integer> deliveries;
    private double totalSales;

    public WaterRefillingSystem() {
        customers = new ArrayList<>();
        deliveries = new HashMap<>();
        totalSales = 0.0;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        recordSale(customer);
        incrementDeliveryCount(customer.getAddress());
    }

    private void recordSale(Customer customer) {
        double price = 0;
        switch (customer.getContainerType()) {
            case "Small": price = 20.0; break;
            case "Medium": price = 30.0; break;
            case "Large": price = 40.0; break;
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
        totalSales += price;
        System.out.println("Sale recorded: P" + price);
    }

    private void incrementDeliveryCount(String address) {
        deliveries.put(address, deliveries.getOrDefault(address, 0) + 1);
    }

    public int getWalkInCount() {
        return (int) customers.stream().filter(c -> c.getPaymentMethod().equals("Cash on Pick-Up")).count();
    }

    public int getDeliveryCount() {
        return (int) customers.stream().filter(c -> c.getPaymentMethod().equals("Cash on Delivery")).count();
    }

    public Map<String, Integer> getDeliveriesByBarangay() {
        return deliveries;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void summaryByContainer() {
        Map<String, Integer> containerSummary = new HashMap<>();
        for (Customer customer : customers) {
            containerSummary.put(customer.getContainerType(),
                containerSummary.getOrDefault(customer.getContainerType(), 0) + 1);
        }
        System.out.println("Container Summary: " + containerSummary);
    }
}
