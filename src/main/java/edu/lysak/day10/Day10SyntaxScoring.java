package edu.lysak.day10;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Day10SyntaxScoring {
    private static final String FILE_PATH = "src/main/resources/day10/input-day10.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Total syntax error score = " + getPart1Result(FILE_PATH));
        System.out.println("Middle completion score = " + getPart2Result(FILE_PATH));
    }

    public static int getPart1Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        List<String> invalidParenthesis = getInvalidParenthesis(input);
        return getSyntaxErrorScore(invalidParenthesis);
    }

    public static long getPart2Result(String filePath) throws IOException {
        List<String> input = Utils.getInputDataAsStringList(filePath);
        List<String> closingParenthesis = getClosingParenthesis(input);
        return getMiddleCompletionScore(closingParenthesis);
    }

    private static List<String> getClosingParenthesis(List<String> input) {
        List<String> closingParenthesis = new ArrayList<>();
        for (String line : input) {
            Deque<String> deque = new ArrayDeque<>();
            String[] tokens = line.split("");
            for (String token : tokens) {
                if (isOpeningParenthesis(token)) {
                    deque.offerLast(token);
                    continue;
                }
                if (isClosingParenthesis(token)) {
                    String invalid = discardParenthesis(token, deque);
                    if (invalid != null) {
                        deque.clear();
                        break;
                    }
                }
            }
            if (!deque.isEmpty()) {
                closingParenthesis.add(convertParenthesis(deque));
            }
        }
        return closingParenthesis;
    }

    private static String convertParenthesis(Deque<String> deque) {
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            String parenthesis = switch (deque.pollLast()) {
                case "(" -> ")";
                case "[" -> "]";
                case "{" -> "}";
                case "<" -> ">";
                default -> throw new IllegalArgumentException();
            };
            result.append(parenthesis);
        }
        return result.toString();
    }

    private static long getMiddleCompletionScore(List<String> closingParenthesis) {
        return closingParenthesis.stream()
                .mapToLong(it -> getCompletionScore(it))
                .sorted()
                .skip(closingParenthesis.size() / 2)
                .findFirst()
                .orElseThrow();
    }

    private static long getCompletionScore(String line) {
        long totalScore = 0;
        for (String s : line.split("")) {
            totalScore = switch (s) {
                case ")" -> totalScore * 5 + 1;
                case "]" -> totalScore * 5 + 2;
                case "}" -> totalScore * 5 + 3;
                case ">" -> totalScore * 5 + 4;
                default -> throw new IllegalArgumentException();
            };
        }
        return totalScore;
    }

    private static List<String> getInvalidParenthesis(List<String> input) {
        List<String> invalidParenthesis = new ArrayList<>();
        for (String line : input) {
            Deque<String> deque = new ArrayDeque<>();
            String[] tokens = line.split("");
            for (String token : tokens) {
                if (isOpeningParenthesis(token)) {
                    deque.offerLast(token);
                    continue;
                }
                if (isClosingParenthesis(token)) {
                    String invalid = discardParenthesis(token, deque);
                    if (invalid != null) {
                        invalidParenthesis.add(invalid);
                        break;
                    }
                }
            }
        }
        return invalidParenthesis;
    }

    private static String discardParenthesis(String parenthesis, Deque<String> deque) {
        String currentToken = deque.getLast();
        switch (parenthesis) {
            case ")" -> {
                if ("(".equals(currentToken)) {
                    deque.pollLast();
                    return null;
                }
            }
            case "]" -> {
                if ("[".equals(currentToken)) {
                    deque.pollLast();
                    return null;
                }
            }
            case "}" -> {
                if ("{".equals(currentToken)) {
                    deque.pollLast();
                    return null;
                }
            }
            case ">" -> {
                if ("<".equals(currentToken)) {
                    deque.pollLast();
                    return null;
                }
            }
        }

        return parenthesis;
    }

    private static int getSyntaxErrorScore(List<String> invalidParenthesis) {
        return invalidParenthesis.stream()
                .mapToInt(it ->
                        switch (it) {
                            case ")" -> 3;
                            case "]" -> 57;
                            case "}" -> 1197;
                            case ">" -> 25137;
                            default -> throw new IllegalArgumentException();
                        })
                .sum();
    }

    private static boolean isOpeningParenthesis(String parenthesis) {
        return "(".equals(parenthesis)
                || "[".equals(parenthesis)
                || "{".equals(parenthesis)
                || "<".equals(parenthesis);
    }

    private static boolean isClosingParenthesis(String parenthesis) {
        return ")".equals(parenthesis)
                || "]".equals(parenthesis)
                || "}".equals(parenthesis)
                || ">".equals(parenthesis);
    }
}
