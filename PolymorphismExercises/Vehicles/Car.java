package PolymorphismExercises.Vehicles;

public class Car extends Vehicle {
    private static final double ADDITIONAL_AC_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);

        // this.setFuelConsumption(this.getFuelConsumption()+ADDITIONAL_AC_CONSUMPTION);

    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + ADDITIONAL_AC_CONSUMPTION);
    }
}
