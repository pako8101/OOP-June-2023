package Inheritance.InheritancePerson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
        int age = Integer.parseInt(sc.nextLine());

        Child child = new Child(name, age);

        System.out.println(child);
        System.out.println(child);
    }
}

