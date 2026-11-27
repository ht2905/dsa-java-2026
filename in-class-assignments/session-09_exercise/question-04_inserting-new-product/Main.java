/**
 * Session 09 Exercise - Question 4: Inserting a New Product After Deletion
 *
 * Problem Summary:
 * After deleting product 114 (Milk) using a DELETED marker at index 11, insert
 * product 153 (Green Tea) assuming duplicate product IDs are NOT allowed.
 * Determine:
 *   a. The initial hash index of 153.
 *   b. The probing sequence and final insertion position.
 *   c. Whether the DELETED slot at index 11 can be reused safely.
 *   d. The updated hash table.
 *
 * Design Decisions:
 * - To prevent duplicate product IDs while still reusing DELETED slots:
 *   1. Remember the first DELETED slot encountered (`firstDeletedIndex`), but DO NOT
 *      insert immediately.
 *   2. Continue probing until either the key is found (reject duplicate) or an
 *      EMPTY slot is reached (confirming the key is not already in the table).
 *   3. Write the new product into `firstDeletedIndex` if one was seen; otherwise
 *      write into the EMPTY slot.
 *
 * Complexity:
 * - Time Complexity: O(1) expected; O(m) worst-case.
 * - Space Complexity: O(m) for the hash table of size m = 13.
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

    static Slot[] buildTableAfterDeletionOf114() {
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

        // Delete product 114 using a DELETED marker
        int h114 = primaryHash(114);
        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(h114, i);
            if (table[idx].state == SlotState.OCCUPIED && table[idx].id == 114) {
                table[idx].markDeleted();
                break;
            }
        }
        return table;
    }

    static void insertNoDuplicates(Slot[] table, int id, String name) {
        int h0 = primaryHash(id);
        int firstDeletedIndex = -1;
        int emptyIndex = -1;
        StringBuilder sequence = new StringBuilder();

        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(h0, i);
            if (i > 0) {
                sequence.append(" -> ");
            }
            sequence.append(idx);

            if (table[idx].state == SlotState.OCCUPIED && table[idx].id == id) {
                System.out.println("Duplicate product ID " + id + " already exists at index " + idx + "; insertion rejected.");
                return;
            }
            if (table[idx].state == SlotState.DELETED && firstDeletedIndex == -1) {
                firstDeletedIndex = idx;
                sequence.append(" [DELETED, saved]");
            }
            if (table[idx].state == SlotState.EMPTY) {
                emptyIndex = idx;
                sequence.append(" [EMPTY, stop]");
                break;
            }
        }

        int insertIndex = (firstDeletedIndex != -1) ? firstDeletedIndex : emptyIndex;
        if (insertIndex == -1) {
            throw new IllegalStateException("No available slot to insert product " + id);
        }

        table[insertIndex].setOccupied(id, name);

        System.out.println("Part (a) Initial hash index h(" + id + ") = " + id + " mod " + TABLE_SIZE + " = " + h0);
        System.out.println("Part (b) Probing sequence (duplicate-safe): " + sequence);
        System.out.println("         Final insertion position         : index " + insertIndex);
        System.out.println("Part (c) Reused DELETED slot?             : " + (firstDeletedIndex != -1 ? "YES (reused index " + firstDeletedIndex + " after confirming no duplicate)" : "NO"));
    }

    static void printTable(Slot[] table) {
        System.out.println("\nPart (d) Updated Hash Table After Inserting 153 (Green Tea):");
        System.out.printf("%-6s | %-20s%n", "Index", "Slot Content");
        System.out.println("-------+---------------------");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.printf("%-6d | %-20s%n", i, table[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Question 4: Inserting New Product 153 (Green Tea) ===");
        Slot[] table = buildTableAfterDeletionOf114();
        insertNoDuplicates(table, 153, "Green Tea");
        printTable(table);
    }
}
