package cs2.hash;

public class ClassTester {
    public static void main(String[] args) {
        System.out.println("Testing Constructor");
        CS2HashMap<Integer, Double> map = new CS2HashMap<>(5);
        System.out.println("Test size. Prints 0");
        System.out.println(map.size());

        System.out.println("Testing put");
        for (int i=0; i<10; i++) {
            map.put (i, Math.random());
        }
        System.out.println("Test size. Prints 10");
        System.out.println(map.size());

        System.out.println("Test gets for known values - print key/val pairs");

        for (int i=0; i<10; i++) {
            System.out.println("Key: "+i+" Value: "+map.get(i));
        }
        System.out.println("Test get for invalid key");
        System.out.println("Key 10 Value: "+map.get(10));

        System.out.println("Test toString");
        System.out.println(map);

        System.out.println("Test clear, size, toString again");
        map.clear();
        System.out.println("List Cleared\n size: " + map.size());
        System.out.println(map);

        // refill - new random values
        for (int i=0; i<10; i++) {
            map.put (i, Math.random());
        }
        System.out.println("Refilled list is now: "+ map);
        System.out.println("Check for Keys - 10 trues and a false");
        for (int i=0; i<11; i++) {
            System.out.println("Contains key "+i+": "+map.containsKey(i));
        }

        System.out.println("Checks for values - all values and false");
        for (int i=0; i<10; i++) {
            Double val = map.get(i);
            System.out.println("Contains value "+val+": "+map.containsValue(val));
        }
        System.out.println("Tests containsValue for invalid");
        System.out.println("Map contains -1?: "+map.containsValue(-1.0));

        System.out.println("Tests keySet with enhanced for - prints all key/val again");
        for (Integer i: map.keySet()) {
            System.out.println("Key: "+i+" Value: "+map.get(i));
        }

        System.out.println("Tests values with enhanced for - prints all vals again");
        for (Double v: map.values()) {
            System.out.println("Value: "+v);
        }

        System.out.println("Attempt removal of invalid key");
        System.out.println("Removing Key: -1 Value: "+map.remove(-1));

        System.out.println("Tests removes and isEmpty by removing all");
        for (Integer i: map.keySet()) {
            System.out.println("Removing Key: "+i+" Value: "+map.remove(i)+" Empty: "+map.isEmpty());
        }

    }
}