# DSA Lab 02 Report
- Author: Tran Quoc Hoang – BTBTWE24036
- Course: Algorithms & Data Structures (IT013IU)
- For: Lab 01, Version 04

## Problem 1 – Calibration Offset Ordering
### Main Idea & Correctness Argument
- **Optimized Bubble Sort** repeatedly steps through the array, compares adjacent elements, and swaps them if they are out of order (`a[j] > a[j + 1]`). After pass $i$, the $(i+1)$-th largest element is guaranteed to be placed at the end of the active suffix. If a full pass completes with zero swaps, every adjacent pair satisfies `a[j] <= a[j + 1]`, proving the entire array is sorted.
    
- **Selection Sort** partitions the array into a sorted prefix and an unsorted suffix. In step $i$, it scans the suffix `a[i..n-1]` to find the index of the minimum element and swaps it into `a[i]`. By induction, the prefix `a[0..i]` contains the $i+1$ smallest elements in non-decreasing order.
    
- **Insertion Sort** maintains a sorted prefix `a[0..i-1]` and inserts `key = a[i]` into its proper position by shifting larger elements one index to the right. Once a smaller or equal element is met, `key` is written into the vacated slot, expanding the sorted prefix to `a[0..i]`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
    
    - _Optimized Bubble Sort:_ Best case $O(n)$ (already sorted), Average/Worst case $O(n^2)$.
        
    - _Selection Sort:_ Best, Average, and Worst case $O(n^2)$.
        
    - _Insertion Sort:_ Best case $O(n)$ (already sorted), Average/Worst case $O(n^2)$.
        
- **Auxiliary-Space Complexity:** $O(1)$ working memory for all three sorting algorithms (only a few scalar `int` and `boolean` variables). The test driver separately allocates $O(n)$ storage per fresh copy so all three algorithms run on identical original inputs.

### Algorithm Analysis Table
|**Criterion**|**Optimized Bubble Sort**|**Selection Sort**|**Insertion Sort**|
|---|---|---|---|
|**Main idea**|Swap adjacent out-of-order pairs; stop early if a pass makes no swaps.|Repeatedly select the minimum from the unsorted suffix and swap it to the front.|Insert each element into a growing sorted prefix by shifting larger elements right.|
|**Time complexity**|Best: $O(n)$, Worst: $O(n^2)$|Best/Worst: $O(n^2)$|Best: $O(n)$, Worst: $O(n^2)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|$O(1)$|
|**Advantages**|Detects already-sorted arrays in one pass; stable.|Minimizes array writes (at most $n - 1$ swaps / $2(n - 1)$ writes).|Fast on nearly-sorted inputs; stable; fewer writes than bubble sort.|
|**Disadvantages**|High number of swaps and writes on unordered/reverse data.|Always performs $\frac{n(n-1)}{2}$ comparisons; not stable in standard array swap form.|Performs $O(n^2)$ shifts and writes on reverse-ordered data.|
|**Suitable when**|Checking if data is already sorted or teaching basic swap mechanics.|Writing to memory is very expensive compared to reading/comparing.|Input is small ($n \le 2,000$) or already nearly sorted.|

### Which approach would you choose for the stated constraints, and why?
For up to 2,000 calibration offsets, **Insertion Sort** is the best choice. It runs in $O(1)$ working space, preserves stability for duplicate offsets, adapts to nearly-sorted regions in $O(n)$ best-case time, and performs half as many writes per misplaced element as Bubble Sort.

### Analysis Questions
1. **Sorted regions maintained:** After $i$ passes, **Optimized Bubble Sort** maintains the $i$ largest elements in final sorted order at the right end (`a[n - i .. n - 1]`). **Selection Sort** maintains the $i$ smallest elements in final sorted order at the left prefix (`a[0 .. i - 1]`). **Insertion Sort** maintains the first $i$ original elements in relative sorted order at the left prefix (`a[0 .. i - 1]`), though later elements may still be inserted into this prefix.
	
2. **Early termination:** If a complete left-to-right pass makes zero swaps, then `a[j] <= a[j + 1]` holds for every adjacent pair. By transitivity, the entire array is in non-decreasing order. Its best-case time is $O(n)$ ($n - 1$ comparisons, 0 swaps) and worst-case time is $O(n^2)$ ($\frac{n(n - 1)}{2}$ comparisons and swaps on strictly descending data).
	
3. **Fixed comparison count:** **Selection Sort** always performs exactly $\frac{n(n - 1)}{2}$ key comparisons regardless of input order because its inner loop must scan every remaining element in the unsorted suffix to be certain it found the minimum.
	
4. **Insertion shifts vs. Selection swaps:** In Insertion Sort, each shift moves one value one index right (1 array write), plus 1 final write to place `key`, totaling $k + 1$ writes when an element moves past $k$ larger items (up to $\frac{n(n-1)}{2} + (n-1)$ writes in the worst case). In Selection Sort, each pass performs at most 1 swap (2 array writes), so total writes never exceed $2(n - 1)$ ($O(n)$ writes) even in the worst case.
	
5. **Working space and stability:** Each sorting algorithm uses $O(1)$ auxiliary working memory. The comparison driver allocates three separate $O(n)$ working copies (`copyBubble`, `copySelection`, `copyInsertion`) so the original array is preserved. **Optimized Bubble Sort** and **Insertion Sort** preserve equal-key order (stable) because they use strict inequality (`>`) and never move equal elements past one another. Standard **Selection Sort** is **not stable** because a long-range swap can jump an earlier occurrence of a value past an identical value into the unsorted suffix (e.g., in `[4a, 4b, 1]`, `4a` swaps with `1` to yield `[1, 4b, 4a]`).
	
6. **Why elementary sorting stays small:** Elementary sorts take $O(n^2)$ comparisons in the average and worst cases. At $n = 2,000$, $\frac{n(n-1)}{2} \approx 2 \times 10^6$ operations, which finishes in milliseconds. At $n = 10^6$ (permitted in later $O(n)$ sliding window or $O(\log n)$ binary search tasks), $\frac{n(n-1)}{2} \approx 5 \times 10^{11}$ operations, which would take minutes or hours.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array**|**Expected Sorted Output**|**Actual Output & Measured Counters (Comp / Writes / Swaps or Shifts)**|
|---|---|---|---|
|**Normal (Unordered)**|`[7, -3, 4, 0, -3, 9]`|`[-3, -3, 0, 4, 7, 9]`|`[-3, -3, 0, 4, 7, 9]`<br><br>  <br><br>• Bubble: `12 / 14 / 7 swaps`<br><br>  <br><br>• Selection: `15 / 8 / 4 swaps`<br><br>  <br><br>• Insertion: `11 / 11 / 7 shifts`|
|**Normal (Already ordered)**|`[-3, -3, 0, 4, 7, 9]`|`[-3, -3, 0, 4, 7, 9]`|`[-3, -3, 0, 4, 7, 9]`<br><br>  <br><br>• Bubble: `5 / 0 / 0 swaps`<br><br>  <br><br>• Selection: `15 / 0 / 0 swaps`<br><br>  <br><br>• Insertion: `5 / 0 / 0 shifts`|
|**Tricky (Opposite order)**|`[9, 7, 4, 0, -3, -3]`|`[-3, -3, 0, 4, 7, 9]`|`[-3, -3, 0, 4, 7, 9]`<br><br>  <br><br>• Bubble: `15 / 28 / 14 swaps`<br><br>  <br><br>• Selection: `15 / 6 / 3 swaps`<br><br>  <br><br>• Insertion: `15 / 19 / 14 shifts`|
|**Normal (Nearly ordered)**|`[-3, -3, 4, 0, 7, 9]`|`[-3, -3, 0, 4, 7, 9]`|`[-3, -3, 0, 4, 7, 9]`<br><br>  <br><br>• Bubble: `9 / 2 / 1 swap`<br><br>  <br><br>• Selection: `15 / 2 / 1 swap`<br><br>  <br><br>• Insertion: `6 / 2 / 1 shift`|
|**Boundary (Empty array)**|`[]`|`[]`|`[]` (All methods: `0 / 0 / 0`)|
|**Boundary (Single value)**|`[42]`|`[42]`|`[42]` (All methods: `0 / 0 / 0`)|
|**Tricky (All-equal array)**|`[5, 5, 5, 5, 5]`|`[5, 5, 5, 5, 5]`|`[5, 5, 5, 5, 5]`<br><br>  <br><br>• Bubble: `4 / 0 / 0 swaps`<br><br>  <br><br>• Selection: `10 / 0 / 0 swaps`<br><br>  <br><br>• Insertion: `4 / 0 / 0 shifts`|

## Problem 2 – Repair Production-Time Order
### Main Idea & Correctness Argument
- **Insertion Sort** scans the production log from index `1` to `n - 1`. At index `i`, the prefix `a[0..i-1]` is already in chronological order. If entry `a[i]` is on time (`a[i - 1] <= a[i]`), the inner loop stops after 1 comparison. If `a[i]` was delayed (`a[i - 1] > a[i]`), all prefix entries strictly greater than `a[i]` shift one position right until `a[i]` is placed in its chronological spot, restoring the sorted invariant for `a[0..i]`.
    
