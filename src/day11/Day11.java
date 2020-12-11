package day11;

import util.Util;

import java.io.IOException;

public class Day11 {

    public static void main(String[] args) throws IOException {
        var input = "L.LL.LL.LL" +
                "LLLLLLL.LL" +
                "L.L.L..L.." +
                "LLLL.LL.LL" +
                "L.LL.LL.LL" +
                "L.LLLLL.LL" +
                "..L.L....." +
                "LLLLLLLLLL" +
                "L.LLLLLL.L" +
                "L.LLLLL.LL";
//        var input = Util.readAllLinesStringList("src/day11/input.txt");
        var lineLength = 10;
//        var lineLength = input.get(0).length();

        var charArray = input.toCharArray();
//        var charArray = Util.allLinesToString("src/day11/input.txt").toCharArray();

        boolean changed = true;
        int unoccupied = 0;

        while(changed) {
            changed = updateSeats(charArray, lineLength);
        }

        for(char c : charArray) {
            if(c == 'L') {
                unoccupied++;
            }
        }

        System.out.println(unoccupied);
    }

    public static boolean updateSeats(char[] seats, int linelength) {

        boolean changed = false;

        for(int i = 0; i < seats.length; i++) {
            int[] adjacentCounts = new int[seats.length];
            if(i > linelength && i < seats.length - linelength - 1) { // Everything between first and last row
                if(seats[i - linelength - 1] == '#')
                    adjacentCounts[i]++;
                if(seats[i - linelength] == '#')
                    adjacentCounts[i]++;
                if(seats[i - linelength + 1] == '#')
                    adjacentCounts[i]++;

                if(seats[i-1] == '#') // adjacent left and right
                    adjacentCounts[i]++;
                if(seats[i+1] == '#')
                    adjacentCounts[i]++;

                if(seats[i + linelength - 1] == '#')
                    adjacentCounts[i]++;
                if(seats[i + linelength] == '#')
                    adjacentCounts[i]++;
                if(seats[i + linelength + 1] == '#')
                    adjacentCounts[i]++;

            } else if(i % linelength < linelength) {
                if(i > 0) { // Everything but First row
                    if(seats[i-1] == '#')
                        adjacentCounts[i]++;
                    if(seats[i + linelength - 1] == '#')
                        adjacentCounts[i]++;
                }
                if(seats[i+1] == '#')
                    adjacentCounts[i]++;

                if(seats[i + linelength] == '#')
                    adjacentCounts[i]++;
                if(seats[i + linelength + 1] == '#')
                    adjacentCounts[i]++;
            }
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
            }
        }

        return changed;
    }
}
