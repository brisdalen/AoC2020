package day6;

import util.Util;

import java.io.IOException;
import java.lang.reflect.Array;
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
			// List of the group's sets
			ArrayList<HashSet<Character>> yesAnswersList = new ArrayList<>();
			// Check every passenger's answer
			for(String line : lines) {
				HashSet<Character> yesAnswersSinglePassanger = new HashSet<>();
				var chars = line.toCharArray();
				for(char c : chars) {
					yesAnswersSinglePassanger.add(c);
				}
				yesAnswersList.add(yesAnswersSinglePassanger);
			}

			for(int i = 0; i < yesAnswersList.size()-1; i++) {
				var next = yesAnswersList.get(i+1);
				yesAnswersList.get(0).retainAll(next);
			}
			count[0] += yesAnswersList.get(0).size();
		});

		System.out.println(count[0]);
	}
}
