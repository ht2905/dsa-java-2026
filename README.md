# Algorithms & Data Structures (IT013IU)

Undergraduate coursework, algorithm implementations, analytical reports, and problem sets for **IT013IU – Algorithms & Data Structures**.

- **Author:** Tran Quoc Hoang
- **Student ID:** BTBTWE24036
- **Focus:** Applied algorithms and data structures using standard Java, emphasizing algorithmic complexity, proof of correctness, and clean structural design.

---

## Repository Overview

```
dsa-java-2026/
├── AGENTS.md                  # Workspace-specific instructions and coding rules
├── README.md                  # Project overview and documentation
├── docs/                      # Course specifications, problem sets, and review guides
│   ├── lab-01/                # Lab 01 handouts, problem set, and report template
│   ├── lab-02/                # Lab 02 problem descriptions and guidelines
│   ├── lab-03/                # Lab 03 problem set and guidelines
│   ├── lab-04/                # Midterm cheat sheets and review materials
│   └── lab-report-guidelines.md
├── in-class-assignments/      # In-class exercises and live problem quizzes
│   ├── exercise-01_session-04/ # Singly linked list (SLList) implementation
│   ├── exercise-01_session-05/ # Divide-and-conquer Merge Sort (CLRS-style)
│   └── lab-05-quiz/           # Competitive programming problems (VJudge ready)
│       ├── problem-01/        # "Build Stairs" (Greedy non-decreasing sequence)
│       └── problem-02/        # "Balance" (Prefix-sum linear partition)
├── lab-01/                    # Lab 01: Arrays, Subarray Sums & Invariance
│   ├── report/                # Comprehensive analytical report & test results
│   └── src/                   # Problem implementations (Problem 01 - Problem 06)
├── lab-02/                    # Lab 02: Sorting, Binary Search & Sliding Window
│   ├── report/                # Empirical comparisons, swap/shift counters, proofs
│   └── src/                   # Elementary sorts, search utilities, sliding windows
├── lab-03/                    # Lab 03: Linear ADTs, Queues & Monotonic Structures
│   ├── report/                # LIFO/FIFO proofs, amortized analysis, LRU cache
│   └── src/                   # Custom stacks, circular queues, deques, linked lists
├── lecture-slides/            # Official course lecture slides (Lectures 00 - 06)
├── src/                       # Standalone algorithms (e.g., descending binary search)
├── summary-and-cheatsheets/   # First-principles guide & Java quick-reference sheets
│   ├── dsa_midterm-review-book.md # Comprehensive 6-week DSA handbook for non-Java majors
│   └── java-shorthands.md     # Java language idioms, fast I/O, live templates
└── temp/                      # Working scratchpad and test scripts
```

---

## Lab Curriculum & Implementations

### Lab 01: Array Fundamentals & Complexity Analysis
Focuses on invariant verification, prefix/suffix arrays, and single-pass traversals over linear arrays.

| Problem | Title | Primary Technique | Time | Aux Space |
|---|---|---|---|---|
| **Problem 01** | Equal Value Blocks | Single linear pass; boundary transition detection | $O(n)$ | $O(1)$ |
| **Problem 02** | Suffix Maximum Queries | Precomputed suffix maximum array | $O(n + q)$ | $O(n)$ |
| **Problem 03** | Missing Number | Arithmetic progression sum ($\sum_{i=1}^n i - \text{sum}$) | $O(n)$ | $O(1)$ |
| **Problem 04** | First Unique Position | Frequency counting via frequency map | $O(n)$ | $O(k)$ |
| **Problem 05** | All Subarray Sums | Element contribution weighting ($A[i] \cdot (i + 1)(n - i)$) | $O(n)$ | $O(1)$ |
| **Problem 06** | Interleave Halves | In-place / buffer array splitting and interleaving | $O(n)$ | $O(n)$ |

### Lab 02: Sorting, Binary Search & Sliding Window
Covers elementary sorting metrics (exact comparisons, writes, and swaps/shifts), binary search variants, and two-pointer techniques.

