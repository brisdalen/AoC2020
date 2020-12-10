package day8;

import util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLinesStringList("src/day8/input.txt");
        var testinput = new ArrayList<String>();
        testinput.add("nop +0");
        testinput.add("acc +1");
        testinput.add("jmp +4");
        testinput.add("acc +3");
        testinput.add("jmp -3");
        testinput.add("acc -99");
        testinput.add("acc +1");
        testinput.add("jmp -4");
        testinput.add("acc +6");

        Instruction[] instructions = new Instruction[input.size()];
        for (int i = 0; i < instructions.length; i++) {
            var split = input.get(i).trim().split("\\s");
            instructions[i] = new Instruction(split);
        }

        Instruction[] testInstructions = new Instruction[testinput.size()];
        for (int i = 0; i < testInstructions.length; i++) {
            var split = testinput.get(i).trim().split("\\s");
            testInstructions[i] = new Instruction(split);
        }

//        a(instructions);
        b(testInstructions);
    }

    private static void a(Instruction[] instructions) {
        int accVal = 0;

        for (int i = 0; i < instructions.length; i++) {
            Instruction temp = instructions[i];
            System.out.println(temp.operation + ":" + temp.argument + " - " + temp.executed);
            if (!temp.executed) {
                instructions[i].setExecuted(true);

                if (temp.isNop()) {
                    System.out.println("No operation");
                } else if (temp.isAcc()) {
                    accVal += temp.argument;
                } else {
                    i += temp.argument - 1;
                }
            } else {
                System.out.println(accVal);
                break;
            }
        }
    }

    private static void b(Instruction[] instructions) {
        int accVal = 0;
        boolean foundSequence = true;
        boolean done = false;
        int iterations = 0;
        System.out.println(instructions.length);
        // Iterate through all input, switch the nop and jmp operation when encountered
        for(int i = 0; i < instructions.length; i++) {
            if (done) {
                break;
            } else {
                System.out.println("i iteration: " + ++iterations);

                if (instructions[i].operation.equals("nop")) {
                    instructions[i].operation = "jmp";
//                System.out.println(instructions[i]);
                } else if (instructions[i].operation.equals("jmp")) {
                    instructions[i].operation = "nop";
//                System.out.println(instructions[i]);
                }
                // Iterate through all the input, now with the switch nop or jmp operation
                for (int j = 0; j < instructions.length; j++) {
                    System.out.println(j + ":" + instructions[j] + " - " + accVal);
//                    System.out.println("Iteration: " + ++iterations + " @ " + j);
                    if (!instructions[j].executed) {
//                        System.out.println(instructions[j].toString());
                        instructions[j].setExecuted(true);
                        if (instructions[j].isJump()) {
                            j += instructions[j].argument - 1;
                        } else if (instructions[j].isAcc()) {
                            accVal += instructions[j].argument;
                        }
                    } else {
                        System.out.println("else");
                        accVal = 0;
                        foundSequence = false;
                        if (instructions[i].operation.equals("nop")) {
                            instructions[i].operation = "jmp";
                        } else if (instructions[i].operation.equals("jmp")) {
                            instructions[i].operation = "nop";
                        }
                        reset(instructions);
                        j = instructions.length + 1;
                    }
                }
                if (foundSequence) {
                    System.out.println(accVal);
                    done = true;
                }
            }
        }
    }

    private static void reset(Instruction[] instructions) {
        for(var i : instructions) {
            i.executed = false;
        }
    }

    public static class Instruction {

        String operation;
        int argument;
        public boolean executed = false;

        Instruction(String[] instruction) {
            this.operation = instruction[0];
            this.argument = Integer.parseInt(instruction[1]);
        }

        Instruction(String operation, int argument) {
            this.operation = operation;
            this.argument = argument;
        }

        public void setExecuted(boolean executed) {
            this.executed = executed;
        }

        public boolean isNop() {
            return operation.startsWith("nop");
        }

        public boolean isJump() {
            return operation.startsWith("jmp");
        }

        public boolean isAcc() {
            return operation.startsWith("acc");
        }

        @Override
        public String toString() {
            return operation + ":" + argument + " - " + executed;
        }
    }
}
