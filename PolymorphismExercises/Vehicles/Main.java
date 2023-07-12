package Vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        Vehicle car = getVehicle(tokens);

        tokens = scanner.nextLine().split("\\s+");
        Vehicle truck = getVehicle(tokens);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String command = tokens[0];
            String vehicleType = tokens[1];
            switch (command) {
                case "Drive":
                    double distance = Double.parseDouble(tokens[2]);
                    String driveMessage = vehicleMap.get(vehicleType).drive(distance);
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
                default:
                    throw new IllegalArgumentException("No such command");
            }
        }
        vehicleMap.values().forEach(System.out::println);

    }

    private static Vehicle getVehicle(String[] tokens) {
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double fuelConsumption = Double.parseDouble(tokens[2]);
        String vehicleType = tokens[0];
        Vehicle vehicle = null;
        switch (vehicleType) {
            case "Car":
                vehicle = new Car(fuelQuantity, fuelConsumption);
                break;
            case "Truck":
                vehicle = new Truck(fuelQuantity, fuelConsumption);
                break;
        }

        return vehicle;
    }
}
