class Node {
    String value;
    Node left, right;

    Node(String value) {
        this.value = value;
        left = right = null;
    }
}

public class CountNodes {

    public static int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node("A");
        root.left = new Node("B");
        root.right = new Node("C");
        root.left.left = new Node("D");
        root.left.right = new Node("E");
        root.right.right = new Node("F");

        System.out.println("Total de nós na árvore: " + countNodes(root));
    }
}