- **Selection Sort** scans the entire remaining suffix `a[i..n-1]` on every pass `i` to find the minimum time code and swaps it with `a[i]`.

### Time & Auxiliary-Space Complexity
- **Pre-processing Cost:** $O(1)$ (sorting acts directly on the working copy without pre-indexing; making a protective test copy in the driver takes $O(n)$ time and $O(n)$ output/copy storage).
    
- **Insertion Sort:** Best case $O(n)$ time (0 delayed entries), nearly-sorted time $O(n + d)$ where $d$ is the number of inversions, Worst case $O(n^2)$ time (strictly reverse order). Auxiliary space: $O(1)$.
    
- **Selection Sort:** Best, Average, and Worst case $O(n^2)$ time (always $\frac{n(n-1)}{2}$ comparisons). Auxiliary space: $O(1)$.

### Algorithm Analysis Table

|**Criterion**|**Approach A: Insertion Sort**|**Approach B: Selection Sort**|
|---|---|---|
|**Main idea**|Shift larger prefix entries right only when a delayed entry is encountered.|Scan the entire remaining suffix on every pass to locate and swap the minimum time code.|
|**Time complexity**|Best: $O(n)$, Nearly-sorted: $O(n + d)$, Worst: $O(n^2)$|Best / Average / Worst: $O(n^2)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Exploits existing chronological order ($O(n)$ when few entries are delayed); stable for equal time codes.|Performs at most $n - 1$ swaps ($2(n - 1)$ writes) even on completely reversed logs.|
|**Disadvantages**|Performs many writes/shifts if the entire log arrives in reverse order.|Blind to existing order (always $\frac{n(n - 1)}{2}$ comparisons); unstable swaps can scramble payloads of equal timestamps.|
|**Suitable when**|Repairing nearly-sorted logs ($n \le 5,000$) or when equal-timestamp stability matters.|Data is completely random/reversed and write operations are much costlier than comparisons.|

### Which approach would you choose for the stated constraints, and why?

**Insertion Sort** is strongly preferred for this production log ($n \le 5,000$). Because scheduling logs normally arrive in chronological order with only occasional delayed entries, Insertion Sort finishes in near-linear $O(n)$ time (only 11 comparisons on the 10-element nearly-sorted test versus 45 for Selection Sort) and preserves the relative arrival order of records that share the same time code.

### Analysis Questions

- **Which positions move:** When a delayed entry at index `i` with value `key` is inserted into `a[0..i-1]`, only the contiguous block of positions `j` in the sorted prefix whose values are **strictly greater** than `key` (`a[j] > key`) shift one position to the right (moving from `j` to `j + 1`). Entries with `a[j] <= key` are never moved.
    
- **Why insertion sort benefits:** On a mostly ordered log, almost every element satisfies `a[i - 1] <= a[i]`, so the inner `while` loop terminates on its very first comparison without shifting. Insertion Sort's best case is $O(n)$ ($n - 1$ comparisons, 0 writes) and worst case is $O(n^2)$, whereas Selection Sort is $O(n^2)$ ($\frac{n(n-1)}{2}$ comparisons) in both best and worst cases because it always scans the full unsorted suffix.
    
- **Final key placement write when no shift occurs:** Under our implementation and standard write-avoidance convention (`if (j + 1 != i) a[j + 1] = key;`), when no shift occurs (`j + 1 == i`), `key` is already sitting at `a[i]`, so **0 array writes** are performed. (If an implementation unconditionally executes `a[j + 1] = key` at the end of every outer pass without that `if` guard, it would count $n - 1$ redundant self-writes of `a[i]` back into `a[i]`).
    
- **Changing the requested order:** Changing from ascending to descending order requires reversing the relational comparison in the inner loop (from `a[j] > key` to `a[j] < key` in Insertion Sort, and `a[j] < a[minIdx]` to `a[j] > a[maxIdx]` in Selection Sort) because the comparison dictates which elements move and how the sorted invariant is built. Simply relabeling output without changing comparisons leaves the array in ascending order.
    
