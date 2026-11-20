# DSA Lab 03 Report
- Author: Tran Quoc Hoang – BTBTWE24036
- Course: Algorithms & Data Structures (IT013IU)
- For: Lab 03, Version 04

---

## Problem 1 – Cargo Scan Stack
### Main Idea & Correctness Argument
A cargo terminal records container IDs during freight loading. Scans must follow Last-In, First-Out (LIFO) semantics, where the most recently scanned container is undone first.

We implement an `IntStack` using a dynamic resizable array:
1. **Physical Layout & State Invariant:** Elements occupy contiguous array indices `0` through `size - 1` within an internal array `a`. The variable `size` strictly tracks the number of active elements.
2. **Push (`SCAN x`):** If `size == a.length`, the capacity is doubled before inserting. The new container ID `x` is written to `a[size]`, and `size` is incremented.
3. **Pop (`UNDO`):** If `size == 0`, the stack is empty, and the driver outputs `EMPTY`. Otherwise, the value at `a[size - 1]` is retrieved and returned, and `size` is decremented.
4. **Peek (`LATEST`):** If `size == 0`, outputs `EMPTY`. Otherwise, directly inspects and outputs `a[size - 1]` without changing `size`.
5. **Legitimate Value Handling:** Container IDs can be zero or negative. Because emptiness is strictly checked using `size == 0`, zero is never mistakenly interpreted as an empty sentinel.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - `push(x)`: Worst-case $O(n)$ when doubling capacity and copying $n$ elements; amortized $O(1)$ per push over any sequence of $n$ pushes.
  - `pop()`: Worst-case $O(1)$ (direct index access at `size - 1`).
  - `peek()`: Worst-case $O(1)$ (direct index access at `size - 1`).
  - `size()` / `isEmpty()`: Worst-case $O(1)$.
- **Auxiliary-Space Complexity:**
  - Internal working storage is $\Theta(n)$ words of memory.
  - Because capacity doubles from an initial size of 2, physical capacity $C$ after $n$ insertions satisfies $n \le C < 2n$. Unused capacity is at most $n$, maintaining a constant-factor space overhead.

### Analysis Questions
1. **LIFO Invariant:**
   At all times, the top element at index `size - 1` corresponds to the most recently added container that has not yet been undone. Because pushes and pops exclusively occur at the end of the active prefix `0 .. size - 1`, no older elements can be removed or observed ahead of newer elements.
2. **Resizing Cost vs. Amortized $O(1)$ Push:**
   When array capacity is exhausted at size $n$, a new array of capacity $2n$ is allocated, requiring $n$ copy operations ($O(n)$ worst-case time for that specific insertion). However, resizes only occur at power-of-two boundaries ($2, 4, 8, 16, \dots, n$). Across $n$ consecutive pushes, total copying work is:
   $$\sum_{i=1}^{\log_2 n} 2^i < 2n$$
   Distributing these $2n$ copy operations evenly over $n$ pushes contributes $\frac{2n}{n} = 2$ operations per push. Adding the 1 direct assignment gives an amortized cost of $O(1)$ per push.
3. **Total Physical Storage After $n$ Scans:**
   Starting from initial capacity 2, after $n$ scans without undos, the array capacity is $C = 2^{\lceil \log_2 n \rceil + 1}$ for $n \ge 2$. Physical memory is bounded by $2n$ integer elements ($\Theta(n)$ space), with no per-element reference overhead.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Commands**|**Expected Output**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Handout Example)**|8 commands:<br>`SCAN 45`<br>`SCAN 12`<br>`LATEST`<br>`UNDO`<br>`LATEST`<br>`COUNT`<br>`UNDO`<br>`UNDO`|`12`<br>`12`<br>`45`<br>`1`<br>`45`<br>`EMPTY`|`12`<br>`12`<br>`45`<br>`1`<br>`45`<br>`EMPTY`<br><br>Trace: `SCAN 45` (sz=1, cap=2), `SCAN 12` (sz=2, cap=2), `LATEST` -> 12, `UNDO` -> 12 (sz=1), `LATEST` -> 45, `COUNT` -> 1, `UNDO` -> 45 (sz=0), `UNDO` -> `EMPTY`.|
|**Boundary (Empty Command Stream)**|`Q = 0` (no commands)|*(no output)*|*(no output)*|
|**Boundary (Queries on Empty Stack)**|`LATEST`<br>`UNDO`<br>`COUNT`|`EMPTY`<br>`EMPTY`<br>`0`|`EMPTY`<br>`EMPTY`<br>`0`|
|**Tricky (Repeated Resizes)**|`SCAN 10`, `SCAN 20`<br>`SCAN 30` *(cap 2->4)*<br>`SCAN 40`<br>`SCAN 50` *(cap 4->8)*<br>`COUNT`<br>`LATEST`|`5`<br>`50`|`5`<br>`50`<br><br>Trace: `cap` doubled from 2 to 4 on 3rd scan, and from 4 to 8 on 5th scan.|
|**Tricky (Zero and Negative IDs)**|`SCAN 0`<br>`LATEST`<br>`SCAN -999`<br>`LATEST`<br>`UNDO`<br>`LATEST`<br>`UNDO`<br>`UNDO`|`0`<br>`-999`<br>`-999`<br>`0`<br>`0`<br>`EMPTY`|`0`<br>`-999`<br>`-999`<br>`0`<br>`0`<br>`EMPTY`<br><br>(Confirmed: 0 is handled as a valid container ID, not as an empty flag).|
|**Normal (Clear and Reuse Stack)**|`SCAN 100`<br>`UNDO`<br>`LATEST`<br>`SCAN 200`<br>`LATEST`<br>`COUNT`|`100`<br>`EMPTY`<br>`200`<br>`1`|`100`<br>`EMPTY`<br>`200`<br>`1`|

