package Problem04_FirstUniquePosition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem 4: Find the First Unique Position
 * Identifies the smallest index whose value occurs exactly once in the entire array.
 */
public class FirstUniquePosition {

    // -------------------------------------------------------------
    // Approach A: Repeated Counting
    // -------------------------------------------------------------

    /**
     * Finds the first unique position by counting occurrences of each candidate
     * position across the entire array.
     *
     * @param arr the input array of integers
     * @return the smallest index of an element occurring exactly once, or -1 if none exists
     */
    public static int firstUniquePositionRepeatedCounting(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int n = arr.length;

        // Visit candidate positions from left to right
        for (int i = 0; i < n; i++) {
            int count = 0;

            // Scan the entire array to count occurrences of arr[i]
            for (int j = 0; j < n; j++) {
                if (arr[j] == arr[i]) {
                    count++;
                }
            }

            // Return the first index whose value occurs exactly once
            if (count == 1) {
                return i;
            }
        }

        return -1;
    }

    // -------------------------------------------------------------
    // Approach B: Frequency Map and Ordered Scan
    // -------------------------------------------------------------

    /**
     * Finds the first unique position using a frequency map followed by
     * an ordered scan over the input array.
     *
     * @param arr the input array of integers
     * @return the smallest index of an element occurring exactly once, or -1 if none exists
     */
    public static int firstUniquePositionHashMap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Pass 1: Build frequency map of every value
        for (int i = 0; i < n; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }

        // Pass 2: Scan array from left to right to preserve minimal index order
        for (int i = 0; i < n; i++) {
            if (freqMap.get(arr[i]) == 1) {
                return i;
            }
        }

        return -1;
    }

    // -------------------------------------------------------------
    // Test Driver & Verification
    // -------------------------------------------------------------

    public static void main(String[] args) {
        int[][] testCases = {
            {6, 2, 6, 4, 2, 9, 4},             // Normal Case (from specification)
            {},                                // Boundary Case: Empty array
            {42},                              // Boundary Case: Single element
            {7, 7, 7, 7},                      // Boundary Case: No unique element (all identical)
            {-1, 5, -1, 3, 5},                 // Tricky Case: Data value contains -1
            {10, 20, 30, 40},                  // Tricky Case: All elements distinct (early return at 0)
            {1, 1, 2, 2, 3, 3, 4}              // Adversarial Case: Unique element at final position
        };

        int[] expectedOutputs = {5, -1, 0, -1, 3, 0, 6};

        System.out.printf("%-5s | %-28s | %-10s | %-12s | %-12s | %-6s%n",
                "Test", "Input Array", "Expected", "Approach A", "Approach B", "Status");
        System.out.println("--------------------------------------------------------------------------------------");

        for (int t = 0; t < testCases.length; t++) {
            int[] arr = testCases[t];
            int expected = expectedOutputs[t];

            int actualA = firstUniquePositionRepeatedCounting(arr);
            int actualB = firstUniquePositionHashMap(arr);

            boolean passed = (actualA == expected) && (actualB == expected);

            System.out.printf("%-5d | %-28s | %-10d | %-12d | %-12d | %-6s%n",
                    (t + 1),
                    Arrays.toString(arr),
                    expected,
                    actualA,
                    actualB,
                    (passed ? "PASS" : "FAIL"));
        }
    }
}