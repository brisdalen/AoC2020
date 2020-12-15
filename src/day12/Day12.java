package day12;

import util.Util;

import java.io.IOException;
import java.util.List;

public class Day12 {

    public static void main(String[] args) throws IOException {
        var inputFilePath = Util.getDayFilePath(12);
        var input = Util.readAllLinesStringList(inputFilePath);

        a(input);
        b(input);
    }

    public static void a(List<String> inputList) {

        String facing = "E";
        int positionN = 0;
        int positionE = 0;

        for(String s : inputList) {
            String command = s.substring(0, 1);
            int argument = Integer.parseInt(s.substring(1));

            switch (command) {

                case "F":
                    switch (facing) {
                        case "E":
                            positionE += argument;
                            break;
                        case "N":
                            positionN += argument;
                            break;
                        case "W":
                            positionE -= argument;
                            break;
                        case "S":
                            positionN -= argument;
                            break;
                        default:
                            break;
                    }
                    break;

                default:
                    if(command.equals("E"))
                        positionE += argument;
                    if(command.equals("N"))
                        positionN += argument;
                    if(command.equals("W"))
                        positionE -= argument;
                    if(command.equals("S"))
                        positionN -= argument;
                    break;
            }

            if(command.equals("L")) {
                switch (facing) { // TODO: own utility method

                    case "E":
                        if(argument == 90)
                            facing = "N";
                        if(argument == 180)
                            facing = "W";
                        if(argument == 270)
                            facing = "S";
                        break;

                    case "N":
                        if(argument == 90)
                            facing = "W";
                        if(argument == 180)
                            facing = "S";
                        if(argument == 270)
                            facing = "E";
                        break;

                    case "W":
                        if(argument == 90)
                            facing = "S";
                        if(argument == 180)
                            facing = "E";
                        if(argument == 270)
                            facing = "N";
                        break;

                    case "S":
                        if(argument == 90)
                            facing = "E";
                        if(argument == 180)
                            facing = "N";
                        if(argument == 270)
                            facing = "W";
                        break;

                    default:
                        break;
                }
            }

            if(command.equals("R")) {
                switch (facing) { // TODO: own utility method

                    case "E":
                        if(argument == 90)
                            facing = "S";
                        if(argument == 180)
                            facing = "W";
                        if(argument == 270)
                            facing = "N";
                        break;

                    case "N":
                        if(argument == 90)
                            facing = "E";
                        if(argument == 180)
                            facing = "S";
                        if(argument == 270)
                            facing = "W";
                        break;

                    case "W":
                        if(argument == 90)
                            facing = "N";
                        if(argument == 180)
                            facing = "E";
                        if(argument == 270)
                            facing = "S";
                        break;

                    case "S":
                        if(argument == 90)
                            facing = "W";
                        if(argument == 180)
                            facing = "N";
                        if(argument == 270)
                            facing = "E";
                        break;

                    default:
                        break;
                }
            }
        }

        System.out.println(Math.abs(positionN) + Math.abs(positionE));
    }

    public static void b(List<String> inputList) {
        int shipPositionE = 0;
        int shipPositionN = 0;

        int waypointE = 10;
        int waypointN = 1;

        for(String s : inputList) {
            String command = s.substring(0, 1);
            int argument = Integer.parseInt(s.substring(1));

            switch (command) {

                case "F":
                    shipPositionE += waypointE * argument;
                    shipPositionN += waypointN * argument;
                    break;

                default:
                    if(command.equals("E"))
                        waypointE += argument;
                    if(command.equals("N"))
                        waypointN += argument;
                    if(command.equals("W"))
                        waypointE -= argument;
                    if(command.equals("S"))
                        waypointN -= argument;
                    break;
            }

            if(command.equals("L")) {
                if(argument == 90) {
                    int temp = waypointE;
                    waypointE = waypointN * -1;
                    waypointN = temp;
                } else if(argument == 180) {
                    waypointE *= -1;
                    waypointN *= -1;
                } else {
                    int temp = waypointE;
                    waypointE = waypointN;
                    waypointN = temp * -1;
                }
            }

            if(command.equals("R")) {
                if(argument == 90) {
                    int temp = waypointE;
                    waypointE = waypointN;
                    waypointN = temp * -1;
                } else if(argument == 180) {
                    waypointE *= -1;
                    waypointN *= -1;
                } else {
                    int temp = waypointE;
                    waypointE = waypointN * -1;
                    waypointN = temp;
                }
            }
        }

        System.out.println(Math.abs(shipPositionE) + Math.abs(shipPositionN));
    }
}
