package edu.lysak.day3;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day3PowerConsumption {
    private static final String FILE_PATH = "src/main/resources/day3/input-day3.txt";

    public static void main(String[] args) throws IOException {
        List<String> commands = Utils.getInputDataAsStringList(FILE_PATH);
        System.out.println("Power consumption = " + getPart1Result(commands));
        System.out.println("Life support rating = " + getPart2Result(commands));
    }

    public static int getPart2Result(List<String> commands) {
        return getOxygenGeneratorRating(commands) * getCO2ScrubberRating(commands);
    }

    public static int getPart1Result(List<String> commands) {
        return getPowerConsumption(commands);
    }

    private static int getOxygenGeneratorRating(List<String> commands) {
        int commandLength = getCommandLength(commands);
        List<String> commandsCopy = new ArrayList<>(commands);

        for (int i = 0; i < commandLength; i++) {
            Map<Integer, int[]> bitsCount = getBitsCount(commandsCopy);
            commandsCopy = filterOxygenByBits(bitsCount.get(i), i, commandsCopy);
            if (commandsCopy.size() == 1) {
                break;
            }
        }

        return Integer.parseInt(commandsCopy.get(0), 2);
    }

    private static int getCO2ScrubberRating(List<String> commands) {
        int commandLength = getCommandLength(commands);
        List<String> commandsCopy = new ArrayList<>(commands);

        for (int i = 0; i < commandLength; i++) {
            Map<Integer, int[]> bitsCount = getBitsCount(commandsCopy);
            commandsCopy = filterCO2ByBits(bitsCount.get(i), i, commandsCopy);
            if (commandsCopy.size() == 1) {
                break;
            }
        }

        return Integer.parseInt(commandsCopy.get(0), 2);
    }

    private static List<String> filterOxygenByBits(int[] bits, int bitPosition, List<String> commands) {
        if (bits[1] >= bits[0]) {
            return commands.stream()
                    .filter(it -> it.charAt(bitPosition) == '1')
                    .collect(Collectors.toList());
        } else {
            return commands.stream()
                    .filter(it -> it.charAt(bitPosition) == '0')
                    .collect(Collectors.toList());
        }
    }

    private static List<String> filterCO2ByBits(int[] bits, int bitPosition, List<String> commands) {
        if (bits[0] <= bits[1]) {
            return commands.stream()
                    .filter(it -> it.charAt(bitPosition) == '0')
                    .collect(Collectors.toList());
        } else {
            return commands.stream()
                    .filter(it -> it.charAt(bitPosition) == '1')
                    .collect(Collectors.toList());
        }
    }

    private static int getCommandLength(List<String> commands) {
        return commands.get(0).length();
    }

    private static int getPowerConsumption(List<String> commands) {
        // <position, int[0]= 0 count, int[1] = 1 count>
        Map<Integer, int[]> bitsCount = getBitsCount(commands);

        StringBuilder gRate = new StringBuilder();
        StringBuilder eRate = new StringBuilder();
        for (var pair : bitsCount.entrySet()) {

            if (pair.getValue()[1] > pair.getValue()[0]) {
                gRate.append("1");
                eRate.append("0");
            } else {
                gRate.append("0");
                eRate.append("1");
            }
        }

        int gammaRate = Integer.parseInt(gRate.toString(), 2);
        int epsilonRate = Integer.parseInt(eRate.toString(), 2);
        return gammaRate * epsilonRate;
    }

    private static Map<Integer, int[]> getBitsCount(List<String> commands) {
        Map<Integer, int[]> bitsCount = new HashMap<>();

        for (String command : commands) {
            String[] split = command.split("");
            for (int i = 0; i < split.length; i++) {
                int[] counts;
                if (bitsCount.get(i) == null) {
                    counts = new int[2];
                    bitsCount.put(i, counts);
                } else {
                    counts = bitsCount.get(i);
                }

                if ("0".equals(split[i])) {
                    counts[0]++;
                } else {
                    counts[1]++;
                }
            }
        }
        return bitsCount;
    }
}
