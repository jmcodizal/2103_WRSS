package waterrefillingsalesystem;

public class Customer {
    private final String name;
    private final String containerType;
    private final int quantity;
    private final String paymentMethod;
    private final String barangay;
    private final String contactNumber;
    private double gcashAmount;
    private final String deliveryDate;
    private final String gcashNumber;
    private final String gcashName;

    public Customer(String name, String containerType, int quantity, String paymentMethod, String contactNumber, String barangay, String deliveryDate, String gcashNumber, String gcashName, double gcashAmount) {
        this.name = name;
        this.containerType = containerType;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
        this.contactNumber = contactNumber;
        this.barangay = barangay;
        this.deliveryDate = deliveryDate;
        this.gcashNumber = gcashNumber;
        this.gcashName = gcashName;
        this.gcashAmount = gcashAmount;
    }

    public String getName() {
        return name;
    }

    public String getBarangay() {
        return barangay;
    }

    public String getContactNumber() {
        return contactNumber;
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

    public double getGcashAmount() {
        return gcashAmount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getGcashNumber() {
        return gcashNumber;
    }

    public String getGcashName() {
        return gcashName;
    }
}
