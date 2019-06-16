package G2_PCM;

import java.sql.SQLOutput;
import java.util.*;


public class TL_MergeSort {

    private static int[] array;
    static ArrayList<List<Integer>> allLists = new ArrayList<>();
    static int CORES;
    static int MAX;
    static int counter=0;
    static boolean donesorting = false;
    
    public static void main(int provCores, int highestValue, int[] anArray) throws InterruptedException{
        // unsorted array 
        MAX = highestValue;
        CORES = provCores;
        array = new int[anArray.length]; array = anArray;

        // create subarrays
        for(int subs=0;subs<CORES;subs++) {
            allLists.add(subs, new ArrayList<>());
        }

        Utils.splitIntoCores(MAX, CORES, array);

        for (int j=0; j<CORES; j++) {
            new mergeThread(j).run();
        }

//        for (List<Integer> list : allLists) {
//            System.out.print("list number : " + allLists.indexOf(list) + "  ");
//            Utils.print(list.stream().mapToInt(Integer::intValue).toArray());
//        }
    }
}