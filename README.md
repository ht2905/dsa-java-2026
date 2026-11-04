# Algorithms & Data Structures (IT013IU)

Undergraduate coursework, algorithm implementations, analytical reports, and problem sets for **IT013IU – Algorithms & Data Structures**.

- **Author:** Tran Quoc Hoang
- **Student ID:** BTBTWE24036
- **Focus:** Applied algorithms and data structures using standard Java, emphasizing algorithmic complexity, proof of correctness, and clean structural design.

---

## Repository Overview

```text
dsa-java-2026/
├── docs/                      # Course specifications and laboratory guidelines
├── in-class-assignments/      # In-class algorithmic exercises and quizzes
├── lab-*/                     # Laboratory implementations, test drivers, and formal reports
├── lecture-slides/            # Official course theoretical slides
├── src/                       # Standalone algorithmic implementations
└── summary-and-cheatsheets/   # First-principles review guides and Java quick reference
```

---

## Curriculum Structure & Problem Sets

All detailed problem breakdowns, algorithmic classifications, and formal time and auxiliary space complexity bounds are cataloged in [`problem-index.md`](problem-index.md).

- **Lab 01 – Array Fundamentals & Invariant Verification:**
  Single-pass prefix and suffix arrays, missing element arithmetic progressions, frequency indexing, and subarray element-contribution weighting.
- **Lab 02 – Sorting, Binary Search & Sliding Windows:**
  Comparison counts and swap profiling across Bubble, Selection, and Insertion sorts; standard binary search with lower/upper bound variants; dynamic two-pointer and sliding window subarrays.
- **Lab 03 – Abstract Data Types, Stacks, Queues & Monotonic Structures:**
  Dynamic resizing arrays with amortized $O(1)$ scaling; circular ring buffers; monotonic deques for sliding window extrema; monotonic stacks for Next Greater Element (NGE) evaluation; and LRU Cache combining hash maps with doubly linked lists.
- **In-Class Assignments & Competitions:**
  CLRS-aligned divide-and-conquer Merge Sort with sentinels, custom singly linked lists (`SLList`), and competitive programming greedy and prefix-sum evaluations.

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

- **Problem Specifications & Complexity Index:** Detailed operation counts and asymptotic bounds cataloged in [`problem-index.md`](problem-index.md).
- **Midterm Master Handbook (`summary-and-cheatsheets/dsa_midterm-review-book.md`):**
  A first-principles guide tailored for bioinformaticians transitioning to DSA, covering memory models (Stack vs. Heap), reference semantics, Big-O mechanics, and recursion frames.
- **Java Shorthands Cheatsheet (`summary-and-cheatsheets/java-shorthands.md`):**
  Fast I/O patterns, IDE live templates, lambda comparators, and heap/map idioms.
- **Midterm Review Cheatsheets (`docs/lab-04/`):**
  Summary reference sheets covering algorithm analysis and core data structures.
- **Course Lecture Slides (`lecture-slides/`):**
  Curriculum slide presentations spanning computational complexity, linear collections, sorting algorithms, and recursion trees.
- **Textbook Alignment:** Cormen, Leiserson, Rivest, Stein (CLRS) – *Introduction to Algorithms*.
