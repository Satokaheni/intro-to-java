package heap;

public class MaxHeapTest {
    public static void main(String[] args) {
        testDefaultConstructor();
        testArrayConstructorSize();
        testHeapifyUp();
        testHeapifyDown();
        testRebuildHeap();
        testExtractMax();
        testExtractOrdering();
        testEmptyHeapThrows();
        System.out.println("All tests passed.");
    }

    static void testDefaultConstructor() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        assert heap.size() == 0 : "Expected size 0, got " + heap.size();
        assert heap.isEmpty() : "Expected isEmpty true";
        System.out.println("testDefaultConstructor passed");
    }

    static void testArrayConstructorSize() {
        Integer[] arr = {3, 1, 4, 1, 5, 9, 2};
        MaxHeap<Integer> heap = new MaxHeap<>(arr);
        assert heap.size() == 7 : "Expected size 7, got " + heap.size();
        assert !heap.isEmpty() : "Expected isEmpty false";
        System.out.println("testArrayConstructorSize passed");
    }

    static void testHeapifyUp() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.insert(10);
        assert heap.peekMax() == 10 : "Expected root 10, got " + heap.peekMax();
        heap.insert(20);
        assert heap.peekMax() == 20 : "Expected root 20 after bubbling up, got " + heap.peekMax();
        System.out.println("testHeapifyUp passed");
    }

    static void testHeapifyDown() {
        Integer[] arr = {3, 1, 4, 1, 5, 9, 2};
        MaxHeap<Integer> heap = new MaxHeap<>(arr);
        assert heap.peekMax() == 9 : "Expected root 9, got " + heap.peekMax();
        System.out.println("testHeapifyDown passed");
    }

    static void testRebuildHeap() {
        Integer[] arr = {1, 2, 3, 4, 5};
        MaxHeap<Integer> heap = new MaxHeap<>(arr);
        assert heap.peekMax() == 5 : "Expected root 5, got " + heap.peekMax();
        assert heap.size() == 5 : "Expected size 5, got " + heap.size();
        System.out.println("testRebuildHeap passed");
    }

    static void testExtractMax() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        int max = heap.extractMax();
        assert max == 8 : "Expected 8, got " + max;
        assert heap.size() == 3 : "Expected size 3, got " + heap.size();
        System.out.println("testExtractMax passed");
    }

    static void testExtractOrdering() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.insert(4);
        heap.insert(10);
        heap.insert(7);
        heap.insert(2);
        heap.insert(15);
        assert heap.extractMax() == 15 : "Expected 15";
        assert heap.extractMax() == 10 : "Expected 10";
        assert heap.extractMax() == 7  : "Expected 7";
        assert heap.extractMax() == 4  : "Expected 4";
        assert heap.extractMax() == 2  : "Expected 2";
        assert heap.isEmpty() : "Expected empty heap";
        System.out.println("testExtractOrdering passed");
    }

    static void testEmptyHeapThrows() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        boolean threw = false;
        try {
            heap.extractMax();
        } catch (java.util.NoSuchElementException e) {
            threw = true;
        }
        assert threw : "Expected NoSuchElementException on empty extractMax";

        threw = false;
        try {
            heap.peekMax();
        } catch (java.util.NoSuchElementException e) {
            threw = true;
        }
        assert threw : "Expected NoSuchElementException on empty peekMax";
        System.out.println("testEmptyHeapThrows passed");
    }
}
