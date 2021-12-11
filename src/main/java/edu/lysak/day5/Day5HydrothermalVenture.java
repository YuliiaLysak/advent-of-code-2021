package edu.lysak.day5;

import edu.lysak.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5HydrothermalVenture {
    private static final String FILE_PATH = "src/main/resources/day5/input-day5.txt";
    private static final String FILE_PATH_OUTPUT = "src/main/resources/day5/input-day5-output.txt";

    public static void main(String[] args) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(FILE_PATH);
        int size = getMaxCoordinate(input) + 1;

        System.out.println("Overlaps (horizontal and vertical) = " + getPart1Result(input, size));
        System.out.println("Overlaps (horizontal, vertical and diagonal) = " + getPart2Result(input, size));
    }

    public static int getPart1Result(List<String> input, int size) {
        List<Coordinates> coordinates = getCoordinates(input);
        int[][] diagram = new int[size][size];
        fillDiagram(coordinates, diagram);
        return getOverlaps(diagram);
    }

    public static int getPart2Result(List<String> input, int size) throws FileNotFoundException {
        List<Coordinates> coordinatesWithDiagonals = getCoordinatesWithDiagonals(input);
        int[][] diagramWithDiagonals = new int[size][size];
        fillDiagramWithDiagonals(coordinatesWithDiagonals, diagramWithDiagonals);
        return getOverlaps(diagramWithDiagonals);
    }

    private static int getMaxCoordinate(List<String> input) {
        return input.stream()
                .map(it -> it.replaceAll(" -> ", ","))
                .map(it -> it.split(","))
                .flatMap(Arrays::stream)
                .mapToInt(Integer::parseInt)
                .max()
                .orElseThrow();
    }

    private static List<Coordinates> getCoordinates(List<String> input) {
        return input.stream()
                .map(it -> it.replaceAll(" -> ", ","))
                .map(it -> it.split(","))
                .map(nums -> {
                    Coordinates coord = new Coordinates();
                    coord.setX1(Integer.parseInt(nums[0]));
                    coord.setY1(Integer.parseInt(nums[1]));
                    coord.setX2(Integer.parseInt(nums[2]));
                    coord.setY2(Integer.parseInt(nums[3]));
                    return coord;
                })
                .filter(coord -> coord.getX1() == coord.getX2() || coord.getY1() == coord.getY2())
                .collect(Collectors.toList());
    }

    private static List<Coordinates> getCoordinatesWithDiagonals(List<String> input) {
        return input.stream()
                .map(it -> it.replaceAll(" -> ", ","))
                .map(it -> it.split(","))
                .map(nums -> {
                    Coordinates coord = new Coordinates();
                    coord.setX1(Integer.parseInt(nums[0]));
                    coord.setY1(Integer.parseInt(nums[1]));
                    coord.setX2(Integer.parseInt(nums[2]));
                    coord.setY2(Integer.parseInt(nums[3]));
                    return coord;
                })
                .filter(coord -> coord.getX1() == coord.getX2()
                        || coord.getY1() == coord.getY2()
                        || (coord.getX1() == coord.getY1() && coord.getX2() == coord.getY2())
                        || (Math.abs(coord.getX1() - coord.getX2()) == Math.abs(coord.getY1() - coord.getY2()))
                )
                .collect(Collectors.toList());
    }

    private static void fillDiagram(List<Coordinates> coordinates, int[][] diagram) {
        for (int[] ints : diagram) {
            Arrays.fill(ints, 0);
        }

        for (Coordinates c : coordinates) {
            int xFrom = Math.min(c.getX1(), c.getX2());
            int xTo = Math.max(c.getX1(), c.getX2());
            int yFrom = Math.min(c.getY1(), c.getY2());
            int yTo = Math.max(c.getY1(), c.getY2());

            for (int i = yFrom; i <= yTo; i++) {
                for (int j = xFrom; j <= xTo; j++) {
                    diagram[j][i]++;
                }
            }
        }
    }

    private static void fillDiagramWithDiagonals(List<Coordinates> coordinates, int[][] diagram) throws FileNotFoundException {
        for (int[] ints : diagram) {
            Arrays.fill(ints, 0);
        }

        for (Coordinates c : coordinates) {
            int x1 = c.getX1();
            int x2 = c.getX2();
            int y1 = c.getY1();
            int y2 = c.getY2();

            int yFrom = Math.min(y1, y2);
            int yTo = Math.max(y1, y2);
            int xFrom = Math.min(x1, x2);
            int xTo = Math.max(x1, x2);

            if (x1 == x2) {
                // vertical
                for (int y = yFrom; y <= yTo; y++) {
                    diagram[y][x1]++;
                }
            } else if (y1 == y2) {
                // horizontal
                for (int x = xFrom; x <= xTo; x++) {
                    diagram[y1][x]++;
                }
            } else if ((x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2)) {
                // main diagonal
                for (int i = 0; i <= xTo - xFrom; i++) {
                    diagram[yFrom + i][xFrom + i]++;
                }
            } else {
                // secondary diagonal
                for (int i = 0; i <= xTo - xFrom; i++) {
                    diagram[yTo - i][xFrom + i]++;
                }
            }
        }

        printToFile(diagram);
    }

    private static void printToFile(int[][] diagram) throws FileNotFoundException {
        PrintStream out = System.out;
        System.setOut(new PrintStream(new FileOutputStream(FILE_PATH_OUTPUT)));
        for (int[] ints : diagram) {
            for (int i : ints) {
                if (i == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(i);
                }
            }
            System.out.println();
        }
        System.setOut(out);
    }

    private static int getOverlaps(int[][] diagram) {
        int overlaps = 0;
        for (int[] ints : diagram) {
            for (int i : ints) {
                if (i >= 2) {
                    overlaps++;
                }
            }
        }
        return overlaps;
    }
}
