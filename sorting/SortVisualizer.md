# Sorting Algorithm Visualizer

A visual reference for the six sorting algorithms in `Sorts.java`.

---

## Bubble Sort

Repeatedly compares adjacent elements and swaps them if they are out of order.
The largest unsorted element "bubbles up" to the end on each pass.

![Bubble Sort](https://upload.wikimedia.org/wikipedia/commons/0/06/Bubble-sort.gif)

| | |
|---|---|
| Best | O(n) — already sorted |
| Average | O(n²) |
| Worst | O(n²) |
| Space | O(1) |

**When to use:** Only for very small arrays or teaching purposes. Easy to understand but slow.

---

## Insertion Sort

Builds a sorted region at the front of the array one element at a time.
Each new element is slid left into its correct position within the sorted region.

![Insertion Sort](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)

| | |
|---|---|
| Best | O(n) — already sorted |
| Average | O(n²) |
| Worst | O(n²) |
| Space | O(1) |

**When to use:** Great for small arrays or nearly-sorted data. Used as the base case inside Tim Sort.

---

## Merge Sort

Divide-and-conquer: recursively split the array in half, sort each half, then merge the two sorted halves back together.

![Merge Sort](https://upload.wikimedia.org/wikipedia/commons/8/8e/Merge_sort_animation.gif)

| | |
|---|---|
| Best | O(n log n) |
| Average | O(n log n) |
| Worst | O(n log n) |
| Space | O(n) — needs extra arrays for merging |

**When to use:** When you need a guaranteed O(n log n) and stable sort. Good for linked lists and external sorting.

---

## Quick Sort

Picks a pivot, partitions the array so smaller elements go left and larger go right, then recursively sorts each side.

![Quick Sort](https://upload.wikimedia.org/wikipedia/commons/6/6a/Sorting_quicksort_anim.gif)

| | |
|---|---|
| Best | O(n log n) |
| Average | O(n log n) |
| Worst | O(n²) — bad pivot choices (e.g. already sorted) |
| Space | O(log n) — call stack |

**When to use:** Generally the fastest in practice for in-memory sorting. Avoid when worst-case matters.

---

## Radix Sort

Sorts integers digit by digit from least significant to most significant.
Uses counting sort as a stable subroutine for each digit position.

![Radix Sort](https://upload.wikimedia.org/wikipedia/commons/0/04/%E5%9F%BA%E6%95%B0%E6%8E%92%E5%BA%8F.gif)

| | |
|---|---|
| Best | O(d × n) |
| Average | O(d × n) |
| Worst | O(d × n) — d = number of digits |
| Space | O(n) |

**When to use:** When sorting large sets of integers with a bounded number of digits. Faster than comparison-based sorts in that scenario.

---

## Tim Sort

Hybrid of Merge Sort and Insertion Sort — the actual algorithm used by Java's `Arrays.sort()` and Python's `list.sort()`.

1. Split the array into small **runs** of ~32 elements.
2. **Insertion-sort** each run (fast on small arrays).
3. **Merge** adjacent runs together like merge sort.

> No single GIF captures Tim Sort well since it is a hybrid. See this interactive visualization:
> [chrislaux.com/timsort](https://www.chrislaux.com/timsort)

| | |
|---|---|
| Best | O(n) — already sorted |
| Average | O(n log n) |
| Worst | O(n log n) |
| Space | O(n) |

**When to use:** Real-world general-purpose sorting. Excellent on partially sorted data, which is common in practice.

---

## Side-by-Side Comparison

| Algorithm | Best | Average | Worst | Space | Stable |
|---|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |
| Radix Sort | O(dn) | O(dn) | O(dn) | O(n) | Yes |
| Tim Sort | O(n) | O(n log n) | O(n log n) | O(n) | Yes |

**Stable** means equal elements keep their original relative order after sorting.
