package day3;

import util.Util;

import java.io.IOException;
import java.util.List;

public class Day3 {

    static List<String> input2;

    public static void main(String[] args) throws IOException {

        var input = Util.linesToStringStream("src/day3/input.txt");
        input.findFirst()
                .ifPresent(x -> System.out.println(x.length()));

        input2 = Util.readAllLinesStringList("src/day3/input.txt");
        System.out.println(input2.size());
        int config1 = testSlope(1, 1);
        int config2 = testSlope(3 ,1);
        int config3 = testSlope(5, 1);
        int config4 = testSlope(7, 1);
        int config5 = testSlope(1, 2);

        System.out.println(config1 * config2 * config3 * config4 * config5);
    }

    private static int testSlope(int right, int down) {
        // Every time we get to the right edge, start at the left side again
        int position = right;
        int treesHit = 0;
        for(int i = down; i < Day3.input2.size(); i += down) {
            var line = input2.get(i);
            if(line.charAt(position) == '#') {
                treesHit++;
            }
            position = (position + right) % line.length();
            //System.out.println("new pos: " + position);
        }
        System.out.println(treesHit);
        return treesHit;
    }
}
