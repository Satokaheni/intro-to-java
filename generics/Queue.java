package generics;

public class Queue<E> {
    // only queue operations queue, dequeue and peek

    private Linkedlist<E> queue;
    
    Queue() {
        this.queue = new Linkedlist<E>();
    }

    // queue - adding something to the end of the queue
    public void queue(E element) {
        this.queue.add(element);
    }

    //peek - see whats at the front of the queue
    public E peek() {
        return this.queue.get(0);
    }

    //dequeue - remove the first element of the queue and return it
    public E dequeue() {
        E element = this.peek();

        this.queue.remove(0);

        return element;
    }
}
