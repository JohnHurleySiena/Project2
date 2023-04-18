import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Stack;

class Node {
    char data;
    Node left, right;

    public Node(char data) {
        this.data = data;
        left = right = null;
    }
}

class PropositionalTree {
    Node root;
    int size;

    public PropositionalTree() {
        root = null;
        size = 0;
    }

    public void insert(char data, Stack<Node> stack) {
        Node current = root;
        Node newNode = new Node(data);

        while(!stack.empty()){
            if(root == null){
                root = stack.pop();
            }else{
                



            }

        }
        
        // Increment the size of the tree
        size++;
    }



    public void readPropositionsFromFile() throws FileNotFoundException {
        String file = "C:\\Users\\johnh\\Desktop\\list.txt";
        
        Stack<Character> charStack = new Stack<>();
        

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {

                    // Need to check here if ( or ) or ' ' then we don't add to the stack
                    char temp = line.charAt(i);
                    if( temp != '(' & temp != ')' & temp != ' '){
                    charStack.push(temp);
                    }

                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the stack
        System.out.println("Variables in the stack: " + charStack);
    }





    public static void main(String[] args) {
        PropositionalTree tree = new PropositionalTree();

        try {
            tree.readPropositionsFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }


        System.out.println("\nTotal number of nodes in propositional tree is: " + tree.size);

        // (P biconditional Q) needs to be turned into ((P > Q) & (Q > P))

    }
}
