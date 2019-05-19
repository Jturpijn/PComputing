package G2_PCM;

import java.util.ArrayList;
import java.util.List;

public class TL_MergeSort {
    final static int SIZE = 500000;
    final static int MAX = 100;
    final int two_threads = 2;
    final int three_threads = 3;
    final int four_threads = 4;

    final static int[] array = Utils.random(SIZE, MAX);
    final static int originalMiddle = array[SIZE/2];
    static List<Integer> low = new ArrayList<Integer>();
    static List<Integer> high = new ArrayList<Integer>();
    static int[] outputLow;
    static int[] outputHigh;
    
    public static void main(String[] args) throws InterruptedException{
        // unsorted array 
        // Utils.print(array);

        // set starting timer
        long start = System.currentTimeMillis();

        // split array in lower and higher half
        for (int i = 0; i < array.length; i++) {
            if(array[i] <= originalMiddle) { low.add(array[i]); } 
            else { high.add(array[i]); }
        }

        // set up threads 
        Thread t1 = new Thread(new TL_mergethreadLow(low));
        Thread t2 = new Thread(new TL_mergethreadHigh(high));
        t1.start(); t2.start();
        t1.join();  t2.join();
        
        

        // stop timer and output results
        long end = System.currentTimeMillis();
        System.out.println("Type: Threads & Locks MergeSort");
        System.out.println("\t Amount of elements: " + SIZE);
        System.out.println("\t Total time in milliseconds: " + (end - start));
        // System.out.println("\t Sorted array : "); Utils.print(outputLow); Utils.print(outputHigh);
    }
}