import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem E: Binary Search Tree II (Aizu ALDS1_8_B)
 *
 * Problem Summary:
 * Extend Binary Search Tree I to support three operations:
 *   - "insert k": insert key k into the BST.
 *   - "find k":   print "yes" if key k is in the BST, otherwise "no".
 *   - "print":    print the keys in Inorder and Preorder (space before each key).
 *
 * Plain-English Explanation & Design Decisions:
 * - Searching a BST works like looking up a word in a dictionary: start at the
 *   root and compare the target key with the current node's key.
 *     - If equal, we found it ("yes").
 *     - If smaller, it can only exist in the left subtree.
 *     - If larger, it can only exist in the right subtree.
 *     - If we hit null, the key is not in the tree ("no").
 * - Using an iterative loop for both insert and find avoids function-call
 *   overhead and directly matches CLRS ITERATIVE-TREE-SEARCH (Chapter 12).
 *
 * Complexity Analysis (for tree height H <= 100 and N inserted nodes):
 * - insert / find: O(H) time, O(1) extra space.
 * - print:         O(N) time, O(H) recursion stack space.
 * - Overall Space Complexity: O(N) for storing up to N nodes.
 */
public class Main {

    static class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
        }
    }

    private static Node root = null;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int m = in.nextInt();

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String command = in.next();
            if (command.equals("insert")) {
                insert(in.nextInt());
            } else if (command.equals("find")) {
                out.append(contains(in.nextInt()) ? "yes\n" : "no\n");
            } else if (command.equals("print")) {
                inorder(root, out);
                out.append('\n');
                preorder(root, out);
                out.append('\n');
            }
        }

        System.out.print(out);
    }

    private static void insert(int key) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (key < current.key) {
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

    private static boolean contains(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.key) {
                return true;
            } else if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    private static void inorder(Node node, StringBuilder out) {
        if (node == null) {
            return;
        }
        inorder(node.left, out);
        out.append(' ').append(node.key);
        inorder(node.right, out);
    }

    private static void preorder(Node node, StringBuilder out) {
        if (node == null) {
            return;
        }
        out.append(' ').append(node.key);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    static class FastScanner {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
