package G2_PCM;

import java.util.*;

public class mergeThread implements Runnable {
    private int max;
    private int counted;
    private int[] arr;
    private int[] output = new int[counted];
    private List<Integer> list;

    static ArrayList<List<Integer>> allLists = TL_MergeSort.allLists;
    public mergeThread(int index, int[] bucket, int counter, int[] anArray) {
        max = bucket[0];
        counted = counter;
        arr = anArray;
        new Thread(this).start();
    }
    public void run() {
        int[] tobesorted = new int[counted];
        int count=0;

        for(int i = 0; i<counted;i++) {
            if(count >= counted) { break; }
            for(int j =0; j<arr.length; j++) {

            }
        }

    }
}