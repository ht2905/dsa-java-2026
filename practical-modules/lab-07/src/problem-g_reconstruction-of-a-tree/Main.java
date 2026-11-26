import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem G: Reconstruction of a Tree (Aizu ALDS1_7_D)
 *
 * Problem Summary:
 * Given the Preorder and Inorder traversal sequences of a binary tree with n
 * uniquely labeled nodes (1 to n), output its Postorder traversal sequence.
 *
 * Plain-English Explanation & Design Decisions:
 * - Preorder visits:  [Root] [Left subtree] [Right subtree]
 * - Inorder visits:   [Left subtree] [Root] [Right subtree]
 * - Postorder visits: [Left subtree] [Right subtree] [Root]
 * - Because every node ID is unique, these two sequences uniquely determine
 *   the tree:
 *     1. The next unread element in the Preorder array is always the root of
 *        the current subtree.
 *     2. Find that root's position `mid` in the Inorder array. Everything to
 *        the left of `mid` belongs to the left subtree; everything to the right
 *        belongs to the right subtree.
 *     3. Recursively reconstruct the left subtree, then the right subtree, and
 *        finally append the root to the postorder answer (Left -> Right -> Root).
 * - Precomputing `inorderPos[nodeId]` gives O(1) lookup of `mid` instead of
 *   scanning the inorder array at every recursive step.
 *
 * Complexity Analysis:
 * - Time Complexity: O(n) — each node is processed once with O(1) root lookup.
 * - Space Complexity: O(n) for the traversal arrays, position lookup table,
 *   and recursion stack.
 */
public class Main {

    private static int[] preorder;
    private static int[] inorderPos;
    private static int[] postorder;
    private static int preIndex = 0;
    private static int postIndex = 0;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        preorder = new int[n];
        inorderPos = new int[n + 1]; // Node IDs are 1..n
        postorder = new int[n];

        for (int i = 0; i < n; i++) {
            preorder[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int nodeId = in.nextInt();
            inorderPos[nodeId] = i;
        }

        reconstruct(0, n - 1);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.append(' ');
            }
            out.append(postorder[i]);
        }
        System.out.println(out);
    }

    /**
     * Reconstructs the subtree corresponding to inorder[inLeft .. inRight]
     * and records its nodes in postorder.
     */
    private static void reconstruct(int inLeft, int inRight) {
        if (inLeft > inRight) {
            return;
        }

        int rootVal = preorder[preIndex++];
        int mid = inorderPos[rootVal];

        reconstruct(inLeft, mid - 1);
        reconstruct(mid + 1, inRight);
        postorder[postIndex++] = rootVal;
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
