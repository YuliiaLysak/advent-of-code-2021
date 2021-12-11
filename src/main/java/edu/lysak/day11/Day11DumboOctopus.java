package edu.lysak.day11;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.List;

public class Day11DumboOctopus {
    private static final String FILE_PATH = "src/main/resources/day11/input-day11.txt";
    private static final int STEP_COUNT = 100;

    public static void main(String[] args) throws IOException {
        System.out.println("Total flash count = " + getPart1Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        int[][] matrix = Utils.convertInputToMatrix(input);
//        System.out.println("=== Before any steps: ===");
//        Utils.printMatrix(matrix);
        int totalFlashCount = 0;
        for (int i = 0; i < STEP_COUNT; i++) {
            increaseEnergyLevelByOne(matrix);
            totalFlashCount += getFlashCount(matrix);
        }
//        System.out.println("=== After step 100: ===");
//        Utils.printMatrix(matrix);
        return totalFlashCount;
    }

    private static void increaseEnergyLevelByOne(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col]++;
            }
        }
    }

    private static int getFlashCount(int[][] matrix) {
        int flashCount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                flashCount += processAdjacentFlashes(matrix, row, col);
            }
        }
        return flashCount;
    }

    private static int processAdjacentFlashes(int[][] matrix, int row, int col) {
        if (matrix[row][col] >= 10) {
            matrix[row][col] = 0;
            int total = 1;
            total += increaseAdjacent(matrix, row + 1, col);
            total += increaseAdjacent(matrix, row - 1, col);
            total += increaseAdjacent(matrix, row, col + 1);
            total += increaseAdjacent(matrix, row, col - 1);
            total += increaseAdjacent(matrix, row + 1, col + 1);
            total += increaseAdjacent(matrix, row + 1, col - 1);
            total += increaseAdjacent(matrix, row - 1, col - 1);
            total += increaseAdjacent(matrix, row - 1, col + 1);
            return total;
        }
        return 0;
    }

    private static int increaseAdjacent(int[][] matrix, int row, int col) {
        if (row < matrix.length && row >= 0 && col < matrix[row].length && col >= 0) {
            if (matrix[row][col] != 0) {
                matrix[row][col]++;
            }
            return processAdjacentFlashes(matrix, row, col);
        }
        return 0;
    }
}
