package edu.lysak.day15;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15ChitonTest {
    private static final String FILE_PATH_1 = "src/test/resources/day15/input-day15-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day15/input-day15-2.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day15Chiton.getPart1Result(FILE_PATH_1);
        int result2 = Day15Chiton.getPart1Result(FILE_PATH_2);
        assertEquals(40, result1);
        assertEquals(790, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        int result1 = Day15Chiton.getPart2Result(FILE_PATH_1);
        int result2 = Day15Chiton.getPart2Result(FILE_PATH_2);
        assertEquals(315, result1);
        // TODO: 16.12.2021 should be 2998, I have 3001. Find out why?
        assertEquals(2998, result2);
    }
}
