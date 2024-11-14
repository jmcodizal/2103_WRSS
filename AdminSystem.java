
package waterrefillingsalesystem;

import java.util.Map;

public class AdminSystem {
    private final WaterRefillingSystem system;

    /**
     *
     * @param system
     */
    public AdminSystem(WaterRefillingSystem system) {
        this.system = system;
    }

    public int getWalkInCount() {
        return system.getWalkInCount();  // Example: Replace with actual logic from WaterRefillingSystem
    }

    public int getDeliveryCount() {
        return system.getDeliveryCount(); // Example: Replace with actual logic from WaterRefillingSystem
    }

    public Map<String, Integer> getDeliveriesByBarangay() {
        return system.getDeliveriesByBarangay(); // Example: Replace with actual logic from WaterRefillingSystem
    }

    public double getTotalSales() {
        return system.getTotalSales();  // Example: Replace with actual logic from WaterRefillingSystem
    }

    public void summaryByContainer() {
        system.summaryByContainer();  // Example: Replace with actual logic from WaterRefillingSystem
    }
}
