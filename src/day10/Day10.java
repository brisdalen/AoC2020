package day10;

import util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {

    Integer[] input;

    static String testString = "28\n" +
            "33\n" +
            "18\n" +
            "42\n" +
            "31\n" +
            "14\n" +
            "46\n" +
            "20\n" +
            "48\n" +
            "47\n" +
            "24\n" +
            "23\n" +
            "49\n" +
            "45\n" +
            "19\n" +
            "38\n" +
            "39\n" +
            "11\n" +
            "1\n" +
            "32\n" +
            "25\n" +
            "35\n" +
            "8\n" +
            "17\n" +
            "7\n" +
            "9\n" +
            "4\n" +
            "2\n" +
            "34\n" +
            "10\n" +
            "3";

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLinesArray("src/day10/input.txt");
        var testInput = Util.readStringToArray(testString);
        new Day10(testInput);
//        new Day10(input);
    }

    public Day10(Integer[] input) {
        this.input = input;
        Arrays.sort(this.input);
//        a();
        b();
    }

    public void a() {
        int ones = 1;
        int threes = 1;

//        System.out.println(input[0]);
        for(int i = 1; i < input.length; i++) {
//            System.out.println(input[i] - input[i-1]);
            if(input[i] - input[i-1] == 1) {
                ones++;
            } else {
                threes++;
            }
        }

        System.out.println("\n\n" + ones);
        System.out.println(threes);
        System.out.println(ones * threes);
    }

    public void b() {
        int amountValid = 0;
        System.out.println(input.length);
        // Try every variation of every adapter sequence
        for(int i = 0; i < input.length; i++) {
            // Remove the first, check if its valid, and move on
            for(int j = 0; j < input.length; j++) {
                ArrayList<Integer> inputList = new ArrayList<>();
                inputList.add(0);
                inputList.addAll(Arrays.asList(input));
                inputList.add(inputList.get(inputList.size()-1) + 3);
                System.out.println(inputList.size());
                boolean success = true;
                // if this is successful, it is still valid
                for(int k = 1; k < inputList.size(); k++) {
                    if(inputList.get(k) - inputList.get(k-1) > 3) {
                        success = false;
                        break;
                    }
                }
                if(success) {
                    amountValid++;
                }
            }
        }
        System.out.println(amountValid);
    }
}
