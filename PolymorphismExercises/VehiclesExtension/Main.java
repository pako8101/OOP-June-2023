package PolymorphismExercises.VehiclesExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final double ADDITIONAL_FUEL_CONSUMPTION = 1.4;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        Vehicle car = getVehicle(tokens);

        tokens = scanner.nextLine().split("\\s+");
        Vehicle truck = getVehicle(tokens);
        tokens = scanner.nextLine().split("\\s+");
        Vehicle bus = getVehicle(tokens);
        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String command = tokens[0];
            String vehicleType = tokens[1];
            try {
                switch (command) {
                    case "Drive":
                        double distance = Double.parseDouble(tokens[2]);

                        Vehicle vehicle = vehicleMap.get(vehicleType);
                        if (vehicle instanceof Bus) {
                            bus.setFuelConsumption(bus.getFuelConsumption() + ADDITIONAL_FUEL_CONSUMPTION);
                            System.out.println(bus.drive(distance));
                            bus.setFuelConsumption(bus.getFuelConsumption() - ADDITIONAL_FUEL_CONSUMPTION);
                            continue;
//                        ((Bus) vehicle).setEmpty(false);
//                        ((Bus) vehicle).drive(distance,false);

                        }
                        String driveMessage = vehicle.drive(distance);
                        System.out.println(driveMessage);
//                    if (vehicleType.equals("Car")){
//                        System.out.println(car.drive(distance));
//                    }else {
//                        System.out.println(truck.drive(distance));
//                    }
                        break;
                    case "Refuel":
                        double fuelAmount = Double.parseDouble(tokens[2]);
                        vehicleMap.get(vehicleType).refuel(fuelAmount);
//                    if (vehicleType.equals("Car")){
//                       car.refuel(fuelAmount);
//                    }else {
//                       truck.refuel(fuelAmount);
//                    }
                        break;
                    case "DriveEmpty":
                        double driveEmptyDistance = Double.parseDouble(tokens[2]);
                        String driveEmptyMessage = bus.drive(driveEmptyDistance); //((Bus)bus).drive(driveEmptyDistance,true);
                        System.out.println(driveEmptyMessage);
                        break;
                    default:
                        throw new IllegalArgumentException("No such command");
                }
            }catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }
        }
        vehicleMap.values().forEach(System.out::println);

    }

    private static Vehicle getVehicle(String[] tokens) {
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double fuelConsumption = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);
        String vehicleType = tokens[0];
        Vehicle vehicle = null;
        switch (vehicleType) {
            case "Car":
                vehicle = new Car(fuelQuantity, fuelConsumption,tankCapacity);
                break;
            case "Truck":
                vehicle = new Truck(fuelQuantity, fuelConsumption,tankCapacity);
                break;
            case "Bus":
            vehicle = new Bus(fuelQuantity, fuelConsumption,tankCapacity);
                break;
        }

        return vehicle;
    }
}
