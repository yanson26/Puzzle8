import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int visibleTrees = 0;

        Scanner sc = new Scanner(new File("Puzzle8.txt"));
        String firstLine = sc.nextLine();
        Path path = Paths.get("Puzzle8.txt");
        int lineCounter = (int)Files.lines(path).count();
        int lineLength = firstLine.length();

        int[][] TreeMap = new int[lineCounter][lineLength];
        boolean[][] isVisible = new boolean[lineCounter][lineLength];
        char[] inputString = firstLine.toCharArray();

        for (int i = 0; i < inputString.length; i++) {
            int x = Character.getNumericValue(inputString[i]);
            TreeMap[0][i] = x;
        }

        for (int i = 1; i < lineCounter; i++) {
            char[] input = sc.nextLine().toCharArray();
            for (int j = 0; j < input.length; j++) {
                int x = Character.getNumericValue(input[j]);
                TreeMap[i][j] = x;
            }
        }



    }

}
