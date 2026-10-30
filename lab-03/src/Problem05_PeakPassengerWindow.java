import java.util.ArrayDeque;
import java.util.Deque;

public class Problem05_PeakPassengerWindow {

    // Approach A: Direct k-item scan (baseline / answer checker)
    public static int[] maxSlidingWindowDirect(int[] a, int k) {
        if (a == null || a.length == 0 || k < 1 || k > a.length) {
            return new int[0];
        }
        int n = a.length;
        int numWindows = n - k + 1;
        int[] result = new int[numWindows];

        for (int i = 0; i < numWindows; i++) {
            int maxVal = a[i];
            for (int j = i + 1; j < i + k; j++) {
                if (a[j] > maxVal) {
                    maxVal = a[j];
                }
            }
            result[i] = maxVal;
        }
        return result;
    }

    // Approach B: Monotonic deque (storing indices in descending order of value)
    public static int[] maxSlidingWindowDeque(int[] a, int k, boolean printTrace) {
        if (a == null || a.length == 0 || k < 1 || k > a.length) {
            return new int[0];
        }
        int n = a.length;
        int numWindows = n - k + 1;
        int[] result = new int[numWindows];
        Deque<Integer> deque = new ArrayDeque<>();

        if (printTrace) {
            System.out.println("  Trace (i | a[i] | Deque Indices | Deque Values | Window Peak):");
        }

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are outside the current window [i - k + 1 .. i]
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. Remove indices whose values are <= current value a[i] (they are dominated)
            while (!deque.isEmpty() && a[deque.peekLast()] <= a[i]) {
                deque.pollLast();
            }

            // 3. Add current index to the back of the deque
            deque.addLast(i);

            // 4. If current index completes at least one window of length k, record max
            if (i >= k - 1) {
                int peak = a[deque.peekFirst()];
                result[i - k + 1] = peak;

                if (printTrace) {
                    System.out.printf("    %-2d | %-4d | %-14s | %-12s | %d%n",
                            i, a[i], dequeIndicesToString(deque), dequeValuesToString(deque, a), peak);
                }
            } else if (printTrace) {
                System.out.printf("    %-2d | %-4d | %-14s | %-12s | (incomplete)%n",
                        i, a[i], dequeIndicesToString(deque), dequeValuesToString(deque, a));
            }
        }

        return result;
    }

    private static String dequeIndicesToString(Deque<Integer> deque) {
        return deque.toString();
    }

    private static String dequeValuesToString(Deque<Integer> deque, int[] a) {
        StringBuilder sb = new StringBuilder("[");
        int count = 0;
        for (int idx : deque) {
            sb.append(a[idx]);
            if (++count < deque.size()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String arrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    private static void runTest(String label, int[] a, int k, boolean trace) {
        System.out.println("=== Test Case: " + label + " (k = " + k + ") ===");
        System.out.println("Counts: " + arrayToString(a));

        int[] directRes = maxSlidingWindowDirect(a, k);
        int[] dequeRes = maxSlidingWindowDeque(a, k, trace);

        // Verify that direct scan and deque match identically
        boolean match = true;
        if (directRes.length != dequeRes.length) match = false;
        else {
            for (int i = 0; i < directRes.length; i++) {
                if (directRes[i] != dequeRes[i]) { match = false; break; }
            }
        }

        System.out.println("Direct Scan  : " + arrayToString(directRes));
        System.out.println("Deque Output : " + arrayToString(dequeRes));
        System.out.println("Verification : " + (match ? "PASSED (Matches Direct Scan)" : "FAILED (Mismatch)"));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example (Traced)
        int[] example = {9, 1, 4, 7, 3, 5, 2};
        runTest("Normal - Handout Worked Example (Traced)", example, 3, true);

        // 2. Boundary: Window size k = 1
        runTest("Boundary - k = 1 (Every element is its own maximum)", example, 1, false);

        // 3. Boundary: Window size k = n
        runTest("Boundary - k = n (Single maximum of full array)", example, example.length, false);

        // 4. Tricky: Repeated equal maxima
        int[] repeatedMaxima = {7, 7, 7, 7, 7};
        runTest("Tricky - Repeated Equal Maxima", repeatedMaxima, 3, false);

        // 5. Tricky: All-negative values
        int[] allNegatives = {-10, -50, -20, -5, -40, -15};
        runTest("Tricky - All-Negative Passenger Counts", allNegatives, 3, false);

        // 6. Tricky: Previously maximal value expiring
        int[] expiringMax = {100, 2, 3, 4, 5};
        runTest("Tricky - Maximum at Leftmost Index Expiring", expiringMax, 3, false);
    }
}

