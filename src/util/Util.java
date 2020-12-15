package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author brisdalen
 */
public class Util {

    public static String getDayFilePath(int day) {
        return String.format("src/day%d/input.txt", day);
    }

    public static Stream<String> linesToStringStream(String filepath) throws IOException {
        return Files.lines(Paths.get(filepath));
    }

    public static List<String> readAllLinesStringList(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
        String line;
        var list = new ArrayList<String>();

        while((line = reader.readLine()) != null) {
            list.add(line);
        }

        return list;
    }

    public static String allLinesToString(String filepath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Path.of(filepath)).forEach(s -> {
            s = s.trim();
            stringBuilder.append(s);
        });
        return stringBuilder.toString();
    }

    public static String[] readFileAndSplit(String filepath, String splitBy) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
        String line;
        var builder = new StringBuilder();

        while((line = reader.readLine()) != null) {
            if(line.isBlank()) {
                builder.append("\n");
            }
            builder.append(line + "\n");
        }

        return builder.toString().split(splitBy);
    }

    public static IntStream linesToIntStream(String filepath) throws IOException {
        return linesToStringStream(filepath)
                .mapToInt(Integer::valueOf);
    }

    public static Integer[] readAllLinesArray(String filepath) throws IOException {
        var lines = Files.readAllLines(Paths.get(filepath));
        var numbers = new ArrayList<Integer>();
        for(String s : lines) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers.toArray(new Integer[lines.size()]);
    }

    public static Integer[] readStringToArray(String inputString) throws IOException {
        var split = inputString.split("\n");
        var numbers = new ArrayList<Integer>();
        for(String s : split) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers.toArray(new Integer[split.length]);
    }

    public static Long[] readAllLinesArrayLong(String filepath) throws IOException {
        var lines = Files.readAllLines(Paths.get(filepath));
        var numbers = new ArrayList<Long>();
        for(String s : lines) {
            numbers.add(Long.parseLong(s));
        }
        return numbers.toArray(new Long[lines.size()]);
    }

    public static int[] csvIntsToIntArray(String filepath) throws IOException {
        String input = Files.readString(Paths.get(filepath));
        return Arrays.stream(input.trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    //public static Supplier<T extends Object> getNewSupplier(T type)

}
