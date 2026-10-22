public class Problem07_QuietestMachineServiceSlot {

    public static class WindowResult {
        public final long minTotal;
        public final int startIndex;
        public final int endIndex;
        public final long[] allSums;

        public WindowResult(long minTotal, int startIndex, int endIndex, long[] allSums) {
            this.minTotal = minTotal;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.allSums = allSums;
        }
    }

    // Approach A: Independently sum each window
    public static WindowResult minWindowDirect(int[] loads, int k) {
        if (loads == null || loads.length == 0 || k < 1 || k > loads.length) {
            throw new IllegalArgumentException("Invalid input: require 1 <= k <= n and non-empty array.");
        }

        int n = loads.length;
        int numWindows = n - k + 1;
        long[] allSums = new long[numWindows];

        long minTotal = Long.MAX_VALUE;
        int bestStart = 0;

        for (int i = 0; i < numWindows; i++) {
            long currentSum = 0;
            for (int j = i; j < i + k; j++) {
                currentSum += loads[j];
            }
            allSums[i] = currentSum;

            // Strict comparison (<) preserves the earliest starting index on ties
            if (currentSum < minTotal) {
                minTotal = currentSum;
                bestStart = i;
            }
        }

        return new WindowResult(minTotal, bestStart, bestStart + k - 1, allSums);
    }

    // Approach B: Compute the first sum once and slide
    public static WindowResult minWindowSliding(int[] loads, int k) {
        if (loads == null || loads.length == 0 || k < 1 || k > loads.length) {
            throw new IllegalArgumentException("Invalid input: require 1 <= k <= n and non-empty array.");
        }

        int n = loads.length;
        int numWindows = n - k + 1;
        long[] allSums = new long[numWindows];

        // 1. Initial window sum
        long currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += loads[i];
        }
        allSums[0] = currentSum;

        long minTotal = currentSum;
        int bestStart = 0;

        // 2. Slide the window: subtract leaving element, add entering element
        for (int i = 1; i < numWindows; i++) {
            currentSum = currentSum - loads[i - 1] + loads[i + k - 1];
            allSums[i] = currentSum;

            // Strict comparison (<) preserves the earliest starting index on ties
            if (currentSum < minTotal) {
                minTotal = currentSum;
                bestStart = i;
            }
        }

        return new WindowResult(minTotal, bestStart, bestStart + k - 1, allSums);
    }

    private static String sumsToString(long[] sums) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < sums.length; i++) {
            sb.append(sums[i]);
            if (i < sums.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static void runTest(String label, int[] loads, int k) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Loads: " + SortUtils.arrayToString(loads) + " | k = " + k);

        try {
            WindowResult resA = minWindowDirect(loads, k);
            WindowResult resB = minWindowSliding(loads, k);

            System.out.println("Approach A Window Sums: " + sumsToString(resA.allSums));
            System.out.println("Approach B Window Sums: " + sumsToString(resB.allSums));
            System.out.println("Minimum Total: " + resB.minTotal);
            System.out.println("Start Index  : " + resB.startIndex);
            System.out.println("End Index    : " + resB.endIndex);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Example
        runTest("Normal - Handout Worked Example", new int[]{8, 2, 5, 1, 4, 2, 7}, 3);

        // 2. Window size k = 1
        runTest("Boundary - k = 1", new int[]{8, 2, 5, 1, 4, 2, 7}, 1);

        // 3. Window size k = n
        runTest("Boundary - k = n", new int[]{8, 2, 5, 1, 4, 2, 7}, 7);

        // 4. All zeros
        runTest("Boundary - All Zeros", new int[]{0, 0, 0, 0}, 2);

        // 5. All equal positive loads
        runTest("Normal - All Equal Loads", new int[]{5, 5, 5, 5}, 2);

        // 6. Earliest tie test from handout
        runTest("Tricky - Earliest Tie [2, 1, 2, 1] with k = 2", new int[]{2, 1, 2, 1}, 2);

        // 7. Total larger than int (uses long)
        runTest("Tricky - Total Exceeding 32-bit Integer", new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}, 3);

        // 8. Rejections
        runTest("Invalid - k = 0", new int[]{5, 2, 8}, 0);
        runTest("Invalid - k > n", new int[]{5, 2, 8}, 5);
        runTest("Invalid - Empty Array", new int[]{}, 1);
    }
}

