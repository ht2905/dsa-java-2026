# DSA Lab 01 Problem Set
## Problem 1 – Count Equal-Value Blocks
**Task.** Count the number of maximal contiguous blocks of equal values in an integer array. A block is a consecutive group that cannot be extended left or right without including a different value. Equal values separated by other values belong to different blocks.

**Example.**
- Input: [4, 4, −1, −1, −1, 7, 4, 4]
- Output: 4

The blocks are [4, 4], [−1, −1, −1], [7], and [4, 4].

**Required approach.** Implement a single traversal that detects where a new block begins. Return zero for an empty array. A one-element array has one block. Do not store copies of the blocks or use sorting.

**Analysis questions.**
1. What is the input size *n*? For a nonempty array, how many adjacent pairs need to be
checked?
2. What are the time and auxiliary-space complexities?
3. How should the count be initialized for empty and nonempty arrays?
4. Why is counting distinct values not the same as counting blocks? Use the example to
explain.
5. What results should be returned for an all-equal array and for an array whose adjacent
values are always different?

## Problem 2 - Suffix Maximum Queries

**Task.** Given an integer array and several query indices p, report the largest value from
index p through index n − 1, inclusive. This part of the array is called a suffix. The array
does not change between queries. Every query satisfies 0 ≤ p < n.

**Example.**
- Array: [3, 8, −2, 6, 1]
- Queries: [0, 2, 4, 1]
- Output: [8, 6, 1, 8]

**Approach A - Direct Suffix Scan.** For each query, inspect all elements from the
requested index through the end of the array and find their maximum.

**Approach B - Suffix-Maximum Pre-processing.** Traverse the array from right to
left to build an additional array. At each position, store the largest value in the suffix
beginning there. Answer each query using its corresponding stored value.

Implement both approaches. Process answers in query order, one at a time; a separate
array of all answers is not required. An empty data array is allowed only when there are
no queries. Do not modify the original array.

**Analysis questions.**

1. Analyze the time for one direct query in terms of both *n* and *p*. What is its auxiliary-
space complexity?
2. Analyze the pre-processing time, time per query, and auxiliary space of Approach B.
3. Let q denote the number of queries. Compare the total costs, including pre-processing,
when queries may start at index zero.
4. Which approach would you choose for one query at *p = n − 1*? How would your choice
change for many queries on the same array?
5. How should the running maximum be initialized to handle all-negative arrays? What
value is stored at the final position?
6. Why does a right-to-left traversal make the required summary available? What could
become invalid if an input value changed after pre-processing?

## Problem 3 - Find the Missing Number
**Task.** An array of length n contains n distinct integers from the inclusive range 0
through n. Exactly one value in that range is missing. Return the missing value. These
input conditions are guaranteed; you do not need to validate them.

**Example.**
- Input: [3, 0, 4, 1]
- Output: 2

The array has length four, so the possible values are 0, 1, 2, 3, 4.

**Approach A - Repeated Membership Search.** Try candidate values from zero
through n, in increasing order. For each candidate, search the original array from the
beginning. Stop that search when a match is found. Return the first candidate that is
absent.

**Approach B - Presence Array.** Allocate a boolean array with one position for each
possible value. Mark the values found in the input, then scan the marks to find the missing
value. Include allocation, initialization, marking, and the final scan in your analysis.

Implement both approaches without sorting or modifying the input. An empty array
must return zero. Do not replace either required approach with an arithmetic formula,
XOR method, or a shortcut based on the order of a particular test input.

**Analysis questions.**
1. Analyze the worst-case time and auxiliary space of repeated membership search. Give
an input that forces a search for every candidate.
2. How does the work change when zero is missing instead of *n*? Explain why input
arrangement and the missing value can affect measurements.
3. Analyze the total time and auxiliary space of the presence-array approach.
4. Why does the presence array need *n + 1* positions? Which input constraint makes
using a value as an index safe?
5. What would need to change if values could be arbitrary negative or very large integers?
Would a directly indexed array still be appropriate?
6. Why are both the distinctness and range guarantees important to the statement that
exactly one value is missing?

## Problem 4 - Find the First Unique Position
**Task.** Return the smallest index whose value occurs exactly once in the entire array.
Return −1 if no such index exists. The result is an index, not the value at that index.

**Example.**
- Input: [6, 2, 6, 4, 2, 9, 4]
- Output: 5

The value 9 occurs once and is located at index five.

