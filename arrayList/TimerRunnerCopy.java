package cs2.arrayList;

public class TimerRunnerCopy {
    public static void main(String[] args) {
        // create a new arraylist
        CS2ArrayList<Double> list;
        int n = 1000;                    // list size
        long trials = 1000000;          // number of trials

        // fill with n Double values


        // create arrays to keep track of timing values
        int [] time = new int[n + 1];
        int [] count = new int[n + 1];

        int listSize = 0;

        // time get operation
        Double val = null;
        for (int i=0; i<trials; i++) {

            list = new CS2ArrayList<Double>();

            for (int j = 0; j < listSize; j++) {
                list.add(Math.random());
            }

            // pick a random index (you want to avoid sequential access)
            int index = (int)(Math.random()*listSize);

            // time get operation
            long start = System.nanoTime();
            val = list.get(index);
            long end = System.nanoTime();

            // add elapsed time to the total time for that index and increment count
            time[listSize]+=(end-start);
            count[listSize]++;
        }

        // print results as averages (data value/count)
        System.out.println("Get times\n");
        System.out.println("ListSize\ttime");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + (double)time[i]/count[i]);
        }
    }
}