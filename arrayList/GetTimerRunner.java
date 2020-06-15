package cs2.arrayList;

public class GetTimerRunner {
    public static void main(String[] args) {
        CS2ArrayList<Double> list;

//        for (int i = 100; i <= 1000; i = i + 50) {
//            list = new CS2ArrayList<Double>();
//            long time;
//            int count;
//        }
        int n = 51;                    // list size
        long trials = 1000000;          // number of trials

        // create arrays to keep track of timing values
        int [] time = new int[n];
        int [] count = new int[n];

        Double val = null;

        for (int i = 100; i <= 5000; i = i + 100) {
            list = new CS2ArrayList<Double>();

            for (int r = 0; r < i; r++) {
                list.add(Math.random());
            }

            for (int j = 0; j < trials; j++) {
                int index = (int)(Math.random()*i);

                // time get operation
                long start = System.nanoTime();
                val = list.get(index);
                long end = System.nanoTime();

                // add elapsed time to the total time for that list size and increment count
                time[i/100] += (end - start);
                count[i/100]++;
            }
        }

        // print results as averages (data value/count)
        System.out.println("Get times\n");
        System.out.println("List Size\ttime");

        for (int i = 1; i < time.length; i++) {
            System.out.println(i*100 + "\t" + (double)time[i]/count[i]);
        }
    }
}