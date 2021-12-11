package edu.lysak.day2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2DepthAndHorizontalPositionTest {
    private static final String FILE_PATH_1 = "src/test/resources/day2/input-day2-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day2/input-day2-2.txt";
    private Map<String, List<Integer>> commands1;
    private Map<String, List<Integer>> commands2;

    @BeforeEach
    void setUp() throws IOException {
        commands1 = Day2DepthAndHorizontalPosition.getCommands(FILE_PATH_1);
        commands2 = Day2DepthAndHorizontalPosition.getCommands(FILE_PATH_2);
    }

    @Test
    void getPart1Result() {
        int depthPosition1 = Day2DepthAndHorizontalPosition.getDepthPosition(commands1);
        int horizontalPosition1 = Day2DepthAndHorizontalPosition.getHorizontalPosition(commands1);
        int result1 = Day2DepthAndHorizontalPosition.getPart1Result(depthPosition1, horizontalPosition1);

        int depthPosition2 = Day2DepthAndHorizontalPosition.getDepthPosition(commands2);
        int horizontalPosition2 = Day2DepthAndHorizontalPosition.getHorizontalPosition(commands2);
        int result2 = Day2DepthAndHorizontalPosition.getPart1Result(depthPosition2, horizontalPosition2);
        assertEquals(150, result1);
        assertEquals(1507611, result2);
    }

    @Test
    void getPart2Result() throws IOException {
        int result1 = Day2DepthAndHorizontalPosition.getPart2Result(FILE_PATH_1);
        int result2 = Day2DepthAndHorizontalPosition.getPart2Result(FILE_PATH_2);
        assertEquals(900, result1);
        assertEquals(1880593125, result2);
    }
}
