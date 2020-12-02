package day2;

import util.Util;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {

    /*
            Pattern policy = Pattern.compile("[d+]-[d+] [a-z]");
        Supplier<Stream> supplier = () -> Stream.of(input);
     */

    public static void main(String[] args) throws IOException {
        var input = Util.linesToStringStream("src/day2/input.txt");

        //a(input);
        b(input);
    }

    private static void a(Stream<String> input) {
        AtomicInteger amountValid = new AtomicInteger();

        input.forEach(s -> {
            String[] parts = s.split(":");
            var minMax = parts[0].split("-");
            int min = Integer.parseInt(minMax[0]);
            int max = Integer.parseInt(minMax[1].split("\\s")[0]);
            char letter = minMax[1].split("\\s")[1].charAt(0);

            if(parts[1].contains(Character.toString(letter))) {
                int matchingLetters = 0;
                for(char c : parts[1].toCharArray()) {
                    if(c == letter) {
                        matchingLetters++;
                    }
                }
                if(matchingLetters >= min && matchingLetters <= max) {
                    amountValid.getAndIncrement();
                }
            }
        });

        System.out.println(amountValid);
    }

    private static void b(Stream<String> input) {
        AtomicInteger amountValid = new AtomicInteger();

        input.forEach(s -> {
            String[] parts = s.split(":");
            var minMax = parts[0].split("-");
            int pos1 = Integer.parseInt(minMax[0]);
            int pos2 = Integer.parseInt(minMax[1].split("\\s")[0]);
            char letter = minMax[1].split("\\s")[1].charAt(0);

            if(parts[1].contains(Character.toString(letter))) {
                if(parts[1].charAt(pos1) == letter ^ parts[1].charAt(pos2) == letter) {
                    amountValid.getAndIncrement();
                }
            }
        });

        System.out.println(amountValid);
    }
}
