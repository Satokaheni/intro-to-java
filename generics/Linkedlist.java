package generics;

import java.lang.IndexOutOfBoundsException;

public class Linkedlist<E>{

    private class Node {
        E element;
        Node next = null;

        Node(E element) {
            this.element = element;
        }
    }

    private Node header;
    private int size = 0;

    Linkedlist() {this.header = null;}

    // bounds checking
    private boolean checkIndex(int index) {
        //out of bounds
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of Bounds. ArraySize: " + String.valueOf(this.size));
        }
        return true;
    }

    // add at specific index
    public void add(E element, int index) {
        if (this.checkIndex(index)) {
            Node current = this.header;

            for(int i = 0; i < index-1; i++) {
                current = current.next;
            }

            Node insertNode = new Node(element);
            insertNode.next = current.next;
            current.next = insertNode;
        }
        size++;
    }
    
    // add to the end of the list
    public void add(E element) {
        //no header first element
        if (this.header == null) {
            this.header = new Node(element);
        }
        //header not first element
        else {
            this.add(element, this.size);
        }
        this.size++;
    }

    public void remove(int index) {
        if (this.checkIndex(index)) {
            Node current = this.header;

            for(int i = 0; i < index-1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
        }
        this.size--;
    }

    public E get(int index) {
        if (this.checkIndex(index)) {
            Node current = this.header;

            for(int i = 0; i <= index; i++) {
                current = current.next;
            }

            return current.element;
        }

        return null;
    }

    public void reverse() {
        Node current = this.header;
        Node previous = null;
        Node next = this.header;

        while(current.next != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        this.header = current;
    }
}
