import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem A: Complete Binary Tree (Aizu ALDS1_9_A)
 *
 * Problem Summary:
 * Given a binary heap of size H stored in a 1-indexed array A[1..H], print for
 * every node i (from 1 to H) its key, its parent's key (if it has a parent),
 * its left child's key (if it has a left child), and its right child's key
 * (if it has a right child).
 *
 * Plain-English Explanation & Design Decisions:
 * - A complete binary tree packs nodes level by level from left to right, so
 *   we do not need node objects or pointers at all — a plain array is enough.
 * - By storing the root at index 1 instead of index 0, the index formulas stay
 *   clean and match CLRS Chapter 6 directly:
 *     - parent(i) = i / 2        (exists when i > 1)
 *     - left(i)   = 2 * i        (exists when 2 * i <= H)
 *     - right(i)  = 2 * i + 1    (exists when 2 * i + 1 <= H)
 * - Note the exact output format required by Aizu: every printed line ends
 *   with a comma followed by a single trailing space.
 *
 * Complexity Analysis:
 * - Time Complexity: O(H) — we visit each of the H nodes once and do O(1) index
 *   arithmetic to look up its parent and children.
 * - Space Complexity: O(H) to store the 1-indexed heap array.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int heapSize = in.nextInt();

        // Use 1-based indexing so parent/child formulas are i/2, 2*i, 2*i+1
        int[] heap = new int[heapSize + 1];
        for (int i = 1; i <= heapSize; i++) {
            heap[i] = in.nextInt();
        }

        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= heapSize; i++) {
            out.append("node ").append(i).append(": key = ").append(heap[i]).append(", ");

            int parentIdx = i / 2;
            if (parentIdx >= 1) {
                out.append("parent key = ").append(heap[parentIdx]).append(", ");
            }

            int leftIdx = 2 * i;
            if (leftIdx <= heapSize) {
                out.append("left key = ").append(heap[leftIdx]).append(", ");
            }

            int rightIdx = 2 * i + 1;
            if (rightIdx <= heapSize) {
                out.append("right key = ").append(heap[rightIdx]).append(", ");
            }

            out.append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
