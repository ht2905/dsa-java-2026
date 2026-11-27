/**
 * Session 09 Exercise - Question 2: Product Search (Quadratic Probing)
 *
 * Problem Summary:
 * After inserting the six initial products into the m = 13 quadratic-probing
 * hash table, search for product IDs 127, 140, and 166. For each query, report
 * the probing sequence, total number of probes, and whether the search succeeded.
 *
 * Design Decisions:
 * - Search follows the exact same probe sequence h(k, i) = (h(k) + i^2) mod 13
 *   used during insertion.
 * - Because no deletions have occurred yet, encountering a null (EMPTY) slot
 *   immediately proves the key is absent and stops the search.
 *
 * Complexity:
 * - Time Complexity: O(1) expected per search; O(m) worst-case.
 * - Space Complexity: O(m) for the hash table array.
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

    static class SearchTrace {
        final int queryId;
        final String probingSequence;
        final int probes;
        final Product foundProduct;
        final int foundIndex;

        SearchTrace(int queryId, String probingSequence, int probes, Product foundProduct, int foundIndex) {
            this.queryId = queryId;
            this.probingSequence = probingSequence;
            this.probes = probes;
            this.foundProduct = foundProduct;
            this.foundIndex = foundIndex;
        }
    }

    static int primaryHash(int key) {
        return Math.floorMod(key, TABLE_SIZE);
    }

    static int probeIndex(int initialHash, int step) {
        return (initialHash + step * step) % TABLE_SIZE;
    }

    static void insert(Product[] table, Product product) {
        int initialHash = primaryHash(product.id);
        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(initialHash, i);
            if (table[idx] == null) {
                table[idx] = product;
                return;
            }
        }
        throw new IllegalStateException("Insertion failed for product " + product.id);
    }

    static SearchTrace search(Product[] table, int targetId) {
        int initialHash = primaryHash(targetId);
        StringBuilder sequence = new StringBuilder();

        for (int i = 0; i < TABLE_SIZE; i++) {
            int idx = probeIndex(initialHash, i);
            if (i > 0) {
                sequence.append(" -> ");
            }
            sequence.append(idx);

            if (table[idx] == null) {
                // First EMPTY slot terminates the search: key cannot lie further down this probe chain.
                return new SearchTrace(targetId, sequence.toString(), i + 1, null, -1);
            }
            if (table[idx].id == targetId) {
                return new SearchTrace(targetId, sequence.toString(), i + 1, table[idx], idx);
            }
        }

        return new SearchTrace(targetId, sequence.toString(), TABLE_SIZE, null, -1);
    }

    public static void main(String[] args) {
        Product[] table = new Product[TABLE_SIZE];
        Product[] initialProducts = {
            new Product(101, "Rice"),
            new Product(114, "Milk"),
            new Product(127, "Bread"),
            new Product(102, "Eggs"),
            new Product(140, "Coffee"),
            new Product(115, "Orange Juice")
        };

        for (Product product : initialProducts) {
            insert(table, product);
        }

        int[] queries = {127, 140, 166};

        System.out.println("=== Question 2: Product Search (Quadratic Probing, m = 13) ===");
        System.out.printf("%-10s | %-24s | %-6s | %-30s%n",
                "Product ID", "Probing Sequence", "Probes", "Search Result");
        System.out.println("-----------+--------------------------+--------+-------------------------------");

        for (int id : queries) {
            SearchTrace trace = search(table, id);
            String result = (trace.foundProduct != null)
                    ? "Successful: " + trace.foundProduct + " at index " + trace.foundIndex
                    : "Unsuccessful (hit EMPTY slot)";
            System.out.printf("%-10d | %-24s | %-6d | %-30s%n",
                    trace.queryId, trace.probingSequence, trace.probes, result);
        }
    }
}
