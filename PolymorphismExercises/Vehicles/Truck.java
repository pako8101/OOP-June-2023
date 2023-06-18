package PolymorphismExercises.Vehicles;

public class Truck extends Vehicle {
    private static final double ADDITIONAL_AC_CONSUMPTION = 1.6;
    private static final double MINUS_GAS_Truck = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_AC_CONSUMPTION);

    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * MINUS_GAS_Truck);

    }

}
