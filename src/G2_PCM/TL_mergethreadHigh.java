package G2_PCM;

import java.util.List;

public class TL_mergethreadHigh implements Runnable {
    private int[] anArray;
    public TL_mergethreadHigh(List<Integer> list) {
        anArray = new int[list.size()];
        for (int i=0;i<anArray.length;i++) {
            anArray[i] = list.get(i);
        }
    }

    public void run() {
        System.out.println("Thread start");
        TL_MergeSort.outputHigh = new int[anArray.length];
        TL_MergeSort.outputHigh = anArray;
        MergeSort.mergesort(TL_MergeSort.outputHigh);
    }
}