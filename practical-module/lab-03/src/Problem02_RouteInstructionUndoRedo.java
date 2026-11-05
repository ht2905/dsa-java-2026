import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Problem02_RouteInstructionUndoRedo {

    public static class RouteEditor {
        // history stack stores currently active instructions (newest at top/head)
        private final Deque<String> history = new ArrayDeque<>();
        // redo stack stores undone instructions available to be restored
        private final Deque<String> redo = new ArrayDeque<>();

        // WRITE x appends one nonempty token, clears redo history, prints nothing
        public void write(String token) {
            history.push(token);
            redo.clear(); // Any new route edit clears the redo branch
        }

        // UNDO removes latest active token onto redo; prints NOTHING if history empty
        public String undo() {
            if (history.isEmpty()) {
                return "NOTHING";
            }
            String token = history.pop();
            redo.push(token);
            return null; // Valid undo prints nothing
        }

        // REDO restores latest undone token onto history; prints NOTHING if redo empty
        public String redo() {
            if (redo.isEmpty()) {
                return "NOTHING";
            }
            String token = redo.pop();
            history.push(token);
            return null; // Valid redo prints nothing
        }

        // SHOW displays active tokens in chronological order (oldest to newest), or EMPTY
        public String show() {
            if (history.isEmpty()) {
                return "EMPTY";
            }
            StringBuilder sb = new StringBuilder();
            // descendingIterator traverses from bottom (oldest) to top (newest)
            Iterator<String> it = history.descendingIterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }

    public static List<String> processCommands(String[] commands, boolean printTrace) {
        RouteEditor editor = new RouteEditor();
        List<String> outputs = new ArrayList<>();

        if (printTrace) {
            System.out.println("  Trace (Command | Output | History Bottom->Top | Redo Top):");
        }

        for (String cmdLine : commands) {
            String trimmed = cmdLine.trim();
            if (trimmed.isEmpty()) continue;

            String[] parts = trimmed.split("\\s+");
            String op = parts[0];

            switch (op) {
                case "WRITE": {
                    String token = parts[1];
                    editor.write(token);
                    if (printTrace) {
                        System.out.printf("    %-15s | %-8s | %-20s | %s%n",
                                trimmed, "-", editor.show(), editor.redo.peek());
                    }
                    break;
                }
                case "UNDO": {
                    String res = editor.undo();
                    if (res != null) {
                        outputs.add(res);
                        if (printTrace) {
                            System.out.printf("    %-15s | %-8s | %-20s | %s%n",
                                    trimmed, res, editor.show(), editor.redo.peek());
                        }
                    } else if (printTrace) {
                        System.out.printf("    %-15s | %-8s | %-20s | %s%n",
                                trimmed, "-", editor.show(), editor.redo.peek());
                    }
                    break;
                }
                case "REDO": {
                    String res = editor.redo();
                    if (res != null) {
                        outputs.add(res);
                        if (printTrace) {
                            System.out.printf("    %-15s | %-8s | %-20s | %s%n",
                                    trimmed, res, editor.show(), editor.redo.peek());
                        }
                    } else if (printTrace) {
                        System.out.printf("    %-15s | %-8s | %-20s | %s%n",
                                trimmed, "-", editor.show(), editor.redo.peek());
                    }
                    break;
                }
                case "SHOW": {
                    String text = editor.show();
                    outputs.add(text);
                    if (printTrace) {
                        System.out.printf("    %-15s | %-8s | %-20s | %s%n",
                                trimmed, text, editor.show(), editor.redo.peek());
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
        // 1. Handout Worked Example (Traced)
        String[] handoutExample = {
                "WRITE NORTH",
                "WRITE EAST",
                "UNDO",
                "SHOW",
                "REDO",
                "SHOW",
                "UNDO",
                "WRITE DEPOT",
                "REDO",
                "SHOW"
        };
        runTest("Normal - Handout Worked Example (Traced)", handoutExample, true);

        // 2. Boundary: Undo on empty history
        String[] emptyUndo = {
                "UNDO",
                "SHOW"
        };
        runTest("Boundary - Undo on Empty History", emptyUndo, false);

        // 3. Boundary: Redo before any undo
        String[] redoBeforeUndo = {
                "WRITE START",
                "REDO",
                "SHOW"
        };
        runTest("Boundary - Redo Before Any Undo", redoBeforeUndo, false);

        // 4. Tricky: Multiple undos and redos
        String[] multipleUndos = {
                "WRITE A",
                "WRITE B",
                "WRITE C",
                "UNDO",
                "UNDO",
                "SHOW",
                "REDO",
                "SHOW",
                "REDO",
                "SHOW"
        };
        runTest("Normal - Multiple Undos and Redos", multipleUndos, false);

        // 5. Tricky: Clearing redo after a new edit
        String[] clearRedoBranch = {
                "WRITE STEP1",
                "WRITE STEP2",
                "UNDO",
                "WRITE STEP3", // must clear redo (STEP2 is discarded)
                "REDO",        // must output NOTHING
                "SHOW"
        };
        runTest("Tricky - New Write Clears Redo Branch", clearRedoBranch, false);
    }
}

