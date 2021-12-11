package edu.lysak.day6;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day6Lanternfish {
    private static final String FILE_PATH = "src/main/resources/day6/input-day6.txt";
    private static final int NEW_FISH_TIMER = 9;
    private static final int TOTAL_DAYS_COUNT_1 = 80;
    private static final int TOTAL_DAYS_COUNT_2 = 256;

    public static void main(String[] args) throws IOException {
        List<Integer> internalTimers = Utils.splitCSVInputToIntegerList(FILE_PATH);
        System.out.println("Lanternfish count after 80 days = " + getResultNotOptimal(internalTimers, TOTAL_DAYS_COUNT_1));
        System.out.println("Lanternfish count after 256 days = " + getResultOptimal(getFishArray(FILE_PATH), TOTAL_DAYS_COUNT_2));
    }

    public static long getResultOptimal(long[] internalTimers, int totalDaysCount) {
        int daysCount = 1;
        while (daysCount <= totalDaysCount) {
            long zeros = internalTimers[0];
            for (int j = 0; j < internalTimers.length - 1; j++) {
                internalTimers[j] = internalTimers[j + 1];
            }
            internalTimers[6] += zeros;
            internalTimers[8] = zeros;
            daysCount++;
        }

        return Arrays.stream(internalTimers).sum();
    }

    public static int getResultNotOptimal(List<Integer> internalTimers, int totalDaysCount) {
        int daysCount = 1;
//        System.out.println("Initial state: " + internalTimers);
        while (daysCount <= totalDaysCount) {
            for (int i = 0; i < internalTimers.size(); i++) {
                int currentTimer = internalTimers.get(i);
                int updatedTimer;
                if (currentTimer == 0) {
                    updatedTimer = 6;
                    internalTimers.add(NEW_FISH_TIMER);
                } else {
                    updatedTimer = currentTimer - 1;
                }
                internalTimers.set(i, updatedTimer);
            }
//            System.out.println("After " + daysCount + " day:  " + internalTimers);
            daysCount++;
        }

        return internalTimers.size();
    }

    public static long[] getFishArray(String filePath) throws IOException {
        long[] fishArray = new long[9];
        List<Integer> input = Utils.splitCSVInputToIntegerList(filePath);
        for (int i : input) {
            fishArray[i]++;
        }
        return fishArray;
    }
}
