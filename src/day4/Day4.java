package day4;

import util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

	public static void main(String[] args) throws IOException {
		String[] input = Util.readFileAndSplit("src/day4/input.txt", "\n\n");
		/*
		String test = "eyr:1972 cid:100\n" +
				"hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
				"\n" +
				"iyr:2019\n" +
				"hcl:#602927 eyr:1967 hgt:170cm\n" +
				"ecl:grn pid:012533040 byr:1946\n" +
				"\n" +
				"hcl:dab227 iyr:2012\n" +
				"ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" +
				"\n" +
				"hgt:59cm ecl:zzz\n" +
				"eyr:2038 hcl:74454a iyr:2023\n" +
				"pid:3556412378 byr:2007";
		String[] input = test.split("\n\n");
		 */

		//a(input);
		b(input);
	}

	private static void a(String[] input) {
		int amountValid = 0;

		for(var s : input) {
			var passportCategorized = s.replace("\n", " ");
			if(allRequiredPresent(passportCategorized)) {
				amountValid++;
			}
		}
		System.out.println(amountValid);
	}

	private static void b(String[] input) {
		int amountValid = 0;

		for(var s : input) {
			var passportCategorized = s.replace("\n", " ");
			if(allRequiredPresent(passportCategorized)) {
				boolean valid = true;

				String[] toCheck = passportCategorized.split(" ");
				Arrays.sort(toCheck);

				for(var s2 : toCheck) {
					if(s2.startsWith("byr")) {
						int value = Integer.parseInt(s2.substring(4));
						if(valid) {
							valid = isValidBirthYear(value);
						}
					} else if(s2.startsWith("ecl")) {
						String eyeColour = s2.substring(4);
						if(valid) {
							valid = isValidEyeColour(eyeColour);
						}
					} else if(s2.startsWith("eyr")) {
						int expirationYear = Integer.parseInt(s2.substring(4));
						if(valid) {
							valid = isValidExpYear(expirationYear);
						}
					} else if(s2.startsWith("hcl")) {
						String colourCode = s2.substring(4);
						if(valid) {
							valid = isValidHairColour(colourCode);
						}
					} else if(s2.startsWith("hgt")) {
						boolean isCm = s2.endsWith("cm");
						int height = Integer.parseInt(s2.substring(4, s2.length()-2));
						if(valid) {
							valid = isValidHeight(height, isCm);
						}
					} else if(s2.startsWith("iyr")) {
						int value = Integer.parseInt(s2.substring(4));
						if(valid) {
							valid = isValidIssueYear(value);
						}
					} else if(s2.startsWith("pid")) {
						String pid = s2.substring(4);
						if(valid) {
							valid = isValidPID(pid);
						}
					}
				}

				if(valid) {
					amountValid++;
				}
			}
		}
		System.out.println("\n" + amountValid);
	}

	private static boolean allRequiredPresent(String in) {
		return in.contains("byr") &&
				in.contains("iyr") &&
				in.contains("eyr") &&
				in.contains("hgt") &&
				in.contains("hcl") &&
				in.contains("ecl") &&
				in.contains("pid");
	}

	private static boolean isValidBirthYear(int birthYear) {
		return birthYear >= 1920 && birthYear <= 2002;
	}

	private static boolean isValidIssueYear(int issueYear) {
		return issueYear >= 2010 && issueYear <= 2020;
	}

	private static boolean isValidExpYear(int expYear) {
		return expYear >= 2020 && expYear <= 2030;
	}

	private static boolean isValidHeight(int height, boolean cm) {
		if(cm) {
			return height >= 150 && height <= 193;
		} else {
			return height >= 59 && height <= 76;
		}
	}

	private static boolean isValidHairColour(String code) {
		Pattern pattern = Pattern.compile("^#[0-9a-f]{6}$");
		Matcher matcher = pattern.matcher(code);
		return matcher.find();
	}

	private static boolean isValidEyeColour(String eyeColour) {
		Pattern pattern = Pattern.compile("^[a-z]{3}$");
		Matcher matcher = pattern.matcher(eyeColour);
		String[] validColours = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
		return Arrays.asList(validColours).contains(eyeColour) && matcher.find();
	}

	private static boolean isValidPID(String pid) {
		Pattern pattern = Pattern.compile("^[0-9]{9}$");
		Matcher matcher = pattern.matcher(pid);
		return matcher.find();
	}
}
