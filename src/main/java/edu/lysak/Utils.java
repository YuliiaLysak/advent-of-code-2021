package edu.lysak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Utils {

    public static List<String> getInputDataAsStringList(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    public static List<Integer> getInputDataAsIntegerList(String filePath) throws IOException {
        return getInputDataAsStringList(filePath).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> splitCSVInputToIntegerList(String filePath) throws IOException {
        return getInputDataAsStringList(filePath).stream()
                .map(it -> it.split(","))
                .flatMap(Arrays::stream)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int[][] convertInputToMatrix(List<String> input) {
        int columns = input.get(0).length();
        int rows = input.size();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < input.size(); i++) {
            String[] row = input.get(i).trim().split("");
            for (int j = 0; j < row.length; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (var vector : matrix) {
            for (var i : vector) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    private Utils() {
    }
}
