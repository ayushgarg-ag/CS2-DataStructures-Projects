package cs2.linkedList;

import cs2.CS2List;

import java.util.List;

public class CS2LinkedList<E> implements CS2List<E> {
    private ListNode head;
    private ListNode tail;
    private int mySize;

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
        }
        else {
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
        }
        else if (index == 0) {
            ListNode aNode = new ListNode(head.getValue());
            aNode.setNext(head.getNext());
            head = objListNode;
            objListNode.setNext(aNode);
        }
        else {
            ListNode aNode = head;
            for (int i = 0; i < index - 1; i++) {
                aNode = aNode.getNext();
            }
            ListNode nextNode = aNode.getNext();
            aNode.setNext(objListNode);
            objListNode.setNext(nextNode);
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
        }
        else if (index == mySize - 1) {
            ListNode node = head;
            while (node.getNext().getNext() != null) {
                node = node.getNext();
            }
            old = node.getNext().getValue();
            node.setNext(null);
        }
        else {
            ListNode aNode = head;
            for (int i = 0; i < index - 1; i++) {
                aNode = aNode.getNext();
            }
            old = aNode.getNext().getValue();
            aNode.setNext(aNode.getNext().getNext());
        }

        mySize--;
        if (mySize == 0) {
            head = null;
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

    private class ListNode {
        private E value;
        private ListNode next;

        public ListNode(E val) {
            value = val;
            next = null;
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
    }
}