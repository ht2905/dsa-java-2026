# DSA Lab 01 Report
- Author: Tran Quoc Hoang – BTBTWE24036
- Course: Algorithms & Data Structures (IT013IU)
- For: Lab 01, Version 04

---

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

---

## Problem 2 – Suffix Maximum Queries
### Algorithm Explanation

The problem asks to determine $\max_{k=p}^{n-1} \text{arr}[k]$ for multiple query indices $p$ ($0 \le p < n$) without modifying the original array. Two distinct algorithmic approaches were implemented.

#### Approach A (Direct Suffix Scan)
- **Mechanism**: Evaluates each query on demand without pre-processing. For a given index $p$, a single loop scans elements from $k = p$ to $n - 1$, tracking the running maximum initialized to $\text{arr}[p]$.
- **Execution**: Answers are produced on-the-fly in query order without caching intermediate states.

#### Approach B (Suffix-Maximum Pre-processing)
- **Mechanism**: Exploits the dynamic programming recurrence:
$$
\text{suffixMax}[i] = \begin{cases} \text{arr}[n - 1] & \text{if } i = n - 1 \\ \max(\text{arr}[i], \text{suffixMax}[i + 1]) & \text{if } 0 \le i < n - 1 \end{cases}
$$
- **Execution**: Traverses $\text{arr}$ once from right to left ($i = n - 2$ down to $0$) to construct an auxiliary array `suffixMax` of size $n$. Each subsequent query at index $p$ is answered via direct indexed lookup `suffixMax[p]`.

### Complexity Analysis
Let $n$ denote the length of the data array and $q$ denote the total number of queries.

#### Approach A (Direct Suffix Scan)
- **Pre-processing Time**: $O(0) = O(1)$ (no pre-computation performed).
- **Per-Query Time**: $\Theta(n - p)$.
    - **Worst Case**: $\Theta(n)$ when $p = 0$ (scans the entire array).
    - **Best Case**: $\Theta(1)$ when $p = n - 1$ (inspects only the final element).
    - **Average Case**: $\Theta(n)$ assuming uniformly distributed query positions $p \in [0, n - 1]$, where the expected scan length is $\frac{n + 1}{2}$.
- **Total Time ($q$ queries)**: $O(q \cdot n)$. When queries frequently target small indices $p$, the overall time is $\Theta(q \cdot n)$.
- **Auxiliary-Space Complexity**: $O(1)$. Requires only a single integer variable to maintain the running maximum during the iteration.

#### Approach B: Suffix-Maximum Pre-processing
- **Pre-processing Time**: $\Theta(n)$. Traverses indices $n - 1$ down to $0$ performing one comparison and assignment per step.
- **Per-Query Time**: $\Theta(1)$. Serves queries through a direct array lookup `suffixMax[p]`.
- **Total Time ($q$ queries)**: $\Theta(n + q)$. Pre-processing takes linear time, after which each of the $q$ queries takes constant time.
- **Auxiliary-Space Complexity**: $\Theta(n)$. Allocates a dedicated auxiliary array `suffixMax` of size $n$ to hold the prefix summaries.

### Algorithm Analysis Table

|**Criterion**|**Approach A (Direct Scan)**|**Approach B (Pre-processed Suffix Max)**|
|---|---|---|
|**Main idea**|Scan array from index $p$ to $n - 1$ on demand per query.|Precompute suffix maxima right-to-left into an auxiliary table; query via index lookup.|
|**Pre-processing time**|$O(1)$ (None)|$\Theta(n)$|
|**Per-query time**|$\Theta(n - p)$ ($O(n)$ worst case)|$\Theta(1)$|
|**Total time ($q$ queries)**|$O(q \cdot n)$|$\Theta(n + q)$|
|**Auxiliary-space complexity**|$O(1)$|$\Theta(n)$|
|**Advantages**|Zero memory overhead; zero startup latency; optimal for $q = 1$ near the array end.|Constant query time $O(1)$; highly scalable across large numbers of queries.|
|**Disadvantages**|Redundant scans across queries; degrades to $O(q \cdot n)$ time under large $q$.|Allocates an extra $O(n)$ array; incurs initial $\Theta(n)$ overhead even if few queries arrive.|
|**Suitable when**|Very few queries ($q \ll n$) or queries predominantly cluster near $p \approx n - 1$.|Static data with many queries ($q \gg 1$) distributed across arbitrary indices.|

### Chosen for Most Situations: Approach B

Under the lab constraints ($n \le 100{,}000$ integers, multiple queries, and an immutable input array), **Approach B (Suffix-Maximum Pre-processing)** is the preferred choice. For non-trivial workloads where $q \ge 100$, Approach A incurs up to $O(q \cdot n) \approx 10^{10}$ operations, causing substantial execution delays. In contrast, Approach B requires a single linear pass of $\approx 10^5$ operations and $400\text{ KB}$ of auxiliary heap memory, subsequently serving all $q$ queries in $\Theta(1)$ time each with total runtime $\Theta(n + q)$.

### Problem-Specific Analysis Questions
1. **Analyze the time for one direct query in terms of both $n$ and $p$. What is its auxiliary-space complexity?**
	- A direct query starting at index $p$ scans elements from index $p$ through $n - 1$, inclusive. This examines exactly $n - p$ elements and executes $n - p - 1$ comparison steps (for $p < n - 1$). Thus, the exact time complexity is $\Theta(n - p)$.
	- The auxiliary-space complexity is $O(1)$, as the traversal only uses a scalar loop variable and a scalar accumulator (`maxVal`) without auxiliary allocations.
2. **Analyze the pre-processing time, time per query, and auxiliary space of Approach B.**
	- **Pre-processing time**: $\Theta(n)$. The right-to-left loop visits each element from index $n - 1$ down to 0, executing exactly $n - 1$ comparisons of the form $\max(\text{arr}[i], \text{suffixMax}[i + 1])$.
	- **Time per query**: $\Theta(1)$. Serving query index $p$ is an immediate $O(1)$ array access into `suffixMax[p]`.
	- **Auxiliary space**: $\Theta(n)$. Requires allocating an additional integer array `suffixMax` of length $n$.
3. **Let $q$ denote the number of queries. Compare the total costs, including pre-processing, when queries may start at index zero.**
	- When queries can start at index $0$, each query in Approach A scans all $n$ elements in the worst case, giving a total time of $\Theta(q \cdot n)$ with $O(1)$ auxiliary space.
    
	- In Approach B, pre-processing requires $\Theta(n)$ operations, and the $q$ queries consume $\Theta(q)$ operations combined. The total time is $\Theta(n + q)$ with $\Theta(n)$ auxiliary space.
    
	- If $q = 1$, Approach A takes $c_1 n$ operations, whereas Approach B takes $c_2 n + c_3$ operations; Approach A is preferable due to lower constant factors and zero space overhead. However, once $q > 1$, the ratio of total work $\frac{\Theta(q \cdot n)}{\Theta(n + q)}$ quickly favors Approach B. When $q \approx n$, Approach A requires quadratic time $O(n^2)$ while Approach B completes in linear time $O(n)$.
4. **Which approach would you choose for one query at $p = n − 1$? How would your choice change for many queries on the same array?**
	- **Single query at $p = n - 1$**: Choose **Approach A**. The scan inspects only the final element `arr[n - 1]`, executing in $\Theta(1)$ time and $O(1)$ auxiliary space. Approach B would perform an unnecessary $\Theta(n)$ pre-processing phase and allocate an $n$-element array for a query that inspects a single element.
    
	- **Many queries on the same array**: Choose **Approach B**. Amortizing the initial $\Theta(n)$ pre-processing over $q$ queries drops the marginal cost per query to $\Theta(1)$, preventing the severe $O(q \cdot n)$ bottleneck of repeated scans.
