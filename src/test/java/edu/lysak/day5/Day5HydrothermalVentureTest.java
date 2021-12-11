package edu.lysak.day5;

import edu.lysak.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5HydrothermalVentureTest {
    private static final String FILE_PATH_1 = "src/test/resources/day5/input-day5-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day5/input-day5-2.txt";
    private List<String> input1;
    private List<String> input2;
    private int size1;
    private int size2;

    @BeforeEach
    void setUp() throws IOException {
        input1 = Utils.getInputDataAsStringList(FILE_PATH_1);
        input2 = Utils.getInputDataAsStringList(FILE_PATH_2);
        size1 = getMaxCoordinate(input1) + 1;
        size2 = getMaxCoordinate(input2) + 1;
    }

    @Test
    void getPart1Result() {
        int result1 = Day5HydrothermalVenture.getPart1Result(input1, size1);
        int result2 = Day5HydrothermalVenture.getPart1Result(input2, size2);
        assertEquals(5, result1);
        assertEquals(7468, result2);
    }

    @Test
    void getPart2Result() throws FileNotFoundException {
        int result1 = Day5HydrothermalVenture.getPart2Result(input1, size1);
        int result2 = Day5HydrothermalVenture.getPart2Result(input2, size2);
        assertEquals(12, result1);
        assertEquals(22364, result2);
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
}
