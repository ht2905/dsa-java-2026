import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem I: Even Relation (AtCoder ABC126 D)
 *
 * Problem Summary:
 * Given a weighted tree with N vertices (1 to N) and N - 1 undirected edges
 * with integer lengths w_i, color each vertex 0 (white) or 1 (black) so that
 * any two vertices of the same color have an even distance between them.
 *
 * Plain-English Explanation & Design Decisions:
 * - Pick vertex 1 as the root and imagine measuring the distance from vertex 1
 *   to every other vertex v, calling it dist(1, v).
 * - In any tree, the unique path between two vertices x and y goes up to their
 *   lowest common ancestor (LCA) and back down, so:
 *       dist(x, y) = dist(1, x) + dist(1, y) - 2 * dist(1, LCA(x, y))
 * - Because 2 * dist(1, LCA(x, y)) is ALWAYS even, the parity (even/odd) of
 *   dist(x, y) depends only on dist(1, x) + dist(1, y):
 *     - If dist(1, x) and dist(1, y) have the SAME parity (both even or both
 *       odd), their sum is even, so dist(x, y) is guaranteed to be even!
 * - Therefore, coloring vertex v with (dist(1, v) % 2) always works:
 *     - Crossing an even-length edge keeps the same color.
 *     - Crossing an odd-length edge flips the color (0 <-> 1).
 * - We run an iterative Breadth-First Search (BFS) from vertex 1 so even a
 *   chain of 100,000 vertices never causes a StackOverflowError.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) — each vertex and edge is visited once during BFS.
 * - Space Complexity: O(N) for the adjacency list, color array, and BFS queue.
 */
public class Main {

    private static final int UNVISITED = -1;

    static class Edge {
        final int to;
        final int weightParity; // 0 if edge length is even, 1 if odd

        Edge(int to, int weightParity) {
            this.to = to;
            this.weightParity = weightParity;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        @SuppressWarnings("unchecked")
        List<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int weight = in.nextInt();
            int parity = weight & 1; // Only even/odd matters
            adj[u].add(new Edge(v, parity));
            adj[v].add(new Edge(u, parity));
        }

        int[] color = colorTreeByDistanceParity(n, adj);

        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            out.append(color[i]).append('\n');
        }
        System.out.print(out);
    }

    private static int[] colorTreeByDistanceParity(int n, List<Edge>[] adj) {
        int[] color = new int[n + 1];
        Arrays.fill(color, UNVISITED);

        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        color[1] = 0;
        queue[tail++] = 1;

        while (head < tail) {
            int u = queue[head++];
            for (Edge edge : adj[u]) {
                if (color[edge.to] == UNVISITED) {
                    // XOR flips the color when the edge length is odd
                    color[edge.to] = color[u] ^ edge.weightParity;
                    queue[tail++] = edge.to;
                }
            }
        }
        return color;
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