5. **How should the running maximum be initialized to handle all-negative arrays? What value is stored at the final position?**
	- **Initialization**: The running maximum must **not** be initialized to `0`. In an array where all elements are negative (e.g., $[-9, -2, -15]$), initializing to `0` would yield an incorrect maximum of `0`. Instead:
    
	    - In Approach A, initialize `maxVal` directly to the first element of the queried suffix: `maxVal = arr[p]` (or alternatively `Integer.MIN_VALUE`).
	        
	    - In Approach B, initialize the base case at index $n - 1$ directly to `arr[n - 1]`.
        
	- **Value stored at the final position**: `suffixMax[n - 1]` stores `arr[n - 1]`. Because the suffix beginning at index $n - 1$ contains only one element, that element is trivially its own maximum.
6. **Why does a right-to-left traversal make the required summary available? What could become invalid if an input value changed after pre-processing?**
	- **Why right-to-left traversal works**: The suffix starting at index $i$, $\text{arr}[i \dots n - 1]$, can be decomposed into $\{\text{arr}[i]\} \cup \text{arr}[i + 1 \dots n - 1]$. Its maximum satisfies the recurrence $\text{suffixMax}[i] = \max(\text{arr}[i], \text{suffixMax}[i + 1])$. Traversing right-to-left guarantees that when computing index $i$, the optimal summary for the subproblem at $i + 1$ has already been evaluated and recorded.
	- **Consequences of an input modification**:
		
		- If an entry `arr[k]` is modified after pre-processing, every suffix containing index $k$—specifically all entries `suffixMax[i]` for $0 \le i \le k$—becomes invalid.
		    
		- If `arr[k]` increases above `suffixMax[k]`, the new larger value must propagate leftward. If `arr[k]` was the unique maximum of the suffix and decreases, the previous maximum is no longer present, necessitating a complete re-scan of the suffix. Static pre-processing is valid only under the assumption that the underlying array is immutable.

### Test Cases and Verification

|**Case Type**|**Input Array (arr)**|**Query Indices (queries)**|**Expected Output**|**Actual Output**|**Status**|
|---|---|---|---|---|---|
|**Normal Case**|`[3, 8, -2, 6, 1]`|`[0, 2, 4, 1]`|`[8, 6, 1, 8]`|`[8, 6, 1, 8]`|PASS|
|**Boundary Case (Empty)**|`[]`|`[]`|`[]`|`[]`|PASS|
|**Boundary Case (Single)**|`[42]`|`[0]`|`[42]`|`[42]`|PASS|
|**Boundary Case ($p = n - 1$)**|`[10, -5, 20, 4]`|`[3]`|`[4]`|`[4]`|PASS|
|**Tricky Case (All-Negative)**|`[-9, -2, -15, -4, -7]`|`[0, 1, 2, 3, 4]`|`[-2, -2, -4, -4, -7]`|`[-2, -2, -4, -4, -7]`|PASS|
|**Tricky Case (Decreasing)**|`[100, 50, 25, 10, 5]`|`[0, 1, 2, 3, 4]`|`[100, 50, 25, 10, 5]`|`[100, 50, 25, 10, 5]`|PASS|

---

## Problem 3 - Find the Missing Number

### Algorithm Explanation
Given an array of length $n$ containing $n$ distinct integers from the inclusive range $[0, n]$, exactly one integer is missing. Both required approaches identify this missing value without sorting, modifying the input, or using closed-form mathematical shortcuts (e.g., Gauss summation or bitwise XOR).

#### Approach A (Repeated Membership Search)
- **Mechanism**: Generates candidate integers sequentially in increasing order: $c = 0, 1, 2, \dots, n$.
- **Execution**: For each candidate $c$, the algorithm initiates a linear search through $\text{arr}[0 \dots n - 1]$ from index $0$. If $\text{arr}[i] == c$, the search terminates early with a match. If the entire array is scanned without finding $c$, candidate $c$ is absent and returned immediately.

#### Approach B (Presence Array)
- **Mechanism**: Allocates an auxiliary boolean array `present` of length $n + 1$, where indices directly represent domain values $[0, n]$.
- **Execution**:
	- **Allocation & Initialization**: Allocates `boolean[n + 1]`, with Java automatically zero-initializing all entries to `false`.
	- **Marking**: Performs a single pass over $\text{arr}$, setting `present[arr[i]] = true` for each element.
	- **Scan**: Iterates through `present` from index $0$ to $n$. The first index where `present[candidate] == false` corresponds to the missing number.

### Complexity Analysis
Let $n$ denote the length of the input array $\text{arr}$.

#### Approach A: Repeated Membership Search
- **Time Complexity**:
	- **Best Case**: $\Theta(n)$. Occurs when candidate $0$ is missing. The algorithm searches for $c = 0$, checks all $n$ elements, finds no match, and immediately returns $0$ in a single pass of $n$ comparisons.
    
	- **Worst Case**: $\Theta(n^2)$. Occurs when candidate $n$ is missing, and the elements $0, 1, \dots, n - 1$ appear in reverse order or toward the end of the array. The algorithm must successfully search for all candidates $c \in [0, n - 1]$ and then perform a full scan of $n$ comparisons for candidate $n$, requiring up to $\frac{n(n + 1)}{2} + n = \Theta(n^2)$ total comparisons.
    
	- **Average Case**: $\Theta(n^2)$ when candidates are uniformly distributed, as finding each present candidate takes $\approx \frac{n}{2}$ comparisons on average across $\approx \frac{n}{2}$ candidate iterations.
- **Auxiliary-Space Complexity**: $O(1)$. Requires only primitive scalar variables (`candidate`, `i`, `found`) to drive the search loops.

#### Approach B: Presence Array
- **Time Complexity**:
	- **Allocation & Zeroing**: $\Theta(n + 1) = \Theta(n)$ time to allocate and zero-initialize the boolean array.
    
	- **Marking Phase**: $\Theta(n)$ time. Traverses all $n$ elements of $\text{arr}$ once, performing an $O(1)$ direct array write for each.
	    
	- **Final Scan Phase**: $O(n)$ time. Scans `present` from index $0$ to $m$ (where $m$ is the missing value), performing $m + 1 \le n + 1$ boolean checks.
	    
	- **Total Time**: $\Theta(n)$ across all cases (best, average, worst).
- **Auxiliary-Space Complexity**: $\Theta(n)$. Allocates a boolean array of size $n + 1$ on the heap, requiring $\Theta(n)$ additional memory.

### Algorithm Analysis Table

|**Criterion**|**Approach A (Repeated Search)**|**Approach B (Presence Array)**|
|---|---|---|
|**Main idea**|Sequentially test candidates $0 \dots n$ via repeated linear scans of $\text{arr}$.|Mark seen values into an auxiliary boolean array of size $n + 1$; scan for the unmarked index.|
|**Time complexity**|Best: $\Theta(n)$, Worst: $\Theta(n^2)$, Average: $\Theta(n^2)$|Best/Average/Worst: $\Theta(n)$|
|**Auxiliary-space complexity**|$O(1)$|$\Theta(n)$|
|**Advantages**|Zero memory overhead ($O(1)$ auxiliary space); fast when $0$ is missing.|Optimal linear runtime $\Theta(n)$; consistent performance regardless of data order.|
|**Disadvantages**|Severe quadratic bottleneck $\Theta(n^2)$ on large inputs; highly sensitive to data permutation.|Allocates an extra array of size $n + 1$ ($\Theta(n)$ auxiliary space).|
|**Suitable when**|Memory is strictly constrained ($O(1)$ space budget) and $n$ is very small ($n \le 1{,}000$).|Standard execution environments with large arrays ($n$ up to $100{,}000$) where runtime efficiency is paramount.|

### Chosen for Most Cases: Approach B

Under the lab constraints ($n \le 100{,}000$ integers), **Approach B (Presence Array)** is the vastly superior choice. For $n = 100{,}000$, Approach A's worst-case time involves $\approx 5 \times 10^9$ comparison operations, taking several seconds or timing out. In contrast, Approach B executes strictly in linear time $\Theta(n)$, taking mere milliseconds and requiring only $\approx 100\text{ KB}$ of boolean memory, well within memory limits.

