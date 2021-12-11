package edu.lysak.day10;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10SyntaxScoringTest {
    private static final String FILE_PATH_1 = "src/test/resources/day10/input-day10-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day10/input-day10-2.txt";

    @Test
    void getPart1Result() throws IOException {
        int result1 = Day10SyntaxScoring.getPart1Result(FILE_PATH_1);
        int result2 = Day10SyntaxScoring.getPart1Result(FILE_PATH_2);
        assertEquals(26397, result1);
        assertEquals(168417, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        long result1 = Day10SyntaxScoring.getPart2Result(FILE_PATH_1);
        long result2 = Day10SyntaxScoring.getPart2Result(FILE_PATH_2);
        assertEquals(288957L, result1);
        assertEquals(2802519786L, result2);
    }
}
