package Google;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        Map<String, Person> personData = new HashMap<>();
        while (!info.equals("End")) {
//•	"{Name} company {companyName} {department} {salary}"
//•	"{Name} pokemon {pokemonName} {pokemonType}"
//•	"{Name} parents {parentName} {parentBirthday}"
//•	"{Name} children {childName} {childBirthday}"
//•	"{Name} car {carModel} {carSpeed}"
            String[] parameters = info.split("\\s+");
            String personName = parameters[0];
            if (!personData.containsKey(personName)) {
                personData.put(personName, new Person());
            }
            String typeCommand = parameters[1];
            switch (typeCommand) {
                case "company":
                    String companyName = parameters[2];
                    String department = parameters[3];
                    double salary = Double.parseDouble(parameters[4]);
                    Company company = new Company(companyName, department, salary);
                    personData.get(personName).setCompany(company);
                    break;
                case " pokemon":
                    String pokemonName = parameters[2];
                    String pokemonType = parameters[3];
                    Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
                    personData.get(personName).getPokemons().add(pokemon);
                    break;
                case "parents":
                    String parentName = parameters[2];
                    String parentBirthday = parameters[3];
                    Parents parents = new Parents(parentName, parentBirthday);
                    personData.get(personName).getParents().add(parents);
                    break;
                case "children":
                    String childName = parameters[2];
                    String childBirthday = parameters[3];
                    Kid kid = new Kid(childName, childBirthday);
                    personData.get(personName).getKids().add(kid);
                    break;
                case "car":
                    String carModel = parameters[2];
                    int carSpeed = Integer.parseInt(parameters[3]);
                    Car car = new Car(carModel, carSpeed);
                    personData.get(personName).setCars(car);
                    break;
            }
            info = scanner.nextLine();
        }
        String searchedPerson = scanner.nextLine();
        System.out.println(searchedPerson);

        Person person = personData.get(searchedPerson);
        System.out.println(person);
    }
}
