import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Problem01_CargoScanStack {

    // Custom dynamic array stack as required by Problem 1
    public static class IntStack {
        private int[] a;
        private int size;

        public IntStack() {
            this(2); // Initial array capacity is 2 as specified
        }

        public IntStack(int initialCapacity) {
            this.a = new int[initialCapacity];
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return a.length;
        }

        public void push(int x) {
            // When full, double capacity before inserting
            if (size == a.length) {
                int newCap = a.length * 2;
                int[] newA = new int[newCap];
                for (int i = 0; i < size; i++) {
                    newA[i] = a[i];
                }
                a = newA;
            }
            a[size++] = x;
        }

        public int peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            return a[size - 1];
        }

        public int pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            int val = a[size - 1];
            size--;
            return val;
        }
    }

    // Process a list of commands and return the output lines
    public static List<String> processCommands(String[] commands, boolean printTrace) {
        IntStack stack = new IntStack();
        List<String> outputs = new ArrayList<>();

        if (printTrace) {
            System.out.println("  Trace (Command | Output | Stack Size | Physical Capacity):");
        }

        for (String cmdLine : commands) {
            String trimmed = cmdLine.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            String op = parts[0];

            switch (op) {
                case "SCAN": {
                    int x = Integer.parseInt(parts[1]);
                    stack.push(x);
                    if (printTrace) {
                        System.out.printf("    %-12s | %-7s | size=%-2d | cap=%-2d%n",
                                trimmed, "-", stack.size(), stack.capacity());
                    }
                    break;
                }
                case "UNDO": {
                    if (stack.isEmpty()) {
                        outputs.add("EMPTY");
                        if (printTrace) {
                            System.out.printf("    %-12s | %-7s | size=%-2d | cap=%-2d%n",
                                    trimmed, "EMPTY", stack.size(), stack.capacity());
                        }
                    } else {
                        int removed = stack.pop();
                        outputs.add(String.valueOf(removed));
                        if (printTrace) {
                            System.out.printf("    %-12s | %-7d | size=%-2d | cap=%-2d%n",
                                    trimmed, removed, stack.size(), stack.capacity());
                        }
                    }
                    break;
                }
                case "LATEST": {
                    if (stack.isEmpty()) {
                        outputs.add("EMPTY");
                        if (printTrace) {
                            System.out.printf("    %-12s | %-7s | size=%-2d | cap=%-2d%n",
                                    trimmed, "EMPTY", stack.size(), stack.capacity());
                        }
                    } else {
                        int latest = stack.peek();
                        outputs.add(String.valueOf(latest));
                        if (printTrace) {
                            System.out.printf("    %-12s | %-7d | size=%-2d | cap=%-2d%n",
                                    trimmed, latest, stack.size(), stack.capacity());
                        }
                    }
                    break;
                }
                case "COUNT": {
                    int count = stack.size();
                    outputs.add(String.valueOf(count));
                    if (printTrace) {
                        System.out.printf("    %-12s | %-7d | size=%-2d | cap=%-2d%n",
                                trimmed, count, stack.size(), stack.capacity());
                    }
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown command: " + op);
            }
        }

        return outputs;
    }

    private static void runTest(String label, String[] commands, boolean trace) {
        System.out.println("=== Test Case: " + label + " ===");
        List<String> results = processCommands(commands, trace);
        System.out.println("Output Result:");
        for (String out : results) {
            System.out.println(out);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Sample (Traced)
        String[] handoutExample = {
                "SCAN 45",
                "SCAN 12",
                "LATEST",
                "UNDO",
                "LATEST",
                "COUNT",
                "UNDO",
                "UNDO"
        };
        runTest("Normal - Handout Worked Example (Traced)", handoutExample, true);

        // 2. Boundary: Empty command stream
        runTest("Boundary - Empty Command Stream", new String[]{}, false);

        // 3. Boundary: Operations on empty stack
        String[] emptyStackOps = {
                "LATEST",
                "UNDO",
                "COUNT"
        };
        runTest("Boundary - Queries on Empty Stack", emptyStackOps, false);

        // 4. Tricky: Multiple resizes across power-of-two boundaries (2 -> 4 -> 8 -> 16)
        String[] resizeStress = {
                "SCAN 10",
                "SCAN 20",
                "SCAN 30", // triggers resize to 4
                "SCAN 40",
                "SCAN 50", // triggers resize to 8
                "COUNT",
                "LATEST"
        };
        runTest("Tricky - Repeated Resizes Beyond Initial Capacity (Traced)", resizeStress, true);

        // 5. Tricky: Zero and negative IDs (must not be treated as empty sentinels)
        String[] zeroAndNegatives = {
                "SCAN 0",
                "LATEST",
                "SCAN -999",
                "LATEST",
                "UNDO",
                "LATEST",
                "UNDO",
                "UNDO"
        };
        runTest("Tricky - Zero and Negative Container IDs", zeroAndNegatives, false);

        // 6. Normal: Clear then reuse stack
        String[] clearAndReuse = {
                "SCAN 100",
                "UNDO",
                "LATEST",
                "SCAN 200",
                "LATEST",
                "COUNT"
        };
        runTest("Normal - Clear and Reuse Stack", clearAndReuse, false);
    }
}

