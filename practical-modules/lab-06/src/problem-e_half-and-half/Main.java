import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem E: Half and Half (half-and-half_v-judge.pdf)
 *
 * Problem Summary:
 * A-pizzas cost A, B-pizzas cost B, and AB-pizzas cost C. Two AB-pizzas can
 * be rearranged into 1 A-pizza and 1 B-pizza. Find the minimum cost to obtain
 * at least X A-pizzas and Y B-pizzas (having extra pizzas is allowed).
 *
 * Design Decision:
 * - Rather than deriving error-prone closed-form casework (especially when
 *   buying extra AB-pizzas to cover only one remaining pizza type is cheaper),
 *   we use Complete Search (Brute-Force Enumeration) over the number of pairs
 *   of AB-pizzas bought.
 * - Let `pairs` be the number of AB-pizza pairs (so 2 * pairs AB-pizzas total,
 *   providing `pairs` A-pizzas and `pairs` B-pizzas).
 * - Buying more than max(X, Y) pairs can never be optimal, because at max(X, Y)
 *   pairs we already satisfy both X and Y.
 * - For each `pairs` in [0, max(X, Y)], the remaining A-pizzas and B-pizzas are
 *   bought directly at prices A and B, and we take the minimum total cost.
 *
 * Complexity Analysis:
 * - Time Complexity: O(max(X, Y)), with max(X, Y) <= 10^5, which is tiny and
 *   completely avoids edge-case bugs.
 * - Space Complexity: O(1) auxiliary space.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        long priceA = in.nextLong();
        long priceB = in.nextLong();
        long priceAB = in.nextLong();
        int needA = in.nextInt();
        int needB = in.nextInt();

        long minCost = findMinimumPizzaCost(priceA, priceB, priceAB, needA, needB);
        System.out.println(minCost);
    }

    private static long findMinimumPizzaCost(long priceA, long priceB, long priceAB,
                                              int needA, int needB) {
        long minCost = Long.MAX_VALUE;
        int maxPairs = Math.max(needA, needB);

        for (int pairs = 0; pairs <= maxPairs; pairs++) {
            int remainingA = Math.max(0, needA - pairs);
            int remainingB = Math.max(0, needB - pairs);

            long cost = 2L * pairs * priceAB
                    + remainingA * priceA
                    + remainingB * priceB;

            if (cost < minCost) {
                minCost = cost;
            }
        }

        return minCost;
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

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
