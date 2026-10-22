# Lab 02 Additional Guidelines/Requirements

These are lab-02-specific requirements imposed upon the base guidelines. **These rules take priority over the base guidelines in all cases.**

## Programming Requirements
- Implement the requested sorting and binary-search algorithms yourself. Built-in helpers such as Arrays.sort(), Collections.sort(), and library search methodsm may not replace the required implementations.
- Use arrays for array problems. HashSet is allowed for Problem 11; its optional map version may use HashMap. Do not replace requested linear or binary searches with hash lookups.
- Reuse your own helpers and include them in the source package. Each problem must have a small input driver or clearly labeled test driver; no menu is required.
- Pseudocode is a learning guide. Write the Java implementation and tests and explain your own decisions.

## Conventions Used Throughout the Handout

- Indices are zero-based. A nonempty inclusive window [L, . . . , R] has length R − L + 1. Dates, row labels, and IDs are not automatically indices.
- Arrays and strings are non-null. Empty inputs are allowed where stated. An unsuccessful exact-index search returns −1; a lower-bound position can be n.
- Use int for data and indices; use long for sums, targets, budgets, pair counts, and operation counters. Unless restricted further, use at most 106 values with |a[i]| ≤ 109. Limit quadratic sorts to the small sizes specified in their problems.
- Fixed windows require 1 ≤ k ≤ n. Reject invalid k with a clear message or IllegalArgumentException. Other inputs are assumed to satisfy stated contracts; parsing malformed text is not the learning objective.
- Preserve original arrays unless mutation is requested. When an answer includes a tied best window, return the earliest start. Never sort a sequence whose original adjacency is part of the question.

## Analysis and Testing

For each completed problem, give its idea, a short correctness argument, time and auxiliary-space costs, answers to the questions, and at least three tests: **normal, boundary, and tricky**. Cover the specifically named cases too.

Record input, expected output, and actual output. Compare required approaches on the
same original values. Identify output storage separately from working memory. Include
pre-processing in the complete cost. Timing is optional; operation counters are required
only where specified.