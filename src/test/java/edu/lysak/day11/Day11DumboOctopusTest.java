package edu.lysak.day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11DumboOctopusTest {
    private static final String FILE_PATH_1 = "src/test/resources/day11/input-day11-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day11/input-day11-2.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day11DumboOctopus.getPart1Result(FILE_PATH_1);
        int result2 = Day11DumboOctopus.getPart1Result(FILE_PATH_2);
        assertEquals(1656, result1);
        assertEquals(1741, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        int result1 = Day11DumboOctopus.getPart2Result(FILE_PATH_1);
        int result2 = Day11DumboOctopus.getPart2Result(FILE_PATH_2);
        assertEquals(195, result1);
        assertEquals(440, result2);
    }
}
