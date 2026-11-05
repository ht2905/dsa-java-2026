public class SortUtils {

    // Stores operation counts for each sorting run
    public static class SortMetrics {
        public long comparisons = 0;
        public long writes = 0;
        public long swaps = 0;
        public long shifts = 0;
    }

    // Manual array copy to keep original input unchanged
    public static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    // Manual array formatting for clean console output
    public static String arrayToString(int[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 1. Optimized Bubble Sort
    public static SortMetrics optimizedBubbleSort(int[] a) {
        SortMetrics m = new SortMetrics();
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                m.comparisons++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    m.swaps++;
                    m.writes += 2; // 1 swap = 2 array writes
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // Early termination if already sorted
            }
        }
        return m;
    }

    // 2. Selection Sort
    public static SortMetrics selectionSort(int[] a) {
        SortMetrics m = new SortMetrics();
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            // Only perform a swap if a smaller element was found at a different index
            if (minIdx != i) {
                int temp = a[i];
                a[i] = a[minIdx];
                a[minIdx] = temp;
                m.swaps++;
                m.writes += 2;
            }
        }
        return m;
    }

    // 3. Insertion Sort
    public static SortMetrics insertionSort(int[] a) {
        SortMetrics m = new SortMetrics();
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0) {
                m.comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.shifts++;
                    m.writes++; // 1 shift = 1 array write
                    j--;
                } else {
                    break;
                }
            }
            // Only write the key if at least one shift changed its position
            if (j + 1 != i) {
                a[j + 1] = key;
                m.writes++;
            }
        }
        return m;
    }
}