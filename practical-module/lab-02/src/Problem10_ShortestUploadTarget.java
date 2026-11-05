public class Problem10_ShortestUploadTarget {

    public static class UploadResult {
        public final int minLength;
        public final int startIndex;
        public final int endIndex;
        public final long selectedTotal;

        public UploadResult(int minLength, int startIndex, int endIndex, long selectedTotal) {
            this.minLength = minLength;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.selectedTotal = selectedTotal;
        }

        @Override
        public String toString() {
            return "Length: " + minLength + " | Start: " + startIndex
                    + " | End: " + endIndex + " | Selected Total: " + selectedTotal;
        }
    }

    public static UploadResult findShortestUploadWindow(int[] volumes, long S, boolean printTrace) {
        if (volumes == null || volumes.length == 0 || S <= 0) {
            return new UploadResult(0, -1, -1, 0);
        }

        int n = volumes.length;
        int left = 0;
        long currentSum = 0;

        int bestLen = Integer.MAX_VALUE;
        int bestStart = -1;
        int bestEnd = -1;
        long bestSum = 0;

        if (printTrace) {
            System.out.println("  Trace (right | vol[right] | sum | action | saved candidate):");
        }

        for (int right = 0; right < n; right++) {
            currentSum += volumes[right];

            // When sum reaches S, record candidate before removing leftmost element
            while (currentSum >= S) {
                int curLen = right - left + 1;
                // Strict inequality (<) preserves the earliest starting index on ties
                if (curLen < bestLen) {
                    bestLen = curLen;
                    bestStart = left;
                    bestEnd = right;
                    bestSum = currentSum;
                    if (printTrace) {
                        System.out.println("    Valid window [" + left + ".." + right + "] sum=" + currentSum
                                + " -> Recorded new bestLen=" + bestLen);
                    }
                } else if (printTrace) {
                    System.out.println("    Valid window [" + left + ".." + right + "] sum=" + currentSum
                            + " -> Tie/longer len=" + curLen + " (kept bestLen=" + bestLen + ")");
                }

                // Shrink from left to seek an even shorter valid window
                currentSum -= volumes[left];
                left++;
            }
        }

        if (bestLen == Integer.MAX_VALUE) {
            return new UploadResult(0, -1, -1, 0);
        }
        return new UploadResult(bestLen, bestStart, bestEnd, bestSum);
    }

    private static void runTest(String label, int[] volumes, long S, boolean trace) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Volumes: " + SortUtils.arrayToString(volumes) + " | Target S: " + S);
        UploadResult res = findShortestUploadWindow(volumes, S, trace);
        System.out.println("Result -> " + res);
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Example (traced)
        runTest("Normal - Handout Example [5, 1, 2, 7, 3, 4] with S = 10",
                new int[]{5, 1, 2, 7, 3, 4}, 10, true);

        // 2. Empty array
        runTest("Boundary - Empty Array", new int[]{}, 10, false);

        // 3. Unreachable target
        runTest("Boundary - Unreachable Target", new int[]{1, 2, 3}, 100, false);

        // 4. One-entry answer
        runTest("Boundary - Single Value Qualifies", new int[]{5, 12, 3}, 10, false);

        // 5. Whole-array-only answer
        runTest("Boundary - Entire Array Needed", new int[]{2, 3, 4}, 9, false);

        // 6. Tied minimum lengths (earliest tie preferred)
        runTest("Tricky - Tied Minimum Lengths [4, 6, 2, 8] with S = 10",
                new int[]{4, 6, 2, 8}, 10, false);

        // 7. Several removals after one expansion
        runTest("Tricky - Several Removals After Single Large Element",
                new int[]{1, 1, 1, 100}, 10, false);

        // 8. Totals beyond 32-bit int
        runTest("Boundary - Totals Beyond 32-bit Int",
                new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}, 2_000_000_000L, false);
    }
}

