/*
C:\\Users\\johnh\\Desktop\\logic_test_cases\\easy1_CNF.txt
C:\\Users\\John H\\Desktop\\easy1_CNF.txt
 */

 import java.util.ArrayList; // Import the ArrayList class
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner; // Import the Scanner class for reading input
 import java.io.File; // Import the File class for reading files
 import java.io.FileNotFoundException; // Import the FileNotFoundException class for handling file not found errors
import java.io.FileWriter;
import java.io.IOException;
 
 public class PL {
     public static void main(String[] args) throws IOException {
         ArrayList<ArrayList<String>> inputList = new ArrayList<ArrayList<String>>(); // Create a new ArrayList of ArrayLists to store the input
         ArrayList<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>();


         try {
             File inputFile = new File("C:\\Users\\John H\\Desktop\\logic_test_cases\\hard1_CNF.txt"); // Create a new File object representing the input file

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
         ArrayList<String> temp = null;
         boolean cancelled = false;
         

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
                            cancelled = true;
                            
                        }


                    }else if(firstCompare.get(k).length() == 1){ // This will check if the variable is regular
                        if(("~" + firstCompare.get(k)).equals(secondCompare.get(o))){
                            cancelled = true;
                            
                        }

                    }

                    if(cancelled){ // If there is a variable that is cancelled then we need to add a new list to our output.
                        temp = new ArrayList<String>(firstCompare);
                        temp.remove(k);
                        if(!outputList.contains(temp)){
                            outputList.add(temp);
                        }

                        if(!inputList.contains(temp)){
                            inputList.add(temp);
                        }
                        
                        cancelled = false;
                    }

                }



            }
            }

            }


         }
         
         if(outputList.size() == 0){
            outputList = new ArrayList<>(inputList);
         }
         
         Collections.sort(outputList, new Comparator<ArrayList<String>>() {
            public int compare(ArrayList<String> list1, ArrayList<String> list2) {
                String str1 = String.join("", list1);
                String str2 = String.join("", list2);
                return str1.compareTo(str2);
            }
        });



         /*// Print out the contents of the inputList ArrayList of ArrayLists
         for (ArrayList<String> lineList : inputList) { // Loop through each ArrayList in the inputList ArrayList of ArrayLists
             for (String s : lineList) { // Loop through each String in the current ArrayList
                 System.out.print(s + " "); // Print the current String followed by a space
             }
             System.out.println(); // Print a newline character to separate the lines
         }
         */


         for (ArrayList<String> lineList : outputList) { // Loop through each ArrayList in the inputList ArrayList of ArrayLists
            for (String s : lineList) { // Loop through each String in the current ArrayList
                System.out.print(s + " "); // Print the current String followed by a space
            }
            System.out.println(); // Print a newline character to separate the lines
        }

        String filePath = "output.txt";
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);


        for (ArrayList<String> sublist : outputList) {
            for (String str : sublist) {
                writer.write(str + " ");
            }
            writer.write(System.lineSeparator());
        }

        // Close the FileWriter object
        writer.close();


     } // End of main
     

 }
 