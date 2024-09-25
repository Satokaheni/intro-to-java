package generics;

public class Stack<E> {
    // only operations are push, pop, and peek
    
    private Arraylist stack;

    Stack() {
        this.stack = new Arraylist<E>();
    }

    // push to the top of the stack
    public void push(E element) {
        this.stack.add(0, element);
    }

    // remove first element of the stack
    public E pop() {
        E element = this.peek();

        // remove element
        this.stack.remove(0);

        return element;
    }

    // Peek at what the top of the stack contains
    public E peek() {
        E element = (E)this.stack.get(0);

        return element;
    }
}
