package cs2.bst;

public class BSTRunner {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<Integer>();
        int[] vals = {3, 7, 2, 9, 3, 8, 6, 4, 1, 5, 9};

        // build tree
        for (int v: vals) {
            if (tree.add (v)) {
                System.out.println (v + " was added to the tree");
            }
            else {
                System.out.println (v + " was NOT added to the tree");
            }
        }

        System.out.println();
        vals = new int[] {3, 7, 2, 9, 8, 6, 4, 1, 5, 10};
        for (int v: vals) {
            if (tree.contains(v)) {
                System.out.println(v + " is in the tree");
            }
            else {
                System.out.println(v + " is NOT in the tree");
            }
        }

        System.out.println(); System.out.println();
        for (int v: vals) {
            System.out.println(v + " is at level " + tree.level(v));
        }

        System.out.println();
        System.out.println (tree.inOrderTraversal ());
        System.out.println (tree.reverseOrderTraversal ());
        System.out.println (tree.postOrderTraversal());
        System.out.println (tree.preOrderTraversal());

        System.out.println();
        System.out.println (tree);

        for (Integer i: tree) {
            System.out.println (i + " ");
        }

        System.out.println();
        tree.clear();
        System.out.println ("Size is: " + tree.size());
        vals = new int[] {2, 1, 7, 4, 9, 3, 6, 8, 5};
        for (int i: vals) tree.add (i);
        System.out.println ("Size is: " + tree.size());
        System.out.println (tree);


        System.out.println("Part 2 Runner");
        tree.clear();
        System.out.println ("Size is: " + tree.size());
        vals = new int[] {2, 1, 7, 4, 9, 3, 6, 8, 5};
        for (int i: vals) tree.add (i);
        System.out.println ("Size is: " + tree.size());
        System.out.println (tree);

        tree.remove(8);
        System.out.println ("Size is: " + tree.size());
        System.out.println (tree);
        tree.add (8);

        tree.remove(6);
        System.out.println ("Size is: " + tree.size());
        System.out.println (tree);
        tree.add (6);

        tree.remove(2);
        System.out.println ("Size is: " + tree.size());
        System.out.println (tree);
        tree.add (2);

        tree.remove(7);
        System.out.println ("Size is: " + tree.size());
        System.out.println (tree);

    }
}
