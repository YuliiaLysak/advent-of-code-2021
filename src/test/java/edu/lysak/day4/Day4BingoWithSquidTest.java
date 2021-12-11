package edu.lysak.day4;

import edu.lysak.Utils;
import edu.lysak.day3.Day3PowerConsumption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4BingoWithSquidTest {
    private static final String FILE_PATH_1 = "src/test/resources/day4/input-day4-1.txt";
    private static final String FILE_PATH_2 = "src/test/resources/day4/input-day4-2.txt";
    private List<String> input1;
    private List<String> input2;
    private String[] numbers1;
    private String[] numbers2;

    @BeforeEach
    void setUp() throws IOException {
        input1 = Utils.getInputDataAsStringList(FILE_PATH_1);
        input2 = Utils.getInputDataAsStringList(FILE_PATH_2);
        numbers1 = input1.get(0).split(",");
        numbers2 = input2.get(0).split(",");
    }

    @Test
    void getPart1Result() {
        int result1 = Day4BingoWithSquid.getPart1Result(input1, numbers1);
        int result2 = Day4BingoWithSquid.getPart1Result(input2, numbers2);
        assertEquals(4512, result1);
        assertEquals(63552, result2);
    }

    @Test
    void getPart2Result() {
        int result1 = Day4BingoWithSquid.getPart2Result(input1, numbers1);
        int result2 = Day4BingoWithSquid.getPart2Result(input2, numbers2);
        assertEquals(1924, result1);
        assertEquals(9020, result2);
    }
}
