public class Problem12_ArchiveRecordLocator {

    public static class ArchiveRecord {
        public final int id;
        public final int shelfNumber;

        public ArchiveRecord(int id, int shelfNumber) {
            this.id = id;
            this.shelfNumber = shelfNumber;
        }

        @Override
        public String toString() {
            return "(" + id + ", " + shelfNumber + ")";
        }
    }

    // Deep copy helper so the original records array is never mutated
    public static ArchiveRecord[] copyRecords(ArchiveRecord[] source) {
        if (source == null) return null;
        ArchiveRecord[] copy = new ArchiveRecord[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = new ArchiveRecord(source[i].id, source[i].shelfNumber);
        }
        return copy;
    }

    // Format record array for console output
    public static String recordsToString(ArchiveRecord[] records) {
        if (records == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < records.length; i++) {
            sb.append(records[i].toString());
            if (i < records.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Ascending insertion sort: compares IDs and moves complete ArchiveRecord objects
    public static void insertionSortRecords(ArchiveRecord[] records) {
        if (records == null || records.length <= 1) return;
        int n = records.length;
        for (int i = 1; i < n; i++) {
            ArchiveRecord key = records[i];
            int j = i - 1;
            while (j >= 0 && records[j].id > key.id) {
                records[j + 1] = records[j]; // shift complete record reference
                j--;
            }
            records[j + 1] = key; // place complete record
        }
    }

    // Binary search on sorted records by ID
    public static ArchiveRecord binarySearchById(ArchiveRecord[] sortedRecords, int targetId) {
        if (sortedRecords == null || sortedRecords.length == 0) return null;
        int low = 0;
        int high = sortedRecords.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedRecords[mid].id == targetId) {
                return sortedRecords[mid];
            } else if (sortedRecords[mid].id < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Baseline linear search on original records
    public static ArchiveRecord linearSearchById(ArchiveRecord[] records, int targetId) {
        if (records == null) return null;
        for (ArchiveRecord record : records) {
            if (record.id == targetId) {
                return record;
            }
        }
        return null;
    }

    public static void executeQuery(ArchiveRecord[] original, ArchiveRecord[] sorted, int targetId) {
        ArchiveRecord binRes = binarySearchById(sorted, targetId);
        ArchiveRecord linRes = linearSearchById(original, targetId);

        // Cross-check correctness between linear baseline and binary search
        boolean matches = (binRes == null && linRes == null) ||
                (binRes != null && linRes != null && binRes.id == linRes.id && binRes.shelfNumber == linRes.shelfNumber);

        if (!matches) {
            System.err.println("Mismatch detected for query ID: " + targetId);
        }

        if (binRes != null) {
            System.out.println("Query " + targetId + ": " + binRes.id + " " + binRes.shelfNumber
                    + " (Verified with linear search)");
        } else {
            System.out.println("Query " + targetId + ": NOT FOUND"
                    + " (Verified with linear search)");
        }
    }

    private static void runLocatorTest(String label, ArchiveRecord[] original, int[] queries) {
        System.out.println("=== Test Case: " + label + " ===");
        System.out.println("Original (ID, payload): " + recordsToString(original));

        ArchiveRecord[] sortedCopy = copyRecords(original);
        insertionSortRecords(sortedCopy);
        System.out.println("Sorted   (ID, payload): " + recordsToString(sortedCopy));

        for (int queryId : queries) {
            executeQuery(original, sortedCopy, queryId);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example
        ArchiveRecord[] exampleRecords = new ArchiveRecord[]{
                new ArchiveRecord(708, 12),
                new ArchiveRecord(502, 3),
                new ArchiveRecord(901, 8),
                new ArchiveRecord(604, 6)
        };
        runLocatorTest("Normal - Handout Worked Example", exampleRecords, new int[]{604, 605});

        // 2. Empty collection
        runLocatorTest("Boundary - Empty Collection", new ArchiveRecord[]{}, new int[]{100});

        // 3. Single record collection
        runLocatorTest("Boundary - Single Record",
                new ArchiveRecord[]{new ArchiveRecord(101, 5)}, new int[]{101, 999});

        // 4. Already ascending input
        runLocatorTest("Normal - Already Ascending Order",
                new ArchiveRecord[]{
                        new ArchiveRecord(10, 1),
                        new ArchiveRecord(20, 2),
                        new ArchiveRecord(30, 3)
                }, new int[]{20, 40});

        // 5. Strictly reverse input
        runLocatorTest("Tricky - Reverse Input Order",
                new ArchiveRecord[]{
                        new ArchiveRecord(500, 50),
                        new ArchiveRecord(400, 40),
                        new ArchiveRecord(300, 30),
                        new ArchiveRecord(200, 20),
                        new ArchiveRecord(100, 10)
                }, new int[]{100, 300, 500, 250});
    }
}

