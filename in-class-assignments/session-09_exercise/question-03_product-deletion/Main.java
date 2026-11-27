/**
 * Session 09 Exercise - Question 3: Product Deletion (DELETED Tombstone Marker)
 *
 * Problem Summary:
 * Delete product 114 (Milk) from the m = 13 quadratic-probing hash table using
 * a special DELETED marker, then search for product 127. Also compare against
 * the broken behavior that occurs if the deleted slot is marked EMPTY instead.
 *
 * Design Decisions:
 * - Represent each slot with an explicit SlotState enum (EMPTY, OCCUPIED, DELETED)
 *   so tombstone semantics are crystal clear and impossible to confuse with null.
 * - During search, skip over DELETED slots and only terminate on EMPTY (or match).
 * - Include a side-by-side demonstration of naive deletion (marking EMPTY) to
 *   prove empirically why searching for 127 fails without a tombstone.
 *
 * Complexity:
 * - Time Complexity: O(1) expected for delete and search; O(m) worst-case.
 * - Space Complexity: O(m) for the table of size m = 13.
 */
public class Main {

    private static final int TABLE_SIZE = 13;

    enum SlotState {
        EMPTY, OCCUPIED, DELETED
    }

    static class Slot {
        SlotState state = SlotState.EMPTY;
        int id;
        String name;

        void setOccupied(int id, String name) {
            this.state = SlotState.OCCUPIED;
            this.id = id;
            this.name = name;
        }

        void markDeleted() {
            this.state = SlotState.DELETED;
            this.id = -1;
            this.name = null;
        }

        void markEmptyBroken() {
            this.state = SlotState.EMPTY;
            this.id = -1;
            this.name = null;
        }

        @Override
        public String toString() {
            if (state == SlotState.EMPTY) {
                return "EMPTY";
            }
            if (state == SlotState.DELETED) {
                return "DELETED";
            }
            return id + " (" + name + ")";
        }
    }

    static int primaryHash(int key) {
        return Math.floorMod(key, TABLE_SIZE);
    }

    static int probeIndex(int initialHash, int step) {
        return (initialHash + step * step) % TABLE_SIZE;
    }

    static Slot[] buildInitialTable() {
        Slot[] table = new Slot[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new Slot();
        }
        int[] ids = {101, 114, 127, 102, 140, 115};
        String[] names = {"Rice", "Milk", "Bread", "Eggs", "Coffee", "Orange Juice"};

        for (int k = 0; k < ids.length; k++) {
            int h0 = primaryHash(ids[k]);
            for (int i = 0; i < TABLE_SIZE; i++) {
                int idx = probeIndex(h0, i);
                if (table[idx].state == SlotState.EMPTY) {
                    table[idx].setOccupied(ids[k], names[k]);
                    break;
                }
            }
        }
        return table;
    }

    static int deleteProduct(Slot[] table, int targetId, boolean useDeletedMarker) {
        int h0 = primaryHash(targetId);
        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(h0, i);
            if (table[idx].state == SlotState.EMPTY) {
                return -1;
            }
            if (table[idx].state == SlotState.OCCUPIED && table[idx].id == targetId) {
                if (useDeletedMarker) {
                    table[idx].markDeleted();
                } else {
                    table[idx].markEmptyBroken();
                }
                return idx;
            }
        }
        return -1;
    }

    static void traceSearch(Slot[] table, int targetId, String label) {
        int h0 = primaryHash(targetId);
        StringBuilder sequence = new StringBuilder();
        int foundIndex = -1;
        int probes = 0;

        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(h0, i);
            probes = i + 1;
            if (i > 0) {
                sequence.append(" -> ");
            }
            sequence.append(idx);

            if (table[idx].state == SlotState.EMPTY) {
                break;
            }
            if (table[idx].state == SlotState.OCCUPIED && table[idx].id == targetId) {
                foundIndex = idx;
                break;
            }
        }

        System.out.println(label);
        System.out.println("  Probing sequence : " + sequence);
        System.out.println("  Number of probes : " + probes);
        if (foundIndex != -1) {
            System.out.println("  Result           : FOUND " + table[foundIndex] + " at index " + foundIndex);
        } else {
            System.out.println("  Result           : NOT FOUND (probe chain broken by EMPTY slot!)");
        }
    }

    static void printTable(Slot[] table) {
        System.out.println("\nHash Table After Deleting 114 (with DELETED marker):");
        System.out.printf("%-6s | %-20s%n", "Index", "Slot Content");
        System.out.println("-------+---------------------");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.printf("%-6d | %-20s%n", i, table[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Question 3: Product Deletion (Quadratic Probing, m = 13) ===");

        // Part (a) & (b): Proper deletion using DELETED tombstone marker
        Slot[] properTable = buildInitialTable();
        int deletedAt = deleteProduct(properTable, 114, true);
        System.out.println("Deleted product 114 (Milk) at index " + deletedAt + " using DELETED marker.");
        printTable(properTable);

        System.out.println();
        traceSearch(properTable, 127, "Part (b) Search for 127 with DELETED marker at index 11:");

        // Part (c): Broken deletion marking slot 11 as EMPTY instead of DELETED
        Slot[] brokenTable = buildInitialTable();
        deleteProduct(brokenTable, 114, false);
        System.out.println();
        traceSearch(brokenTable, 127, "Part (c) Search for 127 if index 11 were marked EMPTY instead:");
    }
}