| Problem / Class | Topic / Description | Time Complexity | Aux Space | Stability |
|---|---|---|---|---|
| **SortUtils** | Optimized Bubble Sort (early stop flag) | Best: $O(n)$, Worst: $O(n^2)$ | $O(1)$ | Stable |
| **SortUtils** | Selection Sort (minimum scan) | Best: $O(n^2)$, Worst: $O(n^2)$ | $O(1)$ | Unstable |
| **SortUtils** | Insertion Sort (right-shifting key) | Best: $O(n)$, Worst: $O(n^2)$ | $O(1)$ | Stable |
| **SearchUtils** | Standard Binary Search (`binarySearch`) | $O(\log n)$ | $O(1)$ | N/A |
| **SearchUtils** | Lower Bound (`lowerBound` / first $\ge target$) | $O(\log n)$ | $O(1)$ | N/A |
| **SearchUtils** | Upper Bound (`upperBound` / first $> target$) | $O(\log n)$ | $O(1)$ | N/A |
| **SearchUtils** | Exact Occurrence Range (`countOccurrences`) | $O(\log n)$ | $O(1)$ | N/A |
| **Problem 07** | Quietest Machine Slot (Fixed Sliding Window) | $O(n)$ | $O(1)$ | N/A |
| **Problem 09** | Longest Playlist Limit (Dynamic Sliding Window) | $O(n)$ | $O(1)$ | N/A |
| **Problem 10** | Shortest Upload Target (Dynamic Sliding Window) | $O(n)$ | $O(1)$ | N/A |
| **Problem 12** | Archive Record Locator (Saddleback 2D Search) | $O(R + C)$ | $O(1)$ | N/A |

### Lab 03: Stacks, Queues, Linked Lists & Monotonic Structures
Explores abstract data types, resizing amortized analysis, pointer-based collections, and optimal monotonic structures.

| Problem | Structure / Pattern | Key Mechanics | Complexity |
|---|---|---|---|
| **Problem 01** | `IntStack` (Dynamic Array Stack) | Geometric doubling ($2n$) on overflow; amortized $O(1)$ push | Push: $O(1)$ amortized<br>Pop/Peek: $O(1)$ |
| **Problem 02** | Route Undo/Redo Engine | Dual stack architecture (`history` + `redo`) | $O(1)$ per operation |
| **Problem 03** | Shipping Manifest Bracket Check | Matching brackets with character stack | $O(n)$ time, $O(n)$ space |
| **Problem 04** | Boarding Circular Queue | Array-based circular ring buffer with modulo indexing (`(head + 1) % cap`) | $O(1)$ enqueue / dequeue |
| **Problem 05** | Peak Passenger Window | Monotonic Decreasing Deque (`ArrayDeque`) for sliding window maximum | $O(n)$ time, $O(k)$ space |
| **Problem 06** | Next Busier Service Period | Monotonic Decreasing Stack for Next Greater Element (NGE) | $O(n)$ time, $O(n)$ space |
| **Problem 07** | Editable Stop Registry | Singly Linked List with head, tail, and targeted deletion | $O(1)$ head/tail, $O(n)$ search |
| **Problem 09** | Bidirectional Itinerary List | Doubly Linked List with bidirectional pointers | $O(1)$ insert/remove at node |
| **Problem 10** | Station Incident Queue | FIFO queue via singly linked list with sentinel tail maintenance | $O(1)$ enqueue / dequeue |
| **Problem 12** | Recently Used Station Cache | LRU Cache combining hash index and Doubly Linked List eviction | $O(1)$ get / put |

---

## In-Class Assignments & Quizzes

- **Session 04 – Singly Linked List (`in-class-assignments/exercise-01_session-04/`):**
  - Custom `SLList` implementation illustrating recursive/iterative node structures and front insertion.
- **Session 05 – Merge Sort (`in-class-assignments/exercise-01_session-05/`):**
  - Divide-and-conquer sorting following the CLRS specification with sentinels (`Integer.MAX_VALUE`), achieving optimal $\Theta(n \log n)$ time.
- **Lab 05 Quiz Solutions (`in-class-assignments/lab-05-quiz/`):**
  - **Problem 01 – Build Stairs (`Main.java`):** Greedy sequence verification; reduces heights by at most 1 from left to right to enforce non-decreasing ordering in $O(N)$ time and $O(N)$ space.
  - **Problem 02 – Balance (`Main.java`):** Optimal split point minimizing $|S_1 - S_2|$ across contiguous weight arrays using prefix sum difference evaluation in $O(N)$ time and $O(1)$ auxiliary space.

