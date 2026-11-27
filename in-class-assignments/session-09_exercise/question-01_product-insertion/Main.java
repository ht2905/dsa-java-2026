/**
 * Session 09 Exercise - Question 1: Product Insertion (Quadratic Probing)
 *
 * Problem Summary:
 * Insert six products (101, 114, 127, 102, 140, 115) in order into an initially
 * empty open-addressing hash table of size m = 13 using:
 *   h(k)    = k mod 13
 *   h(k, i) = (h(k) + i^2) mod 13, for i = 0, 1, 2, ...
 * Record each product's initial hash index, probing sequence, collision count,
 * and final storage index, then print the resulting hash table.
 *
 * Design Decisions:
 * - Use a fixed-size array of Product entries representing the m = 13 slots.
 * - Use Math.floorMod(key, TABLE_SIZE) for h(k) so the modulo result is always
 *   non-negative even if negative keys were ever passed.
 * - Bound probing to TABLE_SIZE attempts so insertion terminates cleanly if a
 *   slot cannot be found.
 *
 * Complexity:
 * - Time Complexity: O(1) expected per insertion at low load factor (alpha <= 0.5);
 *   O(m) worst-case per insertion.
 * - Space Complexity: O(m) for the hash table array of size m = 13.
 */
public class Main {

    private static final int TABLE_SIZE = 13;

    static class Product {
        final int id;
        final String name;

        Product(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + " (" + name + ")";
        }
    }

    static class InsertTrace {
        final Product product;
        final int initialHash;
        final String probingSequence;
        final int collisions;
        final int finalIndex;

        InsertTrace(Product product, int initialHash, String probingSequence, int collisions, int finalIndex) {
            this.product = product;
            this.initialHash = initialHash;
            this.probingSequence = probingSequence;
            this.collisions = collisions;
            this.finalIndex = finalIndex;
        }
    }

    static int primaryHash(int key) {
        return Math.floorMod(key, TABLE_SIZE);
    }

    static int probeIndex(int initialHash, int step) {
        return (initialHash + step * step) % TABLE_SIZE;
    }

    static InsertTrace insert(Product[] table, Product product) {
        int initialHash = primaryHash(product.id);
        StringBuilder sequence = new StringBuilder();

        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(initialHash, i);
            if (i > 0) {
                sequence.append(" -> ");
            }
            sequence.append(idx);

            if (table[idx] == null) {
                table[idx] = product;
                return new InsertTrace(product, initialHash, sequence.toString(), i, idx);
            }
        }

        throw new IllegalStateException("Insertion failed for product " + product.id + ": no reachable empty slot.");
    }

    static void printTable(Product[] table) {
        System.out.println("\nFinal Hash Table (m = " + TABLE_SIZE + "):");
        System.out.printf("%-6s | %-20s%n", "Index", "Slot Content");
        System.out.println("-------+---------------------");
        for (int i = 0; i < TABLE_SIZE; i++) {
            String content = (table[i] == null) ? "EMPTY" : table[i].toString();
            System.out.printf("%-6d | %-20s%n", i, content);
        }
    }

    public static void main(String[] args) {
        Product[] table = new Product[TABLE_SIZE];
        Product[] incoming = {
            new Product(101, "Rice"),
            new Product(114, "Milk"),
            new Product(127, "Bread"),
            new Product(102, "Eggs"),
            new Product(140, "Coffee"),
            new Product(115, "Orange Juice")
        };

        System.out.println("=== Question 1: Product Insertion (Quadratic Probing, m = 13) ===");
        System.out.printf("%-10s | %-12s | %-22s | %-10s | %-11s%n",
                "Product ID", "Initial Hash", "Probing Sequence", "Collisions", "Final Index");
        System.out.println("-----------+--------------+------------------------+------------+------------");

        for (Product product : incoming) {
            InsertTrace trace = insert(table, product);
            System.out.printf("%-10d | %-12d | %-22s | %-10d | %-11d%n",
                    trace.product.id, trace.initialHash, trace.probingSequence, trace.collisions, trace.finalIndex);
        }

        printTable(table);
    }
}
