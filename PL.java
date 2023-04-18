/*
C:\\Users\\johnh\\Desktop\\logic_test_cases\\easy1_CNF.txt
C:\\Users\\John H\\Desktop\\easy1_CNF.txt
 */

import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.FileWriter;
import java.io.IOException;
 



 public class PL {
     public static void main(String[] args) throws IOException {
         ArrayList<ArrayList<String>> inputList = new ArrayList<ArrayList<String>>(); // Create a new ArrayList of ArrayLists to store the input
         


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
             scanner.close(); // Close the Scanner object to free up resources
         } catch (FileNotFoundException e) { // Handle the FileNotFoundException that may be thrown if the input file is not found
             System.out.println("Error: Input file not found."); // Print an error message
             return; // End the program
         }


         ArrayList<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>(inputList);

         // Here's where the PL Resolution happens
         // The firstCompare will point to one of the lines of variables
         // The secondCompare will be the other line of variables to compare to
         // I am using temp here to store temporary arrays to put into outputLines
         // The cancelled boolean will indicate whether anything was cancelled, if nothing is
         // cancelled then the input lines will be the output
         ArrayList<String> firstCompare = null;
         ArrayList<String> secondCompare = null;
         ArrayList<String> temp = null;
         ArrayList<String> temp2 = null;
         boolean cancelled = false;
         

         for(int i = 0; i < inputList.size(); i++){ // Getting first list to compare and storing it
            firstCompare = inputList.get(i);
            for(int j = 0; j < inputList.size(); j++){ //  Getting second list to compare and storing it
                secondCompare = inputList.get(j);

                for(int k = 0; k < firstCompare.size();k++){ // To loop through the variables in first list


                    for(int o = 0; o < secondCompare.size();o++){ //  To loop through the variables in second list

                // This if statement will check if the variable exists in the two lists to save computing power in large lists.
                //                                          if(secondCompare.contains(firstCompare.get(k)) | secondCompare.contains("~" + firstCompare.get(k))){
                    if(firstCompare.get(k).length() == 2){ // This will check if the variable is negated (2 characters indicates negated)
                        // This statement using substrings to check if there is a positive and a negative
                        // firstCompare.get(k).substring(1).equals(secondCompare.get(o)) & secondCompare.get(o).length() == 1 
                        if(firstCompare.get(k).equals("~" + secondCompare.get(o))){
                            System.out.println(firstCompare.toString() + " and " + secondCompare.toString() + " resolving " + firstCompare.get(k) + " + " + secondCompare.get(o));
                            cancelled = true; // Indicates whether or not we cancelled a variable.
                            
                        }


                    }else if(firstCompare.get(k).length() == 1){ // This will check if the variable is positive
                        // Adding negated symbol to check if it equals
                        if(("~" + firstCompare.get(k)).equals(secondCompare.get(o))){
                            System.out.println(firstCompare.toString() + " and " + secondCompare.toString() + " resolving " + firstCompare.get(k) + " + " + secondCompare.get(o));
                            cancelled = true; // Indicates whether or not we cancelled a variable.
                            
                        }

                    }

                    if(cancelled){ // If there is a variable that is cancelled then we need to add a new list to our output.
                        temp = new ArrayList<String>(firstCompare); // Temporary to store and edit current list
                        temp.remove(k); // Removes the variable that needs to be cancelled.


                        
                        temp2 = new ArrayList<String>(secondCompare);
                        temp2.remove(o);

                        for(int p = 0; p < temp2.size(); p++){
                            if(!temp.contains(temp2.get(p)))
                            temp.add(temp2.get(p));
                        }
                        temp.sort(null);
                        


                        if(!outputList.contains(temp)){ // If the outputList already contains the sentence then don't add
                            outputList.add(temp);
                        }

                        if(!inputList.contains(temp)){ // Re-add the new sentence to the inputList if it doesn't exist
                            inputList.add(temp);
                        }
                        
                        cancelled = false; // Reset cancelled variable
                    }

        



            }
            }

            }


         }
         

         // This block of code checks if the outputList is empty, if empty then print the input.
         if(outputList.size() == 0){
            outputList = new ArrayList<>(inputList);
         }
         

         // This block of code sorts the elements of outputList by  ASCII.

         Collections.sort(outputList, new Comparator<ArrayList<String>>() {
            public int compare(ArrayList<String> list1, ArrayList<String> list2) {
                String str1 = String.join("", list1);
                String str2 = String.join("", list2);
                return str1.compareTo(str2);
            }
        });


         for (ArrayList<String> lineList : outputList) { // Loop through each ArrayList in the inputList ArrayList of ArrayLists
            for (String s : lineList) { // Loop through each String in the current ArrayList
                System.out.print(s + " "); // Print the current String followed by a space
            }
            System.out.println(); // Print a newline character to separate the lines
        }



        // This block of code writes the sorted elements of outputList ArrayList to a file named "output.txt"
        String filePath = "output.txt";
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);


        // Each element of the sublist is written to the file followed by a space character.
        for (ArrayList<String> sublist : outputList) {
            for (String str : sublist) {
                writer.write(str + " ");
            }
            writer.write(System.lineSeparator());
        }

        // Close the FileWriter object for efficieny
        writer.close();


     } // End of main
     

 }
 