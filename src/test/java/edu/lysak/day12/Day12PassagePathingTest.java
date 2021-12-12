package edu.lysak.day12;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12PassagePathingTest {
    private static final String FILE_PATH_1 = "src/test/resources/day12/input-day12-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day12/input-day12-2.txt";
    private static final String FILE_PATH_3 = "src/test/resources/day12/input-day12-3.txt";
    private static final String FILE_PATH_4 = "src/test/resources/day12/input-day12-4.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day12PassagePathing.getPart1Result(FILE_PATH_1);
        int result2 = Day12PassagePathing.getPart1Result(FILE_PATH_2);
        int result3 = Day12PassagePathing.getPart1Result(FILE_PATH_3);
        int result4 = Day12PassagePathing.getPart1Result(FILE_PATH_4);
        assertEquals(10, result1);
        assertEquals(19, result2);
        assertEquals(226, result3);
        assertEquals(3292, result4);
    }
}
