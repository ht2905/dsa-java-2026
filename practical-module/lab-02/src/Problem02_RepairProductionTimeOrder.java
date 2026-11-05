public class Problem02_RepairProductionTimeOrder {

    private static void compareInsertionAndSelection(String label, int[] original) {
        System.out.println("=== Comparison Case: " + label + " ===");
        System.out.println("Original Input: " + SortUtils.arrayToString(original));

        // Insertion Sort on fresh copy
        int[] copyIns = SortUtils.copyArray(original);
        SortUtils.SortMetrics mIns = SortUtils.insertionSort(copyIns);
        System.out.println("Insertion Sort -> Output: " + SortUtils.arrayToString(copyIns)
                + " | Comparisons: " + mIns.comparisons
                + " | Writes: " + mIns.writes
                + " | Shifts: " + mIns.shifts);

        // Selection Sort on fresh copy
        int[] copySel = SortUtils.copyArray(original);
        SortUtils.SortMetrics mSel = SortUtils.selectionSort(copySel);
        System.out.println("Selection Sort -> Output: " + SortUtils.arrayToString(copySel)
                + " | Comparisons: " + mSel.comparisons
                + " | Writes: " + mSel.writes
                + " | Swaps: " + mSel.swaps);
        System.out.println();
    }

    public static void main(String[] args) {
        // Problem Example
        compareInsertionAndSelection("Example - Delayed Production Log",
                new int[]{810, 820, 850, 830, 840, 860});

        // Required Comparison Cases
        compareInsertionAndSelection("Normal - Nearly Sorted [1..10 with two adjacent swaps]",
                new int[]{1, 2, 4, 3, 5, 6, 8, 7, 9, 10});
        compareInsertionAndSelection("Normal - Ascending [1..10]",
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        compareInsertionAndSelection("Tricky - Descending [10..1]",
                new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});

        // Required Boundary and Repeated Cases
        compareInsertionAndSelection("Boundary - Empty Log", new int[]{});
        compareInsertionAndSelection("Boundary - Single Time Code", new int[]{900});
        compareInsertionAndSelection("Tricky - Repeated Time Codes",
                new int[]{820, 810, 820, 810, 830, 820});
    }
}