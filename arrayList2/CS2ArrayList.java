package cs2.arrayList2;

import cs2.CS2List;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CS2ArrayList<E> implements CS2List<E>, Iterable<E> {

    private E[] myList;
    private int mySize;

    public CS2ArrayList() {
        myList = (E[])(new Object[20]);
        mySize = 0;
    }

    public Iterator<E> iterator() {
        return new CS2Iterator();
    }


    @Override
    public int size() {
        return mySize;
    }

    @Override
    public boolean add(E obj) {
        if (mySize > myList.length) {
            expand();
        }
        myList[mySize] = obj;
        mySize++;
        return true;
    }

    @Override
    public void add(int index, E obj) {
        if (index < 0 || index > mySize) {
            throw new RuntimeException("Index out of bounds");
        }
        if (mySize >= myList.length) {
            expand();
        }

        for (int i = mySize; i > index; i--) {
            myList[i] = myList[i - 1];
        }
        myList[index] = obj;
        mySize++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > mySize - 1) {
            throw new RuntimeException("Index out of bounds");
        }
        return myList[index];
    }

    @Override
    public E set(int index, E obj) {
        if (index < 0 || index > mySize - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        E previousValue = myList[index];
        myList[index] = obj;
        return previousValue;
    }

    @Override
    public E remove(int index) {
        E previousValue = myList[index];
        if (index < 0 || index > mySize - 1) {
            throw new RuntimeException("Index out of bounds");
        }

        for (int i = index; i < mySize - 1; i++) {
            myList[i] = myList[i + 1];
        }
        mySize--;
        return previousValue;
    }

    public String toString() {
        String rep = "";
        rep += "{";
        for (int i = 0; i < mySize; i++) {
            if (i == mySize - 1) {
                rep += "[" + i + "]:" + myList[i];
            }
            else {
                rep += "[" + i + "]:" + myList[i] + " ";
            }
        }
        rep += "}";
        return rep;
    }

    public void expand() {
        E[] newList = (E[])(new Object[(int)(1.5*myList.length)]);
        for (int i = 0; i < myList.length; i++) {
            newList[i] = myList[i];
        }
        myList = newList;
    }

    public boolean contains(E obj) {
        if (obj == null) {
            return false;
        }
        for (E item: myList) {
            if (item.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(CS2ArrayList newList) {
        for (int i = 0; i < mySize; i++) {
            if (myList[i].equals(newList.get(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(E obj) {
        for (int i = 0; i < mySize; i++) {
            if (myList[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        myList = (E[])(new Object[20]);
        mySize = 0;
    }

    public boolean isEmpty() {
        if (mySize == 0) {
            return true;
        }
        return false;
    }

    public ListIterator<E> listIterator() {
        return new CS2Iterator();
    }

    private class CS2Iterator implements ListIterator<E> {
        private int index = 0;
        private boolean previousRemove = true;
        private boolean nextCalled;

        public boolean hasNext() {
            if (index < mySize) {
                return true;
            }
            return false;
        }

        public E next() {
            if (hasNext() == false) {
                throw new NoSuchElementException("There is no value next");
            }
            previousRemove = false;
            index++;
            nextCalled = true;
            return myList[index - 1];
        }

        public boolean hasPrevious() {
            if (index > 0) {
                return true;
            }
            return false;
        }

        public E previous() {
            if (hasPrevious() == false) {
                throw new NoSuchElementException("There is no previous value");
            }
            previousRemove = false;
            index--;
            nextCalled = false;
            return myList[index];

        }

        public int nextIndex() {
            if (index == mySize) {
                return mySize;
            }
            return index;
        }

        public int previousIndex() {
            if (index == 0) {
                return -1;
            }
            return index - 1;
        }


        public void remove() {
            if (previousRemove == true || mySize <= 0) {
                throw new IllegalStateException("The next method has not yet been called, or the remove/add method has already been called after the last call to the next method");
            }
            previousRemove = true;
            if (nextCalled == true) {
                CS2ArrayList.this.remove(index - 1);
            }
            else {
                CS2ArrayList.this.remove(index);
            }
            index--;
        }

        public void set (E obj) {
            if (previousRemove == true) {
                throw new IllegalStateException("The next method has not yet been called, or the remove/add method has already been called after the last call to the next method");
            }
            if (nextCalled == true) {
                CS2ArrayList.this.set(index - 1, obj);
            }
            else {
                CS2ArrayList.this.set(index, obj);
            }
        }

        public void add (E obj) {
            previousRemove = true;
            CS2ArrayList.this.add(index, obj);
            index++;
        }

    }

}