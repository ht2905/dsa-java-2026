import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Session 09 Exercise - Question 5: Load Factor and Limitations of Quadratic Probing
 *
 * Problem Summary:
 *   a. Calculate load factor alpha = n / m after inserting the original 6 products.
 *   b. Calculate load factor after deleting product 114.
 *   c. Calculate load factor after inserting product 153.
 *   d. Illustrate secondary clustering (distinct keys with the same initial hash
 *      follow the exact same probe sequence).
 *   e. Demonstrate empirically that quadratic probing can fail to insert a new key
 *      even when empty slots remain in the table (since i^2 mod 13 visits only
 *      (m + 1) / 2 = 7 distinct slots).
 *
 * Design Decisions:
 * - Track active record count explicitly (excluding DELETED tombstones from n)
 *   to compute alpha = (double) activeCount / TABLE_SIZE accurately.
 * - Enumerate all quadratic residues modulo 13 to show the exact set of 7
 *   reachable slots and 6 unreachable slots for h(k) = 10, then attempt an 8th
 *   insertion with h(k) = 10 to demonstrate failure despite 6 empty slots.
 *
 * Complexity:
 * - Time Complexity: O(m) for load-factor simulation and reachability analysis.
 * - Space Complexity: O(m) for the table and reachable-slot set.
 */
public class Main {

    private static final int TABLE_SIZE = 13;

    static int primaryHash(int key) {
        return Math.floorMod(key, TABLE_SIZE);
    }

    static int probeIndex(int initialHash, int step) {
        return (initialHash + step * step) % TABLE_SIZE;
    }

    static void printLoadFactor(String label, int activeRecords) {
        double alpha = (double) activeRecords / TABLE_SIZE;
        System.out.printf("%-48s: n = %d, m = %d -> alpha = %d/%d = %.4f (%.2f%%)%n",
                label, activeRecords, TABLE_SIZE, activeRecords, TABLE_SIZE, alpha, alpha * 100.0);
    }

    static void demonstrateSecondaryClustering() {
        System.out.println("\n--- Part (d): Secondary Clustering Demonstration ---");
        int[] sameHashKeys = {101, 114, 127, 140, 153};
        for (int key : sameHashKeys) {
            int h0 = primaryHash(key);
            StringBuilder seq = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                if (i > 0) {
                    seq.append(" -> ");
                }
                seq.append(probeIndex(h0, i));
            }
            System.out.printf("Key %3d (h(k) = %2d): first 7 probes = %s%n", key, h0, seq);
        }
        System.out.println("All keys with h(k) = 10 follow the identical probe path, causing secondary clustering.");
    }

    static void demonstrateInsertionFailureWithEmptySlots() {
        System.out.println("\n--- Part (e): Insertion Failure Despite Empty Slots (m = 13) ---");
        int baseHash = 10;

        // Collect all distinct indices reachable from h(k) = 10 using i^2 mod 13
        Set<Integer> reachable = new LinkedHashSet<>();
        for (int i = 0; i < TABLE_SIZE; i++) {
            reachable.add(probeIndex(baseHash, i));
        }

        Set<Integer> unreachable = new LinkedHashSet<>();
        for (int slot = 0; slot < TABLE_SIZE; slot++) {
            if (!reachable.contains(slot)) {
                unreachable.add(slot);
            }
        }

        System.out.println("Reachable slots from h(k) = 10   (" + reachable.size() + " slots): " + reachable);
        System.out.println("Unreachable slots from h(k) = 10 (" + unreachable.size() + " slots): " + unreachable);

        // Insert 7 keys with h(k) = 10; then attempt an 8th key with h(k) = 10
        Integer[] table = new Integer[TABLE_SIZE];
        int[] keys = {10, 23, 36, 49, 62, 75, 88, 101};

        int occupied = 0;
        for (int key : keys) {
            int h0 = primaryHash(key);
            boolean inserted = false;
            for (int i = 0; i < TABLE_SIZE; i++) {
                int idx = probeIndex(h0, i);
                if (table[idx] == null) {
                    table[idx] = key;
                    occupied++;
                    inserted = true;
                    System.out.printf("Inserted key %3d at index %2d (occupied = %d/%d)%n", key, idx, occupied, TABLE_SIZE);
                    break;
                }
            }
            if (!inserted) {
                int emptySlots = TABLE_SIZE - occupied;
                System.out.printf("FAILED to insert key %3d (h(k) = %d) even though %d empty slots remain!%n",
                        key, h0, emptySlots);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Question 5: Load Factor and Limitations (m = 13) ===\n");

        printLoadFactor("Part (a) After inserting original 6 products", 6);
        printLoadFactor("Part (b) After deleting product 114", 5);
        printLoadFactor("Part (c) After inserting product 153", 6);

        demonstrateSecondaryClustering();
        demonstrateInsertionFailureWithEmptySlots();
    }
}
