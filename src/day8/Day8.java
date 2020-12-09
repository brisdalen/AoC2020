package day8;

import util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLinesStringList("src/day8/input.txt");
        Instruction[] instructions = new Instruction[input.size()];
        for (int i = 0; i < instructions.length; i++) {
            var split = input.get(i).trim().split("\\s");
            instructions[i] = new Instruction(split);
        }

//        a(instructions);
        b(input);
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

    private static void b(List<String> input) {
        int accVal = 0;
        boolean finished = true;
        int iterations = 0;

        Instruction[] instructions = new Instruction[input.size()];
        // Fill the array
        for(int i = 0; i < input.size(); i++) {
            var split = input.get(i).trim().split("\\s");
            instructions[i] = new Instruction(split);
        }

        for(int i = 0; i < instructions.length; i++) {
            System.out.println("i iteration: " + ++iterations);

            if (instructions[i].operation.equals("nop")) {
                instructions[i].operation = "jmp";
            } else if (instructions[i].operation.equals("jmp")) {
                instructions[i].operation = "nop";
            }

            for(int j = 0; j < instructions.length; j++) {
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
                    finished = false;
                    if (instructions[j].operation.equals("nop")) {
                        instructions[i].operation = "jmp";
                    } else if (instructions[j].operation.equals("jmp")) {
                        instructions[i].operation = "nop";
                    }
                    reset(instructions);
                    j = instructions.length;
                }
            }
        }
        if (finished) {
            System.out.println(accVal);
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
