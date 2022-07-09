package org.adex;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyHashMapDemo {
    public static void main(String[] args) {

        final MyHashTable<Integer, String> hashTable = new MyHashTable<>();
        hashTable.put(1, "one");
        hashTable.put(2, "two");
        hashTable.put(41, "foor-one"); // 41 % 101 = 41
        hashTable.put(243, "two-foor-three"); // 243 % 101 = 41 (override the previous put operation
        System.out.println(hashTable);
    }
}

class MyHashTable<K, V> {

    private final Object[] values = new Object[101];

    public void put(K key, V value) {
        values[calculatePosition(key)] = value;
    }

    public V get(K key) {
        return (V) values[calculatePosition(key)];
    }

    private int calculatePosition(K key) {
        final int hash = key.hashCode();
        int position = hash % values.length;
        if (position < 0)
            position += values.length;
        return position;
    }

    @Override
    public String toString() {
        return Stream.of(values)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(",", "{", "}"));
    }
}

