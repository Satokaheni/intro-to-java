package heap;

import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<T>> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public MaxHeap() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MaxHeap(T[] arr) {
        this.data = new Object[Math.max(arr.length, DEFAULT_CAPACITY)];
        this.size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = arr[i];
        }
        rebuildHeap();
    }

    public void insert(T value) {
        if (size == data.length) grow();
        data[size] = value;
        heapifyUp(size);
        size++;
    }

    @SuppressWarnings("unchecked")
    public T extractMax() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        T max = (T) data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;
        if (size > 0) heapifyDown(0);
        return max;
    }

    @SuppressWarnings("unchecked")
    public T peekMax() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        return (T) data[0];
    }

    @SuppressWarnings("unchecked")
    public void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (((T) data[index]).compareTo((T) data[parent]) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void heapifyDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && ((T) data[left]).compareTo((T) data[largest]) > 0) {
                largest = left;
            }
            if (right < size && ((T) data[right]).compareTo((T) data[largest]) > 0) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    public void rebuildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void grow() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
