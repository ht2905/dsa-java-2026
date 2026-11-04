# Problem Index & Complexity Specifications

This document catalogs the complete problem set, algorithmic techniques, and asymptotic complexity bounds across all laboratory sessions and in-class quizzes for **IT013IU – Algorithms & Data Structures**.

---

## Lab 01: Array Fundamentals & Complexity Analysis

Focuses on invariant verification, prefix/suffix arrays, and single-pass traversals over linear arrays.

| Problem | Title | Primary Technique | Time Complexity | Aux Space |
|---|---|---|---|---|
| **Problem 01** | Equal Value Blocks | Single linear pass; boundary transition detection | $O(n)$ | $O(1)$ |
| **Problem 02** | Suffix Maximum Queries | Precomputed suffix maximum array | $O(n + q)$ | $O(n)$ |
| **Problem 03** | Missing Number | Arithmetic progression sum ($\sum_{i=1}^n i - \text{sum}$) | $O(n)$ | $O(1)$ |
| **Problem 04** | First Unique Position | Frequency counting via frequency map | $O(n)$ | $O(k)$ |
| **Problem 05** | All Subarray Sums | Element contribution weighting ($A[i] \cdot (i + 1)(n - i)$) | $O(n)$ | $O(1)$ |
| **Problem 06** | Interleave Halves | In-place / buffer array splitting and interleaving | $O(n)$ | $O(n)$ |

---

## Lab 02: Sorting, Binary Search & Sliding Window

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

---

## Lab 03: Stacks, Queues, Linked Lists & Monotonic Structures

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

### Session 04 – Singly Linked List (`in-class-assignments/exercise-01_session-04/`)
- Custom `SLList` implementation illustrating recursive and iterative node architectures, sentinel pointer handling, and constant-time head insertion.

### Session 05 – Divide-and-Conquer Merge Sort (`in-class-assignments/exercise-01_session-05/`)
- Classical divide-and-conquer recursive sorting adhering to the CLRS specification with sentinels (`Integer.MAX_VALUE`), achieving optimal $\Theta(n \log n)$ time.

### Lab 05 Quiz Solutions (`in-class-assignments/lab-05-quiz/`)
- **Problem 01 – Build Stairs (`problem-01/Main.java`):** Greedy sequence verification; reduces heights by at most 1 from left to right to enforce non-decreasing ordering in $O(N)$ time and $O(N)$ space.
- **Problem 02 – Balance (`problem-02/Main.java`):** Optimal split point minimizing $|S_1 - S_2|$ across contiguous weight arrays using prefix sum difference evaluation in $O(N)$ time and $O(1)$ auxiliary space.
