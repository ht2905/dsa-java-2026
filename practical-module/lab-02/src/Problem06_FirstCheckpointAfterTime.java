public class Problem06_FirstCheckpointAfterTime {

    private static void runTest(String label, int[] checkpoints, int targetTime, boolean trace) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Checkpoints: " + SortUtils.arrayToString(checkpoints) + " | Target Time t: " + targetTime);
        int idx = SearchUtils.findFirstStrictlyGreater(checkpoints, targetTime, trace);
        System.out.println("Returned Index: " + idx);
        if (idx < checkpoints.length) {
            System.out.println("Checkpoint Value: " + checkpoints[idx]);
        } else {
            System.out.println("Checkpoint Value: NO CHECKPOINT");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] exampleLog = new int[]{12, 20, 20, 35, 50};

        // Handout Examples
        runTest("Tricky - Exact Duplicate Match (t = 20, Skip Both 20s)", exampleLog, 20, false);
        runTest("Normal - Absent Target with Qualifying Neighbor (t = 26, Traced)", exampleLog, 26, true);
        runTest("Boundary - Target Below All Checkpoints (t = 5)", exampleLog, 5, false);
        runTest("Boundary - Target Above All Checkpoints (t = 60)", exampleLog, 60, false);

        // Additional Required Boundary Cases
        runTest("Boundary - Exact Match at Last Element (t = 50)", exampleLog, 50, false);
        runTest("Boundary - Empty Log", new int[]{}, 10, false);
        runTest("Boundary - Single Entry (Strictly Later)", new int[]{30}, 20, false);
        runTest("Boundary - Single Entry (Equal, Does Not Qualify)", new int[]{30}, 30, false);
    }
}

