package day11;

import util.Util;

import java.io.IOException;

public class Day11 {

    public static void main(String[] args) throws IOException {
//        var input =
//                "L.LL.LL.LL" +
//                "LLLLLLL.LL" +
//                "L.L.L..L.." +
//                "LLLL.LL.LL" +
//                "L.LL.LL.LL" +
//                "L.LLLLL.LL" +
//                "..L.L....." +
//                "LLLLLLLLLL" +
//                "L.LLLLLL.L" +
//                "L.LLLLL.LL";
        var input = Util.readAllLinesStringList("src/day11/input.txt");
//        var lineLength = 10;
        var lineLength = input.get(0).length();

//        var charArray = input.toCharArray();
        var charArray = Util.allLinesToString("src/day11/input.txt").toCharArray();

        a(charArray, lineLength);
        b(lineLength, charArray);

    }

    private static void a(char[] charArray, int lineLength) {
        boolean changed = true;
        int occupied = 0;

        while(changed) {
            changed = updateSeats(charArray, lineLength);
        }

        for(char c : charArray) {
            if(c == '#') {
                occupied++;
            }
        }

        System.out.println(occupied);
    }

    private static void b(char[] charArray, int lineLength) {
        boolean changed = true;
        int occupied = 0;

        while(changed) {
            changed = updateSeatsRaycast(charArray, lineLength);
        }

        for(char c : charArray) {
            if(c == '#') {
                occupied++;
            }
        }

        System.out.println(occupied);
    }

    public static boolean updateSeats(char[] seats, int linelength) {

        boolean changed = false;
        int[] adjacentCounts = new int[seats.length];

        for(int i = 0; i < seats.length; i++) {
            // left-most row, except bottom row
            if(i % linelength == 0 && i + linelength < seats.length) {
                if (seats[i + 1] == '#') // checks common for all
                    adjacentCounts[i]++;
                if (seats[i + linelength] == '#')
                    adjacentCounts[i]++;
                if (seats[i + linelength + 1] == '#')
                if (seats[i + linelength + 1] == '#')
                    adjacentCounts[i]++;
                if(i != 0) { // Checks for all except the first cell
                    if(seats[i - linelength] == '#')
                        adjacentCounts[i]++;
                    if(seats[i - linelength + 1] == '#')
                        adjacentCounts[i]++;
                }
            }

            // right-most row, except bottom row
            if(i % linelength == linelength - 1 && i + linelength < seats.length) {
                if (seats[i - 1] == '#') // checks common for all
                    adjacentCounts[i]++;
                if (seats[i + linelength] == '#')
                    adjacentCounts[i]++;
                if (seats[i + linelength - 1] == '#')
                    adjacentCounts[i]++;
                if(i != linelength - 1) { // Checks for all except the first cell
                    if(seats[i - linelength] == '#')
                        adjacentCounts[i]++;
                    if(seats[i - linelength - 1] == '#')
                        adjacentCounts[i]++;
                }
            }

            // rows 1 till n-1, except bottom row
            if(i % linelength >= 1 && i % linelength <= linelength - 2 && i + linelength < seats.length) {
                if (seats[i - 1] == '#') // checks common for all
                    adjacentCounts[i]++;
                if (seats[i + 1] == '#')
                    adjacentCounts[i]++;
                if (seats[i + linelength - 1] == '#')
                    adjacentCounts[i]++;
                if (seats[i + linelength] == '#')
                    adjacentCounts[i]++;
                if (seats[i + linelength + 1] == '#')
                    adjacentCounts[i]++;
                if(i >= linelength) { // Checks for all except the top-most row
                    if (seats[i - linelength - 1] == '#')
                        adjacentCounts[i]++;
                    if (seats[i - linelength] == '#')
                        adjacentCounts[i]++;
                    if (seats[i - linelength + 1] == '#')
                        adjacentCounts[i]++;
                }
            }

            // bottom row, except last cell
            if(i >= seats.length - linelength && i != seats.length - 1) {
                if (seats[i - linelength] == '#') // Checks common for all
                    adjacentCounts[i]++;
                if (seats[i - linelength + 1] == '#')
                    adjacentCounts[i]++;
                if (seats[i + 1] == '#')
                    adjacentCounts[i]++;
                if(i % linelength > 0) { // Checks for all except the left-most cell
                    if (seats[i - linelength - 1] == '#')
                        adjacentCounts[i]++;
                    if (seats[i - 1] == '#')
                        adjacentCounts[i]++;
                }
            }

            // Last cell
            if(i == seats.length - 1) {
                if (seats[i - 1] == '#')
                    adjacentCounts[i]++;
                if (seats[i - linelength - 1] == '#')
                    adjacentCounts[i]++;
                if (seats[i - linelength] == '#')
                    adjacentCounts[i]++;
            }
        }

        for(int i = 0; i < seats.length; i++) {
            if(seats[i] == 'L') {
                if(adjacentCounts[i] == 0) {
                    seats[i] = '#';
                    changed = true;
                }
            } else if(seats[i] == '#') {
                if(adjacentCounts[i] >= 4) {
                    seats[i] = 'L';
                    changed = true;
                }
            } else {
                seats[i] = '.';
            }
        }
/*
        for(int i = 0; i < linelength; i++) {
            for(int j = 0; j < linelength; j++) {
                System.out.print(seats[i * linelength + j]);
            }
            System.out.println();
        }*/

        return changed;
    }

    public static boolean updateSeatsRaycast(char[] seats, int linelength) {

    }
}
