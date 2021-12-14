package edu.lysak.day14;

import edu.lysak.Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14ExtendedPolymerization {
    private static final String FILE_PATH = "src/main/resources/day14/input-day14.txt";
    private static final int STEP_COUNT = 10;
    private static final int STEP_COUNT_2 = 40;

    public static void main(String[] args) throws IOException {
        System.out.println("Most common subtract least common = " + getPart1ResultNotOptimal(FILE_PATH, STEP_COUNT));
        System.out.println("Most common subtract least common (optimal) = " + getPart2ResultOptimal(FILE_PATH, STEP_COUNT_2));
    }

    public static long getPart1ResultNotOptimal(String filePath, int stepCount) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        Map<String, String> pairs = convertInputToInsertionPair(input);
        String line = input.get(0);
        for (int i = 0; i < stepCount; i++) {
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

    public static long getPart2ResultOptimal(String filePath, int stepCount) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        Map<String, String> insertionPairs = convertInputToInsertionPair(input);

        String inputLine = input.get(0);
        Map<String, Long> original = convertLineToPairMap(inputLine);
        Map<String, Long> resultPairs = new HashMap<>(Map.copyOf(original));
        for (int i = 0; i < stepCount; i++) {
            resultPairs = insertChar(resultPairs, insertionPairs);
        }

        long mostCount = getMostCommonElementCount(
                convertToCharPairs(resultPairs),
                inputLine.charAt(0),
                inputLine.charAt(inputLine.length() - 1)
        );
        long leastCount = getLeastCommonElementCount(
                convertToCharPairs(resultPairs),
                inputLine.charAt(0),
                inputLine.charAt(inputLine.length() - 1)
        );
        return (mostCount - leastCount) / 2;
    }

    private static Map<String, Long> convertLineToPairMap(String line) {
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < line.length() - 1; i++) {
            pairs.add(line.substring(i, i + 2));
        }
        return pairs.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static Map<String, Long> insertChar(Map<String, Long> input, Map<String, String> insertionPairs) {
        Map<String, Long> output = new HashMap<>();
        for (String pair : input.keySet()) {
            if (insertionPairs.containsKey(pair)) {
                Long count = input.getOrDefault(pair, 0L);
                String newPair1 = pair.charAt(0) + insertionPairs.get(pair);
                String newPair2 = insertionPairs.get(pair) + pair.charAt(1);
                Long count1 = output.getOrDefault(newPair1, 0L);
                output.put(newPair1, count1 + count);
                Long count2 = output.getOrDefault(newPair2, 0L);
                output.put(newPair2, count2 + count);
            }
        }
        return output;
    }

    private static Map<Character, Long> convertToCharPairs(Map<String, Long> array) {
        return array.entrySet().stream()
                .map(pair -> {
                    Map<Character, Long> charPairs = new HashMap<>();
                    charPairs.merge(pair.getKey().charAt(0), pair.getValue(), Long::sum);
                    charPairs.merge(pair.getKey().charAt(1), pair.getValue(), Long::sum);
                    return charPairs;
                })
                .flatMap(charPairs -> charPairs.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
    }

    private static long getMostCommonElementCount(Map<Character, Long> chars, char startChar, char endChar) {
        return chars.entrySet().stream()
                .peek(pair -> {
                    if (pair.getKey() == startChar) {
                        pair.setValue(pair.getValue() + 1);
                    }
                    if (pair.getKey() == endChar) {
                        pair.setValue(pair.getValue() + 1);
                    }
                })
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElseThrow();
    }

    private static long getLeastCommonElementCount(Map<Character, Long> chars, char startChar, char endChar) {
        return chars.entrySet().stream()
                .peek(pair -> {
                    if (pair.getKey() == startChar) {
                        pair.setValue(pair.getValue() + 1);
                    }
                    if (pair.getKey() == endChar) {
                        pair.setValue(pair.getValue() + 1);
                    }
                })
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElseThrow();
    }
}
