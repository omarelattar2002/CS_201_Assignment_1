package binary_search_treexo;

import java.util.Scanner;

// Binary Search Tree implementation
class BinarySearchTree {


    static class Node {
        int value; 
        Node left, right; 

        // Constructor 
        public Node(int value) {
            this.value = value; 
            this.left = this.right = null; 
        }
    }

    private Node root; // Root of the binary search tree

    // Method to create a balanced binary search tree from a sorted array
    public void createTree(int[] values) {
        root = createBalancedTree(values, 0, values.length - 1);
    }

    // Recursive helper method to create a balanced binary search tree
    private Node createBalancedTree(int[] values, int start, int end) {
        if (start > end) {
            return null; 
        }
        int mid = (start + end) / 2; 
        Node node = new Node(values[mid]); 
        node.left = createBalancedTree(values, start, mid - 1);
        node.right = createBalancedTree(values, mid + 1, end);
        return node; 
    }

    // Method to add a node to the binary search tree
    public void addNode(int value) {
        root = insertNode(root, value);
    }

    // Recursive helper method to insert a node into the tree
    private Node insertNode(Node root, int value) {
        if (root == null) {
            return new Node(value); 
        }
        if (value < root.value) {
            root.left = insertNode(root.left, value); 
        } else if (value > root.value) {
            root.right = insertNode(root.right, value); 
        }
        return root; 
    }

    // Method to delete a node from the binary search tree
    public void deleteNode(int value) {
        root = deleteNodeRecursive(root, value);
    }

    // Recursive helper method to delete a node
    private Node deleteNodeRecursive(Node root, int value) {
        if (root == null) {
            return null; 
        }
        if (value < root.value) {
            root.left = deleteNodeRecursive(root.left, value); 
        } else if (value > root.value) {
            root.right = deleteNodeRecursive(root.right, value); 
        } else {
            // Node to be deleted is found
            if (root.left == null) {
                return root.right; 
            } else if (root.right == null) {
                return root.left; 
            }
            // Node has two children: replace with in-order successor
            root.value = findMin(root.right); 
            root.right = deleteNodeRecursive(root.right, root.value); 
        }
        return root; // Return the root after deletion
    }

    // Helper method to find the minimum value in a subtree
    private int findMin(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            root = root.left; 
            minValue = root.value;
        }
        return minValue;
    }

    // Method to print the tree using in-order traversal
    public void printInOrder() {
        System.out.print("InOrder Traversal: ");
        inOrderTraversal(root);
        System.out.println();
    }

    // Recursive helper method for in-order traversal
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left); 
            System.out.print(node.value + " "); 
            inOrderTraversal(node.right); 
        }
    }

    // Method to print the tree using pre-order traversal
    public void printPreOrder() {
        System.out.print("PreOrder Traversal: ");
        preOrderTraversal(root);
        System.out.println();
    }

    // Recursive helper method for pre-order traversal
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.value + " "); 
            preOrderTraversal(node.left); 
            preOrderTraversal(node.right); 
        }
    }

    // Method to print the tree using post-order traversal
    public void printPostOrder() {
        System.out.print("PostOrder Traversal: ");
        postOrderTraversal(root);
        System.out.println();
    }

    // Recursive helper method for post-order traversal
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left); 
            postOrderTraversal(node.right); 
            System.out.print(node.value + " "); 
        }
    }
}

// Main class to handle user input and run the program
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            System.out.println("\nMenu:");
            System.out.println("1) Create a binary search tree");
            System.out.println("2) Add a node");
            System.out.println("3) Delete a node");
            System.out.println("4) Print nodes by InOrder");
            System.out.println("5) Print nodes by PreOrder");
            System.out.println("6) Print nodes by PostOrder");
            System.out.println("7) Exit program");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            // Handle user input based on the menu choice
            switch (choice) {
                case 1:
                    // Create a balanced tree with predefined values
                    int[] values = {1, 2, 3, 4, 5, 6, 7};
                    bst.createTree(values);
                    System.out.println("Binary search tree created.");
                    break;
                case 2:
                    // Add a node to the tree
                    System.out.print("Enter a value to add: ");
                    int valueToAdd = scanner.nextInt();
                    bst.addNode(valueToAdd);
                    System.out.println("Node added.");
                    break;
                case 3:
                    // Delete a node from the tree
                    System.out.print("Enter a value to delete: ");
                    int valueToDelete = scanner.nextInt();
                    bst.deleteNode(valueToDelete);
                    System.out.println("Node deleted.");
                    break;
                case 4:
                    // Print nodes using in-order traversal
                    bst.printInOrder();
                    break;
                case 5:
                    // Print nodes using pre-order traversal
                    bst.printPreOrder();
                    break;
                case 6:
                    // Print nodes using post-order traversal
                    bst.printPostOrder();
                    break;
                case 7:
                    // Exit the program
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
