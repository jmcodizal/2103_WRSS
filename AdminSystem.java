
package waterrefillingsalesystem;

import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSystem {
    private final WaterRefillingSystem system;

   
    public AdminSystem(WaterRefillingSystem system) {
        this.system = system;
    }

    public int getWalkInCount() {
        return system.getWalkInCount();  
    }

    public int getDeliveryCount() {
        return system.getDeliveryCount(); 
    }

    public Map<String, Integer> getDeliveriesByBarangay() {
        return system.getDeliveriesByBarangay(); 
    }

    public double getTotalSales() {
        return system.getTotalSales();  
    }

    public void summaryByContainer() {
        system.summaryByContainer();  
    }
}
