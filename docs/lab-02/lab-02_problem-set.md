# Lab 02 Problem Set

## Problem 1 – Calibration Offset Ordering

### Focus

Three elementary sorts; Easy; Core

### Scenario and Task

A calibration team records signed integer offsets between $-1000$ and $1000$. Produce an ascending list so the offsets can be inspected in numerical order. Repeated offsets are legitimate observations; retain every occurrence. Implement **optimized bubble sort, selection sort, and insertion sort**. For each method display the sorted array, key comparisons, array writes, and swaps or shifts. Use at most 2,000 values and the common counter definitions.

### Example

```
Input: [7, -3, 4, 0, -3, 9]
Sorted: [-3, -3, 0, 4, 7, 9]
```

Print the measured counters in addition to the sorted values.

### Required Experiment and Tests

|**Pattern**|**Values**|
|---|---|
|Unordered|`[7, -3, 4, 0, -3, 9]`|
|Already in required order|`[-3, -3, 0, 4, 7, 9]`|
|Opposite order|`[9, 7, 4, 0, -3, -3]`|
|Nearly in required order|`[-3, -3, 4, 0, 7, 9]`|

Run each method on a fresh copy of every input. Include an empty array, a single value, and an all-equal array. For Problems 1 and 2, sorting is performed on working copies; keep the original for fair comparisons.

### Analysis Questions

1. Describe the sorted region maintained by each method for the requested order.
    
2. Why does a no-swap pass permit early termination? State the optimized bubble sort best and worst time.
    
3. Which method performs $\frac{n(n - 1)}{2}$ key comparisons regardless of input order?
    
4. Compare insertion shifts with selection swaps, using array writes as the common measure.
    
5. State working space, including separately the copies used by the comparison driver. Explain which standard methods preserve equal-key order.
    
6. Why should the elementary sorting experiment stay small even though later search/window problems permit larger arrays?
    

## Problem 2 – Repair Production-Time Order

### Focus

Insertion vs. selection sort; Easy–Medium; Applied choice

### Scenario and Task

A scheduling log normally arrives in chronological order, but delayed entries may be slightly misplaced. Restore ascending order using insertion sort. Here each record is represented only by an integer time code; if payloads are added, move the entire record together. Time codes are nonnegative, may repeat, and $n \le 5,000$.

### Example

```
Input: [810, 820, 850, 830, 840, 860]
Output: [810, 820, 830, 840, 850, 860]
```

### Required Comparison

Compare your ascending insertion sort with your ascending selection sort on `[1, 2, 4, 3, 5, 6, 8, 7, 9, 10]`. Repeat on ascending and descending arrangements of the same values. Use fresh copies and record key comparisons, writes, insertion shifts, and selection swaps.

Test an empty array, one time code, and repeated time codes. Reuse methods rather than paste a different implementation into every test. Include all shared source files.

### Analysis Questions

1. Which positions move when one delayed entry is inserted into the sorted prefix?
    
2. Why does insertion sort benefit from this pattern? Compare best and worst time with selection sort.
    
3. When no shift occurs, does final key placement count as a write under this handout's convention?
    
4. Why does changing the requested order require changing comparisons, not just relabeling output?
    
5. Explain stability for two equal time codes carrying different payloads. What could be lost if only the keys moved?
    

> [!Applied choice]
> Complete **Problem 2** or **Problem 8**. Submit Java source and a report section for your selected problem. Optional extra work does not replace a core problem.

## Problem 3 – First Sufficient Battery

### Focus

Linear search; Easy; Core

### Scenario and Task

A technician inspects batteries in shelf order. Each value is the stored charge in whole units. Given a nonnegative requirement $t$, find the first original index with charge at least $t$. Return -1 when no battery qualifies. Implement a left-to-right conditional linear search and stop at the first qualifying value. Do not sort the shelf order.

### Example

