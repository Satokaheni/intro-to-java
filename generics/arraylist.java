package generics; 

public class Arraylist<T> {
    private T[] list;
    private int size = 0;
    private int length = 0;

    Arraylist() {
        this.list = new T[2];
        this.size = 2;
    }

    private T[] grow() {
        T[] temp = new T[this.size*2];
        for (i = 0; i < this.length; i++) {
            temp[i] = this.list[i];
        }
        return temp;
    }

    private void add(T element, int position) {
        if (this.length >= this.size) {
            this.list = grow();
            this.size = this.size * 2;
        }
        this.list[position] = element;
    }

    public boolean add(T element) {
        this.add(element, this.length);
        this.length++;
        return true;
    }

    public boolean add(T element, int position) {
        if (position > this.length) {
            return false;
        }
        this.add(element, position);
        return true;
    }
}