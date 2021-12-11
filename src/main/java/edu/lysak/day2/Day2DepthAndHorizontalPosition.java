package edu.lysak.day2;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2DepthAndHorizontalPosition {

    private static final String FILE_PATH = "src/main/resources/day2/input-day2.txt";

    public static void main(String[] args) throws IOException {
        Map<String, List<Integer>> commands = getCommands(FILE_PATH);
        int depthPosition = getDepthPosition(commands);
        int horizontalPosition = getHorizontalPosition(commands);
        System.out.println("Product of depth and horizontal position = " + getPart1Result(depthPosition, horizontalPosition));
        System.out.println("Product of depth and horizontal position with aim = " + getPart2Result(FILE_PATH));
    }

    public static int getPart1Result(int depthPosition, int horizontalPosition) {
        return depthPosition * horizontalPosition;
    }

    public static int getPart2Result(String filePath) throws IOException {
        List<String> commands = Utils.getInputDataAsStringList(filePath);
        int depthPosition = 0;
        int horizontalPosition = 0;
        int aim = 0;
        for (String s : commands) {
            String[] command = s.split(" ");
            int points = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward" -> {
                    horizontalPosition += points;
                    depthPosition += getPart1Result(aim, points);
                }
                case "down" -> aim += points;
                case "up" -> aim -= points;
            }
        }
        return getPart1Result(horizontalPosition, depthPosition);
    }

    public static int getHorizontalPosition(Map<String, List<Integer>> commands) {
        List<Integer> forward = commands.get("forward");
        return forward.stream().mapToInt(it -> it).sum();
    }

    public static int getDepthPosition(Map<String, List<Integer>> commands) {
        List<Integer> down = commands.get("down");
        List<Integer> up = commands.get("up");
        int downSum = down.stream().mapToInt(it -> it).sum();
        int upSum = up.stream().mapToInt(it -> it).sum();
        return downSum - upSum;
    }

    public static Map<String, List<Integer>> getCommands(String filePath) throws IOException {
        List<String> commands = Utils.getInputDataAsStringList(filePath);
        return commands.stream()
                .map(it -> it.split(" "))
                .collect(Collectors.groupingBy(
                        a -> a[0],
                        Collectors.mapping(a -> Integer.parseInt(a[1]), Collectors.toList())
                ));
    }
}
