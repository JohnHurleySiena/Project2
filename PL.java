
// Simple example of resolution
// P, ~Q, R
// P, Q, S, ~T, U
// The ~Q and Q will fall out, the others will continue
// This should produce
// P, R, S, ~T, U
//
//


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PL {


    public static void main(String[] args) {


        String fileName = "Z:\\nList.txt";


        ArrayList<Character> charList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1) {
                char ch = (char) c;


                if(ch != ',' & ch != ' '){
                charList.add(ch);
                }

            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        for(int i = 0; i < charList.size(); i++){
            char temp = charList.get(i);

            for(int j = 0; j < charList.size(); j++){

                if(temp == charList.get(j) & charList.get(j - 1) == '~'){
                    System.out.println("Removed " + temp + " and " + charList.get(j) + " and " + charList.get(j - 1));
                    charList.remove(temp);
                    charList.remove(charList.get(j));
                    charList.remove(charList.get(j-1));
                }
            }
        }

        System.out.println(charList);
    }
}
