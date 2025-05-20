class Node {
    String value;
    Node left, right;

    Node(String value) {
        this.value = value;
        left = right = null;
    }
}

public class PreOrderTraversal {

    public static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node("A");
        root.left = new Node("B");
        root.right = new Node("C");
        root.left.left = new Node("D");
        root.left.right = new Node("E");
        root.right.right = new Node("F");

        System.out.print("Pré-Ordem: ");
        preOrder(root);
        System.out.println();
    }
}
