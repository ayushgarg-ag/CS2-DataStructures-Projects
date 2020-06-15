package cs2.linkedList;

public class Part1Tester {
    public static void main(String[] args) {

        // test constructor
        System.out.println("Create new empty list: {} and 0:");
        CS2LinkedList<Double> list = new CS2LinkedList<Double>();
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
    }
}
