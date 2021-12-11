package edu.lysak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Utils {

    public static List<String> getInputDataAsStringList(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    public static List<Integer> getInputDataAsIntegerList(String filePath) throws IOException {
        return getInputDataAsStringList(filePath).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> splitCSVInputToIntegerList(String filePath) throws IOException {
        return getInputDataAsStringList(filePath).stream()
                .map(it -> it.split(","))
                .flatMap(Arrays::stream)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private Utils() {
    }
}
