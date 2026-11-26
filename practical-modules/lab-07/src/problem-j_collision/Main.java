import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem J: Collision (AtCoder ABC209 D)
 *
 * Problem Summary:
 * Given a tree of N towns connected by N - 1 equal-length roads, answer Q
 * queries. In each query (c, d), Takahashi starts at town c and Aoki starts at
 * town d, and both walk toward each other at the same speed along the shortest
 * path. Output "Town" if they meet at a town, or "Road" if they meet halfway
 * along a road.
 *
 * Plain-English Explanation & Design Decisions:
 * - Because both people travel at the same speed, they meet at the exact
 *   midpoint of the unique path between c and d:
 *     - If the number of roads between c and d is EVEN (e.g., 2, 4, 6), the
 *       midpoint lands squarely on a town -> print "Town".
 *     - If the number of roads between c and d is ODD (e.g., 1, 3, 5), the
 *       midpoint lands in the middle of a road -> print "Road".
 * - We do NOT need to compute the full distance for each query! Every tree is
 *   bipartite (2-colorable like a checkerboard): if we label each town with
 *   the parity (0 or 1) of its depth from town 1 using one BFS:
 *     - Two towns have the same parity  <=>  distance between them is EVEN ("Town").
 *     - Two towns have opposite parity  <=>  distance between them is ODD  ("Road").
 * - This answers every query in O(1) time after a single O(N) preprocessing BFS.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N + Q) — O(N) for the single BFS, O(1) per query.
 * - Space Complexity: O(N) for the adjacency list, parity array, and BFS queue.
 */
public class Main {

    private static final int UNVISITED = -1;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int q = in.nextInt();

        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] parity = computeDepthParity(n, adj);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int c = in.nextInt();
            int d = in.nextInt();
            if (parity[c] == parity[d]) {
                out.append("Town\n");
            } else {
                out.append("Road\n");
            }
        }
        System.out.print(out);
    }

    private static int[] computeDepthParity(int n, List<Integer>[] adj) {
        int[] parity = new int[n + 1];
        Arrays.fill(parity, UNVISITED);

        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        parity[1] = 0;
        queue[tail++] = 1;

        while (head < tail) {
            int u = queue[head++];
            for (int v : adj[u]) {
                if (parity[v] == UNVISITED) {
                    parity[v] = parity[u] ^ 1;
                    queue[tail++] = v;
                }
            }
        }
        return parity;
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
