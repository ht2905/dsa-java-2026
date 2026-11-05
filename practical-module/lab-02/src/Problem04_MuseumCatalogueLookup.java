public class Problem04_MuseumCatalogueLookup {

    private static void runBinarySearchTest(String label, int[] catalogue, int target, boolean trace) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Catalogue: " + SortUtils.arrayToString(catalogue) + " | Target: " + target);
        SearchUtils.SearchResult res = SearchUtils.binarySearchIterative(catalogue, target, trace);
        System.out.println("Returned index: " + res.index + " | Midpoint Inspections: " + res.inspections);
        System.out.println();
    }

    private static void compareLinearAndBinary(String label, int[] catalogue, int target) {
        System.out.println("=== Required Comparison: " + label + " ===");
        System.out.println("Catalogue: " + SortUtils.arrayToString(catalogue) + " | Target: " + target);

        SearchUtils.SearchResult linRes = SearchUtils.linearSearchExact(catalogue, target);
        System.out.println("Linear Search -> Index: " + linRes.index
                + " | Inspected Values: " + linRes.inspections);

        SearchUtils.SearchResult binRes = SearchUtils.binarySearchIterative(catalogue, target, true);
        System.out.println("Binary Search -> Index: " + binRes.index
                + " | Inspected Values: " + binRes.inspections);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] catalogue = new int[]{205, 230, 260, 310, 355, 390, 440};

        // 1. Worked Example from handout
        runBinarySearchTest("Normal - Handout Worked Example (Target 390)", catalogue, 390, true);

        // 2. Required Comparison: Look up the last identifier (440) using both methods
        compareLinearAndBinary("Lookup Last Identifier (440)", catalogue, 440);

        // 3. Required Boundary and Tricky Tests
        runBinarySearchTest("Boundary - First Identifier (205)", catalogue, 205, true);
        runBinarySearchTest("Tricky - Absent Value Between Entries (300)", catalogue, 300, true);
        runBinarySearchTest("Boundary - Value Below Range (100)", catalogue, 100, true);
        runBinarySearchTest("Boundary - Value Above Range (500)", catalogue, 500, true);
        runBinarySearchTest("Boundary - Empty Catalogue", new int[]{}, 310, true);
        runBinarySearchTest("Boundary - Single-Entry Catalogue (Present)", new int[]{310}, 310, true);
        runBinarySearchTest("Boundary - Single-Entry Catalogue (Absent)", new int[]{310}, 999, true);
    }
}