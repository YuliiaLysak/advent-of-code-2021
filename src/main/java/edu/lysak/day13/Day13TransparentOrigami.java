package edu.lysak.day13;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13TransparentOrigami {
    private static final String FILE_PATH = "src/main/resources/day13/input-day13.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("'#' count after first fold = " + getPart1Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        List<String> coordinates = getCoordinates(input);
        List<String[]> instructions = getFoldInstructions(input);
        int[] size = getPaperSize(coordinates);

        char[][] matrix = new char[size[1]][size[0]];
        fillMatrix(matrix, coordinates);
//        Utils.printMatrix(matrix, "src/main/resources/day13/input-day13-output.txt");
        String[] firstInstructions = instructions.get(0);
        char[][] foldedMatrix;
        if ("y".equals(firstInstructions[0])) {
            foldedMatrix = foldUp(matrix, Integer.parseInt(firstInstructions[1]));
        } else {
            foldedMatrix = foldLeft(matrix, Integer.parseInt(firstInstructions[1]));
        }
        return countHashes(foldedMatrix);
    }

    private static List<String> getCoordinates(List<String> input) {
        return input.stream()
                .takeWhile(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    private static List<String[]> getFoldInstructions(List<String> input) {
        return input.stream()
                .dropWhile(line -> !line.isEmpty())
                .skip(1)
                .map(line -> line.substring("fold along".length() + 1))
                .map(line -> line.split("="))
                .collect(Collectors.toList());
    }

    private static int[] getPaperSize(List<String> coordinates) {
        int xMax = 0;
        int yMax = 0;
        for (String line : coordinates) {
            String[] split = line.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if (x > xMax) {
                xMax = x;
            }
            if (y > yMax) {
                yMax = y;
            }
        }
        return new int[]{xMax + 1, yMax + 1};
    }

    private static char[][] foldUp(char[][] matrix, int y) {
        char[][] foldedMatrix = Arrays.copyOfRange(matrix, 0, y);

        int rowCount = matrix.length;
        for (int row = 0; row < foldedMatrix.length; row++) {
            for (int col = 0; col < foldedMatrix[row].length; col++) {
                char current = matrix[rowCount - row - 1][col];
                if ('#' == current) {
                    foldedMatrix[row][col] = current;
                }
            }
        }
        return foldedMatrix;
    }

    private static char[][] foldLeft(char[][] matrix, int x) {
        char[][] foldedMatrix = new char[matrix.length][x];
        for (int i = 0; i < foldedMatrix.length; i++) {
            foldedMatrix[i] = Arrays.copyOfRange(matrix[i], 0, x);
        }

        int colCount = x * 2 + 1;
        for (int row = 0; row < foldedMatrix.length; row++) {
            for (int col = 0; col < foldedMatrix[row].length; col++) {
                if (colCount - col - 1 < matrix[row].length) {
                    char current = matrix[row][colCount - col - 1];
                    if ('#' == current) {
                        foldedMatrix[row][col] = current;
                    }
                }
            }
        }
        return foldedMatrix;
    }

    private static void fillMatrix(char[][] matrix, List<String> coordinates) {
        for (char[] row : matrix) {
            Arrays.fill(row, '.');
        }

        for (String line : coordinates) {
            String[] split = line.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            matrix[y][x] = '#';
        }
    }

    private static int countHashes(char[][] matrix) {
        int count = 0;
        for (char[] row : matrix) {
            for (char c : row) {
                if (c == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}