---

## Problem 2 – Route Instruction Undo and Redo
### Main Idea & Correctness Argument
A transport planner enters route instructions as space-delimited text tokens. The system supports full linear editing with undo and redo capabilities:
1. **Two-Stack Architecture:**
   - `history` (`ArrayDeque<String>`): Holds all currently active tokens in chronological order, with the most recently added token at the top.
   - `redo` (`ArrayDeque<String>`): Holds tokens that have been undone, with the most recently undone token at the top ready for restoration.
2. **Operations:**
   - `WRITE x`: Pushes token `x` onto `history`. Because an edit starts a new line of modifications, `redo.clear()` is called immediately to invalidate any previous undone branch.
   - `UNDO`: If `history.isEmpty()`, outputs `NOTHING`. Otherwise, pops the top token from `history` and pushes it onto `redo` (prints nothing).
   - `REDO`: If `redo.isEmpty()`, outputs `NOTHING`. Otherwise, pops the top token from `redo` and pushes it back onto `history` (prints nothing).
   - `SHOW`: If `history.isEmpty()`, outputs `EMPTY`. Otherwise, outputs all tokens from oldest to newest (traversing `history` from bottom to top via `descendingIterator()`), separated by single spaces.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - `write(x)`: $O(1)$ amortized push onto `history`, $O(1)$ clear on `redo`.
  - `undo()`: $O(1)$ (one pop from `history` and one push to `redo`).
  - `redo()`: $O(1)$ (one pop from `redo` and one push to `history`).
  - `show()`: $O(k)$ where $k$ is the number of active tokens in `history`, with $O(L)$ character output work where $L$ is the combined token length ($L \le 30k$).
- **Auxiliary-Space Complexity:**
  - $O(N)$ total space where $N \le 10^5$ is the total number of retained tokens stored across both `history` and `redo`.

### Analysis Questions
1. **Representation of Each Stack:**
   - `history` represents the established itinerary sequence. Its top element is the latest active instruction.
   - `redo` represents the forward branch of instructions that were undone but have not yet been overwritten by a new edit.
2. **Why WRITE Changes the Reachable Future:**
   In an undo/redo model, the redo stack represents a linear future stemming from a past state. Writing a new token creates a diverging branch in the document's history tree. Retaining older redo actions would allow splicing instructions from an abandoned branch onto the new branch, producing inconsistent instructions. Clearing the redo stack ensures linear consistency.
3. **Per-Operation Cost Analysis:**
   Individual stack operations (`push`, `pop`, `peek`, `clear`) run in $O(1)$ time on `ArrayDeque`. The `SHOW` command requires visiting all $k$ active nodes to format the route string, taking $O(k)$ time.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Commands**|**Expected Output**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|10 commands:<br>`WRITE NORTH`<br>`WRITE EAST`<br>`UNDO`<br>`SHOW`<br>`REDO`<br>`SHOW`<br>`UNDO`<br>`WRITE DEPOT`<br>`REDO`<br>`SHOW`|`NORTH`<br>`NORTH EAST`<br>`NOTHING`<br>`NORTH DEPOT`|`NORTH`<br>`NORTH EAST`<br>`NOTHING`<br>`NORTH DEPOT`<br><br>Trace: `UNDO` moved EAST to redo; `SHOW` printed NORTH; `REDO` restored EAST; 2nd `UNDO` moved EAST to redo; `WRITE DEPOT` cleared redo; subsequent `REDO` printed `NOTHING`.|
|**Boundary (Undo on Empty History)**|`UNDO`<br>`SHOW`|`NOTHING`<br>`EMPTY`|`NOTHING`<br>`EMPTY`|
|**Boundary (Redo Before Any Undo)**|`WRITE START`<br>`REDO`<br>`SHOW`|`NOTHING`<br>`START`|`NOTHING`<br>`START`|
|**Normal (Multiple Undos and Redos)**|`WRITE A`<br>`WRITE B`<br>`WRITE C`<br>`UNDO`<br>`UNDO`<br>`SHOW`<br>`REDO`<br>`SHOW`<br>`REDO`<br>`SHOW`|`A`<br>`A B`<br>`A B C`|`A`<br>`A B`<br>`A B C`|
|**Tricky (New Write Clears Redo Branch)**|`WRITE STEP1`<br>`WRITE STEP2`<br>`UNDO`<br>`WRITE STEP3`<br>`REDO`<br>`SHOW`|`NOTHING`<br>`STEP1 STEP3`|`NOTHING`<br>`STEP1 STEP3`<br><br>(Confirmed: STEP2 was discarded from redo when STEP3 was written).|


---

## Problem 3 – Shipping Manifest Bracket Check
### Main Idea & Correctness Argument
A shipping manifest contains parameter delimiters of types `()`, `[]`, and `{}` embedded in ordinary text. Even when opening and closing counts match, nesting order can be violated. We validate correct nesting while ignoring non-delimiter characters.

1. **Stack Invariant:** A `Deque<Character>` stack maintains the sequence of currently open, unmatched opening delimiters from oldest (bottom) to newest (top).
2. **Algorithm:**
   - Scan each line character by character from left to right.
   - If an opening delimiter (`(`, `[`, `{`) is seen, push it onto the stack.
   - If a closing delimiter (`)`, `]`, `}`) is seen:
     - If the stack is empty, there is no matching opener; reject immediately as `INVALID`.
     - Otherwise, pop the top opener. If the closer does not match the type of the top opener (e.g. `)` popped with `[`), reject as `INVALID`.
   - Non-bracket characters are ignored.
   - At the end of the line, accept as `VALID` if and only if the stack is completely empty (no unmatched opening brackets remain). A line containing no brackets is accepted as `VALID`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(N)$ per line, where $N$ is the number of characters in the line. Each character is checked once, and each delimiter is pushed and popped at most once in $O(1)$ time.