**Approach A - Repeated Counting.** Visit candidate positions from left to right. For
each candidate, scan the entire array to count occurrences of its value. Return the first
index whose count is one; return −1 when all candidates have been checked without
success.

**Approach B - Frequency Map and Ordered Scan.** Build a `HashMap<Integer`,
`Integer>` containing the frequency of every value. Then scan the original array from left
to right and return the first index with frequency one.

Implement both approaches. An empty array returns $−1$; a one-element array returns zero. A value seen once so far is not necessarily unique in the complete input.

**Analysis questions.**
1. Analyze the worst-case time and auxiliary space of repeated counting. Give an input
on which every candidate must be examined.
2. Analyze the average total time of the frequency-map approach, including both passes.
Let d be the number of distinct values when describing storage.
3. Why is a HashSet containing only distinct values insufficient for the required frequency
check?
4. Why must the second pass follow the original array order rather than return an
arbitrary value with frequency one?
5. How much work does each required approach perform when every array value is
distinct? Explain any early return.
6. How does returning an index avoid confusing the legitimate data value −1 with the
no-result marker?

## Problem 5 - Sum of All Subarray Sums
**Task.** Calculate the sum of the sums of all nonempty contiguous subarrays. Each
distinct pair of start and end indices defines one subarray. Include every such subarray
exactly once, even when several subarrays contain the same values.

**Example.**
- Input: [2, −1, 3]
- Output: 11

The six subarray sums are 2, −1, 3, 1, 2, and 4. Their total is 11.

**Approach A - Nested-Loop Accumulation.** Fix a starting index, extend the ending
index one position at a time, and maintain the current subarray sum. Add that sum to
the final total after every extension. Repeat for every starting index. Do not use a third
loop to recompute each sum.

**Approach B - Element Contributions.** For each position, determine how many
subarrays include it by counting the possible starting and ending positions. Multiply
its value by this number and add its contribution to the total, without enumerating all
subarrays.

Implement both approaches. **For this problem only,** 0 ≤ n ≤ 10,000; the general value
limits still apply. Use long for subarray sums, contributions, and the result. Promote an
operand to long before multiplication, not only when storing the result. An empty array
returns zero.

**Analysis questions.**
1. How many nonempty subarrays exist? Use this count to analyze the time of Approach
A and state its auxiliary space.
2. For an index $i$, how many starting positions and ending positions can form a subarray
containing $a[i]$? Derive its contribution.
3. Analyze the time and auxiliary space of Approach B. Why can it avoid generating
the subarrays explicitly?
4. Why does this method remain correct when values are negative or zero? What should
a one-element array return?
5. Why may an int calculation overflow even when the final variable is a long? Explain
why the stated size and value limits keep the total within long range.

## Problem 6 - Interleave Two Array Halves

**Task.** An array has even length $n = 2m$. Interleave its two halves while preserving the
order within each half. Both approaches must modify the original array to contain the
result.

**Example.**
- Input: [1, 2, 3, 10, 20, 30]
- Output: [1, 10, 2, 20, 3, 30]

In general, $[x1 , . . . , xm , y1 , . . . , ym ]$ becomes $[x1 , y1 , . . . , xm , ym ]$.

**Approach A - Auxiliary Array.** For $n ≥ 4$, allocate another array and fill it by
alternately taking the next value from each original half. Copy the complete result back
to the input array. Include allocation and both passes in your analysis.

**Approach B - In-Place Ordered Insertions.** For $n ≥ 4$, repeatedly take the next
unplaced value from the second half and insert it immediately after the next unplaced
value from the first half. Save the selected value in a temporary variable and shift the
intervening values one position to the right. Maintain indices as the positions change; do
not allocate another array or collection proportional to $n$.

Implement both approaches. Lengths zero and two are already correctly arranged; return
without allocating an extra array. Odd lengths are outside this problem’s input conditions.
Test each method on a fresh copy of the same input. This is a positional rearrangement,
not sorting.

**Analysis questions.**
1. Analyze the time and auxiliary space of the auxiliary-array approach, including
copying back.
2. Trace the ordered insertions on the example. How many values must be shifted for
the first insertion, the next insertion, and so on? Use $m = n/2$ to derive the total
shifting work.
3. Analyze the time and auxiliary space of the required in-place approach. Explain the
trade-off compared with Approach A.
4. Why must the selected value be saved before shifting? Why does the shift proceed
from right to left?
5. How does your solution preserve the relative order within both halves, including when
values repeat?
6. Does using less auxiliary memory necessarily mean using less time? Base your answer
on these two required algorithms, not a claim about every possible in-place algorithm.