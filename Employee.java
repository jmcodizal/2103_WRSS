
package waterrefillingsalesystem;

public class Employee {
    public String EmployeeRefilling;
    public String EmployeeDelivered;

    public Employee(String EmployeeRefilling, String EmployeeDelivered) {
        this.EmployeeRefilling = EmployeeRefilling;
        this.EmployeeDelivered = EmployeeDelivered;
    }

    public String getEmployeeRefilling() {
        return EmployeeRefilling;
    }

    public void setEmployeeRefilling(String EmployeeRefilling) {
        this.EmployeeRefilling = EmployeeRefilling;
    }

    public String getEmployeeDelivered() {
        return EmployeeDelivered;
    }

    public void setEmployeeDelivered(String EmployeeDelivered) {
        this.EmployeeDelivered = EmployeeDelivered;
    }
    
}

    