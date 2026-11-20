import java.util.ArrayList;
import java.util.List;

public class Problem07_EditableStopRegistry {

    // Custom singly linked node
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Custom singly linked list with head, tail, and size
    public static class SinglyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public SinglyLinkedList() {
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

        // ADD_FIRST x: insert at head without output
        public void addFirst(int x) {
            Node newNode = new Node(x);
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }

        // ADD_LAST x: append at tail without output
        public void addLast(int x) {
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

        // REMOVE x: deletes first node with value x; prints REMOVED or NOT_FOUND
        public boolean remove(int x) {
            if (isEmpty()) {
                return false;
            }

            // Case 1: Target is at the head
            if (head.value == x) {
                head = head.next;
                size--;
                if (size == 0) {
                    tail = null; // List became empty
                }
                return true;
            }

            // Case 2: Target is in the middle or at tail
            Node prev = head;
            Node curr = head.next;
            while (curr != null) {
                if (curr.value == x) {
                    prev.next = curr.next;
                    // If removed node was the tail, update tail to prev
                    if (curr == tail) {
                        tail = prev;
                    }
                    size--;
                    return true;
                }
                prev = curr;
                curr = curr.next;
            }

            return false; // Not found
        }

        // FIND x: returns 0-based index of first match, or -1
        public int find(int x) {
            Node curr = head;
            int index = 0;
            while (curr != null) {
                if (curr.value == x) {
                    return index;
                }
                curr = curr.next;
                index++;
            }
            return -1;
        }

        // LIST: prints IDs from head to tail separated by spaces, or EMPTY
        public String list() {
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
    }

    public static List<String> processCommands(String[] commands) {
        SinglyLinkedList list = new SinglyLinkedList();
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
                case "FIND": {
                    int target = Integer.parseInt(parts[1]);
                    int idx = list.find(target);
                    outputs.add(String.valueOf(idx));
                    break;
                }
                case "LIST":
                    outputs.add(list.list());
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
                "ADD_FIRST 7",
                "REMOVE 36",
                "FIND 48",
                "LIST"
        };
        runTest("Normal - Handout Worked Example", handoutExample);

        // 2. Boundary: Empty removal and empty queries
        String[] emptyRemoval = {
                "REMOVE 10",
                "FIND 10",
                "LIST"
        };
        runTest("Boundary - Operations on Empty List", emptyRemoval);

        // 3. Boundary: Deleting the only node (head and tail must become null)
        String[] singleNode = {
                "ADD_FIRST 50",
                "LIST",
                "REMOVE 50",
                "LIST",
                "ADD_LAST 60", // Re-add to verify tail was properly reset
                "LIST"
        };
        runTest("Boundary - Insert and Delete the Only Node", singleNode);

        // 4. Boundary: Deleting head and deleting tail
        String[] headTailRemoval = {
                "ADD_LAST 10",
                "ADD_LAST 20",
                "ADD_LAST 30",
                "REMOVE 10", // Delete head
                "LIST",
                "REMOVE 30", // Delete tail
                "LIST"
        };
        runTest("Boundary - Deleting Head and Deleting Tail", headTailRemoval);

        // 5. Tricky: Duplicates (deleting only first match)
        String[] duplicates = {
                "ADD_LAST 5",
                "ADD_LAST 5",
                "ADD_LAST 5",
                "FIND 5",
                "REMOVE 5",
                "FIND 5",
                "LIST"
        };
        runTest("Tricky - Duplicate Values (First Match Removed)", duplicates);
    }
}

