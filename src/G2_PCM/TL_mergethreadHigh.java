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
        TL_MergeSort.outputHigh1 = new int[anArray.length];
        TL_MergeSort.outputHigh1 = anArray;
        MergeSort.mergesort(TL_MergeSort.outputHigh1);
    }
}