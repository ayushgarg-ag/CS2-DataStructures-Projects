package cs2.hash;

import java.util.*;

public class CS2HashMap<K extends Comparable<K>, V> {
    ArrayList<TreeMap<K, V>> arrayList;
    private int arraySize;

    public CS2HashMap(int size) {
        arrayList = new ArrayList<TreeMap<K, V>>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new TreeMap<K, V>());
        }
        arraySize = arrayList.size();
    }

    public void clear() {
        for (int i = 0; i < arraySize; i++) {
            arrayList.get(i).clear();
        }
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            size += arrayList.get(i).size();
        }
        return size;
    }

    public V get(K key) {
        return arrayList.get(key.hashCode() % arraySize).get(key);
    }

    public V put(K key, V value) { // returns null or replaced value
        return arrayList.get(key.hashCode() % arraySize).put(key, value);
    }

    public String toString() { // display all K->V pairings (no ordering necessary) {
        String str = "";
        for (TreeMap<K, V> map : arrayList) {
            for (K key : map.keySet()) {
                str += key + " --> " + map.get(key);
                str += "\n";
            }
        }
        return str;
    }

    public boolean containsKey(K key) {
        return arrayList.get(key.hashCode() % arraySize).containsKey(key);
        // for (TreeMap<K, V> map: arrayList) {
        // for (K tkey: map.keySet()) {
        // if (tkey.equals(key)) {
        // return true;
        // }
        // }
        // }
        // return false;
    }

    public boolean containsValue(V value) {
        for (TreeMap<K, V> map : arrayList) {
            for (K tkey : map.keySet()) {
                if (map.get(tkey).equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public Set<K> keySet() {
        Set<K> set = new TreeSet<K>();
        for (TreeMap<K, V> map : arrayList) {
            set.addAll(map.keySet());
        }
        return set;
    }

    public V remove(K key) {
        if ((key.hashCode() % arraySize) < 0 || (key.hashCode() % arraySize) >= arraySize) {
            return null;
        }
        return arrayList.get(key.hashCode() % arraySize).remove(key);
    }

    public Collection<V> values() {
        Collection<V> vals = new ArrayList<V>();
        for (TreeMap<K, V> map : arrayList) {
            for (K tkey : map.keySet()) {
                vals.add(map.get(tkey));
            }
        }
        return vals;
    }

}
