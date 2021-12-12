package edu.lysak.day8;

import edu.lysak.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.*;

public class Day8SevenSegmentSearch {
    private static final String FILE_PATH = "src/main/resources/day8/input-day8.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Times, when digits 1, 4, 7, or 8 appear => " + getPart1Result(FILE_PATH));
        System.out.println("Output values sum => " + getPart2Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        return getEasyDigitCount(input);
    }

    private static int getEasyDigitCount(List<String> input) {
        int totalEasyDigitCount = 0;
        for (String line : input) {
            String digits = line.substring(line.indexOf("|") + 2);
            totalEasyDigitCount += Arrays.stream(digits.split(" "))
                    .map(String::length)
                    .filter(it -> it == 2 || it == 4 || it == 3 || it == 7)
                    .count();
        }
        return totalEasyDigitCount;
    }

    public static int getPart2Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        List<Integer> outputValues = getOutputValues(input);
        return outputValues.stream()
                .mapToInt(it -> it)
                .sum();
    }

    private static List<Integer> getOutputValues(List<String> input) {
        List<Integer> outputValues = new ArrayList<>();
        for (String line : input) {
            Map<Integer, Set<String>> digitPattern = new HashMap<>();
            String linePart = line.substring(0, line.indexOf("|") - 1);
            String[] patterns = linePart.split(" ");
            for (String pattern : patterns) {
                processEasyDigits(digitPattern, pattern);
            }

            find9or6or0(9, digitPattern, patterns);
            find6Partially(digitPattern);
            find9or6or0(6, digitPattern, patterns);

            patterns = removeFoundPattern(digitPattern.get(1), patterns);
            patterns = removeFoundPattern(digitPattern.get(4), patterns);
            patterns = removeFoundPattern(digitPattern.get(6), patterns);
            patterns = removeFoundPattern(digitPattern.get(7), patterns);
            patterns = removeFoundPattern(digitPattern.get(8), patterns);
            patterns = removeFoundPattern(digitPattern.get(9), patterns);

            find9or6or0(0, digitPattern, patterns);
            patterns = removeFoundPattern(digitPattern.get(0), patterns);
            find3(digitPattern, patterns);
            patterns = removeFoundPattern(digitPattern.get(3), patterns);

            find2Partially(digitPattern);
            find2And5(digitPattern, patterns);


            String digits = line.substring(line.indexOf("|") + 2);
            outputValues.add(getOutputValue(digitPattern, digits.split(" ")));
        }
        return outputValues;
    }

    private static void processEasyDigits(Map<Integer, Set<String>> digitPattern, String pattern) {
        int length = pattern.length();
        String[] split = pattern.split("");

        if (length == 2) {
            digitPattern.put(1, new HashSet<>(Set.of(split)));

            addOrCreate(0, digitPattern, split);
            addOrCreate(3, digitPattern, split);
            addOrCreate(9, digitPattern, split);
        }

        if (length == 4) {
            digitPattern.put(4, new HashSet<>(Set.of(split)));

            addOrCreate(9, digitPattern, split);
        }

        if (length == 3) {
            digitPattern.put(7, new HashSet<>(Set.of(split)));

            addOrCreate(0, digitPattern, split);
            addOrCreate(3, digitPattern, split);
            addOrCreate(9, digitPattern, split);
        }

        if (length == 7) {
            digitPattern.put(8, new HashSet<>(Set.of(split)));
        }
    }

    private static void addOrCreate(int digit, Map<Integer, Set<String>> digitPattern, String[] split) {
        Set<String> set = digitPattern.get(digit);
        if (set == null) {
            digitPattern.put(digit, new HashSet<>(Set.of(split)));
        } else {
            set.addAll(Arrays.asList(split));
            digitPattern.put(digit, set);
        }
    }

    private static int getOutputValue(Map<Integer, Set<String>> digitPattern, String[] digits) {
        StringBuilder result = new StringBuilder();
        for (String digit : digits) {
            for (var pair : digitPattern.entrySet()) {
                Set<String> pattern = pair.getValue();
                List<String> ddddigits = Arrays.asList(digit.split(""));
                if (pattern.containsAll(ddddigits)
                        && pattern.size() == digit.length()) {
                    result.append(pair.getKey());
                }
            }
        }
        return Integer.parseInt(result.toString());
    }

    private static void find9or6or0(int digit, Map<Integer, Set<String>> digitPattern, String[] patterns) {
        Set<String> pattern9or6or0 = digitPattern.get(digit);
        for (String pattern : patterns) {
            int count = 0;
            for (char c : pattern.toCharArray()) {
                if (pattern9or6or0.contains(String.valueOf(c))) {
                    count++;
                }
            }
            if (pattern.length() == 6 && count == pattern9or6or0.size()) {
                pattern9or6or0.addAll(List.of(pattern.split("")));
                digitPattern.put(digit, pattern9or6or0);
            }
        }
    }

    private static void find6Partially(Map<Integer, Set<String>> digitPattern) {
        Set<String> pattern8 = digitPattern.get(8);
        Set<String> pattern1 = digitPattern.get(1);
        Set<String> pattern6 = new HashSet<>(pattern8);
        pattern6.removeAll(pattern1);
        digitPattern.put(6, pattern6);
    }

    private static String[] removeFoundPattern(Set<String> pattern, String[] patterns) {
        for (int i = 0; i < patterns.length; i++) {
            if (pattern.containsAll(Arrays.asList(patterns[i].split("")))
                    && pattern.size() == patterns[i].length()) {
                patterns = ArrayUtils.removeElement(patterns, patterns[i]);
            }
        }
        return patterns;
    }

    private static void find3(Map<Integer, Set<String>> digitPattern, String[] patterns) {
        Set<String> pattern3 = digitPattern.get(3);
        for (String pattern : patterns) {
            int count = 0;
            for (char c : pattern.toCharArray()) {
                if (pattern3.contains(String.valueOf(c))) {
                    count++;
                }
            }
            if (count == pattern3.size()) {
                pattern3.addAll(List.of(pattern.split("")));
                digitPattern.put(3, pattern3);
            }
        }
    }

    private static void find2Partially(Map<Integer, Set<String>> digitPattern) {
        Set<String> pattern8 = digitPattern.get(8);
        Set<String> pattern6 = digitPattern.get(6);
        Set<String> pattern2 = new HashSet<>(pattern8);
        pattern2.removeAll(pattern6);
        digitPattern.put(2, pattern2);
    }

    private static void find2And5(Map<Integer, Set<String>> digitPattern, String[] patterns) {
        Set<String> pattern2 = digitPattern.get(2);
        for (String pattern : patterns) {
            String[] pattern2Array = pattern2.toArray(new String[1]);
            if (pattern.contains(pattern2Array[0])) {
                pattern2.addAll(List.of(pattern.split("")));
                digitPattern.put(2, pattern2);
                patterns = removeFoundPattern(pattern2, patterns);
                break;
            }
        }

        digitPattern.put(5, new HashSet<>(Arrays.asList(patterns[0].split(""))));
    }
}
