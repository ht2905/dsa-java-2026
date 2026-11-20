import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem F: Water Heater (water-heater_v-judge.pdf)
 *
 * Problem Summary:
 * A water heater supplies W liters of hot water per minute. N people each
 * request P_i liters per minute during the half-open time interval [S_i, T_i),
 * where 0 <= S_i < T_i <= 2 * 10^5. Determine whether the heater can satisfy
 * everyone's plan simultaneously.
 *
 * Design Decision:
 * - Because all start and end times are small integers (at most 2 * 10^5), we
 *   can represent minute-by-minute changes in demand using a Difference Array
 *   instead of sorting interval events.
 * - For each person requesting P_i liters on [S_i, T_i):
 *   - Add P_i at diff[S_i] (demand starts at minute S_i).
 *   - Subtract P_i at diff[T_i] (demand stops right before minute T_i).
 * - Taking the prefix sum of `diff` from t = 0 to MAX_TIME recovers the exact
 *   water demand at every minute. If demand ever exceeds W, print "No".
 * - Use `long` for the difference array and running demand because up to
 *   2 * 10^5 people with P_i <= 10^9 can sum to 2 * 10^14, overflowing `int`.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N + T_max), where T_max = 2 * 10^5.
 * - Space Complexity: O(T_max) for the difference array.
 */
public class Main {

    private static final int MAX_TIME = 200_000;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        long capacityPerMinute = in.nextLong();

        long[] diff = new long[MAX_TIME + 1];
        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            long demand = in.nextLong();

            diff[start] += demand;
            diff[end] -= demand;
        }

        if (canSupplyAll(diff, capacityPerMinute)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static boolean canSupplyAll(long[] diff, long capacityPerMinute) {
        long currentDemand = 0;
        for (int t = 0; t <= MAX_TIME; t++) {
            currentDemand += diff[t];
            if (currentDemand > capacityPerMinute) {
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
