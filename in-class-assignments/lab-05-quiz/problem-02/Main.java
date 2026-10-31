import java.util.Scanner;

/**
 * Problem 02: Balance
 * 
 * Problem Summary:
 * We are given N weights with masses W_1, W_2, ..., W_N.
 * We want to choose an integer T (1 <= T < N) to divide the weights into two groups:
 * - Group 1: weights with indices <= T (sum S_1)
 * - Group 2: weights with indices > T (sum S_2)
 * We need to find the minimum possible absolute difference |S_1 - S_2|.
 * 
 * Design Decision:
 * 1. Calculate the total sum of all N weights.
 * 2. Iterate through each possible split point T from 1 to N - 1:
 *    - Maintain a running prefix sum S_1 for the first group.
 *    - The sum of the remaining weights is S_2 = totalSum - S_1.
 *    - The absolute difference for this partition is |S_1 - S_2|.
 *    - Keep track of the minimum difference found.
 * This running sum approach avoids recomputing sums repeatedly and runs in linear time.
 * 
 * Complexity:
 * - Time Complexity: O(N) where N is the number of weights. We do one pass to read
 *   and sum the elements, and a second pass of N - 1 steps to check split points.
 * - Space Complexity: O(N) to store the weight values in an array.
 * 
 * Note for VJudge / Online Judges:
 * Class is named `Main` (package-private) so that it can be pasted directly
 * into VJudge without getting "class Problem02 should be declared in Problem02.java".
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] w = new int[n];
        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            totalSum += w[i];
        }

        int s1 = 0;
        int minDiff = Integer.MAX_VALUE;

        // Try every partition point T where 1 <= T < N
        // In 0-indexed terms, group 1 has elements from index 0 to index i
        for (int i = 0; i < n - 1; i++) {
            s1 += w[i];
            int s2 = totalSum - s1;
            int diff = Math.abs(s1 - s2);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        System.out.println(minDiff);

        scanner.close();
    }
}

