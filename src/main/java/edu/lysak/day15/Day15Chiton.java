package edu.lysak.day15;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.List;

public class Day15Chiton {
    private static final String FILE_PATH = "src/main/resources/day15/input-day15.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Shortest path from top left position to the bottom right:");
        System.out.println("Lowest total risk = " + getPart1Result(FILE_PATH));
        // TODO: 16.12.2021 should be 2998, I have 3001. Find out why?
        System.out.println("Lowest total risk (5x bigger matrix)  = " + getPart2Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        int[][] matrix = Utils.convertInputToMatrix(input);
        return getMinPath(matrix);
    }

    public static int getPart2Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        int[][] matrix = Utils.convertInputToMatrix(input);
//        System.out.println(matrix.length + " * " + matrix[0].length);
        int[][] enlargedMatrix = enlargeMatrix(matrix, 5);
//        System.out.println(enlargedMatrix.length + " * " + enlargedMatrix[0].length);
//        Utils.printIntMatrix(enlargedMatrix);
        return getMinPath(enlargedMatrix);
    }

    // source - http://xyzcode.blogspot.com/2020/03/find-shortest-path-in-2d-array.html
    private static int getMinPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] cache = new int[row][col];
        cache[0][0] = 0; // if starting position should be included in path use => cache[0][0] = matrix[0][0]
        for (int i = 1; i < col; i++) {
            cache[0][i] = cache[0][i - 1] + matrix[0][i];
        }
        for (int j = 1; j < row; j++) {
            cache[j][0] = cache[j - 1][0] + matrix[j][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
//                System.out.println("cache[" + (i - 1) + "][" + j + "] = " + cache[i - 1][j] + " cache[" + i + "][" + (j - 1) + "] = " + cache[i][j - 1]);
                if (cache[i - 1][j] > cache[i][j - 1]) {
                    cache[i][j] = cache[i][j - 1] + matrix[i][j];
//                    System.out.println("[" + i + "," + (j - 1) + "] =" + matrix[i][j - 1]);
                } else {
                    cache[i][j] = cache[i - 1][j] + matrix[i][j];
//                    System.out.println("[" + (i - 1) + "," + j + "] =" + matrix[i - 1][j]);
                }
//                System.out.println("cache[" + i + "][" + j + "] = " + cache[i][j]);
            }
        }
//        System.out.println("[" + (row - 1) + "," + (col - 1) + "] =" + matrix[row - 1][col - 1]);
        return cache[row - 1][col - 1];
    }

    private static int getMinPath2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] minValues = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    minValues[i][j] = 0;
                } else if (i == 0) {
                    minValues[i][j] = minValues[i][j - 1] + matrix[i][j];
                } else if (j == 0) {
                    minValues[i][j] = minValues[i - 1][j] + matrix[i][j];
                } else {
                    minValues[i][j] = Math.min(minValues[i][j - 1], minValues[i - 1][j]) + matrix[i][j];
                }
            }
        }
        return minValues[row - 1][col - 1];
    }

    private static int[][] enlargeMatrix(int[][] matrix, int maxMultiplier) {
        int maxCost = 9;
        int[][] enlarged = new int[matrix.length * maxMultiplier][];
        int baseLineLength = matrix[0].length;

        for (int y = 0; y < matrix.length; y++) {
            enlarged[y] = new int[baseLineLength * maxMultiplier];

            for (int xMultiplier = 0; xMultiplier < maxMultiplier; xMultiplier++) {
                for (int x = 0; x < baseLineLength; x++) {
                    int cost = (matrix[y % matrix.length][x] + xMultiplier);
                    if (cost > maxCost) {
                        cost -= maxCost;
                    }

                    enlarged[y][x + baseLineLength * xMultiplier] = cost;
                }
            }
        }

        for (int y = matrix.length; y < enlarged.length; y++) {
            enlarged[y] = new int[baseLineLength * maxMultiplier];

            for (int x = 0; x < enlarged[0].length; x++) {
                int cost = enlarged[y - matrix.length][x] + 1;
                if (cost > maxCost) {
                    cost -= maxCost;
                }

                enlarged[y][x] = cost;
            }
        }
        return enlarged;
    }
}
