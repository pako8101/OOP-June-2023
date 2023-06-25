package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static long sum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = value++;
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("Let the Force be with you")) {
            int[] heroCoordinates = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            int evilRow = evilCoordinates[0];
            int evilCol = evilCoordinates[1];

            moveEvil(matrix, evilRow, evilCol);

            int heroRow = heroCoordinates[0];
            int heroCol = heroCoordinates[1];

           sum = moveHero(matrix,sum, heroRow, heroCol);
            command = scanner.nextLine();
        }
        System.out.println(sum);
    }

    private static long moveHero(int[][] matrix,long sum, int heroRow, int heroCol) {
        while (heroRow >= 0 && heroCol < matrix[1].length) {
            if (heroRow < matrix.length && heroCol >= 0 && heroCol < matrix[0].length) {
                sum += matrix[heroRow][heroCol];

            }
            heroCol++;
            heroRow--;
        }
        return sum;
    }

    private static void moveEvil(int[][] matrix, int row, int col) {
        while (row >= 0 && col >= 0) {
            if (row < matrix.length && col < matrix[0].length) {
                matrix[row][col] = 0;

            }
            row--;
            col--;

        }
    }
}