- **Auxiliary-Space Complexity:** $O(N)$ worst-case auxiliary space for the stack (e.g. when a line consists entirely of opening brackets).

### Analysis Questions
1. **Why Checking Bracket Counts Does Not Establish Correctness:**
   Counting only the frequencies of each bracket type (e.g. verifying count(`(`) == count(`)`) and count(`[`) == count(`]`)) fails to verify structural nesting. For example, in the string `([)]`, both counts are equal to 1, yet the string is invalid because the brackets interleave rather than nest. A stack enforces that the inner group must close before the enclosing group can close.
2. **Time and Maximum Auxiliary Space:**
   - Time complexity is $O(N)$ for a line of length $N \le 10^5$.
   - Maximum auxiliary space is $O(N)$ when all characters are opening brackets (e.g. $10^5$ opening parentheses).
3. **Preserving Original Character Order:**
   The algorithm processes characters in their natural stream order without sorting or rearranging, ensuring that relative proximity and nesting scope are faithfully evaluated.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input String**|**Expected Output**|**Actual Output & Explanation**|
|---|---|---|---|
|**Normal (Handout Sample 1)**|`route{stop[1](A)}`|`VALID`|`VALID` (Properly nested braces, brackets, and parentheses)|
|**Tricky (Handout Sample 2 - Interleaved)**|`([)]`|`INVALID`|`INVALID` (Closer `)` arrives when `[` is at top)|
|**Normal (Handout Sample 3)**|`manifest[(3)]`|`VALID`|`VALID` (Valid nested parameters)|
|**Tricky (Handout Sample 4 - Unclosed)**|`(()`|`INVALID`|`INVALID` (Unmatched opening `(` remains at end)|
|**Boundary (Handout Sample 5 - No Brackets)**|`cargo text`|`VALID`|`VALID` (Lines with zero delimiters are valid)|
|**Boundary (Empty & Whitespace Lines)**|`""` and `"   "`|`VALID`|`VALID` (Trivially valid)|
|**Boundary (Closer Before Any Opener)**|`")"`, `"]abc"`, `"}route{"`|`INVALID`|`INVALID` (Stack underflow on closer)|
|**Tricky (Deeply Nested Valid)**|`{{{[([()])]}}}`|`VALID`|`VALID` (All 7 layers matched and closed)|
|**Tricky (Deeply Nested Missing One)**|`{{{[([()])]}}`|`INVALID`|`INVALID` (Outer brace left open)|

---

## Problem 4 – Boarding Circular Queue
### Main Idea & Correctness Argument
A bus terminal buffers passenger boarding-ticket IDs in a fixed $K$-slot ring buffer. Tickets must be served in First-In, First-Out (FIFO) arrival order, and newly freed slots must be reused without shifting the array.

1. **State Representation:**
   - Fixed array `int[] a` of length $K$.
   - `front`: The physical array index of the oldest unserved ticket.
   - `size`: The exact number of active tickets currently buffered ($0 \le size \le K$).
2. **Invariant:** The $i$-th logical ticket in the queue ($0 \le i < size$) is stored at physical index $(front + i) \pmod K$.
3. **Operations:**
   - `enqueue(x)`: If `size == K`, the buffer is full; print `FULL`. Otherwise, place $x$ at physical index $(front + size) \pmod K$, and increment `size`.
   - `serve()`: If `size == 0`, buffer is empty; print `EMPTY`. Otherwise, read $a[front]$, advance $front = (front + 1) \pmod K$, and decrement `size`.
   - `front()`: If `size == 0`, print `EMPTY`. Otherwise, read and print $a[front]$ without changing state.
   - `size()`: Print `size`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(1)$ for every operation (`ENQ`, `SERVE`, `FRONT`, `SIZE`). No element shifting or memory reallocation is performed.
- **Auxiliary-Space Complexity:** $O(K)$ space to allocate the physical array of capacity $K$, and $O(1)$ working memory for the integer scalar pointers.

### Analysis Questions
1. **Physical Indices and Logical FIFO Order Through Wrap-Around:**
   When $K = 3$, enqueuing 17, 29, 33 fills slots 0, 1, 2 (`front = 0, size = 3`). Serving 17 advances `front` to index 1 (`size = 2`), freeing physical slot 0. When 46 is enqueued, its position is $(1 + 2) \pmod 3 = 0$. The physical array holds `[46, 29, 33]`, but the logical FIFO order remains strictly $[29, 33, 46]$.
2. **Invariant $a[(front + i) \pmod K]$ and Complexity:**
   Because elements are inserted at $(front + size) \pmod K$ and removed from $front$, every valid element maintains its relative FIFO distance $i$ from `front`. Because index calculations use modular arithmetic, all updates execute in strict $O(1)$ worst-case time.
3. **Why an Unoccupied Slot Does Not Imply an Empty Buffer:**
   Physical array slots contain old or unallocated integer values. A slot may be physically untouched, yet the buffer can hold active tickets elsewhere. Conversely, after multiple wrap-arounds, inactive slots still contain stale integer data. Emptiness and fullness are defined strictly by whether `size == 0` or `size == K`, never by inspecting slot contents.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Capacity & Commands**|**Expected Output**|**Actual Output & Trace Summary**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|$K = 3$<br>`ENQ 17`, `ENQ 29`, `ENQ 33`<br>`ENQ 46`<br>`SERVE`<br>`ENQ 46`<br>`FRONT`<br>`SIZE`|`FULL`<br>`17`<br>`29`<br>`3`|`FULL`<br>`17`<br>`29`<br>`3`<br><br>Trace: At `ENQ 46`, size was 3 -> `FULL`. `SERVE` output 17, front became 1. Second `ENQ 46` wrapped to index 0. `FRONT` was 29, `SIZE` was 3.|
