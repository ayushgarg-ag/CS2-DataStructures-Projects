package cs2.stack;

import cs2.linkedList2.CS2LinkedList;

public class CS2Stack<E> extends CS2LinkedList<E> {
    public void push(E item) {
        add(item);
    }

    public E pop() {
        return remove(size() - 1);
    }
}
