# Algorithms & Data Structures (IT013IU)

Undergraduate coursework, algorithm implementations, analytical reports, and problem sets for **IT013IU – Algorithms & Data Structures**.

- **Author:** Tran Quoc Hoang
- **Student ID:** BTBTWE24036
- **Focus:** Applied algorithms and data structures using standard Java, emphasizing algorithmic complexity, proof of correctness, and clean structural design.

---

## Repository Overview

```text
dsa-java-2026/
├── final-project/             # Final project guidelines, topic catalog, and brainstorm plans
├── in-class-assignments/      # In-class algorithmic exercises and quizzes
├── lecture-slides/            # Official course theoretical slides
├── midterm-review/            # Comprehensive review guide, cheatsheets, and exam solutions
├── practical-modules/         # Laboratory specifications, problem sets, implementations, and reports
└── src/                       # Standalone algorithmic implementations
```

---

## Curriculum Structure & Problem Sets

Overview of algorithmic techniques and complexity evaluations across the curriculum:

- **Lab 01 – Array Fundamentals & Invariant Verification:**
  Single-pass prefix and suffix arrays, missing element arithmetic progressions, frequency indexing, and subarray element-contribution weighting.
- **Lab 02 – Sorting, Binary Search & Sliding Windows:**
  Comparison counts and swap profiling across Bubble, Selection, and Insertion sorts; standard binary search with lower/upper bound variants; dynamic two-pointer and sliding window subarrays.
- **Lab 03 – Abstract Data Types, Stacks, Queues & Monotonic Structures:**
  Dynamic resizing arrays with amortized $O(1)$ scaling; circular ring buffers; monotonic deques for sliding window extrema; monotonic stacks for Next Greater Element (NGE) evaluation; and LRU Cache combining hash maps with doubly linked lists.
- **Lab 06 – Algorithmic Problem Solving (VJudge):**
  Prefix sums, binary search on the answer, difference arrays, and greedy optimization problem sets.
- **Lab 07 – Trees, Heaps & Graph/Tree Traversals:**
  Complete binary trees, rooted trees, preorder/inorder/postorder tree walks, Binary Search Trees (insertion & search), priority queues, tree reconstruction, and tree parity/distance traversals.
- **In-Class Assignments & Final Project:**
  CLRS-aligned divide-and-conquer Merge Sort with sentinels, custom singly linked lists (`SLList`), session exercises, and final project design plans (`final-project/`).

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

Compile and run Java source files directly using `javac` and `java`:

```bash
# Example: Run Lab 02 Problem 01 (Sorting comparison driver)
javac practical-modules/lab-02/src/Problem01_CalibrationOffsetOrdering.java practical-modules/lab-02/src/SortUtils.java
java -cp practical-modules/lab-02/src Problem01_CalibrationOffsetOrdering

# Example: Run Lab 03 Problem 05 (Peak Passenger Window - Sliding Window Maximum)
javac practical-modules/lab-03/src/Problem05_PeakPassengerWindow.java
java -cp practical-modules/lab-03/src Problem05_PeakPassengerWindow

# Example: Run Lab 07 Problem A (Complete Binary Tree)
javac practical-modules/lab-07/src/problem-a_complete-binary-tree/Main.java
java -cp practical-modules/lab-07/src/problem-a_complete-binary-tree Main

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

- **Midterm Master Handbook (`midterm-review/review-guide/midterm-review-book.md`):**
  A first-principles guide tailored for bioinformaticians transitioning to DSA, covering memory models (Stack vs. Heap), reference semantics, Big-O mechanics, and recursion frames.
- **Java Shorthands Cheatsheet (`midterm-review/cheatsheets/java-shorthands.md`):**
  Fast I/O patterns, IDE live templates, lambda comparators, and heap/map idioms.
- **Exam Cheatsheets (`midterm-review/cheatsheets/4-column/`):**
  Calibrated 8-page 4-column Typst cheatsheets with algorithmic templates and reversal rules.
- **Midterm Review Cheatsheets (`practical-modules/docs/lab-04/`):**
  Summary reference sheets covering algorithm analysis and core data structures.
- **Course Lecture Slides (`lecture-slides/`):**
  Curriculum slide presentations spanning computational complexity, linear collections, sorting algorithms, and recursion trees.
- **Textbook Alignment:** Cormen, Leiserson, Rivest, Stein (CLRS) – *Introduction to Algorithms*.
