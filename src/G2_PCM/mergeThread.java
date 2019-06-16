package G2_PCM;

import java.util.*;

public class mergeThread implements Runnable {
    private int listIndex;
    private List<Integer> list;
    private int[] arr;

    static ArrayList<List<Integer>> allLists = TL_MergeSort.allLists;
    public mergeThread(int index) {
        listIndex = index;
        new Thread(this).start();
    }
    public void run() {
        list = allLists.get(listIndex);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        MergeSort.mergesort(arr);


        List<Integer> temp = new ArrayList<>();

        for(int i = 0; i<arr.length;i++) {
            temp.add(arr[i]);
        }


        allLists.set(listIndex, temp);
        TL_MergeSort.counter++;
    }
}