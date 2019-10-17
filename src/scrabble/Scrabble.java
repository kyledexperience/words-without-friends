package scrabble;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scrabble.diction.Dictionary;

public class Scrabble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scnr = new Scanner(System.in);

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
			
			while(s.length()>0) {

			int i = s.length();
			
			permute(s, "");
			
			}

		}
		
		System.out.println("Done!");

		scnr.close();
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
			//System.out.println(ans);
			
			try {
				if(Dictionary.readFile().contains(ans.toUpperCase())) {
					
					System.out.println(ans);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};

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