### Problem-Specific Analysis Questions
1. **Analyze the worst-case time and auxiliary space of repeated membership search. Give an input that forces a search for every candidate.**

	- **Worst-Case Time & Space**: The worst case requires checking all $n + 1$ candidates ($c = 0, 1, \dots, n$). For candidates $0 \dots n - 1$, each search scans through the array until reaching the candidate. For candidate $n$, the entire array of length $n$ is scanned with no match found. The worst-case time is $\Theta(n^2)$, and auxiliary space is $O(1)$.
	    
	- **Adversarial Input**: Consider the input array arranged in strictly descending order:
	    $$
	    \text{arr} = [n - 1, n - 2, \dots, 2, 1, 0]
	    $$
	    
	    Here, candidate $n$ is missing.
	    
	    - Candidate $0$ is located at index $n - 1$, taking $n$ comparisons.
	        
	    - Candidate $1$ is located at index $n - 2$, taking $n - 1$ comparisons.
	        
	    - Candidate $c$ takes $n - c$ comparisons.
	        
	    - Candidate $n - 1$ takes $1$ comparison.
	        
	    - Candidate $n$ is absent, scanning all $n$ elements ($n$ comparisons).
	        
	    - Total comparisons = $\sum_{k=1}^{n} k + n = \frac{n(n + 1)}{2} + n = \Theta(n^2)$.
2. **How does the work change when zero is missing instead of $n$? Explain why input arrangement and the missing value can affect measurements.**

	- **When $0$ is missing**: The outer loop evaluates candidate $c = 0$ first. It performs a single linear scan of all $n$ elements, detects that $0$ is absent, and returns $0$ immediately. The total work is exactly $n$ comparisons ($\Theta(n)$ time), irrespective of how non-zero elements are ordered.
	    
	- **When $n$ is missing**: The outer loop must search for all preceding candidates $0, 1, \dots, n - 1$ before testing $n$. It performs $n + 1$ separate searches.
	    
	- **Why arrangements and values affect measurements**:
	    
	    - **Missing value position**: Lower missing values terminate the outer candidate loop early, while higher missing values force more search iterations.
	        
	    - **Element arrangement**: Each successful search terminates at the earliest index $i$ where $\text{arr}[i] == c$. If elements matching early candidates are clustered near the front of $\text{arr}$, those searches finish in $O(1)$ operations; if placed near the end, they take $O(n)$ operations each.
3. **Analyze the total time and auxiliary space of the presence-array approach.**

	- **Total Time**: $\Theta(n)$.
	    
	    - _Array allocation and zeroing_: The JVM allocates $n + 1$ booleans and zero-initializes them to `false` in $\Theta(n)$ time.
	        
	    - _Marking phase_: Iterating through $\text{arr}$ and assigning `present[arr[i]] = true` takes $n$ constant-time operations ($\Theta(n)$ time).
	        
	    - _Scan phase_: Inspecting `present` from index $0$ to $m$ takes $m + 1$ checks ($O(n)$ time).
	        
	    - Summing all phases yields $\Theta(n) + \Theta(n) + O(n) = \Theta(n)$.
	        
	- **Auxiliary Space**: $\Theta(n)$. The algorithm allocates one auxiliary array of length $n + 1$, requiring $(n + 1)$ bytes of heap space plus scalar loop indices.
4. **Why does the presence array need $n + 1$ positions? Which input constraint makes using a value as an index safe?**

	- **Why $n + 1$ positions are required**: The domain of candidate values is the inclusive range $[0, n]$, which contains exactly $(n - 0) + 1 = n + 1$ distinct possible integers. To establish a direct mapping where value $v$ maps to index $v$, the array must contain valid indices from $0$ up to $n$. An array of size $n$ only spans indices $0$ to $n - 1$; if the value $n$ were present, accessing index $n$ would throw an `ArrayIndexOutOfBoundsException`.
	    
	- **Safe indexing constraint**: The guarantee that all integers come from the _inclusive range $0$ through $n$_. This guarantees $0 \le \text{arr}[i] \le n$ for all $i$, ensuring every value is guaranteed to be a valid index in `present[0 ... n]` without underflow or overflow.
5. **What would need to change if values could be arbitrary negative or very large integers? Would a directly indexed array still be appropriate?**

	- **Why direct indexing fails**:
	    
	    - Direct indexing cannot handle negative integers because array indices in Java must be non-negative ($\ge 0$).
	        
	    - For very large integers (e.g., values up to $10^9$ or `Integer.MAX_VALUE`), direct indexing would require an array with billions of entries, triggering `OutOfMemoryError` and violating auxiliary-space constraints.
	        
	- **Required algorithmic changes**:
	    
	    - Direct indexing must be replaced by a hash set (`HashSet<Integer>`).
	        
	    - Elements are inserted into the hash set in $O(n)$ expected time, and membership queries are answered in $O(1)$ average time.
	        
	    - The auxiliary space would then scale strictly with the number of present elements $\Theta(n)$ rather than the numerical span of the values.
6. **Why are both the distinctness and range guarantees important to the statement that exactly one value is missing?**

	- **Mathematical justification (Pigeonhole Principle)**:
	    
	    - The universe of possible values is $U = \{0, 1, 2, \dots, n\}$, which contains $\vert{}U\vert{} = n + 1$ elements.
	        
	    - The array contains $\vert{}\text{arr}\vert{} = n$ elements.
	        
	- **Role of the Distinctness Guarantee**: Because all $n$ entries in $\text{arr}$ are strictly distinct, the array covers exactly $n$ unique values from $U$. If distinctness were violated (e.g., duplicate values existed), the array would cover fewer than $n$ unique values, leaving _two or more_ values missing from $U$.
	    
	- **Role of the Range Guarantee**: Every element of $\text{arr}$ is guaranteed to be an element of $U$ ($\text{arr} \subseteq U$). If elements were allowed to fall outside $[0, n]$ (e.g., negative numbers or numbers $> n$), an invalid element would occupy an array slot without covering any element of $U$, again leaving _two or more_ values in $[0, n]$ missing.
	    
	- Both conditions together enforce $\vert{}\text{arr} \cap U\vert{} = n$ distinct items, ensuring that exactly $\vert{}U\vert{} - \vert{}\text{arr} \cap U\vert{} = (n + 1) - n = 1$ value is missing.

### Test Cases and Verification

|**Case Type**|**Input Array (arr)**|**Expected Output**|**Approach A Actual**|**Approach B Actual**|**Status**|
|---|---|---|---|---|---|
|**Normal Case**|`[3, 0, 4, 1]`|`2`|`2`|`2`|PASS|
|**Boundary Case (Empty)**|`[]`|`0`|`0`|`0`|PASS|
|**Boundary Case ($n=1$, missing 1)**|`[0]`|`1`|`1`|`1`|PASS|
|**Boundary Case ($n=1$, missing 0)**|`[1]`|`0`|`0`|`0`|PASS|
|**Boundary Case (Missing $n$)**|`[0, 1, 2, 3]`|`4`|`4`|`4`|PASS|
|**Tricky Case (Missing 0)**|`[4, 2, 1, 3]`|`0`|`0`|`0`|PASS|
|**Adversarial Case (Reversed, Missing $n$)**|`[3, 2, 1, 0]`|`4`|`4`|`4`|PASS|

---

## Problem 4 - Find the First Unique Position
### Algorithm Explanation
The goal is to determine the smallest index $i$ ($0 \le i < n$) whose value occurs exactly once in the entire array, returning $-1$ if no such element exists. Two distinct algorithms were implemented.

#### Approach A (Repeated Counting)
- **Mechanism**: Evaluates candidate index positions from left to right ($i = 0, 1, \dots, n - 1$).
    
