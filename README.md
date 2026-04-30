# Intro to Java — Data Structures & Algorithms

A hands-on reference repo for anyone learning Java and foundational computer science concepts. Each folder is self-contained and focuses on one topic, making it easy to explore at your own pace.

---

## Who this is for

- People new to Java who want to see how common data structures are built from scratch
- Anyone learning CS fundamentals alongside a course or textbook
- Developers coming from another language who want to understand how Java handles generics, OOP, and algorithms

---

## Topics

| Folder | What you'll learn |
|---|---|
| `classes/` | Java classes, fields, methods, and constructors |
| `absinter/` | Abstract classes and interfaces |
| `pai/` | Polymorphism — shapes example (Polygon, Rectangle, Triangle) |
| `generics/` | Generic data structures: ArrayList, LinkedList, Stack, Queue, HashMap |
| `recursion/` | Recursive thinking and common recursive problems |
| `sorting/` | Bubble, insertion, selection, merge, quick, and radix sort |
| `trees/` | Binary search tree (BST) with insert, search, and delete |
| `graphs/` | Graphs with BFS, DFS, and Dijkstra's shortest path |
| `hash/` | Hash table with chaining and dynamic resizing |
| `heap/` | Generic max-heap with heapifyUp, heapifyDown, and rebuildHeap |

---

## How to run any file

No build system is required — just `javac` and `java`.

```bash
# Compile (from the repo root)
javac heap/MaxHeap.java heap/MaxHeapTest.java

# Run
java -ea -cp . heap.MaxHeapTest
```

Replace `heap` and `MaxHeapTest` with whichever folder and file you want to run. The `-ea` flag enables `assert` statements used in test files.

---

## Suggested learning order

If you're just starting out, work through the topics roughly in this order:

1. `classes/` — understand objects first
2. `absinter/` and `pai/` — inheritance and polymorphism
3. `generics/` — build the core structures (list, stack, queue)
4. `recursion/` — essential for understanding trees and sorting
5. `sorting/` — classic algorithms
6. `trees/` — your first tree structure
7. `hash/` — hash tables and collision handling
8. `graphs/` — BFS, DFS, weighted graphs
9. `heap/` — priority queues and heap operations
