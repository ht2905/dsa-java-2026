import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem H: Destroyer Takahashi (destroyer-takahashi_v-judge.pdf)
 *
 * Problem Summary:
 * There are N horizontal walls; wall i spans columns [L_i, R_i]. One punch
 * spans D consecutive columns [x, x + D - 1] and destroys every wall that
 * overlaps with that range. Find the minimum number of punches needed.
 *
 * Design Decision:
 * - This is a Greedy Interval Covering / Stabbing problem (analogous to
 *   CLRS Activity Selection sorted by finish time).
 * - Sort all walls by their right endpoint R_i in ascending order.
 * - Look at the first remaining undestroyed wall [L_i, R_i]. Any valid punch
 *   that destroys it must cover some column <= R_i. To overlap as many future
 *   walls (all having R_j >= R_i) as possible, we place the punch as far to
 *   the right as possible while still hitting R_i: the range [R_i, R_i + D - 1].
 * - Every subsequent wall whose left endpoint satisfies L_j <= punchEnd is
 *   already destroyed by that punch (since R_j >= R_i is guaranteed by sorting).
 * - Whenever L_j > punchEnd, we need a new punch starting at R_j.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N) to sort the N walls by right endpoint, followed
 *   by an O(N) greedy pass.
 * - Space Complexity: O(N) to store the wall intervals.
 */
public class Main {

    static class Wall implements Comparable<Wall> {
        final long left;
        final long right;

        Wall(long left, long right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Wall other) {
            return Long.compare(this.right, other.right);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        long punchWidth = in.nextLong();

        Wall[] walls = new Wall[n];
        for (int i = 0; i < n; i++) {
            long left = in.nextLong();
            long right = in.nextLong();
            walls[i] = new Wall(left, right);
        }

        Arrays.sort(walls);

        int punches = countMinimumPunches(walls, punchWidth);
        System.out.println(punches);
    }

    private static int countMinimumPunches(Wall[] walls, long punchWidth) {
        int punches = 0;
        long punchEnd = -1;

        for (Wall wall : walls) {
            if (wall.left > punchEnd) {
                punches++;
                punchEnd = wall.right + punchWidth - 1;
            }
        }

        return punches;
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