- **Execution**: For each candidate position $i$, an inner loop scans the entire array from $j = 0$ to $n - 1$ to compute the global occurrence count of $\text{arr}[i]$. If the count equals $1$, index $i$ is returned immediately. If all candidates are evaluated without success, the algorithm returns $-1$.

#### Approach B (Frequency Map and Ordered Scan):
- **Mechanism**: Employs a two-pass strategy decoupling frequency counting from index-order evaluation:

- **Pass 1 (Frequency Map Construction)**: Iterates across the array, storing the occurrence counts of each distinct value in a `HashMap<Integer, Integer>`.
    
- **Pass 2 (Ordered Scan)**: Iterates sequentially through the array from index $0$ to $n - 1$, checking the frequency of each element via map lookups. The first index $i$ satisfying $\text{freqMap.get(arr}[i]\text{)} == 1$ is returned immediately. If no element has frequency 1, the method returns $-1$.

### Complexity Analysis
Let $n$ denote the length of the input array, and let $d$ denote the number of distinct values present ($1 \le d \le n$). Per the laboratory guidelines, all hash-based operations assume average constant-time $O(1)$ complexity under suitable hashing with amortized insertion cost, rather than an unconditional worst-case guarantee.

#### Approach A: Repeated Counting
- **Time Complexity**:
    
    - **Best Case**: $\Theta(n)$. Occurs when the first element $\text{arr}[0]$ is globally unique. Candidate $i = 0$ completes an inner scan of $n$ elements, detects $\text{count} == 1$, and returns immediately.
        
    - **Worst Case**: $\Theta(n^2)$. Occurs when no element is unique (e.g., all values paired or all identical), or when the sole unique value is located at the final index $n - 1$. The outer loop examines all $n$ candidates, each requiring a full scan of $n$ elements, totaling $n \times n = n^2$ comparisons.
        
    - **Average Case**: $\Theta(n^2)$ when unique values are located in the latter half of the array or are absent.
        
- **Auxiliary-Space Complexity**: $O(1)$. Requires only scalar control variables (`i`, `j`, `count`) without auxiliary heap allocations.

#### Approach B: Frequency Map and Ordered Scan
- **Time Complexity**:
    
    - **Pass 1 (Map Population)**: Performs $n$ insertions and updates. Under the assumption of suitable hashing, each insertion/update operates in average $O(1)$ amortized time, requiring $\Theta(n)$ average time for the pass.
        
    - **Pass 2 (Ordered Scan)**: Scans the array in index order, performing an average $O(1)$ map lookup for each examined position. This pass takes at most $O(n)$ time (and $\Theta(1)$ best-case time if index $0$ is unique).
        
    - **Total Time**: $\Theta(n)$ on average across all cases (best, average, worst), dominated by the mandatory linear pass to construct the frequency map.
        
- **Auxiliary-Space Complexity**: $\Theta(d)$. The `HashMap` stores exactly $d$ entries, where $d \le n$. In the worst case where all elements are distinct ($d = n$), auxiliary space is $\Theta(n)$.

### Algorithm Analysis Table

|**Criterion**|**Approach A (Repeated Counting)**|**Approach B (Frequency Map & Ordered Scan)**|
|---|---|---|
|**Main idea**|For each candidate index, scan the entire array to count its frequency; return on the first count of 1.|Build a `HashMap` of frequencies in Pass 1; scan original array in Pass 2 to return the first index with frequency 1.|
|**Time complexity**|Best: $\Theta(n)$, Worst: $\Theta(n^2)$, Average: $\Theta(n^2)$|Best/Average: $\Theta(n)$ (under average $O(1)$ hashing assumptions)|
|**Auxiliary-space complexity**|$O(1)$|$\Theta(d)$, where $d$ is the number of distinct values ($1 \le d \le n$)|
|**Advantages**|Zero heap allocation ($O(1)$ space); early return on index 0 can beat hashing constant factors.|Optimal linear runtime $\Theta(n)$ on average; scales gracefully to large arrays without quadratic slowdown.|
|**Disadvantages**|Severe quadratic bottleneck $\Theta(n^2)$ when unique items are late or absent.|Heap memory overhead for $d$ entries; relies on hashing performance assumptions; boxing overhead.|
|**Suitable when**|Memory is strictly constrained ($O(1)$ auxiliary space) and array size is tiny ($n \le 1{,}000$).|Large arrays ($n$ up to $100{,}000$) where time efficiency is critical.|

### Chosen for Most Cases: Approach B

Under the lab constraints ($n \le 100{,}000$ integers), **Approach B (Frequency Map and Ordered Scan)** is the superior choice. For $n = 100{,}000$, Approach A's worst-case time requires up to $10^{10}$ comparisons, which takes tens of seconds and risks timing out. In contrast, Approach B finishes both passes in milliseconds with $\Theta(n)$ average time, using a manageable memory footprint of at most $100{,}000$ map entries.

#### Problem-Specific Analysis Questions
1. **Analyze the worst-case time and auxiliary space of repeated counting. Give an input on which every candidate must be examined.**

	- **Worst-Case Time and Space**: The worst case requires evaluating all $n$ candidate positions $i \in [0, n - 1]$. For each candidate, the inner loop inspects all $n$ elements from $j = 0$ to $n - 1$. This results in exactly $n \times n = n^2$ total comparison operations, yielding a worst-case time complexity of $\Theta(n^2)$. The auxiliary space is strictly $O(1)$ because only primitive loop counters and accumulators are maintained on the call stack.
	    
	- **Adversarial Input**: Any array where **no element is unique** (or where the only unique element is at the very last index $n - 1$) forces every candidate to be examined. A concrete adversarial input is an array of identical pairs:
	    
	    $$\text{arr} = [1, 1, 2, 2, 3, 3, \dots, k, k]$$
	    
	    Because every element occurs twice, every candidate $i \in [0, n - 1]$ completes its full inner scan of $n$ elements, detects $\text{count} = 2 \ne 1$, and continues. All $n$ candidates are checked without success, executing all $n^2$ iterations before returning $-1$.
2. **Analyze the average total time of the frequency-map approach, including both passes. Let $d$ be the number of distinct values when describing storage.**

	- **Average Total Time**:
	    
	    - _Pass 1 (Map Population)_: Iterates through all $n$ elements. Under the assumption of suitable hashing, each `put` and `getOrDefault` operation takes $O(1)$ average time with amortized insertion cost. The total time for Pass 1 is $\Theta(n)$.
	        
	    - _Pass 2 (Ordered Scan)_: Iterates through the array, performing an average $O(1)$ `get` operation per element. In the worst case, it scans all $n$ elements, requiring $O(n)$ time.
	        
	    - _Total Average Time_: $\Theta(n) + O(n) = \Theta(n)$.
	        
	- **Storage / Auxiliary Space**: The `HashMap` stores exactly one key-value pair for each unique value present in the array. Thus, it stores $d$ entries, where $d \le n$. The auxiliary-space complexity is $\Theta(d)$. When all elements are distinct ($d = n$), space is $\Theta(n)$; when all elements are identical ($d = 1$), space is $\Theta(1)$.
3. **Why is a HashSet containing only distinct values insufficient for the required frequency check?**

	- A standard `HashSet` only records binary presence (membership vs. absence) and discards occurrence counts. When an element is inserted into a `HashSet`, subsequent additions are ignored.
	    
	- Consequently, a single `HashSet` cannot differentiate between an element that appears exactly once in the entire array and an element that appears multiple times; both simply appear as present in the set.
	    
	- Furthermore, tracking uniqueness requires a global property: a value seen once _so far_ during a forward scan cannot be declared unique, as duplicate instances may appear later in the array. A frequency map (or two sets: one for seen elements and one for duplicates) is required to determine whether an element's global frequency is strictly 1.
