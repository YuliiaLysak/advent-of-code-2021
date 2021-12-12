package edu.lysak.day8;

import edu.lysak.Utils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8SevenSegmentSearchZZ {
    private static final String FILE_PATH = "src/main/resources/day8/input-day8.txt";

    public static void main(String[] args) throws IOException {
        long startTimeMs = System.currentTimeMillis();
        int sum = Utils.getInputDataAsStringList(FILE_PATH)
                .stream()
//                .peek(System.out::println)
                .map(it -> it.split(" \\| ", 2))
                .map(it -> decodeNumber(
                        List.of(it[0].split(" ")),
                        List.of(it[1].split(" "))
                ))
                .mapToInt(it -> it)
                .sum();
        // Sum: 986034
        System.out.println("\nSum: " + sum);
        System.out.println("\nExecution time: " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTimeMs) + " sec");
    }

    private static int decodeNumber(List<String> encodedDigits, List<String> encodedNumber) {
        List<Digit> digits = resolveDigits(encodedDigits);
        String decodedNumber = encodedNumber.stream()
                .map(it -> digits.stream().filter(digit -> digit.equalsTo(it)).findFirst())
                .map(Optional::orElseThrow)
                .map(it -> String.valueOf(it.value))
                .collect(Collectors.joining());
        return Integer.parseInt(decodedNumber);
    }

    private static List<Digit> resolveDigits(List<String> encodedDigits) {
        Map<Integer, List<Digit>> digits = encodedDigits.stream()
                .map(Day8SevenSegmentSearchZZ::possibleDigits)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(it -> it.value));

        for (var outer : digits.keySet()) {
            for (var inner : digits.keySet()) {
                digits.put(inner, digits.get(inner)
                        .stream()
                        .filter(it -> digits.get(outer).stream().anyMatch(valid -> it.value == valid.value || valid.intersectedWith(it)))
                        .collect(Collectors.toUnmodifiableList()));
            }
        }

        return digits.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possibleDigits(String chars) {
        return switch (chars.length()) {
            case 2 -> possible1(getVariations(chars));
            case 3 -> possible7(getVariations(chars));
            case 4 -> possible4(getVariations(chars));
            case 5 -> {
                var list = new ArrayList<Digit>();
                list.addAll(possible2(getVariations(chars)));
                list.addAll(possible3(getVariations(chars)));
                list.addAll(possible5(getVariations(chars)));
                yield List.copyOf(list);
            }
            case 6 -> {
                var list = new ArrayList<Digit>();
                list.addAll(possible0(getVariations(chars)));
                list.addAll(possible6(getVariations(chars)));
                list.addAll(possible9(getVariations(chars)));
                yield List.copyOf(list);
            }
            case 7 -> possible8(getVariations(chars));
            default -> throw new IllegalArgumentException();
        };
    }

    static List<char[]> getVariations(String chars) {
        if (chars.length() < 2) {
            throw new IllegalArgumentException();
        }
        if (chars.length() == 2) {
            return List.of(
                    new char[]{chars.charAt(0), chars.charAt(1)},
                    new char[]{chars.charAt(1), chars.charAt(0)}
            );
        }
        var variations = new ArrayList<char[]>();
        for (int i = 0; i < chars.length(); i++) {
            String head = chars.substring(i, i + 1);
            String next = chars.substring(0, i) + chars.substring(i + 1);
            List<char[]> subset = getVariations(next)
                    .stream()
                    .map(String::new)
                    .map(it -> head + it)
                    .map(String::toCharArray)
                    .collect(Collectors.toList());
            variations.addAll(subset);
        }
        return List.copyOf(variations);
    }

    static List<Digit> possible0(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(0)
                                .topLine(it[0])
                                .topLeft(it[1])
                                .topRight(it[2])
                                .bottomLeft(it[3])
                                .bottomRight(it[4])
                                .bottomLine(it[5])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible1(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(1)
                                .topRight(it[0])
                                .bottomRight(it[1])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible2(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(2)
                                .topLine(it[0])
                                .topRight(it[1])
                                .midLine(it[2])
                                .bottomLeft(it[3])
                                .bottomLine(it[4])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible3(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(3)
                                .topLine(it[0])
                                .topRight(it[1])
                                .midLine(it[2])
                                .bottomRight(it[3])
                                .bottomLine(it[4])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible4(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(4)
                                .topLeft(it[0])
                                .topRight(it[1])
                                .midLine(it[2])
                                .bottomRight(it[3])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible5(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(5)
                                .topLine(it[0])
                                .topLeft(it[1])
                                .midLine(it[2])
                                .bottomRight(it[3])
                                .bottomLine(it[4])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible6(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(6)
                                .topLine(it[0])
                                .topLeft(it[1])
                                .midLine(it[2])
                                .bottomLeft(it[3])
                                .bottomRight(it[4])
                                .bottomLine(it[5])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible7(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(7)
                                .topLine(it[0])
                                .topRight(it[1])
                                .bottomRight(it[2])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible8(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(8)
                                .topLine(it[0])
                                .topLeft(it[1])
                                .topRight(it[2])
                                .midLine(it[3])
                                .bottomLeft(it[4])
                                .bottomRight(it[5])
                                .bottomLine(it[6])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    static List<Digit> possible9(List<char[]> variations) {
        return variations.stream()
                .map(it ->
                        Digit.builder(9)
                                .topLine(it[0])
                                .topLeft(it[1])
                                .topRight(it[2])
                                .midLine(it[3])
                                .bottomRight(it[4])
                                .bottomLine(it[5])
                                .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }


    static final class Digit {
        final int value;
        final Character topLine;
        final Character topLeft;
        final Character topRight;
        final Character midLine;
        final Character bottomLeft;
        final Character bottomRight;
        final Character bottomLine;

        static Builder builder(int value) {
            return new Builder(value);
        }

        private Digit(Builder builder) {
            this.value = builder.value;
            this.topLine = builder.topLine;
            this.topLeft = builder.topLeft;
            this.topRight = builder.topRight;
            this.midLine = builder.midLine;
            this.bottomLeft = builder.bottomLeft;
            this.bottomRight = builder.bottomRight;
            this.bottomLine = builder.bottomLine;
        }

        boolean intersectedWith(Digit another) {
            return intersected(this.topLine, another.topLine)
                    && intersected(this.topLeft, another.topLeft)
                    && intersected(this.topRight, another.topRight)
                    && intersected(this.midLine, another.midLine)
                    && intersected(this.bottomLeft, another.bottomLeft)
                    && intersected(this.bottomRight, another.bottomRight)
                    && intersected(this.bottomLine, another.bottomLine);
        }

        private boolean intersected(Character a, Character b) {
            return a == null || b == null || a.equals(b);
        }

        boolean equalsTo(String digitChars) {
            return Stream.of(topLine, topLeft, topRight, midLine, bottomLeft, bottomRight, bottomLine)
                    .filter(Objects::nonNull)
                    .map(Objects::toString)
                    .sorted()
                    .collect(Collectors.joining())
                    .equals(Stream.of(digitChars.split(""))
                            .sorted()
                            .collect(Collectors.joining()));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Digit digit = (Digit) o;
            return value == digit.value && Objects.equals(topLine, digit.topLine) && Objects.equals(topLeft, digit.topLeft) && Objects.equals(topRight, digit.topRight) && Objects.equals(midLine, digit.midLine) && Objects.equals(bottomLeft, digit.bottomLeft) && Objects.equals(bottomRight, digit.bottomRight) && Objects.equals(bottomLine, digit.bottomLine);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, topLine, topLeft, topRight, midLine, bottomLeft, bottomRight, bottomLine);
        }

        @Override
        public String toString() {
            return " " + repeat(topLine, 4) + " "
                    + "\n" + toKnown(topLeft) + repeat(' ', 4) + toKnown(topRight)
                    + "\n" + toKnown(topLeft) + repeat(' ', 4) + toKnown(topRight)
                    + "\n" + " " + repeat(midLine, 4) + " "
                    + "\n" + toKnown(bottomLeft) + repeat(' ', 4) + toKnown(bottomRight)
                    + "\n" + toKnown(bottomLeft) + repeat(' ', 4) + toKnown(bottomRight)
                    + "\n" + " " + repeat(bottomLine, 4) + " ";
        }

        private String repeat(Character ch, int count) {
            StringBuilder sb = new StringBuilder();
            while (count-- > 0) {
                sb.append(toKnown(ch));
            }
            return sb.toString();
        }

        private char toKnown(Character ch) {
            return ch != null ? ch : '.';
        }


        static final class Builder {
            final int value;
            Character topLine;
            Character topLeft;
            Character topRight;
            Character midLine;
            Character bottomLeft;
            Character bottomRight;
            Character bottomLine;

            Builder(int value) {
                this.value = value;
            }

            Builder topLine(Character topLine) {
                this.topLine = topLine;
                return this;
            }

            Builder topLeft(Character topLeft) {
                this.topLeft = topLeft;
                return this;
            }

            Builder topRight(Character topRight) {
                this.topRight = topRight;
                return this;
            }

            Builder midLine(Character midLine) {
                this.midLine = midLine;
                return this;
            }

            Builder bottomLeft(Character bottomLeft) {
                this.bottomLeft = bottomLeft;
                return this;
            }

            Builder bottomRight(Character bottomRight) {
                this.bottomRight = bottomRight;
                return this;
            }

            Builder bottomLine(Character bottomLine) {
                this.bottomLine = bottomLine;
                return this;
            }

            Digit build() {
                return new Digit(this);
            }
        }
    }
}
