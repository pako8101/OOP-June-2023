package PolymorphismExercises.VehiclesExtension;

public class Bus extends Vehicle {

//    private boolean isEmpty;
    //private static   final double ADDITIONAL_FUEL_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
//        this.isEmpty = true;
    }
//
//    public boolean isEmpty() {
//        return isEmpty;
//    }
//
//
//    public String drive(double distance,boolean empty) {
//       this.setEmpty(empty);
//       return super.drive(distance);
//    }
//
//    public void setEmpty(boolean empty) {
//
//
//        if (!isEmpty && empty) {
//            this.fuelConsumption -= ADDITIONAL_FUEL_CONSUMPTION;
//        }
//
//        if (isEmpty && !empty) {
//            this.fuelConsumption += ADDITIONAL_FUEL_CONSUMPTION;
//        }
//
//        this.fuelConsumption = this.fuelConsumption - ADDITIONAL_FUEL_CONSUMPTION;
//        isEmpty = empty;
//    }
//
//    @Override
//    public void setFuelConsumption(double fuelConsumption) {
//        super.setFuelConsumption(fuelConsumption);
//    }
}
