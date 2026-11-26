import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem H: Party (CodeForces 115A)
 *
 * Problem Summary:
 * Given n employees (1 to n) where each employee i has an immediate manager
 * p_i (or -1 if they have no manager) and there are no cycles, find the
 * minimum number of party groups so that no employee is in the same group as
 * any of their superiors (direct or indirect managers).
 *
 * Plain-English Explanation & Design Decisions:
 * - The company hierarchy forms a forest (one or more rooted trees), where
 *   each top-level boss (p_i == -1) is a root.
 * - Consider the longest chain of command from a top boss down to an employee,
 *   say of length D. Every person in this chain is a superior of everyone below
 *   them, so all D people must go into D different groups. Therefore, we need
 *   at least D groups.
 * - Conversely, D groups are always enough: assign every employee to the group
 *   numbered by their level (depth) in the hierarchy (top bosses in Group 1,
 *   their direct reports in Group 2, and so on). Two people on the same level
 *   can never be superior to one another.
 * - So the problem reduces to finding the maximum depth (chain length) over
 *   all n employees.
 * - For each employee i, we simply walk upward through `manager[curr]` until
 *   reaching -1 and count the number of people in the chain. Since n <= 2000,
 *   this direct walk is completely readable, needs no recursion or adjacency
 *   list, and finishes in a few milliseconds. (Memoizing depth[i] would reduce
 *   worst-case time from O(n^2) to O(n) if n were much larger.)
 *
 * Complexity Analysis:
 * - Time Complexity: O(n^2) in the worst case (a single chain of n employees),
 *   at most ~2,000,000 simple array lookups for n <= 2000.
 * - Space Complexity: O(n) to store the manager array.
 */
public class Main {

    private static final int NO_MANAGER = -1;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        int[] manager = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            manager[i] = in.nextInt();
        }

        int maxDepth = 0;
        for (int i = 1; i <= n; i++) {
            int depth = chainLength(i, manager);
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }

        System.out.println(maxDepth);
    }

    /**
     * Counts how many employees are on the chain from employee `employee`
     * up to their top-level boss (inclusive).
     */
    private static int chainLength(int employee, int[] manager) {
        int depth = 0;
        int current = employee;
        while (current != NO_MANAGER) {
            depth++;
            current = manager[current];
        }
        return depth;
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
