package edu.lysak.day14;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14ExtendedPolymerizationTest {
    private static final String FILE_PATH_1 = "src/test/resources/day14/input-day14-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day14/input-day14-2.txt";

    @Test
    void getPart1Result() throws IOException {
        long result1 = Day14ExtendedPolymerization.getPart1Result(FILE_PATH_1);
        long result2 = Day14ExtendedPolymerization.getPart1Result(FILE_PATH_2);
        assertEquals(1588, result1);
        assertEquals(2891, result2);
    }
}
