package G2_PCM;

import java.util.ArrayList;
import java.util.List;

import static G2_PCM.TL_MergeSort.*;
import static G2_PCM.TL_MergeSort.array;
import static G2_PCM.Utils.split;
import static G2_PCM.Utils.print;

public class TL_splitThread implements Runnable {
    List<Integer> tempLow = new ArrayList<>();
    List<Integer> tempHigh = new ArrayList<>();
    int indexLow;
    int indexHigh;
    int pivot;

    public TL_splitThread(int indexL, int indexH, int piv) {
        indexLow = indexL; indexHigh = indexH; pivot = piv;
        new Thread(this).start();
    }

    public void run() {
//        System.out.println("splitthread for part " + pivot + " has started.");

            splitLists.get(indexHigh).add(1);
            split(tempLow, tempHigh, indexLow, pivot);
            splitLists.set(indexLow, tempLow);
            splitLists.set(indexHigh, tempHigh);
            finished_splitting++;
    }
}