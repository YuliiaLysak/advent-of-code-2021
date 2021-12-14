package edu.lysak.day13;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13TransparentOrigamiTest {
    private static final String FILE_PATH_1 = "src/test/resources/day13/input-day13-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day13/input-day13-2.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day13TransparentOrigami.getPart1Result(FILE_PATH_1);
        int result2 = Day13TransparentOrigami.getPart1Result(FILE_PATH_2);
        assertEquals(17, result1);
        assertEquals(810, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        char[][] result1 = Day13TransparentOrigami.getPart2Result(FILE_PATH_1);
        char[][] result2 = Day13TransparentOrigami.getPart2Result(FILE_PATH_2);
        char[][] expected1 = {
                {'#', '#', '#', '#', '#',},
                {'#', '.', '.', '.', '#',},
                {'#', '.', '.', '.', '#',},
                {'#', '.', '.', '.', '#',},
                {'#', '#', '#', '#', '#',},
                {'.', '.', '.', '.', '.',},
                {'.', '.', '.', '.', '.',}
        };
        char[][] expected2 = {
                {'#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '#', '#', '.', '.', '#', '.', '.', '#', '.', '#', '#', '#', '.', '.', '.', '#', '#', '.', '.', '#', '#', '#', '#', '.', '#', '#', '#', '.', '.'},
                {'#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.',},
                {'#', '#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '#', '#', '.', '.', '#', '.', '.', '#', '.', '#', '#', '#', '.', '.', '#', '.', '.', '.', '.', '#', '#', '#', '.', '.', '#', '.', '.', '#', '.',},
                {'#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '#', '#', '#', '.', '.',},
                {'#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#', '.', '.',},
                {'#', '.', '.', '#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '.', '.', '.', '#', '#', '.', '.', '#', '#', '#', '.', '.', '.', '#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.'}
        };
        assertArrayEquals(expected1, result1);
        assertArrayEquals(expected2, result2);
    }
}
