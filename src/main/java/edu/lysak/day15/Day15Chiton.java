package edu.lysak.day15;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.List;

public class Day15Chiton {
    private static final String FILE_PATH = "src/main/resources/day15/input-day15.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Lowest total risk " +
                "(shortest path from top left position to the bottom right) = " + getPart1Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        int[][] matrix = Utils.convertInputToMatrix(input);
        return getMinPath(matrix);
    }

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
}
