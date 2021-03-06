package day6;

import util.Util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {

	public static void main(String[] args) throws IOException {
		String inputPath = "src/day6/input.txt";
		var input = Util.readFileAndSplit(inputPath, "\n\n");
		a(input);
		b(input);
	}

	public static void a(String[] input) {
		final int[] count = {0};

		HashSet<Character> yesAnswers = new HashSet<>();
		Stream.of(input)
				.peek(hs -> yesAnswers.clear())
				.map(s -> s.replace("\n", ""))
				.forEach(s -> {
					s.chars().forEach(i -> yesAnswers.add((char) i));
					count[0] += yesAnswers.size();
				});

		System.out.println(count[0]);
	}

	public static void b(String[] input) {
		final int[] count = {0, 0};

		HashSet<Character> yesAnswers = new HashSet<>();
		HashSet<Character> yesAnswersPrevious = new HashSet<>();
		Stream.of(input)
				.peek(hs -> yesAnswers.clear())
				.map(s -> s.trim().replace("\n", ""))
				.forEach(s -> {
					s.chars().forEach(i -> yesAnswers.add((char) i));
					if(yesAnswersPrevious.size() > 0) {
						yesAnswers.retainAll(yesAnswersPrevious);
						yesAnswersPrevious.clear();
					}
					yesAnswersPrevious.addAll(yesAnswers);
				});
		count[1] += yesAnswersPrevious.size();
		System.out.println("stream attempt: " + count[1]);


		// For each group of passengers
		Arrays.asList(input).forEach(s -> {
			// Remove excess new-lines
			s = s.trim();
			var lines = s.split("\n");
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
