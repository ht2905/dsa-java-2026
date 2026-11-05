# Java Shorthands & Live Templates Cheatsheet
### Essential Code Snippets, Postfix Completions, and Language Idioms for DSA & Everyday Java Development

---

## Table of Contents
1. [Introduction](#1-introduction)
2. [IntelliJ IDEA Live Templates (Standard Java Snippets)](#2-intellij-idea-live-templates-standard-java-snippets)
   - [2.1 Console Output & Logging](#21-console-output--logging)
   - [2.2 Entry Point / Main Method](#22-entry-point--main-method)
   - [2.3 Loops & Iteration (DSA Essentials)](#23-loops--iteration-dsa-essentials)
   - [2.4 Conditionals & Null Checks](#24-conditionals--null-checks)
   - [2.5 Variable & Constant Declarations](#25-variable--constant-declarations)
3. [IntelliJ IDEA Postfix Code Completion](#3-intellij-idea-postfix-code-completion)
4. [VS Code & Eclipse Java Snippets Cross-Reference](#4-vs-code--eclipse-java-snippets-cross-reference)
5. [Modern Java Language Shorthands & Syntactic Sugar](#5-modern-java-language-shorthands--syntactic-sugar)
6. [DSA-Specific Java Quick Idioms](#6-dsa-specific-java-quick-idioms)
   - [6.1 Fast Printing & Debugging](#61-fast-printing--debugging)
   - [6.2 Array & Matrix Operations](#62-array--matrix-operations)
   - [6.3 Custom Sorting & Comparators](#63-custom-sorting--comparators)
   - [6.4 Heaps & Priority Queues](#64-heaps--priority-queues)
   - [6.5 Frequency Counting & Maps](#65-frequency-counting--maps)
   - [6.6 Fast I/O Boilerplate for Online Judges](#66-fast-io-boilerplate-for-online-judges)
7. [Essential IDE Productivity Shortcuts](#7-essential-ide-productivity-shortcuts)

---

## 1. Introduction

In Java development—especially when implementing **Data Structures and Algorithms (DSA)**, participating in coding interviews, or completing lab assignments—speed and muscle memory matter.

Java shorthands generally fall into three categories:
1. **IDE Live Templates / Snippets**: Type an abbreviation like `sout` or `fori` and press `Tab` (or `Enter` / `Space`) to expand into full boilerplate code.
2. **Postfix Completions**: Type an expression followed by a dot `.` and a keyword (e.g., `arr.fori` or `res.sout`) to wrap the expression in boilerplate automatically.
3. **Java Language Idioms**: Modern syntax patterns (`var`, lambdas, records, `getOrDefault`, `merge`) that eliminate verbose ceremony.

---

## 2. IntelliJ IDEA Live Templates (Standard Java Snippets)

Type the abbreviation and press `Tab` or `Enter`.

### 2.1 Console Output & Logging

| Abbreviation | Expansion | Notes & Usage |
| :--- | :--- | :--- |
| `sout` | `System.out.println();` | Prints a blank line or wraps text with newline |
| `souf` | `System.out.printf("");` | Formatted output (printf) |
| `serr` | `System.err.println();` | Prints to standard error stream (red text in console) |
| `soutm` | `System.out.println("ClassName.methodName");` | Prints current class name and method (great for tracing recursion) |
| `soutp` | `System.out.println("param1 = " + param1 + ", param2 = " + param2);` | Prints current method parameter names and their runtime values |
| `soutv` | `System.out.println("variable = " + variable);` | Prints the nearest variable name and its value |

```java
// Example: tracing recursion in DSA
public int fib(int n) {
    soutp + Tab; // -> System.out.println("n = " + n);
    if (n <= 1) return n;
    int ans = fib(n - 1) + fib(n - 2);
    soutv + Tab; // -> System.out.println("ans = " + ans);
    return ans;
}
```

---

### 2.2 Entry Point / Main Method

| Abbreviation | Expansion | Notes |
| :--- | :--- | :--- |
| `psvm` | `public static void main(String[] args) { }` | Classic IntelliJ shorthand |
| `main` | `public static void main(String[] args) { }` | Modern IntelliJ & VS Code shortcut |

---

### 2.3 Loops & Iteration (DSA Essentials)

| Abbreviation | Expansion | Usage Scenario |
| :--- | :--- | :--- |
| `fori` | `for (int i = 0; i < max; i++) { }` | Standard forward indexed loop |
| `iter` | `for (Type item : collection) { }` | Enhanced for-each loop over array / iterable |
| `itar` | `for (int i = 0; i < array.length; i++) { Type item = array[i]; }` | Iterates array by index, extracting current element |
| `ritar` | `for (int i = array.length - 1; i >= 0; i--) { Type item = array[i]; }` | Reverse array iteration |
| `itli` | `for (int i = 0; i < list.size(); i++) { Type item = list.get(i); }` | Indexed loop over a `List` |
| `itin` | `for (Iterator<Type> iterator = coll.iterator(); iterator.hasNext(); )` | Iterator-based traversal |

```java
// Using fori and ritar
int[] arr = {10, 20, 30, 40};

// Type 'fori' + Tab:
for (int i = 0; i < arr.length; i++) {
    // Forward pass
}

// Type 'ritar' + Tab:
for (int i = arr.length - 1; i >= 0; i--) {
    int val = arr[i];
    // Backward pass
}
```

---

### 2.4 Conditionals & Null Checks

| Abbreviation | Expansion | Usage |
| :--- | :--- | :--- |
| `ifn` | `if (target == null) { }` | If-null guard clause |
| `inn` | `if (target != null) { }` | If-not-null check |
| `inst` | `if (expr instanceof Type) { Type var = (Type) expr; }` | Instance-of check and cast |
| `lazy` | `if (field == null) { field = new Type(); } return field;` | Lazy initialization pattern |

---

### 2.5 Variable & Constant Declarations

| Abbreviation | Expansion | Usage |
| :--- | :--- | :--- |
| `psf` | `public static final` | Public constant modifier prefix |
| `psfi` | `public static final int` | Integer constant (e.g. `MOD = 1_000_000_007`) |
| `psfs` | `public static final String` | String constant |
| `prsf` | `private static final` | Private constant prefix |
| `const` | `private static final int` | Private integer constant |

---

## 3. IntelliJ IDEA Postfix Code Completion

Postfix completion lets you transform an already-typed expression into a statement or block without jumping back to the beginning of the line.

Type: `<expression>.<postfix_keyword>` and press `Tab` or `Enter`.

| Postfix Shorthand | Input Expression | Generated Output |
| :--- | :--- | :--- |
| `.sout` | `ans.sout` | `System.out.println(ans);` |
| `.soutv` | `ans.soutv` | `System.out.println("ans = " + ans);` |
| `.var` | `new ArrayList<Integer>().var` | `ArrayList<Integer> list = new ArrayList<Integer>();` |
| `.for` / `.fori` | `arr.length.fori` | `for (int i = 0; i < arr.length; i++) { }` |
| `.forr` | `arr.length.forr` | `for (int i = arr.length; i > 0; i--) { }` |
| `.iter` | `list.iter` | `for (Integer item : list) { }` |
| `.null` | `head.null` | `if (head == null) { }` |
| `.notnull` / `.nn` | `head.nn` | `if (head != null) { }` |
| `.if` | `(count > 0).if` | `if (count > 0) { }` |
| `.else` | `(count > 0).else` | `if (count <= 0) { }` |
| `.while` | `(ptr != null).while` | `while (ptr != null) { }` |
| `.return` | `res.return` | `return res;` |
| `.throw` | `new IllegalArgumentException().throw` | `throw new IllegalArgumentException();` |
| `.try` | `Integer.parseInt(s).try` | `try { Integer.parseInt(s); } catch (...) { }` |
| `.cast` | `obj.cast` | `((TargetType) obj)` |
| `.arg` | `val.arg` | Wraps `val` as argument in outer method call |

---

## 4. VS Code & Eclipse Java Snippets Cross-Reference

If you work in **VS Code** (with the Red Hat Java extension) or **Eclipse**, here is how the shortcuts correspond:

| Purpose | IntelliJ IDEA | VS Code (Java Ext) | Eclipse |
| :--- | :--- | :--- | :--- |
| **Print Line** | `sout` + Tab | `sysout` + Tab | `sysout` + Ctrl+Space |
| **Print Variable** | `soutv` + Tab | N/A | N/A |
| **Print Error** | `serr` + Tab | `syserr` + Tab | `syserr` + Ctrl+Space |
| **Main Method** | `psvm` / `main` | `main` + Tab | `main` + Ctrl+Space |
| **For Loop (Index)** | `fori` + Tab | `fori` + Tab | `for` (select index template) |
| **For-Each Loop** | `iter` + Tab | `fore` or `foreach` + Tab | `foreach` + Ctrl+Space |
| **While Loop** | `while` + Tab | `while` + Tab | `while` + Ctrl+Space |
| **Do-While Loop** | `do` + Tab | `do` + Tab | `do` + Ctrl+Space |
| **Try-Catch** | `try` + Tab | `try_catch` + Tab | `try` + Ctrl+Space |

---

## 5. Modern Java Language Shorthands & Syntactic Sugar

Modern Java (Java 11, 17, 21+) provides concise syntax features that significantly speed up DSA coding:

### 5.1 Local Variable Type Inference (`var`)
Eliminates repeating long generic types:
```java
// Verbose
Map<String, List<Integer[]>> graph = new HashMap<String, List<Integer[]>>();

// Shorthand with var (Java 10+)
var graph = new HashMap<String, List<Integer[]>>();
var pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]));
```

### 5.2 Ternary Operator
Inline if-else for assignments:
```java
// Shorthand
int max = (a > b) ? a : b;
String label = (val % 2 == 0) ? "EVEN" : "ODD";
```

### 5.3 Switch Expression (Java 14+)
Replaces bulky multi-line `switch-case-break`:
```java
int days = switch (month) {
    case 2 -> isLeap ? 29 : 28;
    case 4, 6, 9, 11 -> 30;
    default -> 31;
};
```

### 5.4 Records (Java 16+)
One-line immutable data carrier (perfect for Graph nodes, Coordinate pairs, or Tree return states):
```java
// Replaces 40 lines of boilerplate (constructor, getters, equals, hashCode, toString)
record Point(int r, int c) {}
record Edge(int to, int weight) {}
record NodeState(int maxLen, int sum) {}

// Usage in BFS / Dijkstra:
Queue<Point> queue = new ArrayDeque<>();
queue.offer(new Point(0, 0));
```

### 5.5 Collection Factory Methods (Java 9+)
Quick immutable collections for test cases or lookup tables:
```java
var vowels = Set.of('a', 'e', 'i', 'o', 'u');
var directions = List.of(new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1});
var charMap = Map.of('(', ')', '{', '}', '[', ']');
```

### 5.6 Lambda Expressions & Method References
```java
// Method references
list.forEach(System.out::println);
numbers.sort(Integer::compareTo);

// Lambda comparator
Arrays.sort(arr, (x, y) -> y - x); // Descending order
```

---

## 6. DSA-Specific Java Quick Idioms

When working on algorithms, memorizing these specific one-liners saves immense time:

### 6.1 Fast Printing & Debugging

```java
// Print 1D Array
System.out.println(Arrays.toString(nums));

// Print 2D Array / Grid
System.out.println(Arrays.deepToString(grid));

// Print List, Set, or Map directly
System.out.println(list);
System.out.println(map);
```

### 6.2 Array & Matrix Operations

```java
// Quick initialization / reset DP array:
Arrays.fill(dp, -1);

// Fill 2D DP matrix:
for (int[] row : dp2d) {
    Arrays.fill(row, -1);
}

// Quick copy of an array:
int[] copy = Arrays.copyOf(original, original.length);
int[] subArray = Arrays.copyOfRange(original, start, end); // [start, end)
```

### 6.3 Custom Sorting & Comparators

```java
// 1. Sort 2D intervals by start time (ascending):
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

// 2. Sort intervals by start time asc, then by end time desc:
Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(b[1], a[1]));

// 3. Sort objects by multiple criteria using Comparator chaining:
list.sort(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName));
```

> **Note on Integer subtraction:** Prefer `Integer.compare(a, b)` over `a - b` to prevent integer overflow bugs when numbers have large opposing signs.

### 6.4 Heaps & Priority Queues

```java
// Min Heap (default in Java):
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap:
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Min Heap for pair [node, cost] sorted by cost:
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
```

### 6.5 Frequency Counting & Maps

```java
Map<Integer, Integer> freq = new HashMap<>();

// Pattern 1: getOrDefault (Standard DSA shorthand)
freq.put(val, freq.getOrDefault(val, 0) + 1);

// Pattern 2: merge (Ultra-concise modern Java shorthand)
freq.merge(val, 1, Integer::sum);

// Pattern 3: computeIfAbsent (For Adjacency List Graphs)
Map<Integer, List<Integer>> adj = new HashMap<>();
adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
```

### 6.6 Fast I/O Boilerplate for Online Judges

In coding platforms (LeetCode, Codeforces, HackerRank), `Scanner` is often too slow and causes Time Limit Exceeded (TLE). Use `BufferedReader` and `StringTokenizer`:

```java
import java.io.*;
import java.util.*;

public class FastIOExample {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        // Read input
        // int n = in.nextInt();
        // out.println(result);

        out.flush(); // Crucial: Always flush buffered output before termination!
    }
}
```

---

## 7. Essential IDE Productivity Shortcuts

| Action | IntelliJ IDEA (Linux/Win) | VS Code (Linux/Win) |
| :--- | :--- | :--- |
| **Generate Code** (Constructor, Getters, Setters, toString, equals) | `Alt + Insert` | `Ctrl + .` (Quick Fix / Source Action) |
| **Surround With** (`if`, `try-catch`, `while`, `for`) | `Ctrl + Alt + T` | `Ctrl + Shift + P` -> "Surround With" |
| **Rename Symbol** (variable, method, class) | `Shift + F6` | `F2` |
| **Reformat Code** | `Ctrl + Alt + L` | `Shift + Alt + F` |
| **Optimize Imports** (removes unused imports) | `Ctrl + Alt + O` | `Shift + Alt + O` |
| **Duplicate Current Line** | `Ctrl + D` | `Shift + Alt + Down` |
| **Delete Line** | `Ctrl + Y` | `Ctrl + Shift + K` |
| **Move Line Up/Down** | `Shift + Alt + Up/Down` | `Alt + Up/Down` |
| **Show Method Parameter Info** | `Ctrl + P` | `Ctrl + Shift + Space` |
| **Quick Documentation Lookup** | `Ctrl + Q` | `Ctrl + K, Ctrl + I` (Hover) |
| **Comment / Uncomment Line** | `Ctrl + /` | `Ctrl + /` |
| **Block Comment** | `Ctrl + Shift + /` | `Shift + Alt + A` |
| **Search Everywhere / Command Palette** | Double `Shift` | `Ctrl + Shift + P` |

