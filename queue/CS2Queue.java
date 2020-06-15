package cs2.queue;

import cs2.linkedList2.CS2LinkedList;

public class CS2Queue<E> extends CS2LinkedList<E> {
    // @Override
    public boolean add(E item) {
        return super.add(item);
    }

    public E remove() {
        return super.remove(0);
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
            obj = remove();
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
