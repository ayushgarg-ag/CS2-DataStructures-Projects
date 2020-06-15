package cs2.bst;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BST <E extends Comparable<E>> implements Iterable<E> {
    private TreeNode root;
    private int size;

    public boolean remove (E value) {
        return remove(root, new TreeNode(value));
    }

    private boolean remove (TreeNode subRoot, TreeNode item) {
        // add special cases for root
        if (contains(item.getValue()) == false) {
            return false;
        }
        if (item.compareTo(subRoot) > 0) {
            return remove(subRoot.getRight(), item);
        }
        else if (item.compareTo(subRoot) < 0) {
            return remove(subRoot.getLeft(), item);
        }
        // else -- item equals subroot
        else {
            // removing leaf node
            if (subRoot.getLeft() == null && subRoot.getRight() == null) {
                if (subRoot.getParent() == null) {
                    root = null;
                    return true;
                }
                if (subRoot.getParent().getLeft() != null && subRoot.getParent().getLeft().equals(item)) {
                    subRoot.getParent().setLeft(null);
                } else if (subRoot.getParent().getRight() != null && subRoot.getParent().getRight().equals(item)) {
                    subRoot.getParent().setRight(null);
                }

                size--;
                return true;
            }
            // removing 1-child node if child is on left
            else if (subRoot.getLeft() != null && subRoot.getRight() == null) {
                if (subRoot.equals(root)) {
                    root = subRoot.getLeft();
                    return true;
                }
                if (subRoot.getParent().getLeft() != null && subRoot.getParent().getLeft().equals(subRoot)) {
                    subRoot.getParent().setLeft(subRoot.getLeft());
                } else if (subRoot.getParent().getRight() != null && subRoot.getParent().getRight().equals(subRoot)) {
                    subRoot.getParent().setRight(subRoot.getLeft());
                }
                size--;
                return true;
            }
            // removing 1-child node if child is on right
            else if (subRoot.getRight() != null && subRoot.getLeft() == null) {
                if (subRoot.equals(root)) {
                    root = subRoot.getRight();
                    return true;
                }
                if (subRoot.getParent().getLeft() != null && subRoot.getParent().getLeft().equals(subRoot)) {
                    subRoot.getParent().setLeft(subRoot.getRight());
                } else if (subRoot.getParent().getRight() != null && subRoot.getParent().getRight().equals(subRoot)) {
                    subRoot.getParent().setRight(subRoot.getRight());
                }
                size--;
                return true;
            }
            // removing 2-child node
            else if (subRoot.getRight() != null && subRoot.getLeft() != null) {
                TreeNode largestLeft = largestOnLeft(subRoot.getLeft());
                E val = largestLeft.getValue();
                if (subRoot.getLeft() == null) {
                    remove(subRoot, largestLeft);
                }
                else {
                    remove(subRoot.getLeft(), largestLeft);
                }
                size++;
                subRoot.setValue(val);
                size--;
                return true;
            }
            return false;
        }
    }

    private TreeNode largestOnLeft(TreeNode subRoot) {
        if (subRoot.getRight() == null && subRoot.getLeft() == null) {
            return subRoot;
        }
        else if (subRoot.getRight() != null) {
            return largestOnLeft(subRoot.getRight());
        }
        else if (subRoot.getLeft() != null) {
            return subRoot;
        }
        return subRoot;
    }

    public boolean add (E value) {
        if (root == null) {
            root = new TreeNode (value);
            size++;
            return true;
        }
        else {
            return add(root, new TreeNode(value));
        }
    }

    private boolean add (TreeNode subRoot, TreeNode item) {
        if (item.equals(subRoot)) {
            return false;
        }
        if (item.getValue().compareTo(subRoot.getValue()) > 0) {
            if (subRoot.getRight() == null) {
                subRoot.setRight(item);
                item.setParent(subRoot);
                size++;
            }
            else {
                return add (subRoot.getRight(), item);
            }
        }
        else if (item.getValue().compareTo(subRoot.getValue()) < 0) {
            if (subRoot.getLeft() == null) {
                subRoot.setLeft(item);
                item.setParent(subRoot);
                size++;
            }
            else {
                return add (subRoot.getLeft(), item);
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public boolean contains (E value) {
        if (root == null) {
            return false;
        }
        return contains(root, new TreeNode(value));
    }

    private boolean contains (TreeNode subRoot, TreeNode item) {
        // recursively check for item in the sub-tree
        if (item.equals(subRoot)) {
            return true;
        }
        if (item.getValue().compareTo(subRoot.getValue()) > 0) {
            if (subRoot.getRight() == null) {
                return false;
            }
            return contains (subRoot.getRight(), item);
        }
        else if (item.getValue().compareTo(subRoot.getValue()) < 0) {
            if (subRoot.getLeft() == null) {
                return false;
            }
            return contains (subRoot.getLeft(), item);
        }
        return false;
    }

    public int level (E value) {
        return level (root, new TreeNode(value), 0);
    }

    private int level (TreeNode subRoot, TreeNode item, int level) {
        // recursively get the level of the item
        // if found, return level
        // when recursing, increment level
        if (contains(item.getValue()) == false) {
            return -1;
        }
        if (item.equals(subRoot)) {
            return level;
        }
        if (item.getValue().compareTo(subRoot.getValue()) > 0) {
            return level (subRoot.getRight(), item, level + 1);
        }
        else if (item.getValue().compareTo(subRoot.getValue()) < 0) {
            return level (subRoot.getLeft(), item, level + 1);
        }
        return -1;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public List<E> inOrderTraversal() {
        List<E> list = new LinkedList<E>();
        inOrderTraversal (root, list);
        return list;
    }

    private void inOrderTraversal(TreeNode subRoot, List<E> list) {
        // build list recursively
        if (subRoot.getLeft() != null) {
            inOrderTraversal(subRoot.getLeft(), list);
        }
        list.add(subRoot.getValue());
        if (subRoot.getRight() != null) {
            inOrderTraversal(subRoot.getRight(), list);
        }
    }

    public List<E> reverseOrderTraversal() {
        List<E> list = new LinkedList<E>();
        reverseOrderTraversal (root, list);
        return list;
    }

    private void reverseOrderTraversal(TreeNode subRoot, List<E> list) {
        if (subRoot.getRight() != null) {
            reverseOrderTraversal(subRoot.getRight(), list);
        }
        list.add(subRoot.getValue());
        if (subRoot.getLeft() != null) {
            reverseOrderTraversal(subRoot.getLeft(), list);
        }
    }

    public List<E> postOrderTraversal() {
        List<E> list = new LinkedList<E>();
        postOrderTraversal (root, list);
        return list;
    }

    private void postOrderTraversal(TreeNode subRoot, List<E> list) {
        if (subRoot.getLeft() != null) {
            postOrderTraversal(subRoot.getLeft(), list);
        }
        if (subRoot.getRight() != null) {
            postOrderTraversal(subRoot.getRight(), list);
        }
        list.add(subRoot.getValue());
    }

    public List<E> preOrderTraversal() {
        List<E> list = new LinkedList<E>();
        preOrderTraversal (root, list);
        return list;
    }

    private void preOrderTraversal(TreeNode subRoot, List<E> list) {
        list.add(subRoot.getValue());
        if (subRoot.getLeft() != null) {
            preOrderTraversal(subRoot.getLeft(), list);
        }
        if (subRoot.getRight() != null) {
            preOrderTraversal(subRoot.getRight(), list);
        }
    }

    public String toString() {
        List<E> list = reverseOrderTraversal();
        String str = "";
        for (E item: list) {
            for (int i = 0; i < level(item); i++) {
                str += " -";
            }
            str += " " + item;
            str += "\n";
        }
        return str;
    }

    public Iterator<E> iterator () {
        return new CS2TreeIterator();
    }

    private class CS2TreeIterator implements Iterator<E> {
        private List<E> list = inOrderTraversal();
        private Iterator<E> iter = list.iterator();

        public boolean hasNext() {
            return iter.hasNext();
        }

        public E next() {
            return iter.next();
        }
    }

    private class TreeNode implements Comparable<TreeNode> {
        private E value;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(E val) {
            value = val;
            left = null;
            right = null;
            parent = null;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public boolean equals(TreeNode other) {
            if (this.getValue().equals(other.getValue())) {
                return true;
            }
            else {
                return false;
            }
        }

        public int compareTo(TreeNode other) {
            return this.getValue().compareTo(other.getValue());
        }

        public String toString() {
            return value.toString();
        }
    }
}