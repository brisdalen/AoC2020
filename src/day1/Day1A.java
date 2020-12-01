package day1;

import util.Util;

import java.io.IOException;

public class Day1A {

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLines("src/day1/input.txt");
        var input2 = Util.readAllLines("src/day1/input.txt");
        var input3 = Util.readAllLinesArray("src/day1/input.txt");


        for (int i : input3) {
            for (int j : input3) {
                if (j + i == 2020) {
                    System.out.println(j * i);
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
