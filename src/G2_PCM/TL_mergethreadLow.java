package G2_PCM;

import java.util.List;

public class TL_mergethreadLow implements Runnable {
    private int[] anArray;
    public TL_mergethreadLow(List<Integer> list) {
        anArray = new int[list.size()];
        for (int i=0;i<anArray.length;i++) {
            anArray[i] = list.get(i);
        }
    }

    public void run() {
        TL_MergeSort.outputLow = new int[anArray.length];
        TL_MergeSort.outputLow = anArray;
        MergeSort.mergesort(TL_MergeSort.outputLow);
    }
}