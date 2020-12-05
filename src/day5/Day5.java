package day5;

import util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class Day5 {

	public static void main(String[] args) throws IOException {
		b(a());
	}

	public static ArrayList<Integer> a() throws IOException {
		var testData = new String[] {"FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"};
		var input = Util.readAllLinesStringList("src/day5/input.txt").toArray(new String[0]);
		var boardingPasses = new ArrayList<Integer>();

		Stream.of(input).forEach(
			s -> {
				Range rows = new Range(127, 0);
				Range cols = new Range(7, 0);

				String[] data = new String[2];
				data[0] = s.substring(0, 7);
				data[1] = s.substring(7);

				for(int i = 0; i < data[0].length(); i++) {
					if(data[0].charAt(i) == 'F') {
						rows.cutTopHalf();
					} else {
						rows.cutBottomHalf();
					}
				}

				for(int i = 0; i < data[1].length(); i++) {
					if(data[1].charAt(i) == 'L') {
						cols.cutTopHalf();
					} else {
						cols.cutBottomHalf();
					}
				}
				boardingPasses.add(rows.high * 8 + cols.high);
			}
		);
		System.out.println(Collections.max(boardingPasses));
		return boardingPasses;
	}

	public static void b(ArrayList<Integer> seats) {
		var sorted = seats;
		Collections.sort(sorted);

		for(int i = 1; i < sorted.size()-1; i++) {
			int prev = sorted.get(i-1);

			if(sorted.get(i) - prev != 1) {
				System.out.println(prev + 1);
			}
		}
	}

	private static class Range {

		public int high;
		public int low;

		public Range(int high, int low) {
			this.high = high;
			this.low = low;
		}

		public void cutTopHalf() {
			high = high - ((high - low) / 2) - 1;
		}

		public void cutBottomHalf() {
			low = low + ((high - low) / 2) + 1;
		}
	}
}
