package cs2.queue;

public class CS2QueueRunner {

    public static void main(String[] args) {
        CS2PriorityQueue<Integer> q = new CS2PriorityQueue<>();
        System.out.println(q); // Empty
        System.out.println(q.peek()); // null
        for (int i = 0; i < 10; i++) {
            q.add((int) (Math.random() * 10.));
            System.out.println(q); // list
        }
        System.out.println("Front: " + q.element());
        for (int i = 0; i < 10; i++) {
            System.out.println(q.remove() + " : " + q);
        }
        System.out.println(q);
        try {
            System.out.println("Front: " + q.element());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
