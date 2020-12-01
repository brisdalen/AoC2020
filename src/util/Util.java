package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author brisdalen
 */
public class Util {

    public static Stream<String> readAllLines(String filepath) throws IOException {
        return Files.lines(Paths.get(filepath));
    }

    public static Integer[] readAllLinesArray(String filepath) throws IOException {
        var lines = Files.readAllLines(Paths.get(filepath));
        var numbers = new ArrayList<Integer>();
        for(String s : lines) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers.toArray(new Integer[lines.size()]);
    }

    public static int[] csvIntsToIntArray(String filepath) throws IOException {
        String input = Files.readString(Paths.get(filepath));
        return Arrays.stream(input.trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
