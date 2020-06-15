package cs2.set;

import cs2.bst.BST;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class CS2Set<E extends Comparable<E>> implements Set<E> {
    private BST<E> tree = new BST<E>();

    public boolean add(E item) {
        return tree.add(item);
    }

    public boolean addAll(Collection<? extends E> c) {
        for (Object o : c) {
            if (tree.add((E) o) == false) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        tree.clear();
    }

    public boolean contains(Object o) {
        return tree.contains((E) o);
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (tree.contains((E) o) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        if (tree.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Iterator<E> iterator() {
        return tree.iterator();
    }

    public boolean remove(Object o) {
        return tree.remove((E) o);
    }

    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (tree.remove((E) o) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        boolean tru = false;
        for (E o : tree.inOrderTraversal()) {
            if (c.contains((Object) o) == false) {
                tree.remove(o);
                tru = true;
            }
        }
        return tru;
    }

    public int size() {
        return tree.size();
    }

    public Object[] toArray() {
        ArrayList<Object> arraylist = new ArrayList<Object>();
        for (Object o : tree) {
            arraylist.add(o);
        }
        return arraylist.toArray();
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < tree.size()) {
            a = (T[]) new Object[tree.size()];
        }
        int i = 0;
        for (Object o : tree) {
            a[i++] = (T) o;
        }
        if (i < a.length) {
            a[i] = null;
        }
        return a;
    }
}
