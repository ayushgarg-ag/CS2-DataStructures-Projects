package cs2.queue;

import cs2.linkedList2.CS2LinkedList;

import java.util.ListIterator;

public class CS2PriorityQueue<E extends Comparable<E>> extends CS2LinkedList<E> {

    public E remove() {
        return super.remove(0);
    }

    public boolean add(E item) {
        if (mySize == 0 || tail.getValue().compareTo(item) <= 0) {
            return super.add(item);
        }
        ListIterator<E> iter = this.listIterator();
        E larger;
        while (iter.hasNext()) {
            larger = iter.next();
            if (item.compareTo(larger) < 0) {
                iter.previous();
                iter.add(item);
                return true;
            }
        }
        return false;
    }

    public E element() {
        if (this.get(0) == null) {
            throw new IllegalStateException("There is no head element");
        }
        return this.get(0);
    }

    public boolean offer(E item) {
        try {
            add(item);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public E poll() {
        E obj;
        try {
            obj = remove(0);
        } catch (Exception e) {
            return null;
        }
        return obj;
    }

    public E peek() {
        E obj;
        try {
            obj = element();
        } catch (Exception e) {
            return null;
        }
        return obj;
    }
}
