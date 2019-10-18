package scrabble;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import scrabble.diction.Dictionary;

public class Scrabble {

	static List<String> wordList = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<Character, Integer> valueMap = new HashMap<>();

		valueMap.put('a', 1);
		valueMap.put('e', 1);
		valueMap.put('i', 1);
		valueMap.put('o', 1);
		valueMap.put('u', 1);
		valueMap.put('r', 1);
		valueMap.put('s', 1);
		valueMap.put('t', 1);
		valueMap.put('n', 1);
		valueMap.put('l', 1);
		valueMap.put('d', 2);
		valueMap.put('g', 2);
		valueMap.put('b', 3);
		valueMap.put('c', 3);
		valueMap.put('m', 3);
		valueMap.put('p', 3);
		valueMap.put('f', 4);
		valueMap.put('h', 4);
		valueMap.put('v', 4);
		valueMap.put('w', 4);
		valueMap.put('y', 4);
		valueMap.put('k', 5);
		valueMap.put('j', 8);
		valueMap.put('x', 8);
		valueMap.put('q', 10);
		valueMap.put('z', 10);

		Scanner scnr = new Scanner(System.in);
		boolean cont = true;

		while (cont) {

			System.out.println("Enter your letters: ");
			String word = scnr.nextLine();
			List<String> list = new ArrayList<>();

			// add word(s) to list
			if (word.contains("*")) {
				list = getWords(word);
			} else {
				list.add(word);
			}

			for (String s : list) {

				for (int i = 0; i < s.length(); i++) {

					s = s.substring(0, s.length() - i);
					
					System.out.println(s + " try:" + i);
					permute(s, "");

				}

			}

			for (String w : wordList) {
				System.out.println(w + " " + score(w, valueMap));
			}

			System.out.println("Done!\n");
			System.out.println("Another word?");
			String response = scnr.nextLine();
			if (!response.toLowerCase().startsWith("y")) {
				cont = false;
				System.out.println("Later on!");
			}

		}

		scnr.close();
	}

	public static int score(String word, HashMap<Character, Integer> map) {

		int sum = 0;
		for (int i = 0; i < word.length(); i++) {

			sum = sum + map.get(word.charAt(i));

		}

		return sum;

	}

	public static List<String> getWords(String word) {

		List<String> list = new ArrayList<>();

		int len = word.length();
		char c = 'a';

		if (word.contains("*")) {
			word = word.replaceAll("[^[a-zA-Z]]", "");

			list.add(word);

			while (c <= 'z') {

				list.add(word.concat(Character.toString(c)));
				c++;
				word = word.substring(0, len - 1);
			}
		}

		return list;

	}

	public static void permute(String word, String ans) {

		if (word.length() == 0) {
			// System.out.println(ans);

			try {
				if (Dictionary.readFile().contains(ans.toUpperCase())) {

					// System.out.println(ans);
					if (!wordList.contains(ans)) {
						wordList.add(ans);
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

			return;
		}

		boolean[] alpha = new boolean[26];

		for (int i = 0; i < word.length(); i++) {

			char c = word.charAt(i);

			String remain = word.substring(0, i) + word.substring(i + 1);

			if (alpha[c - 'a'] == false) {
				permute(remain, ans + c);
				alpha[c - 'a'] = true;
			}

		}

	}

}
