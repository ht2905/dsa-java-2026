# DSA Lab Report Requirements/Guidelines

## General Requirements
### Programming Requirements
- Use Java for all implementations.
- Organize source code clearly by problem and approach.
- Use meaningful class, method, and variable names.
- Programs must compile and run without modification.
- Do not use sorting as a shortcut in this lab.

**Input conventions for this version.** Data arrays are non-null, contain at most 100,000
integers, and have values between −1,000,000 and 1,000,000. Follow any additional range
or length restrictions stated in a problem. Empty arrays are allowed where the problem
permits them. Array indices start at zero. Use long for sums and subarray counts. Unless
stated otherwise, do not modify the input arrays.

For hash-based approaches, state the assumption of average constant-time operations
under suitable hashing, with amortized insertion cost. Do not present this assumption as
an unconditional worst-case guarantee. Auxiliary-space analysis concerns the algorithm
itself, not copies or expected-output data used only by the test driver.

### Analysis Requirements
For every required problem, students must:
1. explain the main idea of the implemented approach;
2. identify the time complexity;
3. identify the auxiliary-space complexity;
4. provide appropriate test cases;
5. answer the comparison questions provided for that problem.

For selected problems, students must implement and compare multiple approaches. The
purpose is to understand meaningful differences, not to invent unnecessary solutions
simply to increase the number of approaches.
 
### Testing Requirements
For each required problem, include at least three test cases:
• one normal case;
• one boundary case;
• one tricky or adversarial case where appropriate.

For each test case, record the input, expected output, and actual output.

## Algorithm Analysis Template
For each problem requiring multiple approaches, summarize the comparison using a table
such as the following.

| **Criterion**              | **Approach A** | **Approach B** |
| -------------------------- | -------------- | -------------- |
| Main idea                  |                |                |
| Time complexity            |                |                |
| Auxiliary-space complexity |                |                |
| Advantages                 |                |                |
| Disadvantages              |                |                |
| Suitable when              |                |                |

Conclude each comparison with a short answer to:
> **Which approach would you choose for the stated constraints, and
why?**

## Report Requirements

Submit one PDF report. Identify your full name, student ID, course, and Lab 1 – Version
4 on the first page. The report does not need to repeat the complete problem statements.

For each required problem, include:
1. a short explanation of the implemented algorithm(s);
2. time-complexity analysis;
3. auxiliary-space analysis;
4. answers to the problem-specific analysis questions;
5. the required test cases with expected and actual outputs.

For Problems 2–6, include the Algorithm Analysis Table comparing the required approaches.

Include the runtime experiment and reflection. For Problem 2, separate pre-processing, per-quiery, and total costs. Put completed challenges in separate sections.

## Submission Requirements

Each student must submit one compressed ZIP file named `StudentID_FullName_Lab01_V4.zip` containing:
- all Java source-code files;
- one PDF report;

Recommended structure:
```bash
dsa-java-2026 on  main 
❯ lt lab-01/
lab-01
├── report
└── src
    ├── Problem01_EqualValueBlocks
    ├── Problem02_SuffixMaximumQueries
    ├── Problem03_MissingNumber
    ├── Problem04_FirstUniquePosition
    ├── Problem05_AllSubarraySums
    └── Problem06_InterleaveHalves
```

Include the runtime-experiment driver in `src/` with brief compile/run instructions. Submit
actual `.java` files; screenshots or code pasted into the report do not replace the source
files. Do not include compiled `.class` files or IDE build folders.

## Final Checklist

Before submitting, make sure that:
- all required Java programs compile and run;
- the report includes time and auxiliary-space analysis;
- multiple approaches are implemented where required;
- comparisons explain meaningful trade-offs rather than only listing Big-O values;
- test cases include normal, boundary, and tricky situations;
- the runtime experiment is included;
- the ZIP file follows the requested organization and naming convention.