package edu.lysak.day7;

import edu.lysak.Utils;
import edu.lysak.day4.Day4BingoWithSquid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day7WhaleAndCrabsTest {
    private static final String FILE_PATH_1 = "src/test/resources/day7/input-day7-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day7/input-day7-2.txt";
    private List<Integer> positions1;
    private List<Integer> positions2;

    @BeforeEach
    void setUp() throws IOException {
        positions1 = Utils.splitCSVInputToIntegerList(FILE_PATH_1);
        positions2 = Utils.splitCSVInputToIntegerList(FILE_PATH_2);
    }

    @Test
    void getPart1Result() {
        int result1 = Day7WhaleAndCrabs.getPart1Result(positions1);
        int result2 = Day7WhaleAndCrabs.getPart1Result(positions2);
        assertEquals(37, result1);
        assertEquals(351901, result2);
    }

    @Test
    void getPart2Result() {
        int result1 = Day7WhaleAndCrabs.getPart2Result(positions1);
        int result2 = Day7WhaleAndCrabs.getPart2Result(positions2);
        assertEquals(168, result1);
        assertEquals(101079875, result2);
    }
}
