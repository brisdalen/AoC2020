package day6;

import util.Util;

import java.io.IOException;
import java.util.*;

public class Day6 {

	public static void main(String[] args) throws IOException {
		String inputPath = "src/day6/input.txt";
		var input = Util.readFileAndSplit(inputPath, "\n\n");
		a(input);
		b(input);
	}

	public static void a(String[] input) {
		final int[] count = {0};

		Arrays.asList(input).forEach(s -> {
			HashSet<Character> yesAnswers = new HashSet<>();
			s = s.replace("\n", "");
			var chars = s.toCharArray();
			for(char c : chars) {
				yesAnswers.add(c);
			}
			count[0] += yesAnswers.size();
		});

		System.out.println(count[0]);
	}

	public static void b(String[] input) {
		final int[] count = {0};

		// For each group of passengers
		Arrays.asList(input).forEach(s -> {
			// Remove excess new-lines
			s = s.trim();
			var lines = s.split("\n");
			int groupSize = lines.length;
			System.out.println("Group size: " + groupSize);
			// Map of the group's character frequency
			HashMap<Character, Integer> yesAnswers = new HashMap<>();
			// Check every passenger's answer
			for(String line : lines) {
				var chars = line.toCharArray();
				for(char c : chars) {
					int newVal = yesAnswers.getOrDefault(c, 0) + 1;
					yesAnswers.put(c, newVal);
				}
			}

			int groupCharCount = 0;
			for(var key : yesAnswers.keySet()) {
				int charCount = yesAnswers.get(key);
				if(charCount == groupSize) {
					groupCharCount++;
				}
			}
			count[0] += groupCharCount;
		});

		System.out.println(count[0]);
	}
}
