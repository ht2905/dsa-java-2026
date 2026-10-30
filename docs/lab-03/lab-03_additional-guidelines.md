# Lab 03 Additional Guidelines/Requirements
# Data Structures and Algorithms – Lab 3 – Version 4
# Stacks, Queues, and Linked Lists

These are lab-03-specific requirements imposed upon the base guidelines. **These rules take priority over the general guidelines in all cases.**

## Lab Overview
This lab introduces three fundamental abstract data types and their implementations: stacks for last-in-first-out work, queues for first-in-first-out work, and linked lists for sequences connected by references. You will compare ordinary and specialized variants, trace state changes, read complete Java examples, and choose a representation based on the operations a problem requires.

The lab develops skills in implementing data structures, preserving invariants, working with empty and one-element structures, analyzing time and auxiliary space, and using structures to solve realistic problems. This is a one-week individual Java assignment. Each required program must be tested and explained.

## Assignment Scope

| Component | What to complete |
| --- | --- |
| Core problems | Problems 1, 3, 4, 5, 7, 9, and 10. |
| Applied choice | Choose one of Problems 2 or 8; submit Java code and its report section. |
| Interview choice | Choose one of Problems 6 or 11; submit Java code and its report section. |
| Written practice | Interview Corner A–D, comparison table, technique-recognition exercise, and reflection: PDF report only. |
| Optional extension | Problem 12 and any unselected choice problems. |

Complete nine required programming problems. Some problems compare two representations or algorithms. Each exercise includes a complete input/output contract, implementation guidance, and a worked example; sample input is read exactly as shown. The separate Interview Corner needs written reasoning, traces, or short snippets, not additional runnable programs.

## Three guiding questions
- Which end is accessible? A stack uses the top; a queue uses the front and back.
- How is the sequence stored? An array has indexed positions; a linked list follows references.
- Which operations matter? Choose the representation and variant from the workload, not from its name.

---

## Programming and Analysis Requirements

### Programming Rules
- Use Java. Implement your own array stack, circular-array queue, singly linked list, doubly linked list, and linked queue where explicitly required.
- Java ArrayDeque and PriorityQueue are allowed when a problem explicitly names them; do not replace a required manual implementation with a library container.
- Put algorithms into named methods or small classes. Include a driver or test harness for each submitted problem. A graphical user interface and interactive menu are not required.
- Represent links using references to Node objects, not arrays of simulated links. Do not use java.util.LinkedList where custom links are required.
- The input contract is part of the problem. If the contract allows an empty structure, handle it; do not silently return 0 as an empty-stack value when zero is valid data.

### Common Conventions
Commands and examples operate in the listed order. Unless a problem states otherwise, identifiers and values are signed Java ints, input is well-formed, and the sequence has at most 10^5 operations. A failed pop, dequeue, or peek should use the problem’s specified EMPTY result (or an explicitly documented exception in reusable APIs). For capacity-limited queues, K ≥ 1. Preserve the FIFO or LIFO ordering exactly.

### Analysis and Testing
For each required program, record the idea, an invariant or short correctness argument, worst-case time and auxiliary space (and amortized/expected bounds when appropriate), answers to its questions, and at least three tests: normal, boundary, and tricky. Show input, expected output, and actual output. Distinguish the space used to store n data items from extra working space used by an algorithm acting on an existing collection.