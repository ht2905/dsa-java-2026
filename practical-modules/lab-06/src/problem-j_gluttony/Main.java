import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem J: Gluttony (gluttony_v-judge.pdf)
 *
 * Problem Summary:
 * There are N team members with consumption coefficients A_i and N foods with
 * difficulties F_i. Each member is assigned one distinct food, taking A'_i * F_j
 * seconds, where A'_i >= 0 is member i's coefficient after training. Up to K
 * total training sets can be performed (each reduces some A_i by 1). Find the
 * minimum possible team score (maximum time taken by any member).
 *
 * Design Decision:
 * - Optimal Pairing (Greedy / Rearrangement):
 *   To minimize the maximum product (and equally, to minimize total training
 *   needed to keep every product <= targetScore), pair larger A_i with smaller
 *   F_j. Sorting A ascending and F descending achieves this optimal pairing.
 * - Binary Search on the Answer:
 *   If a team score `limit` is achievable using <= K training sets, then any
 *   larger score is also achievable. This monotonicity lets us binary search
 *   for the smallest feasible `limit` in [0, max(A) * max(F)].
 * - Feasibility Check for a fixed `limit`:
 *   For pair i, we need A'_i * F_i <= limit, meaning A'_i <= limit / F_i.
 *   If A_i > limit / F_i, member i needs exactly (A_i - limit / F_i) training
 *   sets. Summing this across all i tells us the minimum total training needed.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N + N log(max(A) * max(F))), where sorting takes
 *   O(N log N) and binary search runs ~40 iterations over [0, 10^12].
 * - Space Complexity: O(N) to store arrays A and F.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        long maxTraining = in.nextLong();

        long[] coeffs = new long[n];
        for (int i = 0; i < n; i++) {
            coeffs[i] = in.nextLong();
        }

        long[] difficulties = new long[n];
        for (int i = 0; i < n; i++) {
            difficulties[i] = in.nextLong();
        }

        // Pair smallest consumption coefficients with largest food difficulties.
        Arrays.sort(coeffs);
        Arrays.sort(difficulties);
        reverse(difficulties);

        long minScore = findMinimumTeamScore(coeffs, difficulties, maxTraining);
        System.out.println(minScore);
    }

    private static long findMinimumTeamScore(long[] coeffs, long[] difficulties, long maxTraining) {
        long low = 0;
        long high = coeffs[coeffs.length - 1] * difficulties[0];
        long best = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canAchieveScore(coeffs, difficulties, mid, maxTraining)) {
                best = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return best;
    }

    private static boolean canAchieveScore(long[] coeffs, long[] difficulties,
                                           long targetScore, long maxTraining) {
        long trainingNeeded = 0;
        for (int i = 0; i < coeffs.length; i++) {
            long allowedCoeff = targetScore / difficulties[i];
            if (coeffs[i] > allowedCoeff) {
                trainingNeeded += coeffs[i] - allowedCoeff;
                if (trainingNeeded > maxTraining) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void reverse(long[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            long temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
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
