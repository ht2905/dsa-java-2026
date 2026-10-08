# DSA Lab 01 Report
- Author: Tran Quoc Hoang – BTBTWE24036
- Course: Algorithms & Data Structures (IT013IU)
- For: Lab 01, Version 04

## Problem 1 – Count Equal-Value Blocks

### Algorithm Explanation
The algorithm processes the array in a single linear pass from left to right:

1. **Base verification**: If the array length $n = 0$, the function immediately returns `0`.
    
2. **Initial state**: For any non-empty array ($n \ge 1$), the first element `arr[0]` unconditionally starts the first contiguous block, initializing `blockCount = 1`.
    
3. **Transition detection**: As the loop traverses index $i$ from $1$ to $n - 1$, it compares `arr[i]` with its immediate predecessor `arr[i - 1]`. Whenever `arr[i] != arr[i - 1]`, the previous maximal contiguous segment is closed and a new block begins, incrementing `blockCount` by 1.
    
4. **Result**: Once the traversal finishes, `blockCount` represents the total number of maximal contiguous blocks.

### Complexity Analysis
- **Time Complexity**: $O(n)$
	- The algorithm iterates through the array of length $n$ exactly once, executing $n - 1$ comparison steps and at most $n - 1$ increment operations. Each comparison and increment takes $O(1)$ time, yielding an overall worst-, average-, and best-case time complexity of $O(n)$ for $n \ge 1$ (and $O(1)$ when $n = 0$).

- **Auxiliary-Space Complexity**: $O(1)$
	- Only two primitive integer variables (`blockCount` and loop index `i`) are allocated. The algorithm operates in-place without copying elements, allocating data structures, or mutating the input array.

### Problem-Specific Analysis Questions
1. **What is the input size $n$? For a nonempty array, how many adjacent pairs need to be checked?**
	- The input size $n$ is `arr.length`, denoting the total number of integer elements stored in the array.
	- For a non-empty array of length $n$, exactly $n - 1$ adjacent pairs `(arr[i - 1], arr[i])` need to be checked (for $i \in [1, n - 1]$). When $n = 1$, zero adjacent pairs are checked.
2. **What are the time and auxiliary-space complexities?**
	- **Time Complexity**: $O(n)$, requiring a single pass over $n$ elements.
	- **Auxiliary-Space Complexity**: $O(1)$, using only scalar control variables.
3. **How should the count be initialized for empty and nonempty arrays?**
	- **Empty array ($n = 0$)**: The count must be initialized to (or return) `0`, as zero elements yield zero blocks.
	- **Nonempty array ($n \ge 1$)**: The count must be initialized to `1`. The initial item `arr[0]` establishes the first block, after which each detected mismatch `arr[i] != arr[i - 1]` marks the boundary of a subsequent block.
4. **Why is counting distinct values not the same as counting blocks? Use the example to explain.**
	- Distinct-value counting measures global set cardinality ($\{x \mid x \in \text{arr}\}$), collapsing identical elements regardless of their index positions. Contiguous block counting requires adjacency; an identical value re-appearing after a different value belongs to an entirely new block.
	- In the example array `[4, 4, -1, -1, -1, 7, 4, 4]`:
		- The distinct values are $\{4, -1, 7\}$, producing a distinct count of **3**.
		- The maximal contiguous blocks are `[4, 4]`, `[-1, -1, -1]`, `[7]`, and `[4, 4]`, producing a block count of **4**.
		- The value `4` appears in two disconnected intervals; a distinct counter counts `4` only once, whereas block counting recognizes both disjoint segments.
5. **What results should be returned for an all-equal array and for an array whose adjacent values are always different?**
	- **All-equal array ($n \ge 1$)**: Returns **1**. Since `arr[i] != arr[i - 1]` evaluates to false for all $i \in [1, n - 1]$, no new block boundaries are triggered.
	- **Array whose adjacent values are always different ($n \ge 1$)**: Returns **$n$**. Because `arr[i] != arr[i - 1]` evaluates to true at every step, each element forms a maximal block of length 1.

### Test Cases and Verification

|**Case Type**|**Input (arr)**|**Expected Output**|**Actual Output**|**Status**|
|---|---|---|---|---|
|**Normal Case**|`[4, 4, -1, -1, -1, 7, 4, 4]`|`4`|`4`|PASS|
|**Boundary Case (Empty)**|`[]`|`0`|`0`|PASS|
|**Boundary Case (Single)**|`[42]`|`1`|`1`|PASS|
|**Boundary Case (All-Equal)**|`[5, 5, 5, 5, 5]`|`1`|`1`|PASS|
|**Tricky Case (Alternating)**|`[1, 2, 1, 2, 1, 2]`|`6`|`6`|PASS|
|**Tricky Case (Extremes)**|`[-1000000, 1000000, -1000000]`|`3`|`3`|PASS|

