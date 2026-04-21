import java.util.LinkedList;

public class HashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private int size;
    private int capacity;
    private final double loadFactor;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = new LinkedList[capacity];
    }

    public HashTable(int initialCapacity) {
        this(initialCapacity, 0.5);
    }

    public HashTable() {
        this(16, 0.5);
    }

    private int getBucket(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void add(K key, V value) {
        int index = getBucket(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;

        if ((double) size / capacity >= loadFactor) {
            rehash();
        }
    }

    public boolean remove(K key) {
        int index = getBucket(key);
        LinkedList<Entry<K, V>> chain = buckets[index];
        if (chain == null) return false;

        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                chain.remove(entry);
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean contains(K key) {
        int index = getBucket(key);
        LinkedList<Entry<K, V>> chain = buckets[index];
        if (chain == null) return false;

        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) return true;
        }
        return false;
    }

    public V get(K key) {
        int index = getBucket(key);
        LinkedList<Entry<K, V>> chain = buckets[index];
        if (chain == null) return null;

        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) return entry.value;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        capacity *= 2;
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new LinkedList[capacity];
        size = 0;

        for (LinkedList<Entry<K, V>> chain : oldBuckets) {
            if (chain == null) continue;
            for (Entry<K, V> entry : chain) {
                add(entry.key, entry.value);
            }
        }
    }

    public int size() {
        return size;
    }
}
