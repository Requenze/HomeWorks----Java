import java.util.Objects;

public class CustomHashMap<K, V> {
    private MyMapBucket[] bucket;
    private int CAPACITY = 10;
    private int size = 0;

    public CustomHashMap() {
        this.bucket = new MyMapBucket[CAPACITY];
    }

    public int size() {
        return size;
    }

    private int getHash(K key) {
        return key.hashCode() & (CAPACITY - 1);
    }

    private MyKeyValueEntry getEntry(K key) {
        int hash = getHash(key);
        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            MyKeyValueEntry myKeyValueEntry = bucket[hash].getEntries().get(i);
            if (myKeyValueEntry.getKey().equals(key)) {
                return myKeyValueEntry;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int hash = getHash(key);
        return !(Objects.isNull(bucket[hash]) || Objects.isNull(getEntry(key)));
    }
    public V put(K key, V value) {
        if (containsKey(key)) {
            MyKeyValueEntry entry = getEntry(key);
            V temp = (V) entry.getValue();
            entry.setValue(value);
            return temp;
        } else {
            int hash = getHash(key);
            if (bucket[hash] == null) {
                bucket[hash] = new MyMapBucket();
            }
            bucket[hash].addEntry(new MyKeyValueEntry<>(key, value));
            size++;
        }
        return null;
    }

    public V get(K key) {
        return containsKey(key) ? (V) getEntry(key).getValue() : null;
    }
    public V remove(K key) {
        if(containsKey(key)) {
            V temp = get(key);
            int hash = getHash(key);
            bucket[hash].removeEntry(getEntry(key));
            size--;
            return temp;
        }
        return null;
    }

    public static void main(String[] args) {
        CustomHashMap<Integer, String> myHashMap = new CustomHashMap<>();
        myHashMap.put(1, "One");
        myHashMap.put(2, "Two");
        myHashMap.put(3, "Three");
        System.out.println(myHashMap.size());
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.remove(1));
        System.out.println(myHashMap.size());
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.containsKey(1));
        System.out.println(myHashMap.containsKey(2));
        System.out.println(myHashMap.put(1,"One"));
        System.out.println(myHashMap.put(1,"Two"));
    }
}
