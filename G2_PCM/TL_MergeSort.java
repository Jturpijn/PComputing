package G2_PCM;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static G2_PCM.Utils.print;
import static java.lang.Thread.sleep;

public class TL_MergeSort {

    static List<List<Integer>> splitLists = new ArrayList<>();
    static int[] array;
    static int MAX;
    static int CORES;
    static int finished_splitting = 0;
    static int finished_merging = 0;

    public static void main(int provCores, int highestValue, int[] anArray) throws InterruptedException{
        List<Integer> tempLow = new ArrayList<>();
        List<Integer> tempHigh = new ArrayList<>();

        // unsorted array 
        MAX = highestValue;
        CORES = provCores; // provided cores
        array = new int[anArray.length]; array = anArray;

        // create subarrays
        for(int subs=0;subs<CORES;subs++) {
            List<Integer> todo = new LinkedList<>();
            splitLists.add(todo);
        }

        // split into two

        for(int i=0;i<array.length;i++) {
            if (array[i] < (MAX/2)) {
                tempLow.add(array[i]);
            } else {
                tempHigh.add(array[i]);
            }
        }
        splitLists.set(0, tempLow);
        splitLists.set(4, tempHigh);

            new TL_splitThread(0, 2, (MAX/4));
            new TL_splitThread(4, 6, (MAX-(MAX/4)));

        while(finished_splitting != 2) { Thread.sleep(5); }

            new TL_splitThread(0, 1, (MAX/8));
            new TL_splitThread(2, 3, ((MAX/2)-(MAX/8)));
            new TL_splitThread(4, 5, ((MAX/2)+(MAX/8)));
            new TL_splitThread(6, 7, ((MAX)-(MAX/8)));

        while(finished_splitting != 6) { Thread.sleep(5); }
            for (int k=0; k<splitLists.size(); k++) {
                if(finished_merging == (splitLists.size())) {
                    break;
                } else {
                    new TL_mergeThread(k);
                    Thread.sleep(2);
                }
            }

        while(finished_merging != splitLists.size()) { Thread.sleep(2); }

//        System.out.println("Finished sorting ");
//        for (List<Integer> list : splitLists) {
//            System.out.print("list number : " + splitLists.indexOf(list) + "  ");
//            print(list.stream().mapToInt(Integer::intValue).toArray());
//        }
    }
}