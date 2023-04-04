import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
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

    public void insert(int data, Stack<Node> stack) {
        Node current = root;
        Node newNode = new Node(data);
        

        

        // Push the new node onto the stack
        stack.push(newNode);

        // Increment the size of the tree
        size++;
    }

    public void printInorder(Node Node) {
        if (Node == null)
            return;

        printInorder(Node.left);
        System.out.print(Node.data + " ");
        printInorder(Node.right);
    }

    public void readPropositionsFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        Stack<Node> stack = new Stack<Node>();

        while (scanner.hasNextLine()) {
            int data = Integer.parseInt(scanner.nextLine());
            insert(data, stack);
        }

        scanner.close();

        // Print the stack
        System.out.println("Variables in the stack: " + stack);
    }

    public static void main(String[] args) {
        PropositionalTree tree = new PropositionalTree();
        String fileName = "C:\\Users\\johnh\\Desktop\\list.txt";

        try {
            tree.readPropositionsFromFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        System.out.println("Inorder traversal of propositional tree is: ");
        tree.printInorder(tree.root);
        System.out.println("\nTotal number of nodes in propositional tree is: " + tree.size);

        // (P biconditional Q) needs to be turned into ((P > Q) & (Q > P))
        
    }
}