4. **Why must the second pass follow the original array order rather than return an arbitrary value with frequency one?**

	- The problem specifically demands the **smallest index** (the first position) whose value occurs exactly once.
	    
	- A `HashMap` is an unordered collection whose iteration order depends on hash codes and internal bucket chains, bearing no relationship to the original array indices. Iterating over the map's keys and picking an arbitrary key with frequency 1 could yield a unique value that appears later in the array rather than the one appearing at the minimal index.
	    
	- Scanning the original array from index $0$ to $n - 1$ guarantees that candidate positions are evaluated in strict ascending index order, ensuring that the first match found corresponds to the minimum index.
5. **How much work does each required approach perform when every array value is distinct? Explain any early return.**

	- **Approach A (Repeated Counting)**:
	    
	    - Evaluates candidate $i = 0$.
	        
	    - The inner loop scans all $n$ elements ($j = 0$ to $n - 1$) and finds that $\text{arr}[0]$ occurs only once ($\text{count} == 1$).
	        
	    - It immediately executes an early return of index `0`.
	        
	    - **Total Work**: Exactly $1$ outer iteration and $n$ comparisons, completing in $\Theta(n)$ time and $O(1)$ space.
	        
	- **Approach B (Frequency Map and Ordered Scan)**:
	    
	    - _Pass 1_: Cannot return early; it must unconditionally process all $n$ elements to insert $n$ entries into the map, establishing that each has frequency 1. This takes $\Theta(n)$ time.
	        
	    - _Pass 2_: Checks index $i = 0$, queries `freqMap.get(arr[0]) == 1`, and immediately executes an early return of index `0`.
	        
	    - **Total Work**: $n$ map insertions followed by $1$ map lookup. Although asymptotically $\Theta(n)$, Approach B performs significantly more work in practice than Approach A for this specific case due to hashing, heap allocations, and object boxing overhead.
6. **How does returning an index avoid confusing the legitimate data value −1 with the no-result marker?**

	- The specification states that array values can range from $-1{,}000{,}000$ to $1{,}000{,}000$, meaning $-1$ is a valid data element that could itself be the unique value (as demonstrated in test case `[-1, 5, -1, 3, 5]` where the unique value is $3$, but $-1$ could just as easily appear once).
	    
	- Valid array indices, however, are strictly non-negative integers bounded by $0 \le i < n$.
	    
	- By defining the return type as the **index** rather than the data value, successful outcomes always yield a non-negative integer ($\ge 0$). This reserves $-1$ as an unambiguous, out-of-band sentinel indicating that no unique value exists, preventing any collision between valid return values and the failure sentinel.

### Test Cases and Verification

|**Case Type**|**Input Array (arr)**|**Expected Output**|**Approach A Actual**|**Approach B Actual**|**Status**|
|---|---|---|---|---|---|
|**Normal Case**|`[6, 2, 6, 4, 2, 9, 4]`|`5`|`5`|`5`|PASS|
|**Boundary Case (Empty)**|`[]`|`-1`|`-1`|`-1`|PASS|
|**Boundary Case (Single)**|`[42]`|`0`|`0`|`0`|PASS|
|**Boundary Case (All-Identical)**|`[7, 7, 7, 7]`|`-1`|`-1`|`-1`|PASS|
|**Tricky Case (Data contains -1)**|`[-1, 5, -1, 3, 5]`|`3`|`3`|`3`|PASS|
|**Tricky Case (All Distinct)**|`[10, 20, 30, 40]`|`0`|`0`|`0`|PASS|
|**Adversarial Case (Unique at End)**|`[1, 1, 2, 2, 3, 3, 4]`|`6`|`6`|`6`|PASS|

---

## Problem 5 - Sum of All Subarray Sums
### Algorithm Explanation
The task calculates the grand total of all nonempty contiguous subarray sums:

$$\text{Total} = \sum_{s=0}^{n-1} \sum_{e=s}^{n-1} \sum_{k=s}^{e} \text{arr}[k]$$

Per the laboratory guidelines, all intermediate subarray sums, contributions, and return values are maintained using 64-bit `long` precision. Two distinct algorithmic approaches were implemented.

#### Approach A (Nested-Loop Accumulation)
- **Mechanism**: Fixes a starting index $s \in [0, n - 1]$ in an outer loop. An inner loop progressively increments the ending index $e$ from $s$ to $n - 1$.
    
- **Execution**: Instead of using a third loop to recompute each subarray sum from scratch, the algorithm maintains a running sum $\text{currentSubarraySum} = \sum_{k=s}^{e} \text{arr}[k]$ by executing `currentSubarraySum += arr[e]` at each step. This running sum is immediately accumulated into `totalSum`, evaluating every contiguous subarray in $O(1)$ operations per subarray.

#### Approach B (Element Contributions)
- **Mechanism**: Leverages the algebraic properties of addition (commutativity and associativity) to rewrite the summation by grouping by element rather than by subarray:
    
    $$\text{Total} = \sum_{i=0}^{n-1} \left( \text{arr}[i] \times \text{count of subarrays containing index } i \right)$$
    
- **Execution**: For each index $i$, any contiguous subarray $\text{arr}[s \dots e]$ containing index $i$ must have a starting index $s \le i$ ($i + 1$ choices) and an ending index $e \ge i$ ($n - i$ choices). The total number of subarrays containing $\text{arr}[i]$ is $(i + 1)(n - i)$. The algorithm performs a single pass from $i = 0$ to $n - 1$, multiplying `arr[i]` by $(i + 1)(n - i)$ using 64-bit integer arithmetic and accumulating the result into `totalSum`.

### Complexity Analysis
Let $n$ denote the length of the input array ($0 \le n \le 10{,}000$).

#### Approach A: Nested-Loop Accumulation
- **Time Complexity**: $\Theta(n^2)$
    
    - The outer loop runs $n$ times ($s = 0, 1, \dots, n - 1$).
        
    - For each starting index $s$, the inner loop executes $n - s$ times ($e = s, s + 1, \dots, n - 1$).
        
    - The total number of inner loop executions is exactly:
        
        $$\sum_{s=0}^{n-1} (n - s) = n + (n - 1) + \dots + 1 = \frac{n(n + 1)}{2} = \frac{n^2 + n}{2}$$
        
    - Each inner step performs one constant-time addition to `currentSubarraySum` and one constant-time addition to `totalSum`. Thus, the time complexity is strictly $\Theta(n^2)$ for all non-empty arrays ($n \ge 1$) and $\Theta(1)$ when $n = 0$.
        
- **Auxiliary-Space Complexity**: $O(1)$
    
    - Operates entirely in-place, allocating only scalar primitive variables (`totalSum`, `currentSubarraySum`, `s`, `e`) on the call stack.

#### Approach B: Element Contributions
- **Time Complexity**: $\Theta(n)$
    
    - The loop iterates through indices $i = 0$ to $n - 1$ exactly once.
        
    - In each iteration, evaluating $(i + 1)$, $(n - i)$, the product $(i + 1)(n - i)\text{arr}[i]$, and the addition to `totalSum` takes $O(1)$ primitive arithmetic operations.
        
    - The overall runtime is strictly linear $\Theta(n)$ for $n \ge 1$, and $\Theta(1)$ when $n = 0$.
        
- **Auxiliary-Space Complexity**: $O(1)$
    
    - Requires only scalar tracking variables (`totalSum`, `startChoices`, `endChoices`, `occurrences`, `elementContribution`) without allocating extra memory or data structures.

### Algorithm Analysis Table