- **Stability with payloads:** Stability guarantees that if two records have equal time codes (e.g., `(820, "JobA")` followed by `(820, "JobB")`), their original relative order is preserved in the sorted output. Insertion Sort is stable because it stops shifting as soon as `a[j] <= key`. If only the integer time-code keys were moved while leaving payload fields behind at their old indices, every shifted timestamp would become associated with the wrong job payload, corrupting the records.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array**|**Expected Sorted Output**|**Actual Output & Measured Counters (Comp / Writes / Shifts or Swaps)**|
|---|---|---|---|
|**Example (Delayed Log)**|`[810, 820, 850, 830, 840, 860]`|`[810, 820, 830, 840, 850, 860]`|`[810, 820, 830, 840, 850, 860]`<br><br>  <br><br>• Insertion: `7 / 4 / 2 shifts`<br><br>  <br><br>• Selection: `15 / 4 / 2 swaps`|
|**Normal (Nearly Sorted)**|`[1, 2, 4, 3, 5, 6, 8, 7, 9, 10]`|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`<br><br>  <br><br>• Insertion: `11 / 4 / 2 shifts`<br><br>  <br><br>• Selection: `45 / 4 / 2 swaps`|
|**Normal (Ascending)**|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`<br><br>  <br><br>• Insertion: `9 / 0 / 0 shifts`<br><br>  <br><br>• Selection: `45 / 0 / 0 swaps`|
|**Tricky (Descending)**|`[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]`|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`|`[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`<br><br>  <br><br>• Insertion: `45 / 54 / 45 shifts`<br><br>  <br><br>• Selection: `45 / 10 / 5 swaps`|
|**Boundary (Empty Log)**|`[]`|`[]`|`[]` (Both: `0 / 0 / 0`)|
|**Boundary (Single Code)**|`[900]`|`[900]`|`[900]` (Both: `0 / 0 / 0`)|
|**Tricky (Repeated Codes)**|`[820, 810, 820, 810, 830, 820]`|`[810, 810, 820, 820, 820, 830]`|`[810, 810, 820, 820, 820, 830]`<br><br>  <br><br>• Insertion: `8 / 7 / 4 shifts`<br><br>  <br><br>• Selection: `15 / 6 / 3 swaps`|

## Problem 3 – First Sufficient Battery
### Main Idea & Correctness Argument
The algorithm scans the battery array `charges` sequentially from index `0` to `n - 1` without reordering the shelf. At each index `i`, it increments the inspection counter and checks the condition `charges[i] >= requirement`. Because indices are visited in strictly increasing order (`0, 1, ..., i`), the first index `i` that satisfies `charges[i] >= requirement` is guaranteed that all preceding positions `0 .. i - 1` failed the threshold check (`charges[j] < requirement`). Thus, returning `i` immediately upon the first match yields the earliest qualifying position. If the loop completes without finding a qualifying battery, no element meets the threshold and the method returns `-1`.

### Time & Auxiliary-Space Complexity
- **Pre-processing Cost:** $O(1)$ (searches directly on the original array).
	
	- **Time Complexity:** Best case $O(1)$ (when the array is empty or the very first battery `charges[0] >= requirement`); Worst and Average case $O(n)$ (when the first qualifying battery is near the end or no battery qualifies, requiring all $n$ elements to be inspected).
	
	- **Auxiliary-Space Complexity:** $O(1)$ working memory (only loop index `i` and `long inspections` counter).

### Analysis Questions
1. **Why the first successful check establishes the earliest position:** Because the loop iterates strictly left-to-right starting at index `0` (`i = 0, 1, 2, ...`), reaching index `i` means indices `0` through `i - 1` were already tested and found strictly less than $t$. Therefore, no earlier qualifying index can exist.

2. **Why testing equality alone answers a different question:** Testing `charges[i] == t` only finds a battery with _exact_ charge $t$ and ignores batteries with more than enough charge (`charges[i] > t`). For example, on `[20, 75, 40, 90, 60]` with $t = 60$, `charges[1] = 75` is sufficient to power the device, whereas an equality check would skip `75` and `90` to return index `4` (or return `-1` for $t = 50$ even though four batteries have at least 50 units).
	
3. **Best/worst time, auxiliary space, and immediate return cases:** Best-case time is $O(1)$, worst-case time is $O(n)$, and auxiliary space is $O(1)$. Immediate return occurs in two cases: an **empty shelf** (`n == 0`, returning `-1` with `0` inspections immediately) and when the **first shelf position qualifies** (`charges[0] >= t`, returning index `0` after `1` inspection, including whenever $t = 0$ on a non-empty shelf of nonnegative charges).
	
4. **Why ordinary binary search is invalid on the unsorted shelf:** Binary search requires a monotonic (sorted) invariant so that inspecting `a[mid]` allows discarding half of the search range. On an unsorted shelf like `[20, 75, 40, 90, 60]` with $t = 60$, inspecting `mid = 2` (`40 < 60`) tells us nothing about whether a battery $\ge 60$ exists to the left (such as `75` at index `1`) or to the right.
	
5. **Sorting before searching and required extra record information:** Sorting the raw integer array before searching would destroy the original shelf positions, so returning an index from the sorted array would not match the physical shelf index. Even if we paired each battery with its original index as a record `(charge, originalIndex)` ($O(n)$ extra space and $O(n \log n)$ or $O(n^2)$ sorting time), sorting by `charge` orders batteries by capacity rather than shelf position—so among all batteries with `charge >= t`, we would still have to scan for the minimum `originalIndex`, making sorting counterproductive compared to a single $O(n)$ pass.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array & Requirement (t)**|**Expected Output (found, index, inspections)**|**Actual Output (found, index, inspections)**|
|---|---|---|---|
|**Normal (Example $t = 60$)**|`[20, 75, 40, 90, 60]`, $t = 60$|`true`, index `1`, `2` inspections|`true`, index `1`, `2` inspections|
|**Boundary (Example $t = 20$, First / Exact)**|`[20, 75, 40, 90, 60]`, $t = 20$|`true`, index `0`, `1` inspection|`true`, index `0`, `1` inspection|
|**Normal (Example $t = 90$, Exact Deeper)**|`[20, 75, 40, 90, 60]`, $t = 90$|`true`, index `3`, `4` inspections|`true`, index `3`, `4` inspections|
|**Tricky (Example $t = 100$, Absent)**|`[20, 75, 40, 90, 60]`, $t = 100$|`false`, index `-1`, `5` inspections|`false`, index `-1`, `5` inspections|
|**Boundary (Requirement Zero)**|`[20, 75, 40, 90, 60]`, $t = 0$|`true`, index `0`, `1` inspection|`true`, index `0`, `1` inspection|
|**Boundary (Empty Shelf)**|`[]`, $t = 50$|`false`, index `-1`, `0` inspections|`false`, index `-1`, `0` inspections|
|**Boundary (Single Battery - Qualifies)**|`[45]`, $t = 45$|`true`, index `0`, `1` inspection|`true`, index `0`, `1` inspection|
|**Boundary (Single Battery - Fails)**|`[30]`, $t = 45$|`false`, index `-1`, `1` inspection|`false`, index `-1`, `1` inspection|
|**Tricky (Repeated Qualifying Charges)**|`[15, 50, 80, 80, 80, 95]`, $t = 80$|`true`, index `2`, `3` inspections|`true`, index `2`, `3` inspections|

## Problem 4 – Museum Catalogue Lookup
### Main Idea & Correctness Argument
- **Iterative Binary Search** maintains an inclusive candidate index range `[low, high]`, initialized to `[0, n - 1]`. Loop invariant: if `target` exists in the strictly ascending array `a`, its index must lie within `[low, high]`. On each iteration, the midpoint `mid = low + (high - low) / 2` is inspected:
    
    - If `a[mid] == target`, the exact match is found and `mid` is returned.
        
    - If `a[mid] < target`, because the array is in ascending order, every element at or before `mid` satisfies `a[0..mid] <= a[mid] < target`, so the left half is safely discarded by setting `low = mid + 1`.
        
    - If `a[mid] > target`, every element at or after `mid` satisfies `a[mid..n-1] >= a[mid] > target`, so the right half is safely discarded by setting `high = mid - 1`.
        
- Because the active range `[low, high]` strictly shrinks on every non-matching step, the loop must terminate—either returning `mid` or exhausting the range (`low > high`) and returning `-1`.

### Time & Auxiliary-Space Complexity

- **Pre-processing Cost:** $O(1)$ per query when the catalogue is already maintained in ascending order.
    
- **Time Complexity:**
    
    - _Iterative Binary Search:_ Best case $O(1)$ (when `a[mid] == target` on the first check), Worst and Average case $O(\log n)$ (at most $\lfloor \log_2 n \rfloor + 1$ midpoint inspections).
        
    - _First-Match Linear Search Baseline:_ Best case $O(1)$, Worst case $O(n)$ ($n$ inspections when looking up the last identifier or an absent value).
        
- **Auxiliary-Space Complexity:** $O(1)$ working memory for both iterative binary search and linear search (only scalar index variables `low`, `high`, `mid`, and `inspections`).

### Algorithm Analysis Table

|**Criterion**|**Approach A: First-Match Linear Search**|**Approach B: Iterative Binary Search**|
|---|---|---|
|**Main idea**|Scan left-to-right from index `0` until `a[i] == target` or the array ends.|Repeatedly halve the inclusive range `[low, high]` by comparing `target` with `a[mid]`.|
|**Time complexity**|Best: $O(1)$, Worst: $O(n)$|Best: $O(1)$, Worst: $O(\log n)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Works on any array without requiring sorted order.|Logarithmic scaling ($O(\log n)$); at most 20 inspections for $1,000,000$ items.|
|**Disadvantages**|Slow on large catalogues when target is near the end or absent ($n$ inspections).|Requires the input array to be sorted in advance as a strict precondition.|
|**Suitable when**|Data is unsorted and only a single one-off search is needed.|Data is already sorted (like the museum catalogue) or queried repeatedly.|

### Which approach would you choose for the stated constraints, and why?
**Iterative Binary Search (Approach B)** is the clear choice because the museum catalogue is already maintained in ascending order. It reduces worst-case inspections from $n$ down to $\lfloor \log_2 n \rfloor + 1$ while still using $O(1)$ auxiliary memory. On the 7-item example, looking up the last identifier (`440`) takes only `3` inspections with Binary Search versus `7` with Linear Search.

### Analysis Questions
- **Where the target can occur & justification of discarded regions:** At the start of each loop iteration, if `target` is present in the array, it can only occur within the inclusive index range `[low, high]`. Because the array is sorted in ascending order (`a[0] < a[1] < ... < a[n-1]`), if `a[mid] < target`, transitivity guarantees `a[i] <= a[mid] < target` for all `i <= mid`, justifying discarding `[low .. mid]`. Symmetrically, if `a[mid] > target`, `a[i] >= a[mid] > target` for all `i >= mid`, justifying discarding `[mid .. high]`.
    
- **Why both updates must move past `mid` & when `low == high`:** Because `a[mid]` has already been inspected and found unequal to `target`, `mid` cannot be the answer. Moving to `mid + 1` or `mid - 1` guarantees that the search range `[low, high]` strictly decreases in size every iteration. When `low == high`, the search space has narrowed to a single remaining candidate at `mid = low = high`: if `a[mid] == target`, it returns `mid`; otherwise, either `low` becomes `mid + 1` or `high` becomes `mid - 1`, causing `low > high` and cleanly terminating the loop (whereas setting `low = mid` or `high = mid` when `low == high` would cause an infinite loop).
    
- **Best/worst time, auxiliary space, and worst-case inspections for $10^6$ entries:**
    
    - _Binary Search:_ Best time $O(1)$ (1 inspection), Worst time $O(\log n)$, Iterative auxiliary space $O(1)$. For $n = 1,000,000$, worst-case inspections = $\lfloor \log_2(1,000,000) \rfloor + 1 = 19 + 1 = 20$ midpoint inspections.
        
    - _Linear Search:_ Best time $O(1)$ (1 inspection), Worst time $O(n)$, Auxiliary space $O(1)$. For $n = 1,000,000$, worst-case inspections = $1,000,000$ inspections.
        
- **Unordered source data preparation and memory:** If the source data are unordered and we must preserve the original array, we must allocate an $O(n)$ protective copy and sort it once before running binary searches. Complete cost for $q$ queries: $O(n)$ protective-copy space plus $O(n^2 + q \log n)$ time using an elementary sort (like insertion sort on small $n$) or $O(n \log n + q \log n)$ time using an $O(n \log n)$ sort, compared to $O(q \cdot n)$ time and $O(1)$ space for repeated linear scans.
    
- **Appending an out-of-order identifier:** Appending an out-of-order identifier (for example, appending `250` to the end of `[205, 230, 260, 310, 355, 390, 440]`) **violates the sorted precondition** and breaks the search contract. When searching for `250`, binary search checks `mid = 3` (`310 > 250`) and discards the entire right half `[3..7]`—which is where `250` was appended—falsely returning `-1`.

### Test Cases & Recorded Outputs
|**Test Type & Name**|**Input Catalogue & Target**|**Expected Output (index, inspections)**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Worked Example)**|`[205, 230, 260, 310, 355, 390, 440]`, target `390`|Index: `5`, Inspections: `2`|Index: `5`, Inspections: `2`<br><br>  <br><br>Trace: `(0,6,mid=3,310)->low=4`; `(4,6,mid=5,390)->Return 5`|
|**Required Comparison (Last ID `440`)**|`[205, 230, 260, 310, 355, 390, 440]`, target `440`|• Linear: Index `6`, `7` inspections<br><br>  <br><br>• Binary: Index `6`, `3` inspections|• Linear: Index `6`, `7` inspections<br><br>  <br><br>• Binary: Index `6`, `3` inspections (`mid=3->5->6`)|
|**Boundary (First ID `205`)**|`[205, 230, 260, 310, 355, 390, 440]`, target `205`|Index: `0`, Inspections: `3`|Index: `0`, Inspections: `3` (`mid=3->1->0`)|
|**Tricky (Absent Between Entries `300`)**|`[205, 230, 260, 310, 355, 390, 440]`, target `300`|Index: `-1`, Inspections: `3`|Index: `-1`, Inspections: `3` (`mid=3->1->2, low=3>high=2`)|
|**Boundary (Below Range `100`)**|`[205, 230, 260, 310, 355, 390, 440]`, target `100`|Index: `-1`, Inspections: `3`|Index: `-1`, Inspections: `3` (`mid=3->1->0, low=0>high=-1`)|
|**Boundary (Above Range `500`)**|`[205, 230, 260, 310, 355, 390, 440]`, target `500`|Index: `-1`, Inspections: `3`|Index: `-1`, Inspections: `3` (`mid=3->5->6, low=7>high=6`)|
|**Boundary (Empty Catalogue)**|`[]`, target `310`|Index: `-1`, Inspections: `0`|Index: `-1`, Inspections: `0` (`low=0 > high=-1` immediately)|
|**Boundary (Single Entry - Present)**|`[310]`, target `310`|Index: `0`, Inspections: `1`|Index: `0`, Inspections: `1` (`mid=0, 310==310 -> Return 0`)|
|**Boundary (Single Entry - Absent)**|`[310]`, target `999`|Index: `-1`, Inspections: `1`|Index: `-1`, Inspections: `1` (`mid=0, 310<999 -> low=1>high=0`)|

## Problem 5 – Packet-Type Occurrence Range
### Main Idea & Correctness Argument
In a sorted packet report where type codes are arranged in non-decreasing order, all duplicate occurrences of any type code form a single contiguous index block $[first, last]$. An ordinary binary search terminates upon encountering an arbitrary match within this block, failing to identify where the group begins and ends.

To find the complete range without scanning duplicate blocks linearly or using a hash map:
1. **First Occurrence Binary Search (`findFirstOccurrence`):** Maintains the candidate search range $[low, high]$. When $a[mid] == target$, $mid$ is recorded into `candidate` as the earliest known match, and the search immediately contracts the active interval to the left by setting $high = mid - 1$. If $a[mid] < target$, $low = mid + 1$; if $a[mid] > target$, $high = mid - 1$.
   - *Invariant:* If any occurrence of $target$ exists at or before $mid$, setting $high = mid - 1$ guarantees that any strictly earlier occurrence will be inspected in subsequent iterations, while `candidate` preserves the best match found so far.
2. **Last Occurrence Binary Search (`findLastOccurrence`):** Symmetrically, when $a[mid] == target$, $mid$ is recorded into `candidate`, and the search continues strictly to the right by setting $low = mid + 1$.
3. **Range and Count Computation:** If $first == -1$, the target is absent, so the algorithm immediately reports `first = -1`, `last = -1`, `count = 0`. Otherwise, the last boundary is queried, and the total count is calculated in $O(1)$ time as $last - first + 1$.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - _First Occurrence Search:_ $O(\log n)$ (at most $\lfloor \log_2 n \rfloor + 1$ midpoint inspections).
  - _Last Occurrence Search:_ $O(\log n)$ (at most $\lfloor \log_2 n \rfloor + 1$ midpoint inspections).
  - _Total Per-Query Time:_ $O(\log n) + O(\log n) = O(\log n)$ in the worst and average cases. (Best case is $O(\log n)$ as boundary refinement continues until the interval $[low, high]$ is exhausted).
  - _Comparison to Linear Expansion:_ A hybrid search (binary search followed by linear scanning left and right) takes $O(\log n + k)$ time, which degrades to $O(n)$ when a value repeats $k = O(n)$ times.
- **Auxiliary-Space Complexity:** $O(1)$ working memory (only scalar index variables `low`, `high`, `mid`, `candidate`). The returned `RangeResult` object is $O(1)$ output storage.

### Algorithm Analysis Table

|**Criterion**|**Approach A: Linear Scan / Hybrid Expansion**|**Approach B: Two Modified Binary Searches**|
|---|---|---|
|**Main idea**|Locate one match via binary search (or scan from 0), then step linearly left and right through duplicates.|Run two independent boundary binary searches: one shifting left on match, the other shifting right.|
|**Time complexity**|Best: $O(1)$, Worst: $O(n)$ (when target repeats $O(n)$ times)|Best: $O(\log n)$, Worst: $O(\log n)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Conceptually simple to implement once an initial match is found.|Strict $O(\log n)$ guarantee regardless of duplicate block width $k$.|
|**Disadvantages**|Degrades to $O(n)$ linear time when an element repeats extensively (e.g. all-equal data).|Requires two separate binary search passes with different boundary-update logic.|
|**Suitable when**|Duplicate blocks are guaranteed to be very small ($k \le 2$).|Duplicate blocks can be large or repeated queries require consistent logarithmic latency.|

### Which approach would you choose for the stated constraints, and why?
**Two Modified Binary Searches (Approach B)** is the optimal choice. For large packet reports up to $10^6$ entries where a type code may repeat thousands of times, Approach B guarantees boundary detection within at most $\approx 40$ total midpoint inspections ($2 \times \lceil \log_2 10^6 \rceil$), whereas linear expansion would require up to $1,000,000$ operations.

### Analysis Questions
1. **Why one ordinary binary-search match does not identify the whole occurrence range:** Ordinary binary search stops as soon as $a[mid] == target$. Because duplicate values form a contiguous block, this first-found match could be located at the first index, the last index, or anywhere in the interior of the duplicate range. A single index does not reveal where the block starts or terminates.
2. **Why searching left/right after saving a match is safe:** When $a[mid] == target$, `candidate` retains $mid$. For the first occurrence, all indices $\ge mid$ can never be an *earlier* occurrence than $mid$, so discarding $[mid, high]$ by setting $high = mid - 1$ is safe. If an earlier occurrence exists, it will be found in $[low, mid - 1]$; if not, the saved `candidate` remains the true first occurrence. Symmetrically, setting $low = mid + 1$ for the last occurrence safely explores only potential later occurrences while preserving the current match.
3. **Derivation of $last - first + 1$ and absence guard:** In a 0-indexed array, an inclusive range $[first, last]$ spans $(last - first + 1)$ contiguous elements. If $target$ is absent, both boundary searches return $-1$. Without a guard, computing $(-1) - (-1) + 1 = 1$ would falsely report a frequency of 1. A guard `if (first == -1) return new RangeResult(-1, -1, 0);` ensures that absent elements report $count = 0$.
4. **Comparison of the two searches with a full scan on repeated values:** If a single packet type repeats $k$ times (with $k = n$ in an all-equal array of $1,000,000$ elements):
   - Two modified binary searches inspect at most $2 \times (\lfloor \log_2 10^6 \rfloor + 1) = 40$ midpoints ($O(\log n)$).
   - A full linear scan or hybrid linear expansion inspects all $1,000,000$ elements ($O(n)$), which is 25,000 times more operations.
5. **Separating sorting cost from per-query cost:**
   - If the raw packet data arrives unsorted: Sorting the array once takes $O(n \log n)$ time (or $O(n^2)$ for elementary sort) and $O(n)$ copy space. Subsequent $q$ range queries take $O(q \log n)$ time, giving a total cost of $O(n \log n + q \log n)$.
   - If the packet report is already maintained in sorted order (as specified in this task): Pre-processing cost is $0$ ($O(1)$ time and space), and each query immediately runs in $O(\log n)$ time and $O(1)$ working space.
6. **Information lost when original order is discarded:** Sorting groups identical type codes together by key, but completely discards their temporal arrival sequence and physical order in the arrival stream. Any chronological relationships or relative interleaving with other packet types are lost unless each packet is paired with an original arrival timestamp or sequence ID.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array & Target**|**Expected Output (first, last, count)**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Example Target 9)**|`[2, 2, 2, 5, 7, 7, 9, 9, 9, 9]`, target `9`|`first = 6, last = 9, count = 4`|`first = 6, last = 9, count = 4`<br><br>First trace: `mid=4(7)->low=5`; `mid=7(9)->cand=7,high=6`; `mid=5(7)->low=6`; `mid=6(9)->cand=6,high=5`.<br><br>Last trace: `mid=4(7)->low=5`; `mid=7(9)->cand=7,low=8`; `mid=8(9)->cand=8,low=9`; `mid=9(9)->cand=9,low=10`.|
|**Boundary (Duplicates at Left End)**|`[2, 2, 2, 5, 7, 7, 9, 9, 9, 9]`, target `2`|`first = 0, last = 2, count = 3`|`first = 0, last = 2, count = 3`|
|**Normal (Single Match Middle)**|`[2, 2, 2, 5, 7, 7, 9, 9, 9, 9]`, target `5`|`first = 3, last = 3, count = 1`|`first = 3, last = 3, count = 1`|
|**Tricky (All-Equal Data)**|`[4, 4, 4, 4, 4]`, target `4`|`first = 0, last = 4, count = 5`|`first = 0, last = 4, count = 5`|
|**Tricky (Absent Between Values)**|`[2, 2, 2, 5, 7, 7, 9, 9, 9, 9]`, target `6`|`first = -1, last = -1, count = 0`|`first = -1, last = -1, count = 0`|
|**Boundary (Absent Above Range)**|`[2, 2, 2, 5, 7, 7, 9, 9, 9, 9]`, target `15`|`first = -1, last = -1, count = 0`|`first = -1, last = -1, count = 0`|
|**Boundary (Empty Array)**|`[]`, target `9`|`first = -1, last = -1, count = 0`|`first = -1, last = -1, count = 0`|

## Problem 6 – First Checkpoint After a Time
### Main Idea & Correctness Argument
A monitoring log stores checkpoint times in ascending order. Given a target time $t$, we must find the smallest index whose time is **strictly greater** than $t$ ($a[i] > t$). A checkpoint at exactly $t$ does not qualify. If no checkpoint is later than $t$, the method must return $n$ (where the driver displays `NO CHECKPOINT` without dereferencing $a[n]$).

This problem implements an **Upper-Bound Binary Search**:
1. Maintain search range $[low, high]$ initialized to $[0, n - 1]$, and initialize `candidate = n`.
2. In each iteration, evaluate midpoint $mid = low + (high - low) / 2$:
   - If $a[mid] > t$: Checkpoint $a[mid]$ is strictly later than $t$, so it qualifies. Save `candidate = mid`. Because we seek the *earliest* qualifying checkpoint, any earlier qualifying checkpoint must be to the left, so set $high = mid - 1$.
   - If $a[mid] \le t$: Checkpoint $a[mid]$ does not qualify. Because the array is sorted, every element at or before $mid$ satisfies $a[0..mid] \le a[mid] \le t$, so none can qualify. Safely discard the left half by setting $low = mid + 1$.
3. When $low > high$, the loop terminates. `candidate` contains the smallest index with $a[candidate] > t$, or $n$ if all elements are $\le t$.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - _Upper-Bound Binary Search:_ Best, Worst, and Average case $O(\log n)$ ($\lfloor \log_2 n \rfloor + 1$ iterations as the search narrows to the exact boundary).
  - _Linear Scan Baseline:_ Best case $O(1)$ (when $a[0] > t$), Worst case $O(n)$ ($n$ inspections when target is $\ge a[n - 1]$).
- **Auxiliary-Space Complexity:** $O(1)$ working memory (scalar variables `low`, `high`, `mid`, `candidate`). No array shifting or auxiliary copies are made.

### Algorithm Analysis Table

|**Criterion**|**Approach A: Linear Scan for $a[i] > t$**|**Approach B: Upper-Bound Binary Search**|
|---|---|---|
|**Main idea**|Scan from index `0` sequentially until finding the first entry with $a[i] > t$.|Halve $[low, high]$; if $a[mid] > t$, record candidate and move left; if $\le t$, discard left.|
|**Time complexity**|Best: $O(1)$, Worst: $O(n)$|Best / Worst / Average: $O(\log n)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Simple logic; does not assume random-access indexing.|Logarithmic scaling; at most 20 midpoint checks for $1,000,000$ checkpoints.|
|**Disadvantages**|Slow on large logs when $t$ is near the end or above all checkpoints.|Requires array to be pre-sorted in non-decreasing order.|
|**Suitable when**|Small datasets or streaming sequential data where indexing is unsupported.|Large, sorted checkpoint logs with frequent time-boundary queries.|

### Which approach would you choose for the stated constraints, and why?
**Upper-Bound Binary Search (Approach B)** is chosen because checkpoint logs are already maintained in ascending order. For logs with up to $10^6$ entries, it delivers the qualifying checkpoint index in at most 20 checks while requiring only $O(1)$ auxiliary memory.

### Analysis Questions
1. **Why this task needs an upper bound rather than a lower bound:** A lower bound returns the first position where $a[i] \ge t$. If $t$ is present in the log, a lower bound returns the index of $t$. However, this problem explicitly specifies that checkpoints at exactly $t$ do not qualify, requiring the first checkpoint strictly greater than $t$ ($a[i] > t$). This is the exact definition of an upper bound.
2. **What must happen when the midpoint equals the target:** When $a[mid] == t$, $a[mid]$ does not qualify. Furthermore, by sorted order, all values at $j \le mid$ satisfy $a[j] \le a[mid] = t$, meaning no element in the left half can qualify either. The algorithm must discard the left half and continue searching strictly to the right by executing $low = mid + 1$.
3. **What is known about positions before `low` and beyond `high` during the search:**
   - All indices $j < low$ have been verified to satisfy $a[j] \le t$ (they cannot qualify).
   - All indices $j > high$ that were tested and satisfied $a[j] > t$ have their leftmost occurrence captured in `candidate`, where $a[candidate] > t$.
   - Any remaining candidates must lie strictly within $[low, high]$.
4. **Why $n$ is a meaningful boundary but an invalid index to dereference:** An array of size $n$ has valid indices $0, 1, \dots, n - 1$. The value $n$ represents the insertion boundary indicating that all elements in the array are $\le t$ (no checkpoint qualifies). Dereferencing $a[n]$ would throw an `ArrayIndexOutOfBoundsException`. For an empty array ($n = 0$), `candidate` initializes to $0 = n$ and the loop terminates immediately ($low = 0 > high = -1$), correctly returning $0$ and signaling `NO CHECKPOINT` without memory faults.
5. **Lookup time, working space, and why no array shifting is needed:** Lookup time is $O(\log n)$ and working space is $O(1)$. No array shifting is needed because this operation is a read-only query to find a time boundary; no values are inserted, removed, or relocated in the log.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Checkpoints & Target Time ($t$)**|**Expected Returned Index & Value**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Tricky (Duplicate Match $t = 20$)**|`[12, 20, 20, 35, 50]`, $t = 20$|Index: `3`, Value: `35`|Index: `3`, Value: `35` (Skips both 20s)|
|**Normal (Absent Target $t = 26$, Traced)**|`[12, 20, 20, 35, 50]`, $t = 26$|Index: `3`, Value: `35`|Index: `3`, Value: `35`<br><br>Trace: `mid=2(20)<=26->low=3`; `mid=3(35)>26->cand=3,high=2`. Loop ends.|
|**Boundary (Target Below All $t = 5$)**|`[12, 20, 20, 35, 50]`, $t = 5$|Index: `0`, Value: `12`|Index: `0`, Value: `12`|
|**Boundary (Target Above All $t = 60$)**|`[12, 20, 20, 35, 50]`, $t = 60$|Index: `5`, Value: `NO CHECKPOINT`|Index: `5`, Value: `NO CHECKPOINT`|
|**Boundary (Exact Match Last $t = 50$)**|`[12, 20, 20, 35, 50]`, $t = 50$|Index: `5`, Value: `NO CHECKPOINT`|Index: `5`, Value: `NO CHECKPOINT`|
|**Boundary (Empty Log $t = 10$)**|`[]`, $t = 10$|Index: `0`, Value: `NO CHECKPOINT`|Index: `0`, Value: `NO CHECKPOINT`|
|**Boundary (Single Entry Later $t = 20$)**|`[30]`, $t = 20$|Index: `0`, Value: `30`|Index: `0`, Value: `30`|
|**Boundary (Single Entry Equal $t = 30$)**|`[30]`, $t = 30$|Index: `1`, Value: `NO CHECKPOINT`|Index: `1`, Value: `NO CHECKPOINT`|



## Problem 7 – Quietest Machine-Service Slot
### Main Idea & Correctness Argument
A workshop records a series of nonnegative machine loads. Scheduled maintenance requires a continuous slot of exactly $k$ consecutive time intervals ($1 \le k \le n$). We seek the window with the smallest total load, breaking ties by selecting the earliest starting index.

Two approaches are implemented:
1. **Approach A (Independent Summation):** For each starting index $i$ from $0$ to $n - k$, compute the window sum $\sum_{j=i}^{i+k-1} loads[j]$ directly from scratch.
2. **Approach B (Sliding Window):** Compute the initial window sum $S_0 = \sum_{j=0}^{k-1} loads[j]$ once. For each subsequent window starting at index $i \ge 1$, obtain its sum by subtracting the element that left the window ($loads[i - 1]$) and adding the new element entering the window ($loads[i + k - 1]$):
   $$S_i = S_{i-1} - loads[i - 1] + loads[i + k - 1]$$
- **Tie-Breaking Correctness:** When tracking the minimum, using the strict inequality `currentSum < minTotal` guarantees that whenever an equal sum is encountered at a later start index $i$, the condition is false. Thus, `bestStart` is never overwritten on ties, preserving the earliest starting index.
- **Contract Enforcement:** Inputs with $k < 1$, $k > n$, or $n = 0$ are rejected by throwing an `IllegalArgumentException`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - _Approach A (Direct Summation):_ Summing each of the $(n - k + 1)$ windows of length $k$ requires $(n - k + 1)k$ operations, running in $O(n \cdot k)$ time (which degrades to $O(n^2)$ when $k \approx n / 2$).
  - _Approach B (Sliding Window):_ Initial window takes $k$ additions. Each of the remaining $(n - k)$ windows takes exactly 1 subtraction and 1 addition. Total operations: $k + 2(n - k) = 2n - k$, running in strict $O(n)$ linear time.
- **Auxiliary-Space Complexity:** $O(1)$ working memory for both methods (scalar counters and accumulator variables). An array of size $n - k + 1$ is allocated to store the recorded window sums for inspection, representing $O(n - k + 1)$ output storage.

### Algorithm Analysis Table

|**Criterion**|**Approach A: Independent Summation**|**Approach B: Sliding Window**|
|---|---|---|
|**Main idea**|Sum each $k$-slot window from scratch using an inner loop.|Compute the first sum once, then slide by subtracting the leaving value and adding the entering value.|
|**Time complexity**|Best / Worst / Average: $O((n - k + 1)k) = O(nk)$|Best / Worst / Average: $O(n)$|
|**Auxiliary-space complexity**|$O(1)$ working memory|$O(1)$ working memory|
|**Advantages**|Trivially simple; no state carried across iterations.|Optimal linear time; independent of window length $k$.|
|**Disadvantages**|Redundant recalculation of $k - 1$ overlapping elements on every step.|Requires tracking boundary indices to slide correctly.|
|**Suitable when**|Window size $k$ is extremely small (e.g. $k = 1, 2$) or only a single window is inspected.|Any general fixed-size window problem on moderate to large arrays ($n \le 10^6$).|

### Which approach would you choose for the stated constraints, and why?
**Sliding Window (Approach B)** is strictly superior. At $n = 100,000$ and $k = 50,000$, direct summation performs $\approx 2.5 \times 10^9$ operations (taking several seconds), whereas the sliding window completes in $2n - k \approx 150,000$ operations (a few milliseconds), while both use identical $O(1)$ working memory.

### Analysis Questions
1. **Valid window count and direct work derivation:** In an array of length $n$, the first window starts at index $0$ and the final window of length $k$ starts at index $n - k$. The total number of valid starting positions is $(n - k) - 0 + 1 = n - k + 1$. Because Approach A calculates each sum by visiting all $k$ elements, the total direct arithmetic work is $(n - k + 1) \cdot k$.
2. **Why one subtraction and one addition replace the next full summation:** Two adjacent windows $[i .. i + k - 1]$ and $[i + 1 .. i + k]$ overlap on $k - 1$ common elements ($loads[i + 1 .. i + k - 1]$). Window $i + 1$ differs from window $i$ only by excluding $loads[i]$ and including $loads[i + k]$. By the algebraic identity $\sum_{j=i+1}^{i+k} a[j] = \left(\sum_{j=i}^{i+k-1} a[j]\right) - a[i] + a[i + k]$, one subtraction and one addition update the sum in $O(1)$ time.
3. **Why the initial minimum must come from a real window rather than zero:** Because machine loads are nonnegative, every window sum is $\ge 0$. If `minTotal` were initialized to 0 and all valid window sums were strictly positive (e.g. sums $\ge 5$), the test `currentSum < minTotal` would never be true. The algorithm would mistakenly output a minimum total of 0 and an invalid index. Initializing `minTotal` to the sum of the very first window guarantees that our starting candidate corresponds to a genuine physical window.
4. **Which strict comparison preserves the earliest tied start:** The strict comparison `currentSum < minTotal` preserves the earliest start. When a subsequent window has a sum identical to the best sum seen so far (`currentSum == minTotal`), the condition evaluates to `false`, leaving `minTotal` and `bestStart` unchanged.
5. **Time and working/output space for both methods:**
   - Approach A: Time is $O((n - k + 1)k)$, auxiliary working space is $O(1)$.
   - Approach B: Time is $O(n)$, auxiliary working space is $O(1)$.
   - Both methods use $O(n - k + 1)$ output storage to record the sequence of computed window sums.
6. **Contrast with Lab 1's sum over subarrays of every length:** In Lab 1, the objective was to calculate the *total aggregate sum* of all $\frac{n(n+1)}{2}$ subarrays of all possible lengths. Because addition is associative and commutative, element frequencies could be collected via a closed-form contribution formula $(i + 1)(n - i)$. In contrast, finding the *minimum* over fixed-length windows is an order statistic (an extreme-value search). The minimum function does not distribute over addition, so no element-counting formula can select the minimum window without inspecting the window sums.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array & $k$**|**Expected Result (Min Total, Start, End)**|**Actual Output & Window Sums**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|`[8, 2, 5, 1, 4, 2, 7]`, $k = 3$|Total: `7`, Start: `3`, End: `5`|Total: `7`, Start: `3`, End: `5`<br><br>Sums: `[15, 8, 10, 7, 13]`|
|**Boundary ($k = 1$)**|`[8, 2, 5, 1, 4, 2, 7]`, $k = 1$|Total: `1`, Start: `3`, End: `3`|Total: `1`, Start: `3`, End: `3`<br><br>Sums: `[8, 2, 5, 1, 4, 2, 7]`|
|**Boundary ($k = n$)**|`[8, 2, 5, 1, 4, 2, 7]`, $k = 7$|Total: `29`, Start: `0`, End: `6`|Total: `29`, Start: `0`, End: `6`<br><br>Sums: `[29]`|
|**Boundary (All Zeros)**|`[0, 0, 0, 0]`, $k = 2$|Total: `0`, Start: `0`, End: `1`|Total: `0`, Start: `0`, End: `1`<br><br>Sums: `[0, 0, 0]`|
|**Normal (All Equal Loads)**|`[5, 5, 5, 5]`, $k = 2$|Total: `10`, Start: `0`, End: `1`|Total: `10`, Start: `0`, End: `1`<br><br>Sums: `[10, 10, 10]`|
|**Tricky (Earliest Tie)**|`[2, 1, 2, 1]`, $k = 2$|Total: `3`, Start: `0`, End: `1`|Total: `3`, Start: `0`, End: `1`<br><br>Sums: `[3, 3, 3]` (Earliest start 0 selected)|
|**Tricky (Total Larger Than 32-bit Int)**|`[10^9, 10^9, 10^9]`, $k = 3$|Total: `3000000000`, Start: `0`, End: `2`|Total: `3000000000`, Start: `0`, End: `2`<br><br>Sums: `[3000000000]`|
|**Invalid Rejections**|`k = 0`, `k = 5 > 3`, `[]`|Rejected with message|All rejected cleanly (`IllegalArgumentException`)|

---

## Problem 9 – Longest Playlist Within a Time Limit
### Main Idea & Correctness Argument
Given a playback queue of nonnegative song durations and a budget limit $S \ge 0$, we seek the longest contiguous segment of songs whose total duration does not exceed $S$. If multiple segments tie for maximum length, the earliest starting segment is chosen.

A **Variable-Size Sliding Window** maintains the valid window $[left, right]$:
1. Initialize `left = 0`, `currentSum = 0L`, and `maxLen = 0`.
2. Expand the window to the right by iterating `right` from $0$ to $n - 1$ and adding `durations[right]` to `currentSum`.
3. If `currentSum > S`, the window is over budget. Shrink from the left by repeatedly subtracting `durations[left]` and advancing `left++` until `currentSum <= S` or `left > right`.
4. Once validity is restored (`currentSum <= S` with `left <= right`), the window length is $right - left + 1$. If $curLen > maxLen$, record the new best length, start/end indices, and sum. Using strict inequality ($>$) ensures that later windows of equal length do not overwrite earlier ties.
5. If no non-empty window is found (e.g. empty array or all individual values $> S$), the sentinel `(0, -1, -1, 0)` is returned.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(n)$ linear time.
  - The right endpoint `right` advances from $0$ to $n - 1$ (exactly $n$ steps).
  - The left endpoint `left` starts at $0$ and only advances forward (`left++`). It can move forward at most $n$ times across the entire algorithm execution.
  - Across the entire run, the inner `while` loop executes at most $n$ times. Total pointer advances: at most $2n$.
- **Auxiliary-Space Complexity:** $O(1)$ working memory (scalar pointers `left`, `right`, and `long` accumulators).

### Algorithm Analysis Table

|**Criterion**|**Approach A: Brute-Force Check All Subarrays**|**Approach B: Variable-Size Sliding Window**|
|---|---|---|
|**Main idea**|Check all $\frac{n(n+1)}{2}$ subarrays and compute each sum to see if $\le S$.|Expand `right`, shrink `left` when sum exceeds $S$, and record best valid length.|
|**Time complexity**|Worst: $O(n^2)$ with running sum, $O(n^3)$ naive.|Worst / Average: $O(n)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Straightforward nested loop.|Single-pass linear scaling; processes $10^6$ entries in milliseconds.|
|**Disadvantages**|Completely unviable for $n > 10,000$.|Requires nonnegative values for the monotonic shrinking invariant to hold.|
|**Suitable when**|Educational illustration on tiny inputs ($n \le 100$).|Standard queue/stream processing with nonnegative costs ($n \le 10^6$).|

### Which approach would you choose for the stated constraints, and why?
**Variable-Size Sliding Window (Approach B)** is chosen because it runs in $O(n)$ time and $O(1)$ working memory. For $n = 100,000$, brute force requires $\approx 5 \times 10^9$ operations, whereas sliding window finishes in fewer than $200,000$ operations.

### Analysis Questions
1. **Meaning of the current window after shrinking:** After shrinking until `currentSum <= S`, $[left, right]$ represents the longest contiguous subsegment ending at index $right$ whose total duration is within budget $S$. Extending this segment any further to the left (e.g. including $left - 1$) would cause its total sum to exceed $S$.
2. **Why the condition is $\text{sum} > S$ rather than $\text{sum} \ge S$:** The budget condition requires $\text{sum} \le S$. A window whose total is exactly equal to $S$ is completely valid and should be considered for the longest playlist. If the condition were $\text{sum} \ge S$, a qualifying playlist totaling $S$ would be prematurely shrunk, discarding valid answers.
3. **Why nonnegative values justify permanently discarding a left boundary:** Because all durations are nonnegative ($a[i] \ge 0$), adding future elements on the right can only increase or maintain the sum. If the window $[left, right]$ exceeds $S$, any extension $[left, right']$ with $right' > right$ will have sum $\ge \text{sum} > S$. Thus, index $left$ can never be the start of any valid window ending at or after $right$. Permanently incrementing `left` is therefore mathematically safe.
4. **Endpoint bounds and complexity:** The right pointer advances exactly $n$ times. The left pointer starts at $0$ and is only incremented; it never exceeds $right + 1 \le n$. Thus, both pointers together make at most $2n$ steps, establishing an $O(n)$ time bound. Working space is $O(1)$ because only a constant number of scalar index and sum variables are allocated.
5. **Earliest-tie rule and no-result marker:** The earliest-tie rule is enforced by strict inequality `if (curLen > maxLen)`: later windows of equal length do not update the saved indices. The no-result marker `(0, -1, -1, 0)` unambiguously signals absence of any valid non-empty playlist, distinguishing it from a valid 1-song playlist starting at index 0 (`length = 1, start = 0`).
6. **Signed-value counterexample:** Consider budget $S = 5$ on the array `[6, -3, 2]`.
   - At $right = 0$, $a[0] = 6 > 5$. The algorithm shrinks from the left, discarding $left = 0$.
   - At $right = 2$, the algorithm finds $[1..2]$ (`[-3, 2]`) with sum $-1 \le 5$, length 2.
   - However, the full window $[0..2]$ has sum $6 + (-3) + 2 = 5 \le 5$, which has length 3! The negative value reduced the sum, making the discarded start index $0$ valid again. Thus, the two-pointer sliding window fails on arrays with negative numbers.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array & Limit $S$**|**Expected Output (Len, Start, End, Sum)**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Handout Example, Traced)**|`[5, 0, 2, 4, 1, 1, 3]`, $S = 8$|`Len: 5, Start: 1, End: 5, Sum: 8`|`Len: 5, Start: 1, End: 5, Sum: 8`<br><br>Trace: `right=3: sum=11>8->removes dur[0]=5`; `right=5: len=5 [1..5]`; `right=6: sum=11>8->removes dur[1]=0, dur[2]=2, dur[3]=4`.|
|**Boundary (Empty Array)**|`[]`, $S = 8$|`Len: 0, Start: -1, End: -1, Sum: 0`|`Len: 0, Start: -1, End: -1, Sum: 0`|
|**Boundary (Entire Array Fits)**|`[1, 2, 3]`, $S = 10$|`Len: 3, Start: 0, End: 2, Sum: 6`|`Len: 3, Start: 0, End: 2, Sum: 6`|
|**Tricky (All Exceed Limit)**|`[10, 20, 30]`, $S = 5$|`Len: 0, Start: -1, End: -1, Sum: 0`|`Len: 0, Start: -1, End: -1, Sum: 0`|
|**Tricky (Zeros with Limit Zero)**|`[0, 0, 5, 0]`, $S = 0$|`Len: 2, Start: 0, End: 1, Sum: 0`|`Len: 2, Start: 0, End: 1, Sum: 0`|
|**Normal (Tied Best Lengths)**|`[3, 3, 3, 3]`, $S = 6$|`Len: 2, Start: 0, End: 1, Sum: 6`|`Len: 2, Start: 0, End: 1, Sum: 6` (Earliest start 0)|
|**Boundary (Sums Beyond 32-bit Int)**|`[10^9, 10^9, 10^9]`, $S = 2.5 \times 10^9$|`Len: 2, Start: 0, End: 1, Sum: 2000000000`|`Len: 2, Start: 0, End: 1, Sum: 2000000000`|

---

## Problem 10 – Shortest Upload Target
### Main Idea & Correctness Argument
An upload log records positive data transfer volumes ($volumes[i] > 0$). Given a target volume $S > 0$, find the shortest contiguous segment whose total volume is at least $S$. If multiple windows share the minimum length, retain the earliest start. If no window reaches $S$, return `(0, -1, -1, 0)`.

A **Variable-Size Shrinking Sliding Window** operates as follows:
1. Initialize `left = 0`, `currentSum = 0L`, and `bestLen = Integer.MAX_VALUE`.
2. Expand `right` from $0$ to $n - 1$, adding `volumes[right]` to `currentSum`.
3. Whenever `currentSum >= S`, the current window $[left, right]$ is valid.
   - Record candidate: If $curLen = right - left + 1 < bestLen$, update `bestLen = curLen`, `bestStart = left`, `bestEnd = right`, `bestSum = currentSum`. (Strict inequality preserves the earliest start on ties).
   - Shrink window: Subtract `volumes[left]` from `currentSum` and advance `left++`. Continue shrinking in a `while` loop as long as `currentSum >= S` to see if a strictly shorter valid window exists.
4. **Critical Difference from Problem 9:** In Problem 10, shrinking occurs *while the window is valid* to minimize length. The candidate must be recorded *before* removing elements; updating only after the window becomes invalid would lose the valid candidate.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(n)$ linear time. The `right` pointer advances $n$ times. Because all volumes are strictly positive, `left` advances monotonically and moves at most $n$ times. The total number of additions and subtractions across the entire run is bounded by $2n$.
- **Auxiliary-Space Complexity:** $O(1)$ working memory (only scalar pointers and accumulators).

### Algorithm Analysis Table

|**Criterion**|**Approach A: Brute-Force Nested Loops**|**Approach B: Shrinking Sliding Window**|
|---|---|---|
|**Main idea**|Test all subarrays starting at every index $i$ and scan until sum $\ge S$.|Expand `right`; while sum $\ge S$, record candidate and shrink `left`.|
|**Time complexity**|Worst case: $O(n^2)$|Best / Worst / Average: $O(n)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Simple logic without stateful two-pointer maintenance.|Strict $O(n)$ linear time; scales to millions of upload records.|
|**Disadvantages**|Quadratic time; fails on large inputs.|Requires strictly positive values for the monotonic contraction invariant.|
|**Suitable when**|Tiny arrays ($n < 500$).|High-throughput telemetry and upload log processing ($n \le 10^6$).|

### Which approach would you choose for the stated constraints, and why?
**Shrinking Sliding Window (Approach B)** is chosen. It operates in $O(n)$ time and $O(1)$ auxiliary memory, finding the optimal upload window in a single pass without redundant recalculations.

### Analysis Questions
1. **Why a candidate must be recorded before the next removal:** In Problem 10, the validity requirement is $\text{sum} \ge S$. When the loop condition holds, the window $[left, right]$ is valid. Shrinking (`currentSum -= volumes[left]; left++;`) strictly reduces the sum and may drop it below $S$. If we waited until after removal, the valid state would be destroyed and lost.
2. **Why a single `if` statement is insufficient:** A single newly added element $volumes[right]$ might be much larger than $S$ (for example, adding $100$ when $S = 10$). The sum may remain $\ge S$ even after removing several preceding elements from the left. A single `if` statement would only discard at most one element, missing the true minimal window. The `while` loop guarantees the window shrinks as far as possible for that `right` endpoint.
3. **How positivity supports the reasoning:** Because all volumes are strictly positive ($volumes[i] > 0$), adding an element strictly increases the sum and removing an element strictly decreases the sum. This guarantees monotonic behavior: once a window $[left, right]$ has sum $< S$, shrinking it further to $[left + 1, right]$ will produce an even smaller sum. If negative values were permitted, removing a negative value would increase the sum, breaking the sliding window logic.
4. **Comparison with Problem 9:**
   - **Objective:** Problem 9 maximizes length; Problem 10 minimizes length.
   - **Condition:** Problem 9 requires $\text{sum} \le S$; Problem 10 requires $\text{sum} \ge S$.
   - **Shrinking Trigger:** Problem 9 shrinks when the window is *invalid* ($\text{sum} > S$); Problem 10 shrinks while the window is *valid* ($\text{sum} \ge S$).
   - **Update Moment:** Problem 9 updates *after* shrinking restores validity; Problem 10 updates *during/before* shrinking while the window is valid.
5. **Time, auxiliary space, and forward-only movement:** Time is $O(n)$ and space is $O(1)$. Forward-only movement of `left` is justified because if $[left, right]$ meets the target $S$, any window starting earlier $[left - 1, right]$ has length $right - (left - 1) + 1 = curLen + 1$, which is strictly longer than $curLen$ and can never improve our minimum length objective.
6. **Distinguishing no result from length-one answer at index zero:** `bestLen` is initialized to the sentinel `Integer.MAX_VALUE`. If a single element at index 0 satisfies $volumes[0] \ge S$, `bestLen` becomes 1 and `bestStart` becomes 0, returning `(1, 0, 0, volumes[0])`. If no window reaches $S$, `bestLen` remains `Integer.MAX_VALUE`, which is converted to `(0, -1, -1, 0)`.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array & Target $S$**|**Expected Output (Len, Start, End, Sum)**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Handout Example, Traced)**|`[5, 1, 2, 7, 3, 4]`, $S = 10$|`Len: 2, Start: 3, End: 4, Sum: 10`|`Len: 2, Start: 3, End: 4, Sum: 10`<br><br>Trace: `[0..3] sum=15->len=4`; `[1..3] sum=10->len=3`; `[2..4] sum=12->len=3`; `[3..4] sum=10->len=2`.|
|**Boundary (Empty Array)**|`[]`, $S = 10$|`Len: 0, Start: -1, End: -1, Sum: 0`|`Len: 0, Start: -1, End: -1, Sum: 0`|
|**Boundary (Unreachable Target)**|`[1, 2, 3]`, $S = 100$|`Len: 0, Start: -1, End: -1, Sum: 0`|`Len: 0, Start: -1, End: -1, Sum: 0`|
|**Boundary (Single Value Qualifies)**|`[5, 12, 3]`, $S = 10$|`Len: 1, Start: 1, End: 1, Sum: 12`|`Len: 1, Start: 1, End: 1, Sum: 12`|
|**Boundary (Entire Array Needed)**|`[2, 3, 4]`, $S = 9$|`Len: 3, Start: 0, End: 2, Sum: 9`|`Len: 3, Start: 0, End: 2, Sum: 9`|
|**Tricky (Tied Minimum Lengths)**|`[4, 6, 2, 8]`, $S = 10$|`Len: 2, Start: 0, End: 1, Sum: 10`|`Len: 2, Start: 0, End: 1, Sum: 10` (Earliest start 0 over 2)|
|**Tricky (Several Removals After Single Large Value)**|`[1, 1, 1, 100]`, $S = 10$|`Len: 1, Start: 3, End: 3, Sum: 100`|`Len: 1, Start: 3, End: 3, Sum: 100`|
|**Boundary (Totals Beyond 32-bit Int)**|`[10^9, 10^9, 10^9]`, $S = 2 \times 10^9$|`Len: 2, Start: 0, End: 1, Sum: 2000000000`|`Len: 2, Start: 0, End: 1, Sum: 2000000000`|

---

## Problem 12 – Archive Record Locator
### Main Idea & Correctness Argument
A digital archive stores unordered records consisting of a unique integer `id` and an associated integer payload `shelfNumber`. To answer multiple lookup queries efficiently while preserving the original input records:
1. **Record Representation:** An `ArchiveRecord` class encapsulates both the search key `id` and the payload `shelfNumber`.
2. **Protective Copy & Insertion Sort:** A fresh protective copy of the record array is created. Ascending insertion sort orders the copy by `id`. In each step, the algorithm compares IDs (`records[j].id > key.id`) and **shifts complete `ArchiveRecord` references**, ensuring each ID remains permanently bound to its corresponding shelf number.
3. **Binary Search by ID:** Binary search operates on the sorted copy. In each step, it inspects midpoint `mid`:
   - If `sorted[mid].id == targetId`, the record is found; return `sorted[mid]`.
   - If `sorted[mid].id < targetId`, narrow to right half (`low = mid + 1`).
   - If `sorted[mid].id > targetId`, narrow to left half (`high = mid - 1`).
   - If `low > high`, report `NOT FOUND`.
4. **Verification with Linear Search Baseline:** Each query is cross-checked against a linear scan of the original records array to guarantee identical lookup results.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - _Pre-processing Cost (Sort Once):_ Creating the protective copy takes $O(n)$ time. Insertion sort takes $O(n)$ best-case time (already sorted), and $O(n^2)$ worst-case time (reverse order).
  - _Per-Query Lookup Time:_ $O(\log n)$ using binary search on the sorted copy.
  - _Total Cost for $q$ Queries:_ $O(n^2 + q \log n)$ overall time.
  - _Comparison to Repeated Linear Scans:_ Linear scanning takes $O(q \cdot n)$ total time with zero pre-processing.
- **Auxiliary-Space Complexity:** $O(n)$ auxiliary memory to store the protective copy of the $n$ records. The insertion sort and binary search algorithms themselves operate in $O(1)$ working memory.

### Algorithm Analysis Table

|**Criterion**|**Approach A: Repeated Linear Scans**|**Approach B: Sort Protective Copy Once + Binary Search**|
|---|---|---|
|**Main idea**|Leave records unsorted; perform a linear scan over all $n$ records for every query.|Sort a protective copy of records by ID using insertion sort once; answer all queries via binary search.|
|**Pre-processing time**|$0$|$O(n^2)$ using insertion sort ($O(n \log n)$ if using merge/heap sort)|
|**Per-query time**|$O(n)$|$O(\log n)$|
|**Total time ($q$ queries)**|$O(q \cdot n)$|$O(n^2 + q \log n)$|
|**Auxiliary space**|$O(1)$|$O(n)$ (protective record copy)|
|**Advantages**|Zero pre-processing delay; original order untouched.|Fast queries ($O(\log n)$); scales to massive query volumes.|
|**Disadvantages**|Slow when query count $q$ is large ($q \cdot n$ operations).|Upfront sorting delay; extra $O(n)$ storage for the sorted copy.|
|**Suitable when**|Only one or very few queries are performed ($q \ll n$).|Fixed archive queried repeatedly ($q \gg n$) with $n \le 2,000$.|

### Which approach would you choose for the stated constraints, and why?
For an archive with $n \le 2,000$ queried repeatedly ($q \gg n$), **Approach B (Sort Once + Binary Search)** is chosen. For example, with $n = 2,000$ and $q = 10,000$, repeated linear scans take $\approx 2 \times 10^7$ inspections, whereas sorting once followed by binary search takes $\approx 2 \times 10^6 + 10,000 \times 11 \approx 2.1 \times 10^6$ operations—nearly 10 times fewer operations—with each individual user query responding in sub-microsecond time.

### Analysis Questions
1. **Why sorting only the IDs would corrupt lookup results:** Each archive entry binds an ID to a specific physical shelf number. If an algorithm sorts an array of IDs while leaving the array of shelf numbers in their original order, the ID at index $i$ will be paired with the shelf number that belonged to a completely different record. Shifting complete `ArchiveRecord` objects ensures that the ID-payload relationship remains intact.
2. **Comparing repeated scans with insertion sort once + binary searches:**
   - Repeated Linear Scans: Pre-processing: $0$. Query time: $O(q \cdot n)$. Auxiliary memory: $O(1)$.
   - Sort Once + Binary Searches: Pre-processing: $O(n)$ memory for protective copy, $O(n^2)$ insertion sort time. Query time: $O(q \log n)$. Total cost: $O(n^2 + q \log n)$ time and $O(n)$ auxiliary space.
3. **Why the insertion-based worst case is $O(n^2 + q \log n)$, not $O(n \log n + q \log n)$:** Binary search operates in $O(\log n)$ per query, but the pre-processing phase uses insertion sort, whose worst-case time is quadratic $O(n^2)$ (on reverse-sorted input). Because the total worst-case cost is the sum of pre-processing and querying, it is $O(n^2 + q \log n)$. The bound would only become $O(n \log n + q \log n)$ if a divide-and-conquer sort (such as merge sort) were used.
4. **Single query vs. many queries, and consequences of dynamic record changes:**
   - For a single query ($q = 1$), a linear scan ($O(n)$) is vastly faster than sorting ($O(n^2)$). Sorting is only beneficial when the upfront cost is amortized across many queries ($q \gg 1$).
   - If the archive is dynamic (records are inserted, deleted, or updated), the sorted order is violated. Any newly inserted record requires $O(n)$ shifting to maintain sorted order, or a complete re-sort, making the static sorted-array model inefficient compared to balanced search trees or hash tables.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Records & Query IDs**|**Expected Query Results**|**Actual Output & Baseline Cross-Check**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|`[(708, 12), (502, 3), (901, 8), (604, 6)]`<br>Queries: `604, 605`|• Query 604: `604 6`<br>• Query 605: `NOT FOUND`|• Query 604: `604 6` (Verified with linear search)<br>• Query 605: `NOT FOUND` (Verified with linear search)|
|**Boundary (Empty Collection)**|`[]`<br>Query: `100`|• Query 100: `NOT FOUND`|• Query 100: `NOT FOUND` (Verified with linear search)|
|**Boundary (Single Record Collection)**|`[(101, 5)]`<br>Queries: `101, 999`|• Query 101: `101 5`<br>• Query 999: `NOT FOUND`|• Query 101: `101 5` (Verified with linear search)<br>• Query 999: `NOT FOUND` (Verified with linear search)|
|**Normal (Already Ascending Order)**|`[(10, 1), (20, 2), (30, 3)]`<br>Queries: `20, 40`|• Query 20: `20 2`<br>• Query 40: `NOT FOUND`|• Query 20: `20 2` (Verified with linear search)<br>• Query 40: `NOT FOUND` (Verified with linear search)|
|**Tricky (Strictly Reverse Input Order)**|`[(500, 50), (400, 40), (300, 30), (200, 20), (100, 10)]`<br>Queries: `100, 300, 500, 250`|• Query 100: `100 10`<br>• Query 300: `300 30`<br>• Query 500: `500 50`<br>• Query 250: `NOT FOUND`|• Query 100: `100 10`<br>• Query 300: `300 30`<br>• Query 500: `500 50`<br>• Query 250: `NOT FOUND`<br>(All verified with linear search)|