```
Charges: [20, 75, 40, 90, 60]
Requirement: 60
Found: true
First sufficient index: 1
Inspections: 2
```

### Required Tests

Use requirements 20, 60, 90, and 100 on the example. Include exact equality, repeated charges, an empty shelf, a single battery, and requirement zero. Charges are nonnegative. An empty shelf returns `false`, index -1, and zero inspections. Count one evaluated threshold check per inspected value.

### Analysis Questions

1. Why does the first successful threshold check establish the earliest qualifying shelf position?
    
2. Explain why testing equality alone would answer a different question.
    
3. State best/worst time and auxiliary space. Which cases permit immediate return?
    
4. Why is ordinary binary search invalid on the unsorted shelf even though the comparison uses an ordered threshold?
    
5. Would sorting before searching preserve the required original-index answer? Explain the extra record information that would be needed.
    

## Problem 4 – Museum Catalogue Lookup

### Focus

Iterative binary search; Easy–Medium; Core

### Scenario and Task

A catalogue stores distinct integer identifiers in ascending order. Implement iterative binary search using inclusive boundaries. Return the exact matching index or -1; do not return a nearby entry when a match is absent.

For test runs, print or record `low`, `high`, `mid`, and the inspected value at each iteration. Count one midpoint inspection per loop iteration; this is not the same as counting each relational expression in the implementation.

### Example and Worked Trace

```
Identifiers: [205, 230, 260, 310, 355, 390, 440]
Target: 390
Returned index: 5
```

|**low**|**high**|**mid**|**a[mid]**|**Action**|
|---|---|---|---|---|
|0|6|3|310|Set low to 4.|
|4|6|5|390|Return 5.|

### Required Comparison and Tests

Look up the last identifier using both a left-to-right first-match linear search and binary search. Record their indices and inspected-value counts. If your Problem 3 contract differs, write a small first-match helper for this comparison.

Test the first identifier, an absent value between entries, values below/above the range, and an empty or single-entry catalogue. Sorted order is a precondition; do not rescan to validate it within every query.

### Analysis Questions

1. State where a target can still occur during the loop. How does ascending order justify each discarded region?
    
2. Why must both updates move past `mid`? What happens when `low` equals `high`?
    
3. Compare best and worst time, iterative auxiliary space, and worst-case inspections for a million entries.
    
4. If the source data are unordered, what preparation and memory must be included in a complete repeated-query comparison?
    
5. Would appending an out-of-order identifier preserve the search contract? Explain.
    

## Problem 5 – Packet-Type Occurrence Range

### Focus

First/last binary-search boundaries; Medium; Core

### Scenario and Task

A packet report has already grouped type codes by sorting them in nondecreasing order. Find the first index, last index, and total frequency of a requested type. These indices belong to the sorted report, not the original arrival log. Implement two modified binary searches. After finding a match, retain it as a candidate and continue toward the requested boundary. Do not linearly scan a duplicate block or replace the searches with a map.

### Example

```
Sorted values: [2, 2, 2, 5, 7, 7, 9, 9, 9, 9]
Target: 9
First index: 6
Last index: 9
Count: 4
```

### Boundary Cases and Tests

For an absent target report `first = -1`, `last = -1`, `count = 0`. Test one match, duplicates at either end, all-equal data, an absent target, and an empty array. Include a trace of one boundary search. Equal values in the original log may occupy several separated blocks. After sorting, they occupy one group. This changes block layout, even though the total frequency is preserved.

### Analysis Questions

1. Why does one ordinary binary-search match not identify the whole occurrence range?
    
2. Why is searching left/right after saving a match safe?
    
3. Derive $\text{last} - \text{first} + 1$ and explain the guard needed for absence.
    
4. Compare the two searches with a full scan when one value repeats many times.
    
5. For repeated queries, separate sorting cost from per-query cost. What changes if the input is already maintained in sorted order?
    
6. Which information is lost if original order or original record positions are discarded?
    

## Problem 6 – First Checkpoint After a Time

