import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem D: Binary Search Tree I (Aizu ALDS1_8_A)
 *
 * Problem Summary:
 * Maintain a Binary Search Tree (BST) under a sequence of m operations:
 *   - "insert k": insert key k into the BST.
 *   - "print": print the keys in Inorder (sorted) and Preorder, each key
 *     preceded by a single space.
 *
 * Plain-English Explanation & Design Decisions:
 * - We follow the exact iterative CLRS TREE-INSERT algorithm given in the
 *   problem statement: start at the root, walk left if k < current.key or
 *   right otherwise, and attach the new node under the last visited node.
 * - An inorder walk of a valid BST always outputs the keys in ascending order,
 *   while a preorder walk reveals the exact branching shape of the tree.
 * - The problem guarantees that the tree height never exceeds 100 and there
 *   are at most 10 print commands, so recursive traversals are completely safe.
 *
 * Complexity Analysis (for tree height H <= 100 and N inserted nodes):
 * - insert: O(H) time, O(1) extra space per insertion.
 * - print:  O(N) time, O(H) recursion stack space.
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
                int key = in.nextInt();
                insert(key);
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