|**Boundary (Capacity $K = 1$)**|$K = 1$<br>`ENQ 99`<br>`ENQ 100`<br>`FRONT`<br>`SERVE`<br>`SERVE`<br>`ENQ 200`<br>`FRONT`|`FULL`<br>`99`<br>`99`<br>`EMPTY`<br>`200`|`FULL`<br>`99`<br>`99`<br>`EMPTY`<br>`200`|
|**Boundary (Empty Buffer Queries)**|$K = 4$<br>`SERVE`<br>`FRONT`<br>`SIZE`|`EMPTY`<br>`EMPTY`<br>`0`|`EMPTY`<br>`EMPTY`<br>`0`|
|**Tricky (Multiple Wrap-Arounds & Reuses)**|$K = 3$<br>`ENQ 1, 2, 3`<br>`SERVE, SERVE`<br>`ENQ 4, 5`<br>`SERVE, SERVE, SERVE`<br>`SIZE`, `FRONT`|`1`<br>`2`<br>`3`<br>`4`<br>`5`<br>`0`<br>`EMPTY`|`1`<br>`2`<br>`3`<br>`4`<br>`5`<br>`0`<br>`EMPTY`<br><br>(Confirmed: Multiple wrap-arounds preserved FIFO ordering).|

---

## Problem 5 – Peak Passenger Window
### Main Idea & Correctness Argument
Given a chronological array of passenger counts and window size $k$, find the peak (maximum) count in each of the $n - k + 1$ consecutive windows of length $k$.

We implement and compare two approaches:
1. **Approach A (Direct Scan):** For each window start index $i$ from $0$ to $n - k$, inspect all $k$ elements to determine the maximum.
2. **Approach B (Monotonic Deque):**
   - Store array **indices** in an `ArrayDeque<Integer>` such that corresponding array values are in **strictly descending order** from front to back.
   - For each index $i$:
     1. *Evict Expired Indices:* While the front index is $< i - k + 1$ (outside current window), remove it from the front.
     2. *Evict Dominated Candidates:* While the back index has $a[deque.peekLast()] \le a[i]$, remove it from the back. (An older element that is smaller than or equal to $a[i]$ can never be the maximum of any future window that contains $a[i]$).
     3. *Add Current Index:* Push $i$ to the back of the deque.
     4. *Report Window Peak:* For $i \ge k - 1$, the front of the deque $a[deque.peekFirst()]$ is the peak for window $[i - k + 1 .. i]$.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - _Approach A (Direct Scan):_ $O((n - k + 1)k)$ time, which degrades to $O(nk) = O(n^2)$ when $k \approx n / 2$.
  - _Approach B (Monotonic Deque):_ Strict $O(n)$ linear time. Every index $i$ enters the deque at most once and is popped at most once (either from the front when expired, or from the back when dominated). Total deque operations across all $n$ elements are at most $2n$.
- **Auxiliary-Space Complexity:**
  - Deque working storage: At most $O(k)$ indices stored simultaneously.
  - Output storage: $O(n - k + 1)$ integers to store the window maxima array.

### Algorithm Analysis Table

|**Criterion**|**Approach A: Direct $k$-Item Scan**|**Approach B: Monotonic Deque**|
|---|---|---|
|**Main idea**|Inspect all $k$ items independently for every window.|Maintain candidate indices in decreasing value order in a double-ended queue.|
|**Time complexity**|$O((n - k + 1)k) = O(nk)$|$O(n)$|
|**Auxiliary-space complexity**|$O(1)$ working memory|$O(k)$ deque working memory|
|**Advantages**|Trivially simple; no complex data structure needed.|Linear scaling regardless of window size $k$.|
|**Disadvantages**|Redundant scanning of overlapping elements; slow for large $k$.|Requires maintaining a monotonic invariant with double-ended eviction.|
|**Suitable when**|Window size $k$ is very small ($k \le 3$) or $n \le 2,000$.|General sliding-window maximum problems with large $n$ ($n \le 10^5$).|

### Which approach would you choose for the stated constraints, and why?
**Approach B (Monotonic Deque)** is chosen. For $n = 100,000$ and $k = 50,000$, direct scanning takes $\approx 2.5 \times 10^9$ operations (several seconds), while the monotonic deque completes in fewer than $200,000$ operations (a few milliseconds).

### Analysis Questions
1. **Deque Content Trace and Amortized $O(1)$ Step Proof:**
   On `[9, 1, 4, 7, 3, 5, 2]` with $k = 3$:
   - $i=0 (9)$: Deque indices `[0]`, values `[9]`.
   - $i=1 (1)$: Deque indices `[0, 1]`, values `[9, 1]`.
   - $i=2 (4)$: 1 is dominated by 4 -> Deque `[0, 2]` (values `[9, 4]`). Window 0 peak = **9**.
   - $i=3 (7)$: 0 expired ($0 < 1$). 4 is dominated by 7 -> Deque `[3]` (value `[7]`). Window 1 peak = **7**.
   - $i=4 (3)$: Deque `[3, 4]` (values `[7, 3]`). Window 2 peak = **7**.
   - $i=5 (5)$: 3 is dominated by 5 -> Deque `[3, 5]` (values `[7, 5]`). Window 3 peak = **7**.
   - $i=6 (2)$: 3 expired ($3 < 4$). Deque `[5, 6]` (values `[5, 2]`). Window 4 peak = **5**.
   Each index is pushed exactly once and popped at most once, bounding total operations by $2n$ ($O(n)$ total time).
