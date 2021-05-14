package com.contactsunny.poc.reversetraversalbinarytree;

public class Node {

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void insert(Node node) {
        insert(this, node);
    }

    /**
     * Method to insert a node to the BST.
     *
     * @param root The root of the BST.
     * @param node The node to insert.
     *
     * @return The root of the BST.
     */
    private Node insert(Node root, Node node) {
        // Checking if the root is null. If so, the new node becomes the root.
        if (root == null) {
            root = node;
            return root;
        }

        // If the value of root is the same as the value we're trying to insert,
        // logging an error that the value is already present.
        // Returning back the root.
        if (node.value == root.value) {
            System.out.println("Node already present in tree.");
            return root;
        }

        // If the value is greater than the root's value, we have to insert it
        // to the right sub tree. So calling the same method recursively to
        // insert the new node to the right.
        if (node.value > root.value) {
            root.right = insert(root.right, node);
        }

        // If the value is less than the root's value, we have to insert it
        // to the left sub tree. So calling the same method recursively to
        // insert the new node to the left.
        if (node.value < root.value) {
            root.left = insert(root.left, node);
        }
        return root;
    }

    public Node delete(int valueToDelete) {
        return delete(this, valueToDelete);
    }

    /**
     * Method to delete a node from the BST.
     *
     * @param node The root node.
     * @param valueToDelete The value to delete.
     *
     * @return The root node.
     */
    private Node delete(Node node, int valueToDelete) {
        // If the node is null, it means the value we're trying to delete is not present
        // in the BST. So logging an error.
        if (node == null) {
            System.out.println("Value not found in tree!");
            return null;
        }
        // If the value of the node is the value we're trying to delete,
        // then we found the node that has to be deleted.
        if (node.value == valueToDelete) {
            // If there are not child nodes for the current node,
            // then we don't have to worry about re-balancing the BST.
            // So returning null.
            if (node.left == null && node.right == null) {
                return null;
            }
            // If the node has only the right node and no left node,
            // then we have to connect the parent of the node to the
            // right sub tree of the node. So returning that.
            if (node.left == null) {
                return node.right;
            }
            // If the node has only the left node and no right node,
            // then we have to connect the parent of the node to the
            // left sub tree of the node. So returning that.
            if (node.right == null) {
                return node.left;
            }
            // If we have both children for the node we're trying to delete, then we have to
            // balance the BST. For this, we hae to find the max node from the left sub-tree of the node.
            Node maxNode = getMax(node.left);
            // Once we find the max value, we're changing the value of the current node
            // to the max value.
            node.value = maxNode.value;
            // We have to now delete the max node from the left sub-tree as that has been moved to the
            // current node. So doing that here, recursively.
            node.left = delete(node.left, maxNode.value);
            // Once that is done, we return the node back as this is the new child for the parent of the
            // node we just deleted.
            return node;
        }
        // If the value to delete is less than the value of the node,
        // we move to the left sub-tree to delete that node.
        if (valueToDelete < node.value) {
            node.left = delete(node.left, valueToDelete);
        }
        // If the value to delete is greater than the value of the node,
        // we move to the right sub-tree to delete that node.
        if (valueToDelete > node.value) {
            node.right = delete(node.right, valueToDelete);
        }
        // After everything, we return the current node back.
        return node;
    }

    /**
     * Method to get the node with the highest value in the given BST.
     *
     * @param node the root node
     *
     * @return node with the max value
     */
    private Node getMax(Node node) {
        // If the node is null, then we can't do much.
        if (node == null) {
            return null;
        }
        // If the right node is null, it means we have reached
        // the node with the highest in the given BST.
        // So returning that.
        if (node.right == null) {
            return node;
        }
        // If we have a right sub tree, it means we have to traverse more
        // to find the highest.
        return getMax(node.right);
    }
}