### Focus

Boundary binary search; Medium; Interview choice

### Scenario and Task

A monitoring log stores checkpoint times in ascending order. Given time $t$, return the first index whose time is strictly greater than $t$. A checkpoint at exactly $t$ does not qualify. Return $n$ if no later checkpoint exists and display `NO CHECKPOINT` instead of reading $a[n]$. Do not insert or change any value.

### Examples

Use `[12, 20, 20, 35, 50]`.

|**Target**|**Result**|**Meaning**|
|---|---|---|
|20|3|Skip both entries equal to 20.|
|26|3|35 is the first later checkpoint.|
|5|0|The first checkpoint is already later.|
|60|5|No checkpoint is later; report no result.|

### Suggested Reasoning and Tests

This position is the upper bound. Adapt the lower-bound pattern: values at most $t$ belong on the discarded left side. For an empty array the boundary position is zero and there is no checkpoint to display.

Test exact matches, repeated equal times, gaps, targets outside the range, empty data, and one entry. Trace a case in which the target is absent but a qualifying neighboring time exists.

### Analysis Questions

1. Why does this task need an upper bound rather than a lower bound?
    
2. What must happen when the midpoint equals the target?
    
3. State what is known about positions before `low` and beyond `high` during the search.
    
4. Why is $n$ a meaningful boundary but an invalid index to dereference? Include the empty case.
    
5. State lookup time and working space. Explain why no array shifting is needed.
    

> [!Interview choice]
> Complete **Problem 6** or **Problem 11**. Submit Java source and a report section for your selected problem. Optional extra work does not replace a core problem.

## Problem 7 – Quietest Machine-Service Slot

### Focus

Fixed-size window; Easy–Medium; Core

### Scenario and Task

A workshop records a nonnegative load for each consecutive production slot. Maintenance must occupy exactly $k$ slots. Find the window with the smallest total load; when totals tie, choose the earliest start.

Implement:

- **A:** independently sum each window, and
    
- **B:** compute the first sum once and slide.
    

Return the minimum total as `long` and its zero-based start/end indices. Keep the time order unchanged. Require $1 \le k \le n$.

### Example

```
Loads: [8, 2, 5, 1, 4, 2, 7]
k: 3
Window sums: [15, 8, 10, 7, 13]
Minimum total: 7
Start index: 3
End index: 5
```

### Required Tests and Comparison

Test $k = 1$, $k = n$, all zeros, all equal positive loads, an earliest tie, and a total larger than `int`. On `[2, 1, 2, 1]` with $k = 2$, all sums equal 3, so choose indices 0–1. Reject $k = 0$, $k > n$, and an empty input with no valid window.

Record all window sums for one normal input from both methods. Include the standard comparison table. No wall-clock benchmark is required.

### Analysis Questions

1. How many valid windows exist? Derive the direct work as $(n - k + 1)k$.
    
2. Why can one subtraction and one addition replace the next full summation?
    
3. Why must the initial minimum come from a real window rather than zero?
    
4. Which strict comparison preserves the earliest tied start?
    
5. State time and working/output space for both methods.
    
6. Contrast one fixed window length with Lab 1's sum over subarrays of every length. Why does its contribution formula not directly select this minimum?
    

## Problem 8 – Rolling Queue Delay

### Focus

Moving averages; Medium; Applied choice

### Scenario and Task

A service centre records integer queue-delay readings at equally spaced times. Produce each moving mean and locate the lowest-average period. These are illustrative readings, not a causal performance study. Use one fixed-size sliding window with a `long` running sum. For fixed $k$, compare exact sums to select the best mean and retain the earliest start in a tie. The observations are nonnegative. Require $1 \le k \le n$.

### Example

```
Observations: [12, 6, 9, 3, 6, 12]
k: 3
Moving means: 9.0000, 6.0000, 6.0000, 7.0000
Lowest mean: 6.0000
Start index: 1
End index: 3
```

