
package waterrefillingsalesystem;

public class Customer {
    private final String name;
    private final String address;
    private final String containerType;
    private final String paymentMethod;
    private final String gcashNumber;
    private final String gcashName;
    private final int gcashAmount;
    private final int quantity;

   
    public Customer(String name, String address, String containerType, String paymentMethod, String gcashNumber, 
                    String gcashName, int gcashAmount, int quantity) {
        this.name = name;
        this.address = address;
        this.containerType = containerType;
        this.paymentMethod = paymentMethod;
        this.gcashNumber = gcashNumber;
        this.gcashName = gcashName;
        this.gcashAmount = gcashAmount;
        this.quantity = quantity;
    }

    
    public String getAddress() {
        return address;
    }

   
    public String getContainerType() {
        return containerType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getGcashAmount() {
        return gcashAmount;
    }

    public int getQuantity() {
        return quantity;
    }
}
