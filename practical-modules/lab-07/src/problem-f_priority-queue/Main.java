import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem F: Priority Queue (Aizu ALDS1_9_C)
 *
 * Problem Summary:
 * Maintain a max-priority queue supporting up to 2,000,000 operations:
 *   - "insert k": add integer k into the priority queue.
 *   - "extract":  remove and print the largest integer currently in the queue.
 *   - "end":      stop reading input.
 *
 * Plain-English Explanation & Design Decisions:
 * - A binary Max-Heap is a nearly complete binary tree stored in a 1-indexed
 *   array where every parent is >= both of its children. That guarantees the
 *   largest element is always sitting at index 1 (the root).
 * - insert(k) (CLRS MAX-HEAP-INSERT):
 *     Put k at the next open slot at the end of the array, then repeatedly
 *     swap it upward with its parent ("bubble up") until its parent is >= k
 *     or it reaches the root.
 * - extractMax() (CLRS HEAP-EXTRACT-MAX):
 *     Save heap[1] as the answer, move the last element into heap[1], shrink
 *     the heap size by 1, and repeatedly swap it downward with its larger
 *     child ("sift down" / MAX-HEAPIFY) until the max-heap property is restored.
 * - Implementing the heap with a primitive int[] instead of Java's boxed
 *   PriorityQueue<Integer> avoids creating up to 2,000,000 Integer objects and
 *   keeps the code directly aligned with CLRS Chapter 6.
 *
 * Complexity Analysis (for M <= 2,000,000 operations and current heap size N):
 * - insert:     O(log N) time, O(1) extra space.
 * - extractMax: O(log N) time, O(1) extra space.
 * - Total Time Complexity: O(M log M); Space Complexity: O(M).
 */
public class Main {

    private static final int MAX_OPERATIONS = 2_000_000;

    static class MaxHeap {
        // 1-indexed array: root is at index 1
        private final int[] heap;
        private int size = 0;

        MaxHeap(int capacity) {
            this.heap = new int[capacity + 1];
        }

        void insert(int key) {
            size++;
            int i = size;
            heap[i] = key;

            // Bubble up while the new key is larger than its parent
            while (i > 1 && heap[i / 2] < heap[i]) {
                swap(i, i / 2);
                i = i / 2;
            }
        }

        int extractMax() {
            int max = heap[1];
            heap[1] = heap[size];
            size--;
            siftDown(1);
            return max;
        }

        private void siftDown(int i) {
            while (true) {
                int left = 2 * i;
                int right = 2 * i + 1;
                int largest = i;

                if (left <= size && heap[left] > heap[largest]) {
                    largest = left;
                }
                if (right <= size && heap[right] > heap[largest]) {
                    largest = right;
                }
                if (largest == i) {
                    break;
                }
                swap(i, largest);
                i = largest;
            }
        }

        private void swap(int a, int b) {
            int tmp = heap[a];
            heap[a] = heap[b];
            heap[b] = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        MaxHeap pq = new MaxHeap(MAX_OPERATIONS);

        while (true) {
            String command = in.next();
            if (command == null || command.equals("end")) {
                break;
            }
            if (command.equals("insert")) {
                pq.insert(in.nextInt());
            } else if (command.equals("extract")) {
                out.println(pq.extractMax());
            }
        }

        out.flush();
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
