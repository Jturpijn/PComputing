package G2_PCM;

import java.util.LinkedList;
import java.util.List;

public class TL_MergeSort {

    static volatile List<List<Integer>> splitLists = new LinkedList<>();
    static int[] array;
    static int MAX;
    static int CORES;

    public static void main(int provCores, int highestValue, int[] anArray) throws InterruptedException{
        // unsorted array 
        MAX = highestValue;
        CORES = provCores; // provided cores
        array = new int[anArray.length]; array = anArray;

        // create subarrays
        for(int subs=0;subs<CORES;subs++) {
            List<Integer> todo = new LinkedList<>();
            splitLists.add(todo);
        }

        for (int j=0; j<CORES; j++) {
            new TL_splitThread(j).run();
        }

        for (int j=0; j<CORES; j++) {
            new TL_mergeThread(j).run();
        }

        System.out.println("Finished sorting ");
//        for (List<Integer> list : splitLists) {
//            System.out.print("list number : " + splitLists.indexOf(list) + "  ");
//            print(list.stream().mapToInt(Integer::intValue).toArray());
//        }
    }
}