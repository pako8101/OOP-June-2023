package InterfacesExercises.FoodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        List<Citizen> citizens = new ArrayList<>();
        List<Rebel>rebels = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            String[] info = scanner.nextLine().split(" ");

            if (info.length==4){
                citizens.add(new Citizen(info[0], Integer.parseInt(info[1]),info[2],
                        info[3]));
                continue;
            }
            rebels.add(new Rebel(info[0], Integer.parseInt(info[1]),info[2]));
        }
        String command = scanner.nextLine();
        while (!command.equals("End")){
            String name = command;
            citizens.stream().filter(citizen -> citizen.getName()
                    .equals(name))
                    .findFirst()
                    .ifPresent(Citizen::buyFood);
rebels.stream().filter(citizen -> citizen.getName()
                .equals(name))
        .findFirst()
        .ifPresent(Rebel::buyFood);

            command = scanner.nextLine();
        }
        System.out.println(citizens.stream().map(Citizen::getFood).reduce(0, Integer::sum)
                + rebels.stream().map(Rebel::getFood).reduce(0, Integer::sum));

        scanner.close();
    }
}
