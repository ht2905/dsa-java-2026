public class Problem05_PacketTypeOccurrenceRange {

    private static void runTest(String label, int[] sortedValues, int target, boolean trace) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Sorted values: " + SortUtils.arrayToString(sortedValues));
        System.out.println("Target: " + target);
        SearchUtils.RangeResult res = SearchUtils.findOccurrenceRange(sortedValues, target, trace);
        System.out.println("First index: " + res.first);
        System.out.println("Last index: " + res.last);
        System.out.println("Count: " + res.count);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] exampleArr = new int[]{2, 2, 2, 5, 7, 7, 9, 9, 9, 9};

        // 1. Handout Example (with trace of boundary search as required)
        runTest("Normal - Example Target 9 (Duplicates at Right End, Traced)", exampleArr, 9, true);

        // 2. Duplicates at left end
        runTest("Boundary - Duplicates at Left End (Target 2)", exampleArr, 2, false);

        // 3. Single match in middle
        runTest("Normal - Single Match (Target 5)", exampleArr, 5, false);

        // 4. All-equal data
        runTest("Tricky - All-Equal Data", new int[]{4, 4, 4, 4, 4}, 4, false);

        // 5. Absent target (between values and outside range)
        runTest("Tricky - Absent Target Between Values (Target 6)", exampleArr, 6, false);
        runTest("Boundary - Absent Target Above Range (Target 15)", exampleArr, 15, false);

        // 6. Empty array
        runTest("Boundary - Empty Array", new int[]{}, 9, false);
    }
}