|**Criterion**|**Approach A (Nested-Loop Accumulation)**|**Approach B (Element Contributions)**|
|---|---|---|
|**Main idea**|Enumerate all subarrays using nested loops; maintain running sum during ending index extension.|Compute each element's total subarray occurrences combinatorially in a single linear pass.|
|**Time complexity**|Best/Average/Worst: $\Theta(n^2)$|Best/Average/Worst: $\Theta(n)$|
|**Auxiliary-space complexity**|$O(1)$|$O(1)$|
|**Advantages**|Conceptually direct; verifies each concrete subarray sum sequentially without combinatorial formulas.|Optimal linear runtime $\Theta(n)$; eliminates explicit subarray enumeration; scales effortlessly to large $n$.|
|**Disadvantages**|Quadratic operation count $\Theta(n^2)$; requires $\approx 5 \times 10^7$ iterations at $n = 10{,}000$.|Requires formal algebraic deduction of index combinations; sensitive to 32-bit overflow before multiplication.|
|**Suitable when**|Educational inspection of individual subarray sums or tiny inputs ($n \le 1{,}000$).|All production scenarios, large arrays ($n$ up to $10{,}000$ or beyond), and runtime-critical pipelines.|

### Chosen for Most Cases: Approach B
Under the stated constraints ($0 \le n \le 10{,}000$, values between $-1{,}000{,}000$ and $1{,}000{,}000$), Approach B (Element Contributions) is the definitively superior choice. For $n = 10{,}000$, Approach A must execute $\frac{10{,}000 \times 10{,}001}{2} \approx 50{,}005{,}000$ loop iterations, incurring measurable latency. In contrast, Approach B executes in strictly $10{,}000$ operations ($\Theta(n)$), executing in under a single millisecond with identical $O(1)$ auxiliary space. 

### Problem-Specific Analysis Questions
1. **How many nonempty subarrays exist? Use this count to analyze the time of Approach A and state its auxiliary space.**
    
    - A nonempty contiguous subarray is uniquely identified by choosing a starting index $s$ and an ending index $e$ such that $0 \le s \le e \le n - 1$.
        
    - The total number of valid index pairs $(s, e)$ is:
        
        $$\sum_{s=0}^{n-1} (n - s) = \frac{n(n + 1)}{2} = \frac{n^2 + n}{2}$$
        
    - In Approach A, each iteration of the inner loop processes exactly one such index pair $(s, e)$, performing an incremental addition to `currentSubarraySum` and accumulating into `totalSum`. Because exactly $\frac{n(n + 1)}{2}$ pairs are visited and each step takes $O(1)$ operations, the total time complexity is $\Theta(n^2)$.
        
    - The auxiliary-space complexity is $O(1)$ as only scalar counters and accumulators are stored.
1. **For an index $i$, how many starting positions and ending positions can form a subarray containing $a[i]$? Derive its contribution.**

	- For an element at index $i$ to be contained within a subarray $\text{arr}[s \dots e]$:
	    
	    - The start index $s$ must be chosen such that $0 \le s \le i$. There are $i + 1$ independent choices.
	        
	    - The end index $e$ must be chosen such that $i \le e \le n - 1$. There are $(n - 1) - i + 1 = n - i$ independent choices.
	        
	- By the fundamental counting principle, the number of distinct subarrays spanning index $i$ is:
	    
	    $$\text{occurrences}(i) = (i + 1) \times (n - i)$$
	    
	- Because $\text{arr}[i]$ appears in each of these subarrays, its total contribution to the grand total of all subarray sums is:
	    
	    $$\text{contribution}(i) = (i + 1) \cdot (n - i) \cdot \text{arr}[i]$$
3. **Analyze the time and auxiliary space of Approach B. Why can it avoid generating the subarrays explicitly?**

	- **Time and auxiliary space**: Approach B runs a single loop from $i = 0$ to $n - 1$. Each iteration computes $(i + 1)(n - i)\text{arr}[i]$ and adds it to `totalSum` in $O(1)$ primitive steps, yielding $\Theta(n)$ total time. It requires $O(1)$ auxiliary space because it only uses a few 64-bit scalar variables.
	    
	- **Why explicit subarray generation is avoided**: Because addition is commutative and associative, the sum over all subarrays of their internal elements can be rearranged:
	    
	    $$\sum_{\text{all subarrays } S} \left( \sum_{x \in S} x \right) = \sum_{i=0}^{n-1} \left( \sum_{S \text{ containing } i} \text{arr}[i] \right) = \sum_{i=0}^{n-1} \left( \text{arr}[i] \times \vert{}\{S : i \in S\}\vert{} \right)$$
	    
	    Since combinatorics determines $\vert{}\{S : i \in S\}\vert{} = (i + 1)(n - i)$ directly in $O(1)$ time, the algorithm bypasses the need to enumerate individual subarrays.
4. **Why does this method remain correct when values are negative or zero? What should a one-element array return?**

	- **Negative or zero values**:
	    
	    - The frequency factor $(i + 1)(n - i)$ depends solely on index geometry and array length $n$, not on the numerical values stored in $\text{arr}$.
	        
	    - By the distributive law of multiplication over addition, summing a negative value $-v$ across $k$ separate subarrays is mathematically equivalent to adding $k \times (-v) = -k \cdot v$ directly to the total sum.
	        
	    - If $\text{arr}[i] = 0$, its contribution is $(i + 1)(n - i) \times 0 = 0$, reflecting that adding zero to any subarray sum leaves the total unchanged.
	        
	- **One-element array ($n = 1$, `arr = [x]`)**:
	    
	    - For $i = 0$, start choices $= 0 + 1 = 1$, and end choices $= 1 - 0 = 1$.
	        
	    - Total contribution $= 1 \times 1 \times x = x$.
	        
	    - Thus, it returns the value of that single element as a `long` (`(long) arr[0]`).
5. **Why may an int calculation overflow even when the final variable is a long? Explain why the stated size and value limits keep the total within long range.**

	- **Why 32-bit `int` calculations overflow**:
	    
	    - In Java, expression evaluation types depend strictly on the static types of the operands, regardless of the destination variable's type.
	        
	    - If $i$, $n$, and $\text{arr}[i]$ are `int`, the expression `(i + 1) * (n - i) * arr[i]` is computed entirely in 32-bit signed integer arithmetic.
	        
	    - If the true value exceeds $[ -2^{31}, 2^{31} - 1 ]$ ($[-2{,}147{,}483{,}648, 2{,}147{,}483{,}647]$), the product wraps around and truncates before it is promoted and assigned to the 64-bit `long` variable.
	        
	    - To prevent this, at least one operand must be cast to `long` before multiplication: `(long) (i + 1) * (n - i) * arr[i]`.
	        
	- **Why the total remains within `long` range**:
	    
	    - Input constraints specify $n \le 10{,}000$ and $\vert{}\text{arr}[i]\vert{} \le 1{,}000{,}000 = 10^6$.
	        
	    - The total sum of all contributions across all indices is bounded by:
	        
	        $$\text{Max Total} \le \max \vert{}\text{arr}[i]\vert{} \times \sum_{i=0}^{n-1} (i + 1)(n - i)$$
	        
	    - Evaluating the sum of products of linear factors:
	        
	        $$\sum_{i=0}^{n-1} (i + 1)(n - i) = \sum_{k=1}^{n} k(n - k + 1) = \frac{n(n + 1)(n + 2)}{6}$$
	        
	    - For $n = 10{,}000$:
	        
	        $$\frac{10{,}000 \times 10{,}001 \times 10{,}002}{6} = \frac{1{,}000{,}300{,}020{,}000}{6} \approx 1.667 \times 10^{11}$$
	        
	    - Multiplying by the maximum magnitude $\vert{}\text{arr}[i]\vert{} \le 10^6$:
	        
	        $$\text{Max Magnitude} \approx 1.667 \times 10^{11} \times 10^6 = 1.667 \times 10^{17}$$
	        
	    - In Java, a signed 64-bit `long` spans up to $2^{63} - 1 \approx 9.22 \times 10^{18}$.
	        
	    - Because $1.667 \times 10^{17} < 9.22 \times 10^{18}$, the total cannot overflow a 64-bit `long`.

### Test Cases and Verification

