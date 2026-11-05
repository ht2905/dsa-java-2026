# DSA Midterm Cheatsheet Implementation Plan
## Context
- I didn't have enough time to review all the concepts.
- I have almost zero Java programming background – more Python and R. I can read and understand the concepts before the exam, but writing code for their implementation is another matter entirely. In fact, I don't think I can even write a functional Java program without having the AI check my syntaxes.

## General Rules
- Since I can't find proper Java code, your cheatsheet should be focused on **code**  and **actual templates for implementing** each concept I've learned in the `midterm-review-book`. Handhold me: write those modular templates so simply that I can just copy (from the cheatsheet) and paste (into the exam paper) during the exam. Also include instructions on how to use those modules together.
- Simple code and implementation is better: I just need them to be simple and readable, not optimized the way I usually expect.
- Some stuff that aren't on the DSA exam but are fundamental: getters, setters, and constructors. Include ready-to-use template for each in the cheatsheets.
- I'm only allowed to bring eight A4 pages as my cheatsheet into the exam room. I've decided to take you generated cheatsheet and put it through this Typst template:
	```typst
	// =========================================================================
	// 4-COLUMN BALANCED EXAM CHEATSHEET
	// =========================================================================
	#set page(
	  paper: "a4",
	  flipped: true,
	  margin: (x: 3.5mm, y: 3.5mm),
	)
	
	// Font stack with condensed fallbacks for maximum character density
	#set text(
	  font: ("Roboto Condensed", "Liberation Sans Narrow", "Arial Narrow", "Liberation Sans"),
	  size: 5.8pt,
	  spacing: 90%,
	  hyphenate: true,
	)
	
	// Tight line and paragraph rhythm
	#set par(
	  leading: 0.32em,
	  spacing: 0.35em,
	  justify: false,
	  first-line-indent: 0pt,
	)
	
	// List & Enum compaction
	#set list(tight: true, spacing: 0.25em, body-indent: 0.25em, marker: ([•], [–]))
	#set enum(tight: true, spacing: 0.25em, indent: 0pt, body-indent: 0.25em)
	#show list: set block(above: 0.25em, below: 0.25em)
	#show enum: set block(above: 0.25em, below: 0.25em)
	
	// Code & Raw formatting
	#show raw: set text(font: ("Fira Code", "DejaVu Sans Mono", "Consolas", "monospace"), size: 5pt)
	#show raw.where(block: false): box.with(
	  fill: rgb("#eef1f6"),
	  inset: (x: 1.5pt, y: 0.5pt),
	  radius: 1pt,
	)
	#show raw.where(block: true): it => block(
	  fill: rgb("#f6f8fa"),
	  stroke: 0.25pt + rgb("#d0d7de"),
	  inset: (x: 2pt, y: 1.5pt),
	  radius: 1.5pt,
	  above: 0.3em,
	  below: 0.3em,
	  width: 100%,
	  it,
	)
	
	// Display Math compaction
	#show math.equation.where(block: true): set block(above: 0.28em, below: 0.28em)
	#show math.equation: set text(size: 0.96em)
	
	// Headings: Inverted high-contrast level-1 banner, underlined level-2
	#show heading.where(level: 1): it => block(
	  fill: rgb("#1d3557"),
	  inset: (x: 2.5pt, y: 1.8pt),
	  radius: 1pt,
	  width: 100%,
	  above: 0.45em,
	  below: 0.25em,
	  text(fill: white, weight: "bold", size: 6.8pt)[#it.body]
	)
	
	#show heading.where(level: 2): it => block(
	  above: 0.35em,
	  below: 0.2em,
	  width: 100%,
	  [
	    #text(fill: rgb("#1d3557"), weight: "bold", size: 6.2pt)[#it.body]
	    #v(-0.35em)
	    #line(length: 100%, stroke: 0.35pt + rgb("#1d3557"))
	  ]
	)
	
	#show heading.where(level: 3): it => text(weight: "bold", size: 5.8pt, fill: rgb("#457b9d"))[#it.body — ]
	
	// Table Defaults
	#set table(
	  inset: (x: 1.5pt, y: 1pt),
	  stroke: 0.25pt + rgb("#cbd5e1"),
	  fill: (col, row) => if row == 0 { rgb("#1d3557") } else if calc.even(row) { rgb("#f8fafc") } else { none },
	)
	#show table.cell.where(y: 0): set text(fill: white, weight: "bold")
	#show table.cell: set text(size: 4.8pt)
	
	// 4-Column flow
	#show: columns.with(4, gutter: 2.8mm)
	```

Make sure the cheatsheet is long enough to take advantage of all the space I get (eight 4-column A4 pages).

- If you still have space left on the cheatsheet, fill that space with the real exam solutions and mock exam solutions from `/home/ht2905/projects/dsa-java-2026/midterm-review/exam-solution/`. Make sure the end results fill all $4 \cdot 8 = 32 \text{ columns}$.
- Your generated cheatsheets should be written in normal Markdown – I will convert them into Typst myself.

## Some Areas to Focus on
- Compare the time complexity of the algorithms of interest, such as Bubble Sort, Merge Sort, Insertion Sort, Selection Sort, Binary Search, Optimized Linear Search, Linear Search, and so on.
- Include explanations of how each algorithm works.