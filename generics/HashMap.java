package generics;

public class HashMap<K,V> {
    class Node {
        K key;
        V value;
        int hash;
        boolean isActive;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
            this.isActive = true;
        }

        public void remove() {
            this.isActive = false;
        }
    }

    Node[] table;
    int capacity = 10;
    int size = 0;

    HashMap() {
        this.table = (Node[])new Object[capacity];
    }

    private int hashFuntion(Node node) {
        return node.hash % this.capacity;
    }

    private int hashFunction(K key) {
        return key.hashCode() % this.capacity;
    }

    private int collision(int hash) {
        return hash % this.capacity;
    }

    private int getIndex(Node node, Node[] t) {
        int i = 1;
        int index = this.hashFuntion(node);
        while(t[index] != null && t[index].isActive) {
            index = this.collision(node.hash + i);
            i++;
        }

        return index;
    }

    public void add(K key, V value) {
        Node node = new Node(key, value);
        int index = this.getIndex(node, this.table);
        table[index] = node;
        size++;
        this.rehash();
    }

    private Node searchNode(K key) {
        int i = 1;
        int originalIndex = this.hashFunction(key);
        int index = this.hashFunction(key);
        while(!this.table[index].key.equals(key)) {
            index = this.collision(key.hashCode() + i);
            i++;

            if (originalIndex == index) {
                return null;
            }
        }

        return this.table[index];
    }

    public Node get(K key) {
        return this.searchNode(key);
    }

    public void removal(K key) {
        Node node = this.searchNode(key);
        if (node != null) {
            node.remove();
        }
    }

    private void rehashing() {
        this.capacity = this.capacity * 2;
        Node[] temp = (Node[])new Object[this.capacity];
        for(int i = 0; i < this.table.length; i ++) {
            if (this.table[i] != null) {
                int index = this.getIndex(this.table[i], temp);
                temp[index] = this.table[i];
            }
        }
        this.table = temp;
    }

    private void rehash() {
        if ((double)this.size/(double)this.capacity > .5) {
            this.rehashing();
        }
    }
}