|**Case Type**|**Input Array (arr)**|**Expected Output**|**Approach A Actual**|**Approach B Actual**|**Status**|
|---|---|---|---|---|---|
|**Normal Case**|`[2, -1, 3]`|`11`|`11`|`11`|PASS|
|**Boundary Case (Empty)**|`[]`|`0`|`0`|`0`|PASS|
|**Boundary Case (Single Positive)**|`[42]`|`42`|`42`|`42`|PASS|
|**Boundary Case (Single Negative)**|`[-5]`|`-5`|`-5`|`-5`|PASS|
|**Tricky Case (All Zeros)**|`[0, 0, 0]`|`0`|`0`|`0`|PASS|
|**Tricky Case (Mixed Values)**|`[-2, 0, 5, -1]`|`18`|`18`|`18`|PASS|
|**Tricky Case (Large Magnitudes)**|`[1000000, 1000000, 1000000]`|`10000000`|`10000000`|`10000000`|PASS|

---

## Problem 6 – Interleave Two Array Halves
### Algorithm Explanation
Given an array of even length $n = 2m$ structured as $[x_1, \dots, x_m, y_1, \dots, y_m]$, the task rearranges the elements in-place into $[x_1, y_1, x_2, y_2, \dots, x_m, y_m]$ without changing the relative order within either half. For arrays of length $n \le 2$, the elements are already in their correct interleaved order, so both approaches return immediately without allocating memory. Two distinct algorithmic approaches were implemented.

#### Approach A (Auxiliary Array)
- **Mechanism**: Allocates an auxiliary buffer `aux` of size $n$.
        
- **Execution**:
	
	1. **Interleaving Pass**: Iterates through $i \in [0, m - 1]$, mapping each element $x_{i+1}$ from $\text{arr}[i]$ to the even destination index `aux[2 * i]`, and each element $y_{i+1}$ from $\text{arr}[m + i]$ to the odd destination index `aux[2 * i + 1]`.
		
	2. **Copy-Back Pass**: Copies all $n$ elements from `aux` back into the original input array `arr` using `System.arraycopy`, modifying it in-place.

#### Approach B (In-Place Ordered Insertions)
- **Mechanism**: Sequentially inserts each element of the second half into its destination position immediately following its corresponding first-half partner, displacing intervening elements rightward without allocating extra heap space.
        
- **Execution**: For each insertion step $k \in [0, m - 2]$:
	
	1. The next unplaced second-half element $y_{k+1}$ currently resides at index $\text{source} = m + k$. Its value is saved in a scalar temporary variable `temp`.
		
	2. The target destination is $\text{target} = 2k + 1$ (the slot immediately following $x_{k+1}$ at $2k$).
		
	3. A right-to-left shift moves all intervening elements at indices from $\text{target}$ to $\text{source} - 1$ one position to the right into slots $[\text{target} + 1, \text{source}]$.
		
	4. `temp` is written into `arr[target]`. The remaining element pair $(x_m, y_m)$ naturally occupies the final two indices without requiring a shift.

### Complexity Analysis
Let $n$ denote the length of the array, and let $m = \frac{n}{2}$ denote the size of each half.

#### Approach A: Auxiliary Array
- **Time Complexity**: $\Theta(n)$
    
    - **Allocation**: The JVM allocates an integer buffer of length $n$ in $\Theta(n)$ time.
        
    - **Interleaving Pass**: Exactly $m$ loop iterations perform two indexed reads and two indexed writes each, taking $4m = 2n$ operations ($\Theta(n)$ time).
        
    - **Copy-Back Pass**: Copying $n$ contiguous words back to `arr` executes in $\Theta(n)$ time.
        
    - **Total Time**: $\Theta(n)$ across all cases (best, average, worst).
        
- **Auxiliary-Space Complexity**: $\Theta(n)$
    
    - Allocates an additional `int[n]` buffer on the heap, requiring $4n$ bytes of auxiliary memory.

#### Approach B: In-Place Ordered Insertions
- **Time Complexity**: $\Theta(n^2)$
    
    - **Shift Count**: As derived in question 2, insertion $k$ shifts $(m - 1 - k)$ elements. Across all $m - 1$ insertions, the total number of element shift assignments is:
        
        $$\sum_{k=0}^{m-2} (m - 1 - k) = \frac{m(m - 1)}{2} = \frac{\frac{n}{2}\left(\frac{n}{2} - 1\right)}{2} = \frac{n(n - 2)}{8} = \frac{n^2 - 2n}{8}$$
        
    - Each shift executes one read and one write operation.
        
    - Since positional shifting must occur regardless of data values, the time complexity is strictly $\Theta(n^2)$ across best, average, and worst cases for $n \ge 4$ (and $\Theta(1)$ for $n \le 2$).
        
- **Auxiliary-Space Complexity**: $O(1)$
    
    - Uses only primitive scalar variables (`n`, `m`, `k`, `target`, `source`, `temp`, `j`) on the call stack, maintaining zero auxiliary heap allocations.

### Algorithm Analysis Table

|**Criterion**|**Approach A (Auxiliary Array)**|**Approach B (In-Place Ordered Insertions)**|
|---|---|---|
|**Main idea**|Alternate values into an auxiliary array of size $n$, then copy all elements back to $\text{arr}$.|Sequentially insert each second-half element into position by shifting intervening values rightward.|
|**Time complexity**|Best/Average/Worst: $\Theta(n)$|Best/Average/Worst: $\Theta(n^2)$|
|**Auxiliary-space complexity**|$\Theta(n)$|$O(1)$|
|**Advantages**|Optimal linear runtime $\Theta(n)$; simple implementation; minimal cache-unfriendly branching.|True in-place manipulation; requires strictly $O(1)$ extra memory; no heap allocation.|
|**Disadvantages**|Allocates an extra buffer of size $n$ ($\Theta(n)$ memory overhead); requires two complete passes.|Severe quadratic runtime $\Theta(n^2)$; repeated memory shifting causes major slowdown on large arrays.|
|**Suitable when**|Standard execution environments where memory is available and $n$ is moderate to large ($n \le 100{,}000$).|Embedded or strictly memory-constrained systems where auxiliary heap allocation is forbidden and $n$ is small ($n \le 2{,}000$).|

### Chosen for Most Cases: Approach A
Under the lab constraints ($n \le 100{,}000$ integers), **Approach A (Auxiliary Array)** is the only viable choice. For $n = 100{,}000$, Approach B must execute $\frac{100{,}000 \times 99{,}998}{8} \approx 1.25 \times 10^9$ shift operations, resulting in unacceptable execution times of several seconds or timeouts. Approach A executes strictly in linear time $\Theta(n)$ ($\approx 2 \times 10^5$ memory operations), finishing in a few milliseconds while consuming only $400\text{ KB}$ of auxiliary buffer memory.

### Problem-Specific Analysis Questions
1. **Analyze the time and auxiliary space of the auxiliary-array approach, including copying back.**

	- **Time Complexity**: $\Theta(n)$.
	    
	    - _Array Allocation_: Allocating `int[n]` takes $\Theta(n)$ time to claim and zero-initialize memory.
	        
	    - _Interleaving Pass_: The loop runs $m = \frac{n}{2}$ times. In each iteration, reading $\text{arr}[i]$ and $\text{arr}[m + i]$ and writing them to `aux[2 * i]` and `aux[2 * i + 1]` takes $O(1)$ operations, totaling $\Theta(n)$ time.
	        
	    - _Copy-Back Pass_: Copying all $n$ integers from `aux` back to `arr` takes $\Theta(n)$ time.
	        
	    - _Total Time_: $\Theta(n) + \Theta(n) + \Theta(n) = \Theta(n)$ across best, average, and worst cases.
	        
	- **Auxiliary Space**: $\Theta(n)$. Allocates a dedicated array of size $n$ on the heap, requiring $\Theta(n)$ auxiliary space beyond the scalar loop counters.
