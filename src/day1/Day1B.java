package day1;

import util.Util;

import java.io.IOException;

public class Day1B {

    public static void main(String[] args) throws IOException {
        var input = Util.linesToStringStream(Util.getDayFilePath(1));
        var input3 = Util.readAllLinesArray(Util.getDayFilePath(1));


        for (int i : input3) {
            for (int j : input3) {
                for (int k : input3) {
                    if (j + i + k == 2020) {
                        System.out.println(j * i * k);
                        return;
                    }
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
