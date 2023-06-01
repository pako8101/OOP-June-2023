package InterfacesExercises.BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Birthable>livingBeings = new ArrayList<>();
        String input = scanner.nextLine();
        while (!input.equals("End")){
            String[]inputSplit = input.split("\\s+");

            switch (inputSplit[0]){
                case "citizen":
                    livingBeings.add(new Citizen(inputSplit[1],
                            Integer.parseInt(inputSplit[2]),
                            inputSplit[3],
                            inputSplit[4]));
                    break;
                case "Pet":
                    livingBeings.add(new Pet(inputSplit[1],inputSplit[2]));
                    break;
                default:
                    input= scanner.nextLine();
                    continue;
            }
            input= scanner.nextLine();
        }
        String year = scanner.nextLine();

        livingBeings.stream().map(Birthable::getBirthDate)
                .filter(birthDate -> birthDate.endsWith(year))
                .forEach(System.out::println);

        scanner.close();
    }
}