2. **Trace the ordered insertions on the example. How many values must be shifted for the first insertion, the next insertion, and so on? Use $m = n/2$ to derive the total shifting work.**

	- **Trace on Example**: Array $[1, 2, 3, 10, 20, 30]$ ($n = 6$, $m = 3$). Halves are $X = [1, 2, 3]$ and $Y = [10, 20, 30]$.
	    
	    - **Initial Array**: `[1, 2, 3, 10, 20, 30]`
	        
	    - **Insertion 1 ($k = 0$)**: Target index is $2(0) + 1 = 1$. Source index is $m + 0 = 3$ (value $10$).
	        
	        - Save $\text{temp} = 10$.
	            
	        - Shift intervening elements at indices $1$ and $2$ rightward into indices $2$ and $3$: `arr[3] = arr[2]` ($3$), `arr[2] = arr[1]` ($2$).
	            
	        - **Values shifted**: $2$ values ($m - 1 = 3 - 1 = 2$).
	            
	        - Write `arr[1] = 10`.
	            
	        - **Array state**: `[1, 10, 2, 3, 20, 30]`
	            
	    - **Insertion 2 ($k = 1$)**: Target index is $2(1) + 1 = 3$. Source index is $m + 1 = 4$ (value $20$).
	        
	        - Save $\text{temp} = 20$.
	            
	        - Shift intervening element at index $3$ rightward into index $4$: `arr[4] = arr[3]` ($3$).
	            
	        - **Values shifted**: $1$ value ($m - 2 = 3 - 2 = 1$).
	            
	        - Write `arr[3] = 20`.
	            
	        - **Array state**: `[1, 10, 2, 20, 3, 30]`
	            
	    - **Insertion 3 ($k = 2$)**: Target index is $5$, source index is $5$ (value $30$).
	        
	        - **Values shifted**: $0$ values ($m - 3 = 0$). Pair $(x_3, y_3) = (3, 30)$ is already at indices $4$ and $5$.
	            
	- **Derivation of Total Shifting Work**:
	    
	    - In step $k$ ($0 \le k \le m - 2$), target is $2k + 1$ and source is $m + k$. The number of shifted elements is:
	        
	        $$\text{source} - \text{target} = (m + k) - (2k + 1) = m - 1 - k$$
	        
	    - Summing across all $k = 0, 1, \dots, m - 2$:
	        
	        $$\text{Total Shifts} = \sum_{k=0}^{m-2} (m - 1 - k) = (m - 1) + (m - 2) + \dots + 1 = \frac{m(m - 1)}{2} = \frac{n(n - 2)}{8}$$
	        
	    - For $m = 3$ ($n = 6$), total shifting work is $\frac{3(2)}{2} = 3$ shifts.
3. **Analyze the time and auxiliary space of the required in-place approach. Explain the trade-off compared with Approach A.**

	- **Time Complexity**: $\Theta(n^2)$. The algorithm executes $\frac{n(n - 2)}{8}$ individual element shifts. Since shifting operations dominate and are independent of value comparisons, time complexity is $\Theta(n^2)$ in all cases.
	    
	- **Auxiliary Space**: $O(1)$. Operates with zero heap allocations, using only primitive scalars (`temp`, loop variables).
	    
	- **Trade-off compared with Approach A**:
	    
	    - Approach A trades memory for speed: it consumes $\Theta(n)$ auxiliary space to deliver an optimal linear runtime $\Theta(n)$.
	        
	    - Approach B trades speed for memory: it eliminates auxiliary heap allocations entirely ($O(1)$ space), but this requires quadratic time $\Theta(n^2)$ due to repeated element shifting. At $n = 100{,}000$, Approach B incurs over $10^9$ operations, whereas Approach A finishes in $\approx 2 \times 10^5$ operations.
4. **Why must the selected value be saved before shifting? Why does the shift proceed from right to left?**
    
    - **Why the selected value must be saved**: The rightward shift copies element `arr[source - 1]` into index `source`. If `arr[source]` were not stored in `temp` prior to this step, its value would be permanently overwritten and lost before being moved into its destination slot.
        
    - **Why shifting must proceed from right to left**: Shifting rightward moves each element from position $j - 1$ to position $j$. If processed left-to-right (from $\text{target}$ upward), writing `arr[target]` into `arr[target + 1]` would overwrite the original contents of `arr[target + 1]` before they could be read, causing the value at `arr[target]` to overwrite the rest of the array. Traversing right-to-left (from $j = \text{source}$ down to $\text{target} + 1$) guarantees that destination slot $j$ is filled only after its current value has already been safely shifted into $j + 1$.
5. **How does your solution preserve the relative order within both halves, including when values repeat?**

	- **First Half Elements ($x_1, \dots, x_m$)**: When an element $y_k$ is inserted at position $2k + 1$, all unplaced first-half elements located between $\text{target}$ and $\text{source} - 1$ are shifted together by exactly one position to the right. Because they shift as a uniform contiguous block without changing relative offsets, their relative order is strictly preserved.
	    
	- **Second Half Elements ($y_1, \dots, y_m$)**: Elements from the second half are selected and placed in strict increasing order of their original indices: $y_1$ is placed first, then $y_2$, and so forth.
	    
	- **Handling Repeating Values**: The algorithm is purely structural and index-driven; it performs no magnitude comparisons or value-dependent swaps. Identical values maintain their exact relative index order throughout the transformation, ensuring positional stability.
6. **Does using less auxiliary memory necessarily mean using less time? Base your answer on these two required algorithms, not a claim about every possible in-place algorithm.**
    
    - **No**, using less auxiliary memory does not imply using less time. In fact, these two algorithms demonstrate an inverse relationship.
        
    - Approach A uses $\Theta(n)$ auxiliary space and completes in linear time $\Theta(n)$.
        
    - Approach B reduces auxiliary space to $O(1)$, but this forces the algorithm to execute $\frac{n(n - 2)}{8}$ element shifts, escalating time complexity to $\Theta(n^2)$.
        
    - For an input of $n = 100{,}000$, Approach A completes in mere milliseconds using $\approx 400\text{ KB}$ of RAM, whereas Approach B requires over $1.25 \times 10^9$ shift operations and exhibits severe latency. Restricting auxiliary buffer memory forces the algorithm to expend substantial computational work repeatedly rearranging elements in place.

### Test Cases and Verification

|**Case Type**|**Input Array (arr)**|**Expected Output**|**Approach A Actual**|**Approach B Actual**|**Status**|
|---|---|---|---|---|---|
|**Normal Case**|`[1, 2, 3, 10, 20, 30]`|`[1, 10, 2, 20, 3, 30]`|`[1, 10, 2, 20, 3, 30]`|`[1, 10, 2, 20, 3, 30]`|PASS|
|**Boundary Case ($n = 0$)**|`[]`|`[]`|`[]`|`[]`|PASS|
|**Boundary Case ($n = 2$)**|`[5, 9]`|`[5, 9]`|`[5, 9]`|`[5, 9]`|PASS|
|**Boundary Case ($n = 4$)**|`[1, 2, 3, 4]`|`[1, 3, 2, 4]`|`[1, 3, 2, 4]`|`[1, 3, 2, 4]`|PASS|
|**Tricky Case (All Identical)**|`[7, 7, 7, 7, 7, 7]`|`[7, 7, 7, 7, 7, 7]`|`[7, 7, 7, 7, 7, 7]`|`[7, 7, 7, 7, 7, 7]`|PASS|
|**Tricky Case (Mixed Signs)**|`[-10, -20, 100, 200]`|`[-10, 100, -20, 200]`|`[-10, 100, -20, 200]`|`[-10, 100, -20, 200]`|PASS|
|**Adversarial (Repeated)**|`[1, 1, 2, 2, 1, 1, 2, 2]`|`[1, 1, 1, 1, 2, 2, 2, 2]`|`[1, 1, 1, 1, 2, 2, 2, 2]`|`[1, 1, 1, 1, 2, 2, 2, 2]`|PASS|