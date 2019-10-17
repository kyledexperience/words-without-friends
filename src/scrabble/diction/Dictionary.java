package scrabble.diction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Dictionary {

	private static Path filePath = Paths.get("dictionary.txt");

	public static List<String> readFile() throws IOException {

		List<String> lines = Files.readAllLines(filePath);

		return lines;

	}

}
