package edu.lysak.day3;

import edu.lysak.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3PowerConsumptionTest {
    private static final String FILE_PATH_1 = "src/test/resources/day3/input-day3-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day3/input-day3-2.txt";
    private List<String> commands1;
    private List<String> commands2;

    @BeforeEach
    void setUp() throws IOException {
        commands1 = Utils.getInputDataAsStringList(FILE_PATH_1);
        commands2 = Utils.getInputDataAsStringList(FILE_PATH_2);
    }

    @Test
    void getPart1Result() {
        int result1 = Day3PowerConsumption.getPart1Result(commands1);
        int result2 = Day3PowerConsumption.getPart1Result(commands2);
        assertEquals(198, result1);
        assertEquals(2498354, result2);
    }

    @Test
    void getPart2Result() {
        int result1 = Day3PowerConsumption.getPart2Result(commands1);
        int result2 = Day3PowerConsumption.getPart2Result(commands2);
        assertEquals(230, result1);
        assertEquals(3277956, result2);
    }
}
