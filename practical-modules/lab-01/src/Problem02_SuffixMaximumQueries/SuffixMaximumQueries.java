package Problem02_SuffixMaximumQueries;

import java.util.Arrays;

/**
 * Problem 2: Suffix Maximum Queries
 * Computes the maximum value in array suffixes arr[p ... n-1] for query indices p.
 */
public class SuffixMaximumQueries {

    // -------------------------------------------------------------
    // Approach A: Direct Suffix Scan
    // -------------------------------------------------------------

    /**
     * Answers a single suffix maximum query by scanning arr from index p to n - 1.
     *
     * @param arr the input array
     * @param p   the starting index of the suffix (0 <= p < arr.length)
     * @return the maximum value in arr[p ... arr.length - 1]
     */
    public static int queryDirect(int[] arr, int p) {
        if (arr == null || arr.length == 0 || p < 0 || p >= arr.length) {
            throw new IllegalArgumentException("Invalid query index or empty array.");
        }

        // Initialize with the first element of the suffix to support all-negative values
        int maxVal = arr[p];
        for (int i = p + 1; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        return maxVal;
    }

    /**
     * Executes queries one at a time in query order using Approach A.
     */
    public static void executeQueriesDirect(int[] arr, int[] queries) {
        if (queries == null || queries.length == 0) {
            return;
        }
        for (int p : queries) {
            int ans = queryDirect(arr, p);
            System.out.print(ans + " ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    // Approach B: Suffix-Maximum Pre-processing
    // -------------------------------------------------------------

    /**
     * Precomputes suffix maxima in a single right-to-left pass.
     * suffixMax[i] stores the maximum value in arr[i ... n - 1].
     *
     * @param arr the input array
     * @return precomputed suffix maximum array of length arr.length
     */
    public static int[] preprocessSuffixMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int[] suffixMax = new int[n];

        // Base case: the suffix starting at index n - 1 contains only arr[n - 1]
        suffixMax[n - 1] = arr[n - 1];

        // Optimal substructure: suffixMax[i] = max(arr[i], suffixMax[i + 1])
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(arr[i], suffixMax[i + 1]);
        }

        return suffixMax;
    }

    /**
     * Answers a single suffix query in O(1) time using the precomputed array.
     *
     * @param suffixMax precomputed suffix maxima array
     * @param p         query index (0 <= p < suffixMax.length)
     * @return the maximum value in the suffix
     */
    public static int queryPreprocessed(int[] suffixMax, int p) {
        if (suffixMax == null || suffixMax.length == 0 || p < 0 || p >= suffixMax.length) {
            throw new IllegalArgumentException("Invalid query index or empty array.");
        }
        return suffixMax[p];
    }

    /**
     * Executes queries one at a time in query order using Approach B.
     */
    public static void executeQueriesPreprocessed(int[] suffixMax, int[] queries) {
        if (queries == null || queries.length == 0) {
            return;
        }
        for (int p : queries) {
            int ans = queryPreprocessed(suffixMax, p);
            System.out.print(ans + " ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    // Test Driver & Verification
    // -------------------------------------------------------------

    public static void main(String[] args) {
        int[][] testArrays = {
            {3, 8, -2, 6, 1},                  // Normal Case (from specification)
            {},                                // Boundary Case: Empty array
            {42},                              // Boundary Case: Single element
            {10, -5, 20, 4},                   // Boundary Case: Query at last index (p = n - 1)
            {-9, -2, -15, -4, -7},             // Tricky Case: All-negative array
            {100, 50, 25, 10, 5}               // Tricky Case: Strictly decreasing array
        };

        int[][] testQueries = {
            {0, 2, 4, 1},
            {},
            {0},
            {3},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4}
        };

        int[][] expectedOutputs = {
            {8, 6, 1, 8},
            {},
            {42},
            {4},
            {-2, -2, -4, -4, -7},
            {100, 50, 25, 10, 5}
        };

        System.out.printf("%-5s | %-22s | %-16s | %-20s | %-20s | %-6s%n",
                "Test", "Input Array", "Queries", "Approach A Actual", "Approach B Actual", "Status");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (int t = 0; t < testArrays.length; t++) {
            int[] arr = testArrays[t];
            int[] queries = testQueries[t];
            int[] expected = expectedOutputs[t];

            int[] actualA = new int[queries.length];
            int[] actualB = new int[queries.length];

            // Approach A
            for (int i = 0; i < queries.length; i++) {
                actualA[i] = queryDirect(arr, queries[i]);
            }

            // Approach B
            int[] suffixMax = preprocessSuffixMax(arr);
            for (int i = 0; i < queries.length; i++) {
                actualB[i] = queryPreprocessed(suffixMax, queries[i]);
            }

            boolean passedA = Arrays.equals(actualA, expected);
            boolean passedB = Arrays.equals(actualB, expected);
            boolean passed = passedA && passedB;

            System.out.printf("%-5d | %-22s | %-16s | %-20s | %-20s | %-6s%n",
                    (t + 1),
                    Arrays.toString(arr),
                    Arrays.toString(queries),
                    Arrays.toString(actualA),
                    Arrays.toString(actualB),
                    (passed ? "PASS" : "FAIL"));
        }
    }
}