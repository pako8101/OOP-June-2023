package PolymorphismExercises.Vehicles;

import java.text.DecimalFormat;

public  class Vehicle {
    protected double fuelQuantity;
    protected double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.setFuelConsumption(fuelConsumption);
    }

    public String drive(double distance) {
        double fuelNeeded = distance * this.fuelConsumption;
        if (fuelNeeded > this.fuelQuantity) {
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        }
        this.fuelQuantity = this.fuelQuantity - fuelNeeded;

        DecimalFormat dc = new DecimalFormat("##.##");
        return String.format("%s travelled %s km", this.getClass().getSimpleName(), dc.format(distance));

    }

    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {

        return String.format("%s: %.2f",this.getClass().getSimpleName(),this.fuelQuantity);
    }
}