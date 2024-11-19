
package waterrefillingsalesystem;

public class Employee {
    public String EmployeeDesk;
    public String EmployeeDelivered;

    public Employee(String EmployeeDesk, String EmployeeDelivered) {
        this.EmployeeDesk = EmployeeDesk;
        this.EmployeeDelivered = EmployeeDelivered;
    }

    public String getEmployeeDesk() {
        return EmployeeDesk;
    }

    public void setEmployeeDesk(String EmployeeDesk) {
        this.EmployeeDesk = EmployeeDesk;
    }

    public String getEmployeeDelivered() {
        return EmployeeDelivered;
    }

    public void setEmployeeDelivered(String EmployeeDelivered) {
        this.EmployeeDelivered = EmployeeDelivered;
    }
    
}

    