package G2_PCM;

import java.util.LinkedList;
import java.util.List;

import static G2_PCM.Utils.print;
import static java.lang.Thread.sleep;

public class TL_MergeSort {

    static List<List<Integer>> splitLists = new LinkedList<>();
    static int[] array;
    static int MAX;
    static int CORES;
    static boolean first_layer = false;
    static boolean second_layer = false;
    static boolean third_layer = false;
    static boolean finished_merging = false;

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

        new TL_splitThread(1);

        while(!first_layer) { Thread.sleep(1); }

            for (int j=0; j<2; j++) {
                new TL_splitThread(j);
            }

        while(!second_layer) { Thread.sleep(5); }

            for (int j=0; j<4; j++) {
                new TL_splitThread(j);
            }

        while(!third_layer) { Thread.sleep(5); }

            for (int k=0; k<CORES; k++) {
                if(finished_merging) {
                    break;
                } else {
                    new TL_mergeThread(k);
                    Thread.sleep(5);
                }
            }

        System.out.println("Finished sorting ");
//        for (List<Integer> list : splitLists) {
//            System.out.print("list number : " + splitLists.indexOf(list) + "  ");
//            print(list.stream().mapToInt(Integer::intValue).toArray());
//        }
    }
}