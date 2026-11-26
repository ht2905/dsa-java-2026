/**
 * Main Problem: Binary Search Tree (main-problem.png)
 *
 * Problem Summary:
 * Given the list of keys:
 *   40, 73, 6, 24, 33, 29, 50, 45, 12, 67, 52, 15, 18, 24, 67, 9
 * 1.a. Insert all items from left to right into a Binary Search Tree (BST)
 *      and display the resulting tree.
 * 1.b. Delete the root node (40) and display the updated tree.
 *
 * Plain-English Explanation & Design Decisions:
 * - A Binary Search Tree keeps smaller keys in the left subtree and larger
 *   keys in the right subtree of every node.
 * - Duplicate handling: In the reference diagram in main-problem.png, the
 *   repeated values (24 and 67) do not appear twice, so our insert method
 *   ignores a key if it is already present in the tree.
 * - Deleting the root (40): Because 40 has two children (left child 6 and
 *   right child 73), we cannot just remove it directly. Following CLRS
 *   (Chapter 12), we replace it with its Inorder Successor — the smallest key
 *   in its right subtree (45). That preserves the BST ordering everywhere.
 *   (Using the Inorder Predecessor, 33, would also produce a valid BST.)
 *
 * Complexity Analysis (for a tree of N nodes and height H):
 * - Insert: O(H) time (O(log N) for a balanced tree, O(N) worst case), O(1) extra space.
 * - Delete: O(H) time to locate the node and its inorder successor, O(1) extra space.
 * - Traversals / printing: O(N) time, O(H) recursion stack space.
 */
public class Main {

    private static final int[] INPUT_KEYS = {
        40, 73, 6, 24, 33, 29, 50, 45, 12, 67, 52, 15, 18, 24, 67, 9
    };

    static class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
        }
    }

    static class BinarySearchTree {
        Node root;

        /**
         * Inserts a key into the BST using the iterative CLRS procedure.
         * Duplicate keys are skipped to match the diagram in main-problem.png.
         */
        void insert(int key) {
            Node parent = null;
            Node current = root;

            while (current != null) {
                parent = current;
                if (key == current.key) {
                    return; // Duplicate key: already in the tree
                } else if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            Node newNode = new Node(key);
            newNode.parent = parent;

            if (parent == null) {
                root = newNode;
            } else if (key < parent.key) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        /**
         * Replaces subtree rooted at u with subtree rooted at v (CLRS TRANSPLANT).
         */
        private void transplant(Node u, Node v) {
            if (u.parent == null) {
                root = v;
            } else if (u == u.parent.left) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
            if (v != null) {
                v.parent = u.parent;
            }
        }

        /**
         * Returns the node with the smallest key in the subtree rooted at node.
         */
        Node minimum(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        /**
         * Deletes node z from the BST (CLRS TREE-DELETE).
         */
        void delete(Node z) {
            if (z == null) {
                return;
            }

            if (z.left == null) {
                // Case 1: no left child -> promote right child
                transplant(z, z.right);
            } else if (z.right == null) {
                // Case 2: no right child -> promote left child
                transplant(z, z.left);
            } else {
                // Case 3: two children -> replace z with its inorder successor y
                Node y = minimum(z.right);
                if (y.parent != z) {
                    // Detach y from its current position and promote y's right child
                    transplant(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplant(z, y);
                y.left = z.left;
                y.left.parent = y;
            }
        }

        void printInorder(Node node, StringBuilder out) {
            if (node == null) {
                return;
            }
            printInorder(node.left, out);
            out.append(node.key).append(' ');
            printInorder(node.right, out);
        }

        void printPreorder(Node node, StringBuilder out) {
            if (node == null) {
                return;
            }
            out.append(node.key).append(' ');
            printPreorder(node.left, out);
            printPreorder(node.right, out);
        }

        /**
         * Prints a sideways ASCII diagram of the tree (right subtree on top,
         * left subtree on bottom) so parent-child links are easy to read.
         */
        void printTree() {
            printSubtree(root, "", true);
        }

        private void printSubtree(Node node, String prefix, boolean isLast) {
            if (node == null) {
                return;
            }
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.key);
            String childPrefix = prefix + (isLast ? "    " : "│   ");

            if (node.left != null && node.right != null) {
                printSubtree(node.left, childPrefix, false);
                printSubtree(node.right, childPrefix, true);
            } else if (node.left != null) {
                printSubtree(node.left, childPrefix, true);
            } else if (node.right != null) {
                printSubtree(node.right, childPrefix, true);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        for (int key : INPUT_KEYS) {
            bst.insert(key);
        }

        System.out.println("=== Part 1.a: BST after inserting all items ===");
        bst.printTree();
        printTraversals(bst);

        int oldRootKey = bst.root.key;
        int successorKey = bst.minimum(bst.root.right).key;
        bst.delete(bst.root);

        System.out.println();
        System.out.println("=== Part 1.b: BST after deleting root (" + oldRootKey
            + ", replaced by inorder successor " + successorKey + ") ===");
        bst.printTree();
        printTraversals(bst);
    }

    private static void printTraversals(BinarySearchTree bst) {
        StringBuilder inorder = new StringBuilder();
        StringBuilder preorder = new StringBuilder();
        bst.printInorder(bst.root, inorder);
        bst.printPreorder(bst.root, preorder);
        System.out.println("Inorder (sorted): " + inorder.toString().trim());
        System.out.println("Preorder:         " + preorder.toString().trim());
    }
}
