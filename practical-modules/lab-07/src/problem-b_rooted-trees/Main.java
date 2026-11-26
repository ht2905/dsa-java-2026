import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem B: Rooted Trees (Aizu ALDS1_7_A)
 *
 * Problem Summary:
 * Given a rooted tree with n nodes (IDs 0 to n-1) and each node's ordered list
 * of children, report for every node: its parent (-1 for root), its depth
 * (distance from root), its type ("root", "internal node", or "leaf"), and its
 * list of children in order.
 *
 * Plain-English Explanation & Design Decisions:
 * - A node in a general rooted tree can have any number of children, so we use
 *   the classic CLRS "left-child, right-sibling" representation (three plain
 *   int arrays):
 *     - parent[u]       = parent of node u (-1 if u is the root)
 *     - leftChild[u]    = first (leftmost) child of u (-1 if u has no children)
 *     - rightSibling[u] = next sibling immediately to the right of u (-1 if none)
 * - This keeps memory flat and fast even when n = 100,000.
 * - Finding the root: the root is the unique node whose parent is still -1
 *   after reading all parent-child links.
 * - Computing depths iteratively (BFS queue): a skewed tree with 100,000 nodes
 *   would overflow Java's call stack if we used recursion, so we walk down from
 *   the root level by level with a simple array-based queue.
 *
 * Complexity Analysis:
 * - Time Complexity: O(n) — each node and edge is visited a constant number of times.
 * - Space Complexity: O(n) for the tree arrays and BFS queue.
 */
public class Main {

    private static final int NIL = -1;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        int[] parent = new int[n];
        int[] leftChild = new int[n];
        int[] rightSibling = new int[n];
        Arrays.fill(parent, NIL);
        Arrays.fill(leftChild, NIL);
        Arrays.fill(rightSibling, NIL);

        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            int degree = in.nextInt();
            int prevChild = NIL;

            for (int j = 0; j < degree; j++) {
                int child = in.nextInt();
                parent[child] = id;

                if (j == 0) {
                    leftChild[id] = child;
                } else {
                    rightSibling[prevChild] = child;
                }
                prevChild = child;
            }
        }

        int root = findRoot(parent);
        int[] depth = computeDepths(n, root, leftChild, rightSibling);

        StringBuilder out = new StringBuilder();
        for (int u = 0; u < n; u++) {
            out.append("node ").append(u)
               .append(": parent = ").append(parent[u])
               .append(", depth = ").append(depth[u])
               .append(", ").append(nodeType(u, parent, leftChild))
               .append(", [");

            int child = leftChild[u];
            boolean first = true;
            while (child != NIL) {
                if (!first) {
                    out.append(", ");
                }
                out.append(child);
                first = false;
                child = rightSibling[child];
            }
            out.append("]\n");
        }

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

    private static int[] computeDepths(int n, int root, int[] leftChild, int[] rightSibling) {
        int[] depth = new int[n];
        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        queue[tail++] = root;
        depth[root] = 0;

        while (head < tail) {
            int u = queue[head++];
            int child = leftChild[u];
            while (child != NIL) {
                depth[child] = depth[u] + 1;
                queue[tail++] = child;
                child = rightSibling[child];
            }
        }
        return depth;
    }

    private static String nodeType(int u, int[] parent, int[] leftChild) {
        if (parent[u] == NIL) {
            return "root";
        }
        if (leftChild[u] == NIL) {
            return "leaf";
        }
        return "internal node";
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
