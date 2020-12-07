package day7;

import util.Util;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day7 {

    public static void main(String[] args) throws IOException {
        String inputPath = "src/day7/input.txt";
        var input = Util.readAllLinesStringList(inputPath);

//        a(input);
        b(input);
    }

    public static void a(List<String> input) {
        ArrayList<String> found = new ArrayList<>();
        found.add("shiny gold");

        boolean foundNewBag = true;

        while(foundNewBag) {
            foundNewBag = false;

            for (int i = 0; i < input.size(); i++) {
                var split = input.get(i).split(" bags ");
                var colour = split[0];
                var rest = split[1];
                System.out.println(colour);

                for(int j = 0; j < found.size(); j++) {
                    if(rest.contains(found.get(j))) {
                        foundNewBag = true;
                        found.add(colour);
                        input.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }

        System.out.println(found.size()-1);
    }

    public static void b(List<String> input) {
        AtomicInteger bags = new AtomicInteger();
        Stack<String> bagsToProcess = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        bagsToProcess.add("shiny gold");
        nums.add(1);

        while (!bagsToProcess.isEmpty()) {
            String bag = bagsToProcess.pop();
            int num = nums.pop();

            int j = 0;
            while (!input.get(j).startsWith(bag))
                j++;

            String in = input.get(j);
            String[] contents = in.substring(in.indexOf("contain") + 8).replace(".",
                    "").split(", ");
            if (!contents[0].equals("no other bags")) {
                Stream.of(contents).forEach(c -> {
                    var b = c.substring(c.indexOf(" ")).replace("bags", "").trim();
                    var n = Integer.parseInt(c.substring(0, c.indexOf(" ")).trim());
                    bagsToProcess.add(b);
                    nums.add(n * num);
                    bags.addAndGet(n * num);
                });
            }
        }
        System.out.println(bags.get());
    }
}
