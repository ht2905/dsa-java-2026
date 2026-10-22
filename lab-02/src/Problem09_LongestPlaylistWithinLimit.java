public class Problem09_LongestPlaylistWithinLimit {

    public static class PlaylistResult {
        public final int maxLength;
        public final int startIndex;
        public final int endIndex;
        public final long selectedTotal;

        public PlaylistResult(int maxLength, int startIndex, int endIndex, long selectedTotal) {
            this.maxLength = maxLength;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.selectedTotal = selectedTotal;
        }

        @Override
        public String toString() {
            return "Length: " + maxLength + " | Start: " + startIndex
                    + " | End: " + endIndex + " | Selected Total: " + selectedTotal;
        }
    }

    public static PlaylistResult findLongestPlaylist(int[] durations, long S, boolean printTrace) {
        if (durations == null || durations.length == 0 || S < 0) {
            return new PlaylistResult(0, -1, -1, 0);
        }

        int n = durations.length;
        int left = 0;
        long currentSum = 0;

        int maxLen = 0;
        int bestStart = -1;
        int bestEnd = -1;
        long bestSum = 0;

        if (printTrace) {
            System.out.println("  Trace (right | dur[right] | sum | action | window [left..right]):");
        }

        for (int right = 0; right < n; right++) {
            currentSum += durations[right];

            // Shrink window from the left while sum exceeds limit S
            while (currentSum > S && left <= right) {
                if (printTrace) {
                    System.out.println("    Over limit: sum=" + currentSum + " > " + S
                            + " -> removing dur[" + left + "]=" + durations[left]);
                }
                currentSum -= durations[left];
                left++;
            }

            // If a valid non-empty window remains, check if it improves maxLen
            if (currentSum <= S && left <= right) {
                int curLen = right - left + 1;
                // Strict inequality (>) preserves earliest start when lengths tie
                if (curLen > maxLen) {
                    maxLen = curLen;
                    bestStart = left;
                    bestEnd = right;
                    bestSum = currentSum;
                    if (printTrace) {
                        System.out.println("    New best window: length=" + maxLen
                                + " [" + bestStart + ".." + bestEnd + "] sum=" + bestSum);
                    }
                }
            }
        }

        if (maxLen == 0) {
            return new PlaylistResult(0, -1, -1, 0);
        }
        return new PlaylistResult(maxLen, bestStart, bestEnd, bestSum);
    }

    private static void runTest(String label, int[] durations, long S, boolean trace) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Durations: " + SortUtils.arrayToString(durations) + " | Limit S: " + S);
        PlaylistResult res = findLongestPlaylist(durations, S, trace);
        System.out.println("Result -> " + res);
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example (with trace showing multiple removals)
        runTest("Normal - Handout Example (Traced)", new int[]{5, 0, 2, 4, 1, 1, 3}, 8, true);

        // 2. Empty array
        runTest("Boundary - Empty Array", new int[]{}, 8, false);

        // 3. Full array answer
        runTest("Boundary - Entire Array Fits", new int[]{1, 2, 3}, 10, false);

        // 4. Every positive value above limit
        runTest("Tricky - All Values Exceed Limit", new int[]{10, 20, 30}, 5, false);

        // 5. Zeros with limit zero (from handout: [0, 0, 5, 0] with S=0 gives len 2, indices 0-1)
        runTest("Tricky - Zeros with Limit Zero", new int[]{0, 0, 5, 0}, 0, false);

        // 6. Tied lengths (earliest tie preferred)
        runTest("Normal - Tied Best Lengths", new int[]{3, 3, 3, 3}, 6, false);

        // 7. Sum beyond 32-bit int
        runTest("Boundary - Sums Beyond 32-bit Int",
                new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}, 2_500_000_000L, false);
    }
}

