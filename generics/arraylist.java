package generics; 

import java.lang.IndexOutOfBoundsException;

public class Arraylist<E> {
    private E[] list;
    private int size = 2;
    private int length = 0;

    Arraylist() {
        this.list = (E[])new Object[this.size];
    }

    Arraylist(int initialCapacity) {
        this.size = initialCapacity;
        this.list = (E[])new Object[this.size];
    }

    private void rangeCheckForAdd(int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of Bounds. ArraySize: " + String.valueOf(this.size));
        }
    }

    private E[] grow() {
        E[] temp = (E[])new Object[this.size*2];
        for (int i = 0; i < this.length; i++) {
            temp[i] = this.list[i];
        }
        return temp;
    }

    private void add(E element, int position) {
        if (this.length >= this.size) {
            this.list = this.grow();
            this.size = this.size * 2;
        }
        this.list[position] = element;
    }

    public void add(E element) {
        this.add(element, this.length);
        this.length++;
    }

    public void add(int index, E element) {
        this.rangeCheckForAdd(index);
        this.list[index] = element;
        this.length++;
    }
}