2. **Why a Running Sum Alone Cannot Maintain the Maximum:**
   Addition is an invertible algebraic operation: subtracting the leaving element from a running sum restores the exact sum of the remaining elements. In contrast, the `max` operation is non-invertible: if the maximum element leaves the window, its value gives zero information about which remaining element becomes the new maximum. A monotonic structure is strictly necessary to preserve candidate ordering.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array & $k$**|**Expected Maxima**|**Actual Output & Verification**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|`[9, 1, 4, 7, 3, 5, 2]`, $k = 3$|`9 7 7 7 5`|`9 7 7 7 5` (PASSED)|
|**Boundary ($k = 1$)**|`[9, 1, 4, 7, 3, 5, 2]`, $k = 1$|`9 1 4 7 3 5 2`|`9 1 4 7 3 5 2` (PASSED)|
|**Boundary ($k = n$)**|`[9, 1, 4, 7, 3, 5, 2]`, $k = 7$|`9`|`9` (PASSED)|
|**Tricky (Repeated Equal Maxima)**|`[7, 7, 7, 7, 7]`, $k = 3$|`7 7 7`|`7 7 7` (PASSED)|
|**Tricky (All-Negative Counts)**|`[-10, -50, -20, -5, -40, -15]`, $k = 3$|`-10 -5 -5 -5`|`-10 -5 -5 -5` (PASSED)|
|**Tricky (Expiring Maximum at Left)**|`[100, 2, 3, 4, 5]`, $k = 3$|`100 4 5`|`100 4 5` (PASSED)|

---

## Problem 6 – Next Busier Service Period
### Main Idea & Correctness Argument
For each service period $i$, determine the distance $j - i$ to the first later period $j > i$ that has a strictly greater passenger count ($a[j] > a[i]$). Equal counts do not qualify. If no such period exists, the answer is 0.

1. **Monotonic Stack Invariant:**
   A `Deque<Integer>` stack maintains the indices of unresolved periods. The corresponding passenger counts of these indices are in **monotonically decreasing (or non-increasing)** order from bottom to top.
2. **Algorithm:**
   - Scan periods $i$ from $0$ to $n - 1$.
   - While the stack is not empty and $a[i] > a[stack.peek()]$:
     - The current count $a[i]$ is the *first* strictly greater future count for the unresolved index $j = stack.pop()$.
     - Record its distance as $result[j] = i - j$.
   - Push $i$ onto the stack as a new unresolved candidate.
   - Any indices left in the stack when the scan finishes have no strictly greater future reading; their answers remain `0`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(n)$ linear time. Although nested loops appear in the implementation, each index is pushed onto the stack exactly once and popped at most once. The total number of inner loop comparisons and pops is at most $n$.
- **Auxiliary-Space Complexity:** $O(n)$ space for the stack in the worst case (e.g. on a strictly decreasing array where no index resolves until the end).

### Analysis Questions
1. **Invariant of Unresolved Indices and Safety of Popping:**
   The stack maintains indices in non-increasing order of value. When a new count $a[i]$ arrives that is strictly greater than $a[top]$, index $i$ is guaranteed to be the earliest index to the right of $top$ with a strictly larger value. Popping $top$ and finalizing its distance as $i - top$ is completely safe because no index between $top$ and $i$ had a value $> a[top]$, and no future index after $i$ can be closer to $top$ than $i$.
2. **Strict Inequality vs. Equality:**
   The problem specifies that equal-count days do not qualify. If $a[i] == a[top]$, $a[i]$ cannot resolve $top$. Popping only when $a[i] > a[top]$ preserves earlier equal values on the stack so that they remain waiting for a genuinely busier period.
3. **Linear Time Bound ($O(n)$):**
   The outer loop runs $n$ times. The inner `while` loop pops one index per iteration. Because an index cannot be popped more than once, the total number of pops across the entire program cannot exceed $n$. Hence, total operations are bounded by $2n$, yielding $O(n)$ time.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Array**|**Expected Distances**|**Actual Output & Baseline Verification**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|`[6, 9, 8, 10, 9]`|`1 2 1 0 0`|`1 2 1 0 0` (Verified with $O(n^2)$ direct scan)|
|**Boundary (Empty Array $n = 0$)**|`[]`|*(blank line)*|*(blank line)*|
|**Boundary (Single Element $n = 1$)**|`[42]`|`0`|`0`|
|**Tricky (All Equal Values)**|`[5, 5, 5, 5, 5]`|`0 0 0 0 0`|`0 0 0 0 0` (Equal counts do not qualify)|
|**Normal (Strictly Increasing)**|`[1, 2, 3, 4, 5]`|`1 1 1 1 0`|`1 1 1 1 0`|
|**Normal (Strictly Decreasing)**|`[5, 4, 3, 2, 1]`|`0 0 0 0 0`|`0 0 0 0 0`|
|**Tricky (Signed and Negative Counts)**|`[-10, -5, -20, -15, 0, -1]`|`1 3 1 1 0 0`|`1 3 1 1 0 0`|

---

## Problem 7 – Editable Stop Registry
### Main Idea & Correctness Argument
A transit planner maintains an editable forward linked chain of stop IDs. The registry supports insertion at both ends without shifting an array, lookup by ID, first-match deletion, and display.

