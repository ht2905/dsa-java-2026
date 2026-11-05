import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem12_RecentlyUsedStationCache {

    // Doubly linked list node storing key and value
    public static class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    // LRU Cache combining HashMap with a custom Doubly Linked List
    public static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> map;
        private Node head; // Most recently used (MRU)
        private Node tail; // Least recently used (LRU)

        public LRUCache(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("Capacity K must be at least 1");
            }
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.head = null;
            this.tail = null;
        }

        // Unlink an existing node from the doubly linked list
        private void removeNode(Node node) {
            if (node == head) {
                head = node.next;
            } else {
                node.prev.next = node.next;
            }

            if (node == tail) {
                tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }

            node.prev = null;
            node.next = null;
        }

        // Insert a node at the head (mark as most recently used)
        private void addToHead(Node node) {
            node.next = head;
            node.prev = null;
            if (head != null) {
                head.prev = node;
            }
            head = node;
            if (tail == null) {
                tail = node;
            }
        }

        // Move an existing node to the head
        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        // Evict the least recently used node (at the tail)
        private Node removeTail() {
            Node lru = tail;
            removeNode(lru);
            return lru;
        }

        // GET key: returns cached value or null if missing; marks key as MRU
        // Note: Returns Integer so we can distinguish missing from a stored -1
        public Integer get(int key) {
            if (!map.containsKey(key)) {
                return null;
            }
            Node node = map.get(key);
            moveToHead(node);
            return node.value;
        }

        // PUT key value: updates existing or inserts new; evicts LRU if at capacity
        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                if (map.size() == capacity) {
                    Node lru = removeTail();
                    map.remove(lru.key);
                }
                Node newNode = new Node(key, value);
                addToHead(newNode);
                map.put(key, newNode);
            }
        }
    }

    public static List<String> processCommands(int capacity, String[] commands) {
        LRUCache cache = new LRUCache(capacity);
        List<String> outputs = new ArrayList<>();

        for (String cmdLine : commands) {
            String trimmed = cmdLine.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            String op = parts[0];

            switch (op) {
                case "PUT": {
                    int k = Integer.parseInt(parts[1]);
                    int v = Integer.parseInt(parts[2]);
                    cache.put(k, v);
                    break;
                }
                case "GET": {
                    int k = Integer.parseInt(parts[1]);
                    Integer val = cache.get(k);
                    outputs.add(val == null ? "-1" : String.valueOf(val));
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown command: " + op);
            }
        }

        return outputs;
    }

    private static void runTest(String label, int capacity, String[] commands) {
        System.out.println("=== Test Case: " + label + " (Capacity K = " + capacity + ") ===");
        List<String> results = processCommands(capacity, commands);
        System.out.println("Output Result:");
        for (String out : results) {
            System.out.println(out);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Worked Example
        String[] handoutExample = {
                "PUT 4 40",
                "PUT 8 80",
                "GET 4",
                "PUT 12 120",
                "GET 8",
                "GET 12"
        };
        runTest("Normal - Handout Worked Example", 2, handoutExample);

        // 2. Boundary: Capacity K = 1
        String[] capOne = {
                "PUT 10 100",
                "GET 10",
                "PUT 20 200", // Evicts 10
                "GET 10",     // -1 (missing)
                "GET 20"      // 200
        };
        runTest("Boundary - Single Slot Cache (K = 1)", 1, capOne);

        // 3. Normal: Update existing key without evicting
        String[] updateExisting = {
                "PUT 1 10",
                "PUT 2 20",
                "PUT 1 999", // Overwrite key 1 value, refresh to MRU
                "PUT 3 30",  // Evicts key 2 (key 1 was refreshed)
                "GET 1",     // 999
                "GET 2",     // -1
                "GET 3"      // 30
        };
        runTest("Normal - Updating Existing Key (Refreshing Recency)", 2, updateExisting);

        // 4. Tricky: Storing value -1 (distinguishing stored -1 from missing -1)
        String[] storedNegativeOne = {
                "PUT 7 -1",
                "GET 7",    // Should print -1 (present with value -1)
                "GET 99"    // Should print -1 (missing)
        };
        runTest("Tricky - Distinguishing Stored Value -1 from Missing Key", 2, storedNegativeOne);
    }
}

