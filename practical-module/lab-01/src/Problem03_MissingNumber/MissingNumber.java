package Problem03_MissingNumber;

import java.util.Arrays;

/**
 * Problem 3: Find the Missing Number
 * Finds the single missing integer from the inclusive range [0, n]
 * in an array of length n containing distinct integers.
 */
public class MissingNumber {

    // -------------------------------------------------------------
    // Approach A: Repeated Membership Search
    // -------------------------------------------------------------

    /**
     * Finds the missing value by testing candidate integers 0 through n.
     * For each candidate, linearly searches arr from the beginning.
     *
     * @param arr the input array of length n
     * @return the missing integer in the range [0, n]
     */
    public static int findMissingRepeatedSearch(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // Test candidate values 0, 1, ..., n in ascending order
        for (int candidate = 0; candidate <= n; candidate++) {
            boolean found = false;

            // Search the original array from the beginning
            for (int i = 0; i < n; i++) {
                if (arr[i] == candidate) {
                    found = true;
                    break; // Match found; advance to next candidate
                }
            }

            // The first candidate not found in arr is the missing value
            if (!found) {
                return candidate;
            }
        }

        return 0; // Fallback per problem contract
    }

    // -------------------------------------------------------------
    // Approach B: Presence Array
    // -------------------------------------------------------------

    /**
     * Finds the missing value using a boolean presence lookup array.
     * Marks seen elements, then scans to identify the unmarked index.
     *
     * @param arr the input array of length n
     * @return the missing integer in the range [0, n]
     */
    public static int findMissingPresenceArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // Allocation and default initialization to false
        boolean[] present = new boolean[n + 1];

        // Mark values present in the input array
        for (int i = 0; i < n; i++) {
            present[arr[i]] = true;
        }

        // Scan marks to locate the single unvisited index
        for (int candidate = 0; candidate <= n; candidate++) {
            if (!present[candidate]) {
                return candidate;
            }
        }

        return 0; // Fallback per problem contract
    }

    // -------------------------------------------------------------
    // Test Driver & Verification
    // -------------------------------------------------------------

    public static void main(String[] args) {
        int[][] testCases = {
            {3, 0, 4, 1},                      // Normal Case (from specification)
            {},                                // Boundary Case: Empty array
            {0},                               // Boundary Case: Single element, missing 1
            {1},                               // Boundary Case: Single element, missing 0
            {0, 1, 2, 3},                      // Boundary Case: Missing n at the upper bound
            {4, 2, 1, 3},                      // Tricky Case: Missing 0 at the lower bound
            {3, 2, 1, 0}                       // Tricky/Adversarial Case: Reversed order, missing n
        };

        int[] expectedOutputs = {2, 0, 1, 0, 4, 0, 4};

        System.out.printf("%-5s | %-24s | %-10s | %-12s | %-12s | %-6s%n",
                "Test", "Input Array", "Expected", "Approach A", "Approach B", "Status");
        System.out.println("----------------------------------------------------------------------------------");

        for (int t = 0; t < testCases.length; t++) {
            int[] arr = testCases[t];
            int expected = expectedOutputs[t];

            int actualA = findMissingRepeatedSearch(arr);
            int actualB = findMissingPresenceArray(arr);

            boolean passed = (actualA == expected) && (actualB == expected);

            System.out.printf("%-5d | %-24s | %-10d | %-12d | %-12d | %-6s%n",
                    (t + 1),
                    Arrays.toString(arr),
                    expected,
                    actualA,
                    actualB,
                    (passed ? "PASS" : "FAIL"));
        }
    }
}