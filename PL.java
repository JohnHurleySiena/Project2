/*
 * 1: ~Z,a,b,c,d
2: ~a
3: ~b
4: ~c
5: ~d
 
1-2, produces 6: ~Z,b,c,d
1-3, produces 7: ~Z,a,c,d
1-4, produces 8: ~Z,a,b,d
1-5, produces 9: ~Z,a,b,c

2-7, produces 10: ~Z,c,d
2-8, produces 11: ~Z,b,d
2-9, produces 12: ~Z,b,c

3-8, produces 13: ~Z,a,d
3-9, produces 14: ~Z,a,c
3-11, produces 15: ~Z,d
3-12, produces 16: ~Z,c

4-9, produces 17: ~Z,a,b
4-12, produces 18: ~Z,b
4-14, produces 19: ~Z,a
4-16, produces: ~Z

 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PL {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> inputList = new ArrayList<ArrayList<String>>();
        try {
            File inputFile = new File("C:\\Users\\johnh\\Desktop\\logic_test_cases\\easy1_CNF.txt"); // Replace with the name of your input file
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ArrayList<String> lineList = new ArrayList<String>();
                for (String s : line.split(",")) {
                    lineList.add(s.trim());
                }
                inputList.add(lineList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found.");
            return;
        }
        // Print out the contents of the inputList ArrayList of ArrayLists
        for (ArrayList<String> lineList : inputList) {
            for (String s : lineList) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
