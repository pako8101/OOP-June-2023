package PolymorphWildFArm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int lineCount = 0;
//        String input;
//        Animal animal = null;
//        while (!"End".equals(input = scanner.nextLine())) {
//            String[] elements = input.split("\\s+");
//            if (lineCount % 2 == 0) {
//                animal = animalFactory(elements);
//            } else {
//                Food food = foodFactory(elements);
//                animal.makeSound();
//                animal.eat(food);
//                System.out.println(animal);
//            }
//            lineCount++;
//        }
//    }
//
//    private static Animal animalFactory(String[] elements) {
//        Animal animal= null;
//        String animalType = elements[0];
//        String animalName = elements[1];
//        double animalWeight = Double.parseDouble(elements[2]);
//        String animalLiving = elements[3];
//        switch (animalType) {
//            case "Cat":
//                String breed = elements[4];
//                animal= new Cat(animalName, animalType, animalWeight, animalLiving,breed);
//                break;
//            case "Tiger":
//                animal= new Tiger(animalName, animalType, animalWeight, animalLiving);
//                break;
//            case "Mouse":
//                animal=  new Mouse(animalName, animalType, animalWeight, animalLiving);
//                break;
//            case "Zebra":
//                animal=  new Zebra(animalName, animalType, animalWeight, animalLiving);
//                break;
//        }
//return  animal;
//    }
//
//    private static Food foodFactory(String[] elements) {
//        Food food = null;
//        String foodType = elements[0];
//        int foodQuantity = Integer.parseInt(elements[1]);
//        switch (foodType) {
//            case "Vegetable":
//                food = new Vegetable(foodQuantity);
//                break;
//            case "Meat":
//                food = new Meat(foodQuantity);
//                break;
//        }
//        return  food;
//    }
//}

        List<Animal> animals = new ArrayList<>();

        String animalInput = scanner.nextLine();
        while (!animalInput.equals("End")) {
            String[] tokens = animalInput.split(" ");
            Animal animal = createAnimal(tokens);

            String foodInput = scanner.nextLine();
            Food food = getFood(foodInput.split(" "));

            animal.makeSound();
            try {
                animal.eat(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animals.add(animal);
            animalInput = scanner.nextLine();
        }

        animals.forEach(System.out::println);
    }

    public static Food getFood(String[] tokens) {
        String type = tokens[0];
        int quantity = Integer.parseInt(tokens[1]);

        if (type.equals("Meat")) {
            return new Meat(quantity);
        } else return new Vegetable(quantity);
    }

    public static Animal createAnimal(String[] tokens) {
        String animalType = tokens[0];
        String animalName = tokens[1];
        double animalWeight = Double.parseDouble(tokens[2]);
        String animalLivingRegion = tokens[3];
        switch (animalType) {
            case "Mouse":
                return new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
            case "Cat":
                return new Cat(animalName, animalType, animalWeight, animalLivingRegion, tokens[4]);
            case "Zebra":
                return new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
            case "Tiger":
                return new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
            default:
                throw new IllegalArgumentException("No such animal");
        }
    }
}