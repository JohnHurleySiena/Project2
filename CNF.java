import java.util.Stack;

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

public class CNF {
    public static Node parse(String sentence) {
        Stack<Node> stack = new Stack<Node>();

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);

            if (Character.isLetter(c)) {
                Node node = new Node(Character.toString(c));
                stack.push(node);
            } else if (c == '~') {
                Node node = new Node(Character.toString(c));
                node.right = stack.pop();
                stack.push(node);
            } else if (c == '>' || c == '|') {
                Node node = new Node(Character.toString(c));
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            } else if (c == '&') {
                Node node = new Node(Character.toString(c));
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            }
        }

        return stack.pop();
    }

    public static void printTree(Node node, int level) {
        if (node == null) {
            return;
        }

        printTree(node.right, level + 1);

        System.out.println(" ".repeat(level * 4) + node.data);

        printTree(node.left, level + 1);
    }

    public static void main(String[] args) {
        String sentence = "((~Z > (a | (b | (c | d)))) & (~a & (~b & (~c & ~d))))";
        Node root = parse(sentence);
        printTree(root, 0);
    }
}
