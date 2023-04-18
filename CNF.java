import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;


/**
 * The Nodes that will hold data and left and right pointers.
 *
 * @author John Hurley
 * @version April 17th, 2023
 */
class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

/**
 * CNF class will input a sentence and reduce it to CNF.
 *
 * @author John Hurley
 * @version April 17th, 2023
 */
public class CNF {
    public static Node parse(String sentence) {
        Stack<Node> stack = new Stack<Node>();
        boolean notted = false;
        Node node = null;




        // This is where we will being to parse our sentence.
        // We are looping through the sentence for each character.
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);

            // Here we are checking if it is a character, if it is "notted" then a not symbol has come before
            // and we need to set it up so the not symbol is its parent node.
            // If it is normal then we push it to the stack.
            if (Character.isLetter(c)) {
                if(notted){
                    node.left = new Node(Character.toString(c));
                    notted = false;
                    stack.push(node);
                }else{
                    node = new Node(Character.toString(c));
                    stack.push(node);
                }
                // Here we check if the symbol is a not symbol and we will set notted to true and attempt to store it as the 
                // parent node of a character.
            } else if (c == '~') {
                node = new Node(Character.toString(c));
                node.right = stack.pop();
                stack.push(node);
                notted = true;
                // Here we attempt to tackle the implies and or. We will make it the parent node and set
                // The left and right nodes as the variables.
            } else if (c == '>' || c == '|') {
                node = new Node(Character.toString(c));
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
                // Here we do the same as above but seperate it for future parsing purposes.
            } else if (c == '&') {
                node = new Node(Character.toString(c));
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            }
        }

        // Returning our stack after parsing.
        return stack.pop();
    }


     /**
     * Inputs a node and level and prints our tree.
     * 
     * @param node The root node of our tree.
     * @param level How many levels the tree is.
     * @return none, will print out the outcome.
     */
    public static void printTree(Node node, int level) {
        if (node == null) { // If null do nothing
            return;
        }

        // Else we need to print in-order.
        printTree(node.right, level++);

        System.out.println(" ".repeat(level * 4) + node.data);

        printTree(node.left, level++);
    }


     /**
     * The rules method will check for certain theorems and refine if found.
     * 
     * @param node The root node of our tree.
     * @return A refined tree that has goen through a series of rules.
     */
    public static void rules(Node node) {


        // This is the double-negation elimination ~(~a) = a
        if(node.data.equals("~") & node.left.data.equals("~")){


        }else if(node.data.equals(">") & Character.isLetter(node.left.data.charAt(0)) & 
        Character.isLetter(node.right.data.charAt(0))){
        // This is the implication elimination: (a > b) = (~a or b)


        }else if(node.data.equals("&") & Character.isLetter(node.left.data.charAt(0)) & 
        node.right.data.equals("|") & Character.isLetter(node.right.left.data.charAt(0))
        & Character.isLetter(node.right.right.data.charAt(0))){
        // This is distributivity of or over and: (a | (B & C))


        }


    }

/**
 * Our main method to read in the file name and begin our program.
 *
 * @author John Hurley
 * @version April 17th, 2023
 */
    public static void main(String[] args) {

        // Please set the file name to the location on your computer
        // ***********************************************************************
        String filename = "Z:\\logic_test_cases\\medium2_in.txt";
        // ***********************************************************************


        // Our string builder and buffered reader to read in the line of input.
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // Print errors.
        } catch (IOException e) {
            e.printStackTrace();
        }
       

            // setting our root to our parsed node.
        Node root = parse(sb.toString());
        printTree(root, 0);
    }
}
