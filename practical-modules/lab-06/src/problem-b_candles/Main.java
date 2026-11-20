import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem B: Candles (candles_v-judge.pdf)
 *
 * Problem Summary:
 * There are N candles at strictly increasing coordinates x_1 < x_2 < ... < x_N.
 * Starting at coordinate 0 with speed 1, find the minimum time needed to light
 * any K candles.
 *
 * Design Decision:
 * - Any optimal choice of K candles must form a contiguous subarray of length K
 *   in the sorted coordinate array. Any path visiting a leftmost candle L and a
 *   rightmost candle R passes through every candle between L and R anyway, so
 *   skipping an intermediate candle can never reduce travel time.
 * - We slide a fixed-size window [i, i + K - 1] across all N - K + 1 windows.
 * - To cover all candles from `left` to `right`, Snuke must traverse the span
 *   (right - left) plus the initial distance from 0 to whichever endpoint he
 *   visits first:
 *   time = min(|left|, |right|) + (right - left).
 *   This single formula holds whether both endpoints are negative, both are
 *   positive, or they straddle 0.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) to read the coordinates and evaluate all N - K + 1
 *   contiguous windows in a single pass.
 * - Space Complexity: O(N) to store the candle coordinates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int k = in.nextInt();

        long[] x = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextLong();
        }

        long answer = findMinimumLightingTime(x, n, k);
        System.out.println(answer);
    }

    private static long findMinimumLightingTime(long[] x, int n, int k) {
        long minTime = Long.MAX_VALUE;

        for (int i = 0; i + k - 1 < n; i++) {
            long left = x[i];
            long right = x[i + k - 1];

            // Visit the endpoint closer to 0 first, then walk across [left, right].
            long distToCloserEnd = Math.min(Math.abs(left), Math.abs(right));
            long span = right - left;
            long totalTime = distToCloserEnd + span;

            if (totalTime < minTime) {
                minTime = totalTime;
            }
        }

        return minTime;
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
