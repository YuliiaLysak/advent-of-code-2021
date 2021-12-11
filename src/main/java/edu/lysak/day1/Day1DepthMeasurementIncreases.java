package edu.lysak.day1;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1DepthMeasurementIncreases {
    private static final String FILE_PATH = "src/main/resources/day1/input-day1.txt";

    public static void main(String[] args) throws IOException {
        List<Integer> measurements = Utils.getInputDataAsIntegerList(FILE_PATH);
        System.out.println("Increases count = " + getMeasurementIncreasesCount(measurements));
        System.out.println("Three-measurement increases count = " + getThreeMeasurementIncreasesCount(measurements));
    }

    public static int getMeasurementIncreasesCount(List<Integer> measurements) {
        int increasesCount = 0;
        for (int i = 1; i < measurements.size(); i++) {
            if (measurements.get(i) > measurements.get(i - 1)) {
                increasesCount++;
            }
        }
        return increasesCount;
    }

    public static int getThreeMeasurementIncreasesCount(List<Integer> measurements) {
        List<Integer> sums = new ArrayList<>();
        for (int i = 0; i < measurements.size() - 2; i++) {
            int sum = measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2);
            sums.add(sum);
        }
        return getMeasurementIncreasesCount(sums);
    }
}
