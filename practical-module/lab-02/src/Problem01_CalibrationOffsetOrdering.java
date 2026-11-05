public class Problem01_CalibrationOffsetOrdering {

    private static void runTest(String label, int[] original) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Original Input: " + SortUtils.arrayToString(original));

        // 1. Optimized Bubble Sort on a fresh copy
        int[] copyBubble = SortUtils.copyArray(original);
        SortUtils.SortMetrics mBubble = SortUtils.optimizedBubbleSort(copyBubble);
        System.out.println("Bubble Sort    -> Sorted: " + SortUtils.arrayToString(copyBubble)
                + " | Comparisons: " + mBubble.comparisons
                + " | Writes: " + mBubble.writes
                + " | Swaps: " + mBubble.swaps);

        // 2. Selection Sort on a fresh copy
        int[] copySelection = SortUtils.copyArray(original);
        SortUtils.SortMetrics mSelection = SortUtils.selectionSort(copySelection);
        System.out.println("Selection Sort -> Sorted: " + SortUtils.arrayToString(copySelection)
                + " | Comparisons: " + mSelection.comparisons
                + " | Writes: " + mSelection.writes
                + " | Swaps: " + mSelection.swaps);

        // 3. Insertion Sort on a fresh copy
        int[] copyInsertion = SortUtils.copyArray(original);
        SortUtils.SortMetrics mInsertion = SortUtils.insertionSort(copyInsertion);
        System.out.println("Insertion Sort -> Sorted: " + SortUtils.arrayToString(copyInsertion)
                + " | Comparisons: " + mInsertion.comparisons
                + " | Writes: " + mInsertion.writes
                + " | Shifts: " + mInsertion.shifts);
        System.out.println();
    }

    public static void main(String[] args) {
        // Required experiment patterns
        runTest("Normal - Unordered", new int[]{7, -3, 4, 0, -3, 9});
        runTest("Normal - Already in required order", new int[]{-3, -3, 0, 4, 7, 9});
        runTest("Tricky - Opposite order", new int[]{9, 7, 4, 0, -3, -3});
        runTest("Normal - Nearly in required order", new int[]{-3, -3, 4, 0, 7, 9});

        // Required boundary and tricky cases
        runTest("Boundary - Empty array", new int[]{});
        runTest("Boundary - Single value", new int[]{42});
        runTest("Tricky - All-equal array", new int[]{5, 5, 5, 5, 5});
    }
}