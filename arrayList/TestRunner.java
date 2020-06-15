package cs2.arrayList;

import java.sql.SQLOutput;

public class TestRunner {
    public static void main(String[] args) {

        // test constructor
        CS2ArrayList<Double> list = new CS2ArrayList<Double>();
        System.out.println(list);
        System.out.println(list.size());

        // test add
        list.add (0.0);
        list.add (1.0);
        list.add (18.0);
        list.add (19.0);
        System.out.println(list);
        System.out.println(list.size());

        // test add at index
        for (int i=2; i<18; i++) {
            list.add(i, (double) i);
            System.out.println(list);
        }
        System.out.println();

        // force exception - index out of bounds
        try{
            list.add(20.0);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // test get
        for (int i=0; i<20; i++) {
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

        // force exception
        try{
            list.get(20);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // test set (will print 0 through 19, and set to 19 through 0)
        for (int i=0; i<20; i++) {
            System.out.print(list.set(i, 19.0-i) + " ");
        }
        System.out.println();
        System.out.println(list);

        // force exception
        try{
            list.set(20, 3.0);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // indexOf
        System.out.println("Index of value 2.0: " + list.indexOf(2.0));


        // test remove
        for (int i=0; i<20; i++) {
            System.out.println(list.remove(0) + " ");
            System.out.println(list);
        }

        // force exception
        try{
            list.remove(0);
        }
        catch (Exception e) {
            System.out.println(e);
        }


        // test isEmpty
        System.out.println("List is empty: " + list.isEmpty());


        list.add(1.0);
        System.out.println(list);

        // test contains
        System.out.println("List contains 1.0: " + list.contains(1.0));
        System.out.println("List contains 2.0: " + list.contains(2.0));

        // test clear
        list.clear();
        System.out.println("List is cleared: " + list);

        // test equals method
        list.add(1.0);
        list.add(2.0);

        CS2ArrayList<Double> newList = new CS2ArrayList<Double>();
        newList.add(1.0);
        newList.add(2.0);

        System.out.println("Lists are equal: " + list.equals(newList));

    }


}