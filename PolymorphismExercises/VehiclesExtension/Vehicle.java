package PolymorphismExercises.VehiclesExtension;

import java.text.DecimalFormat;

public class Vehicle {
    protected double fuelQuantity;
    protected double fuelConsumption;
    protected double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
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
        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }
        if (liters > this.tankCapacity - this.fuelQuantity) {
            System.out.println("Cannot fit fuel in tank");
            return;
        }


        this.fuelQuantity += liters;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {

        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
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

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}