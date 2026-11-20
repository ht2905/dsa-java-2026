import java.util.ArrayList;
import java.util.List;

public class Problem10_StationIncidentLinkedQueue {

    // Custom singly linked node
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Custom singly linked queue with head, tail, and size
    public static class LinkedQueue {
        private Node head;
        private Node tail;
        private int size;

        public LinkedQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // ENQ x: attach at tail in O(1) without traversing
        public void enqueue(int x) {
            Node newNode = new Node(x);
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        // SERVE: remove and return head in O(1); reset tail = null if empty; return null if empty
        public Integer serve() {
            if (isEmpty()) {
                return null;
            }
            int result = head.value;
            head = head.next;
            size--;
            if (size == 0) {
                tail = null; // Critical: reset tail pointer when queue becomes empty
            }
            return result;
        }

        // FRONT: inspect head in O(1) without removing; return null if empty
        public Integer front() {
            if (isEmpty()) {
                return null;
            }
            return head.value;
        }
    }

    public static List<String> processCommands(String[] commands) {
        LinkedQueue queue = new LinkedQueue();
        List<String> outputs = new ArrayList<>();

        for (String cmdLine : commands) {
            String trimmed = cmdLine.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            String op = parts[0];

            switch (op) {
                case "ENQ":
                    queue.enqueue(Integer.parseInt(parts[1]));
                    break;
                case "SERVE": {
                    Integer val = queue.serve();
                    outputs.add(val == null ? "EMPTY" : String.valueOf(val));
                    break;
                }
                case "FRONT": {
                    Integer val = queue.front();
                    outputs.add(val == null ? "EMPTY" : String.valueOf(val));
                    break;
                }
                case "SIZE":
                    outputs.add(String.valueOf(queue.size()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command: " + op);
            }
        }

        return outputs;
    }

    private static void runTest(String label, String[] commands) {
        System.out.println("=== Test Case: " + label + " ===");
        List<String> results = processCommands(commands);
        System.out.println("Output Result:");
        for (String out : results) {
            System.out.println(out);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example
        String[] handoutExample = {
                "ENQ 19",
                "ENQ 26",
                "SERVE",
                "ENQ 38",
                "FRONT",
                "SIZE",
                "SERVE",
                "SERVE",
                "SERVE"
        };
        runTest("Normal - Handout Worked Example", handoutExample);

        // 2. Boundary: Empty removal and queries on empty queue
        String[] emptyQueue = {
                "SERVE",
                "FRONT",
                "SIZE"
        };
        runTest("Boundary - Queries on Empty Queue", emptyQueue);

        // 3. Boundary: Single item enqueued and served (tail reset check)
        String[] singleItem = {
                "ENQ 55",
                "FRONT",
                "SIZE",
                "SERVE",
                "FRONT",
                "SIZE",
                "ENQ 99", // Re-enqueue after empty to verify tail wasn't corrupted
                "FRONT"
        };
        runTest("Boundary - Single Item Enqueue and Serve (Tail Reset)", singleItem);

        // 4. Tricky: 1,000 alternating operations
        List<String> alternatingOps = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            alternatingOps.add("ENQ " + i);
            alternatingOps.add("SERVE");
        }
        alternatingOps.add("SIZE");
        alternatingOps.add("FRONT");
        runTest("Tricky - 1,000 Alternating Enqueue and Serve Operations",
                alternatingOps.toArray(new String[0]));
    }
}

