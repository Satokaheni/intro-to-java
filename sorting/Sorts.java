package sorting;

import java.util.Arrays;

// T must implement Comparable so we can use compareTo() for ordering.
// Note: Radix Sort only works on integers, so it has its own static method.
public class Sorts<T extends Comparable<T>> {

    // -----------------------------------------------------------------------
    // Bubble Sort
    //
    // Repeatedly walks through the array comparing adjacent elements.
    // If two neighbors are in the wrong order, swap them.
    // Keep doing this until no swaps happen in a full pass — array is sorted.
    //
    // Time:  O(n^2) average/worst,  O(n) best (already sorted)
    // Space: O(1)
    // -----------------------------------------------------------------------

    public T[] bubbleSort(T[] array) {
        int n = array.length;

        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;

            // After each pass the largest unsorted element bubbles to the end,
            // so we can shrink the inner loop by 'pass' each time.
            for (int i = 0; i < n - 1 - pass; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }

            // Early exit: if nothing was swapped the array is already sorted
            if (!swapped) break;
        }

        return array;
    }

    // -----------------------------------------------------------------------
    // Insertion Sort
    //
    // Builds a sorted portion at the front of the array one element at a time.
    // Take the next unsorted element and slide it left until it is in the
    // correct position within the sorted portion.
    //
    // Time:  O(n^2) average/worst,  O(n) best (already sorted)
    // Space: O(1)
    // -----------------------------------------------------------------------

    public T[] insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i]; // the element we are inserting
            int j = i - 1;

            // Shift elements that are greater than key one position to the right
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            // Drop the key into its correct spot
            array[j + 1] = key;
        }

        return array;
    }

    // -----------------------------------------------------------------------
    // Merge Sort
    //
    // Divide-and-conquer: split the array in half, recursively sort each half,
    // then merge the two sorted halves back together.
    //
    // Time:  O(n log n) always
    // Space: O(n) — needs extra arrays for merging
    // -----------------------------------------------------------------------

    public T[] mergeSort(T[] array) {
        if (array.length <= 1) return array;

        int mid = array.length / 2;

        // Copy each half into its own array
        T[] left  = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        left  = mergeSort(left);
        right = mergeSort(right);

        return merge(array, left, right);
    }

    // Merges two sorted arrays (left, right) into 'result'
    private T[] merge(T[] result, T[] left, T[] right) {
        int l = 0, r = 0, i = 0;

        // Pick the smaller front element from left or right each step
        while (l < left.length && r < right.length) {
            if (left[l].compareTo(right[r]) <= 0) {
                result[i++] = left[l++];
            } else {
                result[i++] = right[r++];
            }
        }

        // Copy whatever is left over in either half
        while (l < left.length)  result[i++] = left[l++];
        while (r < right.length) result[i++] = right[r++];

        return result;
    }

    // -----------------------------------------------------------------------
    // Quick Sort
    //
    // Pick a pivot element, partition the array so everything smaller is on
    // the left and everything larger is on the right, then recursively sort
    // each side.
    //
    // Time:  O(n log n) average,  O(n^2) worst (bad pivot choices)
    // Space: O(log n) — recursive call stack
    // -----------------------------------------------------------------------

    public T[] quickSort(T[] array) {
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    private void quickSortHelper(T[] array, int low, int high) {
        if (low >= high) return;

        int pivotIndex = partition(array, low, high);

        quickSortHelper(array, low, pivotIndex - 1);
        quickSortHelper(array, pivotIndex + 1, high);
    }

    // Places pivot in its final position, smaller elements left, larger right.
    // Uses the last element as the pivot for simplicity.
    private int partition(T[] array, int low, int high) {
        T pivot = array[high]; // pick the last element as pivot
        int i = low - 1;       // i tracks the boundary of the "less than" region

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        // Put the pivot in its correct final position
        swap(array, i + 1, high);
        return i + 1;
    }

    // -----------------------------------------------------------------------
    // Selection Sort
    //
    // Divides the array into a sorted and unsorted region.
    // On each pass, find the minimum element in the unsorted region and swap
    // it into the next position of the sorted region.
    //
    // Time:  O(n^2) always
    // Space: O(1)
    // -----------------------------------------------------------------------

    public T[] selectionSort(T[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }

        return array;
    }

    // -----------------------------------------------------------------------
    // Radix Sort  (integers only)
    //
    // Sorts by individual digits, starting from the least significant digit.
    // Uses counting sort as a stable subroutine for each digit position.
    // Works only on non-negative integers.
    //
    // Time:  O(d * n) where d = number of digits
    // Space: O(n)
    // -----------------------------------------------------------------------

    public static int[] radixSort(int[] array) {
        if (array.length == 0) return array;

        int max = array[0];
        for (int val : array) if (val > max) max = val;

        // Sort by each digit position: 1s, 10s, 100s, ...
        for (int place = 1; max / place > 0; place *= 10) {
            array = countingSortByDigit(array, place);
        }

        return array;
    }

    // Stable sort on a single digit position (ones, tens, hundreds, etc.)
    private static int[] countingSortByDigit(int[] array, int place) {
        int n = array.length;
        int[] output = new int[n];
        int[] count  = new int[10]; // digits 0-9

        // Count how many numbers have each digit at this position
        for (int val : array) {
            int digit = (val / place) % 10;
            count[digit]++;
        }

        // Convert counts to starting positions (prefix sum)
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Fill output array from right to left to keep sort stable
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / place) % 10;
            output[--count[digit]] = array[i];
        }

        return output;
    }

    // -----------------------------------------------------------------------
    // Tim Sort
    //
    // The algorithm Java and Python actually use for Arrays.sort().
    // Hybrid of Merge Sort and Insertion Sort:
    //   1. Split the array into small "runs" (usually 32 elements).
    //   2. Insertion-sort each run (fast on small arrays).
    //   3. Merge adjacent runs like merge sort.
    //
    // Time:  O(n log n) worst,  O(n) best (already sorted or few runs)
    // Space: O(n)
    // -----------------------------------------------------------------------

    private static final int TIM_RUN = 32;

    public T[] timSort(T[] array) {
        int n = array.length;

        // Step 1: insertion-sort every run of size TIM_RUN
        for (int start = 0; start < n; start += TIM_RUN) {
            int end = Math.min(start + TIM_RUN - 1, n - 1);
            insertionSortRange(array, start, end);
        }

        // Step 2: merge runs together, doubling the merge size each round
        for (int size = TIM_RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid   = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    mergeRange(array, left, mid, right);
                }
            }
        }

        return array;
    }

    // Insertion sort applied to just the subarray array[start..end]
    private void insertionSortRange(T[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= start && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Merge two adjacent sorted sub-arrays: array[left..mid] and array[mid+1..right]
    private void mergeRange(T[] array, int left, int mid, int right) {
        T[] leftArr  = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArr = Arrays.copyOfRange(array, mid + 1, right + 1);

        int l = 0, r = 0, i = left;
        while (l < leftArr.length && r < rightArr.length) {
            if (leftArr[l].compareTo(rightArr[r]) <= 0) {
                array[i++] = leftArr[l++];
            } else {
                array[i++] = rightArr[r++];
            }
        }
        while (l < leftArr.length)  array[i++] = leftArr[l++];
        while (r < rightArr.length) array[i++] = rightArr[r++];
    }

    // -----------------------------------------------------------------------
    // Helper
    // -----------------------------------------------------------------------

    private void swap(T[] array, int i, int j) {
        T temp   = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
