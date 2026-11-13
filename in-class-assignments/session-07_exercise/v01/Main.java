import java.util.Scanner;

/**
 * Session 07 Exercise: Binary Search Tree (BST) Implementation
 *
 * Problem Summary:
 * Implement a Binary Search Tree (BST) storing integer data with the following operations:
 * 1. Insert a node into the BST
 * 2. Search for a node in the BST
 * 3. Traverse the tree using Inorder Traversal
 *
 * Design Decisions:
 * - Node Structure:
 *   - A static nested `Node` class holds an integer key (`data`) and references to `left` and `right` subtrees.
 * - Tree Encapsulation:
 *   - A `BinarySearchTree` class manages the `root` reference and provides public API methods
 *     (`insert`, `search`, `inorder`) that delegate to clean recursive helper methods.
 * - Inductive / Recursive Logic:
 *   - BST properties naturally lend themselves to recursion (CLRS style):
 *     - Left subtree keys < root key < Right subtree keys.
 *     - Readability is prioritized over micro-optimizations, avoiding complex pointer manipulation.
 * - Duplicate Handling:
 *   - Following standard BST conventions, duplicate keys are identified and preserved without
 *     inserting redundant duplicate nodes.
 * - User Interface (Scanner):
 *   - A menu-driven console loop powered by `Scanner` allows interactive testing as well as
 *     piped input streams.
 *
 * Complexity Analysis:
 * - Time Complexity:
 *   - Insert: O(h), where h is tree height.
 *     - Average case (balanced tree): O(log n).
 *     - Worst case (skewed tree / sorted insertion): O(n).
 *   - Search: O(h), where h is tree height.
 *     - Average case (balanced tree): O(log n).
 *     - Worst case (skewed tree): O(n).
 *   - Inorder Traversal: O(n) because every node is visited exactly once.
 * - Space Complexity:
 *   - Insert: O(h) auxiliary space on the call stack due to recursion (O(log n) avg, O(n) worst-case).
 *   - Search: O(h) auxiliary stack space for recursion.
 *   - Inorder Traversal: O(h) auxiliary stack space for recursion.
 *   - Total Structure: O(n) heap memory to store n tree nodes.
 */
public class Main {

    /**
     * Represents an individual node in the Binary Search Tree.
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Encapsulates Binary Search Tree operations.
     */
    static class BinarySearchTree {
        Node root;

        public BinarySearchTree() {
            this.root = null;
        }

        /**
         * Inserts a new integer into the BST.
         *
         * @param data integer value to insert.
         */
        public void insert(int data) {
            root = insertRecursive(root, data);
        }

        private Node insertRecursive(Node current, int data) {
            // Base case: found insertion point
            if (current == null) {
                return new Node(data);
            }

            // Inductive step: traverse left or right based on BST property
            if (data < current.data) {
                current.left = insertRecursive(current.left, data);
            } else if (data > current.data) {
                current.right = insertRecursive(current.right, data);
            } else {
                // Duplicate values are ignored in a standard BST
                System.out.println("Value " + data + " already exists in the BST. Duplicate ignored.");
            }

            return current;
        }

        /**
         * Searches for a target integer in the BST.
         *
         * @param target integer value to search for.
         * @return true if target exists in the tree, false otherwise.
         */
        public boolean search(int target) {
            return searchRecursive(root, target) != null;
        }

        /**
         * Returns the Node reference matching the target value, or null if not found.
         *
         * @param current current subtree root.
         * @param target  value to search.
         * @return Node if found, null otherwise.
         */
        private Node searchRecursive(Node current, int target) {
            // Base cases: root is null (not found) or target matches current node
            if (current == null || current.data == target) {
                return current;
            }

            // Target is smaller -> search left subtree
            if (target < current.data) {
                return searchRecursive(current.left, target);
            }

            // Target is larger -> search right subtree
            return searchRecursive(current.right, target);
        }

        /**
         * Performs an Inorder Traversal (Left -> Root -> Right).
         * For a BST, inorder traversal prints elements in strictly ascending order.
         */
        public void inorder() {
            if (root == null) {
                System.out.println("(Tree is empty)");
                return;
            }
            inorderRecursive(root);
            System.out.println();
        }

        private void inorderRecursive(Node current) {
            if (current != null) {
                inorderRecursive(current.left);
                System.out.print(current.data + " ");
                inorderRecursive(current.right);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Binary Search Tree (BST) Menu ===");
            System.out.println("1. Insert a node");
            System.out.println("2. Search for a node");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            if (!scanner.hasNextInt()) {
                break;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter an integer to insert: ");
                    if (scanner.hasNextInt()) {
                        int value = scanner.nextInt();
                        bst.insert(value);
                        System.out.println("Inserted " + value + " into the BST.");
                    }
                    break;

                case 2:
                    System.out.print("Enter an integer to search: ");
                    if (scanner.hasNextInt()) {
                        int target = scanner.nextInt();
                        boolean found = bst.search(target);
                        if (found) {
                            System.out.println("Result: Node " + target + " was FOUND in the BST.");
                        } else {
                            System.out.println("Result: Node " + target + " was NOT FOUND in the BST.");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Inorder Traversal (Sorted order): ");
                    bst.inorder();
                    break;

                case 4:
                    System.out.println("Exiting BST program. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
                    break;
            }
        }

        scanner.close();
    }
}
