import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem C: Tree Walk (Aizu ALDS1_7_C)
 *
 * Problem Summary:
 * Given a binary tree with n nodes (IDs 0 to n-1), where each node lists its
 * left and right child (-1 if absent), print its Preorder, Inorder, and
 * Postorder traversals.
 *
 * Plain-English Explanation & Design Decisions:
 * - A binary tree traversal visits every node in a systematic order:
 *     - Preorder:  visit Root, then Left subtree, then Right subtree.
 *     - Inorder:   visit Left subtree, then Root, then Right subtree.
 *     - Postorder: visit Left subtree, then Right subtree, then Root.
 * - Because node 0 is not guaranteed to be the root, we record each child's
 *   parent while reading the input, then find the unique node with parent -1.
 * - Since n <= 25, direct recursion mirrors the mathematical definition of
 *   binary trees cleanly without any risk of stack overflow.
 * - Note the exact output format: every node ID is preceded by a single space.
 *
 * Complexity Analysis:
 * - Time Complexity: O(n) — each of the three traversals visits every node once.
 * - Space Complexity: O(n) for the parent/left/right arrays and recursion stack.
 */
public class Main {

    private static final int NIL = -1;

    private static int[] leftChild;
    private static int[] rightChild;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        int[] parent = new int[n];
        leftChild = new int[n];
        rightChild = new int[n];
        Arrays.fill(parent, NIL);

        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            int left = in.nextInt();
            int right = in.nextInt();

            leftChild[id] = left;
            rightChild[id] = right;
            if (left != NIL) {
                parent[left] = id;
            }
            if (right != NIL) {
                parent[right] = id;
            }
        }

        int root = findRoot(parent);

        StringBuilder out = new StringBuilder();

        out.append("Preorder\n");
        preorder(root, out);
        out.append('\n');

        out.append("Inorder\n");
        inorder(root, out);
        out.append('\n');

        out.append("Postorder\n");
        postorder(root, out);
        out.append('\n');

        System.out.print(out);
    }

    private static int findRoot(int[] parent) {
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == NIL) {
                return i;
            }
        }
        return 0;
    }

    private static void preorder(int u, StringBuilder out) {
        if (u == NIL) {
            return;
        }
        out.append(' ').append(u);
        preorder(leftChild[u], out);
        preorder(rightChild[u], out);
    }

    private static void inorder(int u, StringBuilder out) {
        if (u == NIL) {
            return;
        }
        inorder(leftChild[u], out);
        out.append(' ').append(u);
        inorder(rightChild[u], out);
    }

    private static void postorder(int u, StringBuilder out) {
        if (u == NIL) {
            return;
        }
        postorder(leftChild[u], out);
        postorder(rightChild[u], out);
        out.append(' ').append(u);
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
