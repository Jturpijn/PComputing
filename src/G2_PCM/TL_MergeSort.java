package G2_PCM;

import java.util.ArrayList;
import java.util.List;

public class TL_MergeSort {

    private static int[] array;
    static List<Integer> low = new ArrayList<Integer>();
    static List<Integer> high = new ArrayList<Integer>();
    static int[] outputLow1;
    static int[] outputLow2;
    static int[] outputHigh1;
    static int[] outputHigh2;
    
    public static void main(int[] anArray) throws InterruptedException{
        // unsorted array 
        array = new int[anArray.length]; array = anArray;

        // split array in lower and higher half
        for (int i = 0; i < array.length; i++) {
            if(array[i] <= (array[array.length/2])) { low.add(array[i]); } 
            else { high.add(array[i]); }
        }

        // set up threads 
        Thread st1 = new Thread(new TL_splitterthread(low, outputLow1, outputLow2));
        Thread st2 = new Thread(new TL_splitterthread(high, outputHigh1, outputHigh2));
        // Thread mt1 = new Thread(new TL_mergethreadHigh(outputLow1));
        // Thread mt2 = new Thread(new TL_mergethreadHigh(outputLow2));
        // Thread mt3 = new Thread(new TL_mergethreadHigh(outputHigh1));
        // Thread mt4 = new Thread(new TL_mergethreadHigh(outputHigh2));
        st1.start(); st2.start();
        st1.join(); st2.join();

        Utils.print(outputLow1);
        
    }
}