package day1;

import util.Util;

import java.io.IOException;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1A {

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLinesArray("src/day1/input.txt");

        Supplier<IntStream> supplier = () -> Stream.of(input)
                                                .mapToInt(Integer::valueOf);
        supplier.get().forEach(i ->
                supplier.get().filter(j -> i + j == 2020)
                              .map(j -> i * j)
                              .findFirst()
                              .ifPresent(System.out::println));

        var input3 = Util.readAllLinesArray("src/day1/input.txt");

        for (int i : input3) {
            for (int j : input3) {
                if (j + i == 2020) {
                    //System.out.println(j * i);
                    return;
                }
            }
        }

        /*
        int first = input.mapToInt(Integer::valueOf)
                .findFirst()
                .getAsInt();

        int sum = input2.mapToInt(Integer::valueOf)
                .filter(i -> first + i == 2020)
                .findFirst()
                .getAsInt();
        */

//        System.out.println(first * sum);
    }
}
