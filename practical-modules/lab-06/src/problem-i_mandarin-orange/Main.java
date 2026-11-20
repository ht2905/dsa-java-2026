import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem I: Mandarin Orange (mandarin-orange_v-judge.pdf)
 *
 * Problem Summary:
 * There are N dishes in a row; dish i has A_i oranges. Choose a contiguous
 * range [l, r] and an integer x <= min(A_l, ..., A_r) to eat x oranges from
 * each dish in [l, r], maximizing the total number of oranges eaten:
 * x * (r - l + 1).
 *
 * Design Decision:
 * - For any fixed range [l, r], the optimal choice of x is always the minimum
 *   value in that range: x = min(A_l, ..., A_r).
 * - Since N <= 10^4, there are N * (N + 1) / 2 <= 5 * 10^7 contiguous ranges.
 *   By fixing the left endpoint l and expanding r rightward while updating a
 *   running minimum `minOranges`, we evaluate every range in O(1) per step.
 * - Pedagogical Note: An O(N) solution using a monotonic stack (Largest
 *   Rectangle in a Histogram) exists if N were larger (e.g., 2 * 10^5), but
 *   the O(N^2) range-expansion approach is much simpler, avoids stack boundary
 *   bookkeeping, and runs well within the time limit for N <= 10^4.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N^2) to inspect all pairs (l, r) with 0 <= l <= r < N.
 * - Space Complexity: O(N) to store the dish counts.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        int[] dishes = new int[n];
        for (int i = 0; i < n; i++) {
            dishes[i] = in.nextInt();
        }

        long maxOranges = findMaxOranges(dishes, n);
        System.out.println(maxOranges);
    }

    private static long findMaxOranges(int[] dishes, int n) {
        long best = 0;

        for (int left = 0; left < n; left++) {
            int minInRange = dishes[left];
            for (int right = left; right < n; right++) {
                if (dishes[right] < minInRange) {
                    minInRange = dishes[right];
                }
                long total = (long) minInRange * (right - left + 1);
                if (total > best) {
                    best = total;
                }
            }
        }

        return best;
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
