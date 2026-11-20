import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem D: Transportation Expenses (transportation-expenses_v-judge.pdf)
 *
 * Problem Summary:
 * There are N people with transportation costs A_i, and a total budget M.
 * Given a subsidy cap x >= 0, person i receives min(x, A_i). Find the maximum
 * integer x such that the sum of subsidies is at most M, or print "infinite".
 *
 * Design Decision:
 * - First check if the sum of all A_i is already <= M. If so, even setting
 *   x arbitrarily large will never exceed M, so the answer is "infinite".
 * - Otherwise, the total subsidy f(x) = sum(min(x, A_i)) is monotonically
 *   non-decreasing in x, and the maximum valid x lies in [0, max(A_i) - 1].
 * - Because f(x) is monotonic, we apply Binary Search on the Answer over
 *   [0, maxCost] instead of sorting or using prefix sums, keeping the logic
 *   direct and easy to verify.
 * - Use 64-bit `long` for sums and budget M because M <= 2 * 10^14 overflows `int`.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log(max A_i)), where max A_i <= 10^9 (~30 binary
 *   search steps, each taking an O(N) pass).
 * - Space Complexity: O(N) to store the transportation costs.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        long budget = in.nextLong();

        long[] costs = new long[n];
        long totalCost = 0;
        long maxCost = 0;
        for (int i = 0; i < n; i++) {
            costs[i] = in.nextLong();
            totalCost += costs[i];
            if (costs[i] > maxCost) {
                maxCost = costs[i];
            }
        }

        if (totalCost <= budget) {
            System.out.println("infinite");
            return;
        }

        long maxLimit = findMaxSubsidyLimit(costs, budget, maxCost);
        System.out.println(maxLimit);
    }

    private static long findMaxSubsidyLimit(long[] costs, long budget, long maxCost) {
        long low = 0;
        long high = maxCost;
        long best = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (isWithinBudget(costs, mid, budget)) {
                best = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return best;
    }

    private static boolean isWithinBudget(long[] costs, long limit, long budget) {
        long totalSubsidy = 0;
        for (long cost : costs) {
            totalSubsidy += Math.min(limit, cost);
            if (totalSubsidy > budget) {
                return false;
            }
        }
        return true;
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
