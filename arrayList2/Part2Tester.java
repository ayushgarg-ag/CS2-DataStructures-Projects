package cs2.arrayList2;

import java.util.Iterator;
import java.util.ListIterator;

public class Part2Tester {
    public static void main(String[] args) {

        // test constructor
        System.out.println("Create new empty list: {} and 0:");
        CS2ArrayList<Double> list = new CS2ArrayList<Double>();
        System.out.println(list);
        System.out.println(list.size());

        // test add
        System.out.println("\nAdd items: {[0]:0.0 [1]:1.0 [2]:18.0 [3]:19.0} and 4:");
        list.add(0.0);
        list.add(1.0);
        list.add(18.0);
        list.add(19.0);
        System.out.println(list);
        System.out.println(list.size());

        // test add at index
        System.out.println("\nFill remaining positions 0.0 through 19.0: {[0]:0.0 [1]:1.0 [2]:2.0 [3]:3.0 [4]:4.0 [5]:5.0 [6]:6.0 [7]:7.0 [8]:8.0 [9]:9.0 [10]:10.0 [11]:11.0 [12]:12.0 [13]:13.0 [14]:14.0 [15]:15.0 [16]:16.0 [17]:17.0 [18]:18.0 [19]:19.0}");
        for (int i = 2; i < 18; i++) {
            list.add(i, (double) i);
        }
        System.out.println(list);

        // force expansion
        System.out.println("\nadd a 20th item:");
        try {
            list.add(20, 20.0);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(list);

        // test get
        System.out.println("Testing gets - print values 0.0 through 20.0:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        // force exception
        System.out.println("\nAttempt to get invalid index (21) - catch error:");
        try {
            list.get(21);
        } catch (Exception e) {
            System.out.println(e);
        }

        // test set (will print 0 through 19, and set to 19 through 0)
        System.out.println("\nTest set: print retrieved values (0.0 through 20.0) and reset to 20.0 through 0.0");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.set(i, 20.0 - i) + " ");
        }
        System.out.println();
        System.out.println(list);

        // force exception
        System.out.println("\nAttempt to set invalid index (21) - catch error:");
        try {
            list.set(21, 3.0);
        } catch (Exception e) {
            System.out.println(e);
        }

        // test remove
        int size = list.size();
        System.out.println("\nTest removals - values 20.0 through 0.0 then empty list");
        for (int i = 0; i < size; i++) {
            System.out.print(list.remove(0) + " ");
        }
        System.out.println("\n" + list);

        // force exception
        System.out.println("\nAttempt to remove from an empty list - catch error");
        try {
            list.remove(0);
        } catch (Exception e) {
            System.out.println(e);
        }

        // test iterator

        System.out.println("\nTest Iterator: 20 random doubles -0.5 through 0.5 ");
        for (int i = 0; i < 20; i++) {
            list.add(Math.random() - 0.5);
        }
        Iterator<Double> it = list.iterator();
        while (it.hasNext()) {
            Double val = it.next();
            System.out.print(val + " ");
        }
        System.out.println();

        // test enhanced for

        System.out.println("\nTest enhanced for - same list as above");
        for (Double val : list) {
            System.out.print(val + " ");
        }
        System.out.println();

        // test iterator remove

        System.out.println("\nTest iterator remove: remove all negative values from the list:");
        it = list.iterator();
        while (it.hasNext()) {
            if (it.next() < 0.0) it.remove();
        }
        System.out.println(list);

        System.out.println("\nTest Iterator Exception cases");
        System.out.println("\nRemove from new iterator error:");
        it = list.iterator();
        try {
            it.remove();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        System.out.println("\nMultiple removes error:");
        it = list.iterator();
        it.next();
        try {
            it.remove();
            it.remove();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        System.out.println("\nCall next (when there is no next) error");
        it = list.iterator();
        while (it.hasNext()) it.next();
        try {
            it.next();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        // testing listIterator

        list = new CS2ArrayList<Double>();
        for (int i=0; i<5; i++) list.add (Math.random());
        System.out.println("\nNew Test List: "+list);

        System.out.println("\nCreating ListIterator");
        ListIterator<Double> iter = list.listIterator();

        System.out.println("\nLoop through using next/hasNext - Should print out same list as above");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        System.out.println("\nLoop through using previous/hasPrevious - Should print out same list but in reverse order");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println("\n");

        System.out.println("\nTesting nextIndex - should print out numbers 0 through mySize - 1");
        while (iter.hasNext()) {
            System.out.print(iter.nextIndex() + " ");
            iter.next();
        }
        System.out.println();

        System.out.println("\nTesting previousIndex - should print out numbers mySize - 1 through 0");
        while (iter.hasPrevious()) {
            System.out.print(iter.previousIndex() + " ");
            iter.previous();
        }
        System.out.println();

        System.out.println("\nTest set - set all values to 1.23 and print them");
        while (iter.hasNext()) {
            iter.next();
            iter.set(1.23);
        }
        System.out.println(list);
        System.out.println("\nTest set in reverse order - set all values to -1.23 and print them");
        while (iter.hasPrevious()) {
            iter.previous();
            iter.set(-1.23);
        }
        System.out.println(list);

        System.out.println("\nTest add - should print out above list but with 3.45 before each -1.23");
        while (iter.hasNext()) {
            iter.add(3.45);
            iter.next();
        }
        System.out.println(list.toString());
        System.out.println();

        System.out.println("\nTest removes in reverse order - remove all negatives");
        while (iter.hasPrevious()) {
            if (iter.previous() < 0) iter.remove();
        }
        System.out.println(list.toString());
        System.out.println();


        System.out.println("\nTest adds in reverse order - adds -1.23 after each 3.45");
        while (iter.hasNext()) iter.next();

        while (iter.hasPrevious()) {
            iter.add(-1.23);
            iter.previous();
            iter.previous();
        }
        System.out.println(list.toString());
        System.out.println();


        // Exception tester (all of these should throw exceptions)
        System.out.println("Testing Exceptions: ");
        System.out.println("\nTesting Next: ");
        while (iter.hasNext()) {
            iter.next();
        }
        try {
            iter.next();
        } catch (Exception e) {
            System.out.println("Caught error: trying to call next when there is no next element");
            System.out.println(e);
        }

        System.out.println("\nTesting Previous: ");
        while (iter.hasPrevious()) {
            iter.previous();
        }
        try {
            iter.previous();
        } catch (Exception e) {
            System.out.println("Caught error: trying to call previous when there is no previous element");
            System.out.println(e);
        }

        System.out.println("\nTesting Remove: ");
        iter.next();
        iter.remove();
        try {
            iter.remove();
        } catch (Exception e) {
            System.out.println("Caught error: trying to remove twice in a row");
            System.out.println(e);
        }
        iter.next();
        iter.add(7.89);
        try {
            iter.remove();
        } catch (Exception e) {
            System.out.println("Caught error: trying to remove after add");
            System.out.println(e);
        }

        System.out.println("\nTesting Set: ");
        try {
            iter.set(7.89);
        } catch (Exception e) {
            System.out.println("Caught error: trying to set after add");
            System.out.println(e);
        }
        iter.next();
        iter.remove();
        try {
            iter.set(7.89);
        } catch (Exception e) {
            System.out.println("Caught error: trying to set after remove");
            System.out.println(e);
        }
        // Theoretically this should test adding a String or other type of object, but even a try catch with this causes an error
        // Should add throw any exceptions??
    }
}