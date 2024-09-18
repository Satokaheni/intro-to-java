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

    private void rangeCheck(int index) {
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
        this.rangeCheck(index);
        this.list[index] = element;
        this.length++;
    }

    private void shiftRemoval(int index) {
        for(int i = index; i <= this.length-1; i++) {
            this.list[i] = this.list[i+1];
        }
        this.length--;
    }
    
    public int indexOf(E element) {
        int index = -1;

        if (this.contains(element)) {
            for(int i = 0; i <= this.length; i++) {
                if (this.list[i].equals(element)) {
                    index = i;
                }
            }
        }

        return index;
    }

    public void remove(int index) {
        this.shiftRemoval(index);
    }

    public void remove(E element) {
        int index = this.indexOf(element);

        if (index != -1) {
            this.shiftRemoval(index);
        }
    }

    public E get(int index) {
        this.rangeCheck(index);
        return this.list[index];
    }

    public void clear() {
        for(int i = 0; i <= this.length; i++) {
            this.list[i] = null;
        }
    }

    public boolean contains(E element) {
        boolean in = false;
        for(int i = 0; i <= this.length; i++) {
            if (this.list[i].equals(element)) {
                in = true;
            }
        }

        return in;
    }

    public int size() {
        return this.length;
    }
}