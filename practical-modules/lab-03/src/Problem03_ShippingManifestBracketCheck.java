import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Problem03_ShippingManifestBracketCheck {

    // Validates matching brackets in a single line using a Deque stack
    public static boolean validateBrackets(String line) {
        if (line == null) return true;
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            // Push opening delimiters
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // Check closing delimiters against top of stack
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false; // Closing bracket with no opening bracket
                }
                char top = stack.pop();
                if (ch == ')' && top != '(') return false;
                if (ch == ']' && top != '[') return false;
                if (ch == '}' && top != '{') return false;
            }
            // Non-bracket characters are ignored
        }

        // Accept only if no unmatched opening brackets remain
        return stack.isEmpty();
    }

    public static List<String> processLines(String[] lines) {
        List<String> results = new ArrayList<>();
        for (String line : lines) {
            boolean valid = validateBrackets(line);
            results.add(valid ? "VALID" : "INVALID");
        }
        return results;
    }

    private static void runTest(String label, String[] lines) {
        System.out.println("=== Test Case: " + label + " ===");
        List<String> verdicts = processLines(lines);
        for (int i = 0; i < lines.length; i++) {
            System.out.printf("  Line %d: %-25s -> %s%n", (i + 1), "\"" + lines[i] + "\"", verdicts.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Handout Sample
        String[] handoutSample = {
                "route{stop[1](A)}",
                "([)]",
                "manifest[(3)]",
                "(()",
                "cargo text"
        };
        runTest("Normal - Handout Worked Example", handoutSample);

        // 2. Boundary: Empty line and plain text without brackets
        String[] boundaryEmptyAndNoBrackets = {
                "",
                "   ",
                "CONTAINER_1234_NO_BRACKETS"
        };
        runTest("Boundary - Empty Lines & Text Without Brackets", boundaryEmptyAndNoBrackets);

        // 3. Boundary: Closer before opener
        String[] closerBeforeOpener = {
                ")",
                "]abc",
                "}route{"
        };
        runTest("Boundary - Closer Arrives Before Opener", closerBeforeOpener);

        // 4. Tricky: Unmatched openers remaining at end
        String[] unmatchedOpeners = {
                "((((",
                "{[()",
                "route[stop(1)"
        };
        runTest("Tricky - Unmatched Openers Remaining at End", unmatchedOpeners);

        // 5. Tricky: Deeply nested and interleaved brackets
        String[] deeplyNested = {
                "{{{[([()])]}}}",
                "{{{[([()])]}}",  // Missing one closing brace
                "([{}])()[{()}]"
        };
        runTest("Tricky - Deeply Nested Delimiters", deeplyNested);
    }
}

