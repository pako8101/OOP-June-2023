package PolymorphismExercises.VehiclesExtension;

public class Truck extends Vehicle {
    private static final double ADDITIONAL_AC_CONSUMPTION = 1.6;
    private static final double MINUS_GAS_Truck = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption,double tankCapacity) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_AC_CONSUMPTION,tankCapacity);

    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * MINUS_GAS_Truck);

    }

}