### Implementation and Tests

Display means to four decimal places, but do not use rounded values for comparisons. Divide by `(double) k`. Either print each mean immediately or return an output array; explain the storage choice.

Test $k = 1$, $k = n$, zero counts, a fractional mean, constant values, a tie, and sums beyond `int`. Reject invalid $k$. For a constant series, report the first complete period, not the last.

### Analysis Questions

1. How many complete means are produced? Which positions are in the first and final windows?
    
2. Why can exact sums be compared instead of rounded means when all windows have the same length?
    
3. What changes between minimizing and maximizing the mean?
    
4. What error results from integer division before conversion?
    
5. State time, working space, and output storage. Explain why sorting would change the meaning of these periods.
    

> [!Applied choice]
> Complete **Problem 2** or **Problem 8**. Submit Java source and a report section for your selected problem. Optional extra work does not replace a core problem.

## Problem 9 – Longest Playlist Within a Time Limit

### Focus

Longest sum-at-most window; Medium; Core

### Scenario and Task

A playback queue lists nonnegative durations in a fixed order. A listener chooses a contiguous segment whose total duration is no more than limit $S$. A zero-duration marker remains part of the sequence. Find the maximum number of consecutive entries. Return length and zero-based start/end indices, preserving the earliest start when lengths tie. Use a variable-size sum window; $S \ge 0$. Do not reorder or skip entries.

### Example

```
Values: [5, 0, 2, 4, 1, 1, 3]
Budget: 8
Maximum length: 5
Start index: 1
End index: 5
Selected total: 8
```

### Suggested Reasoning and Tests

Add the next rightmost value. While the sum exceeds the limit, remove the leftmost value and advance left. Update the best length after validity has been restored. A single value greater than the limit may leave an empty window.

For no nonempty result, return `{0, -1, -1}`. Test an empty array, a full-array answer, every positive value above the limit, zeros with limit zero, tied lengths, and a sum beyond `int`. `[0, 0, 5, 0]` with $S = 0$ returns length 2, indices 0–1. Trace one run requiring several removals.

### Analysis Questions

1. State what the current window means after shrinking.
    
2. Why is the condition $\text{sum} > S$ rather than $\text{sum} \ge S$?
    
3. Why do nonnegative values justify permanently discarding a left boundary?
    
4. Show that each endpoint advances at most $n$ times. State time and auxiliary space.
    
5. Explain the earliest-tie rule and the no-result marker.
    
6. Give a signed-value counterexample for this exact method. Identify the discarded start that later becomes useful.
    

## Problem 10 – Shortest Upload Target

### Focus

Shortest sum-at-least window; Medium; Core

### Scenario and Task

An upload log contains positive data volumes for consecutive completed transfers. Find the shortest consecutive group whose total volume reaches at least target $S$. Transfers cannot be skipped or split. Use a variable-size sliding window and a `long` running sum, with positive values and $S > 0$. Return minimum length and zero-based start/end indices. If several shortest windows exist, retain the earliest start; return `(0, -1, -1)` when no window reaches the target.

### Example

```
Values: [5, 1, 2, 7, 3, 4]
Target: 10
Minimum length: 2
Start index: 3
End index: 4
Selected total: 10
```

### Suggested Reasoning

Expand the right boundary. Whenever the sum reaches the target, record the current candidate before removing a leftmost value. Continue saving and shrinking while the sum is at least the target. Keep a sentinel for no answer yet, and convert it to length zero only at the end.

Unlike Problem 9, shrinking here improves an already valid window. Updating only after an invalid window has been produced loses the candidate.

### Required Tests

Include an empty array, unreachable target, one-entry answer, whole-array-only answer, tied minimum lengths, several removals after one expansion, and totals beyond `int`. Inputs satisfy positivity: parsing invalid data is outside the task. Trace `left`, `right`, `sum`, and the saved result on one nontrivial case.

### Analysis Questions

