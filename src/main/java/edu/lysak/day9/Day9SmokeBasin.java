package edu.lysak.day9;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.*;

public class Day9SmokeBasin {
    private static final String FILE_PATH = "src/main/resources/day9/input-day9.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Risk level = " + getPart1Result(FILE_PATH));
        System.out.println("Product of three largest basins = " + getPart2Result(FILE_PATH));
    }

    public static int getPart2Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        int[][] matrix = Utils.convertInputToMatrix(input);
        return getBasins(matrix)
                .stream()
                .map(Set::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce((num1, num2) -> num1 * num2)
                .orElseThrow();
    }

    private static List<Set<Point>> getBasins(int[][] matrix) {
        List<Set<Point>> basins = new ArrayList<>();
        Set<Point> basin = new HashSet<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 9 || matrix[row][col] == -1) {
                    continue;
                }
                int basinSum = findValue(matrix, row, col, basin);
                if (basinSum != 0) {
                    basins.add(basin);
                    basin = new HashSet<>();
//                    System.out.println("Basin sum = " + basinSum);
                }
            }
        }
        return basins;
    }

    private static int findValue(int[][] matrix, int row, int col, Set<Point> basin) {
        if (row >= matrix.length
                || row < 0
                || col < 0
                || col >= matrix[row].length
                || matrix[row][col] == 9
        ) {
            return 0;
        }

        int value = matrix[row][col];
        if (value == -1) {
            return 0;
        }
        basin.add(new Point(row, col));
        matrix[row][col] = -1;
        return value
                + findValue(matrix, row + 1, col, basin)
                + findValue(matrix, row - 1, col, basin)
                + findValue(matrix, row, col + 1, basin)
                + findValue(matrix, row, col - 1, basin);
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        int[][] matrix = Utils.convertInputToMatrix(input);
        List<Integer> lowPoints = getLowPoints(matrix);
//        System.out.println("Low points: " + lowPoints);
        return getRiskLevel(lowPoints);
    }

    private static List<Integer> getLowPoints(int[][] matrix) {
        List<Integer> lowPoints = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentValue = matrix[row][col];
                int rightValue = 10;
                int leftValue = 10;
                int topValue = 10;
                int bottomValue = 10;
                if (col + 1 < matrix[row].length) {
                    rightValue = matrix[row][col + 1];
                }
                if (col - 1 >= 0) {
                    leftValue = matrix[row][col - 1];
                }
                if (row - 1 >= 0) {
                    topValue = matrix[row - 1][col];
                }
                if (row + 1 < matrix.length) {
                    bottomValue = matrix[row + 1][col];
                }

                if (currentValue < leftValue
                        && currentValue < rightValue
                        && currentValue < topValue
                        && currentValue < bottomValue
                ) {
                    lowPoints.add(currentValue);
                }
            }
        }
        return lowPoints;
    }

    private static int getRiskLevel(List<Integer> lowPoints) {
        return lowPoints.stream()
                .mapToInt(it -> it + 1)
                .sum();
    }
}
