import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int visibleTrees = 0;
        int highestScenicScore = 0;

        Scanner sc = new Scanner(new File("Puzzle8.txt"));
        String firstLine = sc.nextLine();
        Path path = Paths.get("Puzzle8.txt");
        int lineCounter = (int) Files.lines(path).count();
        int lineLength = firstLine.length();

        int[][] TreeMap = new int[lineCounter][lineLength];
        boolean[][] isVisible = new boolean[lineCounter][lineLength];
        char[] inputString = firstLine.toCharArray();

        for (int i = 0; i < inputString.length; i++) {
            int x = Character.getNumericValue(inputString[i]);
            TreeMap[0][i] = x;
        }

        //FILL FIRST LINE
        for (int i = 1; i < lineCounter; i++) {
            char[] input = sc.nextLine().toCharArray();
            for (int j = 0; j < input.length; j++) {
                int x = Character.getNumericValue(input[j]);
                TreeMap[i][j] = x;
            }
        }

        //FILL OTHER LINES
        for (int i = 0; i < lineCounter; i++) {
            isVisible[i][0] = true;
            isVisible[i][lineLength - 1] = true;
        }
        for (int i = 0; i < lineLength; i++) {
            isVisible[0][i] = true;
            isVisible[lineCounter - 1][i] = true;
        }

        //FILL BOOLEAN ARRAY
        for (int i = 1; i < lineCounter - 1; i++) {
            for (int j = 1; j < lineLength - 1; j++) {
                boolean[] isVisibleNow = {true, true, true, true};

                //CHECK NORTH
                for (int k = 1; i - k >= 0; k++) {
                    if (TreeMap[i][j] <= TreeMap[i - k][j]) {
                        isVisibleNow[0] = false;
                        break;
                    }
                }
                //CHECK SOUTH
                for (int k = 1; i + k < lineCounter; k++) {
                    if (TreeMap[i][j] <= TreeMap[i + k][j]) {
                        isVisibleNow[1] = false;
                        break;
                    }
                }
                //CHECK EAST
                for (int k = 1; j + k < lineLength; k++) {
                    if (TreeMap[i][j] <= TreeMap[i][j + k]) {
                        isVisibleNow[2] = false;
                        break;
                    }
                }
                //CHECK WEST
                for (int k = 1; j - k >= 0; k++) {
                    if (TreeMap[i][j] <= TreeMap[i][j - k]) {
                        isVisibleNow[3] = false;
                        break;
                    }
                }

                isVisible[i][j] = isVisibleNow[0] || isVisibleNow[1] || isVisibleNow[2] || isVisibleNow[3];
            }
        }

        //CHECK VISIBLE TREES ON THE MAP
        for (int i = 0; i < lineCounter; i++) {
            for (int j = 0; j < lineLength; j++) {
                if (isVisible[i][j]) {
                    visibleTrees += 1;
                }
            }
        }
        System.out.println(visibleTrees);


        //CHECK SCENIC SCORE
        for (int i = 0; i < lineCounter; i++) {
            for (int j = 0; j < lineLength; j++) {
                int currentTree = TreeMap[i][j];
                int north = 0;
                int south = 0;
                int west = 0;
                int east = 0;

                //CHECK NORTH
                for (int k = 1; i - k >= 0; k++) {
                    if(currentTree > TreeMap[i - k][j]){
                        north ++;
                    } else {
                        north ++;
                        break;
                    }
                }
                //CHECK SOUTH
                for (int k = 1; i + k < lineCounter; k++) {
                    if (currentTree > TreeMap[i + k][j]) {
                        south ++;
                    } else {
                        south ++;
                        break;
                    }
                }
                //CHECK EAST
                for (int k = 1; j + k < lineLength; k++) {
                    if (currentTree > TreeMap[i][j + k]) {
                        east ++;
                    } else {
                        east ++;
                        break;
                    }
                }
                //CHECK WEST
                for (int k = 1; j - k >= 0; k++) {
                    if (currentTree > TreeMap[i][j - k]) {
                        west ++;
                    } else {
                        west ++;
                        break;
                    }
                }
                int currentScenicScore = north * south * west * east;
                if(currentScenicScore > highestScenicScore){
                    highestScenicScore = currentScenicScore;
                }

            }
        }

        System.out.println(highestScenicScore);

    }
}

