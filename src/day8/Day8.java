package day8;

import util.Util;

import java.io.IOException;
import java.util.Arrays;

public class Day8 {

    public static void main(String[] args) throws IOException {
        var input = Util.readAllLinesStringList("src/day8/input.txt");
        Instruction[] instructions = new Instruction[input.size()];
        for(int i = 0; i < instructions.length; i++) {
            var split = input.get(i).trim().split("\\s");
            instructions[i] = new Instruction(split);
        }

//        a(instructions);
        b(instructions);
    }

    private static void a(Instruction[] instructions) {
        int accVal = 0;

        for(int i = 0; i < instructions.length; i++) {
            Instruction temp = instructions[i];
            System.out.println(temp.operation + ":" + temp.argument + " - " + temp.executed);
            if(!temp.executed) {
                instructions[i].setExecuted(true);

                if(temp.isNop()) {
                    System.out.println("No operation");
                } else if(temp.isAcc()) {
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
        for(int i = instructions.length-1; i > instructions.length - 11; i--) {
            System.out.println(instructions[i]);
        }

        Instruction[] copy = new Instruction[instructions.length];
        for(int i = 0; i < instructions.length; i++) {
            copy[i] = new Instruction(instructions[i].operation, instructions[i].argument);
        }

        for(int i = 0; i < copy.length; i++) {
            if(copy[i])
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
            return operation.equals("nop");
        }

        public boolean isJump() {
            return operation.equals("jmp");
        }

        public boolean isAcc() {
            return operation.equals("acc");
        }

        @Override
        public String toString() {
            return operation + ":" + argument + " - " + executed;
        }
    }
}