1. **Data Structure:** Custom singly linked list with `Node { int value; Node next; }`, tracking `head`, `tail`, and `size`.
2. **Operations & Correctness:**
   - `addFirst(x)`: Allocates `new Node(x)`, sets `next = head`, and updates `head`. If the list was empty, `tail` also points to the new node.
   - `addLast(x)`: Allocates `new Node(x)`, sets `tail.next = newNode`, and advances `tail = newNode`. If empty, both `head` and `tail` point to it.
   - `remove(x)`: Traverses from `head` using `prev` and `curr` pointers.
     - If `head.value == x`, advances `head = head.next`. If the list becomes empty (`size == 0`), resets `tail = null`.
     - Otherwise, upon finding the first match, bypasses it via `prev.next = curr.next`. If the deleted node was `tail`, resets `tail = prev`.
     - Prints `REMOVED` if found, or `NOT_FOUND` if absent.
   - `find(x)`: Scans from `head` with index counter, returning 0-based index of first match or `-1`.
   - `list()`: Traverses from `head` printing values separated by spaces, or `EMPTY` if empty.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - `addFirst(x)`: $O(1)$ (immediate head pointer update).
  - `addLast(x)`: $O(1)$ (immediate tail pointer update without list traversal).
  - `remove(x)`: $O(n)$ worst-case time to locate the target node; $O(1)$ unlinking.
  - `find(x)`: $O(n)$ linear traversal.
  - `list()`: $O(n)$ traversal.
- **Auxiliary-Space Complexity:** $O(1)$ extra working space beyond the $O(n)$ node references used to store the list elements.

### Analysis Questions
1. **Removing a Known Node vs. Finding by Value:**
   Unlinking a known node whose predecessor is already available requires only $O(1)$ pointer adjustments (`prev.next = curr.next`). In contrast, removing by value requires an $O(n)$ linear scan from the head to locate the target node and identify its predecessor before unlinking can occur.
2. **Maintaining Tail Invariant:**
   When removing the last remaining node, `size` becomes 0. Both `head` and `tail` must be explicitly reset to `null`. If `tail` were left pointing to the discarded node, subsequent `addLast` calls would dereference a stale reference, breaking the list.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Commands**|**Expected Output**|**Actual Output & Explanation**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|`ADD_LAST 36`, `ADD_LAST 48`<br>`ADD_FIRST 7`<br>`REMOVE 36`<br>`FIND 48`<br>`LIST`|`REMOVED`<br>`1`<br>`7 48`|`REMOVED`<br>`1`<br>`7 48`<br><br>Sequence: 7 -> 36 -> 48; 36 removed; 48 is at index 1.|
|**Boundary (Operations on Empty List)**|`REMOVE 10`<br>`FIND 10`<br>`LIST`|`NOT_FOUND`<br>`-1`<br>`EMPTY`|`NOT_FOUND`<br>`-1`<br>`EMPTY`|
|**Boundary (Insert and Delete Only Node)**|`ADD_FIRST 50`<br>`LIST`<br>`REMOVE 50`<br>`LIST`<br>`ADD_LAST 60`<br>`LIST`|`50`<br>`REMOVED`<br>`EMPTY`<br>`60`|`50`<br>`REMOVED`<br>`EMPTY`<br>`60`<br><br>(Confirmed: Tail was properly reset to null when empty, allowing clean re-addition).|
|**Boundary (Deleting Head and Tail)**|`ADD_LAST 10, 20, 30`<br>`REMOVE 10` *(head)*<br>`LIST`<br>`REMOVE 30` *(tail)*<br>`LIST`|`REMOVED`<br>`20 30`<br>`REMOVED`<br>`20`|`REMOVED`<br>`20 30`<br>`REMOVED`<br>`20`|
|**Tricky (Duplicates - Only First Removed)**|`ADD_LAST 5, 5, 5`<br>`FIND 5`<br>`REMOVE 5`<br>`FIND 5`<br>`LIST`|`0`<br>`REMOVED`<br>`0`<br>`5 5`|`0`<br>`REMOVED`<br>`0`<br>`5 5`|

---

## Problem 9 – Bidirectional Itinerary List
### Main Idea & Correctness Argument
A transit itinerary requires forward and backward navigation through stop IDs, maintaining full bidirectional consistency after insertions and deletions.

1. **Data Structure:** Custom doubly linked list with `Node { int value; Node prev, next; }`, tracking `head`, `tail`, and `size`.
2. **Operations & Correctness:**
   - `addFirst(x)`: Inserts at `head`, setting `newNode.next = head` and `head.prev = newNode`. If empty, `tail = newNode`.
   - `addLast(x)`: Inserts at `tail`, setting `newNode.prev = tail` and `tail.next = newNode`. If empty, `head = newNode`.
   - `remove(x)`: Scans from `head` for the first node where `curr.value == x`.
     - If `curr == head`, advances `head = head.next` and clears `head.prev = null` (or resets `tail = null` if empty).
     - If `curr == tail`, moves `tail = tail.prev` and sets `tail.next = null`.
     - If interior node, splices neighbors: `curr.prev.next = curr.next` and `curr.next.prev = curr.prev`.
     - Prints `REMOVED` or `NOT_FOUND`.
   - `forward()`: Traverses from `head` via `next` pointers, printing IDs or `EMPTY`.
   - `backward()`: Traverses from `tail` via `prev` pointers, printing IDs or `EMPTY`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:**
  - `addFirst(x)` / `addLast(x)`: $O(1)$ time.
  - `remove(x)`: $O(n)$ search time to locate the node; $O(1)$ unlinking.
  - `forward()` / `backward()`: $O(n)$ traversal time.
- **Auxiliary-Space Complexity:** $O(1)$ extra working memory. The list requires $2$ reference fields (`prev` and `next`) per node, totaling $\Theta(n)$ reference storage.

