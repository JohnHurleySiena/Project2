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


C:\\Users\\johnh\\Desktop\\logic_test_cases\\easy1_CNF.txt
 */

 import java.util.ArrayList; // Import the ArrayList class
 import java.util.Scanner; // Import the Scanner class for reading input
 import java.io.File; // Import the File class for reading files
 import java.io.FileNotFoundException; // Import the FileNotFoundException class for handling file not found errors
 
 public class PL {
     public static void main(String[] args) {
         ArrayList<ArrayList<String>> inputList = new ArrayList<ArrayList<String>>(); // Create a new ArrayList of ArrayLists to store the input
         ArrayList<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>();


         try {
             File inputFile = new File("C:\\Users\\johnh\\Desktop\\logic_test_cases\\easy1_CNF.txt"); // Create a new File object representing the input file

             Scanner scanner = new Scanner(inputFile); // Create a new Scanner object for reading input from the input file


             while (scanner.hasNextLine()) { // Loop through each line in the input file
                 String line = scanner.nextLine(); // Read the next line of input
                 ArrayList<String> lineList = new ArrayList<String>(); // Create a new ArrayList to store the substrings in this line

                 for (String s : line.split(",")) { // Split the line by the ',' character and loop through each resulting substring
                     lineList.add(s.trim()); // Add the trimmed substring to the ArrayList
                 }
                 inputList.add(lineList); // Add the ArrayList of substrings to the inputList ArrayList of ArrayLists
             }
             scanner.close(); // Close the Scanner object to free up system resources
         } catch (FileNotFoundException e) { // Handle the FileNotFoundException that may be thrown if the input file is not found
             System.out.println("Error: Input file not found."); // Print an error message
             return; // End the program
         }



         // Here's where the PL Resolution happens
         ArrayList<String> firstCompare = null;
         ArrayList<String> secondCompare = null;

         for(int i = 0; i < inputList.size(); i++){ // Getting first list
            firstCompare = inputList.get(i);
            for(int j = 0; j < inputList.size(); j++){ //  Getting second list
                secondCompare = inputList.get(j);

                for(int k = 0; k < firstCompare.size();k++){ // To loop through the characters in first list


                    for(int o = 0; o < secondCompare.size();o++){ //  To loop through the characters in second list

                if(secondCompare.contains(firstCompare.get(k)) | secondCompare.contains("~" + firstCompare.get(k))){
                    //System.out.println("We contain" + firstCompare.get(k));

                    if(firstCompare.get(k).length() == 2){ // This will check if the variable is negated
                        if(firstCompare.get(k).substring(1).equals(secondCompare.get(o)) & secondCompare.get(o).length() == 1 ){
                            System.out.println("Winner! " + firstCompare.get(k) + " " + secondCompare.get(o));
                            //Checks if first is negative then checks is second is postive
                            // Need to dupe the array and delete letters.
                        }


                    }else if(firstCompare.get(k).length() == 1){ // This will check if the variable is regular
                        if(("~" + firstCompare.get(k)).equals(secondCompare.get(o))){
                            // Checks if first is positive then checks if second is negative
                            // Needs to dupe the array and delete letters.
                            System.out.println("Winner! " + firstCompare.get(k) + " " + secondCompare.get(o));
                        }

                    }

                }


            }
            }

            }


         }




         // Print out the contents of the inputList ArrayList of ArrayLists
         for (ArrayList<String> lineList : inputList) { // Loop through each ArrayList in the inputList ArrayList of ArrayLists
             for (String s : lineList) { // Loop through each String in the current ArrayList
                 System.out.print(s + " "); // Print the current String followed by a space
             }
             System.out.println(); // Print a newline character to separate the lines
         }
         for (ArrayList<String> lineList : outputList) { // Loop through each ArrayList in the inputList ArrayList of ArrayLists
            for (String s : lineList) { // Loop through each String in the current ArrayList
                System.out.print(s + " "); // Print the current String followed by a space
            }
            System.out.println(); // Print a newline character to separate the lines
        }

     }

 }
 