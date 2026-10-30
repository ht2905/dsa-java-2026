import java.util.ArrayList;
import java.util.List;

public class Problem09_BidirectionalItineraryList {

    // Custom doubly linked node
    public static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    // Custom doubly linked list with head, tail, and size
    public static class DoublyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public DoublyLinkedList() {
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

        // ADD_FIRST x: insert at head, repair prev and next
        public void addFirst(int x) {
            Node newNode = new Node(x);
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }

        // ADD_LAST x: insert at tail, repair prev and next
        public void addLast(int x) {
            Node newNode = new Node(x);
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            size++;
        }

        // REMOVE x: deletes first occurrence of x, repairs both directions
        public boolean remove(int x) {
            if (isEmpty()) {
                return false;
            }

            Node curr = head;
            while (curr != null) {
                if (curr.value == x) {
                    // Node found, unlink it
                    if (curr == head) {
                        head = head.next;
                        if (head != null) {
                            head.prev = null;
                        } else {
                            tail = null; // List became empty
                        }
                    } else if (curr == tail) {
                        tail = tail.prev;
                        tail.next = null;
                    } else {
                        // Interior node: link neighbors together
                        curr.prev.next = curr.next;
                        curr.next.prev = curr.prev;
                    }
                    size--;
                    return true;
                }
                curr = curr.next;
            }

            return false; // Not found
        }

        // FORWARD: prints head-to-tail stop IDs, or EMPTY
        public String forward() {
            if (isEmpty()) {
                return "EMPTY";
            }
            StringBuilder sb = new StringBuilder();
            Node curr = head;
            while (curr != null) {
                sb.append(curr.value);
                if (curr.next != null) {
                    sb.append(" ");
                }
                curr = curr.next;
            }
            return sb.toString();
        }

        // BACKWARD: prints tail-to-head stop IDs, or EMPTY
        public String backward() {
            if (isEmpty()) {
                return "EMPTY";
            }
            StringBuilder sb = new StringBuilder();
            Node curr = tail;
            while (curr != null) {
                sb.append(curr.value);
                if (curr.prev != null) {
                    sb.append(" ");
                }
                curr = curr.prev;
            }
            return sb.toString();
        }
    }

    public static List<String> processCommands(String[] commands) {
        DoublyLinkedList list = new DoublyLinkedList();
        List<String> outputs = new ArrayList<>();

        for (String cmdLine : commands) {
            String trimmed = cmdLine.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            String op = parts[0];

            switch (op) {
                case "ADD_FIRST":
                    list.addFirst(Integer.parseInt(parts[1]));
                    break;
                case "ADD_LAST":
                    list.addLast(Integer.parseInt(parts[1]));
                    break;
                case "REMOVE": {
                    int target = Integer.parseInt(parts[1]);
                    boolean ok = list.remove(target);
                    outputs.add(ok ? "REMOVED" : "NOT_FOUND");
                    break;
                }
                case "FORWARD":
                    outputs.add(list.forward());
                    break;
                case "BACKWARD":
                    outputs.add(list.backward());
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
                "ADD_LAST 36",
                "ADD_LAST 48",
                "ADD_LAST 59",
                "REMOVE 48",
                "ADD_FIRST 7",
                "FORWARD",
                "BACKWARD"
        };
        runTest("Normal - Handout Worked Example", handoutExample);

        // 2. Boundary: Empty list operations
        String[] emptyList = {
                "FORWARD",
                "BACKWARD",
                "REMOVE 100"
        };
        runTest("Boundary - Operations on Empty List", emptyList);

        // 3. Boundary: Singleton insert and removal
        String[] singleton = {
                "ADD_FIRST 99",
                "FORWARD",
                "BACKWARD",
                "REMOVE 99",
                "FORWARD",
                "BACKWARD"
        };
        runTest("Boundary - Singleton Insertion and Deletion", singleton);

        // 4. Boundary: Deletion at both ends (head and tail)
        String[] endDeletions = {
                "ADD_LAST 10",
                "ADD_LAST 20",
                "ADD_LAST 30",
                "REMOVE 10", // Delete head
                "FORWARD",
                "BACKWARD",
                "REMOVE 30", // Delete tail
                "FORWARD",
                "BACKWARD"
        };
        runTest("Boundary - Delete Head and Delete Tail", endDeletions);

        // 5. Tricky: Alternating head and tail additions, with duplicate keys
        String[] alternatingAndDuplicates = {
                "ADD_LAST 20",
                "ADD_FIRST 10",
                "ADD_LAST 20", // Duplicate 20
                "FORWARD",
                "BACKWARD",
                "REMOVE 20",   // Removes only first 20
                "FORWARD",
                "BACKWARD"
        };
        runTest("Tricky - Alternating Additions & Duplicate Removal", alternatingAndDuplicates);
    }
}

