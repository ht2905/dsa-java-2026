package Problem06_InterleaveHalves;

import java.util.Arrays;

/**
 * Problem 6: Interleave Two Array Halves
 * Interleaves the first half [x1, ..., xm] and second half [y1, ..., ym]
 * of an even-length array (n = 2m) into [x1, y1, ..., xm, ym] in-place.
 */
public class InterleaveHalves {

    // -------------------------------------------------------------
    // Approach A: Auxiliary Array
    // -------------------------------------------------------------

    /**
     * Interleaves array halves using an auxiliary array and copies back.
     *
     * @param arr input array of even length n = 2m (modified in-place)
     */
    public static void interleaveAuxiliary(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return; // Lengths 0 and 2 are already correctly arranged
        }

        int n = arr.length;
        int m = n / 2;
        int[] aux = new int[n];

        // Pass 1: Alternately fill auxiliary array from first and second halves
        for (int i = 0; i < m; i++) {
            aux[2 * i] = arr[i];         // x_{i+1} placed at even index
            aux[2 * i + 1] = arr[m + i]; // y_{i+1} placed at odd index
        }

        // Pass 2: Copy the interleaved result back into the original array
        System.arraycopy(aux, 0, arr, 0, n);
    }

    // -------------------------------------------------------------
    // Approach B: In-Place Ordered Insertions
    // -------------------------------------------------------------

    /**
     * Interleaves array halves in-place by repeatedly shifting elements right
     * to insert second-half elements after their corresponding first-half elements.
     *
     * @param arr input array of even length n = 2m (modified in-place)
     */
    public static void interleaveInPlace(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return; // Lengths 0 and 2 are already correctly arranged
        }

        int n = arr.length;
        int m = n / 2;

        // Perform m - 1 insertions (the final pair x_m, y_m lands in place automatically)
        for (int k = 0; k < m - 1; k++) {
            int target = 2 * k + 1; // Destination index directly after x_{k+1}
            int source = m + k;     // Current location of y_{k+1}
            int temp = arr[source]; // Save value before shifting

            // Shift intervening values one position to the right (right-to-left)
            for (int j = source; j > target; j--) {
                arr[j] = arr[j - 1];
            }

            arr[target] = temp;     // Place y_{k+1} into target position
        }
    }

    // -------------------------------------------------------------
    // Test Driver & Verification
    // -------------------------------------------------------------

    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 3, 10, 20, 30},             // Normal Case (from specification)
            {},                                // Boundary Case: Empty array (n = 0)
            {5, 9},                            // Boundary Case: Two elements (n = 2)
            {1, 2, 3, 4},                      // Boundary Case: Minimal rearrangement (n = 4)
            {7, 7, 7, 7, 7, 7},                // Tricky Case: All identical elements
            {-10, -20, 100, 200},              // Tricky Case: Negative and positive integers
            {1, 1, 2, 2, 1, 1, 2, 2}           // Adversarial Case: Repeated repeating patterns
        };

        int[][] expectedOutputs = {
            {1, 10, 2, 20, 3, 30},
            {},
            {5, 9},
            {1, 3, 2, 4},
            {7, 7, 7, 7, 7, 7},
            {-10, 100, -20, 200},
            {1, 1, 1, 1, 2, 2, 2, 2}
        };

        System.out.printf("%-5s | %-26s | %-26s | %-6s%n",
                "Test", "Input Array", "Expected Output", "Status");
        System.out.println("-------------------------------------------------------------------------");

        for (int t = 0; t < testCases.length; t++) {
            int[] expected = expectedOutputs[t];

            // Test Approach A on a fresh copy
            int[] arrA = Arrays.copyOf(testCases[t], testCases[t].length);
            interleaveAuxiliary(arrA);

            // Test Approach B on a fresh copy
            int[] arrB = Arrays.copyOf(testCases[t], testCases[t].length);
            interleaveInPlace(arrB);

            boolean passedA = Arrays.equals(arrA, expected);
            boolean passedB = Arrays.equals(arrB, expected);
            boolean passed = passedA && passedB;

            System.out.printf("%-5d | %-26s | %-26s | %-6s%n",
                    (t + 1),
                    Arrays.toString(testCases[t]),
                    Arrays.toString(expected),
                    (passed ? "PASS" : "FAIL"));
        }
    }
}