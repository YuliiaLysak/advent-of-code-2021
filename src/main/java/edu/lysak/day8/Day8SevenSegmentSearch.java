package edu.lysak.day8;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day8SevenSegmentSearch {
    private static final String FILE_PATH = "src/main/resources/day8/input-day8-1.txt";
    private static final String FILE_PATH_2 = "src/main/resources/day8/input-day8-2.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Times, when digits 1, 4, 7, or 8 appear => " + getPart1Result(FILE_PATH_2));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        return getEasyDigitCount(input);
    }

    private static int getEasyDigitCount(List<String> input) {
        int totalEasyDigitCount = 0;
        for (String line : input) {
            String digits = line.substring(line.indexOf("|") + 1);
            totalEasyDigitCount += Arrays.stream(digits.split(" "))
                    .map(String::length)
                    .filter(it -> it == 2 || it == 4 || it == 3 || it == 7)
                    .count();
        }
        return totalEasyDigitCount;
    }
}
