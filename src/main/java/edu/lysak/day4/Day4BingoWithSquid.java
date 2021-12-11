package edu.lysak.day4;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4BingoWithSquid {
    private static final String FILE_PATH = "src/main/resources/day4/input-day4.txt";
    private static String[][] winnerBoard;

    public static void main(String[] args) throws IOException {
        List<String> inputDataAsStringList = Utils.getInputDataAsStringList(FILE_PATH);
        String[] numbers = inputDataAsStringList.get(0).split(",");

        System.out.println("Final score = " + getPart1Result(inputDataAsStringList, numbers));
        System.out.println("Final score for the last winner board = " + getPart2Result(inputDataAsStringList, numbers));
    }

    public static int getPart1Result(List<String> input, String[] numbers) {
        List<String[][]> boards = getBoards(input);
        int winnerNumber = getWinnerNumber(numbers, boards);
        int unmarkedNumbersSum = getUnmarkedNumbersSum(winnerBoard);
        return winnerNumber * unmarkedNumbersSum;
    }

    public static int getPart2Result(List<String> input, String[] numbers) {
        List<String[][]> boards2 = getBoards(input);
        Map<Integer, String[][]> lastWinner = getLastWinner(numbers, boards2);
        int lastWinnerNumber = 0;
        for (var pair : lastWinner.entrySet()) {
            lastWinnerNumber = pair.getKey();
        }
        int lastUnmarkedNumbersSum = getUnmarkedNumbersSum(lastWinner.get(lastWinnerNumber));
        return lastWinnerNumber * lastUnmarkedNumbersSum;
    }

    private static int getWinnerNumber(String[] numbers, List<String[][]> boards) {
        for (String number : numbers) {
            drawNumber(number, boards);
            if (checkForWinners(boards)) {
                return Integer.parseInt(number);
            }
        }
        throw new IllegalArgumentException("There is no winning number");
    }

    private static Map<Integer, String[][]> getLastWinner(String[] numbers, List<String[][]> boards) {
        for (String number : numbers) {
            drawNumber(number, boards);
            List<String[][]> currentWinners = getCurrentWinners(boards);
            boards.removeAll(currentWinners);
            if (boards.size() == 0) {
                return Map.of(Integer.parseInt(number), currentWinners.get(0));
            }
        }
//        printAllBoards(boards);
        throw new IllegalArgumentException("There is no winners");
    }

    private static List<String[][]> getBoards(List<String> input) {
        List<String> collect = input.stream()
                .skip(1)
                .filter(it -> !it.isEmpty())
                .collect(Collectors.toList());

        List<String[][]> boards = new ArrayList<>();
        for (int k = 0; k < collect.size(); k++) {
            String[][] board;
            if (k % 5 == 0) {
                board = new String[5][5];
                boards.add(board);
            } else {
                board = boards.get(k / 5);
            }

            String[] row = collect.get(k).trim().split("\\s+");
            for (int i = 0; i < row.length; i++) {
                board[k % 5][i] = String.valueOf(row[i]);
            }
        }
        return boards;
    }

    private static int getUnmarkedNumbersSum(String[][] winnerBoard) {
        int sum = 0;
        for (String[] strings : winnerBoard) {
            for (String string : strings) {
                if (!string.contains("m")) {
                    sum += Integer.parseInt(string);
                }
            }
        }
        return sum;
    }

    private static boolean checkForWinners(List<String[][]> boards) {
        for (var board : boards) {
            for (int i = 0; i < board.length; i++) {
                int rowMarkedCount = 0;
                int columnMarkedCount = 0;
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].contains("m")) {
                        rowMarkedCount++;
                    }
                    if (board[j][i].contains("m")) {
                        columnMarkedCount++;
                    }
                }

                if (rowMarkedCount == 5 || columnMarkedCount == 5) {
                    winnerBoard = board;
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String[][]> getCurrentWinners(List<String[][]> boards) {
        List<String[][]> winners = new ArrayList<>();
        for (String[][] board : boards) {
            for (int i = 0; i < board.length; i++) {
                int rowMarkedCount = 0;
                int columnMarkedCount = 0;
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].contains("m")) {
                        rowMarkedCount++;
                    }
                    if (board[j][i].contains("m")) {
                        columnMarkedCount++;
                    }
                }

                if (rowMarkedCount == 5 || columnMarkedCount == 5) {
                    winners.add(board);
                }
            }
        }

        return winners;
    }

    private static void drawNumber(String num, List<String[][]> boards) {
        for (var board : boards) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (num.equals(board[i][j])) {
                        board[i][j] += "m";
                    }
                }
            }
        }
    }

    private static void printAllBoards(List<String[][]> boards) {
        for (var matrix : boards) {
            for (var vector : matrix) {
                for (var i : vector) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println("==================");
        }
    }
}
