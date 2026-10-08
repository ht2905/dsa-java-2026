package Problem01_EqualValueBlocks;

public class EqualValueBlocks {

    /**
     * Counts the number of maximal contiguous equal-value blocks in the array.
     *
     * @param arr the input array of integers (non-null per guidelines)
     * @return 0 if arr is empty; otherwise the number of contiguous blocks
     */
    public static int countEqualValueBlocks(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // A non-empty array has at least one block established by arr[0]
        int blockCount = 1;

        // Traverse once and count a new block whenever an element differs from its predecessor
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                blockCount++;
            }
        }

        return blockCount;
    }

    public static void main(String[] args) {
        // Test suite covering normal, boundary, and tricky cases
        int[][] testCases = {
            {4, 4, -1, -1, -1, 7, 4, 4},       // Normal case (from problem description)
            {},                                // Boundary case: empty array
            {42},                              // Boundary case: single element
            {5, 5, 5, 5, 5},                   // Boundary case: all identical elements
            {1, 2, 1, 2, 1, 2},                // Tricky case: strictly alternating values
            {-1000000, 1000000, -1000000}       // Tricky case: extreme value transitions
        };

        int[] expectedOutputs = {4, 0, 1, 1, 6, 3};

        System.out.printf("%-5s | %-32s | %-10s | %-10s | %-6s%n",
                "Test", "Input", "Expected", "Actual", "Status");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int actual = countEqualValueBlocks(testCases[i]);
            String inputStr = java.util.Arrays.toString(testCases[i]);
            if (inputStr.length() > 30) {
                inputStr = inputStr.substring(0, 27) + "...]";
            }
            boolean passed = (actual == expectedOutputs[i]);
            System.out.printf("%-5d | %-32s | %-10d | %-10d | %-6s%n",
                    (i + 1), inputStr, expectedOutputs[i], actual, (passed ? "PASS" : "FAIL"));
        }
    }
}