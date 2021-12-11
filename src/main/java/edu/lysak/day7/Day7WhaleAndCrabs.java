package edu.lysak.day7;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.List;

public class Day7WhaleAndCrabs {
    private static final String FILE_PATH = "src/main/resources/day7/input-day7.txt";

    public static void main(String[] args) throws IOException {
        List<Integer> positions = Utils.splitCSVInputToIntegerList(FILE_PATH);

        System.out.println("Cheapest possible fuel outcome = " + getPart1Result(positions));
        System.out.println("Cheapest possible fuel outcome (not constant rate) = " + getPart2Result(positions));
    }

    public static int getPart1Result(List<Integer> positions) {
        int max = getMax(positions);
        int min = getMin(positions);
        int cheapestFuel = Integer.MAX_VALUE;
        while (min <= max) {
            int totalFuel = 0;
            for (int position : positions) {
                int fuel = Math.abs(position - min);
                totalFuel += fuel;
            }
            if (totalFuel < cheapestFuel) {
                cheapestFuel = totalFuel;
            }
            min++;
        }
        return cheapestFuel;
    }

    public static int getPart2Result(List<Integer> positions) {
        int max = getMax(positions);
        int min = getMin(positions);
        int cheapestFuel = Integer.MAX_VALUE;
        while (min <= max) {
            int totalFuel = 0;
            for (int position : positions) {
                int steps = Math.abs(position - min);
                totalFuel += getCrabFuel(steps);
            }
            if (totalFuel < cheapestFuel) {
                cheapestFuel = totalFuel;
            }
            min++;
        }
        return cheapestFuel;
    }

    private static int getCrabFuel(int n) {
        int fact = n > 0 ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            fact = fact + i;
        }
        return fact;
    }

    private static int getMin(List<Integer> positions) {
        return positions.stream().mapToInt(it -> it).min().orElseThrow();
    }

    private static int getMax(List<Integer> positions) {
        return positions.stream().mapToInt(it -> it).max().orElseThrow();
    }
}