1. Why must a candidate be recorded before the next removal?
    
2. Why can a single `if` statement be insufficient in place of the shrinking `while` loop?
    
3. Explain how positivity supports the reasoning; do not claim the method handles arbitrary signed data.
    
4. Compare the objective, condition, and update moment with Problem 9.
    
5. State time and auxiliary space and justify forward-only endpoint movement.
    
6. How do you distinguish no result from a length-one answer at index zero?
    

## Problem 11 – Longest Unique Channel Segment

### Focus

Character window with hashing; Medium; Interview choice

### Scenario and Task

A receiver records channel labels as a string, one ASCII character per label. Return the length of the longest contiguous segment containing no repeated label. Comparison is case-sensitive. Use a sliding window with `HashSet<Character>`; do not generate every substring. An empty string returns zero. Reporting the substring itself is optional.

### Examples

|   |   |   |
|---|---|---|
|**Sequence**|**Length**|**One longest segment**|
|`PQRPSTP`|5|`QRPST`|
|`ZZZZ`|1|`Z`|
|`cabdca`|4|`cabd`|
|`aAbBc`|5|`aAbBc`|
|empty string|0|none|

### Required Tests and Optional Map Method

Test empty, one-character, all-equal, all-distinct, mixed-case, and repeated labels after earlier removals. Trace at least one window. Before adding a repeated character, remove leftmost characters until its earlier occurrence is gone.

**Optional:** use a map of most recent indices. Update `left` to $\max(\text{left}, \text{previous} + 1)$. Test `abba`; its answer is 2. An occurrence outside the current window must not move `left` backward.

### Analysis Questions

1. What does the set represent, and why is the window unique after adjustment?
    
2. Why does discarding the entire window at every repeat lose possible answers?
    
3. State expected time and space, including the expected constant-time hashing assumption.
    
4. For a fixed ASCII alphabet, explain why storage is bounded even if the string is long.
    
5. Explain substring versus subsequence using one example. Does sorting preserve this task?
    
6. If you attempted the map method, explain the forward-only left boundary and compare its stored state with the set.
    

> [!Interview choice]
> Complete **Problem 6** or **Problem 11**. Submit Java source and a report section for your selected problem. Optional extra work does not replace a core problem.

## Problem 12 – Archive Record Locator

### Focus

Records; sorting and repeated searching; Medium–Hard; Optional

### Scenario and Task

Build a small lookup tool for unordered records. Each record has a unique integer ID and an associated integer `shelfNumber`. Sort a protective copy of the records once by ID, then answer several exact-ID queries. Use at most 2,000 records for the insertion-sort experiment; discuss larger workloads without running a large quadratic sort.

### Required Program

1. Represent a complete record with its ID and payload, for example an `ArchiveRecord` object.
    
2. Implement ascending insertion sort comparing IDs and moving complete records, not just their ID fields.
    
3. Use binary search on the sorted records. Display the matching ID and payload, or `NOT FOUND`.
    
4. Implement a linear-search baseline on the original records and check that every query returns the same payload or absence.
    

### Example

```
Original (ID, payload): [(708, 12), (502, 3), (901, 8), (604, 6)]
Sorted (ID, payload):   [(502, 3), (604, 6), (708, 12), (901, 8)]

Query 604: 604 6
Query 605: NOT FOUND
```

### Tests and Analysis

Test present and absent IDs, empty and one-record collections, and ascending/reverse input order. Preserve every ID-payload relationship and keep the original records unchanged.

### Analysis Questions

1. Why would sorting only the IDs corrupt lookup results?
    
2. For $n$ records and $q$ queries, compare repeated scans with insertion sort once followed by binary searches. Include preparation and protective-copy memory.
    
3. Why is the insertion-based worst case $O(n^2 + q \log n)$, not automatically $O(n \log n + q \log n)$?
    
4. How does one query differ from many queries on the same fixed data? What becomes invalid if the records change?