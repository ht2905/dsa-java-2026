# DATA STRUCTURES AND ALGORITHMS: THE COMPLETE MIDTERM MASTER BOOK
## A Rigorous, First-Principles Guide for Bioinformaticians and Non-Java Programmers
### Covering Course Material from Week 00 through Week 06 (Recursion & Merge Sort)

---

## Table of Contents
1. [Preface: Java Survival Kit & Mental Models for Bioinformaticians](#preface-java-survival-kit--mental-models-for-bioinformaticians)
   - [1.1 Why Bioinformaticians Need Data Structures & Algorithms](#11-why-bioinformaticians-need-data-structures--algorithms)
   - [1.2 Java vs. Python/R: The Mental Shift](#12-java-vs-pythonr-the-mental-shift)
   - [1.3 Anatomy of a Minimal Java Program](#13-anatomy-of-a-minimal-java-program)
   - [1.4 Primitives vs. Object References: The "Street Address" Analogy](#14-primitives-vs-object-references-the-street-address-analogy)
   - [1.5 Memory Architecture: The JVM Call Stack vs. The Heap](#15-memory-architecture-the-jvm-call-stack-vs-the-heap)
   - [1.6 How Java Allocates Arrays](#16-how-java-allocates-arrays)
2. [Chapter 1: Algorithm Analysis, Asymptotics, and Growth Rates](#chapter-1-algorithm-analysis-asymptotics-and-growth-rates)
   - [2.1 The Philosophy: Program = Algorithms + Data Structures](#21-the-philosophy-program--algorithms--data-structures)
   - [2.2 Why Wall-Clock Time Lies: The RAM Computational Model](#22-why-wall-clock-time-lies-the-ram-computational-model)
   - [2.3 The Hierarchy of Growth Rates](#23-the-hierarchy-of-growth-rates)
   - [2.4 Formal Asymptotic Notations: Big-O, Big-Omega, Big-Theta, and Little-o](#24-formal-asymptotic-notations-big-o-big-omega-big-theta-and-little-o)
   - [2.5 Input Cases: Best, Average, Worst, and Amortized Time](#25-input-cases-best-average-worst-and-amortized-time)
   - [2.6 Space Complexity: Stored Data vs. Auxiliary Space vs. Call Stack](#26-space-complexity-stored-data-vs-auxiliary-space-vs-call-stack)
   - [2.7 Rules for Counting Loops from Code](#27-rules-for-counting-loops-from-code)
   - [2.8 Case Study: Maximum Contiguous Subsequence Sum (The 4 Generations)](#28-case-study-maximum-contiguous-subsequence-sum-the-4-generations)
3. [Chapter 2: Arrays, Memory Contiguity & Lab 1 Problem Patterns](#chapter-2-arrays-memory-contiguity--lab-1-problem-patterns)
   - [3.1 Contiguous Memory & Direct Address Arithmetic](#31-contiguous-memory--direct-address-arithmetic)
   - [3.2 Fixed Java Arrays (`int[]`) vs Dynamic Arrays (`ArrayList<T>`)](#32-fixed-java-arrays-int-vs-dynamic-arrays-arraylistt)
   - [3.3 The Insertion and Deletion Penalty: Why Shifting Takes O(n)](#33-the-insertion-and-deletion-penalty-why-shifting-takes-on)
   - [3.4 Lab 1 Core Problem Patterns & Algorithmic Blueprints](#34-lab-1-core-problem-patterns--algorithmic-blueprints)
     - [Pattern 1: Maximal Contiguous Homopolymer / Equal-Value Blocks](#pattern-1-maximal-contiguous-homopolymer--equal-value-blocks)
     - [Pattern 2: Suffix Maximum Queries & Precomputation](#pattern-2-suffix-maximum-queries--precomputation)
     - [Pattern 3: Finding the Missing Number (Math vs. XOR vs. Frequency)](#pattern-3-finding-the-missing-number-math-vs-xor-vs-frequency)
     - [Pattern 4: First Unique Position (Frequency Arrays vs. Hash Maps)](#pattern-4-first-unique-position-frequency-arrays-vs-hash-maps)
     - [Pattern 5: Sum of All Subarray Sums (The Contribution Technique)](#pattern-5-sum-of-all-subarray-sums-the-contribution-technique)
     - [Pattern 6: In-Place Array Interleaving and Block Reversals](#pattern-6-in-place-array-interleaving-and-block-reversals)
4. [Chapter 3: Searching Techniques, Two Pointers & Sliding Windows (Lab 2)](#chapter-3-searching-techniques-two-pointers--sliding-windows-lab-2)
   - [4.1 Linear Search: Mechanics and Unsorted Guarantees](#41-linear-search-mechanics-and-unsorted-guarantees)
   - [4.2 Binary Search: The Invariant, Interval Shrinking, and Overflow Safety](#42-binary-search-the-invariant-interval-shrinking-and-overflow-safety)
   - [4.3 Binary Search Variants](#43-binary-search-variants)
     - [First Occurrence (`lower_bound`)](#first-occurrence-lower_bound)
     - [Last Occurrence (`upper_bound`)](#last-occurrence-upper_bound)
     - [Counting Duplicates in O(log n)](#counting-duplicates-in-olog-n)
     - [Binary Search on the Answer Space (Feasibility Invariants)](#binary-search-on-the-answer-space-feasibility-invariants)
   - [4.4 The Sliding Window & Two Pointers Paradigm](#44-the-sliding-window--two-pointers-paradigm)
     - [Fixed-Width Sliding Windows (Rolling Sum / Rolling Average / GC Content)](#fixed-width-sliding-windows-rolling-sum--rolling-average--gc-content)
     - [Variable-Width Two Forward Pointers: Expanding and Contracting](#variable-width-two-forward-pointers-expanding-and-contracting)
     - [Sliding Windows with Frequency Tracking (Longest Unique Segment)](#sliding-windows-with-frequency-tracking-longest-unique-segment)
     - [The Non-Negativity Precondition: When Two Pointers Fail](#the-non-negativity-precondition-when-two-pointers-fail)
5. [Chapter 4: Elementary Sorting Algorithms & Invariants (Lab 2 & Week 03)](#chapter-4-elementary-sorting-algorithms--invariants-lab-2--week-03)
   - [5.1 Why Sorting Matters & The Invariant Mental Model](#51-why-sorting-matters--the-invariant-mental-model)
   - [5.2 Stability in Sorting: Why It Matters to Data Integrity](#52-stability-in-sorting-why-it-matters-to-data-integrity)
   - [5.3 Bubble Sort: Adjacent Pairwise Swaps](#53-bubble-sort-adjacent-pairwise-swaps)
   - [5.4 Selection Sort: Finding Extremes with Minimum Swaps](#54-selection-sort-finding-extremes-with-minimum-swaps)
   - [5.5 Insertion Sort: The Card-Player Algorithm & King of Nearly-Sorted Data](#55-insertion-sort-the-card-player-algorithm--king-of-nearly-sorted-data)
   - [5.6 Sorting Objects with Custom Keys (Java `Comparable` & `compareTo`)](#56-sorting-objects-with-custom-keys-java-comparable--compareto)
   - [5.7 Deep Comparison Matrix: Bubble vs. Selection vs. Insertion](#57-deep-comparison-matrix-bubble-vs-selection-vs-insertion)
6. [Chapter 5: Stacks (LIFO), Monotonic Stacks, and Parsing (Lab 3 & Week 04)](#chapter-5-stacks-lifo-monotonic-stacks-and-parsing-lab-3--week-04)
   - [6.1 The Stack ADT: Contract, Operations, and Boundary States](#61-the-stack-adt-contract-operations-and-boundary-states)
   - [6.2 Implementations: Fixed Array vs. Dynamic Array vs. Singly Linked List](#62-implementations-fixed-array-vs-dynamic-array-vs-singly-linked-list)
   - [6.3 Classical Stack Applications](#63-classical-stack-applications)
     - [Sequence Reversal](#sequence-reversal)
     - [Delimiter Matching & RNA Secondary Structure Verification](#delimiter-matching--rna-secondary-structure-verification)
     - [Arithmetic Expression Parsing: Infix to Postfix Conversion & Postfix Evaluation](#arithmetic-expression-parsing-infix-to-postfix-conversion--postfix-evaluation)
     - [Two-Stack Undo and Redo Architecture](#two-stack-undo-and-redo-architecture)
     - [The Min-Stack: Constant-Time Minimum Queries](#the-min-stack-constant-time-minimum-queries)
   - [6.4 The Monotonic Stack Technique: Next Greater Element in O(n) Amortized](#64-the-monotonic-stack-technique-next-greater-element-in-on-amortized)
7. [Chapter 6: Queues (FIFO), Circular Queues, Deques & Monotonic Deques (Lab 3 & Week 04)](#chapter-6-queues-fifo-circular-queues-deques--monotonic-deques-lab-3--week-04)
   - [7.1 The Queue ADT: Contract, Operations, and Real-World Processing](#71-the-queue-adt-contract-operations-and-real-world-processing)
   - [7.2 The Naive Array Queue Flaw: The O(n) Dequeue Shifting Penalty](#72-the-naive-array-queue-flaw-the-on-dequeue-shifting-penalty)
   - [7.3 The Circular Array Queue: Modular Arithmetic & State Disambiguation](#73-the-circular-array-queue-modular-arithmetic--state-disambiguation)
   - [7.4 The Linked Queue with Head and Tail Pointers](#74-the-linked-queue-with-head-and-tail-pointers)
   - [7.5 Priority Queues: Priority-Based Extraction](#75-priority-queues-priority-based-extraction)
   - [7.6 Double-Ended Queues (Deque) & Monotonic Deque for Sliding Window Maximum](#76-double-ended-queues-deque--monotonic-deque-for-sliding-window-maximum)
8. [Chapter 7: Linked Lists in Depth: Nodes, Pointers, and Invariants (Lab 3 & Week 05)](#chapter-7-linked-lists-in-depth-nodes-pointers-and-invariants-lab-3--week-05)
   - [8.1 Memory Architecture: Why References Replace Contiguity](#81-memory-architecture-why-references-replace-contiguity)
   - [8.2 Singly Linked Lists: Node Structure, Head, and Tail](#82-singly-linked-lists-node-structure-head-and-tail)
   - [8.3 The Sentinel Node Pattern: Eliminating Null Pointers Forever](#83-the-sentinel-node-pattern-eliminating-null-pointers-forever)
   - [8.4 Doubly Linked Lists: Bidirectional Traversal and O(1) Internal Deletions](#84-doubly-linked-lists-bidirectional-traversal-and-o1-internal-deletions)
   - [8.5 Circular Linked Lists (Singly and Doubly)](#85-circular-linked-lists-singly-and-doubly)
   - [8.6 Essential Linked List Algorithms](#86-essential-linked-list-algorithms)
     - [Iterative Link Reversal (The Three-Pointer Dance)](#iterative-link-reversal-the-three-pointer-dance)
     - [Middle Node Detection via Tortoise & Hare (Slow and Fast Pointers)](#middle-node-detection-via-tortoise--hare-slow-and-fast-pointers)
     - [Cycle Detection: Floyd's Algorithm vs. Hash Set Tracking](#cycle-detection-floyds-algorithm-vs-hash-set-tracking)
     - [Why Binary Search FAILS on Linked Lists](#why-binary-search-fails-on-linked-lists)
     - [The LRU (Least Recently Used) Cache Architecture](#the-lru-least-recently-used-cache-architecture)
9. [Chapter 8: Recursion & The Call-Stack Mechanics (Lab 4 & Week 06)](#chapter-8-recursion--the-call-stack-mechanics-lab-4--week-06)
   - [9.1 The Recursive Mental Model: Contracts and Self-Similarity](#91-the-recursive-mental-model-contracts-and-self-similarity)
   - [9.2 The Three Mandatory Invariants: Base Case, Recursive Case, Progress](#92-the-three-mandatory-invariants-base-case-recursive-case-progress)
   - [9.3 Under the Hood: The JVM Call Stack & Stack Frame Lifecycle](#93-under-the-hood-the-jvm-call-stack--stack-frame-lifecycle)
   - [9.4 Call Descent vs. Return Ascent (Unwinding)](#94-call-descent-vs-return-ascent-unwinding)
   - [9.5 Fundamental Recursive Patterns](#95-fundamental-recursive-patterns)
     - [Single Decreasing Integer: Factorials and Triangular Numbers](#single-decreasing-integer-factorials-and-triangular-numbers)
     - [Suffix / Linear Array Recursion](#suffix--linear-array-recursion)
     - [Two-Pointer Convergent Recursion: Palindromes](#two-pointer-convergent-recursion-palindromes)
     - [Recursive Binary Search](#recursive-binary-search)
     - [Multi-Branch Recursion: Fibonacci and Towers of Hanoi](#multi-branch-recursion-fibonacci-and-towers-of-hanoi)
   - [9.6 Recurrence Relations and the Unwinding Method](#96-recurrence-relations-and-the-unwinding-method)
10. [Chapter 9: Merge Sort: The Divide-and-Conquer Masterclass (Week 06 & Session 05)](#chapter-9-merge-sort-the-divide-and-conquer-masterclass-week-06--session-05)
    - [10.1 The Divide-and-Conquer Paradigm: Divide, Conquer, Combine](#101-the-divide-and-conquer-paradigm-divide-conquer-combine)
    - [10.2 The Core Merging Engine: Merging Two Sorted Subarrays in O(n)](#102-the-core-merging-engine-merging-two-sorted-subarrays-in-on)
      - [The Sentinel Method (as in `Main.java` with `Integer.MAX_VALUE`)](#the-sentinel-method-as-in-mainjava-with-integermax_value)
      - [The Standard Boundary-Checked Method](#the-standard-boundary-checked-method)
    - [10.3 Complete Merge Sort Algorithm & Recursive Tree Walkthrough](#103-complete-merge-sort-algorithm--recursive-tree-walkthrough)
    - [10.4 Rigorous Complexity & Memory Analysis](#104-rigorous-complexity--memory-analysis)
      - [Recurrence Derivation: Why T(n) = 2T(n/2) + O(n) is Theta(n log n)](#recurrence-derivation-why-tn--2tn2--on-is-thetan-log-n)
      - [Auxiliary Space: Why Merge Sort Requires O(n) Extra Memory](#auxiliary-space-why-merge-sort-requires-on-extra-memory)
      - [Stability Analysis: Why Merge Sort Preserves Equality Order](#stability-analysis-why-merge-sort-preserves-equality-order)
    - [10.5 Merge Sort vs. Elementary Sorts: When to Use What](#105-merge-sort-vs-elementary-sorts-when-to-use-what)
11. [Chapter 10: Midterm Synthesis, Master Complexity Tables & Decision Trees](#chapter-10-midterm-synthesis-master-complexity-tables--decision-trees)
    - [11.1 The Master Complexity Reference Table](#111-the-master-complexity-reference-table)
    - [11.2 Midterm Trap Checklist: 10 Classic Fallacies Exposed](#112-midterm-trap-checklist-10-classic-fallacies-exposed)
    - [11.3 Technique Recognition Decision Tree](#113-technique-recognition-decision-tree)
    - [11.4 Comprehensive Course Glossary for Bioinformaticians](#114-comprehensive-course-glossary-for-bioinformaticians)

---

# Preface: Java Survival Kit & Mental Models for Bioinformaticians

### 1.1 Why Bioinformaticians Need Data Structures & Algorithms
As a bioinformatician, your daily work revolves around massive datasets: billions of base pairs from Illumina sequencing runs, millions of variant records in VCF files, protein 3D coordinate matrices from PDB, and high-throughput RNA-seq expression matrices.

In introductory bioinformatics, using high-level scripts in Python or R with libraries like `pandas`, `Biopython`, or `GenomicRanges` hides what happens behind the scenes. But when an algorithm takes 3 days to process a single FASTQ file because of an accidental quadratic $O(n^2)$ loop, or crashes a 64-GB computing node because it clones arrays unnecessarily, biology hits the hard wall of computer science.

This course, **Data Structures and Algorithms (DSA)**, teaches you how data is physically arranged in silicon and memory, and how mathematical procedures manipulate that data efficiently.

> **Bioinformatics Mental Anchor:**
> - An **Array** is a contiguous strand of DNA nucleotides stored in fixed memory positions (like genomic coordinates `chr1:1000..2000`).
> - A **Sliding Window** is a reading frame or GC-content window shifting across a chromosome.
> - A **Stack** is RNA secondary structure folding, where stems and hairpin loops must strictly match nested base pairs ($A-U$, $G-C$) like nested brackets.
> - A **Queue** is the sequencing pipeline where raw sequencer reads wait to be mapped by an alignment worker.
> - A **Linked List** is a set of overlapping sequence contigs connected by assembly graph edges.
> - **Recursion & Merge Sort** is divide-and-conquer sequence assembly, sorting millions of reads by chromosome coordinate in $O(n \log n)$ time.

---

### 1.2 Java vs. Python/R: The Mental Shift
If you are coming from Python or R, Java feels verbose, strict, and rigid. However, that rigidity exists for a reason: it gives direct, deterministic control over memory and computational types.

Here are the 5 core differences you must understand immediately:

| Concept | Python / R | Java | Why Java Does This |
| :--- | :--- | :--- | :--- |
| **Typing** | Dynamic (`x = 10`, later `x = "ATCG"`) | Static (`int x = 10;`, `String s = "ATCG";`) | The compiler calculates exact byte sizes before running. |
| **Execution** | Interpreted (line by line) | Compiled to Bytecode, run by JVM | JIT (Just-In-Time) compilation gives C-like speed. |
| **Array Sizing** | Lists resize automatically (`list.append(x)`) | Arrays are **fixed-size** at creation (`new int[10]`) | Forces you to understand true memory allocation costs. |
| **Syntax** | Indentation matters, minimal punctuation | Curly braces `{}` define blocks, semicolons `;` terminate lines | Structure is defined by tokens, not whitespace. |
| **Object Orientation** | Optional (you can write loose scripts) | **Mandatory** (every single variable and function belongs to a `class`) | Enforces modular encapsulation and strict type safety. |

---

### 1.3 Anatomy of a Minimal Java Program
In Java, you cannot simply write `print("Hello World")` at the top of a file. Every executable Java file must have a class and an entry-point method:

```java
// File name MUST match the class name: HelloDsa.java
public class HelloDsa {

    // The entry point: the JVM looks specifically for this exact signature
    public static void main(String[] args) {
        // System.out.println prints a line of text to standard output (the terminal)
        System.out.println("Welcome to DSA for Bioinformaticians!");
    }
}
```

#### Deconstructing the keywords for beginners:
1. `public`: Accessible from anywhere in the project.
2. `class HelloDsa`: In Java, a **class** is a blueprint. Think of it like a plasmid map or a gene structure annotation. It describes what data fields exist and what actions (methods) can be performed.
3. `static`: The method belongs to the class itself, not to a specific instantiated object. You don't need to create a `new HelloDsa()` object to run `main`.
4. `void`: This method returns **nothing** to its caller.
5. `main(String[] args)`: The name of the starting method. `String[] args` means it accepts an array of strings representing command-line arguments (e.g., passing input file paths).

---

### 1.4 Primitives vs. Object References: The "Street Address" Analogy
Understanding the difference between **primitive types** and **object references** is the single most important hurdle for Java beginners.

#### 1. Primitive Types (Direct Values)
Java has 8 primitive types. For DSA, you only need four:
- `int`: 32-bit integer (range $-2 	imes 10^9$ to $+2 	imes 10^9$).
- `long`: 64-bit integer (range $-9 	imes 10^{18}$ to $+9 	imes 10^{18}$). Used for large sums or counts $> 2 	imes 10^9$.
- `double`: 64-bit floating point number (for averages, ratios, probabilities).
- `boolean`: `true` or `false`.

When you write `int a = 42;`, the computer puts the raw binary pattern for `42` directly inside the memory box labeled `a`.

#### 2. Object References (Street Addresses)
Anything that is not a primitive is an **Object** (including arrays, strings, stacks, and nodes).
When you write:
```java
int[] arr = new int[5];
```
The variable `arr` does **NOT** contain the 5 integers! Instead, `arr` contains a **memory address** (a pointer) that says: *"The actual 5 integers are stored over at heap address #0x7FFF94."*

```
   STACK (Variables)               HEAP (Objects & Data)
+----------------------+        +-------------------------------+
| arr: [0x7FFF94] ----|------> | [0]: 0 | [1]: 0 | [2]: 0 ... |
+----------------------+        +-------------------------------+
```

> **The House and GPS Analogy:**
> - A primitive variable is like carrying cash in your pocket. You have the actual money right there.
> - An object reference variable is like a piece of paper with an address written on it: *"123 DNA Way"*.
> - If you pass that paper to a friend (`method(arr)`), you did not build a new house! You gave them the address. If they walk to that address and paint the door red (`arr[0] = 99`), your house door is now red!

---

### 1.5 Memory Architecture: The JVM Call Stack vs. The Heap
The Java Virtual Machine (JVM) divides memory into two distinct zones:

```
+---------------------------------------------------------------+
|                         JVM MEMORY                            |
|                                                               |
|   CALL STACK (Fast, Scratchpad)     HEAP (Large, Shared)      |
|   +--------------------------+     +-----------------------+  |
|   | Frame 3: helper()        |     | int[] array data      |  |
|   |   local vars: p, q       |     | Node objects          |  |
|   +--------------------------+     | String literals       |  |
|   | Frame 2: process()       |     | ArrayList instances   |  |
|   |   local vars: arr (ptr)  |     |                       |  |
|   +--------------------------+     | (Cleaned automatically|  |
|   | Frame 1: main()          |     |  by Garbage Collector)|  |
|   +--------------------------+     +-----------------------+  |
+---------------------------------------------------------------+
```

1. **The Call Stack:**
   - Stores active method calls and local primitive variables.
   - Operates in strict Last-In, First-Out (LIFO) order.
   - Every time a method is called, a **Stack Frame** is pushed on top. When the method returns, its frame is instantly popped and destroyed.
   - Very fast, but limited in size (typically 1 MB). If recursion goes too deep without stopping, you get a `StackOverflowError`.

2. **The Heap:**
   - Stores all objects and arrays created with the `new` keyword.
   - Memory stays allocated until no variables point to it anymore, at which point the Java **Garbage Collector (GC)** reclaims it automatically.

---

### 1.6 How Java Allocates Arrays
In Java, an array is an object that holds a fixed number of values of a single type.

```java
// Declaration and allocation
int[] reads = new int[4]; // creates 4 consecutive integer cells in Heap memory
reads[0] = 100;
reads[1] = 250;
reads[2] = 80;
reads[3] = 410;

// Length property (NOT a method call: no parentheses!)
int n = reads.length; // n is 4

// Direct literal initialization:
int[] bases = {2, 5, 8, 12};
```

#### Vital Rules for Arrays in Java:
1. **Zero-Indexed:** First element is `reads[0]`, last element is `reads[n - 1]`.
2. **Bounds Checked:** Accessing `reads[4]` or `reads[-1]` immediately throws `ArrayIndexOutOfBoundsException`.
3. **Fixed Capacity:** Once `new int[4]` is created, you **cannot** expand or shrink it. If you need a 5th element, you must allocate a completely new array of size 5 and manually copy the existing elements over!

---

# Chapter 1: Algorithm Analysis, Asymptotics, and Growth Rates

### 2.1 The Philosophy: Program = Algorithms + Data Structures
In 1976, computer scientist Niklaus Wirth published the seminal book *Algorithms + Data Structures = Programs*.
- A **Data Structure** is the architectural layout of data in physical computer memory.
- An **Algorithm** is the step-by-step recipe of logical operations used to solve a problem by manipulating that data structure.

A brilliant algorithm running on an inappropriate data structure is painfully slow; conversely, the best data structure in the world is useless if traversed by an inefficient algorithm.

---

### 2.2 Why Wall-Clock Time Lies: The RAM Computational Model
Why can't we simply benchmark two programs using a stopwatch or `System.currentTimeMillis()`?
Because wall-clock runtime depends on:
1. The hardware processor speed (a server vs. a laptop).
2. The operating system background tasks (Spotify, browser tabs, OS updates).
3. The programming language and compiler optimizations.
4. The exact input dataset chosen (already sorted vs. completely reversed).

To evaluate algorithms objectively, computer scientists use the **Random Access Machine (RAM) Model**:
- We assume an idealized, generic single-processor computer.
- Each **primitive operation** takes exactly **1 time unit ($O(1)$)**:
  - Arithmetic operations (`+`, `-`, `*`, `/`, `%`)
  - Comparisons (`<`, `>`, `==`, `<=`, `>=`)
  - Variable assignments (`x = y`)
  - Array indexing and memory lookups (`a[i]`)
- We express algorithm runtime $T(n)$ as a mathematical function of the **input size $n$** (e.g., the number of base pairs, sequence reads, or numbers).

---

### 2.3 The Hierarchy of Growth Rates
As the input size $n$ grows toward infinity, the dominant mathematical term dictates how runtime scales. Here is the strict hierarchy of complexity classes studied in DSA:

$$\mathcal{O}(1) < \mathcal{O}(\log n) < \mathcal{O}(\sqrt{n}) < \mathcal{O}(n) < \mathcal{O}(n \log n) < \mathcal{O}(n^2) < \mathcal{O}(n^3) < \mathcal{O}(2^n) < \mathcal{O}(n!)$$

```
Growth Rate Curve (Visual Ascent)
Runtime ^
        |                                        / 2^n (Exponential)
        |                                       /
        |                                  /--/  n^2 (Quadratic)
        |                                 /
        |                             /--/       n log n (Linearithmic)
        |                         /--/
        |                     /--/               n (Linear)
        |                 /--/
        |            /---/                       sqrt(n)
        |      /----/                            log n (Logarithmic)
        |--------------------------------------> 1 (Constant)
        +----------------------------------------> Input Size n
```

#### What These Curves Mean in Real Computation:
Assume a standard CPU executing **$10^9$ operations per second (1 GHz)**:

| Complexity Class | Name | $n = 10$ | $n = 100$ | $n = 1,000$ | $n = 1,000,000$ ($10^6$) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| $\mathcal{O}(1)$ | Constant | $1 	ext{ ns}$ | $1 	ext{ ns}$ | $1 	ext{ ns}$ | $1 	ext{ ns}$ |
| $\mathcal{O}(\log_2 n)$ | Logarithmic | $3.3 	ext{ ns}$ | $6.6 	ext{ ns}$ | $10 	ext{ ns}$ | $20 	ext{ ns}$ |
| $\mathcal{O}(\sqrt{n})$ | Square Root | $3.1 	ext{ ns}$ | $10 	ext{ ns}$ | $31.6 	ext{ ns}$ | $1 \ \mu	ext{s}$ |
| $\mathcal{O}(n)$ | Linear | $10 	ext{ ns}$ | $100 	ext{ ns}$ | $1 \ \mu	ext{s}$ | $1 	ext{ ms}$ |
| $\mathcal{O}(n \log_2 n)$ | Linearithmic | $33 	ext{ ns}$ | $664 	ext{ ns}$ | $10 \ \mu	ext{s}$ | $20 	ext{ ms}$ |
| $\mathcal{O}(n^2)$ | Quadratic | $100 	ext{ ns}$ | $10 \ \mu	ext{s}$ | $1 	ext{ ms}$ | **$16.7 	ext{ minutes}$** |
| $\mathcal{O}(n^3)$ | Cubic | $1 \ \mu	ext{s}$ | $1 	ext{ ms}$ | $1 	ext{ second}$ | **$31.7 	ext{ years}$** |
| $\mathcal{O}(2^n)$ | Exponential | $1 \ \mu	ext{s}$ | **$4 	imes 10^{13} 	ext{ years}$** | Impossible | Heat death of Universe |

> **Bioinformatic Takeaway:**
> If you have a human genome with $n = 3 	imes 10^9$ base pairs:
> - An $O(n)$ linear scan finishes in about 3 seconds.
> - An $O(n^2)$ algorithm would take $9 	imes 10^{18}$ operations $pprox 285 	ext{ years}$!

---

### 2.4 Formal Asymptotic Notations: Big-O, Big-Omega, Big-Theta, and Little-o
In university examinations, precision in notation is strictly tested.

#### 1. Big-O Notation ($\mathcal{O}$): Asymptotic Upper Bound ("At Most")
$T(n) = \mathcal{O}(g(n))$ means that for sufficiently large $n$, $T(n)$ grows no faster than a constant multiple of $g(n)$.
$$\exists\ c > 0, n_0 \ge 0 \quad 	ext{such that} \quad orall\ n \ge n_0, \quad T(n) \le c \cdot g(n)$$
- **Layman's translation:** "In the worst case, the algorithm's work is capped by $g(n)$."
- **Example:** If $T(n) = 3n^2 + 5n + 10$, then $T(n) = \mathcal{O}(n^2)$. It is also technically $\mathcal{O}(n^3)$, because $n^3$ is a valid ceiling, though $n^2$ is tighter.

#### 2. Big-Omega Notation ($\Omega$): Asymptotic Lower Bound ("At Least")
$T(n) = \Omega(g(n))$ means that for sufficiently large $n$, $T(n)$ grows at least as fast as a constant multiple of $g(n)$.
$$\exists\ c > 0, n_0 \ge 0 \quad 	ext{such that} \quad orall\ n \ge n_0, \quad T(n) \ge c \cdot g(n)$$
- **Layman's translation:** "The algorithm will require at least $g(n)$ work; it cannot miraculously do better than this asymptotically."

#### 3. Big-Theta Notation ($\Theta$): Asymptotically Tight Bound ("Exactly")
$T(n) = \Theta(g(n))$ means that $g(n)$ is simultaneously an upper bound AND a lower bound.
$$\exists\ c_1 > 0, c_2 > 0, n_0 \ge 0 \quad 	ext{such that} \quad orall\ n \ge n_0, \quad c_1 \cdot g(n) \le T(n) \le c_2 \cdot g(n)$$
- **Equivalence:** $T(n) = \Theta(g(n)) \iff T(n) = \mathcal{O}(g(n)) 	ext{ and } T(n) = \Omega(g(n))$.
- **Layman's translation:** "The algorithm grows at exactly the same rate as $g(n)$, up to constant scale factors."

#### 4. Little-o Notation ($o$): Strict Upper Bound
$T(n) = o(g(n))$ means that $T(n)$ grows strictly slower than $g(n)$:
$$\lim_{n 	o \infty} rac{T(n)}{g(n)} = 0$$
- **Example:** $5n = o(n^2)$, but $5n^2 
e o(n^2)$.

---

### 2.5 Input Cases: Best, Average, Worst, and Amortized Time
Never confuse the **case of the input** with the **asymptotic notation**:
- **Best-Case:** The absolute minimum number of operations required on the most favorable input of size $n$ (e.g., searching for an element that happens to be at index 0).
- **Worst-Case:** The maximum number of operations on the most adversarial input of size $n$ (e.g., searching for an element not present in the array). Provides an absolute guarantee.
- **Average-Case:** The expected number of operations over a specified probability distribution of inputs (e.g., assuming target elements appear uniformly at random).
- **Amortized Time:** The average cost per operation over a long sequence of operations, even if a single rare operation is very expensive.

#### The Classic Amortized Example: Dynamic Array (`ArrayList`)
In Java, an `ArrayList` starts with a fixed capacity (say, 4). When you call `add()`:
- Usually, there is empty space: adding an element takes $O(1)$.
- When the array is full, it must allocate a new array of size $2 	imes 	ext{capacity}$ (8 cells) and copy all 4 elements over. This single resize operation costs $O(n)$!
- However, doubling the capacity means the next $n$ insertions are guaranteed to take $O(1)$ each without resizing.
- Total cost for $n$ appends:
  $$	ext{Total Work} \le n + \left( rac{n}{2} + rac{n}{4} + rac{n}{8} + \dots + 1 
ight) \le n + n = 2n$$
- Cost per append: $rac{2n}{n} = \mathcal{O}(1)$ **amortized** time.

---

### 2.6 Space Complexity: Stored Data vs. Auxiliary Space vs. Call Stack
When analyzing memory, course examiners strictly distinguish between three components:

1. **Input / Stored Data Memory:** The memory required to hold the initial input dataset (e.g., an array of $n$ numbers takes $\Theta(n)$ storage).
2. **Auxiliary Space:** The **extra** temporary memory allocated by the algorithm itself to do its work (excluding input and required output).
   - If an algorithm only declares a few loop variables (`int i, sum`), its auxiliary space is $\mathcal{O}(1)$ (**in-place**).
   - If an algorithm creates a duplicate scratch array of size $n$, its auxiliary space is $\mathcal{O}(n)$.
3. **Call-Stack Space:** Memory consumed on the JVM call stack by active recursive function frames. A recursive function with depth $n$ uses $\mathcal{O}(n)$ stack space.

---

### 2.7 Rules for Counting Loops from Code

#### Rule 1: The Rule of Sums (Consecutive Blocks)
If two blocks run one after another, add their costs and take the maximum:
```java
for (int i = 0; i < n; i++) { /* O(1) */ } // O(n)
for (int j = 0; j < m; j++) { /* O(1) */ } // O(m)
// Total Time: O(n + m). If n == m, this is O(2n) = O(n).
```

#### Rule 2: The Rule of Products (Nested Loops)
If an inner loop runs inside an outer loop, multiply their iteration counts:
```java
for (int i = 0; i < n; i++) {          // runs n times
    for (int j = 0; j < m; j++) {      // runs m times per outer loop
        doConstantWork();              // O(1)
    }
}
// Total Time: n * m * O(1) = O(n * m)
```

#### Rule 3: Triangular Nested Loops
When the inner loop boundary depends directly on the outer loop index:
```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < i; j++) {
        doConstantWork();
    }
}
```
Total iterations:
$$0 + 1 + 2 + 3 + \dots + (n - 1) = \sum_{j=0}^{n-1} j = rac{n(n - 1)}{2} = rac{n^2 - n}{2} = \Theta(n^2)$$

#### Rule 4: Halving or Doubling Loops
When a loop index doubles or halves in each iteration:
```java
int count = 0;
while (n > 1) {
    n = n / 2;
    count++;
}
```
How many times can you divide $n$ by 2 before reaching 1? Exactly $\lfloor \log_2 n 
floor$ times.
$$	ext{Total Time} = \mathcal{O}(\log n)$$

#### Rule 5: Amortized Two-Pointer / Monotonic Loops
**Crucial Exam Insight:** A nested loop does **NOT** automatically mean $\mathcal{O}(n^2)$!
```java
int right = 0;
for (int left = 0; left < n; left++) {
    while (right < n && condition(left, right)) {
        right++; // right only advances forward!
    }
}
```
Even though there is a `while` inside a `for`, `right` starts at 0 and is **only incremented forward**. Across the entire lifespan of the program, `right` increments at most $n$ times. The outer loop runs $n$ times.
$$	ext{Total Operations} \le n (	ext{left}) + n (	ext{right}) = 2n = \mathcal{O}(n)$$

---

### 2.8 Case Study: Maximum Contiguous Subsequence Sum (The 4 Generations)
In Lecture Week 03.2, the course studies a classic problem that demonstrates how algorithmic paradigms crush computational bottlenecks:

**The Problem:** Given an array of integers (which can include negative numbers), find the maximum sum of any contiguous subarray. If all numbers are negative, the maximum sum is defined as 0.
$$	ext{Example: } [-2, 11, -4, 13, -5, -2] \implies 	ext{Max contiguous sum is } 11 + (-4) + 13 = 20$$

#### Generation 1: Brute Force All Pairs and Sum ($\mathcal{O}(n^3)$)
Try every starting index `i`, every ending index `j`, and compute the sum from `i` to `j` using a third loop:
```java
public static int maxSubsequenceSumGen1(int[] a) {
    int maxSum = 0;
    int n = a.length;
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            int thisSum = 0;
            for (int k = i; k <= j; k++) {
                thisSum += a[k]; // 3rd nested loop!
            }
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }
    }
    return maxSum; // Time: O(n^3), Auxiliary Space: O(1)
}
```

#### Generation 2: Incremental Running Sum ($\mathcal{O}(n^2)$)
Notice that the sum from `i` to `j` is simply `(sum from i to j - 1) + a[j]`. We can eliminate the innermost loop!
```java
public static int maxSubsequenceSumGen2(int[] a) {
    int maxSum = 0;
    int n = a.length;
    for (int i = 0; i < n; i++) {
        int thisSum = 0;
        for (int j = i; j < n; j++) {
            thisSum += a[j]; // Constant time addition
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }
    }
    return maxSum; // Time: O(n^2), Auxiliary Space: O(1)
}
```

#### Generation 3: Divide and Conquer ($\mathcal{O}(n \log n)$)
Split the array at midpoint `mid`. The maximum subarray must either:
1. Reside entirely in the left half (recursively compute).
2. Reside entirely in the right half (recursively compute).
3. Straddle across the midpoint (computed in linear time $O(n)$ by finding the max suffix on the left and max prefix on the right).

Recurrence relation:
$$T(n) = 2T(n/2) + \mathcal{O}(n) \implies T(n) = \mathcal{O}(n \log n)$$

#### Generation 4: Kadane's Algorithm ($\mathcal{O}(n)$ - Linear Time!)
Key mathematical insight: A maximum contiguous subsequence can **never** have a prefix whose sum is negative! If `thisSum` ever drops below 0, reset it to 0 and begin evaluating fresh from the next element:
```java
public static int maxSubsequenceSumGen4(int[] a) {
    int maxSum = 0;
    int thisSum = 0;
    for (int j = 0; j < a.length; j++) {
        thisSum += a[j];
        if (thisSum > maxSum) {
            maxSum = thisSum;
        } else if (thisSum < 0) {
            thisSum = 0; // Reset! A negative prefix can never help a future subarray.
        }
    }
    return maxSum; // Time: O(n), Auxiliary Space: O(1)
}
```
- **Comparison at $n = 1,000,000$:**
  - Gen 1 ($\mathcal{O}(n^3)$): $pprox 31,700 	ext{ years}$.
  - Gen 2 ($\mathcal{O}(n^2)$): $pprox 16.7 	ext{ minutes}$.
  - Gen 3 ($\mathcal{O}(n \log n)$): $pprox 0.02 	ext{ seconds}$.
  - Gen 4 ($\mathcal{O}(n)$): $pprox 0.001 	ext{ seconds}$.

---

# Chapter 2: Arrays, Memory Contiguity & Lab 1 Problem Patterns

### 3.1 Contiguous Memory & Direct Address Arithmetic
An **Array** is a contiguous block of physical memory cells allocated side-by-side in RAM.

Because every element has identical byte size $S$ (e.g., 4 bytes for an `int`), the hardware does not need to search through memory to find element `a[i]`. It computes the exact memory address in a single clock cycle using **Direct Address Arithmetic**:

$$	ext{Address of } a[i] = 	ext{Base Address} + (i 	imes S)$$

```
Memory Layout of int[] a = {12, 99, 37, 45}:
Base Address = 1000
Element size = 4 bytes

Index:       0         1         2         3
Address:   1000      1004      1008      1012
Data:    [  12  ]  [  99  ]  [  37  ]  [  45  ]
```

Because finding the address takes one multiplication and one addition, reading or writing to any index in an array takes **strictly $\mathcal{O}(1)$ constant time**.

---

### 3.2 Fixed Java Arrays (`int[]`) vs Dynamic Arrays (`ArrayList<T>`)
- **Java Array (`int[]`):**
  - Primitive performance: no object wrapper overhead.
  - Fixed capacity allocated at creation: cannot grow or shrink.
  - Length accessed via `.length` property.
- **Java `ArrayList<Integer>`:**
  - Dynamic resizing: grows automatically as elements are added.
  - Backed internally by a raw Java array (`Object[] elementData`).
  - Size accessed via `.size()` method.
  - Only stores Object wrappers (`Integer`), adding small memory overhead (object headers and pointer dereferencing).

---

### 3.3 The Insertion and Deletion Penalty: Why Shifting Takes O(n)
While reading by index is $\mathcal{O}(1)$, inserting or deleting an element at an arbitrary position in an array requires shifting all subsequent elements:

```
Inserting value 50 at index 1 in array [10, 20, 30, 40]:
Step 1: Shift right from back
[10, 20, 30, 40, __]
[10, 20, 30, __, 40]  (shift index 3 -> 4)
[10, 20, __, 30, 40]  (shift index 2 -> 3)
[10, __, 20, 30, 40]  (shift index 1 -> 2)
Step 2: Place 50 into index 1
[10, 50, 20, 30, 40]
```
- In the worst case (inserting at index 0), all $n$ elements must be shifted right: $\mathcal{O}(n)$ time.
- Similarly, deleting element 0 requires shifting all $n - 1$ elements left: $\mathcal{O}(n)$ time.

---

### 3.4 Lab 1 Core Problem Patterns & Algorithmic Blueprints

#### Pattern 1: Maximal Contiguous Homopolymer / Equal-Value Blocks
- **Scenario:** Count contiguous runs of identical values (identical to detecting homopolymer runs in DNA like `AAAA`, `GGG`).
- **Input:** `[4, 4, -1, -1, -1, 7, 4, 4]` $\implies$ **Output:** `4` blocks (`[4, 4]`, `[-1, -1, -1]`, `[7]`, `[4, 4]`).
- **Algorithmic Blueprint:** A single forward traversal. A new block begins if and only if $a[i] 
e a[i - 1]$.

```java
public static int countEqualValueBlocks(int[] arr) {
    // Edge case: empty array has 0 blocks
    if (arr == null || arr.length == 0) return 0;
    
    // Base state: first element unconditionally starts the first block
    int blockCount = 1;
    
    // Traverse adjacent pairs from index 1 to n - 1
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] != arr[i - 1]) {
            blockCount++; // Transition detected! Previous block ended, new block started.
        }
    }
    return blockCount;
}
// Time Complexity: O(n) - exactly n - 1 comparisons
// Auxiliary Space: O(1) - in-place primitive counters
```

#### Pattern 2: Suffix Maximum Queries & Precomputation
- **Scenario:** Given an array and repeated queries for the maximum value in suffix `arr[p ... n - 1]`.
- **Naive approach:** For each query, scan from $p$ to $n - 1$: $\mathcal{O}(n)$ per query $\implies \mathcal{O}(q \cdot n)$ total.
- **Optimal Blueprint:** Precompute a suffix maximum array in **reverse** in $\mathcal{O}(n)$ time, allowing each query to be answered in $\mathcal{O}(1)$ time!

```java
public static int[] buildSuffixMax(int[] arr) {
    int n = arr.length;
    if (n == 0) return new int[0];
    
    int[] suffixMax = new int[n];
    suffixMax[n - 1] = arr[n - 1]; // Base case at the rightmost boundary
    
    // Fill from right to left
    for (int i = n - 2; i >= 0; i--) {
        suffixMax[i] = Math.max(arr[i], suffixMax[i + 1]);
    }
    return suffixMax;
}

public static int querySuffixMax(int[] suffixMax, int p) {
    return suffixMax[p]; // Instant O(1) lookup!
}
// Precomputation Time: O(n), Auxiliary Space: O(n) table
// Query Time: O(1)
```

#### Pattern 3: Finding the Missing Number (Math vs. XOR vs. Frequency)
- **Scenario:** An array contains $n$ distinct numbers chosen from the range $[0, n]$. Exactly one number is missing.
- **Approach A (Mathematical Summation):**
  - Sum of numbers from $0$ to $n$ is $S = rac{n(n + 1)}{2}$.
  - Sum of elements in array is $S_{	ext{actual}}$.
  - $	ext{Missing Number} = S - S_{	ext{actual}}$.
  - *Risk:* Integer overflow if $n$ is very large (must use `long`).
- **Approach B (Bitwise XOR - The Master Trick):**
  - Properties of XOR ($\oplus$): $x \oplus x = 0$ and $x \oplus 0 = x$.
  - XOR all numbers from $0$ to $n$ together with all numbers in the array. Every number present in both pairs will cancel out to 0, leaving strictly the missing number!
  - *Zero risk of overflow!*

```java
public static int findMissingXOR(int[] nums) {
    int n = nums.length;
    int xor = 0;
    for (int i = 0; i <= n; i++) xor ^= i;
    for (int x : nums) xor ^= x;
    return xor; // Time: O(n), Auxiliary Space: O(1)
}
```

#### Pattern 4: First Unique Position (Frequency Arrays vs. Hash Maps)
- **Scenario:** Find the first non-repeating element in an array of values.
- **Blueprint:** Two linear passes.
  1. Pass 1: Build a frequency distribution (using an array `freq[val]` if values are in a small range $[0, R]$, or `HashMap<Integer, Integer>` if values are arbitrary).
  2. Pass 2: Iterate through the original array in original order; return the first element whose recorded frequency is 1.
- **Complexity:** $\mathcal{O}(n)$ time, $\mathcal{O}(n)$ or $\mathcal{O}(R)$ auxiliary space.

#### Pattern 5: Sum of All Subarray Sums (The Contribution Technique)
- **Scenario:** Compute the sum of all possible contiguous subarrays.
- **Example:** `[1, 2, 3]` $\implies$ Subarrays are `[1]`, `[2]`, `[3]`, `[1,2]`, `[2,3]`, `[1,2,3]`. Total sum = $1 + 2 + 3 + 3 + 5 + 6 = 20$.
- **Naive approach:** Generate all subarrays: $\mathcal{O}(n^2)$ time.
- **Optimal Blueprint (Mathematical Contribution):**
  How many subarrays contain element `arr[i]`?
  - A subarray containing `arr[i]` must start at or before index $i$ ($i + 1$ choices: $0, 1, \dots, i$).
  - It must end at or after index $i$ ($n - i$ choices: $i, i + 1, \dots, n - 1$).
  - Total subarrays containing `arr[i]` $= (i + 1) 	imes (n - i)$.
  - Total sum $= \sum_{i=0}^{n-1} 	ext{arr}[i] 	imes (i + 1) 	imes (n - i)$.

```java
public static long totalSubarraySum(int[] arr) {
    long total = 0;
    int n = arr.length;
    for (int i = 0; i < n; i++) {
        long contributionCount = (long)(i + 1) * (n - i);
        total += arr[i] * contributionCount;
    }
    return total; // Time: O(n), Auxiliary Space: O(1)
}
```

#### Pattern 6: In-Place Array Interleaving and Block Reversals
- **Scenario:** Given an array $[a_1, a_2, \dots, a_k, b_1, b_2, \dots, b_k]$, interleave them into $[a_1, b_1, a_2, b_2, \dots]$.
- **Inversion / Reversal Helper:** To reverse any segment between indices `lo` and `hi`:
```java
public static void reverse(int[] a, int lo, int hi) {
    while (lo < hi) {
        int temp = a[lo];
        a[lo] = a[hi];
        a[hi] = temp;
        lo++;
        hi--;
    }
}
```
Reversal is symmetric and runs in $\mathcal{O}(hi - lo)$ time with $\mathcal{O}(1)$ auxiliary space.


---

# Chapter 3: Searching Techniques, Two Pointers & Sliding Windows (Lab 2)

### 4.1 Linear Search: Mechanics and Unsorted Guarantees
Searching is the process of locating a specific target key within a dataset.
The simplest search is **Linear Search** (or Sequential Search):

```java
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i; // Target located! Return its index immediately.
        }
    }
    return -1; // Target does not exist in the array.
}
```

#### Properties and Analysis:
- **Precondition:** None. Works on completely unsorted, random, or duplicated data.
- **Best-Case Time:** $\Theta(1)$ (the target element happens to be at index 0).
- **Worst-Case Time:** $\Theta(n)$ (the target element is at the very end or absent entirely, requiring all $n$ comparisons).
- **Average-Case Time:** $\Theta(n/2) = \Theta(n)$ (assuming uniform probability of presence).
- **Auxiliary Space:** $\mathcal{O}(1)$ (only a single loop counter is used).

---

### 4.2 Binary Search: The Invariant, Interval Shrinking, and Overflow Safety
If an array is **already sorted** in ascending order, we can discard half the remaining search space with every single comparison. This is **Binary Search**.

#### The Core Mental Invariant:
At any step, if the target exists, it is guaranteed to lie within the closed index range `[low, high]`.

```
Searching for target = 10 in sorted array:
Index:   0   1   2   3   4    5    6
Array: [ 2,  4,  6,  8, 10,  12,  14 ]

Pass 1: low = 0, high = 6.
        mid = 0 + (6 - 0) / 2 = 3.
        arr[mid] = arr[3] = 8.
        Since 8 < 10, target MUST lie strictly in the right half!
        Set low = mid + 1 = 4.

Pass 2: low = 4, high = 6.
        mid = 4 + (6 - 4) / 2 = 5.
        arr[mid] = arr[5] = 12.
        Since 12 > 10, target MUST lie strictly in the left half!
        Set high = mid - 1 = 4.

Pass 3: low = 4, high = 4.
        mid = 4 + (4 - 4) / 2 = 4.
        arr[mid] = arr[4] = 10.
        MATCH FOUND! Return index 4.
```

#### The Classic Integer Overflow Bug:
In many introductory textbooks, programmers write:
```java
int mid = (low + high) / 2; // DANGEROUS!
```
In Java, if `low + high` exceeds $2^{31} - 1$ ($2,147,483,647$), the integer overflows into a negative number, causing an immediate `ArrayIndexOutOfBoundsException`!
The professional, overflow-safe formula taught in this course is:
```java
int mid = low + (high - low) / 2; // Mathematically equivalent, 100% overflow-safe!
```

#### Full Iterative Implementation:
```java
public static int binarySearch(int[] arr, int target) {
    int low = 0;
    int high = arr.length - 1;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        
        if (arr[mid] == target) {
            return mid; // Found!
        } else if (arr[mid] < target) {
            low = mid + 1; // Discard left half
        } else {
            high = mid - 1; // Discard right half
        }
    }
    return -1; // Interval became empty (low > high): element is absent
}
```

#### Complexity Analysis:
- In each step, the search interval length shrinks: $n \to \frac{n}{2} \to \frac{n}{4} \to \dots \to 1$.
- Total halving steps: $\log_2 n$.
- **Best-Case Time:** $\Theta(1)$ (target happens to be at the exact initial midpoint).
- **Worst-Case Time:** $\Theta(\log_2 n)$ (target is absent or at the final leaf of the binary tree).
- **Auxiliary Space:** $\mathcal{O}(1)$ for iterative search.

---

### 4.3 Binary Search Variants

In real software and online judge problems (Lab 2 Problems 5 & 6), standard binary search is not enough. Arrays often contain duplicates, or we need boundary positions.

#### Variant 1: Finding the First Occurrence (`lower_bound`)
When duplicates exist (e.g., `[2, 5, 5, 5, 8]`, target 5), standard binary search might return index 2, 1, or 3 unpredictably.
To find the **first** occurrence (the leftmost index): when a match is found, **do not stop**! Record the match as a candidate, and continue searching in the left half (`high = mid - 1`).

```java
public static int findFirstOccurrence(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    int result = -1;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            result = mid;       // Candidate found!
            high = mid - 1;     // Keep searching left for an earlier occurrence
        } else if (arr[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return result; // Returns -1 if target is not in the array
}
```

#### Variant 2: Finding the Last Occurrence (`upper_bound`)
Similarly, to find the **last** occurrence (the rightmost index): when a match is found, record it and keep searching in the right half (`low = mid + 1`).

```java
public static int findLastOccurrence(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    int result = -1;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            result = mid;       // Candidate found!
            low = mid + 1;      // Keep searching right for a later occurrence
        } else if (arr[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return result;
}
```

#### Counting Duplicates in $\mathcal{O}(\log n)$:
How many times does value `target` appear in sorted array `arr`?
- Naive scan: $\mathcal{O}(n)$.
- Optimal Binary Search:
  $$\text{Count} = \begin{cases} 0 & \text{if } \text{first} == -1 \\ \text{last} - \text{first} + 1 & \text{otherwise} \end{cases}$$
  Runs in two $\mathcal{O}(\log n)$ binary searches $\implies \mathcal{O}(\log n)$ total time!

#### Variant 3: Binary Search on the Answer Space
Sometimes we are not searching an array, but searching for an optimal numerical parameter $X$ across a range $[L, R]$.
- If a property is **monotonic** (e.g., "if time $X$ is sufficient, any time $> X$ is also sufficient; if time $X$ is insufficient, any time $< X$ is also insufficient"), we can binary search the answer!
- If verifying feasibility takes $\mathcal{O}(C)$ time, finding the optimal answer takes $\mathcal{O}(C \cdot \log(R - L))$ time.

---

### 4.4 The Sliding Window & Two Pointers Paradigm

#### 1. Fixed-Width Sliding Windows (Lab 2 Problem 7 & 8)
- **Scenario:** Given an array of length $n$, find the maximum sum, minimum noise, or average GC-content of any contiguous window of fixed size $k$.
- **The Naive Mistake:** Recomputing the sum of all $k$ elements from scratch for every window position:
  $$\text{Windows} = n - k + 1, \quad \text{Work per window} = k \implies \text{Total Time} = \mathcal{O}((n - k + 1)k) \approx \mathcal{O}(n \cdot k)$$
  If $k \approx n/2$, this degrades to quadratic $\mathcal{O}(n^2)$!
- **The Sliding Window Insight:**
  When sliding the window one position to the right:
  $$\text{New Window Sum} = \text{Old Window Sum} - \text{Outgoing Element} + \text{Incoming Element}$$

```
Array: [ 5,  2,  8,  1,  9,  3 ],  k = 3
Window 0: [ 5,  2,  8 ]            Sum = 15
Slide ->  -5          +1
Window 1:     [ 2,  8,  1 ]        Sum = 15 - 5 + 1 = 11
Slide ->      -2          +9
Window 2:         [ 8,  1,  9 ]    Sum = 11 - 2 + 9 = 18
```

```java
public static int maxFixedWindowSum(int[] arr, int k) {
    int n = arr.length;
    if (n < k) return 0;
    
    // Step 1: Compute sum of first window
    int currentSum = 0;
    for (int i = 0; i < k; i++) {
        currentSum += arr[i];
    }
    int maxSum = currentSum;
    
    // Step 2: Slide the window from index k to n - 1
    for (int i = k; i < n; i++) {
        currentSum += arr[i] - arr[i - k]; // Add incoming, subtract outgoing
        if (currentSum > maxSum) {
            maxSum = currentSum;
        }
    }
    return maxSum; // Time: O(n), Auxiliary Space: O(1)
}
```

#### 2. Variable-Width Two Forward Pointers (Lab 2 Problems 9 & 10)
- **Scenario:** Find the **longest** contiguous subarray with sum $\le \text{limit}$, or the **shortest** contiguous subarray with sum $\ge \text{target}$.
- **Precondition:** **All elements must be non-negative ($arr[i] \ge 0$)!**
- **Mental Model:** A caterpillar moving forward.
  - The `right` pointer expands the window by taking in new elements.
  - When the window violates the constraint, the `left` pointer contracts the window from the back until the condition is restored.

```java
// Problem 9 Pattern: Longest Subarray with Sum <= Limit
public static int longestSubarrayWithinLimit(int[] arr, int limit) {
    int left = 0;
    int currentSum = 0;
    int maxLength = 0;
    
    for (int right = 0; right < arr.length; right++) {
        currentSum += arr[right]; // Expand window to include right
        
        // Contract window from left while condition is violated
        while (currentSum > limit && left <= right) {
            currentSum -= arr[left];
            left++;
        }
        
        // Window [left ... right] is valid
        int currentLength = right - left + 1;
        if (currentLength > maxLength) {
            maxLength = currentLength;
        }
    }
    return maxLength;
}
// Time Complexity: O(n) amortized (left and right each move forward at most n times)
// Auxiliary Space: O(1)
```

#### 3. Sliding Windows with Frequency Tracking (Lab 2 Problem 11)
- **Scenario:** Find the length of the longest contiguous segment containing all unique identifiers (e.g., unique $k$-mers or ASCII characters).
- **Mechanism:** Maintain an array or hash set recording the count of each element in the active window. When `arr[right]` introduces a duplicate, advance `left` and decrement counts until `arr[right]` is unique again. Runs in $\mathcal{O}(n)$ time.

#### 4. The Non-Negativity Precondition: When Two Pointers Fail!
> **CRITICAL MIDTERM WARNING:**
> Why does the variable two-pointer sliding window work?
> Because when elements are non-negative, expanding the window (`right++`) **monotonically increases** the sum, and shrinking the window (`left++`) **monotonically decreases** the sum!
>
> **If negative numbers exist in the array, monotonicity is shattered!** Adding an element could decrease the sum, and shrinking the window could increase the sum. The caterpillar loses its navigational compass.
> For arrays with negative numbers, you **cannot** use a two-pointer sliding window; you must use **Prefix Sums with a Hash Map** ($\mathcal{O}(n)$ time, $\mathcal{O}(n)$ auxiliary space).

---

# Chapter 4: Elementary Sorting Algorithms & Invariants (Lab 2 & Week 03)

### 5.1 Why Sorting Matters & The Invariant Mental Model
Sorting arranges data in a specified linear order (ascending or descending).
In computer science, sorting is the prerequisite that enables:
- Logarithmic $\mathcal{O}(\log n)$ binary searching.
- Rapid duplicate identification.
- Efficient geometric and biological alignment (sorting sequencing reads by chromosomal position).

#### The Secret to Understanding Sorting: Loop Invariants
Never try to memorize sorting algorithms by line numbers. Instead, memorize their **Loop Invariant**—the mathematical truth that holds at the start of every iteration:
1. **Bubble Sort Invariant:** After pass $k$, the **largest $k$ elements are permanently locked in their final sorted positions at the right end** of the array.
2. **Selection Sort Invariant:** After pass $k$, the **smallest $k$ elements are permanently locked in their final sorted positions at the left end** of the array.
3. **Insertion Sort Invariant:** After pass $k$, the **leftmost $k + 1$ elements are sorted among themselves**, though they are not yet in their final permanent positions (they may shift right later).

---

### 5.2 Stability in Sorting: Why It Matters to Data Integrity
An algorithm is **Stable** if items with identical sorting keys preserve their original relative order after sorting.

```
Original list of patients (sorted by Arrival Time):
Index 0: Patient A (Triage Priority 2)
Index 1: Patient B (Triage Priority 1)
Index 2: Patient C (Triage Priority 2)

We now sort by Triage Priority (1 before 2):
- A STABLE Sort produces:
  [ Patient B (1), Patient A (2), Patient C (2) ]
  Notice: Patient A is STILL before Patient C! Arrival order was preserved.

- An UNSTABLE Sort might produce:
  [ Patient B (1), Patient C (2), Patient A (2) ]
  Patient C jumped ahead of Patient A! Original relative order was corrupted.
```

- **Stable Elementary Sorts:** Bubble Sort (with strict inequality), Insertion Sort (with strict inequality).
- **Unstable Elementary Sorts:** Selection Sort (long-distance swaps leapfrog over equal keys).

---

### 5.3 Bubble Sort: Adjacent Pairwise Swaps

#### The Mechanism:
Traverse the array from left to right, comparing adjacent pairs $(a[j], a[j + 1])$. If $a[j] > a[j + 1]$, swap them. The largest unsorted value "bubbles up" to the end like an air bubble in water.

```
Pass 1 on array [ 5, 2, 4, 1 ]:
Compare (5, 2): 5 > 2 -> Swap -> [ 2, 5, 4, 1 ]
Compare (5, 4): 5 > 4 -> Swap -> [ 2, 4, 5, 1 ]
Compare (5, 1): 5 > 1 -> Swap -> [ 2, 4, 1, 5 ]
End of Pass 1: Largest element (5) is fixed at index 3.
```

#### Early-Exit Optimization:
If an entire pass completes **without a single swap**, the array is already sorted! We can break early.

```java
public static void bubbleSort(int[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
        boolean swapped = false; // Flag to track if any swap occurs in this pass
        
        for (int j = 0; j < n - 1 - i; j++) {
            if (a[j] > a[j + 1]) { // Strict inequality ensures stability
                int temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
                swapped = true;
            }
        }
        
        // If no two elements were swapped by inner loop, array is sorted!
        if (!swapped) break;
    }
}
```

#### Detailed Mathematical Analysis:
- **Number of Comparisons:**
  $$(n - 1) + (n - 2) + \dots + 1 = \frac{n(n - 1)}{2} \approx \frac{n^2}{2} \text{ comparisons}$$
- **Number of Swaps:**
  - Best-Case: $0$ swaps.
  - Worst-Case (strictly descending input): $\frac{n(n - 1)}{2} \approx \frac{n^2}{2}$ swaps.
  - Average-Case (random input): $\approx \frac{n^2}{4}$ swaps.
- **Time Complexity:**
  - **Best-Case (Already sorted, with early-exit):** $\Theta(n)$ (one pass of $n - 1$ comparisons, 0 swaps).
  - **Worst-Case (Reversed array):** $\Theta(n^2)$.
  - **Average-Case:** $\Theta(n^2)$.
- **Auxiliary Space:** $\mathcal{O}(1)$ (in-place).
- **Stability:** **Stable** (equal elements are never swapped because $a[j] > a[j + 1]$ is false when $a[j] == a[j + 1]$).

---

### 5.4 Selection Sort: Finding Extremes with Minimum Swaps

#### The Mechanism:
Scan the entire unsorted suffix to find the absolute minimum element. Once found, perform **exactly one swap** to place that minimum at the beginning of the unsorted section.

```
Pass 1 on array [ 5, 2, 4, 1 ]:
Find minimum from index 0 to 3: Minimum is 1 at index 3.
Swap arr[0] with arr[3]:
Array becomes: [ 1, 2, 4, 5 ]
End of Pass 1: Smallest element (1) is fixed at index 0.
```

```java
public static void selectionSort(int[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i; // Assume current position holds minimum
        
        // Scan remaining unsorted elements to find true minimum
        for (int j = i + 1; j < n; j++) {
            if (a[j] < a[minIndex]) {
                minIndex = j; // Update index of minimum
            }
        }
        
        // Swap true minimum into position i (at most 1 swap per outer pass!)
        if (minIndex != i) {
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }
}
```

#### Detailed Mathematical Analysis:
- **Number of Comparisons:**
  Selection sort **always** scans the entire remaining suffix, regardless of input ordering:
  $$(n - 1) + (n - 2) + \dots + 1 = \frac{n(n - 1)}{2} = \Theta(n^2) \text{ comparisons across ALL cases!}$$
- **Number of Swaps:**
  At most $n - 1$ swaps total across the entire algorithm $\implies \mathcal{O}(n)$ swaps!
- **Time Complexity:**
  - **Best-Case:** $\Theta(n^2)$ comparisons.
  - **Worst-Case:** $\Theta(n^2)$ comparisons.
  - **Average-Case:** $\Theta(n^2)$ comparisons.
- **Auxiliary Space:** $\mathcal{O}(1)$ (in-place).
- **Stability:** **UNSTABLE!**
  *Proof of Instability:* Consider `[ 4a, 4b, 2 ]`.
  In Pass 1, the minimum is `2` at index 2. We swap `arr[0]` (`4a`) with `arr[2]` (`2`).
  Array becomes `[ 2, 4b, 4a ]`.
  Notice that `4b` is now ahead of `4a`! The relative order of equal keys was corrupted.

> **When to use Selection Sort:**
> When writing to memory is extremely slow or physically destructive (such as writing to Flash memory or EEPROM, where write cycles wear out the silicon), Selection Sort is superior to Bubble Sort because it guarantees at most $\mathcal{O}(n)$ writes!

---

### 5.5 Insertion Sort: The Card-Player Algorithm & King of Nearly-Sorted Data

#### The Mechanism:
Think of picking up playing cards one at a time. The cards in your left hand are kept sorted. When you draw a new card (`temp`), you shift all cards in your hand that are larger than `temp` one slot to the right, and insert `temp` into the newly created opening.

```
Pass 1 on array [ 5, 2, 4, 1 ]:
Mark index 1 (value 2). Temp = 2.
Prefix is [ 5 ].
Is 5 > 2? Yes -> Shift 5 right: [ 5, 5, 4, 1 ].
Place temp at index 0: [ 2, 5, 4, 1 ].
End of Pass 1: Sorted prefix is [ 2, 5 ].
```

```java
public static void insertionSort(int[] a) {
    int n = a.length;
    for (int i = 1; i < n; i++) {
        int temp = a[i]; // The marked element to insert
        int j = i - 1;
        
        // Shift larger sorted elements to the right to create an insertion slot
        while (j >= 0 && a[j] > temp) {
            a[j + 1] = a[j]; // Shift right
            j--;
        }
        a[j + 1] = temp; // Insert marked element into its sorted position
    }
}
```

#### Detailed Mathematical Analysis:
- **Shifting vs. Swapping:** A shift (`a[j + 1] = a[j]`) requires only **1 assignment**, whereas a swap requires 3 assignments. Insertion sort is much faster in practice than bubble sort!
- **Number of Comparisons and Shifts:**
  - Best-Case: In an already sorted array, `a[j] > temp` is immediately false for every $i$. The inner `while` loop runs 0 times! Total comparisons $= n - 1 = \Theta(n)$.
  - Worst-Case (descending): Full triangular sum $\approx \frac{n^2}{2}$ comparisons and shifts.
  - Average-Case (random): Halfway shifts $\approx \frac{n^2}{4}$ comparisons and shifts.
- **Time Complexity:**
  - **Best-Case (Already sorted or nearly sorted):** $\Theta(n)$.
  - **Worst-Case:** $\Theta(n^2)$.
  - **Average-Case:** $\Theta(n^2)$.
- **Auxiliary Space:** $\mathcal{O}(1)$ (in-place).
- **Stability:** **Stable** (strictly greater condition `a[j] > temp` means equal elements stop shifting, keeping original order).

---

### 5.6 Sorting Objects with Custom Keys (Java `Comparable` & `compareTo`)
In bioinformatics, we rarely sort plain integers; we sort biological records (such as Sequencing Reads with Chromosome, Position, and Quality Score, or Genes with Name and Expression Level).

In Java, objects define natural ordering by implementing the `Comparable<T>` interface and defining `compareTo(T other)`:

```java
public class GenomicFeature implements Comparable<GenomicFeature> {
    public String chromosome;
    public int startPosition;
    public double expressionScore;

    public GenomicFeature(String chr, int pos, double score) {
        this.chromosome = chr;
        this.startPosition = pos;
        this.expressionScore = score;
    }

    // Contract of compareTo:
    // returns negative if (this < other)
    // returns zero if (this == other)
    // returns positive if (this > other)
    @Override
    public int compareTo(GenomicFeature other) {
        // Primary sort: by chromosome name
        int chrCmp = this.chromosome.compareTo(other.chromosome);
        if (chrCmp != 0) return chrCmp;
        
        // Secondary sort: by start position ascending
        return Integer.compare(this.startPosition, other.startPosition);
    }
}
```

---

### 5.7 Deep Comparison Matrix: Bubble vs. Selection vs. Insertion

| Property | Bubble Sort (Optimized) | Selection Sort | Insertion Sort |
| :--- | :--- | :--- | :--- |
| **Best-Case Time** | $\Theta(n)$ | $\Theta(n^2)$ | **$\Theta(n)$** |
| **Average-Case Time** | $\Theta(n^2)$ | $\Theta(n^2)$ | $\Theta(n^2)$ (about $2\times$ faster than Bubble) |
| **Worst-Case Time** | $\Theta(n^2)$ | $\Theta(n^2)$ | $\Theta(n^2)$ |
| **Best-Case Input** | Already sorted | None (always $\Theta(n^2)$ comparisons) | Already sorted or nearly sorted |
| **Worst-Case Input** | Reverse sorted | Reverse sorted | Reverse sorted |
| **Key Comparisons** | $\approx n^2 / 2$ (worst) | $\approx n^2 / 2$ (all cases) | $\approx n^2 / 4$ (avg), $n - 1$ (best) |
| **Swaps / Shifts** | $\approx n^2 / 4$ swaps | **At most $n - 1$ swaps ($\mathcal{O}(n)$)** | $\approx n^2 / 4$ shifts (1 write/shift) |
| **Auxiliary Space** | $\mathcal{O}(1)$ | $\mathcal{O}(1)$ | $\mathcal{O}(1)$ |
| **Stable?** | **Yes** | **No** | **Yes** |
| **Fixed Boundary** | Largest fixed at right end | Smallest fixed at left end | Left prefix sorted unto itself |
| **Best Used For** | Educational simplicity | Minimizing flash/disk writes | Small lists ($n < 50$) or nearly-sorted data |


---

# Chapter 5: Stacks (LIFO), Monotonic Stacks, and Parsing (Lab 3 & Week 04)

### 6.1 The Stack ADT: Contract, Operations, and Boundary States
An **Abstract Data Type (ADT)** is a mathematical model for data types where the data type is defined by its **behavior (WHAT it does)** from the point of view of a user, completely separated from its concrete **implementation (HOW it does it)**.

A **Stack** is a linear collection governed by the **Last-In, First-Out (LIFO)** principle:
- The last item inserted is the first item removed.
- Think of a vertical spring-loaded plate dispenser in a cafeteria, or a stack of Petri dishes: you can only place a new dish on the top, and you can only take the top dish off.

```
       Visual Model of a Stack:
             |          |
   push(30)  |  [ 30 ]  | <- TOP (accessible item)
             |  [ 20 ]  |
             |  [ 10 ]  |
             +----------+
                BOTTOM
```

#### The Stack Operations Contract:
1. `push(x)`: Inserts element $x$ onto the top of the stack.
2. `pop()`: Removes and returns the element currently at the top of the stack.
3. `peek()`: Returns the element at the top without removing it.
4. `isEmpty()`: Returns `true` if the stack contains zero elements.
5. `isFull()`: Returns `true` if a fixed-capacity stack has reached its limit.
6. `size()`: Returns the number of elements currently stored.

#### Boundary Exceptions:
- **Stack Overflow:** Attempting to `push()` into a fixed-capacity stack that is already full.
- **Stack Underflow:** Attempting to `pop()` or `peek()` from a stack that is empty.

---

### 6.2 Implementations: Fixed Array vs. Dynamic Array vs. Singly Linked List

#### 1. Fixed-Array Stack Implementation
We maintain an array `data[]` of capacity $K$ and an integer pointer `top` representing the index of the uppermost valid element. When empty, `top = -1`.

```java
public class ArrayStack {
    private int[] data;
    private int top;
    private int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.top = -1; // -1 indicates an empty stack
    }

    public void push(int x) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack Overflow: capacity reached!");
        }
        top++;
        data[top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack Underflow: stack is empty!");
        }
        int val = data[top];
        top--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack Underflow: stack is empty!");
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
```
- **Performance:** `push`, `pop`, `peek`, `isEmpty`, `size` are all **strictly $\Theta(1)$ constant time**.
- **Memory:** Preallocates $\Theta(K)$ memory, which cannot grow if more elements arrive.

#### 2. Singly Linked Stack Implementation
A linked stack stores each item in a dynamic `Node`. The top of the stack is anchored at the `head` of the linked list:
- `push(x)`: Create new node, point its `next` to current `head`, set `head = newNode`. ($\mathcal{O}(1)$ time).
- `pop()`: Read `head.val`, set `head = head.next`. ($\mathcal{O}(1)$ time).
- **Pros:** Can grow indefinitely until computer RAM is exhausted; zero wasted preallocated capacity.
- **Cons:** Extra memory overhead for reference pointers (each node stores data + a 64-bit reference address).

---

### 6.3 Classical Stack Applications

#### Application 1: Sequence Reversal
Because of LIFO order, pushing $n$ items onto a stack and then popping them all off produces the exact reverse sequence.
- **Complexity:** $\mathcal{O}(n)$ time, $\mathcal{O}(n)$ auxiliary space.

#### Application 2: Delimiter Matching & RNA Secondary Structure Verification
In computer code and RNA biology, open brackets must match closing brackets in strict nested order. In RNA folding, stems form between complementary bases, which must not cross illegally (pseudoknots aside):

$$\text{Valid: } [100 * (100 - 50)] / 2 \quad \iff \quad (((...))) \text{ (Hairpin loop)}$$
$$\text{Invalid: } [100 * (100 - 50)\} / 2 \quad \iff \quad \text{Mismatched delimiters}$$

```java
public static boolean isBalanced(String expression) {
    java.util.ArrayDeque<Character> stack = new java.util.ArrayDeque<>();
    
    for (int i = 0; i < expression.length(); i++) {
        char ch = expression.charAt(i);
        
        // If opening bracket, push to stack
        if (ch == '(' || ch == '[' || ch == '{') {
            stack.push(ch);
        } 
        // If closing bracket, verify match
        else if (ch == ')' || ch == ']' || ch == '}') {
            if (stack.isEmpty()) return false; // Closing bracket with nothing to match!
            
            char top = stack.pop();
            if (ch == ')' && top != '(') return false;
            if (ch == ']' && top != '[') return false;
            if (ch == '}' && top != '{') return false;
        }
    }
    // Must be completely empty at the end (no unclosed openers)
    return stack.isEmpty();
}
// Time Complexity: O(n) linear scan
// Auxiliary Space: O(n) worst-case call stack depth
```

#### Application 3: Arithmetic Expression Parsing (Infix to Postfix & Evaluation)
Humans write arithmetic in **Infix** notation: $A + B \times C$.
Infix is difficult for computers to parse directly because of operator precedence ($\times$ before $+$) and parentheses.
Computers translate Infix into **Postfix** (Reverse Polish Notation, RPN):
$$A + B \times C \implies A\ B\ C \times +$$
In Postfix, operators appear immediately after their operands. Parentheses are completely unnecessary!

##### Algorithm for Evaluating Postfix Expressions:
1. Initialize an operand stack.
2. Scan the postfix token string from left to right:
   - If token is an **operand** (a number), `push` it onto the stack.
   - If token is an **operator** ($\Omega \in \{+, -, \times, /\}$):
     - Pop the top value into $B$.
     - Pop the next top value into $A$.
     - Compute result $= A\ \Omega\ B$.
     - `push(result)` back onto the stack!
3. The final answer is the sole value remaining on the stack.

#### Application 4: Two-Stack Undo and Redo Architecture (Lab 3 Problem 2)
How do text editors and genome browsers implement Undo and Redo? With **Two Stacks**:
1. `undoStack`: Stores past actions.
2. `redoStack`: Stores undone actions.
- **Perform new action:** `undoStack.push(action)`. Clear `redoStack`!
- **Undo:** If `undoStack` not empty: `action = undoStack.pop()`; reverse the action; `redoStack.push(action)`.
- **Redo:** If `redoStack` not empty: `action = redoStack.pop()`; apply the action; `undoStack.push(action)`.
- **Performance:** Every operation runs in strictly $\mathcal{O}(1)$ time!

#### Application 5: The Min-Stack (Constant-Time $O(1)$ Minimum Queries)
How can we design a stack that supports `push`, `pop`, and `getMin()` in $\mathcal{O}(1)$ time?
- **The Secret:** Maintain an auxiliary parallel stack `minStack`.
  - When pushing $x$: push $x$ to `mainStack`. Push $\min(x, \text{currentMin})$ to `minStack`.
  - When popping: pop from both stacks.
  - `getMin()` simply returns `minStack.peek()`.
- **Time Complexity:** All operations $\Theta(1)$. Auxiliary space: $\mathcal{O}(n)$.

---

### 6.4 The Monotonic Stack Technique: Next Greater Element in O(n) Amortized
A **Monotonic Stack** is a stack whose elements are strictly maintained in sorted order (either monotonically increasing or decreasing).

#### The Next Greater Element Problem (Lab 3 Problem 6):
Given an array `[2, 5, 3, 6]`, for each element, find the first element to its right that is strictly greater than it. If none exists, output `-1`.
- **Naive approach:** For each element, scan all elements to its right $\implies \mathcal{O}(n^2)$ time.
- **Monotonic Stack Blueprint:**
  Maintain a stack of **unresolved array indices**.
  Iterate from left to right:
  While the current element $arr[i]$ is greater than the element represented by the index at the top of the stack, $arr[i]$ is the Next Greater Element for that top index! Pop it and record the answer. Then push index $i$.

```java
public static int[] nextGreaterElement(int[] arr) {
    int n = arr.length;
    int[] result = new int[n];
    java.util.Arrays.fill(result, -1); // Default to -1
    
    java.util.ArrayDeque<Integer> stack = new java.util.ArrayDeque<>(); // Stores INDICES
    
    for (int i = 0; i < n; i++) {
        // While stack not empty and current element resolves the waiting element
        while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
            int resolvedIndex = stack.pop();
            result[resolvedIndex] = arr[i];
        }
        stack.push(i); // Push current index to wait for its future greater element
    }
    return result;
}
```

#### The Amortized $\mathcal{O}(n)$ Proof:
Beginners look at the nested `while` loop inside the `for` loop and conclude the runtime is $\mathcal{O}(n^2)$. This is **incorrect**!
- Each index $i \in [0, n - 1]$ is pushed onto the stack **exactly once**.
- Each index is popped from the stack **at most once**.
- Therefore, the inner `while` loop condition can evaluate to `true` at most $n$ times across the **entire execution of the algorithm**.
$$\text{Total Operations} \le n \text{ pushes} + n \text{ pops} = 2n \implies \Theta(n) \text{ amortized time!}$$

---

# Chapter 6: Queues (FIFO), Circular Queues, Deques & Monotonic Deques (Lab 3 & Week 04)

### 7.1 The Queue ADT: Contract, Operations, and Real-World Processing
A **Queue** is a linear collection governed by the **First-In, First-Out (FIFO)** principle:
- The first item inserted is the first item removed.
- Think of a line of customers at a supermarket checkout, or a printer queue: new jobs arrive at the **rear (tail)**, and the next job to be serviced is taken from the **front (head)**.

```
                  Visual Model of a Queue:
Dequeue (leaves)                                  Enqueue (arrives)
 <--- [ Front: 10 ] <--- [ 20 ] <--- [ 30 ] <--- [ Rear: 40 ] <---
```

#### The Queue Operations Contract:
1. `enqueue(x)`: Inserts element $x$ at the rear of the queue.
2. `dequeue()`: Removes and returns the element at the front of the queue.
3. `peek()`: Returns the element at the front without removing it.
4. `isEmpty()`: Returns `true` if the queue contains 0 elements.
5. `isFull()`: Returns `true` if capacity is reached.

---

### 7.2 The Naive Array Queue Flaw: The O(n) Dequeue Shifting Penalty
If you implement a queue using an ordinary array by enqueuing at the end (`data[size++] = x`), removing from the front requires deleting `data[0]`.
To keep elements contiguous starting at index 0, you must **shift every remaining element left by one slot**:
$$data[0] = data[1], \quad data[1] = data[2], \quad \dots$$
This means `dequeue()` takes **$\Theta(n)$ time**! A sequence of $n$ dequeues would take quadratic $\mathcal{O}(n^2)$ time.

---

### 7.3 The Circular Array Queue: Modular Arithmetic & State Disambiguation
To achieve $\mathcal{O}(1)$ for both `enqueue` and `dequeue` without shifting, we use a **Circular Array (Ring Buffer)**.
Instead of shifting elements, we let the `front` and `rear` pointers walk forward, wrapping around to index 0 when they reach the end of the physical array using the **modulo operator (`%`)**:

$$\text{nextIndex} = (\text{currentIndex} + 1) \pmod{\text{capacity}}$$

```
Array of capacity 5:
Index:     0      1      2      3      4
Data:   [ 50 ] [    ] [    ] [ 30 ] [ 40 ]
          ^                     ^
        rear                  front

Front is at index 3, items are 30, 40, 50.
Rear wrapped around to index 0!
```

#### The State Disambiguation Problem:
Notice that if a circular queue is empty, `front == rear`.
If a circular queue becomes completely full, `front == rear` as well!
How do we distinguish between completely empty and completely full?
- **Method 1:** Maintain an explicit `size` counter (`isEmpty` $\iff size == 0$; `isFull` $\iff size == capacity$).
- **Method 2:** Sacrifice one array cell, declaring full when `(rear + 1) % capacity == front`.

```java
public class CircularQueue {
    private int[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int x) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is Full!");
        }
        rear = (rear + 1) % capacity; // Wrap around using modulo
        data[rear] = x;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is Empty!");
        }
        int val = data[front];
        front = (front + 1) % capacity; // Wrap around using modulo
        size--;
        return val;
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("Queue is Empty!");
        return data[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }
    public int size() { return size; }
}
```
- **Performance:** All operations (`enqueue`, `dequeue`, `peek`) are strictly **$\Theta(1)$ constant time**.

---

### 7.4 The Linked Queue with Head and Tail Pointers
A linked queue maintains two pointers: `head` (points to the front node) and `tail` (points to the rear node).

```
   head                                         tail
    |                                            |
    v                                            v
[ 10 | next ] ----> [ 20 | next ] ----> [ 30 | null ]
```

- **Enqueue(x):**
  - Create `newNode = new Node(x, null)`.
  - If queue is empty: `head = tail = newNode`.
  - Else: `tail.next = newNode; tail = newNode`.
  - Time: $\Theta(1)$.
- **Dequeue():**
  - If empty: throw exception.
  - Read `val = head.val`.
  - `head = head.next`.
  - **Critical Invariant:** If `head == null`, the queue just became empty! You **must** set `tail = null` as well, or the tail reference will dangle in memory and prevent garbage collection.
  - Time: $\Theta(1)$.

---

### 7.5 Priority Queues: Priority-Based Extraction
In an ordinary queue, the first item to arrive is the first to leave.
In a **Priority Queue**, each item has an assigned priority value; `dequeue()` always extracts the item with the **highest priority** (or lowest, in a min-priority queue), regardless of arrival order.

#### Tradeoffs of Elementary Representations:
| Implementation | `enqueue()` Time | `dequeueMax()` Time | Notes |
| :--- | :--- | :--- | :--- |
| **Unordered Array** | $\Theta(1)$ (insert at end) | $\Theta(n)$ (scan all items to find max) | Great if writes are frequent, reads rare. |
| **Ordered Array** | $\Theta(n)$ (shifted insertion) | $\Theta(1)$ (pop from end) | Great if reads/extractions dominate. |
| **Sorted Linked List** | $\Theta(n)$ (linear search for slot) | $\Theta(1)$ (remove head) | No shifts, but pointer traversal costs $\Theta(n)$. |
| **Binary Heap** | $\Theta(\log n)$ | $\Theta(\log n)$ | Advanced tree structure (covered in 2nd half of course). |

---

### 7.6 Double-Ended Queues (Deque) & Monotonic Deque for Sliding Window Maximum
A **Deque** (pronounced "deck") allows insertions and deletions at **both** ends:
`addFirst`, `addLast`, `removeFirst`, `removeLast`.

#### The Sliding Window Maximum Problem (Lab 3 Problem 5):
Given an array and a sliding window of fixed width $k$, output the maximum value in every window.
- **Naive approach:** Scan all $k$ elements in each window $\implies \mathcal{O}(n \cdot k)$ time.
- **Optimal Monotonic Deque Blueprint ($\mathcal{O}(n)$ time):**
  Maintain a Deque that stores **indices** of candidate elements in **monotonically decreasing** order of their values:
  1. **Evict Out-of-Window Elements:** If `deque.peekFirst() <= i - k`, remove it from the front (it has aged out of the window).
  2. **Maintain Monotonicity:** While `deque` is not empty and $arr[i] \ge arr[\text{deque.peekLast()}]$, remove from the rear! (Why? Because as long as $arr[i]$ is in the window, the smaller, older elements at the rear can never possibly be the maximum again!).
  3. `deque.addLast(i)`.
  4. The maximum of the current window is always at `arr[deque.peekFirst()]`!

```java
public static int[] maxSlidingWindow(int[] arr, int k) {
    if (arr == null || k <= 0) return new int[0];
    int n = arr.length;
    int[] result = new int[n - k + 1];
    java.util.ArrayDeque<Integer> deque = new java.util.ArrayDeque<>(); // Stores INDICES
    
    for (int i = 0; i < n; i++) {
        // Step 1: Remove elements out of window range
        if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }
        
        // Step 2: Remove elements smaller than current from rear
        while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
            deque.pollLast();
        }
        
        // Step 3: Add current index to rear
        deque.offerLast(i);
        
        // Step 4: Record answer once first window is formed
        if (i >= k - 1) {
            result[i - k + 1] = arr[deque.peekFirst()];
        }
    }
    return result; // Time: O(n) amortized, Auxiliary Space: O(k) deque
}
```


---

# Chapter 7: Linked Lists in Depth: Nodes, Pointers, and Invariants (Lab 3 & Week 05)

### 8.1 Memory Architecture: Why References Replace Contiguity
In an array, data elements are forced to reside in consecutive physical RAM addresses. While this gives instantaneous $\mathcal{O}(1)$ random access, it comes at a steep price:
1. Resizing requires allocating a brand-new array and copying all elements ($\mathcal{O}(n)$).
2. Inserting or deleting at the front requires shifting every element ($\mathcal{O}(n)$).
3. Memory fragmentation: if RAM is divided into scattered free pockets, allocating a large continuous array of 1 GB may fail even if 4 GB of total free RAM is scattered across memory.

A **Linked List** completely abandons memory contiguity.
Instead of sitting next to each other, elements are stored in independent memory objects called **Nodes**. Each node stores:
1. **Data payload:** The value (e.g., integer, gene ID, nucleotide).
2. **Reference pointer (`next`):** The 64-bit memory address of the next node in the sequence.

```
       Visual Comparison of Physical RAM Storage:
Array:       [ 10 ][ 20 ][ 30 ][ 40 ]  (Locked consecutively in memory)

Linked List: [ 10 | * ] ----> (across RAM) ----> [ 20 | * ] ----> (across RAM) ----> [ 30 | null ]
             Addr: 0x100                         Addr: 0x850                         Addr: 0x420
```

---

### 8.2 Singly Linked Lists: Node Structure, Head, and Tail

#### Node Class Definition:
In Java, a Node is defined as an inner class:
```java
public class Node {
    public int item;
    public Node next; // Reference to the next Node object in Heap memory

    public Node(int item, Node next) {
        this.item = item;
        this.next = next;
    }
}
```

#### Core Operations on a Singly Linked List:

```
Head-Only Singly List:
head
 |
 v
[ 10 | next ] ----> [ 20 | next ] ----> [ 30 | null ]
```

1. **Insert at Front (`addFirst(x)`):**
   - Create `newNode = new Node(x, head)`.
   - Update `head = newNode`.
   - **Time:** $\Theta(1)$ constant time! No elements shifted!
2. **Delete at Front (`removeFirst()`):**
   - If `head == null`, throw underflow.
   - Set `head = head.next`. The old head is automatically reaped by the Garbage Collector.
   - **Time:** $\Theta(1)$ constant time!
3. **Insert at End (`addLast(x)`):**
   - If list has **Head only**: You must traverse all $n$ nodes from `head` to find the last node whose `next == null`. Time is **$\Theta(n)$**.
   - If list maintains a **Tail pointer** (`head + tail`): Set `tail.next = newNode; tail = newNode`. Time is **$\Theta(1)$**.
4. **Delete at End (`removeLast()`):**
   - **CRITICAL EXAM TRAP:** Even if you maintain a `tail` pointer, deleting the last node in a Singly Linked List still takes **$\Theta(n)$ time**!
   - *Why?* Because to remove `tail`, you must update the *predecessor's* `next` pointer to `null`. In a singly linked list, pointers only go forward; you cannot look backward from `tail`! You must traverse from `head` to find the node immediately before `tail`.

---

### 8.3 The Sentinel Node Pattern: Eliminating Null Pointers Forever
In standard linked lists, programmers write dozens of tedious `if (head == null)` boundary checks. Forgetting even one causes a fatal `NullPointerException`.

In CS 61B and university DSA courses, the industry-standard solution is the **Sentinel Node** (as implemented in `SLList.java` in your course repository).
A Sentinel is a permanent, dummy node that always exists at the beginning of the list. It holds dummy data, and `sentinel.next` always points to the true first element.

```
       SLList with Sentinel Node 's':
s (sentinel dummy)       True First Node            Second Node
+---------------+       +---------------+       +---------------+
| item: 0       |       | item: 10      |       | item: 20      |
| next: [ptr]---|-----> | next: [ptr]---|-----> | next: null    |
+---------------+       +---------------+       +---------------+
```

```java
// Direct pattern from course file: lab-materials/in-class-assignments/exercise-01_session-04/SLList.java
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            this.item = i;
            this.next = n;
        }
    }

    private IntNode s; // Sentinel node: NEVER null!
    private int size;

    public SLList() {
        s = new IntNode(0, null); // Sentinel created immediately
        size = 0;
    }

    public void addFirst(int x) {
        // No 'if (head == null)' check needed! Works universally!
        s.next = new IntNode(x, s.next);
        size++;
    }

    public void display() {
        IntNode p = s.next; // Start at the true first node, skipping dummy sentinel
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
}
```

---

### 8.4 Doubly Linked Lists: Bidirectional Traversal and O(1) Internal Deletions
A **Doubly Linked List (DLL)** equips every node with **two** references:
- `next`: Points to the following node.
- `prev`: Points to the preceding node.

```
Visual Model of a Doubly Linked Node:
         +-----------------------+
<------- | prev | item: 42| next | ------->
         +-----------------------+
```

#### Why Doubly Linked Lists are Superhuman:
1. **Bidirectional Traversal:** Can traverse forward or backward with equal ease.
2. **$\mathcal{O}(1)$ Removal of Any Known Node:**
   In a singly linked list, removing node $P$ requires the predecessor. In a DLL, node $P$ *already knows* its predecessor (`P.prev`)!
   ```java
   // Removing node P in O(1) time without any traversal:
   P.prev.next = P.next;
   if (P.next != null) {
       P.next.prev = P.prev;
   }
   // Node P is now completely unlinked from the chain!
   ```
3. **$\mathcal{O}(1)$ Delete at End:** `removeLast()` takes strictly $\Theta(1)$ because `tail.prev` gives instantaneous access to the second-to-last node!

---

### 8.5 Circular Linked Lists (Singly and Doubly)
- **Circular Singly Linked List:** The last node's `next` pointer does not point to `null`; it wraps around to point back to `head`.
  - *Pro-tip:* If you maintain a single pointer to `tail`, you get both ends for free! `tail` is the end, and `tail.next` is the `head`!
- **Circular Doubly Linked List:** `head.prev` points to `tail`, and `tail.next` points to `head`. A complete closed loop.

---

### 8.6 Essential Linked List Algorithms

#### 1. Iterative Link Reversal (The Three-Pointer Dance)
- **Problem:** Reverse a singly linked list in-place in $\mathcal{O}(n)$ time and $\mathcal{O}(1)$ auxiliary space.
- **The Blueprint:** Maintain three pointers: `prev`, `curr`, and `nextTemp`. At each step, turn `curr.next` around to point backward to `prev`.

```java
public static Node reverseList(Node head) {
    Node prev = null;
    Node curr = head;
    
    while (curr != null) {
        Node nextTemp = curr.next; // Step 1: Save next node before breaking pointer!
        curr.next = prev;          // Step 2: Reverse the link!
        prev = curr;               // Step 3: Advance prev forward
        curr = nextTemp;           // Step 4: Advance curr forward
    }
    return prev; // prev is now pointing to the new head of the reversed list!
}
// Time Complexity: O(n) - visits each node once
// Auxiliary Space: O(1) - exactly 3 pointer variables
```

#### 2. Middle Node Detection via Tortoise & Hare (Slow and Fast Pointers)
- **Problem:** Find the exact middle node of a linked list in a single pass without counting the length.
- **The Blueprint:**
  - Launch two pointers at `head`: `slow` and `fast`.
  - Advance `slow` by 1 step (`slow = slow.next`).
  - Advance `fast` by 2 steps (`fast = fast.next.next`).
  - When `fast` reaches the end of the list, `slow` is guaranteed to be sitting at the exact middle!

```java
public static Node findMiddle(Node head) {
    if (head == null) return null;
    Node slow = head;
    Node fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;       // 1 step
        fast = fast.next.next;  // 2 steps
    }
    return slow; // Time: O(n), Auxiliary Space: O(1)
}
```

#### 3. Cycle Detection: Floyd's Algorithm vs. Hash Set Tracking (Lab 3 Problem 11)
- **Problem:** Detect if a linked list contains a cycle (an infinite loop where a node's `next` points back to an earlier node).
- **Approach A (Hash Set):**
  - Store each node's memory identity in a `HashSet<Node>`.
  - If you encounter a node already in the set, a cycle exists.
  - *Drawback:* Uses $\mathcal{O}(n)$ auxiliary memory!
- **Approach B (Floyd's Tortoise and Hare - Optimal $\mathcal{O}(1)$ Space):**
  - Advance `slow` by 1 step, `fast` by 2 steps.
  - If there is no cycle, `fast` will hit `null` and terminate.
  - If a cycle exists, `fast` will enter the loop and relentlessly lap `slow` from behind until `slow == fast`!

```java
public static boolean hasCycle(Node head) {
    if (head == null) return false;
    Node slow = head;
    Node fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return true; // Pointers collided: cycle detected!
        }
    }
    return false; // Reached end: strictly acyclic
}
// Time Complexity: O(n), Auxiliary Space: O(1)
```

#### 4. Why Binary Search FAILS on Linked Lists
> **THE #1 MIDTERM THEORY QUESTION:**
> *"Can we perform Binary Search on a sorted Singly Linked List in $\mathcal{O}(\log n)$ time?"*
>
> **ANSWER: ABSOLUTELY NOT!**
> Binary search requires finding the midpoint in $\mathcal{O}(1)$ time using direct address arithmetic `arr[mid]`.
> In a linked list, to reach the midpoint node, you must walk reference by reference from the head, which takes $\Theta(n)$ time!
> Even if you divide the list in half, the recurrence relation becomes:
> $$T(n) = T(n/2) + \Theta(n) \implies T(n) = \Theta(n)$$
> Binary search on a linked list completely loses its logarithmic speed and runs in **linear $\Theta(n)$ time**—no better than a standard linear search!

#### 5. The LRU (Least Recently Used) Cache Architecture (Lab 3 Problem 12)
How do web browsers and operating systems cache the most recent pages and discard the oldest?
- Combines a **Doubly Linked List** and a **Hash Map**:
  - The Doubly Linked List maintains access recency (Head = Most Recently Used, Tail = Least Recently Used).
  - The Hash Map maps keys directly to DLL Node references for $\mathcal{O}(1)$ lookup.
  - When an item is accessed or added, remove it from its current position in DLL in $\mathcal{O}(1)$ and insert at Head in $\mathcal{O}(1)$.
  - When cache capacity is exceeded, evict `tail.prev` in $\mathcal{O}(1)$!

---

# Chapter 8: Recursion & The Call-Stack Mechanics (Lab 4 & Week 06)

### 9.1 The Recursive Mental Model: Contracts and Self-Similarity
**Recursion** is a programming technique where a method solves a large problem by calling itself to solve a smaller instance of the exact same problem.

#### The Contractual Mental Model:
When writing or reading a recursive function, **never attempt to trace the entire execution in your head all at once**. That leads to cognitive overload.
Instead, think in terms of a **Contract**:
- State clearly: *"What does `method(input)` promise to return?"*
- Assume that `method(smallerInput)` **already works perfectly**.
- Figure out how to combine the answer of `method(smallerInput)` with the current piece of work to fulfill the contract.

---

### 9.2 The Three Mandatory Invariants: Base Case, Recursive Case, Progress
Every valid, terminating recursive algorithm must satisfy three mathematical pillars:

```
+-------------------------------------------------------------------+
|                     THE 3 PILLARS OF RECURSION                    |
|                                                                   |
| 1. THE BASE CASE:                                                 |
|    A condition where the problem is so trivial that the answer    |
|    can be returned immediately without any recursive call.        |
|                                                                   |
| 2. THE RECURSIVE CASE:                                            |
|    Calling the function itself on a strictly smaller subproblem.  |
|                                                                   |
| 3. DEFINITE PROGRESS:                                             |
|    Every recursive call must move strictly closer to the base     |
|    case, guaranteeing termination!                                |
+-------------------------------------------------------------------+
```

If you miss the base case or fail to make progress, your method enters infinite recursion, rapidly filling the JVM call stack until the system crashes with a `StackOverflowError`.

---

### 9.3 Under the Hood: The JVM Call Stack & Stack Frame Lifecycle
How does the computer actually execute a recursive function?
Every time a Java method is called, the JVM allocates a **Stack Frame** (or Activation Record) on the Call Stack.
A Stack Frame contains:
1. The method's parameter arguments.
2. All local variables declared inside the method.
3. The **Return Address**: the line number in the calling function where execution must resume once this call finishes.

---

### 9.4 Call Descent vs. Return Ascent (Unwinding)
Consider computing $4!$ (4 factorial) using:
```java
static long factorial(int n) {
    if (n <= 1) return 1;       // Base case
    return n * factorial(n - 1); // Recursive call
}
```

#### Phase 1: Call Descent (Stack Growth)
Calls plunge downward, allocating a new frame on the call stack at each level. Calculations are suspended waiting for children to return:

```
[ Frame 4: factorial(1) ] -> Hits base case! Returns 1.
[ Frame 3: factorial(2) ] -> Suspended: waiting for 2 * factorial(1)
[ Frame 2: factorial(3) ] -> Suspended: waiting for 3 * factorial(2)
[ Frame 1: factorial(4) ] -> Suspended: waiting for 4 * factorial(3)
```

#### Phase 2: Return Ascent (Stack Unwinding)
Base case returns, and values propagate back upward. Each frame completes its pending multiplication and is popped from the stack:

```
factorial(1) returns 1
factorial(2) computes 2 * 1 = 2, returns 2 (Frame 3 popped)
factorial(3) computes 3 * 2 = 6, returns 6 (Frame 2 popped)
factorial(4) computes 4 * 6 = 24, returns 24 (Frame 1 popped)
```

- **Time Complexity:** $\Theta(n)$ (exactly $n$ method invocations).
- **Auxiliary Call-Stack Space:** $\Theta(n)$ (at peak descent, $n$ stack frames exist simultaneously in memory).

> **Bioinformatics Precision Note (64-bit Long Overflow):**
> As highlighted in Lab 4, $20! \approx 2.43 \times 10^{18}$, which barely fits in Java's signed 64-bit `long` ($9.22 \times 10^{18}$).
> Calling `factorial(21)` silently overflows into a negative number! For $n > 20$, you must use `java.math.BigInteger`.

---

### 9.5 Fundamental Recursive Patterns (From Lab 4 Reference Code)

#### Pattern 1: Single Decreasing Integer (Triangular Numbers & Factorials)
A **Triangular Number** $T(n)$ is the number of dots in a triangle with $n$ dots on a side: $T(n) = n + (n - 1) + \dots + 1$.
- Contract: `triangle(n) = n + triangle(n - 1)`, with base `triangle(1) = 1`.
```java
static int triangle(int n) {
    if (n <= 1) return n;
    return n + triangle(n - 1);
}
```

#### Pattern 2: Suffix / Linear Array Traversal (`sumArray`)
- Contract: `sumArray(a, i)` returns the sum of all elements from index $i$ to the end of the array.
```java
static long sumArray(int[] a, int i) {
    if (i == a.length) return 0; // Base case: empty suffix has sum 0
    return a[i] + sumArray(a, i + 1); // Current element + sum of remaining suffix
}
```

#### Pattern 3: Two-Pointer Convergent Recursion (`isPalindrome`)
- Contract: Checks if subarray from `lo` to `hi` reads identically forwards and backwards.
```java
static boolean isPalindrome(int[] a, int lo, int hi) {
    if (lo >= hi) return true; // Base case: 0 or 1 elements left -> always symmetric!
    if (a[lo] != a[hi]) return false; // Mismatch detected!
    return isPalindrome(a, lo + 1, hi - 1); // Converge inwards
}
```

#### Pattern 4: Digit Sum
- Contract: Compute sum of base-10 digits of an integer.
```java
static int digitSum(int n) {
    if (n < 10) return n; // Base case: single digit
    return (n % 10) + digitSum(n / 10); // Last digit + sum of remaining digits
}
```

#### Pattern 5: Recursive Binary Search
- Contract: Search sorted subarray `arr[lo ... hi]` for target $t$.
```java
static int binarySearchRecursive(int[] sorted, int lo, int hi, int t) {
    if (lo > hi) return -1; // Base case: search interval empty
    
    int mid = lo + (hi - lo) / 2;
    if (sorted[mid] == t) return mid; // Found!
    if (sorted[mid] < t) {
        return binarySearchRecursive(sorted, mid + 1, hi, t); // Recurse right
    } else {
        return binarySearchRecursive(sorted, lo, mid - 1, t); // Recurse left
    }
}
```
- **Recurrence:** $T(n) = T(n/2) + \mathcal{O}(1)$.
- **Time Complexity:** $\Theta(\log n)$.
- **Auxiliary Call-Stack Space:** $\Theta(\log n)$ (maximum call depth is $\log_2 n$).

#### Pattern 6: Multi-Branch Recursion (Towers of Hanoi)
The ancient puzzle: Move $n$ disks from Source peg ($S$) to Destination peg ($D$) using Intermediate peg ($I$), never placing a larger disk on a smaller disk.
- **Contract:**
  1. Recursively move top $n - 1$ disks from $S$ to $I$.
  2. Move disk $n$ directly from $S$ to $D$.
  3. Recursively move $n - 1$ disks from $I$ to $D$.

```java
public static void hanoi(int n, char S, char I, char D) {
    if (n == 1) {
        System.out.println("Move disk 1 from " + S + " to " + D);
        return;
    }
    hanoi(n - 1, S, D, I);
    System.out.println("Move disk " + n + " from " + S + " to " + D);
    hanoi(n - 1, I, S, D);
}
```
- **Recurrence:** $T(n) = 2T(n - 1) + 1$.
- **Time Complexity:** $\Theta(2^n)$ — exponential time! For $n = 64$ disks, the universe will end before it finishes.

---

### 9.6 Recurrence Relations and the Unwinding Method
To find the Big-O bound of a recursive function, we express its runtime as a **Recurrence Relation** and solve it using the **Unwinding (Repeated Substitution) Method**:

#### Example: Solving Factorial / Linear Scan
$$T(n) = T(n - 1) + c, \quad T(1) = d$$
Substitute repeatedly:
$$T(n) = T(n - 2) + c + c = T(n - 2) + 2c$$
$$T(n) = T(n - 3) + 3c$$
After $k$ steps:
$$T(n) = T(n - k) + k \cdot c$$
Reach base case when $n - k = 1 \implies k = n - 1$:
$$T(n) = T(1) + (n - 1)c = d + (n - 1)c = \Theta(n)$$


---

# Chapter 9: Merge Sort: The Divide-and-Conquer Masterclass (Week 06 & Session 05)

### 10.1 The Divide-and-Conquer Paradigm: Divide, Conquer, Combine
All elementary sorting algorithms we studied (Bubble, Selection, Insertion) have quadratic $\mathcal{O}(n^2)$ worst-case time complexity. For a dataset of $n = 1,000,000$ DNA reads, $\mathcal{O}(n^2)$ is completely intractable.

To break the $\mathcal{O}(n^2)$ barrier, computer science uses the **Divide-and-Conquer** paradigm, invented by John von Neumann in 1945:

```
+-----------------------------------------------------------------------+
|                    THE DIVIDE-AND-CONQUER PARADIGM                    |
|                                                                       |
| 1. DIVIDE:                                                            |
|    Divide the problem into a number of smaller subproblems that are   |
|    smaller instances of the same problem.                             |
|                                                                       |
| 2. CONQUER:                                                           |
|    Conquer the subproblems by solving them recursively. If the        |
|    subproblem sizes are small enough (base case), solve them directly.|
|                                                                       |
| 3. COMBINE:                                                           |
|    Combine the solutions of the subproblems into the solution for the |
|    original problem.                                                  |
+-----------------------------------------------------------------------+
```

In **Merge Sort**, the Divide step is trivial (cut array in half), the Conquer step is recursive, and the **Combine step (the Merge)** is where all the actual work happens!

---

### 10.2 The Core Merging Engine: Merging Two Sorted Subarrays in O(n)
Suppose you have two piles of playing cards, each **already sorted** in ascending order:
- Pile $L$: `[ 2, 5, 8 ]`
- Pile $R$: `[ 1, 6, 9 ]`
How do you merge them into a single sorted pile?
You look at the top card of both piles. Whichever is smaller gets placed into the output array, and you advance your pointer on that pile. You repeat this until all cards are merged.

#### Method 1: The Sentinel Method (Directly from Course `Main.java`)
In classic CLRS algorithm design (as taught in session 5: `exercise-01_session-05/Main.java`), to avoid constantly checking whether array $L$ or $R$ has run out of elements, we place a **Sentinel value** ($\infty$, represented by `Integer.MAX_VALUE`) at the end of both temporary arrays.

Since `Integer.MAX_VALUE` is larger than any valid integer, whenever one array is exhausted, the comparison `L[i] <= R[j]` will naturally pick the remaining elements from the other array without needing separate boundary `while` loops!

```java
// Exact code from: lab-materials/in-class-assignments/exercise-01_session-05/Main.java
private static void mergeWithSentinels(int[] A, int p, int q, int r) {
    int n1 = q - p + 1; // Size of left subarray
    int n2 = r - q;     // Size of right subarray

    // Allocate temporary arrays with 1 extra slot for the sentinel!
    int[] L = new int[n1 + 1];
    int[] R = new int[n2 + 1];

    // Copy data into temporary scratch arrays
    for (int i = 0; i < n1; i++) {
        L[i] = A[p + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = A[q + 1 + j];
    }

    // Place sentinels at the ends!
    L[n1] = Integer.MAX_VALUE;
    R[n2] = Integer.MAX_VALUE;

    int i = 0;
    int j = 0;

    // Merge back into original array A from index p to r
    for (int k = p; k <= r; k++) {
        if (L[i] <= R[j]) { // '<=' ensures STABILITY!
            A[k] = L[i];
            i++;
        } else {
            A[k] = R[j];
            j++;
        }
    }
}
```

#### Method 2: The Standard Boundary-Checked Method (Clean Production Code)
If the array may legitimately contain `Integer.MAX_VALUE`, sentinels cannot be used. We use standard index boundary checks:

```java
private static void mergeStandard(int[] a, int p, int q, int r) {
    int n1 = q - p + 1;
    int n2 = r - q;
    int[] L = new int[n1];
    int[] R = new int[n2];

    for (int i = 0; i < n1; i++) L[i] = a[p + i];
    for (int j = 0; j < n2; j++) R[j] = a[q + 1 + j];

    int i = 0, j = 0, k = p;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            a[k++] = L[i++];
        } else {
            a[k++] = R[j++];
        }
    }
    // Copy any remaining elements from L or R
    while (i < n1) a[k++] = L[i++];
    while (j < n2) a[k++] = R[j++];
}
```

---

### 10.3 Complete Merge Sort Algorithm & Recursive Tree Walkthrough

```java
public class MergeSort {

    public static void mergeSort(int[] A, int p, int r) {
        // Base case: if p >= r, the subarray has 0 or 1 elements and is already sorted!
        if (p < r) {
            // Divide: find the midpoint
            int q = p + (r - p) / 2;
            
            // Conquer: recursively sort the left half
            mergeSort(A, p, q);
            
            // Conquer: recursively sort the right half
            mergeSort(A, q + 1, r);
            
            // Combine: merge the two sorted halves
            merge(A, p, q, r);
        }
    }
}
```

#### Visualizing the Complete Execution Tree on `[ 38, 27, 43, 3, 9, 82, 10 ]`:

```
                             [38, 27, 43, 3, 9, 82, 10]
                                      /      \
                        [38, 27, 43, 3]      [9, 82, 10]
                            /      \            /     \
                       [38, 27]   [43, 3]    [9, 82]  [10]
                        /    \     /   \      /   \     |
                      [38]  [27] [43]  [3]  [9]  [82]  [10]  <- Base cases (size 1)
                        \    /     \   /      \   /     |
                       [27, 38]   [3, 43]    [9, 82]  [10]  <- Merge level 1
                            \      /            \     /
                        [3, 27, 38, 43]      [9, 10, 82]    <- Merge level 2
                                      \      /
                             [3, 9, 10, 27, 38, 43, 82]     <- Final Merge
```

---

### 10.4 Rigorous Complexity & Memory Analysis

#### 1. Recurrence Derivation: Why $T(n) = \Theta(n \log n)$ across ALL cases
Let $T(n)$ be the time to sort an array of size $n$:
- **Divide step:** Calculating $q$ takes $\mathcal{O}(1)$ time.
- **Conquer step:** Two recursive calls on subarrays of size $n/2 \implies 2T(n/2)$.
- **Combine step:** Merging two subarrays of combined length $n$ takes $c \cdot n$ operations.

Recurrence Relation:
$$T(n) = 2T\left(\frac{n}{2}\right) + c \cdot n, \quad T(1) = d$$

Let's solve this using the **Recursion Tree Method**:
- **Tree Depth:** How many times can you divide $n$ by 2 before reaching size 1? Exactly $\log_2 n$ levels.
- **Work at Level 0:** 1 problem of size $n \implies c \cdot n$ work.
- **Work at Level 1:** 2 problems of size $n/2 \implies 2 \times c(n/2) = c \cdot n$ work.
- **Work at Level 2:** 4 problems of size $n/4 \implies 4 \times c(n/4) = c \cdot n$ work.
- **Work at Level $i$:** $2^i$ problems of size $n / 2^i \implies 2^i \times c(n / 2^i) = c \cdot n$ work.

$$\text{Total Work} = \sum_{\text{level}=0}^{\log_2 n} c \cdot n = (c \cdot n) \times (\log_2 n + 1) = \Theta(n \log_2 n)$$

> **CRITICAL EXAM TAKEAWAY:**
> Unlike Bubble Sort or Insertion Sort, **Merge Sort does not care about the initial ordering of the input!**
> Whether the array is already sorted, completely reversed, or random, it always divides down to single elements and merges them back up.
> **Best-Case:** $\Theta(n \log n)$
> **Average-Case:** $\Theta(n \log n)$
> **Worst-Case:** $\Theta(n \log n)$

#### 2. Auxiliary Space: Why Merge Sort Requires $\Theta(n)$ Extra Memory
During the merge step, elements from $A[p \dots q]$ and $A[q+1 \dots r]$ are copied into scratch arrays $L$ and $R$.
- At the top level of the recursion tree, merging the two halves requires scratch arrays summing to length $n$.
- Even if temporary arrays are freed or reused, at peak merge, **$\Theta(n)$ auxiliary array memory is required**.
- In addition, the recursive call stack reaches a maximum depth of $\log_2 n$, consuming **$\Theta(\log n)$ call-stack space**.
$$\text{Total Auxiliary Space} = \Theta(n) \text{ scratch memory} + \Theta(\log n) \text{ stack} = \Theta(n)$$

#### 3. Stability Analysis: Why Merge Sort Preserves Equality Order
Look closely at line 28 of `Main.java`:
```java
if (L[i] <= R[j]) {
    A[k] = L[i];
    i++;
}
```
When an element in the left half has the **exact same value** as an element in the right half ($L[i] == R[j]$), the `<=` condition evaluates to `true`, forcing the algorithm to take the element from the **left array first**.
Because the left array held the elements that appeared earlier in the original array, their original relative order is strictly preserved!
Therefore, **Merge Sort is STABLE**.

*(Caution: If a programmer carelessly writes `if (L[i] < R[j])`, the right element is taken first on equality, destroying stability!)*

---

### 10.5 Merge Sort vs. Elementary Sorts: When to Use What

| Feature | Bubble Sort | Selection Sort | Insertion Sort | **Merge Sort** |
| :--- | :--- | :--- | :--- | :--- |
| **Best-Case Time** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n)$ | **$\Theta(n \log n)$** |
| **Average-Case Time** | $\Theta(n^2)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | **$\Theta(n \log n)$** |
| **Worst-Case Time** | $\Theta(n^2)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | **$\Theta(n \log n)$** |
| **Auxiliary Space** | $\mathcal{O}(1)$ (In-place) | $\mathcal{O}(1)$ (In-place) | $\mathcal{O}(1)$ (In-place) | **$\Theta(n)$ (Scratch Array)** |
| **Stable?** | Yes | No | Yes | **Yes** |
| **Scaling to $n = 10^6$** | Unusable ($\approx 16\text{ min}$) | Unusable ($\approx 16\text{ min}$) | Unusable ($\approx 16\text{ min}$) | **Fast ($\approx 0.02\text{ sec}$)** |
| **Ideal Use Case** | Conceptual teaching | Minimal write cycles | Small arrays ($n < 50$), nearly sorted | **Large general-purpose sorting** |

---

# Chapter 10: Midterm Synthesis, Master Complexity Tables & Decision Trees

### 11.1 The Master Complexity Reference Table
*(Synthesized directly from University Midterm Cheatsheets 01 & 02)*

#### 1. Sorting Algorithms
| Algorithm | Best-Case Time | Average Time | Worst-Case Time | Auxiliary Space | Stable? | Key Mechanism |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort (Early Exit)** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | $\mathcal{O}(1)$ | Yes | Adjacent swaps; stops if pass makes 0 swaps. |
| **Bubble Sort (No Early Exit)** | $\Theta(n^2)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | $\mathcal{O}(1)$ | Yes | Always runs all $n(n - 1)/2$ comparisons. |
| **Selection Sort** | $\Theta(n^2)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | $\mathcal{O}(1)$ | **No** | Scans suffix for minimum; at most $n - 1$ swaps. |
| **Insertion Sort** | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | $\mathcal{O}(1)$ | Yes | Shifts sorted prefix rightward; fast on nearly sorted. |
| **Merge Sort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | **$\Theta(n)$** | Yes | Divide-and-conquer; merges sorted halves with scratch array. |

#### 2. Searching Algorithms
| Method | Best Time | Worst Time | Auxiliary Space | Precondition / Requirements |
| :--- | :--- | :--- | :--- | :--- |
| **Linear Search** | $\Theta(1)$ | $\Theta(n)$ | $\mathcal{O}(1)$ | None; unsorted array. |
| **Binary Search (Iterative)** | $\Theta(1)$ | $\Theta(\log n)$ | $\mathcal{O}(1)$ | **Sorted array with $\mathcal{O}(1)$ indexed access.** |
| **Binary Search (Recursive)** | $\Theta(1)$ | $\Theta(\log n)$ | $\Theta(\log n)$ | Sorted array; consumes stack frames. |
| **First/Last Occurrence Binary**| $\Theta(\log n)$ | $\Theta(\log n)$ | $\mathcal{O}(1)$ | Continues search in left/right half on match. |
| **Binary Search on Answer** | $\mathcal{O}(C)$ | $\mathcal{O}(C \cdot \log R)$ | Check-dependent | Monotonic feasibility check function $C$. |

#### 3. Arrays, Vectors, and Sliding Windows
| Operation / Technique | Time Complexity | Auxiliary Space | Notes & Constraints |
| :--- | :--- | :--- | :--- |
| **Array Read/Set by Index** | $\Theta(1)$ | $\mathcal{O}(1)$ | Valid index within $[0, n - 1]$. |
| **Array Insert/Delete (Front/Mid)**| $\Theta(n)$ | $\mathcal{O}(1)$ | Requires shifting subsequent elements. |
| **`ArrayList.add` at End** | $\Theta(1)$ amortized | $\mathcal{O}(1)$ | Occasional resize costs $\mathcal{O}(n)$. |
| **Fixed-Width Window ($k$)** | $\Theta(n)$ | $\mathcal{O}(1)$ | Add incoming, subtract outgoing ($arr[i] - arr[i - k]$). |
| **Two Forward Pointers Window** | $\Theta(n)$ amortized | $\mathcal{O}(1)$ | **Requires non-negative elements ($arr[i] \ge 0$)!** |
| **Prefix Sum Array** | $\Theta(n)$ build, $\Theta(1)$ query | $\Theta(n)$ | Allows range sum queries on arrays with negative numbers. |

#### 4. Stacks, Queues, and Linked Structures
| Structure / Variant | Push / Enqueue | Pop / Dequeue | Peek | Stored Space / Notes |
| :--- | :--- | :--- | :--- | :--- |
| **Fixed Array Stack** | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | Fixed capacity $K$; throws Overflow. |
| **Linked Stack (Head Node)** | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | Node allocations; cannot overflow. |
| **Naive Array Queue** | $\Theta(1)$ | **$\Theta(n)$** | $\Theta(1)$ | Dequeue requires shifting all elements left! |
| **Circular Array Queue** | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | Modular arithmetic `(front + 1) % capacity`. |
| **Linked Queue (Head + Tail)** | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | Clear tail when last node leaves. |
| **Monotonic Stack (Next Greater)**| $\Theta(n)$ amortized | - | - | Each index pushed/popped at most once. |
| **Monotonic Deque (Window Max)** | $\Theta(n)$ amortized | - | - | Auxiliary space: $\mathcal{O}(k)$ deque. |
| **Singly Linked List (Head + Tail)**| Insert First/Last: $\mathcal{O}(1)$ | Remove First: $\mathcal{O}(1)$, **Remove Last: $\mathcal{O}(n)$** | - | Tail removal requires traversal to find predecessor. |
| **Doubly Linked List (Head + Tail)**| Insert/Remove First/Last: $\mathcal{O}(1)$ | Remove Known Node: $\mathcal{O}(1)$ | - | Bidirectional pointers (`prev`, `next`). |

---

### 11.2 Midterm Trap Checklist: 10 Classic Fallacies Exposed

1. **Fallacy:** *"A nested loop is always $\mathcal{O}(n^2)$."*
   - **TRUTH:** If the inner loop index only advances forward (like two forward pointers or monotonic stacks), the total operations over the entire algorithm are bounded by $2n = \mathcal{O}(n)$ amortized!
2. **Fallacy:** *"Deleting a node from a Linked List is always $\mathcal{O}(1)$."*
   - **TRUTH:** Deletion is $\mathcal{O}(1)$ **only if** you already possess a direct pointer to the target node AND its predecessor (or in a DLL). Deleting by value requires an initial $\mathcal{O}(n)$ linear search!
3. **Fallacy:** *"We can perform Binary Search on a Linked List in $\mathcal{O}(\log n)$."*
   - **TRUTH:** No! Linked lists do not support $\mathcal{O}(1)$ random access to the midpoint. Finding the midpoint costs $\mathcal{O}(n)$, making total search time $\mathcal{O}(n)$.
4. **Fallacy:** *"Selection Sort is stable."*
   - **TRUTH:** Selection sort is **unstable** because swapping the minimum into index $i$ leaps across intermediate equal elements, inverting their relative order (e.g., `[4a, 4b, 2]`).
5. **Fallacy:** *"Merge Sort is an in-place sort with $\mathcal{O}(1)$ auxiliary space."*
   - **TRUTH:** Standard Merge Sort requires $\Theta(n)$ auxiliary scratch array memory to merge the two sorted halves!
6. **Fallacy:** *"Recursion is faster than iteration."*
   - **TRUTH:** Recursion is almost always **slower** than iteration due to stack frame allocation, method call overhead, and register saving. It also consumes $\mathcal{O}(\text{depth})$ call-stack memory.
7. **Fallacy:** *"Two-pointer sliding windows work on all subarray sum problems."*
   - **TRUTH:** Two-pointer windows **fail** when numbers can be negative, because the sum ceases to be monotonic. Negative sum problems require Prefix Sums + Hash Map!
8. **Fallacy:** *"In a Singly Linked List with a Tail pointer, `removeLast()` is $\mathcal{O}(1)$."*
   - **TRUTH:** It is $\mathcal{O}(n)$! You cannot step backward from `tail` to update the second-to-last node's `next` pointer to `null`.
9. **Fallacy:** *"Big-O means worst-case."*
   - **TRUTH:** Big-O is an asymptotic upper bound, not an input case. You can have a Big-O of the best-case (e.g., Best-Case of Bubble Sort is $\mathcal{O}(n)$) or a Big-O of the worst case.
10. **Fallacy:** *"The Sentinel node in SLList is returned to the user."*
    - **TRUTH:** The sentinel is a dummy internal anchor. User traversal always starts at `sentinel.next`.

---

### 11.3 Technique Recognition Decision Tree

When reading an exam problem, ask these questions in sequence:

```
+-------------------------------------------------------------------------------+
|                      ALGORITHMIC TECHNIQUE DECISION TREE                      |
+-------------------------------------------------------------------------------+
                                        |
       Is the input an Array or a Linked Structure?
            /                                \
        [ARRAY]                          [LINKED LIST]
           |                                  |
   Is data sorted?                     Need O(1) removal of known nodes?
    /           \                           /                   \
 [YES]          [NO]                     [YES]                  [NO]
   |              |                        |                     |
Use Binary     Need to maintain      Use Doubly Linked     Use Singly Linked
Search O(log n) order?                List (DLL)            List with Sentinel
               /      \
            [YES]     [NO]
              |         |
      Do we need to   Use Linear Scan
      sort it first?  or Hash Map
      - MergeSort: O(n log n), Stable, O(n) space
      - InsertionSort: if nearly sorted, O(n)
      - SelectionSort: if writes are expensive
```

#### Window & Subarray Decision Tree:
- **Contiguous window of fixed size $k$:** $\implies$ Fixed Sliding Window (add incoming, drop outgoing).
- **Contiguous subarray with sum condition, ALL POSITIVE numbers:** $\implies$ Two Forward Pointers Caterpillar ($O(n)$ time, $O(1)$ space).
- **Contiguous subarray with sum condition, NEGATIVE NUMBERS ALLOWED:** $\implies$ Prefix Sums + Hash Map ($O(n)$ time, $O(n)$ space).
- **Find Next Greater / Next Smaller element:** $\implies$ Monotonic Stack ($O(n)$ amortized).
- **Sliding Window Maximum of width $k$:** $\implies$ Monotonic Deque ($O(n)$ amortized, $O(k)$ space).
- **LIFO processing (matching parentheses, backtracking, undo):** $\implies$ Stack.
- **FIFO processing (buffering, task scheduling, BFS):** $\implies$ Circular Queue.

---

### 11.4 Comprehensive Course Glossary for Bioinformaticians

- **Abstract Data Type (ADT):** A mathematical specification of a data structure defining its operations and behaviors independently of physical code implementation.
- **Activation Record (Stack Frame):** A memory block pushed onto the JVM call stack containing a method's parameters, local variables, and return address.
- **Amortized Analysis:** The average runtime of an operation over a worst-case sequence of operations (e.g., dynamic array resizing).
- **Auxiliary Space:** The extra memory allocated by an algorithm during execution, excluding the input data and required output.
- **Binary Search:** An $\mathcal{O}(\log n)$ divide-and-conquer search algorithm that repeatedly halves a sorted search interval.
- **Call Descent:** The phase of recursion where new stack frames are pushed onto the call stack toward the base case.
- **Circular Queue (Ring Buffer):** A fixed-capacity queue that uses modular arithmetic (`%`) to wrap `front` and `rear` pointers, achieving $\mathcal{O}(1)$ operations without memory shifting.
- **Deque (Double-Ended Queue):** A sequence allowing insertion and deletion at both ends in $\mathcal{O}(1)$ time.
- **Divide and Conquer:** An algorithmic paradigm that divides a problem into subproblems, recursively conquers them, and combines their solutions.
- **Dynamic Array (`ArrayList`):** A resizable array that doubles its capacity when full, achieving $\mathcal{O}(1)$ amortized append time.
- **Garbage Collector (GC):** The JVM engine that automatically scans heap memory and reclaims objects that no longer have active references.
- **Heap Memory:** The large shared pool of computer memory where all Java objects and arrays are physically stored.
- **In-Place Algorithm:** An algorithm whose auxiliary space complexity is $\mathcal{O}(1)$ (or $\mathcal{O}(\log n)$ for recursive call stacks).
- **Loop Invariant:** A logical condition that is true before and after each iteration of a loop, used to prove algorithm correctness.
- **Merge Sort:** An asymptotically optimal $\Theta(n \log n)$, stable, divide-and-conquer sorting algorithm.
- **Monotonic Stack / Deque:** A stack or deque whose stored elements are maintained in strictly increasing or decreasing order, solving range-extrema problems in $\mathcal{O}(n)$ amortized time.
- **Primitive Type:** Basic Java data types (`int`, `long`, `double`, `boolean`) stored directly as raw values in memory.
- **Reference (Pointer):** A 64-bit variable that stores the memory address of an object on the heap.
- **Return Ascent (Unwinding):** The phase of recursion where base cases return values upward, popping stack frames.
- **Sentinel Node (Dummy Node):** An artificial node placed at the boundary of a linked list to eliminate null-pointer checks and edge cases.
- **Stability:** The property of a sorting algorithm that preserves the original relative order of items with identical keys.
- **Stack Underflow:** An error caused by attempting to pop or peek an empty stack.
- **Stack Overflow:** An error caused by exceeding stack capacity or exceeding JVM call-stack depth through infinite recursion.
- **Tightly Bound ($\Theta$):** An asymptotic growth rate that acts simultaneously as an upper and lower bound.
