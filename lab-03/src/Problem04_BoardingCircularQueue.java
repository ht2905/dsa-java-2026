import java.util.ArrayList;
import java.util.List;

public class Problem04_BoardingCircularQueue {

    // Custom fixed-capacity circular array queue as required by Problem 4
    public static class CircularQueue {
        private final int[] a;
        private final int capacity;
        private int front;
        private int size;

        public CircularQueue(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("Capacity K must be at least 1");
            }
            this.capacity = capacity;
            this.a = new int[capacity];
            this.front = 0;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return capacity;
        }

        public int frontIndex() {
            return front;
        }

        // ENQ x: adds ticket; returns false if full (prints FULL)
        public boolean enqueue(int x) {
            if (isFull()) {
                return false;
            }
            int rear = (front + size) % capacity;
            a[rear] = x;
            size++;
            return true;
        }

        // SERVE: removes and returns oldest ticket; returns null if empty (prints EMPTY)
        public Integer serve() {
            if (isEmpty()) {
                return null;
            }
            int result = a[front];
            front = (front + 1) % capacity;
            size--;
            return result;
        }

        // FRONT: inspects oldest ticket without removing; returns null if empty (prints EMPTY)
        public Integer front() {
            if (isEmpty()) {
                return null;
            }
            return a[front];
        }

        // Logical contents from front to rear
        public String logicalQueueString() {
            if (isEmpty()) return "[]";
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(a[(front + i) % capacity]);
                if (i < size - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static List<String> processCommands(int capacity, String[] commands, boolean printTrace) {
        CircularQueue queue = new CircularQueue(capacity);
        List<String> outputs = new ArrayList<>();

        if (printTrace) {
            System.out.println("  Trace (Command | Output | Front Idx | Size | Logical Order):");
        }

        for (String cmdLine : commands) {
            String trimmed = cmdLine.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            String op = parts[0];

            switch (op) {
                case "ENQ": {
                    int x = Integer.parseInt(parts[1]);
                    boolean ok = queue.enqueue(x);
                    if (!ok) {
                        outputs.add("FULL");
                        if (printTrace) {
                            System.out.printf("    %-10s | %-6s | front=%-2d | size=%-2d | %s%n",
                                    trimmed, "FULL", queue.frontIndex(), queue.size(), queue.logicalQueueString());
                        }
                    } else if (printTrace) {
                        System.out.printf("    %-10s | %-6s | front=%-2d | size=%-2d | %s%n",
                                trimmed, "-", queue.frontIndex(), queue.size(), queue.logicalQueueString());
                    }
                    break;
                }
                case "SERVE": {
                    Integer served = queue.serve();
                    if (served == null) {
                        outputs.add("EMPTY");
                        if (printTrace) {
                            System.out.printf("    %-10s | %-6s | front=%-2d | size=%-2d | %s%n",
                                    trimmed, "EMPTY", queue.frontIndex(), queue.size(), queue.logicalQueueString());
                        }
                    } else {
                        outputs.add(String.valueOf(served));
                        if (printTrace) {
                            System.out.printf("    %-10s | %-6d | front=%-2d | size=%-2d | %s%n",
                                    trimmed, served, queue.frontIndex(), queue.size(), queue.logicalQueueString());
                        }
                    }
                    break;
                }
                case "FRONT": {
                    Integer frontVal = queue.front();
                    if (frontVal == null) {
                        outputs.add("EMPTY");
                        if (printTrace) {
                            System.out.printf("    %-10s | %-6s | front=%-2d | size=%-2d | %s%n",
                                    trimmed, "EMPTY", queue.frontIndex(), queue.size(), queue.logicalQueueString());
                        }
                    } else {
                        outputs.add(String.valueOf(frontVal));
                        if (printTrace) {
                            System.out.printf("    %-10s | %-6d | front=%-2d | size=%-2d | %s%n",
                                    trimmed, frontVal, queue.frontIndex(), queue.size(), queue.logicalQueueString());
                        }
                    }
                    break;
                }
                case "SIZE": {
                    int sz = queue.size();
                    outputs.add(String.valueOf(sz));
                    if (printTrace) {
                        System.out.printf("    %-10s | %-6d | front=%-2d | size=%-2d | %s%n",
                                trimmed, sz, queue.frontIndex(), queue.size(), queue.logicalQueueString());
                    }
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown command: " + op);
            }
        }

        return outputs;
    }

    private static void runTest(String label, int capacity, String[] commands, boolean trace) {
        System.out.println("=== Test Case: " + label + " (Capacity K = " + capacity + ") ===");
        List<String> results = processCommands(capacity, commands, trace);
        System.out.println("Output Result:");
        for (String out : results) {
            System.out.println(out);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example (Traced)
        String[] handoutCommands = {
                "ENQ 17",
                "ENQ 29",
                "ENQ 33",
                "ENQ 46",
                "SERVE",
                "ENQ 46",
                "FRONT",
                "SIZE"
        };
        runTest("Normal - Handout Worked Example (Traced Wrap-Around)", 3, handoutCommands, true);

        // 2. Boundary: Capacity K = 1
        String[] kOneCommands = {
                "ENQ 99",
                "ENQ 100", // FULL
                "FRONT",   // 99
                "SERVE",   // 99
                "SERVE",   // EMPTY
                "ENQ 200", // Enqueue after empty
                "FRONT"    // 200
        };
        runTest("Boundary - Single Slot Buffer (K = 1)", 1, kOneCommands, false);

        // 3. Boundary: Queries on empty queue
        String[] emptyQueries = {
                "SERVE",
                "FRONT",
                "SIZE"
        };
        runTest("Boundary - Queries on Empty Queue", 4, emptyQueries, false);

        // 4. Tricky: Multiple complete wrap-arounds and reuses
        String[] multiWrap = {
                "ENQ 1", "ENQ 2", "ENQ 3",
                "SERVE", "SERVE",          // leaves 3 at index 2
                "ENQ 4", "ENQ 5",          // wraps to 0 and 1
                "SERVE", "SERVE", "SERVE", // serves 3, 4, 5
                "SIZE",                    // 0
                "FRONT"                    // EMPTY
        };
        runTest("Tricky - Multiple Full Wrap-Arounds and Reuses", 3, multiWrap, false);
    }
}

