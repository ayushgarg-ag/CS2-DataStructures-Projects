package cs2.heap;

public class HeapTimer {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<Integer>();
        System.out.println("Heap Size\tAdd Time\tRemove Time");
        int [] num = new int[200];
        long [] add = new long[200];
        long [] remove = new long[200];
        long addStart;
        long addEnd;
        long removeStart;
        long removeEnd;
        long totalAdd = 0;
        long totalRemove = 0;

        for (int n = 1000; n < 200000; n += 1000) {
            heap = new Heap<Integer>();
            num[n / 1000] = n;

            for (int i = 0; i < n; i++) {
                addStart = System.nanoTime();
                heap.add((int)(Math.random() * 500));
                addEnd = System.nanoTime();
                totalAdd += (addEnd - addStart);
            }
            add[n / 1000] = totalAdd;

            for (int i = n; i > 0; i--) {
                removeStart = System.nanoTime();
                heap.remove();
                removeEnd = System.nanoTime();
                totalRemove += (removeEnd - removeStart);
            }
            remove[n / 1000] = totalRemove;
        }

        for (int i = 1; i < 200; i++) {
            System.out.println(num[i] + "\t" + add[i]/(i*1000) + "\t" + remove[i]/(i*1000));
        }
    }
}
