package com.contactsunny.poc.reversetraversalbinarytree;

public class App {

    public static void main(String[] args) {

        Node root = new Node(50);

        int[] input = {25, 75, 40, 10, 80, 60};
        for (int element : input) {
            root.insert(new Node(element));
        }

        System.out.println(printTree(root));
        System.out.println("---------------------");

        System.out.println("Reverse Post Order Traversal");
        postOrderReverseTraversal(root);
        System.out.println();
        System.out.println("---------------------");

        System.out.println("Reverse In Order Traversal");
        inOrderReverseTraversal(root);
        System.out.println();
        System.out.println("---------------------");

    }

    /**
     * Method to perform a post order traversal of the given tree,
     * but in reverse direction.
     *
     * @param node The root node of the tree.
     */
    private static void postOrderReverseTraversal(Node node) {
        // If the node is null, we've reached the end of the left
        // or the right sub tree. Or the root node itself is null.
        if (node == null) {
            return;
        }

        // Because this is post order, and in reverse order,
        // we'll first visit all the right nodes or the right
        // sub tree.
        postOrderReverseTraversal(node.getRight());
        // After the right sub tree, we'll visit the left sub tree.
        postOrderReverseTraversal(node.getLeft());
        // Finally we'll visit the node itself.
        System.out.print(node.getValue() + " ");
    }

    /**
     * Method to perform an in order traversal of the given tree,
     * but in reverse direction.
     *
     * @param node The root node of the tree.
     */
    private static void inOrderReverseTraversal(Node node) {
        // If the node is null, we've reached the end of the left
        // or the right sub tree. Or the root node itself is null.
        if (node == null) {
            return;
        }

        // Because this is in order, and in reverse order,
        // we'll first visit all the right nodes or the right
        // sub tree.
        inOrderReverseTraversal(node.getRight());
        // After the right sub tree, we'll visit the node itself.
        System.out.print(node.getValue() + " ");
        // Finally we'll visit the left sub tree.
        inOrderReverseTraversal(node.getLeft());
    }

    public static String printTree(Node root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        printTree(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        printTree(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    public static void printTree(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            printTree(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            printTree(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }
}
