package Problem05_AllSubarraySums;

import java.util.Arrays;

/**
 * Problem 5: Sum of All Subarray Sums
 * Calculates the sum of the sums of all nonempty contiguous subarrays
 * for an integer array of length 0 <= n <= 10,000.
 */
public class AllSubarraySums {

    // -------------------------------------------------------------
    // Approach A: Nested-Loop Accumulation
    // -------------------------------------------------------------

    /**
     * Computes the total sum of all contiguous subarray sums by fixing
     * a starting index and incrementally extending the ending index.
     *
     * @param arr the input array of integers
     * @return the sum of all subarray sums as a long
     */
    public static long sumAllSubarraySumsNested(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0L;
        }

        int n = arr.length;
        long totalSum = 0L;

        // Fix starting index s
        for (int s = 0; s < n; s++) {
            long currentSubarraySum = 0L;

            // Incrementally extend ending index e
            for (int e = s; e < n; e++) {
                currentSubarraySum += arr[e];
                totalSum += currentSubarraySum;
            }
        }

        return totalSum;
    }

    // -------------------------------------------------------------
    // Approach B: Element Contributions
    // -------------------------------------------------------------

    /**
     * Computes the total sum of all contiguous subarray sums by calculating
     * the exact combinatorial contribution of each element in a single pass.
     *
     * @param arr the input array of integers
     * @return the sum of all subarray sums as a long
     */
    public static long sumAllSubarraySumsContribution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0L;
        }

        int n = arr.length;
        long totalSum = 0L;

        // Traverse each position and compute its total frequency across all subarrays
        for (int i = 0; i < n; i++) {
            // Count of valid starting indices s in [0, i]
            long startChoices = i + 1;
            // Count of valid ending indices e in [i, n - 1]
            long endChoices = n - i;

            // Number of subarrays containing arr[i]
            long occurrences = startChoices * endChoices;

            // Promote operand to long before multiplication to prevent 32-bit overflow
            long elementContribution = occurrences * (long) arr[i];

            totalSum += elementContribution;
        }

        return totalSum;
    }

    // -------------------------------------------------------------
    // Test Driver & Verification
    // -------------------------------------------------------------

    public static void main(String[] args) {
        int[][] testCases = {
            {2, -1, 3},                        // Normal Case (from specification)
            {},                                // Boundary Case: Empty array
            {42},                              // Boundary Case: Single positive element
            {-5},                              // Boundary Case: Single negative element
            {0, 0, 0},                         // Tricky Case: All zeros
            {-2, 0, 5, -1},                    // Tricky Case: Mixed positives, negatives, and zero
            {1000000, 1000000, 1000000}        // Tricky Case: Large magnitude values
        };

        long[] expectedOutputs = {
            11L,
            0L,
            42L,
            -5L,
            0L,
            18L,
            10000000L
        };

        System.out.printf("%-5s | %-28s | %-12s | %-12s | %-12s | %-6s%n",
                "Test", "Input Array", "Expected", "Approach A", "Approach B", "Status");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int t = 0; t < testCases.length; t++) {
            int[] arr = testCases[t];
            long expected = expectedOutputs[t];

            long actualA = sumAllSubarraySumsNested(arr);
            long actualB = sumAllSubarraySumsContribution(arr);

            boolean passed = (actualA == expected) && (actualB == expected);

            System.out.printf("%-5d | %-28s | %-12d | %-12d | %-12d | %-6s%n",
                    (t + 1),
                    Arrays.toString(arr),
                    expected,
                    actualA,
                    actualB,
                    (passed ? "PASS" : "FAIL"));
        }
    }
}