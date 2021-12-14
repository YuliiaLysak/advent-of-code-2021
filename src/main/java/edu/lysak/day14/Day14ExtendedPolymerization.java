package edu.lysak.day14;

import edu.lysak.Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14ExtendedPolymerization {
    private static final String FILE_PATH = "src/main/resources/day14/input-day14.txt";
    private static final int STEP_COUNT = 10;

    public static void main(String[] args) throws IOException {
        System.out.println("Most common subtract least common = " + getPart1Result(FILE_PATH));
    }

    public static long getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        Map<String, String> pairs = convertInputToInsertionPair(input);
        String line = input.get(0);
        for (int i = 0; i < STEP_COUNT; i++) {
            line = insertChar(line, pairs);
        }

        long mostCount = getMostCommonElementCount(line);
        long leastCount = getLeastCommonElementCount(line);
        return mostCount - leastCount;
    }

    private static Map<String, String> convertInputToInsertionPair(List<String> input) {
        return input.stream()
                .skip(2)
                .map(line -> line.split(" -> "))
                .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
    }

    private static String insertChar(String line, Map<String, String> pairs) {
        List<String> chars = new ArrayList<>(Arrays.asList(line.split("")));
        for (int i = 0; i < chars.size() - 1; i++) {
            String pair = chars.get(i) + chars.get(i + 1);
            if (pairs.containsKey(pair)) {
                chars.add(i + 1, pairs.get(pair));
                i++;
            }
        }
        return StringUtils.join(chars, "");
    }

    private static long getMostCommonElementCount(String line) {
        return Arrays.stream(line.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElseThrow();
    }

    private static long getLeastCommonElementCount(String line) {
        return Arrays.stream(line.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElseThrow();
    }
}
