package cs2.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Heap<E extends Comparable<E>> {
    private ArrayList<E> arrayList;

    public Heap() {
        arrayList = new ArrayList<E>();
    }

    public void add (E value) {
        arrayList.add(value);
        int current = size() - 1;
        E parent = arrayList.get(parent(current));
        while (arrayList.get(parent(current)).compareTo(arrayList.get(current)) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    private void swap(int first, int second) {
        E valOne = arrayList.get(first);
        E valTwo = arrayList.get(second);
        arrayList.set(first, valTwo);
        arrayList.set(second, valOne);
    }

    public E remove() {
        E root = arrayList.get(0);
        arrayList.set(0, arrayList.get(size() - 1));
        arrayList.remove(size()-1);
        int current = 0;
        while ((leftChild(current) < size() && rightChild(current) < size()) &&
                (arrayList.get(leftChild(current)).compareTo(arrayList.get(current)) < 0
                || arrayList.get(rightChild(current)).compareTo(arrayList.get(current)) < 0)) {
            if (arrayList.get(leftChild(current)).compareTo(arrayList.get(rightChild(current))) > 0) {
                swap(current, rightChild(current));
                current = rightChild(current);
            }
            else {
                swap(current, leftChild(current));
                current = leftChild(current);
            }
        }
        return root;
    }

    private int rightChild (int i) {
        return 2*i + 2;
    }

    private int leftChild (int i) {
        return 2*i + 1;
    }

    private int parent (int i) {
        return ((i - 1) / 2);
    }

    public String toString() {
        return toString(0, 0);
    }

    private String toString(int index, int level) {
        String str = "";
        if (index >= arrayList.size()) {
            return "";
        }
        for (int i = 0; i < level; i++) {
            str += "-- ";
        }
        return  toString(rightChild(index), level + 1) + str + arrayList.get(index) + "\n" + toString(leftChild(index), level + 1);
    }

    public void clear() {
        arrayList.clear();
    }

    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    public Iterator<E> iterator() { return new CS2HeapIterator(); }

    private class CS2HeapIterator implements Iterator<E> {
        private ArrayList<E> list;
        private Iterator<E> iter;

        private CS2HeapIterator() {
            int size = size();
            for (int i = size; i >= 0; i--) {
                list.add(Heap.this.remove());
            }
            for (int i = size; i >= 0; i--) {
                Heap.this.add(list.remove(i));
            }
            iter = list.iterator();
        }

        public boolean hasNext() {
            return iter.hasNext();
        }

        public E next() {
            return (E)iter.next();
        }

    }
}
