package edu.lysak.day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8SevenSegmentSearchTest {
    private static final String FILE_PATH_1 = "src/test/resources/day8/input-day8-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day8/input-day8-2.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day8SevenSegmentSearch.getPart1Result(FILE_PATH_1);
        int result2 = Day8SevenSegmentSearch.getPart1Result(FILE_PATH_2);
        assertEquals(26, result1);
        assertEquals(387, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        int result1 = Day8SevenSegmentSearch.getPart2Result(FILE_PATH_1);
        int result2 = Day8SevenSegmentSearch.getPart2Result(FILE_PATH_2);
        assertEquals(61229, result1);
        assertEquals(986034, result2);
    }
}
