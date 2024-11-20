package waterrefillingsalesystem;

public class Customer {
    private String name;
    private String containerType;
    private int quantity;
    private String paymentMethod;
    private String address;
    private double gcashAmount;

   
    public Customer(String name, String containerType, int quantity, String paymentMethod, String address) {
        this.name = name;
        this.containerType = containerType;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
        this.address = address;

        if ("GCash".equals(paymentMethod)) {
          
            System.out.print("Enter GCash Amount: ");
            this.gcashAmount = new java.util.Scanner(System.in).nextDouble();
        }
    }

   
    public String getName() {
        return name;
    }

    public String getContainerType() {
        return containerType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getAddress() {
        return address;
    }

    public double getGcashAmount() {
        return gcashAmount;
    }
}
