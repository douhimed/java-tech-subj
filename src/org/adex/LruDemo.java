package org.adex;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruDemo {

    public static void main(String[] args) {
        System.out.println(1 << 4);
        Map<String, String> map = new LruLinkedHashMap<>(5);
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.put("e", "e");
        map.put("f", "f");
        System.out.println(map);
        map.get("c");
        System.out.println(map);
        map.get("d");
        map.put("g", "g");
        System.out.println(map);
    }

}

class LruLinkedHashMap<T, U> extends LinkedHashMap<T, U> {

    private int maxSize = 8;

    public LruLinkedHashMap(int maxSize) {
        this();
        this.maxSize = maxSize;
    }

    public LruLinkedHashMap() {
        super(1 << 4, .75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<T, U> eldest) {
        return size() > maxSize;
    }

}
