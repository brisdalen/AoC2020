package day9;

import util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalLong;

public class Day9 {

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLinesArrayLong("src/day9/input.txt");
        a(input);
    }

    public static void a(Long[] input) {

        long invalidNumber = 0;

        for(int i = 25; i < input.length; i++) {
            var preamble = Arrays.copyOfRange(input, i-25, i);
            boolean checksOut = false;
            long toFind = input[i];

            for(int j = 0; j < preamble.length; j++) {
                for(int k = 0; k < preamble.length; k++) {
                    if(preamble[j] + preamble[k] == toFind) {
                        checksOut = true;
                        break;
                    }
                }
                if(checksOut) {
                    break;
                }
            }
            if(!checksOut) {
                System.out.println(toFind);
                invalidNumber = toFind;
            }
        }

        for(int i = 0; i < input.length; i++) {
            ArrayList<Long> range = new ArrayList<>();
            long lowerIndex = i;
            long sum = 0;

            range.add(input[i]);

            for(int j = i; j < input.length; j++) {
                range.add(input[j]);

                sum += input[j];

                if(sum == invalidNumber) {
                    var max = range.stream().mapToLong(l -> l).max().getAsLong();
                    var min = range.stream().mapToLong(l -> l).min().getAsLong();
                    System.out.println(max + min);
                    return;
                }
            }
        }
    }
}
