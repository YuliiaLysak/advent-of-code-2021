package edu.lysak.day1;

import edu.lysak.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day1DepthMeasurementIncreasesTest {
    private static final String FILE_PATH_1 = "src/test/resources/day1/input-day1-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day1/input-day1-2.txt";
    private List<Integer> measurements;
    private List<Integer> measurements2;

    @BeforeEach
    void setUp() throws IOException {
        measurements = Utils.getInputDataAsIntegerList(FILE_PATH_1);
        measurements2 = Utils.getInputDataAsIntegerList(FILE_PATH_2);
    }

    @Test
    void getMeasurementIncreasesCount() {
        int measurementIncreasesCount = Day1DepthMeasurementIncreases.getMeasurementIncreasesCount(measurements);
        int measurementIncreasesCount2 = Day1DepthMeasurementIncreases.getMeasurementIncreasesCount(measurements2);
        assertEquals(7, measurementIncreasesCount);
        assertEquals(1393, measurementIncreasesCount2);
    }

    @Test
    void getThreeMeasurementIncreasesCount() {
        int result = Day1DepthMeasurementIncreases.getThreeMeasurementIncreasesCount(measurements);
        int result2 = Day1DepthMeasurementIncreases.getThreeMeasurementIncreasesCount(measurements2);
        assertEquals(5, result);
        assertEquals(1359, result2);
    }
}
