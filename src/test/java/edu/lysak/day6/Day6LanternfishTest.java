package edu.lysak.day6;

import edu.lysak.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6LanternfishTest {
    private static final String FILE_PATH_1 = "src/test/resources/day6/input-day6-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day6/input-day6-2.txt";
    private static final int TOTAL_DAYS_COUNT_1 = 80;
    private static final int TOTAL_DAYS_COUNT_2 = 256;
    private List<Integer> timers1;
    private List<Integer> timers2;

    @BeforeEach
    void setUp() throws IOException {
        timers1 = Utils.splitCSVInputToIntegerList(FILE_PATH_1);
        timers2 = Utils.splitCSVInputToIntegerList(FILE_PATH_2);
    }

    @Test
    void getResultNotOptimal() {
        int result1 = Day6Lanternfish.getResultNotOptimal(timers1, TOTAL_DAYS_COUNT_1);
        int result2 = Day6Lanternfish.getResultNotOptimal(timers2, TOTAL_DAYS_COUNT_1);
        assertEquals(5934, result1);
        assertEquals(390923, result2);
    }

    @Test
    void getResultOptimal() throws IOException {
        long result1 = Day6Lanternfish.getResultOptimal(Day6Lanternfish.getFishArray(FILE_PATH_1), TOTAL_DAYS_COUNT_1);
        long result2 = Day6Lanternfish.getResultOptimal(Day6Lanternfish.getFishArray(FILE_PATH_2), TOTAL_DAYS_COUNT_2);
        assertEquals(5934, result1);
        assertEquals(1749945484935L, result2);
    }
}
