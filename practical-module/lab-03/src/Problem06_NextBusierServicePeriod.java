import java.util.ArrayDeque;
import java.util.Deque;

public class Problem06_NextBusierServicePeriod {

    // Monotonic stack approach to find the distance to the next strictly greater element
    public static int[] nextBusierPeriod(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int n = a.length;
        int[] result = new int[n]; // Default initialized to 0
        Deque<Integer> stack = new ArrayDeque<>(); // Stores unresolved indices

        for (int i = 0; i < n; i++) {
            // While the current value is strictly greater than the value at the top index
            while (!stack.isEmpty() && a[i] > a[stack.peek()]) {
                int prevIdx = stack.pop();
                result[prevIdx] = i - prevIdx;
            }
            // Push current index as an unresolved candidate
            stack.push(i);
        }

        // Any indices remaining in stack have no strictly greater future period; they remain 0
        return result;
    }

    // Direct O(n^2) approach for baseline comparison and verification
    public static int[] nextBusierPeriodDirect(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        int n = a.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] > a[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    private static String arrayToString(int[] a) {
        if (a.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    private static void runTest(String label, int[] a) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Counts: " + arrayToString(a));

        int[] stackRes = nextBusierPeriod(a);
        int[] directRes = nextBusierPeriodDirect(a);

        boolean match = true;
        if (stackRes.length != directRes.length) match = false;
        else {
            for (int i = 0; i < stackRes.length; i++) {
                if (stackRes[i] != directRes[i]) { match = false; break; }
            }
        }

        System.out.println("Stack Output : " + (stackRes.length == 0 ? "(blank line)" : arrayToString(stackRes)));
        System.out.println("Direct Check : " + (directRes.length == 0 ? "(blank line)" : arrayToString(directRes)));
        System.out.println("Verification : " + (match ? "PASSED" : "FAILED"));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example
        runTest("Normal - Handout Worked Example", new int[]{6, 9, 8, 10, 9});

        // 2. Boundary: Empty array (n = 0)
        runTest("Boundary - Empty Array (n = 0)", new int[]{});

        // 3. Boundary: Single element (n = 1)
        runTest("Boundary - Single Element", new int[]{42});

        // 4. Tricky: All-equal values (equality does NOT qualify as strictly greater)
        runTest("Tricky - All Equal Values (Equal values do not qualify)", new int[]{5, 5, 5, 5, 5});

        // 5. Normal: Strictly increasing sequence (each element resolves at next index)
        runTest("Normal - Strictly Increasing Sequence", new int[]{1, 2, 3, 4, 5});

        // 6. Normal: Strictly decreasing sequence (no element ever resolves)
        runTest("Normal - Strictly Decreasing Sequence", new int[]{5, 4, 3, 2, 1});

        // 7. Tricky: Negative and signed values
        runTest("Tricky - Signed and Negative Counts", new int[]{-10, -5, -20, -15, 0, -1});
    }
}

