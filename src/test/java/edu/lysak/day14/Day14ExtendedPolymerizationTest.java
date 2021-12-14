package edu.lysak.day14;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14ExtendedPolymerizationTest {
    private static final String FILE_PATH_1 = "src/test/resources/day14/input-day14-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day14/input-day14-2.txt";
    private static final int STEP_COUNT = 10;
    private static final int STEP_COUNT_2 = 40;

    @Test
    void getPart1Result() throws IOException {
        long result1 = Day14ExtendedPolymerization.getPart1ResultNotOptimal(FILE_PATH_1, STEP_COUNT);
        long result2 = Day14ExtendedPolymerization.getPart1ResultNotOptimal(FILE_PATH_2, STEP_COUNT);
        assertEquals(1588, result1);
        assertEquals(2891, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        long result1 = Day14ExtendedPolymerization.getPart2ResultOptimal(FILE_PATH_1, STEP_COUNT_2);
        long result2 = Day14ExtendedPolymerization.getPart2ResultOptimal(FILE_PATH_2, STEP_COUNT_2);
        assertEquals(2188189693529L, result1);
        assertEquals(4607749009683L, result2);
    }
}
