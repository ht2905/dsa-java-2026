public class Problem03_FirstSufficientBattery {

    private static void runTest(String label, int[] charges, int requirement) {
        SearchUtils.SearchResult result = SearchUtils.findFirstSufficient(charges, requirement);
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Charges: " + SortUtils.arrayToString(charges));
        System.out.println("Requirement: " + requirement);
        System.out.println("Found: " + result.found);
        System.out.println("First sufficient index: " + result.index);
        System.out.println("Inspections: " + result.inspections);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] exampleShelf = new int[]{20, 75, 40, 90, 60};

        // Required tests on the example shelf (20, 60, 90, 100)
        runTest("Normal - Example with Requirement 60", exampleShelf, 60);
        runTest("Boundary - Example with Requirement 20 (First Item, Exact Match)", exampleShelf, 20);
        runTest("Normal - Example with Requirement 90 (Exact Match Deeper in Shelf)", exampleShelf, 90);
        runTest("Tricky - Example with Requirement 100 (No Qualifying Battery)", exampleShelf, 100);

        // Additional required boundary and tricky cases
        runTest("Boundary - Requirement Zero", exampleShelf, 0);
        runTest("Boundary - Empty Shelf", new int[]{}, 50);
        runTest("Boundary - Single Battery (Qualifies)", new int[]{45}, 45);
        runTest("Boundary - Single Battery (Insufficient)", new int[]{30}, 45);
        runTest("Tricky - Repeated Qualifying Charges (Must Pick Earliest)",
                new int[]{15, 50, 80, 80, 80, 95}, 80);
    }
}