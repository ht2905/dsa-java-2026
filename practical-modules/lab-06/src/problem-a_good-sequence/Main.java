import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem A: Good Sequence (good-sequence_v-judge.pdf)
 *
 * Problem Summary:
 * Given a sequence of N positive integers, find the minimum number of elements
 * to remove so that every remaining value x appears exactly x times.
 *
 * Design Decision:
 * - Each distinct value x can be evaluated independently because removing copies
 *   of x never changes the frequency of any other value.
 * - Since values can be as large as 10^9, we cannot allocate a frequency array
 *   indexed by value. Instead, we use a HashMap<Integer, Integer> to count how
 *   many times each distinct value appears.
 * - For a value x occurring `count` times:
 *   - If count < x: we can never reach x occurrences by only removing elements,
 *     so all `count` copies must be removed.
 *   - If count >= x: we keep exactly x copies and remove the excess (count - x).
 *
 * Complexity Analysis:
 * - Time Complexity: O(N) expected time to count frequencies and iterate over
 *   the distinct keys in the HashMap.
 * - Space Complexity: O(N) in the worst case (when all N elements are distinct)
 *   to store the frequency map.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        int removals = countMinimumRemovals(freq);
        System.out.println(removals);
    }

    private static int countMinimumRemovals(Map<Integer, Integer> freq) {
        int removals = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (count < value) {
                removals += count;
            } else {
                removals += count - value;
            }
        }
        return removals;
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
