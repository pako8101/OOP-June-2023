package vehicleShop.models.vehicle;

import static vehicleShop.common.ExceptionMessages.VEHICLE_NAME_NULL_OR_EMPTY;
import static vehicleShop.common.ExceptionMessages.VEHICLE_STRENGTH_LESS_THAN_ZERO;

public class VehicleImpl implements Vehicle {
    private String name;
    private int strengthRequired;

    public VehicleImpl(String name, int strengthRequired) {
        this.setName(name);
        this.setStrengthRequired(strengthRequired);
    }

    private void setStrengthRequired(int strengthRequired) {
        if (strengthRequired < 0) {
            throw new IllegalArgumentException(VEHICLE_STRENGTH_LESS_THAN_ZERO);
        }
        this.strengthRequired = strengthRequired;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(VEHICLE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrengthRequired() {
        return this.strengthRequired;
    }

    @Override
    public boolean reached() {
        return this.strengthRequired == 0;
//        if (this.strengthRequired==0){
//            return true;
//        }
//        return false;
    }

    @Override
    public void making() {
        int neededStrength = getStrengthRequired();
        neededStrength -= 5;
        if (neededStrength < 0) {
            neededStrength = 0;
        }
        this.setStrengthRequired(neededStrength);
    }
}
