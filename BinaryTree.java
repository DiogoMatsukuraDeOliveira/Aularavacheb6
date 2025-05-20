class Node {
    String value;
    Node left, right;

    Node(String value) {
        this.value = value;
        left = right = null;
    }
}

public class BinaryTree {

    // Em-Ordem
    public static void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        // Montando a árvore:
        //       A
        //      / \
        //     B   C
        //    / \   \
        //   D   E   F

        Node root = new Node("A");
        root.left = new Node("B");
        root.right = new Node("C");
        root.left.left = new Node("D");
        root.left.right = new Node("E");
        root.right.right = new Node("F");

        // Executando o percurso em ordem
        System.out.print("Em-Ordem: ");
        inOrder(root);
        System.out.println();
    }
}
