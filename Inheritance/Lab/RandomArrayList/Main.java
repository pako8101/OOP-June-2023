package Inheritance.Lab.RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList<Integer> randomArrayList = new RandomArrayList<>();
        randomArrayList.add(1);
        randomArrayList.add(2);
        randomArrayList.add(3);
        randomArrayList.add(4);
        randomArrayList.add(5);

        System.out.println( randomArrayList.getRandomElement());
        System.out.println(randomArrayList.getRandomElement());
    }

}
