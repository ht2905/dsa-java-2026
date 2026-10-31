import java.util.Scanner;

/**
 * Problem 01: Build Stairs
 * 
 * Problem Summary:
 * There are N squares in a row from left to right with heights H_1, H_2, ..., H_N.
 * For each square, we can either decrease its height by 1 or do nothing.
 * Determine if it is possible to make the heights non-decreasing from left to right.
 * 
 * Design Decision:
 * We use a greedy strategy from left to right.
 * To make it as easy as possible for subsequent squares to be greater than or equal
 * to the current square, we want each square's height to be as small as possible.
 * 
 * 1. For the first square (index 0), we can always decrease its height by 1.
 * 2. For each subsequent square i (from index 1 to N - 1):
 *    - If decreasing by 1 still keeps it >= previous square (h[i] - 1 >= h[i - 1]),
 *      we greedily decrease it by 1: h[i] = h[i] - 1.
 *    - Else if h[i] >= h[i - 1], we cannot decrease it, so we leave it unchanged.
 *    - Else (h[i] < h[i - 1]), even without decreasing, it is strictly less than
 *      the previous square. It is impossible to achieve a non-decreasing sequence,
 *      so we print "No" and terminate.
 * 3. If all squares satisfy the condition, we print "Yes".
 * 
 * Complexity:
 * - Time Complexity: O(N) where N is the number of squares. We iterate through the array once.
 * - Space Complexity: O(N) to store the heights of the N squares in an array.
 * 
 * Note for VJudge / Online Judges:
 * Class is named `Main` (package-private) so that it can be pasted directly
 * into VJudge without getting "class Problem01 should be declared in Problem01.java".
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = scanner.nextInt();
        }

        // Greedily decrease the first square to make it as small as possible
        h[0] = h[0] - 1;

        boolean possible = true;
        for (int i = 1; i < n; i++) {
            if (h[i] - 1 >= h[i - 1]) {
                // We can decrease and keep it non-decreasing
                h[i] = h[i] - 1;
            } else if (h[i] >= h[i - 1]) {
                // Cannot decrease, keep as is
                // h[i] remains unchanged
            } else {
                // h[i] < h[i - 1], impossible to keep non-decreasing
                possible = false;
                break;
            }
        }

        if (possible) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        scanner.close();
    }
}

