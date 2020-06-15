package cs2.linkedList2;

import cs2.CS2List;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CS2LinkedList<E> implements CS2List<E>, Iterable<E> {
    protected ListNode head;
    protected ListNode tail;
    protected int mySize;

    public CS2LinkedList() {
        head = null;
        tail = null;
        mySize = 0;
    }

    @Override
    public int size() {
        return mySize;
    }

    @Override
    public boolean add(E item) {
        ListNode newListNode = new ListNode(item);
        if (mySize == 0) {
            head = newListNode;
            tail = newListNode;
        } else {
            newListNode.setPrev(tail);
            tail.setNext(newListNode);
            tail = newListNode;
        }
        mySize++;
        return true;
    }

    @Override
    public void add(int index, E obj) {
        ListNode objListNode = new ListNode(obj);
        if (index < 0 || index > mySize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == mySize) {
            add(obj);
            return;
        } else if (index == 0) {
            objListNode.setNext(head);
            head.setPrev(objListNode);
            head = objListNode;
        } else {
            ListNode aNode = head;
            for (int i = 0; i < index - 1; i++) {
                aNode = aNode.getNext();
            }
            ListNode nextNode = aNode.getNext();
            aNode.setNext(objListNode);
            objListNode.setPrev(aNode);
            objListNode.setNext(nextNode);
            nextNode.setPrev(objListNode);
        }
        mySize++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ListNode aNode = head;
        for (int i = 0; i < index; i++) {
            aNode = aNode.getNext();
        }
        return aNode.getValue();
    }

    @Override
    public E set(int index, E obj) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        ListNode aNode = head;
        E old;
        for (int i = 0; i < index; i++) {
            aNode = aNode.getNext();
        }
        old = aNode.getValue();
        aNode.setValue(obj);
        return old;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E old;

        if (mySize == 1) {
            old = head.getValue();
            head = null;
            tail = null;
            mySize--;
            return old;
        }
        if (index == 0) {
            old = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        } else if (index == mySize - 1) {
            ListNode node = head;
            while (node.getNext().getNext() != null) {
                node = node.getNext();
            }
            old = node.getNext().getValue();
            node.setNext(null);
            tail = node;
        } else {
            ListNode aNode = head;
            for (int i = 0; i < index - 1; i++) {
                aNode = aNode.getNext();
            }
            old = aNode.getNext().getValue();
            aNode.getNext().getNext().setPrev(aNode);
            aNode.setNext(aNode.getNext().getNext());
        }
        mySize--;
        if (mySize == 0) {
            head = null;
            tail = null;
        }
        return old;
    }

    public String toString() {
        String str = "";
        int i = 0;
        if (mySize == 0) {
            return "empty list";
        }
        ListNode aNode = head;
        while (aNode != null) {
            str += "[" + i + "]:" + aNode.getValue() + " ";
            i++;
            aNode = aNode.getNext();
        }
        return str;
    }

    protected class ListNode {
        private E value;
        private ListNode next;
        private ListNode prev;

        public ListNode(E val) {
            value = val;
            next = null;
            prev = null;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode getPrev() {
            return prev;
        }

        public void setPrev(ListNode prev) {
            this.prev = prev;
        }
    }

    public Iterator<E> iterator() {
        return new CS2Iterator();
    }

    public ListIterator<E> listIterator() {
        return new CS2Iterator();
    }

    private class CS2Iterator implements ListIterator<E> {
        private ListNode nextNode = head;
        private int index = 0;
        private boolean previousRemove = true;
        private boolean nextCalled;

        @Override
        public boolean hasNext() {
            if (index < mySize) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            if (hasNext() == false) {
                throw new NoSuchElementException("There is no next value");
            }
            E old = nextNode.getValue();
            nextNode = nextNode.getNext();
            index++;
            nextCalled = true;
            previousRemove = false;
            return old;
        }

        @Override
        public boolean hasPrevious() {
            if (index > 0) {
                return true;
            }
            return false;
        }

        @Override
        public E previous() {
            if (hasPrevious() == false) {
                throw new NoSuchElementException("There is no previous value");
            }
            E old;
            if (index == mySize) {
                nextNode = tail;
                old = tail.getValue();
            } else {
                old = nextNode.getPrev().getValue();
                nextNode = nextNode.getPrev();
            }
            index--;
            nextCalled = false;
            previousRemove = false;
            return old;
        }

        @Override
        public int nextIndex() {
            if (index == mySize) {
                return mySize;
            }
            return index;
        }

        @Override
        public int previousIndex() {
            if (index == 0) {
                return -1;
            }
            return index - 1;
        }

        @Override
        public void remove() {
            if (previousRemove == true || mySize <= 0) {
                throw new IllegalStateException(
                        "The next method has not yet been called, or the remove/add method has already been called after the last call to the next method");
            }
            if (mySize == 1) {
                head = null;
                tail = null;
                index--;
                mySize--;
                return;
            }
            if (nextCalled == true) {
                if (nextNode == null) {
                    tail = tail.getPrev();
                    tail.setNext(null);
                } else if (nextNode.getPrev() == head) {
                    head = nextNode;
                    head.setPrev(null);
                } else {
                    nextNode.getPrev().getPrev().setNext(nextNode);
                    nextNode.setPrev(nextNode.getPrev().getPrev());
                }
                index--;
            } else {
                if (nextNode.getNext() == null) {
                    tail = nextNode.getPrev();
                    tail.setNext(null);
                } else {
                    nextNode.getPrev().setNext(nextNode.getNext());
                    nextNode.getNext().setPrev(nextNode.getPrev());
                }
            }
            mySize--;
            previousRemove = true;
        }

        @Override
        public void set(E obj) {
            if (previousRemove == true) {
                throw new IllegalStateException(
                        "The next method has not yet been called, or the remove/add method has already been called after the last call to the next method");
            }
            if (mySize == 1) {
                head.setValue(obj);
                tail.setValue(obj);
                return;
            }
            if (nextNode == null) {
                tail.setValue(obj);
                return;
            }
            if (nextCalled == true) {
                nextNode.getPrev().setValue(obj);
            } else {
                nextNode.setValue(obj);
            }
        }

        @Override
        public void add(E obj) {
            previousRemove = true;
            ListNode aNode = new ListNode(obj);

            if (mySize == 0) {
                head = aNode;
                tail = aNode;
                index++;
                return;
            } else if (nextNode == head) {
                head.setPrev(aNode);
                aNode.setNext(head);
                head = aNode;
            } else if (nextNode == null) {
                aNode.setPrev(tail);
                tail.setNext(aNode);
                tail = aNode;
            } else {
                aNode.setPrev(nextNode.getPrev());
                nextNode.getPrev().setNext(aNode);
                nextNode.setPrev(aNode);
                aNode.setNext(nextNode);
            }

            mySize++;
            index++;
        }
    }

}