package cs2.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CS2SetRunner {
    public static void main(String[] args) {
        CS2Set<Integer> set = new CS2Set<Integer>();
        int[] vals = { 2, 1, 7, 4, 9, 3, 6, 8, 5, 2 };
        System.out.println("Size: " + set.size());
        System.out.println("Empty? " + set.isEmpty());
        System.out.println("Contains value 7 (false)? " + set.contains(7));
        System.out.println("Remove value 5 (false)? " + set.remove(5));
        System.out.println("Add value 5 (true)? " + set.add(5));
        System.out.println("Does the value in the set equal 5 (true): " + set.equals(5));
        System.out.println("Remove value 5 (true)? " + set.remove(5));

        System.out.println();
        System.out.println("Testing Add");
        for (int v : vals) {
            if (set.add(v)) {
                System.out.println(v + " was added to the tree");
            } else {
                System.out.println(v + " was NOT added to the tree");
            }
        }

        System.out.println();
        System.out.println("Testing Contains");
        System.out.println("Size: " + set.size());
        System.out.println("Empty? " + set.isEmpty());
        System.out.println("Contains value 0? " + set.contains(0));
        System.out.println("Contains value 7? " + set.contains(7));

        System.out.println();
        System.out.println("Testing Clear");
        set.clear();
        System.out.println("Size: " + set.size());
        System.out.println("Empty? " + set.isEmpty());
        System.out.println("Contains value 7? " + set.contains(7));

        System.out.println();
        System.out.println("Testing Add");
        for (int v : vals) {
            if (set.add(v)) {
                System.out.println(v + " was added to the tree");
            } else {
                System.out.println(v + " was NOT added to the tree");
            }
        }

        System.out.println();
        System.out.println("Testing Iterator");
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println("Next: " + iter.next());
        }

        System.out.println();
        System.out.println("Testing ToArray");
        Object[] arr = set.toArray();
        System.out.println(Arrays.toString(arr));
        arr = new Object[1];
        set.toArray(arr);
        System.out.println("After toArray T" + Arrays.toString(arr));

        System.out.println();
        System.out.println("Testing ContainsAll");
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for (int v : vals) {
            arrList.add(v);
        }
        System.out.println("ContainsAll (true): " + set.containsAll(arrList));
        arrList.add(-3);
        System.out.println("ContainsAll (false): " + set.containsAll(arrList));
        iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println("Next: " + iter.next());
        }

        System.out.println();
        System.out.println("Testing AddAll");
        System.out.println("AddAll (false): " + set.addAll(arrList));
        arrList.clear();
        for (int i = 0; i < 5; i++) {
            arrList.add((int) (Math.random() * 100) + 10);
        }
        System.out.println("AddAll (true): " + set.addAll(arrList));
        iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println("Next: " + iter.next());
        }

        System.out.println();
        System.out.println("Testing RemoveAll");
        System.out.println("ArrList: " + arrList);
        System.out.println("RemoveAll (true): " + set.removeAll(arrList));
        System.out.println("ArrList: " + arrList);
        System.out.println("RemoveAll (false): " + set.removeAll(arrList));
        iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println("Next: " + iter.next());
        }
        arrList.clear();
        set.clear();
        for (int i = 1; i < 6; i++) {
            arrList.add(i);
        }
        set.addAll(arrList);
        arrList.remove(4);
        arrList.remove(0);
        System.out.println("ArrList: " + arrList);

        System.out.println();
        System.out.println("Testing RetainAll");
        System.out.println("RetainAll (true): " + set.retainAll(arrList));
        iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println("Next: " + iter.next());
        }
    }
}