### Analysis Questions
1. **Why an Incorrect `prev` Link Can Pass Forward Tests but Fail Backward Tests:**
   Forward traversal exclusively follows `next` pointers and never inspects `prev`. If a removal method updates `curr.prev.next = curr.next` but forgets `curr.next.prev = curr.prev`, forward traversal appears completely normal. However, backward traversal starts at `tail` and follows `prev` links; an uncorrected `prev` link will jump to the wrong node or crash, exposing the corruption.
2. **Trade-offs of Doubly Linked vs. Singly Linked Lists:**
   - *Advantages of Doubly Linked:* Allows $O(1)$ removal of any given node without needing to search for its predecessor; permits efficient $O(1)$ reverse navigation.
   - *Disadvantages:* Allocates an extra pointer per node (increasing memory footprint by 33-50%) and requires twice as many reference assignments during every insertion and deletion, increasing code complexity.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Commands**|**Expected Output**|**Actual Output & Explanation**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|`ADD_LAST 36, 48, 59`<br>`REMOVE 48`<br>`ADD_FIRST 7`<br>`FORWARD`<br>`BACKWARD`|`REMOVED`<br>`7 36 59`<br>`59 36 7`|`REMOVED`<br>`7 36 59`<br>`59 36 7`<br><br>(Forward and backward sequences verified matching).|
|**Boundary (Operations on Empty List)**|`FORWARD`<br>`BACKWARD`<br>`REMOVE 100`|`EMPTY`<br>`EMPTY`<br>`NOT_FOUND`|`EMPTY`<br>`EMPTY`<br>`NOT_FOUND`|
|**Boundary (Singleton Insert & Removal)**|`ADD_FIRST 99`<br>`FORWARD`, `BACKWARD`<br>`REMOVE 99`<br>`FORWARD`, `BACKWARD`|`99`<br>`99`<br>`REMOVED`<br>`EMPTY`<br>`EMPTY`|`99`<br>`99`<br>`REMOVED`<br>`EMPTY`<br>`EMPTY`|
|**Boundary (Delete Head and Tail)**|`ADD_LAST 10, 20, 30`<br>`REMOVE 10` *(head)*<br>`FORWARD`, `BACKWARD`<br>`REMOVE 30` *(tail)*<br>`FORWARD`, `BACKWARD`|`REMOVED`<br>`20 30`<br>`30 20`<br>`REMOVED`<br>`20`<br>`20`|`REMOVED`<br>`20 30`<br>`30 20`<br>`REMOVED`<br>`20`<br>`20`|
|**Tricky (Alternating Inserts & Duplicates)**|`ADD_LAST 20`, `ADD_FIRST 10`<br>`ADD_LAST 20`<br>`FORWARD`, `BACKWARD`<br>`REMOVE 20`<br>`FORWARD`, `BACKWARD`|`10 20 20`<br>`20 20 10`<br>`REMOVED`<br>`10 20`<br>`20 10`|`10 20 20`<br>`20 20 10`<br>`REMOVED`<br>`10 20`<br>`20 10`|

---

## Problem 10 – Station Incident Linked Queue
### Main Idea & Correctness Argument
A transit station queues incident logs using a singly linked queue. Incidents are serviced strictly in FIFO order. Unlike circular arrays, a linked queue has no fixed capacity bound.

1. **Data Structure:** Custom singly linked list with `Node { int value; Node next; }`, tracking `head` (front of queue), `tail` (rear of queue), and `size`.
2. **Operations & Correctness:**
   - `enqueue(x)`: Creates a new node. If empty, sets `head = tail = newNode`. Otherwise, attaches `tail.next = newNode` and updates `tail = newNode`. Runs in $O(1)$ without traversing.
   - `serve()`: If empty, prints `EMPTY`. Otherwise, extracts `head.value` and advances `head = head.next`. If the queue becomes empty (`size == 0`), explicitly resets `tail = null`.
   - `front()`: If empty, prints `EMPTY`. Otherwise, prints `head.value` in $O(1)$.
   - `size()`: Prints `size` in $O(1)$.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(1)$ worst-case time for all operations (`ENQ`, `SERVE`, `FRONT`, `SIZE`).
- **Auxiliary-Space Complexity:** $\Theta(n)$ space for $n$ dynamically allocated `Node` objects. Each node consumes object header, integer payload, and reference pointer space.

### Analysis Questions
1. **Why Removing the Last Item Must Reset `tail = null`:**
   When a queue holds exactly one element, `head` and `tail` reference that identical node. When `serve()` is called, `head = head.next` sets `head = null`. If `tail` is not reset to `null`, `tail` remains pointing to the garbage node. When a subsequent `enqueue` occurs, code checking `if (isEmpty())` would find `head == null` but `tail != null`, leading to null pointer exceptions or appending to an unlinked node while `head` remains disconnected.
2. **Comparison with Problem 4 Circular Array Queue:**
   - *Capacity Management:* Problem 4 circular array has a rigid capacity limit $K$ and outputs `FULL` upon overflow. The linked queue expands dynamically to available system heap memory without an arbitrary bound.
   - *Memory Overhead:* The circular array allocates one contiguous block of $K$ integers ($4K$ bytes) with zero per-element reference overhead. The linked queue allocates an individual heap object per element ($24$ to $32$ bytes per node), creating substantial garbage collector and reference overhead.
   - *Cache Locality:* The circular array benefits from spatial locality and CPU prefetching. Linked nodes are scattered arbitrarily across heap memory, incurring pointer-chasing cache misses.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Input Commands**|**Expected Output**|**Actual Output & Explanation**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|9 commands:<br>`ENQ 19`, `ENQ 26`<br>`SERVE`<br>`ENQ 38`<br>`FRONT`, `SIZE`<br>`SERVE`, `SERVE`, `SERVE`|`19`<br>`26`<br>`2`<br>`26`<br>`38`<br>`EMPTY`|`19`<br>`26`<br>`2`<br>`26`<br>`38`<br>`EMPTY`<br><br>(Handout sequence perfectly matched).|