---

## Design Principles & Course Philosophy

1. **Clarity & Readability Over Premature Optimization:**
   Code is structured cleanly with explicit variable naming, intuitive flow control, and comprehensive problem statements. Designed specifically for clear learning and pedagogical review.
2. **Zero Non-Standard Dependencies:**
   All solutions rely strictly on Java's core library (`java.util`, `java.io`). No external frameworks are required.
3. **Rigorous Asymptotic Complexity:**
   Every problem implementation is accompanied by formal time and auxiliary space complexity evaluations, backed by empirical operation counters where relevant.
4. **Exhaustive Testing:**
   Each lab problem is validated against three tiers of test cases:
   - **Normal Cases:** Standard operational inputs and representative scenarios.
   - **Boundary Cases:** Empty arrays ($n = 0$), single-element sets ($n = 1$), minimum/maximum values, and full capacities.
   - **Adversarial / Tricky Cases:** Strictly reversed orders, identical elements, alternating sequences, and negative IDs.

---

## How to Build and Run

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Standard terminal shell (`bash`, `zsh`)

### Compiling and Running Standalone Solutions

Compile and run any Java file directly using `javac` and `java`:

```bash
# Example: Run Lab 02 Problem 01 (Sorting comparison driver)
javac lab-02/src/Problem01_CalibrationOffsetOrdering.java lab-02/src/SortUtils.java
java -cp lab-02/src Problem01_CalibrationOffsetOrdering

# Example: Run Lab 03 Problem 05 (Peak Passenger Window - Sliding Window Maximum)
javac lab-03/src/Problem05_PeakPassengerWindow.java
java -cp lab-03/src Problem05_PeakPassengerWindow

# Example: Run Lab 05 Quiz Problem 02 (Balance)
javac in-class-assignments/lab-05-quiz/problem-02/Main.java
java -cp in-class-assignments/lab-05-quiz/problem-02 Main
```

### Running Test Cases
For problems accepting standard input (e.g., in-class quizzes or Lab 03 interactive command streams), provide inputs interactively or redirect from a file:

```bash
java -cp in-class-assignments/lab-05-quiz/problem-01 Main < input.txt
```

---

## Reference Materials & Guides

- **Midterm Master Handbook (`summary-and-cheatsheets/dsa_midterm-review-book.md`):**
  - A 2,400+ line first-principles guide tailored specifically for bioinformaticians and non-Java majors transitioning to DSA.
  - Covers memory layout (Stack vs. Heap), pointer/reference semantics, Big-O mechanics, array patterns, sorting proofs, and recursive call frames.
- **Java Shorthands Cheatsheet (`summary-and-cheatsheets/java-shorthands.md`):**
  - Fast I/O patterns, IntelliJ/VS Code live templates, lambda comparators, heap/map idioms, and debugging helpers.
- **Course Lecture Slides (`lecture-slides/`):**
  - `lecture-00_overview.pdf`: Course structure, expectations, and grading.
  - `lecture-01_oop-review.pdf`: Object-oriented fundamentals in Java.
  - `lecture-02_arrays.pdf`: Array memory models and elementary operations.
  - `lecture-03.1_simple-sorting.pdf`: Bubble, selection, and insertion sorts.
  - `lecture-03.2_algorithm-analysis.pdf`: Big-O, Big-$\Omega$, Big-$\Theta$, and empirical profiling.
  - `lecture-04_stacks-and-queues.pdf`: LIFO/FIFO mechanics, array vs. linked implementations.
  - `lecture-05_linked-list.pdf`: Singly and doubly linked list architectures.
  - `lecture-06_recursion.pdf`: Call stack mechanics and recurrence analysis.
- **Midterm Review Cheatsheets (`docs/lab-04/`):**
  - Summary reference sheets covering algorithm analysis and core data structures.
- **Textbook Alignment:** Cormen, Leiserson, Rivest, Stein (CLRS) – *Introduction to Algorithms*.
