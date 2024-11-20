import java.util.HashMap;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort!!!")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Replace the following statement with your code
		str1 = preProcess(removeSpace(str1));
		str2 = preProcess(removeSpace(str2));
		if (str1.length() != str2.length()) {
			return false;
		}
		for (int i = 0; i < str1.length(); i++) {
			char c = str1.charAt(i);
			int str2CharIndex = str2.indexOf(c);
			if (str2CharIndex == -1) {
				return false;
			}
			str2 = removeChar(str2CharIndex, str2);
		}
		return true;

		// this question is suited to dictionary and hashmaps...
		// return isAnagramDic(str1, str2);
	}

	public static boolean isAnagramDic(String str1, String str2) {
		var letters1 = strToCountMap(str1.toLowerCase());
		var letters2 = strToCountMap(str2.toLowerCase());
		// now compare the dictionaries
		if (letters1.size() != letters2.size()) {
			return false;
		}
		for (var character : letters1.keySet()) {
			if (!letters2.containsKey(character) || letters2.get(character) != letters1.get(character)) {
				// Either a character in str1 not in str2 or the char occurences are not equal.
				return false;
			}
		}
		return true;

	}

	public static HashMap<Character, Integer> strToCountMap(String str) {
		char[] strArray = str.toCharArray();
		String validChars = "abcdefghijklmnopqrstuvwxys";
		var letters = new HashMap<Character, Integer>();
		for (char c : strArray) {
			if (validChars.indexOf(c) != -1 && letters.containsKey(c)) {
				int charCount = letters.get(c);
				letters.put(c, charCount + 1);
			} else {
				letters.put(c, 1);
			}
		}
		return letters;
	}

	public static String removeSpace(String str) {
		String space = " ";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (space.indexOf(c) != -1) {
				str = removeChar(i, str);
				i--;
			}
		}
		return str;
	}

	public static String removeChar(int index, String str) {
		String prefix = str.substring(0, index);
		String suffix = "";
		if (str.length() > index + 1) {
			suffix = str.substring(index + 1);
		}
		return prefix + suffix;
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// Replace the following statement with your code
		String abc = "abcdefghijklmnopqrstuvwxys ";
		str = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (abc.indexOf(c) == -1) {
				str = removeChar(i, str);
				i--;
			}
		}
		return str;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
	public static String randomAnagram(String str) {
		// Replace the following statement with your code
		String randomStr = "";
		int length = str.length();
		for (int i = 0; i < length; i++) {
			int randomIndex = (int) (Math.random() * str.length());
			randomStr += str.charAt(randomIndex);
			str = removeChar(randomIndex, str);
		}

		return randomStr;
	}
}
