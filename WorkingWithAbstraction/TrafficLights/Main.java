package WorkingWithAbstraction.TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[]colors = scanner.nextLine().split(" ");
        int change = Integer.parseInt(scanner.nextLine());
        List<Light>trafficLight = new ArrayList<>();
        for (String color : colors) {
            Light light = new Light(Color.valueOf(color));
            trafficLight.add(light);
        }
        for (int i = 0; i < change; i++) {
            trafficLight.forEach(light-> {
                light.changeColor();
                System.out.print(light.getColor() + " ");
            });
            System.out.println();
        }
    }
}
