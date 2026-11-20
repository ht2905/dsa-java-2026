import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem C: Colorful Candies (colorful-candles_v-judge.pdf)
 *
 * Problem Summary:
 * Given N candies in a row, each with a color c_i (up to 10^9), choose K
 * consecutive candies to maximize the number of distinct colors.
 *
 * Design Decision:
 * - We use a fixed-size Sliding Window of length K combined with a HashMap that
 *   tracks the frequency of each color currently inside the window.
 * - When a color's frequency drops to 0 as it slides out of the left side, we
 *   remove its key from the HashMap so that `colorCounts.size()` always equals
 *   the exact number of distinct colors in the current window.
 * - Because c_i <= 10^9, a HashMap is used instead of a direct-indexed array.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) expected time, as each candy enters and leaves the
 *   sliding window at most once with O(1) expected HashMap updates.
 * - Space Complexity: O(N) for storing the candy array and up to K distinct
 *   entries in the frequency map.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int k = in.nextInt();

        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = in.nextInt();
        }

        int maxDistinct = findMaxDistinctColors(colors, n, k);
        System.out.println(maxDistinct);
    }

    private static int findMaxDistinctColors(int[] colors, int n, int k) {
        Map<Integer, Integer> windowCounts = new HashMap<>();

        // Build the initial window of size K.
        for (int i = 0; i < k; i++) {
            int color = colors[i];
            windowCounts.put(color, windowCounts.getOrDefault(color, 0) + 1);
        }

        int best = windowCounts.size();

        // Slide the window one candy to the right at a time.
        for (int right = k; right < n; right++) {
            int entering = colors[right];
            windowCounts.put(entering, windowCounts.getOrDefault(entering, 0) + 1);

            int leaving = colors[right - k];
            int remaining = windowCounts.get(leaving) - 1;
            if (remaining == 0) {
                windowCounts.remove(leaving);
            } else {
                windowCounts.put(leaving, remaining);
            }

            if (windowCounts.size() > best) {
                best = windowCounts.size();
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
