package edu.lysak.day9;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9SmokeBasinTest {
    private static final String FILE_PATH_1 = "src/test/resources/day9/input-day9-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day9/input-day9-2.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day9SmokeBasin.getPart1Result(FILE_PATH_1);
        int result2 = Day9SmokeBasin.getPart1Result(FILE_PATH_2);
        assertEquals(15, result1);
        assertEquals(423, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        int result1 = Day9SmokeBasin.getPart2Result(FILE_PATH_1);
        int result2 = Day9SmokeBasin.getPart2Result(FILE_PATH_2);
        assertEquals(1134, result1);
        assertEquals(1198704, result2);
    }
}