|**Boundary (Queries on Empty Queue)**|`SERVE`<br>`FRONT`<br>`SIZE`|`EMPTY`<br>`EMPTY`<br>`0`|`EMPTY`<br>`EMPTY`<br>`0`|
|**Boundary (Single Item Tail Reset)**|`ENQ 55`<br>`FRONT`, `SIZE`<br>`SERVE`<br>`FRONT`, `SIZE`<br>`ENQ 99`<br>`FRONT`|`55`<br>`1`<br>`55`<br>`EMPTY`<br>`0`<br>`99`|`55`<br>`1`<br>`55`<br>`EMPTY`<br>`0`<br>`99`<br><br>(Confirmed: Tail was reset cleanly upon empty, allowing successful re-enqueue).|
|**Tricky (1,000 Alternating Operations)**|500 pairs of `ENQ i` followed immediately by `SERVE`, then `SIZE` and `FRONT`|Serves `0` to `499`, then `0` and `EMPTY`|Output printed 0 through 499, followed by `0` and `EMPTY` (No leaks or memory drift).|

---

## Problem 12 – Recently Used Station Cache
### Main Idea & Correctness Argument
A route planner maintains a cache of station-data entries limited to capacity $K$. Accessing or adding an entry marks it as Most Recently Used (MRU). When inserting a new key at capacity, the Least Recently Used (LRU) entry must be evicted.

1. **Combined Architecture:**
   - **`HashMap<Integer, Node>`:** Maps each station `key` directly to its corresponding `Node` in $O(1)$ expected time.
   - **Custom Doubly Linked List:** Stores nodes ordered by recency. The `head` represents the MRU entry; the `tail` represents the LRU entry.
2. **Operations:**
   - `get(key)`:
     - If `map.containsKey(key)` is false, outputs `-1`.
     - Otherwise, retrieves the node, moves it to `head` (`moveToHead`), and outputs its `value`.
     - *Distinguishing Stored -1:* Values can be signed integers including `-1`. Emptiness is tested strictly via `map.containsKey(key)` so that a cached station with value `-1` is correctly returned as `-1` rather than treated as a cache miss.
   - `put(key, value)`:
     - If `key` already exists, updates `node.value = value` and calls `moveToHead(node)`.
     - If `key` is new:
       - If `map.size() == capacity`, evicts `tail` by unlinking it and calling `map.remove(lru.key)`.
       - Allocates `new Node(key, value)`, attaches it at `head`, and puts it into `map`.

### Time & Auxiliary-Space Complexity
- **Time Complexity:** $O(1)$ expected time for both `get` and `put`. Hash map lookup, insertion, and deletion run in $O(1)$ average time, and unlinking/re-attaching a doubly linked node runs in $O(1)$ strict time.
- **Auxiliary-Space Complexity:** $O(K)$ space to store at most $K$ entries in both the hash map and the doubly linked list.

### Analysis Questions
1. **Why Moving an Accessed Node Requires Doubly Linked Unlinking:**
   When an existing node is accessed via `get` or updated via `put`, it must be relocated to the head. Unlinking an arbitrary node in $O(1)$ time requires direct access to both its predecessor (`node.prev`) and successor (`node.next`). In a singly linked list, unlinking would require an $O(n)$ traversal from `head` to locate the predecessor, making $O(1)$ cache recency updates impossible.
2. **How Eviction Updates the Map:**
   When inserting into a full cache, the LRU element is located at `tail`. The cache unlinks `tail` and executes `map.remove(lru.key)`, removing both the node reference from the list and the key mapping from the hash table in $O(1)$ time.
3. **Preserving Capacity Invariant:**
   By evicting the LRU element before allocating a new node whenever `map.size() == capacity`, the number of entries in the hash map and the doubly linked list is strictly bounded by $K$ at all times.

### Test Cases & Recorded Outputs

|**Test Type & Name**|**Capacity & Commands**|**Expected Output**|**Actual Output & Explanation**|
|---|---|---|---|
|**Normal (Handout Worked Example)**|$K = 2$<br>`PUT 4 40`<br>`PUT 8 80`<br>`GET 4`<br>`PUT 12 120`<br>`GET 8`<br>`GET 12`|`40`<br>`-1`<br>`120`|`40`<br>`-1`<br>`120`<br><br>Trace: `GET 4` refreshed key 4 as MRU. `PUT 12 120` evicted 8. `GET 8` was `-1` (miss); `GET 12` returned 120.|
|**Boundary (Capacity $K = 1$)**|$K = 1$<br>`PUT 10 100`<br>`GET 10`<br>`PUT 20 200`<br>`GET 10`<br>`GET 20`|`100`<br>`-1`<br>`200`|`100`<br>`-1`<br>`200`<br><br>(Key 10 evicted immediately when key 20 inserted).|
|**Normal (Updating Existing Key)**|$K = 2$<br>`PUT 1 10`, `PUT 2 20`<br>`PUT 1 999`<br>`PUT 3 30`<br>`GET 1`, `GET 2`, `GET 3`|`999`<br>`-1`<br>`30`|`999`<br>`-1`<br>`30`<br><br>(Key 1 updated and refreshed to MRU; key 2 was evicted).|
|**Tricky (Stored Value -1 vs. Missing)**|$K = 2$<br>`PUT 7 -1`<br>`GET 7`<br>`GET 99`|`-1`<br>`-1`|`-1`<br>`-1`<br><br>(Key 7 exists with value -1; key 99 is missing; both return -1 correctly).|
